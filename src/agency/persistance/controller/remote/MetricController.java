/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agency.persistance.controller.remote;

import agency.persistance.controller.exceptions.NonexistentEntityException;
import com.zegates.sanctus.services.remote.Metric;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sandaruwan
 */
public interface MetricController {
    void create(Metric manufacturer) throws ClassNotFoundException, SQLException;

    void destroy(Long id) throws NonexistentEntityException;

    void edit(Metric manufacturer) throws NonexistentEntityException, Exception;

    Metric findMetric(Long id);

    List<Metric> findMetricEntities();

    List<Metric> findMetricEntities(int maxResults, int firstResult);

    Metric findMetricForName(String name);

    int getMetricCount();
}
