package ge.ibsu.demo.repositories;

import ge.ibsu.demo.dto.CustomerInfo;
import ge.ibsu.demo.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("From Customer where active = :active and " +
            "concat(firstName, concat(' ', lastName)) like :searchValue")
    List<Customer> search(@Param("active") Integer active, @Param("searchValue") String searchValue);

    @Query(value = "select * from customer where active = :active and " +
            "concat(first_name, concat(' ', last_name)) like :searchValue", nativeQuery = true)
    List<Customer> searchWithNative(@Param("active") Integer active, @Param("searchValue") String searchValue);

    @Query("select new ge.ibsu.demo.dto.CustomerInfo(c.firstName, c.lastName, c.address, c.city, c.country) from Customer c")
    Page<CustomerInfo> findCustomer(Pageable pageable);

}