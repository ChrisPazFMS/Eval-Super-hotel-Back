package fms.api.hotels.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fms.api.hotels.dao.CityRepository;
import fms.api.hotels.entities.City;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    // Ajoutez d'autres méthodes de service pour les opérations liées aux villes si
    // nécessaire
}
