/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.service;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.controller.remote.ItemController;
import com.zegates.sanctus.services.remote.Item;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import localhost.agency.item.ItemService;
import localhost.agency.item.ItemServiceRemote;

/**
 *
 * @author sandaruwan
 */
public class ItemControllerService extends Observable implements ItemController {

    private final ItemServiceRemote itemService;

    public ItemControllerService() {
        itemService = new ItemService().getItemServicePort();

    }

    @Override
    public void create(Item item) {
        itemService.create(item);
    }

    @Override
    public void destroy(Long id) throws NonexistentEntityException {
        itemService.destroy(id);
    }

    @Override
    public void edit(Item item) throws NonexistentEntityException, Exception {
        itemService.edit(item);
    }

    @Override
    public Item findItem(Long id) {
        return itemService.findItem(id);
    }

    @Override
    public List<Item> findItemEntities() {
        return itemService.findItemEntities().getItem();
    }

    @Override
    public List<Item> findItemEntities(int maxResults, int firstResult) {
//        return itemService.fi
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item findItemForNameAndManu(String itemName, String manufacturer) {
        return itemService.findItemForNameAndManu(itemName, manufacturer);
    }

    @Override
    public int getItemCount() {
        return itemService.getItemCount();
    }

    @Override
    public void notifyObservers(String s) {
        super.notifyObservers(s);
    }

    
    @Override
    public void setChanged() {
        super.setChanged();
    }
}
