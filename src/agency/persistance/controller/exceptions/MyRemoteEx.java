/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.exceptions;

/**
 *
 * @author Thilina
 */
public class MyRemoteEx extends Exception {

    /**
     * Creates a new instance of
     * <code>MyRemoteEx</code> without detail message.
     */
    public MyRemoteEx() {
    }

    /**
     * Constructs an instance of
     * <code>MyRemoteEx</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public MyRemoteEx(String msg) {
        super(msg);
    }
    
    
}
