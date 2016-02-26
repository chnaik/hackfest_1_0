package com.flipkart.ekl.hackfest;

import com.flipkart.ekl.hackfest.resource.DashboardResource;
import com.flipkart.ekl.hackfest.resource.EmployeeResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.util.Map;

/**
 * Created by chaitanya.naik on 25/02/16.
 */
public class HackfestApplication extends Application<HackfestConfiguration> {
    public static void main(String[] args) throws Exception {
        new HackfestApplication().run(args);
    }
//    private final HibernateBundle<HackfestConfiguration> hibernate =
//            new HibernateBundle<HackfestConfiguration>(GroupMetadata.class, EntityMetadata.class, EntitySummary.class) {
//                @Override
//                public DataSourceFactory getDataSourceFactory(HackfestConfiguration configuration) {
//                    return configuration.getDataSourceFactory();
//                }
//            };

    @Override
    public String getName() {
        return "hackfest";
    }

    @Override
    public void initialize(Bootstrap<HackfestConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<HackfestConfiguration>() {
            @Override
            public Map<String, Map<String, String>> getViewConfiguration(HackfestConfiguration config) {
                return config.getViewRendererConfiguration();
            }
        });
        bootstrap.addBundle(new AssetsBundle("/web/app/", "/web/app/", "web/app/main.ftl", "static"));
        //bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(HackfestConfiguration configuration,
                    Environment environment) {

        final DashboardResource dashboardResource = new DashboardResource();
        final EmployeeResource employeeResource = new EmployeeResource();
        environment.jersey().register(dashboardResource);
        environment.jersey().register(employeeResource);

    }

}
