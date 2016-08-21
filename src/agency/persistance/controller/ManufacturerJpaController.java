/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller;

import agency.persistance.controller.remote.ManufacturerController;
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
import agency.persistance.entity.Manufacturer;
import agency.remote.RemoteDBHandler;

/**
 *
 * @author Sandaruwan
 */
public class ManufacturerJpaController implements ManufacturerController {

    public ManufacturerJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Manufacturer manufacturer) throws ClassNotFoundException, SQLException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(manufacturer);
            em.getTransaction().commit();
            try {
                RemoteDBHandler.setData("INSERT INTO manufacturer (MANUID,NAME) VALUES('" + manufacturer.getManuid() + "','" + manufacturer.getName() + "')");
            } catch (Exception ce) {
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Manufacturer manufacturer) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            manufacturer = em.merge(manufacturer);
            em.getTransaction().commit();
            try {
                RemoteDBHandler.setData("UPDATE manufacturer SET NAME='" + manufacturer.getName() + "' WHERE"
                        + " MANUID='" + manufacturer.getManuid() + "'");
            } catch (Exception ce) {
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = manufacturer.getManuid();
                if (findManufacturer(id) == null) {
                    throw new NonexistentEntityException("The manufacturer with id " + id + " no longer exists.");
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
            Manufacturer manufacturer;
            try {
                manufacturer = em.getReference(Manufacturer.class, id);
                manufacturer.getManuid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The manufacturer with id " + id + " no longer exists.", enfe);
            }
            em.remove(manufacturer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Manufacturer> findManufacturerEntities() {
        return findManufacturerEntities(true, -1, -1);
    }

    @Override
    public List<Manufacturer> findManufacturerEntities(int maxResults, int firstResult) {
        return findManufacturerEntities(false, maxResults, firstResult);
    }

    private List<Manufacturer> findManufacturerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Manufacturer.class));
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
    public Manufacturer findManufacturer(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Manufacturer.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getManufacturerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Manufacturer> rt = cq.from(Manufacturer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public Manufacturer findManufacturerForName(String name) {
        String ejbql = "SELECT m from Manufacturer m WHERE m.name LIKE :pattern";
        Query query = getEntityManager().createQuery(ejbql);
        StringBuilder sb = new StringBuilder();
        sb.append("%");
        sb.append(name);
        sb.append("%");
        query.setParameter("pattern", sb.toString());
        List<Manufacturer> resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }
}
