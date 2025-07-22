package com.training.user.application.service;

import com.training.user.application.dto.PurchaseDTO;
import com.training.user.application.dto.UserDTO;
import com.training.user.application.dto.order.OrderDTO;
import com.training.user.application.mapper.UserMapper;
import com.training.user.application.usecases.UserUseCase;
import com.training.user.domain.model.User;
import com.training.user.infrastructure.configuration.OrderRestTemplate;
import com.training.user.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserUseCase {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final OrderRestTemplate orderRestTemplate;

    @Override
    public OrderDTO getOrders(Long userID) {

        return orderRestTemplate.callUserOrdersRequest(userID);
    }

    @Override
    public void purchaseItem(PurchaseDTO purchaseDTO) {

        // Call the order service to create a purchase
        orderRestTemplate.createPurchase(purchaseDTO);
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        var newUser = new User(userDTO.surname(), userDTO.lastname(), userDTO.age(), userDTO.email(), userDTO.address());
        return userMapper.toDTO(this.userRepository.save(newUser));
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }
}
