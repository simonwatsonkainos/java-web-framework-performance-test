package swatson.DropwizardPerformanceTest.db.dao;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import swatson.DropwizardPerformanceTest.db.mapper.CustomerMapper;
import swatson.DropwizardPerformanceTest.dto.Customer;

public interface CustomerDAO  {

    @SqlUpdate("insert into customer (name, address, email) values (:name, :address, :email)")
    @GetGeneratedKeys
    long insertCustomer(@Bind("name") String name, @Bind("address") String address, @Bind("email") String email);

    @SqlQuery("select customer_id, name, address, email from customer where customer_id = :id")
    @RegisterRowMapper(CustomerMapper.class)
    Customer findCustomerById(@Bind("id") long id);
}
