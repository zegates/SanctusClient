package tireshop.persistance.entity;

import java.sql.Date;
import java.sql.Time;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tireshop.persistance.entity.LogSession;
import tireshop.persistance.entity.OrderDetail;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-08T13:06:35")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Time> timeAdded;
    public static volatile SingularAttribute<Orders, Double> total;
    public static volatile SingularAttribute<Orders, LogSession> logSession;
    public static volatile SingularAttribute<Orders, Boolean> paid;
    public static volatile ListAttribute<Orders, OrderDetail> orderDetails;
    public static volatile SingularAttribute<Orders, String> address;
    public static volatile SingularAttribute<Orders, Long> oid;
    public static volatile SingularAttribute<Orders, Date> dateAdded;
    public static volatile SingularAttribute<Orders, Double> paidAmount;
    public static volatile SingularAttribute<Orders, String> custName;
    public static volatile SingularAttribute<Orders, String> tpNo;
    public static volatile SingularAttribute<Orders, Double> discount;

}