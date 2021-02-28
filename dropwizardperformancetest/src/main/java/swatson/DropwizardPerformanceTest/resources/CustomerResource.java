package swatson.DropwizardPerformanceTest.resources;

import com.codahale.metrics.annotation.Timed;
import swatson.DropwizardPerformanceTest.db.dao.CustomerDAO;
import swatson.DropwizardPerformanceTest.dto.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    CustomerDAO customerDao;

    public CustomerResource(CustomerDAO customerDao) {
        this.customerDao = customerDao;
    }

    @POST
    @Timed
    public Response createCustomer(swatson.DropwizardPerformanceTest.dto.Customer customer) {
        return Response.status(Response.Status.CREATED)
                .entity(customerDao.insertCustomer(customer.getName(), customer.getAddress(), customer.getEmail())).build();
    }

    @GET
    @Timed
    @Path("/{customerId}")
    public Response getCustomer(@PathParam("customerId") long customerId) {

        Customer customer = customerDao.findCustomerById(customerId);

        if(null == customer) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok().entity(customer).build();
        }
    }
}

