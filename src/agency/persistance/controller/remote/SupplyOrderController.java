/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.remote;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.entity.SupplyOrder;
import agency.persistance.entity.SupplyOrderDetail;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sandaruwan
 */
public interface SupplyOrderController extends Serializable {

    void create(SupplyOrder supplyOrder) throws ClassNotFoundException, SQLException;

    void destroy(Long id) throws NonexistentEntityException;

    void edit(SupplyOrder supplyOrder) throws NonexistentEntityException, Exception;

    SupplyOrder findSupplyOrder(Long id);

    List<SupplyOrder> findSupplyOrderByColumn(String column, String keyWord);

    List<SupplyOrder> findSupplyOrderEntities();

    List<SupplyOrder> findSupplyOrderEntities(int maxResults, int firstResult);

    List<SupplyOrderDetail> findSupplyOrdersByLineId(long id);

    int getSupplyOrderCount();

    void setChanged();
    
}
