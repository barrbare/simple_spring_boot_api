package ge.ibsu.demo.repositories;

import ge.ibsu.demo.dto.SearchFilm;
import ge.ibsu.demo.entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query("SELECT f FROM Film f " +
            "WHERE (:title IS NULL OR f.title LIKE %:title%) " +
            "AND (:description IS NULL OR f.description LIKE %:description%) " +
            "AND (:releaseYear IS NULL OR f.releaseYear = :releaseYear) " +
            "AND (:language IS NULL OR f.language LIKE %:language%)")
    Page<SearchFilm> searchFilms(@Param("title") String title, @Param("description") String description, @Param("releaseYear") Integer releaseYear, @Param("language") String language, Pageable pageable);
}
