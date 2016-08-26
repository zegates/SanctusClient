
package com.zegates.sanctus.services.remote;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for cashTransfer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="cashTransfer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ctid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="dateAdded" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="timeAdded" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cashTransferType" type="{http://remote.services.sanctus.zegates.com/}cashTransferType" minOccurs="0"/>
 *         &lt;element name="logSession" type="{http://remote.services.sanctus.zegates.com/}logSession" minOccurs="0"/>
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
@XmlType(name = "cashTransfer", propOrder = {
    "ctid",
    "amount",
    "dateAdded",
    "timeAdded",
    "cashTransferType",
    "logSession"
})
public class CashTransfer {

    protected Long ctid;
    protected double amount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateAdded;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeAdded;
    @XmlSchemaType(name = "string")
    protected CashTransferType cashTransferType;
    protected LogSession logSession;
    @XmlAttribute(name = "uuid")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String uuid;

    /**
     * Gets the value of the ctid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCtid() {
        return ctid;
    }

    /**
     * Sets the value of the ctid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCtid(Long value) {
        this.ctid = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(double value) {
        this.amount = value;
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
     * Gets the value of the cashTransferType property.
     * 
     * @return
     *     possible object is
     *     {@link CashTransferType }
     *     
     */
    public CashTransferType getCashTransferType() {
        return cashTransferType;
    }

    /**
     * Sets the value of the cashTransferType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CashTransferType }
     *     
     */
    public void setCashTransferType(CashTransferType value) {
        this.cashTransferType = value;
    }

    /**
     * Gets the value of the logSession property.
     * 
     * @return
     *     possible object is
     *     {@link LogSession }
     *     
     */
    public LogSession getLogSession() {
        return logSession;
    }

    /**
     * Sets the value of the logSession property.
     * 
     * @param value
     *     allowed object is
     *     {@link LogSession }
     *     
     */
    public void setLogSession(LogSession value) {
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
