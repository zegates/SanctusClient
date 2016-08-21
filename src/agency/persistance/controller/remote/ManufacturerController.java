/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.remote;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.entity.Manufacturer;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sandaruwan
 */
public interface ManufacturerController extends Serializable {

    void create(Manufacturer manufacturer) throws ClassNotFoundException, SQLException;

    void destroy(Long id) throws NonexistentEntityException;

    void edit(Manufacturer manufacturer) throws NonexistentEntityException, Exception;

    Manufacturer findManufacturer(Long id);

    List<Manufacturer> findManufacturerEntities();

    List<Manufacturer> findManufacturerEntities(int maxResults, int firstResult);

    Manufacturer findManufacturerForName(String name);

    int getManufacturerCount();
    
}
