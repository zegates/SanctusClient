
package com.zegates.sanctus.services.remote;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for supplyOrderDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="supplyOrderDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="buyingPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="item" type="{http://remote.services.sanctus.zegates.com/}item" minOccurs="0"/>
 *         &lt;element name="orderDetails" type="{http://remote.services.sanctus.zegates.com/}orderDetail" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="remainingQty" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sellingPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="sodid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="supplyOrder" type="{http://remote.services.sanctus.zegates.com/}supplyOrder" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "supplyOrderDetail", propOrder = {
    "buyingPrice",
    "item",
    "orderDetails",
    "qty",
    "remainingQty",
    "sellingPrice",
    "sodid",
    "supplyOrder"
})
public class SupplyOrderDetail {

    protected double buyingPrice;
    protected Item item;
    @XmlElement(nillable = true)
    protected List<OrderDetail> orderDetails;
    protected int qty;
    protected int remainingQty;
    protected double sellingPrice;
    protected Long sodid;
    protected SupplyOrder supplyOrder;

    /**
     * Gets the value of the buyingPrice property.
     * 
     */
    public double getBuyingPrice() {
        return buyingPrice;
    }

    /**
     * Sets the value of the buyingPrice property.
     * 
     */
    public void setBuyingPrice(double value) {
        this.buyingPrice = value;
    }

    /**
     * Gets the value of the item property.
     * 
     * @return
     *     possible object is
     *     {@link Item }
     *     
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the value of the item property.
     * 
     * @param value
     *     allowed object is
     *     {@link Item }
     *     
     */
    public void setItem(Item value) {
        this.item = value;
    }

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

    /**
     * Gets the value of the qty property.
     * 
     */
    public int getQty() {
        return qty;
    }

    /**
     * Sets the value of the qty property.
     * 
     */
    public void setQty(int value) {
        this.qty = value;
    }

    /**
     * Gets the value of the remainingQty property.
     * 
     */
    public int getRemainingQty() {
        return remainingQty;
    }

    /**
     * Sets the value of the remainingQty property.
     * 
     */
    public void setRemainingQty(int value) {
        this.remainingQty = value;
    }

    /**
     * Gets the value of the sellingPrice property.
     * 
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * Sets the value of the sellingPrice property.
     * 
     */
    public void setSellingPrice(double value) {
        this.sellingPrice = value;
    }

    /**
     * Gets the value of the sodid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSodid() {
        return sodid;
    }

    /**
     * Sets the value of the sodid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSodid(Long value) {
        this.sodid = value;
    }

    /**
     * Gets the value of the supplyOrder property.
     * 
     * @return
     *     possible object is
     *     {@link SupplyOrder }
     *     
     */
    public SupplyOrder getSupplyOrder() {
        return supplyOrder;
    }

    /**
     * Sets the value of the supplyOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplyOrder }
     *     
     */
    public void setSupplyOrder(SupplyOrder value) {
        this.supplyOrder = value;
    }

}
