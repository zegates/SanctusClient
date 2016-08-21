/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.remote;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.entity.OrderDetail;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author sandaruwan
 */
public interface OrderDetailController extends Serializable {

    void create(OrderDetail orderDetail);

    void destroy(Long id) throws NonexistentEntityException;

    void edit(OrderDetail orderDetail) throws NonexistentEntityException, Exception;

    OrderDetail findOrderDetail(Long id);

    List<OrderDetail> findOrderDetailEntities();

    List<OrderDetail> findOrderDetailEntities(int maxResults, int firstResult);

    int getOrderDetailCount();
    
}
