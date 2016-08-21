/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Sandaruwan
 */
@Entity
public class Supplier implements Serializable {

    @OneToMany(mappedBy = "supplier")
    private List<SupplyOrder> supplyOrders;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long suid;
    private String name;
    private Date dateAdded;
    private Time timeAdded;
    private String address;
    private String tpno;
    private String compName;
    private String email;

    public List<SupplyOrder> getSupplyOrders() {
        return supplyOrders;
    }

    public void setSupplyOrders(List<SupplyOrder> supplyOrders) {
        this.supplyOrders = supplyOrders;
    }

    public Long getSuid() {
        return suid;
    }

    public void setSuid(Long suid) {
        this.suid = suid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Time getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Time timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTpno() {
        return tpno;
    }

    public void setTpno(String tpno) {
        this.tpno = tpno;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (suid != null ? suid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the suid fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.suid == null && other.suid != null) || (this.suid != null && !this.suid.equals(other.suid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tireshop.persistance.entity.Supplier[ suid=" + suid + " ]";
    }
}