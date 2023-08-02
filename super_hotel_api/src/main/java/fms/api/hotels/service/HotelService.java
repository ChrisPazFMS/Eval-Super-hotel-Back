package fms.api.hotels.service;

import fms.api.hotels.dao.HotelRepository;
import fms.api.hotels.dao.CityRepository;
import fms.api.hotels.dao.BedroomRepository; // Import the Bedroom repository
import fms.api.hotels.entities.Hotel;
import fms.api.hotels.entities.City;
import fms.api.hotels.entities.Bedroom; // Import the Bedroom entity
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;
    private final BedroomRepository bedroomRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository, CityRepository cityRepository,
            BedroomRepository bedroomRepository) {
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
        this.bedroomRepository = bedroomRepository;
    }

    // Récupérer tous les hôtels
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // Hôtel par son ID
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    // Ajouter un hôtel
    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // Update les informations d'un hôtel existant
    public Hotel updateHotel(Long id, Hotel hotel) {
        // Si hôtel existe mettre à jour
        if (hotelRepository.existsById(id)) {
            hotel.setId(id);
            return hotelRepository.save(hotel);
        } else {
            return null; // Pas d'hôtel, pas de mise à jour
        }
    }

    // Supprimer un hôtel par son ID
    public boolean deleteHotel(Long id) {
        // Si hôtel existe supprimer
        if (hotelRepository.existsById(id)) {
            hotelRepository.deleteById(id);
            return true;
        } else {
            // Pas d'hôtel, pas de suppression.
            return false;
        }
    }

    // Récupére l'identifiant d'une ville par son nom
    public Long getCityIdByName(String cityName) {
        City city = cityRepository.findByName(cityName);
        if (city != null) {
            return city.getId();
        } else {
            return null; // La ville n'a pas été trouvée
        }
    }

    // Nombre total de chambres dans la ville
    public int getTotalNumberOfBedroomsInCity(Long cityId) {
        return hotelRepository.getTotalNumberOfBedroomsInCity(cityId);
    }

    // Nombre total de chambres disponibles dans la ville
    public int getTotalNumberOfAvailableBedroomsInCity(Long cityId) {
        return hotelRepository.getTotalNumberOfAvailableBedroomsInCity(cityId);
    }

    // Ajoute une liste de chambres à un hôtel spécifique
    public List<Bedroom> addBedroomsToHotel(Long hotelId, List<Bedroom> bedrooms) {

        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        if (hotel != null) {
            for (Bedroom bedroom : bedrooms) {
                // Associer chaque chambre à l'hôtel
                bedroom.setHotel(hotel);
            }
            // Enregistrer les chambres dans la base de données
            return bedroomRepository.saveAll(bedrooms);
        } else {

            return new ArrayList<>();
        }
    }

}
