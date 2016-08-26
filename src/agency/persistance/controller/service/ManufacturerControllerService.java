/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.service;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.controller.remote.ManufacturerController;
import com.zegates.sanctus.services.remote.Manufacturer;
import java.sql.SQLException;
import java.util.List;
import localhost.agency.manufacturer.ManufacturerService;
import localhost.agency.manufacturer.ManufacturerServiceRemote;

/**
 *
 * @author sandaruwan
 */
public class ManufacturerControllerService implements ManufacturerController {

    private final ManufacturerServiceRemote manufacturerService;

    public ManufacturerControllerService() {
        manufacturerService = new ManufacturerService().getManufacturerServicePort();

    }

    @Override
    public void create(Manufacturer manufacturer) throws ClassNotFoundException, SQLException {
        manufacturerService.create(manufacturer);
    }

    @Override
    public void destroy(Long id) throws NonexistentEntityException {
        manufacturerService.destroy(id);
    }

    @Override
    public void edit(Manufacturer manufacturer) throws NonexistentEntityException, Exception {
        manufacturerService.edit(manufacturer);
    }

    @Override
    public Manufacturer findManufacturer(Long id) {
        return manufacturerService.findManufacturer(id);
    }

    @Override
    public List<Manufacturer> findManufacturerEntities() {
        return manufacturerService.findManufacturerEntities().getItem();
    }

    @Override
    public List<Manufacturer> findManufacturerEntities(int maxResults, int firstResult) {
        return manufacturerService.findManufacturerEntitiesLimit(maxResults, firstResult).getItem();
    }

    @Override
    public Manufacturer findManufacturerForName(String name) {
       return manufacturerService.findManufacturerForName(name);
    }

    @Override
    public int getManufacturerCount() {
        return manufacturerService.getManufacturerCount();
    }

}
