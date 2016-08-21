/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller;

import agency.persistance.controller.remote.CashTransferController;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.entity.CashTransfer;

/**
 *
 * @author Sandaruwan
 */
public class CashTransferJpaController implements CashTransferController {

    public CashTransferJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(CashTransfer cashTransfer) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cashTransfer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(CashTransfer cashTransfer) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cashTransfer = em.merge(cashTransfer);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cashTransfer.getCtid();
                if (findCashTransfer(id) == null) {
                    throw new NonexistentEntityException("The cashTransfer with id " + id + " no longer exists.");
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
            CashTransfer cashTransfer;
            try {
                cashTransfer = em.getReference(CashTransfer.class, id);
                cashTransfer.getCtid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cashTransfer with id " + id + " no longer exists.", enfe);
            }
            em.remove(cashTransfer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<CashTransfer> findCashTransferEntities() {
        return findCashTransferEntities(true, -1, -1);
    }

    @Override
    public List<CashTransfer> findCashTransferEntities(int maxResults, int firstResult) {
        return findCashTransferEntities(false, maxResults, firstResult);
    }

    private List<CashTransfer> findCashTransferEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CashTransfer.class));
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
    public CashTransfer findCashTransfer(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CashTransfer.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getCashTransferCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CashTransfer> rt = cq.from(CashTransfer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
