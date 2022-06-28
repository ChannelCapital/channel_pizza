package edu.sabanciuniv.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sabanciuniv.project.dto.AddToCartDto;
import edu.sabanciuniv.project.dto.CartDto;
import edu.sabanciuniv.project.dto.CartItemDto;
import edu.sabanciuniv.project.model.Cart;
import edu.sabanciuniv.project.model.Product;
import edu.sabanciuniv.project.model.User;
import edu.sabanciuniv.project.repository.CartRepository;
import edu.sabanciuniv.project.repository.ProductRepository;

@Service
@Transactional
public class CartService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CartRepository cartRepository;

	public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
		
		Cart cart = new Cart(addToCartDto.getQuantity(), user, product);
		
		cartRepository.save(cart);
	}

	public CartDto listCartItems(User user) {
		
		List<Cart> cartList = cartRepository.findAllByUser(user);
		List<CartItemDto> cartItems = new ArrayList<>();
		
		for (Cart cart : cartList) {
			
			CartItemDto cartItemDto = getDtoFromCart(cart);
			cartItems.add(cartItemDto);
			
		}
		
		double totalCost = 0;
		
		for (CartItemDto cartItemDto : cartItems) {
			
			totalCost += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
		}
		
		return new CartDto(cartItems, totalCost);
	}

	public static CartItemDto getDtoFromCart(Cart cart) {
		
		return new CartItemDto(cart);
	}

	public void updateCartItem(AddToCartDto cartDto, User user, Product product) throws NotFoundException{
		
		Optional<Cart> optionalCart = cartRepository.findById(cartDto.getId());
		
		if(optionalCart.isPresent()) {
			
			Cart cart = optionalCart.get();
			
			cart.setQuantity(cartDto.getQuantity());
			
			cartRepository.save(cart);
			
		}else {
			
			throw new NotFoundException();
		}
	}

	public void deleteCartItem(String id, String userId) throws NotFoundException {
		
		if (!cartRepository.existsById(id)) {
			
			throw new NotFoundException();
			
		}else {
			
			cartRepository.deleteById(id);
		}
	}

	
	public void deleteCartItems(String userId) {
		
		cartRepository.deleteAll();
	}

	public void deleteUserCartItems(User user) {
		
		cartRepository.deleteByUser(user);
	}

}
