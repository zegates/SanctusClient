
package com.zegates.sanctus.services.remote;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for supplyOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="supplyOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="supplyOrderDetails" type="{http://remote.services.sanctus.zegates.com/}supplyOrderDetail" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="soid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="dateAdded" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="timeAdded" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="supplier" type="{http://remote.services.sanctus.zegates.com/}supplier" minOccurs="0"/>
 *         &lt;element name="logSession" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="uuid" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "supplyOrder", propOrder = {
    "supplyOrderDetails",
    "soid",
    "dateAdded",
    "timeAdded",
    "discount",
    "total",
    "supplier",
    "logSession"
})
public class SupplyOrder {

    @XmlElement(nillable = true)
    protected List<SupplyOrderDetail> supplyOrderDetails;
    protected Long soid;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateAdded;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeAdded;
    protected double discount;
    protected double total;
    protected Supplier supplier;
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object logSession;
    @XmlAttribute(name = "uuid")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String uuid;

    /**
     * Gets the value of the supplyOrderDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supplyOrderDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupplyOrderDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupplyOrderDetail }
     * 
     * 
     */
    public List<SupplyOrderDetail> getSupplyOrderDetails() {
        if (supplyOrderDetails == null) {
            supplyOrderDetails = new ArrayList<SupplyOrderDetail>();
        }
        return this.supplyOrderDetails;
    }

    public void setSupplyOrderDetails(List<SupplyOrderDetail> supplyOrderDetails) {
        this.supplyOrderDetails = supplyOrderDetails;
    }

    
    /**
     * Gets the value of the soid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSoid() {
        return soid;
    }

    /**
     * Sets the value of the soid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSoid(Long value) {
        this.soid = value;
    }

    /**
     * Gets the value of the dateAdded property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateAdded() {
        return dateAdded;
    }

    /**
     * Sets the value of the dateAdded property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateAdded(XMLGregorianCalendar value) {
        this.dateAdded = value;
    }

    /**
     * Gets the value of the timeAdded property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeAdded() {
        return timeAdded;
    }

    /**
     * Sets the value of the timeAdded property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeAdded(XMLGregorianCalendar value) {
        this.timeAdded = value;
    }

    /**
     * Gets the value of the discount property.
     * 
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * Sets the value of the discount property.
     * 
     */
    public void setDiscount(double value) {
        this.discount = value;
    }

    /**
     * Gets the value of the total property.
     * 
     */
    public double getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     */
    public void setTotal(double value) {
        this.total = value;
    }

    /**
     * Gets the value of the supplier property.
     * 
     * @return
     *     possible object is
     *     {@link Supplier }
     *     
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Sets the value of the supplier property.
     * 
     * @param value
     *     allowed object is
     *     {@link Supplier }
     *     
     */
    public void setSupplier(Supplier value) {
        this.supplier = value;
    }

    /**
     * Gets the value of the logSession property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLogSession() {
        return logSession;
    }

    /**
     * Sets the value of the logSession property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLogSession(Object value) {
        this.logSession = value;
    }

    /**
     * Gets the value of the uuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the value of the uuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

}
