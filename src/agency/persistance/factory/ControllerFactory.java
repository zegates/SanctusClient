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
import agency.persistance.controller.service.LogUserControllerService;
import agency.persistance.controller.service.OrdersControllerService;

/**
 *
 * @author Sandaruwan
 */
public class ControllerFactory {

    private static OrdersController ojc;
    private static ItemController ijc;
    private static SupplierController sjc;
    private static LogUserController luc;

    static {
//        ojc = new OrdersController(EMFHandler.getEmf());
//        ijc = new ItemController(EMFHandler.getEmf());
//        sjc=new SupplierController(EMFHandler.getEmf());

        luc = new LogUserControllerService();

        ojc = new OrdersControllerService();
        ijc = new ItemController(EMFHandler.getEmf());
        sjc = new SupplierController(EMFHandler.getEmf());
    }

    public static ItemController getItemController() {
        return ijc;
    }

    public static ManufacturerController getManufacturerController() {
        return new ManufacturerController(EMFHandler.getEmf());
    }

    public static ConstructionController getConstructionController() {
        return new ConstructionController(EMFHandler.getEmf());
    }

    public static SupplyOrderDetailController getSupplyOrderDetailController() {
        return new SupplyOrderDetailController(EMFHandler.getEmf());
    }

    public static SupplierController getSupplierController() {
        return sjc;
    }

    public static SupplyOrderController getSupplyOrderController() {
        return new SupplyOrderController(EMFHandler.getEmf());
    }

    public static OrdersController getOrdersController() {
        return ojc;
    }

    public static LogSessionController getSessionController() {
        return new LogSessionController(EMFHandler.getEmf());
    }

    public static LogUserController getLogUserController() {
//        return new LogUserController(EMFHandler.getEmf());
        return luc;
    }
}
