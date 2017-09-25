package ua.nure.dorotenko.db.services.interfaces;

import ua.nure.dorotenko.entities.Role;
import ua.nure.dorotenko.exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.List;

public interface RoleService {
    Role getRoleById(long id) throws DatabaseException;
}
