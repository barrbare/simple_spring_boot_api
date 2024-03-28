package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.SearchActor;
import ge.ibsu.demo.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class ActorController {
    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/names")
    public ResponseEntity<List<SearchActor>> getActorNames() {
        List<SearchActor> actorNames = actorService.getAllActorNames();
        return ResponseEntity.ok(actorNames);
    }
}
