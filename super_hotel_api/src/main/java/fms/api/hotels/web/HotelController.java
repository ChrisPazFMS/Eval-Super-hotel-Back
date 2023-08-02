package fms.api.hotels.web;

import fms.api.hotels.dao.CityRepository;
import fms.api.hotels.entities.City;
import fms.api.hotels.entities.Hotel;
import fms.api.hotels.entities.Bedroom;
import fms.api.hotels.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;
    private final CityRepository cityRepository;

    @Autowired
    public HotelController(HotelService hotelService, CityRepository cityRepository) {
        this.hotelService = hotelService;
        this.cityRepository = cityRepository;
    }

    // Récupére tous les hôtels teste ok !!
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    // Récupérer un hôtel par son ID teste ok !!
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        if (hotel != null) {
            return new ResponseEntity<>(hotel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Ajouter un nouvel hôtel teste ok !!
    @PostMapping
    public ResponseEntity<Hotel> addHotelWithCityCheck(@RequestBody Hotel hotel) {
        String cityName = hotel.getCity().getName();
        Long cityId = hotelService.getCityIdByName(cityName);

        if (cityId == null) {
            // Si pas ville enregistre.
            City newCity = new City();
            newCity.setName(cityName);
            City savedCity = cityRepository.save(newCity);

            // Utilise le nouvel ID de la ville pour l'hôtel
            hotel.getCity().setId(savedCity.getId());
        } else {
            // Utilise l'ID existant de la ville pour l'hôtel
            hotel.getCity().setId(cityId);
        }

        // Enregistre l'hôtel et récupere l'ID
        Hotel newHotel = hotelService.addHotel(hotel);

        // Vérifiez que l'enregistrement de l'hôtel s'est bien passé
        if (newHotel != null && newHotel.getId() != null) {
            // Ajoute les chambres
            if (newHotel.getBedrooms() != null) {
                List<Bedroom> addedBedrooms = hotelService.addBedroomsToHotel(newHotel.getId(), newHotel.getBedrooms());
                newHotel.setBedrooms(addedBedrooms);
            }

            return new ResponseEntity<>(newHotel, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update les informations d'un hôtel existant teste ok !!
    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        Hotel updatedHotel = hotelService.updateHotel(id, hotel);
        if (updatedHotel != null) {
            return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Supprime l'hôtel teste ok !!
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        boolean deleted = hotelService.deleteHotel(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Total de chambres dans la ville teste ok !!
    @GetMapping("/cities/{cityId}/total-bedrooms")
    public ResponseEntity<Integer> getTotalNumberOfBedroomsInCity(@PathVariable Long cityId) {
        int totalBedrooms = hotelService.getTotalNumberOfBedroomsInCity(cityId);
        return new ResponseEntity<>(totalBedrooms, HttpStatus.OK);
    }

    // Total de place disponibles dans la ville par lit teste ok !!
    @GetMapping("/cities/{cityId}/available-bedrooms")
    public ResponseEntity<Integer> getTotalNumberOfAvailableBedroomsInCity(@PathVariable Long cityId) {
        int totalAvailableBedrooms = hotelService.getTotalNumberOfAvailableBedroomsInCity(cityId);
        return new ResponseEntity<>(totalAvailableBedrooms, HttpStatus.OK);
    }
}
