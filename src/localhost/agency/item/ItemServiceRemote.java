
package localhost.agency.item;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import com.zegates.sanctus.services.remote.Item;
import com.zegates.sanctus.services.remote.ItemArray;
import com.zegates.sanctus.services.remote.ObjectFactory;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ItemServiceRemote", targetNamespace = "http://remote.services.sanctus.zegates.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ItemServiceRemote {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    public boolean create(
        @WebParam(name = "arg0", partName = "arg0")
        Item arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    public void destroy(
        @WebParam(name = "arg0", partName = "arg0")
        long arg0);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    public String getHello();

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns com.zegates.sanctus.services.remote.Item
     */
    @WebMethod
    @WebResult(partName = "return")
    public Item findItemForNameAndManu(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @return
     *     returns com.zegates.sanctus.services.remote.ItemArray
     */
    @WebMethod
    @WebResult(partName = "return")
    public ItemArray findItemEntities();

    /**
     * 
     * @param arg0
     * @return
     *     returns com.zegates.sanctus.services.remote.Item
     */
    @WebMethod
    @WebResult(partName = "return")
    public Item findItem(
        @WebParam(name = "arg0", partName = "arg0")
        long arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    public void edit(
        @WebParam(name = "arg0", partName = "arg0")
        Item arg0);

    /**
     * 
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(partName = "return")
    public int getItemCount();

}
