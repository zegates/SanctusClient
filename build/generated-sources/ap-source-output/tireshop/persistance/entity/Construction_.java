package tireshop.persistance.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tireshop.persistance.entity.Item;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-08T13:06:35")
@StaticMetamodel(Construction.class)
public class Construction_ { 

    public static volatile ListAttribute<Construction, Item> items;
    public static volatile SingularAttribute<Construction, String> name;
    public static volatile SingularAttribute<Construction, Long> cid;

}