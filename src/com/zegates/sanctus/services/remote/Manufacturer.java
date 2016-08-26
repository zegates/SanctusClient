
package com.zegates.sanctus.services.remote;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for manufacturer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="manufacturer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="items" type="{http://remote.services.sanctus.zegates.com/}item" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="manuid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "manufacturer", propOrder = {
    "items",
    "manuid",
    "name"
})
public class Manufacturer implements Comparable<Manufacturer>{

    @XmlElement(nillable = true)
    protected List<Item> items;
    protected Long manuid;
    protected String name;

    /**
     * Gets the value of the items property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the items property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Item }
     * 
     * 
     */
    public List<Item> getItems() {
        if (items == null) {
            items = new ArrayList<Item>();
        }
        return this.items;
    }

    /**
     * Gets the value of the manuid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getManuid() {
        return manuid;
    }

    /**
     * Sets the value of the manuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setManuid(Long value) {
        this.manuid = value;
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

    @Override
    public int compareTo(Manufacturer o) {
        return this.name.compareTo(o.getName());
    }

}
