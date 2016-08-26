
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
 * <p>Java class for orders complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="orders">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderDetails" type="{http://remote.services.sanctus.zegates.com/}orderDetail" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="oid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="custName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tpNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateAdded" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="timeAdded" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="paidAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="paid" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "orders", propOrder = {
    "orderDetails",
    "oid",
    "custName",
    "tpNo",
    "dateAdded",
    "timeAdded",
    "address",
    "total",
    "discount",
    "paidAmount",
    "paid",
    "logSession"
})
public class Orders {

    @XmlElement(nillable = true)
    protected List<OrderDetail> orderDetails;
    protected Long oid;
    protected String custName;
    protected String tpNo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateAdded;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeAdded;
    protected String address;
    protected double total;
    protected double discount;
    protected double paidAmount;
    protected boolean paid;
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object logSession;
    @XmlAttribute(name = "uuid")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String uuid;

    /**
     * Gets the value of the orderDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderDetail }
     * 
     * 
     */
    public List<OrderDetail> getOrderDetails() {
        if (orderDetails == null) {
            orderDetails = new ArrayList<OrderDetail>();
        }
        return this.orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    

    /**
     * Gets the value of the oid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOid() {
        return oid;
    }

    /**
     * Sets the value of the oid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOid(Long value) {
        this.oid = value;
    }

    /**
     * Gets the value of the custName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustName() {
        return custName;
    }

    /**
     * Sets the value of the custName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustName(String value) {
        this.custName = value;
    }

    /**
     * Gets the value of the tpNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpNo() {
        return tpNo;
    }

    /**
     * Sets the value of the tpNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpNo(String value) {
        this.tpNo = value;
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
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
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
     * Gets the value of the paidAmount property.
     * 
     */
    public double getPaidAmount() {
        return paidAmount;
    }

    /**
     * Sets the value of the paidAmount property.
     * 
     */
    public void setPaidAmount(double value) {
        this.paidAmount = value;
    }

    /**
     * Gets the value of the paid property.
     * 
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * Sets the value of the paid property.
     * 
     */
    public void setPaid(boolean value) {
        this.paid = value;
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
