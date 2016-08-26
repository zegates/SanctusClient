/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.service;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.controller.remote.OrderDetailController;
import com.zegates.sanctus.services.remote.OrderDetail;
import java.util.List;
import localhost.agency.orderdetail.OrderDetailService;
import localhost.agency.orderdetail.OrderDetailServiceRemote;

/**
 *
 * @author sandaruwan
 */
public class OrderDetailControllerService implements OrderDetailController{
    
//    private OrderDeta
    private final OrderDetailServiceRemote detailService;

    public OrderDetailControllerService() {
        detailService = new OrderDetailService().getOrderDetailServicePort();
    }
    

    @Override
    public void create(OrderDetail orderDetail) {
        detailService.create(orderDetail);
    }

    @Override
    public void destroy(Long id) throws NonexistentEntityException {
        detailService.destroy(id);
    }

    @Override
    public void edit(OrderDetail orderDetail) throws NonexistentEntityException, Exception {
        detailService.edit(orderDetail);
    }

    @Override
    public OrderDetail findOrderDetail(Long id) {
        return detailService.findOrderDetail(id);
    }

    @Override
    public List<OrderDetail> findOrderDetailEntities() {
        return detailService.findOrderDetailEntities().getItem();
    }

    @Override
    public List<OrderDetail> findOrderDetailEntities(int maxResults, int firstResult) {
        return detailService.findOrderDetailEntitiesLimit(maxResults, firstResult).getItem();
    }

    @Override
    public int getOrderDetailCount() {
        return detailService.getOrderDetailCount();
    }
    
}
