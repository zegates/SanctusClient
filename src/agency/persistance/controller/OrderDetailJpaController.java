/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller;

import agency.persistance.controller.remote.OrderDetailController;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.entity.OrderDetail;

/**
 *
 * @author Sandaruwan
 */
public class OrderDetailJpaController implements OrderDetailController {

    public OrderDetailJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(OrderDetail orderDetail) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(orderDetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(OrderDetail orderDetail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            orderDetail = em.merge(orderDetail);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = orderDetail.getOdid();
                if (findOrderDetail(id) == null) {
                    throw new NonexistentEntityException("The orderDetail with id " + id + " no longer exists.");
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
            OrderDetail orderDetail;
            try {
                orderDetail = em.getReference(OrderDetail.class, id);
                orderDetail.getOdid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderDetail with id " + id + " no longer exists.", enfe);
            }
            em.remove(orderDetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<OrderDetail> findOrderDetailEntities() {
        return findOrderDetailEntities(true, -1, -1);
    }

    @Override
    public List<OrderDetail> findOrderDetailEntities(int maxResults, int firstResult) {
        return findOrderDetailEntities(false, maxResults, firstResult);
    }

    private List<OrderDetail> findOrderDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrderDetail.class));
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
    public OrderDetail findOrderDetail(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrderDetail.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getOrderDetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrderDetail> rt = cq.from(OrderDetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
