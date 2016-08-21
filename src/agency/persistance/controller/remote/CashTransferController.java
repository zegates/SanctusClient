/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.remote;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.entity.CashTransfer;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author sandaruwan
 */
public interface CashTransferController extends Serializable {

    void create(CashTransfer cashTransfer);

    void destroy(Long id) throws NonexistentEntityException;

    void edit(CashTransfer cashTransfer) throws NonexistentEntityException, Exception;

    CashTransfer findCashTransfer(Long id);

    List<CashTransfer> findCashTransferEntities();

    List<CashTransfer> findCashTransferEntities(int maxResults, int firstResult);

    int getCashTransferCount();
    
}
