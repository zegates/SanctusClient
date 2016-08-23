/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.remote;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import com.zegates.sanctus.services.remote.LogSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sandaruwan
 */
public interface LogSessionController extends Serializable {

    void create(LogSession logSession) throws ClassNotFoundException, ClassNotFoundException, SQLException;

    void destroy(Long id) throws NonexistentEntityException;

    void edit(LogSession logSession) throws NonexistentEntityException, Exception;

    LogSession findLogSession(Long id);

    List<LogSession> findLogSessionEntities();

    List<LogSession> findLogSessionEntities(int maxResults, int firstResult);

    int getLogSessionCount();
    
}
