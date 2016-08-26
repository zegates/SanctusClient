
package com.zegates.sanctus.services.remote;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cashTransferType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="cashTransferType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Recieve"/>
 *     &lt;enumeration value="Transfer"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "cashTransferType")
@XmlEnum
public enum CashTransferType {

    @XmlEnumValue("Recieve")
    RECIEVE("Recieve"),
    @XmlEnumValue("Transfer")
    TRANSFER("Transfer");
    private final String value;

    CashTransferType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CashTransferType fromValue(String v) {
        for (CashTransferType c: CashTransferType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
