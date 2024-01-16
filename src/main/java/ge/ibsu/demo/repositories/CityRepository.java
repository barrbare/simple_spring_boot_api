package ge.ibsu.demo.repositories;

import ge.ibsu.demo.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

}
