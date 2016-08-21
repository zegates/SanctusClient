package tireshop.persistance.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tireshop.persistance.entity.Orders;
import tireshop.persistance.entity.SupplyOrderDetail;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-08T13:06:35")
@StaticMetamodel(OrderDetail.class)
public class OrderDetail_ { 

    public static volatile SingularAttribute<OrderDetail, Long> odid;
    public static volatile SingularAttribute<OrderDetail, Orders> order;
    public static volatile SingularAttribute<OrderDetail, SupplyOrderDetail> supplyOrderDetail;
    public static volatile SingularAttribute<OrderDetail, Integer> qty;
    public static volatile SingularAttribute<OrderDetail, Double> unitPrice;

}