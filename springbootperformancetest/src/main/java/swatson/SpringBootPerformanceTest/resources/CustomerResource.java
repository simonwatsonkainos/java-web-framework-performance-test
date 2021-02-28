package swatson.SpringBootPerformanceTest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swatson.SpringBootPerformanceTest.persistence.CustomerRepository;
import swatson.SpringBootPerformanceTest.persistence.dao.Customer;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerResource {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<Long> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerRepository.save(customer).getCustomerId());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("customerId") long customerId) {
        Optional<Customer> returnedCustomer = customerRepository.findById(customerId);

        if(returnedCustomer.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(returnedCustomer.get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
