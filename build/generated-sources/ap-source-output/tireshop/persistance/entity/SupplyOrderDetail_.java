package tireshop.persistance.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tireshop.persistance.entity.Item;
import tireshop.persistance.entity.OrderDetail;
import tireshop.persistance.entity.SupplyOrder;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-08T13:06:35")
@StaticMetamodel(SupplyOrderDetail.class)
public class SupplyOrderDetail_ { 

    public static volatile SingularAttribute<SupplyOrderDetail, Double> sellingPrice;
    public static volatile ListAttribute<SupplyOrderDetail, OrderDetail> orderDetails;
    public static volatile SingularAttribute<SupplyOrderDetail, Double> buyingPrice;
    public static volatile SingularAttribute<SupplyOrderDetail, Long> sodid;
    public static volatile SingularAttribute<SupplyOrderDetail, Item> item;
    public static volatile SingularAttribute<SupplyOrderDetail, SupplyOrder> supplyOrder;
    public static volatile SingularAttribute<SupplyOrderDetail, Integer> qty;
    public static volatile SingularAttribute<SupplyOrderDetail, Integer> remainingQty;

}