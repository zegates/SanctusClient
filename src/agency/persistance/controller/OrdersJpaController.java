/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller;

import agency.persistance.controller.remote.OrdersController;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.entity.LogSession;
import agency.persistance.entity.OrderDetail;
import agency.persistance.entity.Orders;
import agency.remote.RemoteDBHandler;

/**
 *
 * @author Sandaruwan
 */
public class OrdersJpaController extends Observable implements OrdersController {

    private int o;

    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }

    public OrdersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Orders orders) throws ClassNotFoundException, SQLException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(orders);
            em.merge(orders);
            em.getTransaction().commit();

            em = getEntityManager();
            em.getTransaction().begin();
            LogSession logSession = orders.getLogSession();
            if (logSession != null) {
                logSession = em.getReference(logSession.getClass(), logSession.getSeid());
                orders.setLogSession(logSession);
            }
            List<OrderDetail> attachedOrderDetails = new ArrayList<OrderDetail>();
            for (OrderDetail orderDetailsOrderDetailToAttach : orders.getOrderDetails()) {
                orderDetailsOrderDetailToAttach = em.getReference(orderDetailsOrderDetailToAttach.getClass(), orderDetailsOrderDetailToAttach.getOdid());
                attachedOrderDetails.add(orderDetailsOrderDetailToAttach);
            }
            orders.setOrderDetails(attachedOrderDetails);
            if (logSession != null) {
                logSession.getOrderss().add(orders);
                logSession = em.merge(logSession);
            }
            for (OrderDetail orderDetailsOrderDetail : orders.getOrderDetails()) {
                Orders oldOrderOfOrderDetailsOrderDetail = orderDetailsOrderDetail.getOrder();
                orderDetailsOrderDetail.setOrder(orders);
                orderDetailsOrderDetail = em.merge(orderDetailsOrderDetail);
                if (oldOrderOfOrderDetailsOrderDetail != null) {
                    oldOrderOfOrderDetailsOrderDetail.getOrderDetails().remove(orderDetailsOrderDetail);
                    oldOrderOfOrderDetailsOrderDetail = em.merge(oldOrderOfOrderDetailsOrderDetail);
                }
            }
            em.getTransaction().commit();
            try {
                RemoteDBHandler.setData("INSERT INTO orders  (`OID`, `ADDRESS`, `CUSTNAME`, `DATEADDED`,"
                        + " `DISCOUNT`, `PAIDAMOUNT`, `TIMEADDED`, `TOTAL`, `TPNO`, `LOGSESSION_SEID`)"
                        + " VALUES ('" + orders.getOid() + "', '" + orders.getAddress() + "', '" + orders.getCustName() + "', "
                        + "'" + orders.getDateAdded() + "', '" + orders.getDiscount() + "', '" + orders.getPaidAmount() + "', '" + orders.getTimeAdded() + "',"
                        + " '" + orders.getTotal() + "', '" + orders.getTpNo() + "', '" + orders.getLogSession().getSeid() + "')");
                List<OrderDetail> orderDetails = orders.getOrderDetails();
                List<Long> odids = new ArrayList<>();

                for (int i = 0; i < orderDetails.size(); i++) {
                    OrderDetail orderDetail = orderDetails.get(i);
                    if (!odids.contains(orderDetail.getOdid())) {
                        odids.add(orderDetail.getOdid());
                        RemoteDBHandler.setData("INSERT INTO `orderdetail` (`ODID`, `QTY`, `UNITPRICE`, "
                                + "`ORDER_OID`, `SUPPLYORDERDETAIL_SODID`) "
                                + "VALUES ('" + orderDetail.getOdid() + "', '" + orderDetail.getQty() + "',"
                                + " '" + orderDetail.getUnitPrice() + "', '" + orderDetail.getOrder().getOid() + "', "
                                + "'" + orderDetail.getSupplyOrderDetail().getSodid() + "')");
                    }
                }
            } catch (Exception ce) {
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Orders orders) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            orders = em.merge(orders);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = orders.getOid();
                if (findOrders(id) == null) {
                    throw new NonexistentEntityException("The orders with id " + id + " no longer exists.");
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
    public void destroy(Long id) throws NonexistentEntityException, ClassNotFoundException, SQLException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orders orders;
            try {
                orders = em.getReference(Orders.class, id);
                orders.getOid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orders with id " + id + " no longer exists.", enfe);
            }
            LogSession logSession = orders.getLogSession();
            if (logSession != null) {
                logSession.getOrderss().remove(orders);
                logSession = em.merge(logSession);
            }
            List<OrderDetail> orderDetails = orders.getOrderDetails();
            for (OrderDetail orderDetailsOrderDetail : orderDetails) {
                orderDetailsOrderDetail.setOrder(null);
                orderDetailsOrderDetail = em.merge(orderDetailsOrderDetail);
            }
            em.remove(orders);
            try{
                List<OrderDetail> orderDetails1 = orders.getOrderDetails();
                for (int i = 0; i < orderDetails1.size(); i++) {
                    OrderDetail orderDetail = orderDetails1.get(i);
                    RemoteDBHandler.setData("DELETE FROM `orderdetail` WHERE `odid`="+orderDetail.getOdid()+"");
                }
                RemoteDBHandler.setData("DELETE FROM `orders` WHERE `oid`="+orders.getOid()+"");
                
                
            } catch (Exception ce) {
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Orders> findOrdersEntities() {
        return findOrdersEntities(true, -1, -1);
    }

    @Override
    public List<Orders> findOrdersEntitiesPaidfalse(boolean paid) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT o FROM Orders o WHERE o.paid=" + paid);
        return query.getResultList();
    }

    @Override
    public List<Orders> findOrdersEntities(int maxResults, int firstResult) {
        return findOrdersEntities(false, maxResults, firstResult);
    }

    private List<Orders> findOrdersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orders.class));
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
    public Orders findOrders(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orders.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getOrdersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orders> rt = cq.from(Orders.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public long getLatesOrdersID() {
        List<Orders> os = findOrdersEntities();
        if (os != null && os.size() > 0) {
            Collections.reverse(os);
            Orders get = os.get(0);
            if (get != null) {
                return get.getOid();
            }
        }else{
            return 1L;
        }
        return 1L;
    }
}
