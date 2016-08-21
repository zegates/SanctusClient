/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller;

import agency.persistance.controller.remote.LogSessionController;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.entity.LogSession;
import agency.persistance.entity.LogUser;
import agency.persistance.entity.Orders;
import agency.persistance.entity.SupplyOrder;
import agency.remote.RemoteDBHandler;

/**
 *
 * @author Sandaruwan
 */
public class LogSessionJpaController implements LogSessionController {

    public LogSessionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(LogSession logSession) throws ClassNotFoundException, ClassNotFoundException, SQLException {
        if (logSession.getOrderss() == null) {
            logSession.setOrderss(new ArrayList<Orders>());
        }
        if (logSession.getSupplyOrders() == null) {
            logSession.setSupplyOrders(new ArrayList<SupplyOrder>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(logSession);
            em.getTransaction().commit();

            em = getEntityManager();
            em.getTransaction().begin();
            LogUser logUser = logSession.getLogUser();
            if (logUser != null) {
                logUser = em.getReference(logUser.getClass(), logUser.getUid());
                logSession.setLogUser(logUser);
            }
            List<Orders> attachedOrderss = new ArrayList<Orders>();
            for (Orders orderssOrdersToAttach : logSession.getOrderss()) {
                orderssOrdersToAttach = em.getReference(orderssOrdersToAttach.getClass(), orderssOrdersToAttach.getOid());
                attachedOrderss.add(orderssOrdersToAttach);
            }
            logSession.setOrderss(attachedOrderss);
            List<SupplyOrder> attachedSupplyOrders = new ArrayList<SupplyOrder>();
            for (SupplyOrder supplyOrdersSupplyOrderToAttach : logSession.getSupplyOrders()) {
                supplyOrdersSupplyOrderToAttach = em.getReference(supplyOrdersSupplyOrderToAttach.getClass(), supplyOrdersSupplyOrderToAttach.getSoid());
                attachedSupplyOrders.add(supplyOrdersSupplyOrderToAttach);
            }
            logSession.setSupplyOrders(attachedSupplyOrders);
            if (logUser != null) {
                logUser.getLogSessions().add(logSession);
                logUser = em.merge(logUser);
            }
            for (Orders orderssOrders : logSession.getOrderss()) {
                LogSession oldLogSessionOfOrderssOrders = orderssOrders.getLogSession();
                orderssOrders.setLogSession(logSession);
                orderssOrders = em.merge(orderssOrders);
                if (oldLogSessionOfOrderssOrders != null) {
                    oldLogSessionOfOrderssOrders.getOrderss().remove(orderssOrders);
                    oldLogSessionOfOrderssOrders = em.merge(oldLogSessionOfOrderssOrders);
                }
            }
            for (SupplyOrder supplyOrdersSupplyOrder : logSession.getSupplyOrders()) {
                LogSession oldLogSessionOfSupplyOrdersSupplyOrder = supplyOrdersSupplyOrder.getLogSession();
                supplyOrdersSupplyOrder.setLogSession(logSession);
                supplyOrdersSupplyOrder = em.merge(supplyOrdersSupplyOrder);
                if (oldLogSessionOfSupplyOrdersSupplyOrder != null) {
                    oldLogSessionOfSupplyOrdersSupplyOrder.getSupplyOrders().remove(supplyOrdersSupplyOrder);
                    oldLogSessionOfSupplyOrdersSupplyOrder = em.merge(oldLogSessionOfSupplyOrdersSupplyOrder);
                }
            }
            em.getTransaction().commit();
            try {
                RemoteDBHandler.setData("INSERT INTO logsession (SEID, DATESTARTED, "
                        + "FINALISED, TIMESTARTED, LOGUSER_UID) VALUES("
                        + "'" + logSession.getSeid() + "','" + logSession.getDateStarted() + "',"
                        + "'" + (logSession.isFinalised() ? 1 : 0) + "', "
                        + "'" + logSession.getTimeStarted() + "','" + logSession.getLogUser().getUid() + "')");
            } catch (Exception ce) {
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(LogSession logSession) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            logSession = em.merge(logSession);
            em.getTransaction().commit();

            em = getEntityManager();
            em.getTransaction().begin();
            LogSession persistentLogSession = em.find(LogSession.class, logSession.getSeid());
            LogUser logUserOld = persistentLogSession.getLogUser();
            LogUser logUserNew = logSession.getLogUser();
            List<Orders> orderssOld = persistentLogSession.getOrderss();
            List<Orders> orderssNew = logSession.getOrderss();
            List<SupplyOrder> supplyOrdersOld = persistentLogSession.getSupplyOrders();
            List<SupplyOrder> supplyOrdersNew = logSession.getSupplyOrders();
            if (logUserNew != null) {
                logUserNew = em.getReference(logUserNew.getClass(), logUserNew.getUid());
                logSession.setLogUser(logUserNew);
            }
            List<Orders> attachedOrderssNew = new ArrayList<Orders>();
            for (Orders orderssNewOrdersToAttach : orderssNew) {
                orderssNewOrdersToAttach = em.getReference(orderssNewOrdersToAttach.getClass(), orderssNewOrdersToAttach.getOid());
                attachedOrderssNew.add(orderssNewOrdersToAttach);
            }
            orderssNew = attachedOrderssNew;
            logSession.setOrderss(orderssNew);
            List<SupplyOrder> attachedSupplyOrdersNew = new ArrayList<SupplyOrder>();
            for (SupplyOrder supplyOrdersNewSupplyOrderToAttach : supplyOrdersNew) {
                supplyOrdersNewSupplyOrderToAttach = em.getReference(supplyOrdersNewSupplyOrderToAttach.getClass(), supplyOrdersNewSupplyOrderToAttach.getSoid());
                attachedSupplyOrdersNew.add(supplyOrdersNewSupplyOrderToAttach);
            }
            supplyOrdersNew = attachedSupplyOrdersNew;
            logSession.setSupplyOrders(supplyOrdersNew);
            logSession = em.merge(logSession);
            if (logUserOld != null && !logUserOld.equals(logUserNew)) {
                logUserOld.getLogSessions().remove(logSession);
                logUserOld = em.merge(logUserOld);
            }
            if (logUserNew != null && !logUserNew.equals(logUserOld)) {
                logUserNew.getLogSessions().add(logSession);
                logUserNew = em.merge(logUserNew);
            }
            for (Orders orderssOldOrders : orderssOld) {
                if (!orderssNew.contains(orderssOldOrders)) {
                    orderssOldOrders.setLogSession(null);
                    orderssOldOrders = em.merge(orderssOldOrders);
                }
            }
            for (Orders orderssNewOrders : orderssNew) {
                if (!orderssOld.contains(orderssNewOrders)) {
                    LogSession oldLogSessionOfOrderssNewOrders = orderssNewOrders.getLogSession();
                    orderssNewOrders.setLogSession(logSession);
                    orderssNewOrders = em.merge(orderssNewOrders);
                    if (oldLogSessionOfOrderssNewOrders != null && !oldLogSessionOfOrderssNewOrders.equals(logSession)) {
                        oldLogSessionOfOrderssNewOrders.getOrderss().remove(orderssNewOrders);
                        oldLogSessionOfOrderssNewOrders = em.merge(oldLogSessionOfOrderssNewOrders);
                    }
                }
            }
            for (SupplyOrder supplyOrdersOldSupplyOrder : supplyOrdersOld) {
                if (!supplyOrdersNew.contains(supplyOrdersOldSupplyOrder)) {
                    supplyOrdersOldSupplyOrder.setLogSession(null);
                    supplyOrdersOldSupplyOrder = em.merge(supplyOrdersOldSupplyOrder);
                }
            }
            for (SupplyOrder supplyOrdersNewSupplyOrder : supplyOrdersNew) {
                if (!supplyOrdersOld.contains(supplyOrdersNewSupplyOrder)) {
                    LogSession oldLogSessionOfSupplyOrdersNewSupplyOrder = supplyOrdersNewSupplyOrder.getLogSession();
                    supplyOrdersNewSupplyOrder.setLogSession(logSession);
                    supplyOrdersNewSupplyOrder = em.merge(supplyOrdersNewSupplyOrder);
                    if (oldLogSessionOfSupplyOrdersNewSupplyOrder != null && !oldLogSessionOfSupplyOrdersNewSupplyOrder.equals(logSession)) {
                        oldLogSessionOfSupplyOrdersNewSupplyOrder.getSupplyOrders().remove(supplyOrdersNewSupplyOrder);
                        oldLogSessionOfSupplyOrdersNewSupplyOrder = em.merge(oldLogSessionOfSupplyOrdersNewSupplyOrder);
                    }
                }
            }
            em.getTransaction().commit();
            try {
                RemoteDBHandler.setData("UPDATE logsession SET DATEENDED='" + logSession.getDateEnded() + "',"
                        + " DATESTARTED='" + logSession.getDateStarted() + "', "
                        + " FINALISED='" + (logSession.isFinalised() ? 1 : 0) + "', "
                        + " TIMEENDED='" + logSession.getTimeEnded() + "', "
                        + " TIMESTARTED='" + logSession.getTimeStarted() + "',"
                        + " TURNOVER='" + logSession.getTurnOver() + "',"
                        + " LOGUSER_UID='" + logSession.getLogUser().getUid() + "' WHERE SEID='" + logSession.getSeid() + "' ");
            } catch (Exception ce) {
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = logSession.getSeid();
                if (findLogSession(id) == null) {
                    throw new NonexistentEntityException("The logSession with id " + id + " no longer exists.");
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
            LogSession logSession;
            try {
                logSession = em.getReference(LogSession.class, id);
                logSession.getSeid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The logSession with id " + id + " no longer exists.", enfe);
            }
            em.remove(logSession);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<LogSession> findLogSessionEntities() {
        return findLogSessionEntities(true, -1, -1);
    }

    @Override
    public List<LogSession> findLogSessionEntities(int maxResults, int firstResult) {
        return findLogSessionEntities(false, maxResults, firstResult);
    }

    private List<LogSession> findLogSessionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LogSession.class));
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
    public LogSession findLogSession(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LogSession.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getLogSessionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LogSession> rt = cq.from(LogSession.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
