package swatson.DropwizardPerformanceTest;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.flywaydb.core.Flyway;
import org.jdbi.v3.core.Jdbi;
import swatson.DropwizardPerformanceTest.db.dao.CustomerDAO;
import swatson.DropwizardPerformanceTest.resources.CustomerResource;

public class DropwizardPerformanceTest extends Application<DropwizardPerformanceTestConfiguration> {

    public static void main(String[] args) throws Exception {
        new DropwizardPerformanceTest().run(args);
    }

    @Override
    public void initialize(Bootstrap<DropwizardPerformanceTestConfiguration> bootstrap) {

        /* bootstrap.addBundle(new FlywayBundle<DropwizardPerformanceTestConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(DropwizardPerformanceTestConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }

            @Override
            public FlywayFactory getFlywayFactory(DropwizardPerformanceTestConfiguration configuration) {
                return configuration.getFlywayFactory();
            }
        }); */
    }

    @Override
    public void run(DropwizardPerformanceTestConfiguration configuration, Environment environment) {

        // Migrate DB schema on start-up
        DataSourceFactory dataSourceFactory = configuration.getDataSourceFactory();
        Flyway flyway = Flyway.configure().dataSource(dataSourceFactory.getUrl(), dataSourceFactory.getUser(), dataSourceFactory.getPassword()).load();
        flyway.migrate();

        // Initialise JDBI and DAO Objects
        final JdbiFactory jdbiFactory = new JdbiFactory();
        final Jdbi jdbi = jdbiFactory.build(environment, configuration.getDataSourceFactory(), "orders_performance_test");
        final CustomerDAO customerDao = jdbi.onDemand(CustomerDAO.class);

        // Register web resources
        environment.jersey().register(new CustomerResource(customerDao));
    }
}
