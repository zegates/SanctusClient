
package com.zegates.sanctus.services.remote;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for supplier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="supplier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="compName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateAdded" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="suid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="supplyOrders" type="{http://remote.services.sanctus.zegates.com/}supplyOrder" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="timeAdded" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="tpno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "supplier", propOrder = {
    "address",
    "compName",
    "dateAdded",
    "email",
    "name",
    "suid",
    "supplyOrders",
    "timeAdded",
    "tpno"
})
public class Supplier {

    protected String address;
    protected String compName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateAdded;
    protected String email;
    protected String name;
    protected Long suid;
    @XmlElement(nillable = true)
    protected List<SupplyOrder> supplyOrders;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeAdded;
    protected String tpno;

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
     * Gets the value of the compName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompName() {
        return compName;
    }

    /**
     * Sets the value of the compName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompName(String value) {
        this.compName = value;
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
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the suid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSuid() {
        return suid;
    }

    /**
     * Sets the value of the suid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSuid(Long value) {
        this.suid = value;
    }

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
     * Gets the value of the tpno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTpno() {
        return tpno;
    }

    /**
     * Sets the value of the tpno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTpno(String value) {
        this.tpno = value;
    }

}
