package tireshop.persistance.entity;

import java.sql.Date;
import java.sql.Time;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tireshop.persistance.entity.LogSession;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-08T13:06:35")
@StaticMetamodel(LogUser.class)
public class LogUser_ { 

    public static volatile SingularAttribute<LogUser, Long> uid;
    public static volatile SingularAttribute<LogUser, Time> timeAdded;
    public static volatile SingularAttribute<LogUser, String> tpno;
    public static volatile SingularAttribute<LogUser, String> username;
    public static volatile SingularAttribute<LogUser, String> address;
    public static volatile SingularAttribute<LogUser, String> name;
    public static volatile SingularAttribute<LogUser, Date> dateAdded;
    public static volatile SingularAttribute<LogUser, String> pw;
    public static volatile ListAttribute<LogUser, LogSession> logSessions;

}