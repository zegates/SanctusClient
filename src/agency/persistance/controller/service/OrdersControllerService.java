/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.service;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.controller.remote.OrdersController;
import com.zegates.sanctus.services.remote.Orders;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import localhost.agency.ordersservice.OrdersService;
import localhost.agency.ordersservice.OrdersServiceRemote;

/**
 *
 * @author sandaruwan
 */
public class OrdersControllerService extends Observable implements OrdersController{

    private final OrdersServiceRemote ordersService;

    public OrdersControllerService() {
        ordersService = new OrdersService().getOrdersServicePort();
    }
    
    
    
    @Override
    public void create(Orders orders) throws ClassNotFoundException, SQLException {
        ordersService.create(orders);
    }

    @Override
    public void destroy(Long id) throws NonexistentEntityException, ClassNotFoundException, SQLException {
        ordersService.destroy(id);
    }

    @Override
    public void edit(Orders orders) throws NonexistentEntityException, Exception {
        ordersService.edit(orders);
    }

    @Override
    public Orders findOrders(Long id) {
        return ordersService.findOrders(id);
    }

    @Override
    public List<Orders> findOrdersEntities() {
        return ordersService.findOrdersEntities().getItem();
    }

    @Override
    public List<Orders> findOrdersEntities(int maxResults, int firstResult) {
        return ordersService.findOrdersEntitiesLimit(maxResults, firstResult).getItem();
    }

    @Override
    public List<Orders> findOrdersEntitiesPaidfalse(boolean paid) {
        return ordersService.findOrdersEntitiesPaidfalse(paid).getItem();
    }

    @Override
    public long getLatesOrdersID() {
        return ordersService.getLatesOrdersID();
    }

    @Override
    public int getOrdersCount() {
        return ordersService.getOrdersCount();
    }

    @Override
    public void setChanged() {
        
    }

    @Override
    public void notifyObservers(String s) {
        super.notifyObservers(s);
    }

    
}
