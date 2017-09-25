package ua.nure.dorotenko.db.services.interfaces;

import ua.nure.dorotenko.entities.UnblockRequest;
import ua.nure.dorotenko.exceptions.DatabaseException;

import java.util.List;

public interface UnblockRequestService {
    List<UnblockRequest> getAllUnsatisfiedUnblockRequest() throws DatabaseException;
    void changeUnblockRequestStatusToSatisfied(long id) throws DatabaseException;
    void saveUnblockRequest(UnblockRequest unblockRequest) throws DatabaseException;
}
