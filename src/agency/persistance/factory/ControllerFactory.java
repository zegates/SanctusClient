/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.factory;


import agency.persistance.controller.remote.ItemController;
import agency.persistance.controller.remote.LogSessionController;
import agency.persistance.controller.remote.LogUserController;
import agency.persistance.controller.remote.ManufacturerController;
import agency.persistance.controller.remote.OrdersController;
import agency.persistance.controller.remote.SupplierController;
import agency.persistance.controller.remote.SupplyOrderController;
import agency.persistance.controller.remote.SupplyOrderDetailController;
import agency.persistance.controller.service.ItemControllerService;
import agency.persistance.controller.service.LogSessionControllerService;
import agency.persistance.controller.service.LogUserControllerService;
import agency.persistance.controller.service.ManufacturerControllerService;
import agency.persistance.controller.service.OrdersControllerService;
import agency.persistance.controller.service.SupplierControllerService;
import agency.persistance.controller.service.SupplyOrderControllerService;
import agency.persistance.controller.service.SupplyOrderDetailControllerService;
import com.zegates.sanctus.services.remote.SupplyOrder;

/**
 *
 * @author Sandaruwan
 */
public class ControllerFactory {

    private static OrdersController ojc;
    private static ItemController ijc;
    private static SupplierController sjc;
    private static LogUserController luc;
    private static SupplyOrderController soc;
    private static LogSessionController lsc;

    static {
//        ojc = new OrdersController(EMFHandler.getEmf());
//        ijc = new ItemController(EMFHandler.getEmf());
//        sjc=new SupplierController(EMFHandler.getEmf());

        luc = new LogUserControllerService();

        ojc = new OrdersControllerService();
        ijc = new ItemControllerService();
        sjc = new SupplierControllerService();
        
        soc = new SupplyOrderControllerService();
        lsc = new LogSessionControllerService();
    }

    public static ItemController getItemController() {
        return ijc;
    }

    public static ManufacturerController getManufacturerController() {
        return new ManufacturerControllerService();
    }

    public static SupplyOrderDetailController getSupplyOrderDetailController() {
        return new SupplyOrderDetailControllerService();
    }

    public static SupplierController getSupplierController() {
        return sjc;
    }

    public static SupplyOrderController getSupplyOrderController() {
        return soc;
    }

    public static OrdersController getOrdersController() {
        return ojc;
    }

    public static LogSessionController getSessionController() {
        return lsc;
    }

    public static LogUserController getLogUserController() {
//        return new LogUserController(EMFHandler.getEmf());
        return luc;
    }
}
