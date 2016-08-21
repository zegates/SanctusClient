/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller;

import agency.persistance.controller.remote.SupplierController;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.entity.Supplier;
import agency.persistance.entity.SupplyOrder;
import agency.remote.RemoteDBHandler;

/**
 *
 * @author Sandaruwan
 */
public class SupplierJpaController extends Observable implements SupplierController {

    public SupplierJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }

    @Override
    public void create(Supplier supplier) throws ClassNotFoundException, SQLException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(supplier);
            em.getTransaction().commit();
            /**
             * Merge the Supplier
             */
            em = getEntityManager();
            em.getTransaction().begin();
            List<SupplyOrder> attachedSupplyOrders = new ArrayList<SupplyOrder>();
            for (SupplyOrder supplyOrdersSupplyOrderToAttach : supplier.getSupplyOrders()) {
                supplyOrdersSupplyOrderToAttach = em.getReference(supplyOrdersSupplyOrderToAttach.getClass(), supplyOrdersSupplyOrderToAttach.getSoid());
                attachedSupplyOrders.add(supplyOrdersSupplyOrderToAttach);
            }
            supplier.setSupplyOrders(attachedSupplyOrders);
            em.merge(supplier);
            for (SupplyOrder supplyOrdersSupplyOrder : supplier.getSupplyOrders()) {
                Supplier oldSupplierOfSupplyOrdersSupplyOrder = supplyOrdersSupplyOrder.getSupplier();
                supplyOrdersSupplyOrder.setSupplier(supplier);
                supplyOrdersSupplyOrder = em.merge(supplyOrdersSupplyOrder);
                if (oldSupplierOfSupplyOrdersSupplyOrder != null) {
                    oldSupplierOfSupplyOrdersSupplyOrder.getSupplyOrders().remove(supplyOrdersSupplyOrder);
                    oldSupplierOfSupplyOrdersSupplyOrder = em.merge(oldSupplierOfSupplyOrdersSupplyOrder);
                }
            }
            em.getTransaction().commit();
            try{
            RemoteDBHandler.setData("INSERT INTO `supplier` (`SUID`, `ADDRESS`, `COMPNAME`, `DATEADDED`,"
                    + " `EMAIL`, `NAME`, `TIMEADDED`, `TPNO`) "
                    + "VALUES ('"+supplier.getSuid()+"', '"+supplier.getAddress()+"',"
                    + " '"+supplier.getCompName()+"',"
                    + " '"+supplier.getDateAdded()+"', '"+supplier.getEmail()+"', "
                    + "'"+supplier.getName()+"', '"+supplier.getTimeAdded()+"',"
                    + " '"+supplier.getTpno()+"')");
            } catch (Exception ce) {
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Supplier supplier) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            supplier = em.merge(supplier);
//            em.getTransaction().commit();
            
            em = getEntityManager();
            em.getTransaction().begin();
            Supplier persistentSupplier = em.find(Supplier.class, supplier.getSuid());
            List<SupplyOrder> supplyOrdersOld = persistentSupplier.getSupplyOrders();
            List<SupplyOrder> supplyOrdersNew = supplier.getSupplyOrders();
            List<SupplyOrder> attachedSupplyOrdersNew = new ArrayList<SupplyOrder>();
            for (SupplyOrder supplyOrdersNewSupplyOrderToAttach : supplyOrdersNew) {
                supplyOrdersNewSupplyOrderToAttach = em.getReference(supplyOrdersNewSupplyOrderToAttach.getClass(), supplyOrdersNewSupplyOrderToAttach.getSoid());
                attachedSupplyOrdersNew.add(supplyOrdersNewSupplyOrderToAttach);
            }
            supplyOrdersNew = attachedSupplyOrdersNew;
            supplier.setSupplyOrders(supplyOrdersNew);
            supplier = em.merge(supplier);
            for (SupplyOrder supplyOrdersOldSupplyOrder : supplyOrdersOld) {
                if (!supplyOrdersNew.contains(supplyOrdersOldSupplyOrder)) {
                    supplyOrdersOldSupplyOrder.setSupplier(null);
                    supplyOrdersOldSupplyOrder = em.merge(supplyOrdersOldSupplyOrder);
                }
            }
            for (SupplyOrder supplyOrdersNewSupplyOrder : supplyOrdersNew) {
                if (!supplyOrdersOld.contains(supplyOrdersNewSupplyOrder)) {
                    Supplier oldSupplierOfSupplyOrdersNewSupplyOrder = supplyOrdersNewSupplyOrder.getSupplier();
                    supplyOrdersNewSupplyOrder.setSupplier(supplier);
                    supplyOrdersNewSupplyOrder = em.merge(supplyOrdersNewSupplyOrder);
                    if (oldSupplierOfSupplyOrdersNewSupplyOrder != null && !oldSupplierOfSupplyOrdersNewSupplyOrder.equals(supplier)) {
                        oldSupplierOfSupplyOrdersNewSupplyOrder.getSupplyOrders().remove(supplyOrdersNewSupplyOrder);
                        oldSupplierOfSupplyOrdersNewSupplyOrder = em.merge(oldSupplierOfSupplyOrdersNewSupplyOrder);
                    }
                }
            }
            em.getTransaction().commit();
            
            
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = supplier.getSuid();
                if (findSupplier(id) == null) {
                    throw new NonexistentEntityException("The supplier with id " + id + " no longer exists.");
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
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Supplier supplier;
//            try {
//                supplier = em.getReference(Supplier.class, id);
//                supplier.getSuid();
//            } catch (EntityNotFoundException enfe) {
//                throw new NonexistentEntityException("The supplier with id " + id + " no longer exists.", enfe);
//            }
//            em.remove(supplier);
//            em.getTransaction().commit();
            
            
            em = getEntityManager();
            em.getTransaction().begin();
            Supplier supplier;
            try {
                supplier = em.getReference(Supplier.class, id);
                supplier.getSuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The supplier with id " + id + " no longer exists.", enfe);
            }
            List<SupplyOrder> supplyOrders = supplier.getSupplyOrders();
            for (SupplyOrder supplyOrdersSupplyOrder : supplyOrders) {
                supplyOrdersSupplyOrder.setSupplier(null);
                supplyOrdersSupplyOrder = em.merge(supplyOrdersSupplyOrder);
            }
            em.remove(supplier);
            em.getTransaction().commit();
            
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Supplier> findSupplierEntities() {
        return findSupplierEntities(true, -1, -1);
    }

    @Override
    public List<Supplier> findSupplierEntities(int maxResults, int firstResult) {
        return findSupplierEntities(false, maxResults, firstResult);
    }

    private List<Supplier> findSupplierEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Supplier.class));
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
    public Supplier findSupplier(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Supplier.class, id);
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param name supplier name to search the database
     * @return if there is a supplier returns the instance of the supplier if
     * there is no supplier returns null
     */
    @Override
    public Supplier findSupplier(String name) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT s FROM Supplier s WHERE s.compName='" + name + "'", Supplier.class);
            Object result = query.getSingleResult();
            if (result != null) {
                return (Supplier) result;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * find the supplier from specified column with the given text
     *
     *
     * @param queryPass a text to search the database for the results
     * @param args the database column name for the search
     * @return the list of results as a Supplier instance list
     */
    @Override
    public List<Supplier> findSuppliers(String queryPass, String args) {
        EntityManager em = getEntityManager();
        String query = "SELECT s FROM Supplier s WHERE s." + args + " LIKE '" + queryPass + "%'";
        Query query1 = em.createQuery(query);
        try {
            return query1.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * find the suppliers
     *
     * @param orderby the database column name to sort the result
     * @return the list of results as a Supplier instance list
     */
    @Override
    public List<Supplier> findSuppliers(String orderby) {
        EntityManager em = getEntityManager();
        String query = "SELECT s FROM Supplier s ORDER BY s." + orderby;
        Query query1 = em.createQuery(query);
        try {
            return query1.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @return supplier count from the database
     */
    @Override
    public int getSupplierCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Supplier> rt = cq.from(Supplier.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
