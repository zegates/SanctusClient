/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.remote;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import com.zegates.sanctus.services.remote.Item;
import java.io.Serializable;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author sandaruwan
 */
public interface ItemController extends Serializable {

    void create(Item item);

    void destroy(Long id) throws NonexistentEntityException;

    void edit(Item item) throws NonexistentEntityException, Exception;

    Item findItem(Long id);

    List<Item> findItemEntities();

    List<Item> findItemEntities(int maxResults, int firstResult);

    Item findItemForNameAndManu(String itemName, String manufacturer);

    int getItemCount();

    void setChanged();
    void notifyObservers(String s);
    void addObserver(Observer o);
}
