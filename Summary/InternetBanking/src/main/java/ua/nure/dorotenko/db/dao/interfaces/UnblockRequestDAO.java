package ua.nure.dorotenko.db.dao.interfaces;

import ua.nure.dorotenko.entities.UnblockRequest;
import ua.nure.dorotenko.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UnblockRequestDAO {
    UnblockRequest getRequestById(long id, Connection connection) throws SQLException;
    List<UnblockRequest> getAllReqestsUnsatisfied(Connection connection) throws SQLException;
    void changeRequestStatusById(boolean flag, long id, Connection connection) throws SQLException;
    void saveUnblockRequest(UnblockRequest unblockRequest, Connection connection) throws SQLException;
    UnblockRequest extractRequest(ResultSet resultSet) throws SQLException;
}
