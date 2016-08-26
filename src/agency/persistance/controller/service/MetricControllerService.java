/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.service;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import agency.persistance.controller.remote.MetricController;
import com.zegates.sanctus.services.remote.Metric;
import java.sql.SQLException;
import java.util.List;
import localhost.agency.metric.MetricService;
import localhost.agency.metric.MetricServiceRemote;

/**
 *
 * @author sandaruwan
 */
public class MetricControllerService implements MetricController{
    
    private final MetricServiceRemote metricService;

    public MetricControllerService() {
        metricService = new MetricService().getMetricServicePort();
    }
    

    @Override
    public void create(Metric manufacturer) throws ClassNotFoundException, SQLException {
        metricService.createMetric(manufacturer);
    }

    @Override
    public void destroy(Long id) throws NonexistentEntityException {
//        metricService.
    }

    @Override
    public void edit(Metric manufacturer) throws NonexistentEntityException, Exception {
        metricService.edit(manufacturer);
    }

    @Override
    public Metric findMetric(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Metric> findMetricEntities() {
        return metricService.findMetrics().getItem();
    }

    @Override
    public List<Metric> findMetricEntities(int maxResults, int firstResult) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }

    @Override
    public Metric findMetricForName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMetricCount() {
        return metricService.getMetricCount();
    }
    
}
