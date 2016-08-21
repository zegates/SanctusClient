package tireshop.persistance.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tireshop.persistance.entity.Item;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-08T13:06:35")
@StaticMetamodel(VehicleType.class)
public class VehicleType_ { 

    public static volatile ListAttribute<VehicleType, Item> items;
    public static volatile SingularAttribute<VehicleType, String> name;
    public static volatile SingularAttribute<VehicleType, Long> vid;

}