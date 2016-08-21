/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller;

import agency.persistance.controller.remote.SupplyOrderDetailController;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.entity.SupplyOrderDetail;
import agency.remote.RemoteDBHandler;

/**
 *
 * @author Sandaruwan
 */
public class SupplyOrderDetailJpaController implements SupplyOrderDetailController {

    public SupplyOrderDetailJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(SupplyOrderDetail supplyOrderDetail) throws ClassNotFoundException, ClassNotFoundException, SQLException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(supplyOrderDetail);
            em.merge(supplyOrderDetail);
            em.getTransaction().commit();
            try {
                RemoteDBHandler.setData("INSERT INTO `supplyorderdetail` (`SODID`, `BUYINGPRICE`,"
                        + " `QTY`, `REMAININGQTY`, `SELLINGPRICE`, `ITEM_IID`, `SUPPLYORDER_SOID`)"
                        + " VALUES ('" + supplyOrderDetail.getSodid() + "', '" + supplyOrderDetail.getBuyingPrice() + "', "
                        + "'" + supplyOrderDetail.getQty() + "', '" + supplyOrderDetail.getRemainingQty() + "',"
                        + "'" + supplyOrderDetail.getSellingPrice() + "', '" + supplyOrderDetail.getItem().getIid() + "', "
                        + "'" + supplyOrderDetail.getSupplyOrder().getSoid() + "')");
            } catch (Exception ce) {
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(SupplyOrderDetail supplyOrderDetail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(supplyOrderDetail);
            em.getTransaction().commit();
            try {
                RemoteDBHandler.setData("UPDATE supplyorderdetail SET BUYINGPRICE='" + supplyOrderDetail.getBuyingPrice() + "'"
                        + ", QTY='" + supplyOrderDetail.getQty() + "', REMAININGQTY='" + supplyOrderDetail.getRemainingQty() + "', "
                        + "SELLINGPRICE='" + supplyOrderDetail.getSellingPrice() + "', ITEM_IID='" + supplyOrderDetail.getItem().getIid() + "',"
                        + "SUPPLYORDER_SOID='" + supplyOrderDetail.getSupplyOrder().getSoid() + "' WHERE SODID='" + supplyOrderDetail.getSodid() + "'");
            } catch (CommunicationsException ce) {
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = supplyOrderDetail.getSodid();
                if (findSupplyOrderDetail(id) == null) {
                    throw new NonexistentEntityException("The supplyOrderDetail with id " + id + " no longer exists.");
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
            SupplyOrderDetail supplyOrderDetail;
            try {
                supplyOrderDetail = em.getReference(SupplyOrderDetail.class, id);
                supplyOrderDetail.getSodid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The supplyOrderDetail with id " + id + " no longer exists.", enfe);
            }
            em.remove(supplyOrderDetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<SupplyOrderDetail> findSupplyOrderDetailEntities() {
        return findSupplyOrderDetailEntities(true, -1, -1);
    }

    @Override
    public List<SupplyOrderDetail> findSupplyOrderDetailEntities(int maxResults, int firstResult) {
        return findSupplyOrderDetailEntities(false, maxResults, firstResult);
    }

    private List<SupplyOrderDetail> findSupplyOrderDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SupplyOrderDetail.class));
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
    public SupplyOrderDetail findSupplyOrderDetail(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SupplyOrderDetail.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getSupplyOrderDetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SupplyOrderDetail> rt = cq.from(SupplyOrderDetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    /**
     *
     * filter the details from supply order detail table, by specified item code
     *
     * @param id the item id to filter the supply order detail
     * @return list of supply order details filtered by the item code
     */
    @Override
    public List<SupplyOrderDetail> findSupplyOrderDetailsByItemCode(Long id) {
        EntityManager em = getEntityManager();
        try {//+" ORDER BY s.supplyOrder.dateAdded DESC"
            Query query = em.createQuery("SELECT s FROM SupplyOrderDetail s WHERE s.item.iid = " + id + " ORDER BY s.supplyOrder.dateAdded DESC");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
