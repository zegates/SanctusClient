package tireshop.persistance.entity;

import java.sql.Date;
import java.sql.Time;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tireshop.persistance.entity.SupplyOrder;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-08T13:06:35")
@StaticMetamodel(Supplier.class)
public class Supplier_ { 

    public static volatile SingularAttribute<Supplier, Time> timeAdded;
    public static volatile SingularAttribute<Supplier, String> tpno;
    public static volatile SingularAttribute<Supplier, Long> suid;
    public static volatile SingularAttribute<Supplier, String> address;
    public static volatile SingularAttribute<Supplier, String> email;
    public static volatile SingularAttribute<Supplier, String> name;
    public static volatile SingularAttribute<Supplier, String> compName;
    public static volatile SingularAttribute<Supplier, Date> dateAdded;
    public static volatile ListAttribute<Supplier, SupplyOrder> supplyOrders;

}