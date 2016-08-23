/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.remote;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import com.zegates.sanctus.services.remote.LogUser;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sandaruwan
 */
public interface LogUserController extends Serializable {

    void create(LogUser logUser) throws ClassNotFoundException, SQLException;

    void destroy(Long id) throws NonexistentEntityException;

    void edit(LogUser logUser) throws NonexistentEntityException, Exception;

    LogUser findLogUser(Long id);

    List<LogUser> findLogUserEntities();

    List<LogUser> findLogUserEntities(int maxResults, int firstResult);

    int getLogUserCount();
    
}
