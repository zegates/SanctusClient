package tireshop.persistance.entity;

import java.sql.Date;
import java.sql.Time;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tireshop.persistance.entity.LogUser;
import tireshop.persistance.entity.Orders;
import tireshop.persistance.entity.SupplyOrder;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-08T13:06:35")
@StaticMetamodel(LogSession.class)
public class LogSession_ { 

    public static volatile SingularAttribute<LogSession, Long> seid;
    public static volatile SingularAttribute<LogSession, Boolean> finalised;
    public static volatile ListAttribute<LogSession, Orders> orderss;
    public static volatile SingularAttribute<LogSession, LogUser> logUser;
    public static volatile SingularAttribute<LogSession, Time> timeStarted;
    public static volatile SingularAttribute<LogSession, Date> dateEnded;
    public static volatile SingularAttribute<LogSession, Date> dateStarted;
    public static volatile SingularAttribute<LogSession, Double> turnOver;
    public static volatile ListAttribute<LogSession, SupplyOrder> supplyOrders;
    public static volatile SingularAttribute<LogSession, Time> timeEnded;

}