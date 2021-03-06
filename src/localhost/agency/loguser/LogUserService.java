
package localhost.agency.loguser;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "LogUserService", targetNamespace = "http://localhost/agency/loguser", wsdlLocation = "http://localhost:8080/Sanctus-ejb/LogUserService/LogUserService?wsdl")
public class LogUserService
    extends Service
{

    private final static URL LOGUSERSERVICE_WSDL_LOCATION;
    private final static WebServiceException LOGUSERSERVICE_EXCEPTION;
    private final static QName LOGUSERSERVICE_QNAME = new QName("http://localhost/agency/loguser", "LogUserService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/Sanctus-ejb/LogUserService/LogUserService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LOGUSERSERVICE_WSDL_LOCATION = url;
        LOGUSERSERVICE_EXCEPTION = e;
    }

    public LogUserService() {
        super(__getWsdlLocation(), LOGUSERSERVICE_QNAME);
    }

    public LogUserService(WebServiceFeature... features) {
        super(__getWsdlLocation(), LOGUSERSERVICE_QNAME, features);
    }

    public LogUserService(URL wsdlLocation) {
        super(wsdlLocation, LOGUSERSERVICE_QNAME);
    }

    public LogUserService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LOGUSERSERVICE_QNAME, features);
    }

    public LogUserService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LogUserService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns LogUserServiceRemote
     */
    @WebEndpoint(name = "LogUserServicePort")
    public LogUserServiceRemote getLogUserServicePort() {
        return super.getPort(new QName("http://localhost/agency/loguser", "LogUserServicePort"), LogUserServiceRemote.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LogUserServiceRemote
     */
    @WebEndpoint(name = "LogUserServicePort")
    public LogUserServiceRemote getLogUserServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://localhost/agency/loguser", "LogUserServicePort"), LogUserServiceRemote.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LOGUSERSERVICE_EXCEPTION!= null) {
            throw LOGUSERSERVICE_EXCEPTION;
        }
        return LOGUSERSERVICE_WSDL_LOCATION;
    }

}
