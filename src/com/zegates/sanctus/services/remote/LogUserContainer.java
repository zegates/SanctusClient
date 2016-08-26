
package com.zegates.sanctus.services.remote;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for logUserContainer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="logUserContainer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="logUser" type="{http://remote.services.sanctus.zegates.com/}logUser" minOccurs="0"/>
 *         &lt;element name="logSessions" type="{http://remote.services.sanctus.zegates.com/}logSession" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "logUserContainer", propOrder = {
    "logUser",
    "logSessions"
})
public class LogUserContainer {

    protected LogUser logUser;
    @XmlElement(nillable = true)
    protected List<LogSession> logSessions;

    /**
     * Gets the value of the logUser property.
     * 
     * @return
     *     possible object is
     *     {@link LogUser }
     *     
     */
    public LogUser getLogUser() {
        return logUser;
    }

    /**
     * Sets the value of the logUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link LogUser }
     *     
     */
    public void setLogUser(LogUser value) {
        this.logUser = value;
    }

    /**
     * Gets the value of the logSessions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the logSessions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLogSessions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LogSession }
     * 
     * 
     */
    public List<LogSession> getLogSessions() {
        if (logSessions == null) {
            logSessions = new ArrayList<LogSession>();
        }
        return this.logSessions;
    }

}
