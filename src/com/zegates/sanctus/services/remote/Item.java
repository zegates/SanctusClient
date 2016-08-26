
package com.zegates.sanctus.services.remote;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for item complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="category" type="{http://remote.services.sanctus.zegates.com/}category" minOccurs="0"/>
 *         &lt;element name="iid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="manufacturer" type="{http://remote.services.sanctus.zegates.com/}manufacturer" minOccurs="0"/>
 *         &lt;element name="metric" type="{http://remote.services.sanctus.zegates.com/}metric" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplyOrderDetails" type="{http://remote.services.sanctus.zegates.com/}supplyOrderDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "item", propOrder = {
    "category",
    "iid",
    "manufacturer",
    "metric",
    "name",
    "supplyOrderDetails"
})
public class Item {

    protected Category category;
    protected Long iid;
    protected Manufacturer manufacturer;
    protected Metric metric;
    protected String name;
    @XmlElement(nillable = true)
    protected List<SupplyOrderDetail> supplyOrderDetails;

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link Category }
     *     
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link Category }
     *     
     */
    public void setCategory(Category value) {
        this.category = value;
    }

    /**
     * Gets the value of the iid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIid() {
        return iid;
    }

    /**
     * Sets the value of the iid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIid(Long value) {
        this.iid = value;
    }

    /**
     * Gets the value of the manufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link Manufacturer }
     *     
     */
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the value of the manufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Manufacturer }
     *     
     */
    public void setManufacturer(Manufacturer value) {
        this.manufacturer = value;
    }

    /**
     * Gets the value of the metric property.
     * 
     * @return
     *     possible object is
     *     {@link Metric }
     *     
     */
    public Metric getMetric() {
        return metric;
    }

    /**
     * Sets the value of the metric property.
     * 
     * @param value
     *     allowed object is
     *     {@link Metric }
     *     
     */
    public void setMetric(Metric value) {
        this.metric = value;
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

}
