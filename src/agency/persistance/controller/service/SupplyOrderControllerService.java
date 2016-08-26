/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.service;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.controller.remote.SupplyOrderController;
import com.zegates.sanctus.services.remote.SupplyOrder;
import com.zegates.sanctus.services.remote.SupplyOrderDetail;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import localhost.agency.supplyorder.SupplyOrderService;
import localhost.agency.supplyorder.SupplyOrderServiceRemote;

/**
 *
 * @author sandaruwan
 */
public class SupplyOrderControllerService extends Observable implements SupplyOrderController {

    private final SupplyOrderServiceRemote supplyOrderService;

    public SupplyOrderControllerService() {
        supplyOrderService = new SupplyOrderService().getSupplyOrderServicePort();
    }

    @Override
    public void create(SupplyOrder supplyOrder) throws ClassNotFoundException, SQLException {
        supplyOrderService.create(supplyOrder);
    }

    @Override
    public void destroy(Long id) throws NonexistentEntityException {
        supplyOrderService.destroy(id);
    }

    @Override
    public void edit(SupplyOrder supplyOrder) throws NonexistentEntityException, Exception {
        supplyOrderService.edit(supplyOrder);
    }

    @Override
    public SupplyOrder findSupplyOrder(Long id) {
        return supplyOrderService.findSupplyOrder(id);
    }

    @Override
    public List<SupplyOrder> findSupplyOrderByColumn(String column, String keyWord) {
        return supplyOrderService.findSupplyOrderByColumn(column, keyWord).getItem();
    }

    @Override
    public List<SupplyOrder> findSupplyOrderEntities() {
        return supplyOrderService.findSupplyOrderEntities().getItem();
    }

    @Override
    public List<SupplyOrder> findSupplyOrderEntities(int maxResults, int firstResult) {
        return supplyOrderService.findSupplyOrderEntitiesLimit(maxResults, firstResult).getItem();
    }

    @Override
    public List<SupplyOrderDetail> findSupplyOrdersByLineId(long id) {
        return supplyOrderService.findSupplyOrdersByLineId(id).getItem();
    }

    @Override
    public int getSupplyOrderCount() {
        return supplyOrderService.getSupplyOrderCount();
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }

}
