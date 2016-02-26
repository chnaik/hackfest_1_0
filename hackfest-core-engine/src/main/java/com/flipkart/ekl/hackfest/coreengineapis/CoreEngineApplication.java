package com.flipkart.ekl.hackfest.coreengineapis;

import com.flipkart.ekl.hackfest.coreengineapis.core.Employee;
import com.flipkart.ekl.hackfest.coreengineapis.core.EmployeeScore;
import com.flipkart.ekl.hackfest.coreengineapis.core.LMSScore;
import com.flipkart.ekl.hackfest.coreengineapis.core.ProductivityScoreHistory;
import com.flipkart.ekl.hackfest.coreengineapis.dao.EmployeeDAO;
import com.flipkart.ekl.hackfest.coreengineapis.dao.EmployeeScoreDAO;
import com.flipkart.ekl.hackfest.coreengineapis.dao.LMSScoreDAO;
import com.flipkart.ekl.hackfest.coreengineapis.resource.EmployeeScoreResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by chaitanya.naik on 26/02/16.
 */
public class CoreEngineApplication extends Application<CoreEngineConfiguration> {
    public static void main(String[] args) throws Exception {
        new CoreEngineApplication().run(args);
    }
    private final HibernateBundle<CoreEngineConfiguration> hibernate =
            new HibernateBundle<CoreEngineConfiguration>(Employee.class, LMSScore.class, EmployeeScore.class, ProductivityScoreHistory.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(CoreEngineConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    @Override
    public String getName() {
        return "core-engine";
    }


    @Override
    public void initialize(Bootstrap<CoreEngineConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(CoreEngineConfiguration configuration,
                    Environment environment) {

        EmployeeDAO employeeDAO = new EmployeeDAO(hibernate.getSessionFactory());
        EmployeeScoreDAO employeeScoreDAO = new EmployeeScoreDAO(hibernate.getSessionFactory());
        LMSScoreDAO lmsScoreDAO = new LMSScoreDAO(hibernate.getSessionFactory());
        final EmployeeScoreResource employeeScoreResource = new EmployeeScoreResource(lmsScoreDAO, employeeDAO, employeeScoreDAO);
        environment.jersey().register(employeeScoreResource);

    }
    
}
