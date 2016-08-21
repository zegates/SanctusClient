package tireshop.persistance.entity;

import java.sql.Date;
import java.sql.Time;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tireshop.persistance.entity.LogSession;
import tireshop.persistance.entity.Supplier;
import tireshop.persistance.entity.SupplyOrderDetail;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-08T13:06:35")
@StaticMetamodel(SupplyOrder.class)
public class SupplyOrder_ { 

    public static volatile SingularAttribute<SupplyOrder, Time> timeAdded;
    public static volatile SingularAttribute<SupplyOrder, Double> total;
    public static volatile SingularAttribute<SupplyOrder, LogSession> logSession;
    public static volatile ListAttribute<SupplyOrder, SupplyOrderDetail> supplyOrderDetails;
    public static volatile SingularAttribute<SupplyOrder, Long> soid;
    public static volatile SingularAttribute<SupplyOrder, Date> dateAdded;
    public static volatile SingularAttribute<SupplyOrder, Supplier> supplier;
    public static volatile SingularAttribute<SupplyOrder, Double> discount;

}