package com.training.user.application.controller;

import com.training.user.application.dto.UserDTO;
import com.training.user.application.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

//    @PostMapping
//    public UUID purchaseItem(@RequestBody PurchaseDTO purchaseDTO) {
//        logger.info("Received request: POST /purchaseItem with params: ");
//        logger.info("Created new purchase with OrderID {}", newUser);
//
//    }

    @GetMapping
    public UUID getUserOrders(@RequestParam UUID userId) {
        logger.info("Received request: GET /getUserOrders");
        var orders = this.userService.getOrders(userId);
        logger.info("GET /getUserOrders returned {}", orders);
        return orders;
    }

    @PostMapping
    public UserDTO addNewUser(@RequestBody UserDTO userDTO) {
        logger.info("Received request: POST /addNewUser with params: ");
        var newUser = this.userService.addUser(userDTO);
        logger.info("Created new user {}", newUser);
        return newUser;
    }

    @GetMapping
    public List<UserDTO> getAll() {
        logger.info("Received request: GET /getAll");
        var users = this.userService.getAll();
        logger.info("GET /getAll returned {}", users);
        return users;
    }
}
