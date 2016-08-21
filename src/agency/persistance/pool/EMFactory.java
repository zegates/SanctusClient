/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.pool;

import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Sandaruwan
 */
public class EMFactory {

    private EntityManagerFactory emf;
    private boolean inUse;
    private Long lastUse;

    public EMFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public boolean isInUse() {
        return inUse;
    }

    public long getLastUse() {
        return lastUse;
    }

    public boolean lease() {
        if (inUse) {
            return false;
        } else {
            inUse = true;
            lastUse = System.currentTimeMillis();
            //System.out.println("Leasing........");
            return true;
        }
    }
}
