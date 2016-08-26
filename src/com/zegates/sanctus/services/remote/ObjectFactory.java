
package com.zegates.sanctus.services.remote;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.zegates.sanctus.services.remote package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _LogUserLogSessions_QNAME = new QName("", "logSessions");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.zegates.sanctus.services.remote
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LogUser }
     * 
     */
    public LogUser createLogUser() {
        return new LogUser();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link SupplyOrder }
     * 
     */
    public SupplyOrder createSupplyOrder() {
        return new SupplyOrder();
    }

    /**
     * Create an instance of {@link Metric }
     * 
     */
    public Metric createMetric() {
        return new Metric();
    }

    /**
     * Create an instance of {@link LogUserArray }
     * 
     */
    public LogUserArray createLogUserArray() {
        return new LogUserArray();
    }

    /**
     * Create an instance of {@link Supplier }
     * 
     */
    public Supplier createSupplier() {
        return new Supplier();
    }

    /**
     * Create an instance of {@link LogSession }
     * 
     */
    public LogSession createLogSession() {
        return new LogSession();
    }

    /**
     * Create an instance of {@link OrderDetail }
     * 
     */
    public OrderDetail createOrderDetail() {
        return new OrderDetail();
    }

    /**
     * Create an instance of {@link Orders }
     * 
     */
    public Orders createOrders() {
        return new Orders();
    }

    /**
     * Create an instance of {@link SupplyOrderDetail }
     * 
     */
    public SupplyOrderDetail createSupplyOrderDetail() {
        return new SupplyOrderDetail();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link Manufacturer }
     * 
     */
    public Manufacturer createManufacturer() {
        return new Manufacturer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "logSessions", scope = LogUser.class)
    @XmlIDREF
    public JAXBElement<Object> createLogUserLogSessions(Object value) {
        return new JAXBElement<Object>(_LogUserLogSessions_QNAME, Object.class, LogUser.class, value);
    }

}
