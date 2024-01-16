package ge.ibsu.demo.controllers;

import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<City> addCity(@RequestBody City city) {
        City savedCity = cityService.addCity(city);
        return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}",  method = RequestMethod.DELETE, produces = {"application/json"})
    public ResponseEntity<Void> removeCity(@PathVariable Long cityId) {
        cityService.removeCity(cityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}