package ge.ibsu.demo.services;

import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.repositories.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    public City addCity(City city) {
        return cityRepository.save(city);
    }

    public void removeCity(Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new EntityNotFoundException("City not found"));
        cityRepository.delete(city);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
