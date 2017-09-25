package ua.nure.dorotenko.db.dao;

import org.apache.log4j.Logger;
import ua.nure.dorotenko.db.dao.interfaces.UnblockRequestDAO;
import ua.nure.dorotenko.entities.UnblockRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PgUnblockRequestDAO implements UnblockRequestDAO {
    private static PgUnblockRequestDAO instance;
    private static final String SELECT_REQUEST_BY_ID = "SELECT * FROM unblock_requests WHERE id=?";
    private static final String SELECT_ALL_REQUESTS = "SELECT * FROM unblock_requests WHERE satisfied=FALSE";
    private static final String UPDATE_REQUEST_STATUS = "UPDATE unblock_requests SET satisfied=? WHERE id=?";
    private static final String INSERT_REQUEST = "INSERT INTO unblock_requests VALUES(DEFAULT, ?, ?, ?)";

    private PgUnblockRequestDAO(){

    }

    public synchronized static PgUnblockRequestDAO getInstance(){
        if(instance == null){
            instance = new PgUnblockRequestDAO();
        }
        return instance;
    }
    @Override
    public UnblockRequest getRequestById(long id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REQUEST_BY_ID);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return extractRequest(resultSet);
        }
        return null;
    }

    @Override
    public void saveUnblockRequest(UnblockRequest unblockRequest, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST);
        preparedStatement.setLong(1, unblockRequest.getAccountId());
        preparedStatement.setTimestamp(2, new Timestamp(unblockRequest.getDatetime().getTime()));
        preparedStatement.setBoolean(3, unblockRequest.isSatisfied());
        preparedStatement.execute();
    }

    @Override
    public List<UnblockRequest> getAllReqestsUnsatisfied(Connection connection) throws SQLException {
        List<UnblockRequest> unblockRequests = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_REQUESTS);
        while(resultSet.next()){
            unblockRequests.add(extractRequest(resultSet));
        }
        return unblockRequests;
    }

    @Override
    public void changeRequestStatusById(boolean flag, long id, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST_STATUS);
        preparedStatement.setBoolean(1, flag);
        preparedStatement.setLong(2, id);
        preparedStatement.execute();
    }

    @Override
    public UnblockRequest extractRequest(ResultSet resultSet) throws SQLException {
        UnblockRequest request = new UnblockRequest();
        request.setId(resultSet.getLong("id"));
        request.setAccountId(resultSet.getLong("account_id"));
        request.setDatetime(resultSet.getTimestamp("datetime"));
        request.setSatisfied(resultSet.getBoolean("satisfied"));
        return request;
    }
}
