
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
 * <p>Java class for logSession complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="logSession">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="supplyOrders" type="{http://remote.services.sanctus.zegates.com/}supplyOrder" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="orderss" type="{http://remote.services.sanctus.zegates.com/}orders" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="seid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="dateStarted" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="timeStarted" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="dateEnded" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="timeEnded" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="turnOver" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="logUser" type="{http://www.w3.org/2001/XMLSchema}IDREF" minOccurs="0"/>
 *         &lt;element name="finalised" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "logSession", propOrder = {
    "supplyOrders",
    "orderss",
    "seid",
    "dateStarted",
    "timeStarted",
    "dateEnded",
    "timeEnded",
    "turnOver",
    "logUser",
    "finalised"
})
public class LogSession {

    @XmlElement(nillable = true)
    protected List<SupplyOrder> supplyOrders;
    @XmlElement(nillable = true)
    protected List<Orders> orderss;
    protected Long seid;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateStarted;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeStarted;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateEnded;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeEnded;
    protected double turnOver;
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected LogUser logUser;
    protected boolean finalised;
    @XmlAttribute(name = "uuid")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String uuid;

    /**
     * Gets the value of the supplyOrders property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supplyOrders property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupplyOrders().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupplyOrder }
     * 
     * 
     */
    public List<SupplyOrder> getSupplyOrders() {
        if (supplyOrders == null) {
            supplyOrders = new ArrayList<SupplyOrder>();
        }
        return this.supplyOrders;
    }

    /**
     * Gets the value of the orderss property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderss property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderss().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Orders }
     * 
     * 
     */
    public List<Orders> getOrderss() {
        if (orderss == null) {
            orderss = new ArrayList<Orders>();
        }
        return this.orderss;
    }

    /**
     * Gets the value of the seid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSeid() {
        return seid;
    }

    /**
     * Sets the value of the seid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSeid(Long value) {
        this.seid = value;
        this.uuid = value +"";
    }

    /**
     * Gets the value of the dateStarted property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateStarted() {
        return dateStarted;
    }

    /**
     * Sets the value of the dateStarted property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateStarted(XMLGregorianCalendar value) {
        this.dateStarted = value;
    }

    /**
     * Gets the value of the timeStarted property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeStarted() {
        return timeStarted;
    }

    /**
     * Sets the value of the timeStarted property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeStarted(XMLGregorianCalendar value) {
        this.timeStarted = value;
    }

    /**
     * Gets the value of the dateEnded property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateEnded() {
        return dateEnded;
    }

    /**
     * Sets the value of the dateEnded property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateEnded(XMLGregorianCalendar value) {
        this.dateEnded = value;
    }

    /**
     * Gets the value of the timeEnded property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeEnded() {
        return timeEnded;
    }

    /**
     * Sets the value of the timeEnded property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeEnded(XMLGregorianCalendar value) {
        this.timeEnded = value;
    }

    /**
     * Gets the value of the turnOver property.
     * 
     */
    public double getTurnOver() {
        return turnOver;
    }

    /**
     * Sets the value of the turnOver property.
     * 
     */
    public void setTurnOver(double value) {
        this.turnOver = value;
    }

    /**
     * Gets the value of the logUser property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public LogUser getLogUser() {
        return logUser;
    }

    /**
     * Sets the value of the logUser property.
     * 
     * @param value
     *     allowed object is5
     *     {@link Object }
     *     
     */
    public void setLogUser(LogUser value) {
        this.logUser = value;
    }

    /**
     * Gets the value of the finalised property.
     * 
     */
    public boolean isFinalised() {
        return finalised;
    }

    /**
     * Sets the value of the finalised property.
     * 
     */
    public void setFinalised(boolean value) {
        this.finalised = value;
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
