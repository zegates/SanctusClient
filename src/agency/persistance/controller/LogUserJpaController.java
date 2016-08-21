/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller;

import agency.persistance.controller.remote.LogUserController;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.entity.LogUser;
import agency.remote.RemoteDBHandler;

/**
 *
 * @author Sandaruwan
 */
public class LogUserJpaController implements LogUserController {

    public LogUserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(LogUser logUser) throws ClassNotFoundException, SQLException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(logUser);
            em.getTransaction().commit();
            try {
                RemoteDBHandler.setData("INSERT INTO loguser (UID, ADDRESS, DATEADDED, NAME, PW, TIMEADDED, TPNO, USERNAME) "
                        + "VALUES('" + logUser.getUid() + "', '" + logUser.getAddress() + "',"
                        + "'" + logUser.getDateAdded() + "', '" + logUser.getName() + "',"
                        + "'" + logUser.getPw() + "','" + logUser.getTimeAdded() + "',"
                        + "'" + logUser.getTpno() + "','" + logUser.getUsername() + "')");
            }catch (Exception ce) {
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(LogUser logUser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            logUser = em.merge(logUser);
            em.getTransaction().commit();
            try {
                RemoteDBHandler.setData("UPDATE loguser SET ADDRESS='" + logUser.getAddress() + "', "
                        + "DATEADDED='" + logUser.getDateAdded() + "', NAME='" + logUser.getName() + "', "
                        + "PW='" + logUser.getPw() + "',TIMEADDED='" + logUser.getTimeAdded() + "', "
                        + "TPNO='" + logUser.getTpno() + "', USERNAME='" + logUser.getUsername() + "' ");
            } catch (Exception ce) {
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = logUser.getUid();
                if (findLogUser(id) == null) {
                    throw new NonexistentEntityException("The logUser with id " + id + " no longer exists.");
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
            LogUser logUser;
            try {
                logUser = em.getReference(LogUser.class, id);
                logUser.getUid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The logUser with id " + id + " no longer exists.", enfe);
            }
            em.remove(logUser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<LogUser> findLogUserEntities() {
        return findLogUserEntities(true, -1, -1);
    }

    @Override
    public List<LogUser> findLogUserEntities(int maxResults, int firstResult) {
        return findLogUserEntities(false, maxResults, firstResult);
    }

    private List<LogUser> findLogUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LogUser.class));
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
    public LogUser findLogUser(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LogUser.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getLogUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LogUser> rt = cq.from(LogUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
