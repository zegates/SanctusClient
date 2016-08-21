/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import agency.persistance.controller.exceptions.MyRemoteEx;
import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.entity.Metric;
import agency.remote.RemoteDBHandler;

/**
 *
 * @author Sandaruwan
 */
public class ConstructionJpaController implements Serializable {

    public ConstructionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Metric construction) throws ClassNotFoundException, ClassNotFoundException, SQLException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(construction);
            em.getTransaction().commit();
            try {
                RemoteDBHandler.setData("INSERT INTO construction (NAME,CID) VALUES('" + construction.getName() + "','" + construction.getMid() + "')");
            } catch (Exception e) {
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Metric construction) throws NonexistentEntityException ,Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            construction = em.merge(construction);
            em.getTransaction().commit();
            try {
                RemoteDBHandler.setData("UPDATE construction SET NAME='" + construction.getName() + "' WHERE CID='" + construction.getMid() + "' ");
            } catch (Exception e) {
            }

        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = construction.getMid();
                if (findConstruction(id) == null) {
                    throw new NonexistentEntityException("The construction with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Metric construction;
            try {
                construction = em.getReference(Metric.class, id);
                construction.getMid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The construction with id " + id + " no longer exists.", enfe);
            }
            em.remove(construction);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Metric> findConstructionEntities() {
        return findConstructionEntities(true, -1, -1);
    }

    public List<Metric> findConstructionEntities(int maxResults, int firstResult) {
        return findConstructionEntities(false, maxResults, firstResult);
    }

    private List<Metric> findConstructionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Metric.class));
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

    public Metric findConstruction(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Metric.class, id);
        } finally {
            em.close();
        }
    }

    public int getConstructionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Metric> rt = cq.from(Metric.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Metric getConstructionForName(String name) {
        String ejbql = "SELECT co from Construction co WHERE co.name LIKE :pattern";
        Query query = getEntityManager().createQuery(ejbql);
        StringBuilder sb = new StringBuilder();
        sb.append("%");
        sb.append(name);
        sb.append("%");
        query.setParameter("pattern", sb.toString());
        List<Metric> resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }
}
