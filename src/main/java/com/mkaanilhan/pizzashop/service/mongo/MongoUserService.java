package com.mkaanilhan.pizzashop.service.mongo;

import com.mkaanilhan.pizzashop.dao.PizzaRepository;
import com.mkaanilhan.pizzashop.dao.UserRepository;
import com.mkaanilhan.pizzashop.entity.*;
import com.mkaanilhan.pizzashop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MongoUserService implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PizzaRepository pizzaRepository;

    /*
     *It enables to getting all user from DB
     */

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();

    }

    /*
     *It enables to add a user to DB
     */

    @Override
    public void addUser(User user) {

        userRepository.insert(user);

    }

    /*
     *It enables to add a favorite pizza for a specific user. Not allowed same pizza.
     */

    @Override
    public void addFavorite(PizzaResponse pizzaResponse) {

        Optional<User> user = userRepository.findById(pizzaResponse.getUserID());
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaResponse.getPizzaID());

        if(user.get().getFavorites().size() == 0){

            user.get().getFavorites().add(pizza.get());

        }

        else{

            for (int i = 0; i <user.get().getFavorites().size();i++){

                if (!user.get().getFavorites().get(i).getId().equals(pizzaResponse.getPizzaID())){

                    user.get().getFavorites().add(pizza.get());

                }

            }

        }

        userRepository.save(user.get());

    }

    /*
     *It enables to get all favorites pizza a specific user
     */

    @Override
    public List<Pizza> getFavoriteByUser(String userID) {

        List<User> userList = userRepository.findAll();
        List<Pizza> pizzaList = null;

        for (int i = 0; i < userList.size(); i++) {

            if (userList.get(i).getId().equals(userID)) {

                pizzaList = userList.get(i).getFavorites();

            }

        }

        return pizzaList;
    }

    /*
     *Login Features. Check both email and password attribute.
     */

    @Override
    public User signIn(UserLogin login) {

        List<User> userList = userRepository.findAll();
        User user = null;

        for (User u : userList) {

            if (login.getEmail().equals(u.getEmail()) && login.getPassword().equals(u.getPassword())) {

                user = u;

            }

        }

        return user;

    }

    /*
     *It enables to get basket items for a specific user using userID
     */

    @Override
    public List<Pizza> getBasketByUser(String userID) {

        List<User> userList = userRepository.findAll();
        List<Pizza> pizzaList = null;

        for (int i = 0; i < userList.size(); i++) {

            if (userList.get(i).getId().equals(userID)) {

                pizzaList = userList.get(i).getBasket();

            }

        }

        return pizzaList;

    }

    /*
     *It enables to add pizza to the basket
     */

    @Override
    public void addBasket(PizzaResponse pizzaResponse) {

        Optional<User> user = userRepository.findById(pizzaResponse.getUserID());
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaResponse.getPizzaID());

        user.get().getBasket().add(pizza.get());

        userRepository.save(user.get());

    }

    /*
     *It enables to update user credentials.
     */

    @Override
    public void updateUser(User user) {

        Optional<User> newUser = userRepository.findById(user.getId());

        newUser.get().setAddress(user.getAddress());
        newUser.get().setEmail(user.getEmail());
        newUser.get().setPassword(user.getPassword());

        userRepository.save(newUser.get());

    }

    /*
     *It enables to remove basket item for a specific user.
     */

    @Override
    public void removeBasketItem(PizzaResponse pizzaResponse) {

        Optional<User> user = userRepository.findById(pizzaResponse.getUserID());
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaResponse.getPizzaID());

                for (int i = 0; i <user.get().getBasket().size();i++){

                    if (user.get().getBasket().get(i).getId().equals(pizza.get().getId())){

                        user.get().getBasket().remove(i);
                        break;
                    }

                }

        userRepository.save(user.get());

    }

    /*
     *It enables to remove favorite item for a specific user.
     */

    @Override
    public void removeFavoriteItem(PizzaResponse pizzaResponse) {

        Optional<User> user = userRepository.findById(pizzaResponse.getUserID());
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaResponse.getPizzaID());

        for (int i = 0; i <user.get().getFavorites().size();i++){

            if (user.get().getFavorites().get(i).getId().equals(pizza.get().getId())){

                user.get().getFavorites().remove(i);
                break;
            }

        }

        userRepository.save(user.get());

    }

}
