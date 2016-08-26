/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.service;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.controller.remote.SupplyOrderDetailController;
import com.zegates.sanctus.services.remote.SupplyOrderDetail;
import java.sql.SQLException;
import java.util.List;
import localhost.agency.supplyorderdetail.SupplyOrderDetailService;
import localhost.agency.supplyorderdetail.SupplyOrderDetailServiceRemote;

/**
 *
 * @author sandaruwan
 */
public class SupplyOrderDetailControllerService implements SupplyOrderDetailController {

    private final SupplyOrderDetailServiceRemote orderDetailService;

    public SupplyOrderDetailControllerService() {
        orderDetailService = new SupplyOrderDetailService().getSupplyOrderDetailServicePort();
    }

    @Override
    public void create(SupplyOrderDetail supplyOrderDetail) throws ClassNotFoundException, ClassNotFoundException, SQLException {
        orderDetailService.create(supplyOrderDetail);
    }

    @Override
    public void destroy(Long id) throws NonexistentEntityException {
        orderDetailService.destroy(id);
    }

    @Override
    public void edit(SupplyOrderDetail supplyOrderDetail) throws NonexistentEntityException, Exception {
        orderDetailService.edit(supplyOrderDetail);
    }

    @Override
    public SupplyOrderDetail findSupplyOrderDetail(Long id) {
        return orderDetailService.findSupplyOrderDetail(id);
    }

    @Override
    public List<SupplyOrderDetail> findSupplyOrderDetailEntities() {
        return orderDetailService.findSupplyOrderDetailEntities().getItem();
    }

    @Override
    public List<SupplyOrderDetail> findSupplyOrderDetailEntities(int maxResults, int firstResult) {
        return orderDetailService.findSupplyOrderDetailEntitiesLimit(maxResults, firstResult).getItem();
    }

    @Override
    public List<SupplyOrderDetail> findSupplyOrderDetailsByItemCode(Long id) {
        return orderDetailService.findSupplyOrderDetailsByItemCode(id).getItem();
    }

    @Override
    public int getSupplyOrderDetailCount() {
        return orderDetailService.getSupplyOrderDetailCount();
    }

}
