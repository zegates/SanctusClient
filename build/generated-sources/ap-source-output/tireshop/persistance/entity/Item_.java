package tireshop.persistance.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tireshop.persistance.entity.Category;
import tireshop.persistance.entity.Construction;
import tireshop.persistance.entity.Manufacturer;
import tireshop.persistance.entity.SupplyOrderDetail;
import tireshop.persistance.entity.TubeType;
import tireshop.persistance.entity.VehicleType;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2016-08-08T13:06:35")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, TubeType> tubeType;
    public static volatile SingularAttribute<Item, Category> category;
    public static volatile SingularAttribute<Item, Long> iid;
    public static volatile SingularAttribute<Item, Manufacturer> manufacturer;
    public static volatile ListAttribute<Item, SupplyOrderDetail> supplyOrderDetails;
    public static volatile SingularAttribute<Item, String> name;
    public static volatile SingularAttribute<Item, VehicleType> vehicleType;
    public static volatile SingularAttribute<Item, Construction> construction;

}