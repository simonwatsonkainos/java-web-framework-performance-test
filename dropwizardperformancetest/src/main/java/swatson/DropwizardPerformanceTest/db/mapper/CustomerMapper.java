package swatson.DropwizardPerformanceTest.db.mapper;

import swatson.DropwizardPerformanceTest.dto.Customer;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {

    private static final String CUSTOMER_ID_KEY = "customer_id";
    private static final String NAME_KEY = "name";
    private static final String ADDRESS_KEY = "address";
    private static final String EMAIL_KEY = "email";

    public CustomerMapper() { }

    public Customer map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Customer(rs.getLong(CUSTOMER_ID_KEY), rs.getString(NAME_KEY), rs.getString(ADDRESS_KEY), rs.getString(EMAIL_KEY));
    }
}
