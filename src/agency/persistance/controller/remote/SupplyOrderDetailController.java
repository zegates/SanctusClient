/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.remote;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import com.zegates.sanctus.services.remote.SupplyOrderDetail;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sandaruwan
 */
public interface SupplyOrderDetailController extends Serializable {

    void create(SupplyOrderDetail supplyOrderDetail) throws ClassNotFoundException, ClassNotFoundException, SQLException;

    void destroy(Long id) throws NonexistentEntityException;

    void edit(SupplyOrderDetail supplyOrderDetail) throws NonexistentEntityException, Exception;

    SupplyOrderDetail findSupplyOrderDetail(Long id);

    List<SupplyOrderDetail> findSupplyOrderDetailEntities();

    List<SupplyOrderDetail> findSupplyOrderDetailEntities(int maxResults, int firstResult);

    /**
     *
     * filter the details from supply order detail table, by specified item code
     *
     * @param id the item id to filter the supply order detail
     * @return list of supply order details filtered by the item code
     */
    List<SupplyOrderDetail> findSupplyOrderDetailsByItemCode(Long id);

    int getSupplyOrderDetailCount();
    
}
