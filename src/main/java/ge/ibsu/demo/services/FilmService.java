package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.SearchFilm;
import ge.ibsu.demo.entities.Film;
import ge.ibsu.demo.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;

    public Page<SearchFilm> searchFilms(String title, String description, Integer releaseYear, String language, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return filmRepository.searchFilms(
                title, description, releaseYear, language, pageable);
    }
}
