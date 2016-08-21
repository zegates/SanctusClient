/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.factory;


import agency.persistance.controller.remote.LogUserController;
import agency.persistance.controller.remote.SupplierController;
import agency.persistance.controller.service.LogUserControllerService;
import localhost.agency.loguser.LogUserService;

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
//        ojc = new OrdersJpaController(EMFHandler.getEmf());
//        ijc = new ItemJpaController(EMFHandler.getEmf());
//        sjc=new SupplierJpaController(EMFHandler.getEmf());

        luc = new LogUserControllerService();

        ojc = new OrdersJpaController(EMFHandler.getEmf());
        ijc = new ItemJpaController(EMFHandler.getEmf());
        sjc = new SupplierJpaController(EMFHandler.getEmf());
    }

    public static ItemJpaController getItemJpaController() {
        return ijc;
    }

    public static ManufacturerJpaController getManufacturerJpaController() {
        return new ManufacturerJpaController(EMFHandler.getEmf());
    }

    public static ConstructionJpaController getConstructionJpaController() {
        return new ConstructionJpaController(EMFHandler.getEmf());
    }

    public static SupplyOrderDetailJpaController getSupplyOrderDetailJpaController() {
        return new SupplyOrderDetailJpaController(EMFHandler.getEmf());
    }

    public static SupplierJpaController getSupplierJpaController() {
        return sjc;
    }

    public static SupplyOrderJpaController getSupplyOrderJpaController() {
        return new SupplyOrderJpaController(EMFHandler.getEmf());
    }

    public static OrdersJpaController getOrdersJpaController() {
        return ojc;
    }

    public static LogSessionJpaController getSessionJpaController() {
        return new LogSessionJpaController(EMFHandler.getEmf());
    }

    public static LogUserController getLogUserJpaController() {
//        return new LogUserJpaController(EMFHandler.getEmf());
        return luc;
    }
}
