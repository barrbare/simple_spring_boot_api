package ge.ibsu.demo.repositories;

import ge.ibsu.demo.dto.SearchActor;
import ge.ibsu.demo.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ActorRepository extends JpaRepository<Actor, Long> {
    @Query("SELECT new ge.ibsu.demo.dto.ActorNameDTO(a.firstName, a.lastName) FROM Actor a")
    List<SearchActor> findAllActorsNames();

}
