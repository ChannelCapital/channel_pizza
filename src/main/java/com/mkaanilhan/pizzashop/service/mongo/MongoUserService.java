package com.mkaanilhan.pizzashop.service.mongo;

import com.mkaanilhan.pizzashop.dao.PizzaRepository;
import com.mkaanilhan.pizzashop.dao.UserRepository;
import com.mkaanilhan.pizzashop.entity.*;
import com.mkaanilhan.pizzashop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author Murat Kaan Ihan
 * @since 15.05.2021
 */

@Service
public class MongoUserService implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PizzaRepository pizzaRepository;

    public MongoUserService(UserRepository userRepository, PizzaRepository pizzaRepository) {
        this.userRepository = userRepository;
        this.pizzaRepository = pizzaRepository;
    }

    /**
     * It enables to getting all user from DB
     * @return List of Users from DB as List type.
     */

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();

    }

    /**
     * It enables to add a user to DB
     * @param user type is used to insert item to the DB
     */

    @Override
    public void addUser(User user) {

        userRepository.insert(user);

    }

    /*
     *It enables to add a favorite pizza for a specific user. Not allowed same pizza.
     */

    /**
     * It enables to add a favorite pizza for a specific user. Not allowed same pizza.
     * @param pizzaResponse has UsedID and pizzaID. those used for getting User and Pizza type
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


    /**
     *  It enables to get all favorites pizza a specific user.
     * @param userID is used to get User from DB
     * @return List of Pizza item. List type
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

    /**
     * Login Features. Check both email and password attribute.
     * @param login has userMail and userPass attributes to get User validation.
     * @return User type to get all user information.
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

    /**
     * It enables to get basket items for a specific user using userID.
     * @param userID is used for finding user from databases.
     * @return List type list of pizza for specific user.
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

    /**
     * It enables to add pizza to the basket
     * @param pizzaResponse has UsedID and pizzaID. those used for getting User and Pizza type
     */

    @Override
    public void addBasket(PizzaResponse pizzaResponse) {

        Optional<User> user = userRepository.findById(pizzaResponse.getUserID());
        Optional<Pizza> pizza = pizzaRepository.findById(pizzaResponse.getPizzaID());

        user.get().getBasket().add(pizza.get());

        userRepository.save(user.get());

    }

    /**
     * It enables to update user credentials.
     * @param user is to get user info for updating issue.
     */

    @Override
    public void updateUser(User user) {

        Optional<User> newUser = userRepository.findById(user.getId());

        newUser.get().setAddress(user.getAddress());
        newUser.get().setEmail(user.getEmail());
        newUser.get().setPassword(user.getPassword());

        userRepository.save(newUser.get());

    }

    /**
     * It enables to remove basket item for a specific user.
     * @param pizzaResponse has two attibutes userID and pizzaID to get user and pizza informations.
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

    /**
     * It enables to remove favorite item for a specific user.
     * @param pizzaResponse has two attibutes userID and pizzaID to get user and pizza informations.
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
