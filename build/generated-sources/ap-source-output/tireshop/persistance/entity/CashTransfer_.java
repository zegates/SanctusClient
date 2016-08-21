package tireshop.persistance.entity;

import java.sql.Date;
import java.sql.Time;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tireshop.persistance.entity.CashTransferType;
import tireshop.persistance.entity.LogSession;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-08T13:06:35")
@StaticMetamodel(CashTransfer.class)
public class CashTransfer_ { 

    public static volatile SingularAttribute<CashTransfer, Double> amount;
    public static volatile SingularAttribute<CashTransfer, Time> timeAdded;
    public static volatile SingularAttribute<CashTransfer, LogSession> logSession;
    public static volatile SingularAttribute<CashTransfer, CashTransferType> cashTransferType;
    public static volatile SingularAttribute<CashTransfer, Date> dateAdded;
    public static volatile SingularAttribute<CashTransfer, Long> ctid;

}