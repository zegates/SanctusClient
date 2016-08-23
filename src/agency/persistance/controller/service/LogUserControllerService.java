/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.service;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.controller.remote.LogUserController;
import com.zegates.sanctus.services.remote.LogUser;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import localhost.agency.loguser.LogUserService;
import localhost.agency.loguser.LogUserServiceRemote;

/**
 *
 * @author sandaruwan
 */
public class LogUserControllerService implements LogUserController {

    private LogUserServiceRemote logUserService;

    public LogUserControllerService() {
        logUserService = new LogUserService().getLogUserServicePort();
    }
    

    @Override
    public void create(LogUser logUser) throws ClassNotFoundException, SQLException {
        com.zegates.sanctus.services.remote.LogUser lu = mapLogUser(logUser);
        logUserService.create(lu);
    }

    @Override
    public void destroy(Long id) throws NonexistentEntityException {
        logUserService.destroy(id);
    }

    @Override
    public void edit(LogUser logUser) throws NonexistentEntityException, Exception {
        com.zegates.sanctus.services.remote.LogUser mapLogUser = mapLogUser(logUser);
        logUserService.edit(mapLogUser);   
    }

    @Override
    public LogUser findLogUser(Long id) {
        com.zegates.sanctus.services.remote.LogUser findLogUser = logUserService.findLogUser(id);
        return mapRemoteLogUser(findLogUser);
    }

    @Override
    public List<LogUser> findLogUserEntities() {
        List<com.zegates.sanctus.services.remote.LogUser> item = logUserService.findLogUserEntities().getItem();
        List<LogUser> logUsers = new ArrayList<>();

        for (com.zegates.sanctus.services.remote.LogUser lu : item) {
            LogUser logUser = mapRemoteLogUser(lu);
            logUsers.add(logUser);
        }
        return logUsers;
    }

    @Override
    public List<LogUser> findLogUserEntities(int maxResults, int firstResult) {
        List<com.zegates.sanctus.services.remote.LogUser> item = logUserService.findLogUserEntitiesLimit(maxResults, firstResult).getItem();
        List<LogUser> logUsers = new ArrayList<>();

        for (com.zegates.sanctus.services.remote.LogUser lu : item) {
            LogUser logUser = mapRemoteLogUser(lu);
            logUsers.add(logUser);
        }
        return logUsers;
    }

    @Override
    public int getLogUserCount() {
        return logUserService.getLogUserCount();
    }

    private com.zegates.sanctus.services.remote.LogUser mapLogUser(LogUser logUser) {
        com.zegates.sanctus.services.remote.LogUser lu = new com.zegates.sanctus.services.remote.LogUser();
        lu.setAddress(logUser.getAddress());
        lu.setName(logUser.getName());
        lu.setPw(logUser.getPw());
        lu.setUid(logUser.getUid());
        lu.setUsername(logUser.getUsername());
        return lu;
    }

    private LogUser mapRemoteLogUser(com.zegates.sanctus.services.remote.LogUser logUser) {
        LogUser lu = new LogUser();
        lu.setAddress(logUser.getAddress());
        lu.setName(logUser.getName());
        lu.setPw(logUser.getPw());
        lu.setUid(logUser.getUid());
        lu.setUsername(logUser.getUsername());

        return lu;
    }

}
