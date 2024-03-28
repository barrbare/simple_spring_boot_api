package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.SearchActor;
import ge.ibsu.demo.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ActorService {
    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<SearchActor> getAllActorNames() {
        return actorRepository.findAllActorsNames();
    }
}
