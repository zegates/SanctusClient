
package com.zegates.sanctus.services.remote;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for orderDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="orderDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="odid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="order" type="{http://remote.services.sanctus.zegates.com/}orders" minOccurs="0"/>
 *         &lt;element name="qty" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="supplyOrderDetail" type="{http://remote.services.sanctus.zegates.com/}supplyOrderDetail" minOccurs="0"/>
 *         &lt;element name="unitPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderDetail", propOrder = {
    "odid",
    "order",
    "qty",
    "supplyOrderDetail",
    "unitPrice"
})
public class OrderDetail {

    protected Long odid;
    protected Orders order;
    protected int qty;
    protected SupplyOrderDetail supplyOrderDetail;
    protected double unitPrice;

    /**
     * Gets the value of the odid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOdid() {
        return odid;
    }

    /**
     * Sets the value of the odid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOdid(Long value) {
        this.odid = value;
    }

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link Orders }
     *     
     */
    public Orders getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link Orders }
     *     
     */
    public void setOrder(Orders value) {
        this.order = value;
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
     * Gets the value of the supplyOrderDetail property.
     * 
     * @return
     *     possible object is
     *     {@link SupplyOrderDetail }
     *     
     */
    public SupplyOrderDetail getSupplyOrderDetail() {
        return supplyOrderDetail;
    }

    /**
     * Sets the value of the supplyOrderDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplyOrderDetail }
     *     
     */
    public void setSupplyOrderDetail(SupplyOrderDetail value) {
        this.supplyOrderDetail = value;
    }

    /**
     * Gets the value of the unitPrice property.
     * 
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Sets the value of the unitPrice property.
     * 
     */
    public void setUnitPrice(double value) {
        this.unitPrice = value;
    }

}
