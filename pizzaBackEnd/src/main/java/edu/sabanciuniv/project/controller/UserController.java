package edu.sabanciuniv.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sabanciuniv.project.dto.AddressDto;
import edu.sabanciuniv.project.model.Address;
import edu.sabanciuniv.project.model.User;
import edu.sabanciuniv.project.repository.AddressRepository;
import edu.sabanciuniv.project.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	AddressRepository addressRepo;

	@Autowired
	UserRepository userRepo;

	@PostMapping("/address")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Address> createAddress(@RequestBody AddressDto address) {

		try {
			Optional<User> optionalUser = userRepo.findById(address.getUserId());

			Address _address = addressRepo.save(
					new Address(address.getCountry(), address.getCity(), address.getPostcode(), optionalUser.get()));

			return new ResponseEntity<>(_address, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/address")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Address> getAddressByUserId(@RequestParam("userId") String userId) {

		Optional<Address> optionalAddress = addressRepo.findByUserId(userId);

		if (optionalAddress.isPresent()) {

			return new ResponseEntity<>(optionalAddress.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/address/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Address> updateAddress(@PathVariable("id") String id, @RequestBody AddressDto aDto) {

		Optional<Address> oAddress = addressRepo.findById(id);

		if (oAddress.isPresent()) {

			Address _address = oAddress.get();

			_address.setCountry(aDto.getCountry());

			_address.setCountry(aDto.getCountry());

			_address.setCity(aDto.getCity());

			_address.setPostcode(aDto.getPostcode());

			return new ResponseEntity<>(addressRepo.save(_address), HttpStatus.OK);

		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/address/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteAddress(@PathVariable("id") String id) {

		try {

			addressRepo.deleteById(id);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
