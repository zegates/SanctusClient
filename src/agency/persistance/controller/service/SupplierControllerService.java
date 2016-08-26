/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.service;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.controller.remote.SupplierController;
import com.zegates.sanctus.services.remote.Supplier;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import localhost.agency.supplierservice.SupplierService;
import localhost.agency.supplierservice.SupplierServiceRemote;

/**
 *
 * @author sandaruwan
 */
public class SupplierControllerService extends Observable implements SupplierController{

    private final SupplierServiceRemote supplierService;

    public SupplierControllerService() {
        supplierService = new SupplierService().getSupplierServicePort();
    }
    
    
    @Override
    public void create(Supplier supplier) throws ClassNotFoundException, SQLException {
        supplierService.create(supplier);
    }

    @Override
    public void destroy(Long id) throws NonexistentEntityException {
        supplierService.destroy(id);
    }

    @Override
    public void edit(Supplier supplier) throws NonexistentEntityException, Exception {
        supplierService.edit(supplier);
    }

    @Override
    public Supplier findSupplier(Long id) {
        return supplierService.findSupplier(id);
    }

    @Override
    public Supplier findSupplier(String name) {
        return supplierService.findSupplierForName(name);
    }

    @Override
    public List<Supplier> findSupplierEntities() {
        return supplierService.findSupplierEntities().getItem();
    }

    @Override
    public List<Supplier> findSupplierEntities(int maxResults, int firstResult) {
        return supplierService.findSupplierEntitiesLimit(maxResults, firstResult).getItem();
    }

    @Override
    public List<Supplier> findSuppliers(String queryPass, String args) {
        return supplierService.findSuppliers(queryPass, args).getItem();
    }

    @Override
    public List<Supplier> findSuppliers(String orderby) {
        return supplierService.findSuppliersOrder(orderby).getItem();
    }

    @Override
    public int getSupplierCount() {
        return supplierService.getSupplierCount();
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }
}
