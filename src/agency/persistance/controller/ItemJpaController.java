/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller;

import agency.persistance.controller.remote.ItemController;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import agency.persistance.entity.Manufacturer;
import agency.persistance.entity.SupplyOrderDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.entity.Item;
import agency.remote.RemoteDBHandler;

/**
 *
 * @author Thilina
 */
public class ItemJpaController extends Observable implements ItemController {

    public ItemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Item item) {
        if (item.getSupplyOrderDetails() == null) {
            item.setSupplyOrderDetails(new ArrayList<SupplyOrderDetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Manufacturer manufacturer = item.getManufacturer();
            if (manufacturer != null) {
                manufacturer = em.getReference(manufacturer.getClass(), manufacturer.getManuid());
                item.setManufacturer(manufacturer);
            }
            
            List<SupplyOrderDetail> attachedSupplyOrderDetails = new ArrayList<SupplyOrderDetail>();
            for (SupplyOrderDetail supplyOrderDetailsSupplyOrderDetailToAttach : item.getSupplyOrderDetails()) {
                supplyOrderDetailsSupplyOrderDetailToAttach = em.getReference(supplyOrderDetailsSupplyOrderDetailToAttach.getClass(), supplyOrderDetailsSupplyOrderDetailToAttach.getSodid());
                attachedSupplyOrderDetails.add(supplyOrderDetailsSupplyOrderDetailToAttach);
            }
            item.setSupplyOrderDetails(attachedSupplyOrderDetails);
            em.persist(item);
            if (manufacturer != null) {
                manufacturer.getItems().add(item);
                manufacturer = em.merge(manufacturer);
            }
            
            for (SupplyOrderDetail supplyOrderDetailsSupplyOrderDetail : item.getSupplyOrderDetails()) {
                Item oldItemOfSupplyOrderDetailsSupplyOrderDetail = supplyOrderDetailsSupplyOrderDetail.getItem();
                supplyOrderDetailsSupplyOrderDetail.setItem(item);
                supplyOrderDetailsSupplyOrderDetail = em.merge(supplyOrderDetailsSupplyOrderDetail);
                if (oldItemOfSupplyOrderDetailsSupplyOrderDetail != null) {
                    oldItemOfSupplyOrderDetailsSupplyOrderDetail.getSupplyOrderDetails().remove(supplyOrderDetailsSupplyOrderDetail);
                    oldItemOfSupplyOrderDetailsSupplyOrderDetail = em.merge(oldItemOfSupplyOrderDetailsSupplyOrderDetail);
                }
            }
            em.getTransaction().commit();

//            System.out.println("sd "+item.getIid()+",'"+item.getCategory().toString()+"','"+item.getName()+"','"+item.getTubeType().toString()+"','"
//            +item.getConstruction().getMid()+"','"+item.getManufacturer().getManuid()+"','"+item.getVehicleType().getVid());
            try {
                RemoteDBHandler.setData("INSERT INTO item (IID, CATEGORY_CATID, NAME, MANUFACTURER_MANUID,"
                        + ") VALUES(" + item.getIid() + ",'" + item.getCategory().getCatid()+ "','" + item.getName() + "','"
                        + item.getMetric().getMid() + "','" + item.getManufacturer().getManuid() + "')");
            } catch (Exception e) {
            }

            // Logger.getLogger(ItemJpaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Item item) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Item persistentItem = em.find(Item.class, item.getIid());
            Manufacturer manufacturerOld = persistentItem.getManufacturer();
            Manufacturer manufacturerNew = item.getManufacturer();
            List<SupplyOrderDetail> supplyOrderDetailsOld = persistentItem.getSupplyOrderDetails();
            List<SupplyOrderDetail> supplyOrderDetailsNew = item.getSupplyOrderDetails();
            if (manufacturerNew != null) {
                manufacturerNew = em.getReference(manufacturerNew.getClass(), manufacturerNew.getManuid());
                item.setManufacturer(manufacturerNew);
            }
           
            List<SupplyOrderDetail> attachedSupplyOrderDetailsNew = new ArrayList<>();
            for (SupplyOrderDetail supplyOrderDetailsNewSupplyOrderDetailToAttach : supplyOrderDetailsNew) {
                supplyOrderDetailsNewSupplyOrderDetailToAttach = em.getReference(supplyOrderDetailsNewSupplyOrderDetailToAttach.getClass(), supplyOrderDetailsNewSupplyOrderDetailToAttach.getSodid());
                attachedSupplyOrderDetailsNew.add(supplyOrderDetailsNewSupplyOrderDetailToAttach);
            }
            supplyOrderDetailsNew = attachedSupplyOrderDetailsNew;
            item.setSupplyOrderDetails(supplyOrderDetailsNew);
            item = em.merge(item);
            if (manufacturerOld != null && !manufacturerOld.equals(manufacturerNew)) {
                manufacturerOld.getItems().remove(item);
                manufacturerOld = em.merge(manufacturerOld);
            }
            if (manufacturerNew != null && !manufacturerNew.equals(manufacturerOld)) {
                manufacturerNew.getItems().add(item);
                manufacturerNew = em.merge(manufacturerNew);
            }
            
            for (SupplyOrderDetail supplyOrderDetailsOldSupplyOrderDetail : supplyOrderDetailsOld) {
                if (!supplyOrderDetailsNew.contains(supplyOrderDetailsOldSupplyOrderDetail)) {
                    supplyOrderDetailsOldSupplyOrderDetail.setItem(null);
                    supplyOrderDetailsOldSupplyOrderDetail = em.merge(supplyOrderDetailsOldSupplyOrderDetail);
                }
            }
            for (SupplyOrderDetail supplyOrderDetailsNewSupplyOrderDetail : supplyOrderDetailsNew) {
                if (!supplyOrderDetailsOld.contains(supplyOrderDetailsNewSupplyOrderDetail)) {
                    Item oldItemOfSupplyOrderDetailsNewSupplyOrderDetail = supplyOrderDetailsNewSupplyOrderDetail.getItem();
                    supplyOrderDetailsNewSupplyOrderDetail.setItem(item);
                    supplyOrderDetailsNewSupplyOrderDetail = em.merge(supplyOrderDetailsNewSupplyOrderDetail);
                    if (oldItemOfSupplyOrderDetailsNewSupplyOrderDetail != null && !oldItemOfSupplyOrderDetailsNewSupplyOrderDetail.equals(item)) {
                        oldItemOfSupplyOrderDetailsNewSupplyOrderDetail.getSupplyOrderDetails().remove(supplyOrderDetailsNewSupplyOrderDetail);
                        oldItemOfSupplyOrderDetailsNewSupplyOrderDetail = em.merge(oldItemOfSupplyOrderDetailsNewSupplyOrderDetail);
                    }
                }
            }
            em.getTransaction().commit();
           
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = item.getIid();
                if (findItem(id) == null) {
                    throw new NonexistentEntityException("The item with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Item item;
            try {
                item = em.getReference(Item.class, id);
                item.getIid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The item with id " + id + " no longer exists.", enfe);
            }
            Manufacturer manufacturer = item.getManufacturer();
            if (manufacturer != null) {
                manufacturer.getItems().remove(item);
                manufacturer = em.merge(manufacturer);
            }
           
            List<SupplyOrderDetail> supplyOrderDetails = item.getSupplyOrderDetails();
            for (SupplyOrderDetail supplyOrderDetailsSupplyOrderDetail : supplyOrderDetails) {
                supplyOrderDetailsSupplyOrderDetail.setItem(null);
                supplyOrderDetailsSupplyOrderDetail = em.merge(supplyOrderDetailsSupplyOrderDetail);
            }
            em.remove(item);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Item> findItemEntities() {
        return findItemEntities(true, -1, -1);
    }

    @Override
    public List<Item> findItemEntities(int maxResults, int firstResult) {
        return findItemEntities(false, maxResults, firstResult);
    }

    private List<Item> findItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Item.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Item findItem(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Item.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Item> rt = cq.from(Item.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }

    @Override
    public Item findItemForNameAndManu(String itemName, String manufacturer) {
        String ejbql = "SELECT i from Item i WHERE i.name = :pattern AND i.manufacturer.name=:manu";
        Query query = getEntityManager().createQuery(ejbql);
        StringBuilder sb = new StringBuilder();
        //sb.append("%");
        sb.append(itemName);
//        sb.append("%");
        query.setParameter("pattern", sb.toString());
        sb = new StringBuilder();
        sb.append(manufacturer);
        query.setParameter("manu", sb.toString());
        List<Item> resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }
}
