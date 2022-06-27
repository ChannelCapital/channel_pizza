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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.sabanciuniv.project.dto.AddToCartDto;
import edu.sabanciuniv.project.dto.CartDto;
import edu.sabanciuniv.project.dto.CartItemDto;
import edu.sabanciuniv.project.model.Product;
import edu.sabanciuniv.project.model.User;
import edu.sabanciuniv.project.repository.ProductRepository;
import edu.sabanciuniv.project.repository.UserRepository;
import edu.sabanciuniv.project.service.CartService;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProductRepository productRepo;

	@PostMapping("/add")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<AddToCartDto> addToCart(@RequestBody AddToCartDto addToCartDto,

			@RequestParam("userId") String userId) {

		try {
			Optional<User> optionalUser = userRepo.findById(userId);

			Optional<Product> optionalProduct = productRepo.findById(addToCartDto.getProductId());
			
			/* TODO updating same pizza quantity
			for (CartItemDto cartItemDto : cartService.listCartItems(optionalUser.get()).getCartItems()) {
				
				if(addToCartDto.getProductId().equals(cartItemDto.getProduct().getId())) {
					
					int quantity=0;
					quantity=addToCartDto.getQuantity() + cartItemDto.getQuantity();
					addToCartDto.setQuantity(quantity);
					cartService.addToCart(addToCartDto, optionalProduct.get(), optionalUser.get());
					break;
					
				}else {}	
			}
			*/
			
			cartService.addToCart(addToCartDto, optionalProduct.get(), optionalUser.get());
			
			return new ResponseEntity<>(addToCartDto, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<CartDto> getCartItems(@RequestParam("userId") String userId) {
		try {
			Optional<User> optionalUser = userRepo.findById(userId);

			CartDto cartDto = cartService.listCartItems(optionalUser.get());

			return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<CartItemDto> getCartItemById(@PathVariable("id") String id,
			@RequestParam("userId") String userId) {

		Optional<User> optionalUser = userRepo.findById(userId);

		CartDto cartDto = cartService.listCartItems(optionalUser.get());

		for (CartItemDto cartItemDto : cartDto.getCartItems()) {

			if (cartItemDto.getId().equals(id)) {

				return new ResponseEntity<CartItemDto>(cartItemDto, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/delete/{cartItemId}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> deleteCartItem(@PathVariable("cartItemId") String cartItemId,
			@RequestParam("userId") String userId) {

		try {
			cartService.deleteCartItem(cartItemId, userId);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/deleteAll")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<HttpStatus> deleteAll(@RequestParam("userId") String userId) {

		try {
			cartService.deleteCartItems(userId);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
