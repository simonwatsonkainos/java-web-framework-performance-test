package swatson.SpringBootPerformanceTest.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import swatson.SpringBootPerformanceTest.persistence.dao.Customer;

@Component
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
