/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.service;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.controller.remote.LogSessionController;
import com.zegates.sanctus.services.remote.LogSession;
import java.sql.SQLException;
import java.util.List;
import localhost.agency.logsession.LogSessionService;
import localhost.agency.logsession.LogSessionServiceRemote;

/**
 *
 * @author sandaruwan
 */
public class LogSessionControllerService implements LogSessionController{
    
    private final LogSessionServiceRemote logSessionService;

    public LogSessionControllerService() {
        logSessionService = new LogSessionService().getLogSessionServicePort();
    }
    

    @Override
    public void create(LogSession logSession) throws ClassNotFoundException, ClassNotFoundException, SQLException {
        logSessionService.create(logSession);
    }

    @Override
    public void destroy(Long id) throws NonexistentEntityException {
        logSessionService.destroy(id);
    }

    @Override
    public void edit(LogSession logSession) throws NonexistentEntityException, Exception {
        logSessionService.edit(logSession);
    }

    @Override
    public LogSession findLogSession(Long id) {
        return logSessionService.findLogSession(id);
    }

    @Override
    public List<LogSession> findLogSessionEntities() {
            
        
        return logSessionService.findLogSessionEntities().getItem();
    }

    @Override
    public List<LogSession> findLogSessionEntities(int maxResults, int firstResult) {
        return logSessionService.findLogSessionEntitiesLimit(maxResults, firstResult).getItem();
    }

    @Override
    public int getLogSessionCount() {
        return logSessionService.getLogSessionCount();
    }
    
}
