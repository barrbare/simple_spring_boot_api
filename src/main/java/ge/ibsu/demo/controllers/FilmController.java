package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.SearchFilm;
import ge.ibsu.demo.entities.Film;
import ge.ibsu.demo.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/films")
public class FilmController {
    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<SearchFilm>> searchFilms(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer releaseYear,
            @RequestParam(required = false) String language,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<SearchFilm> films = filmService.searchFilms(title, description, releaseYear, language, page, size);
        return ResponseEntity.ok(films);
    }
}
