/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.remote;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import com.zegates.sanctus.services.remote.Supplier;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sandaruwan
 */
public interface SupplierController extends Serializable {

    void create(Supplier supplier) throws ClassNotFoundException, SQLException;

    void destroy(Long id) throws NonexistentEntityException;

    void edit(Supplier supplier) throws NonexistentEntityException, Exception;

    Supplier findSupplier(Long id);

    /**
     *
     * @param name supplier name to search the database
     * @return if there is a supplier returns the instance of the supplier if
     * there is no supplier returns null
     */
    Supplier findSupplier(String name);

    List<Supplier> findSupplierEntities();

    List<Supplier> findSupplierEntities(int maxResults, int firstResult);

    /**
     * find the supplier from specified column with the given text
     *
     *
     * @param queryPass a text to search the database for the results
     * @param args the database column name for the search
     * @return the list of results as a Supplier instance list
     */
    List<Supplier> findSuppliers(String queryPass, String args);

    /**
     * find the suppliers
     *
     * @param orderby the database column name to sort the result
     * @return the list of results as a Supplier instance list
     */
    List<Supplier> findSuppliers(String orderby);

    /**
     *
     * @return supplier count from the database
     */
    int getSupplierCount();

    void setChanged();
    
}
