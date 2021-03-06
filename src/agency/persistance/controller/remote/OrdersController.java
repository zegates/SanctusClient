/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.remote;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import com.zegates.sanctus.services.remote.Orders;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author sandaruwan
 */
public interface OrdersController extends Serializable {

    void create(Orders orders) throws ClassNotFoundException, SQLException;

    void destroy(Long id) throws NonexistentEntityException, ClassNotFoundException, SQLException;

    void edit(Orders orders) throws NonexistentEntityException, Exception;

    Orders findOrders(Long id);

    List<Orders> findOrdersEntities();

    List<Orders> findOrdersEntities(int maxResults, int firstResult);

    List<Orders> findOrdersEntitiesPaidfalse(boolean paid);

    long getLatesOrdersID();

    int getOrdersCount();

    void setChanged();
    void notifyObservers(String s);
    void addObserver(Observer o);
    
}
