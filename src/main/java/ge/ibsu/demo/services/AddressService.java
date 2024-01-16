package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.AddAddress;
import ge.ibsu.demo.entities.Address;
import ge.ibsu.demo.entities.City;
import ge.ibsu.demo.repositories.AddressRepository;
import ge.ibsu.demo.repositories.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, CityRepository cityRepository) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
    }

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Transactional
    public Address getAddress(AddAddress addAddress) {
        Address address = addressRepository.findOneByAddress(addAddress.getAddress());
        if (address != null) {
            return address;
        }
        address = new Address();
        address.setAddress(addAddress.getAddress());
        address.setPostalCode(addAddress.getPostalCode());

        return addressRepository.save(address);
    }

    @Transactional
    public Address addAddressWithCity(Address address, Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new EntityNotFoundException("City not found"));
        address.setCity(city);
        return addressRepository.save(address);
    }
}
