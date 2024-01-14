package ge.ibsu.demo.repositories;

import ge.ibsu.demo.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findOneByAddress(String address);

}