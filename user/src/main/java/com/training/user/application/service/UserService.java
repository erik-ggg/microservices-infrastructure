package com.training.user.application.service;

import com.training.user.application.dto.PurchaseDTO;
import com.training.user.application.dto.UserDTO;
import com.training.user.application.mapper.UserMapper;
import com.training.user.application.usecases.UserUseCase;
import com.training.user.domain.model.User;
import com.training.user.infrastructure.adapter.OrderServiceRestAdapter;
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

    private final OrderServiceRestAdapter orderServiceRestAdapter;

    @Override
    public UUID getOrders(UUID userID) {
        UUID transactionId = UUID.randomUUID();
        orderServiceRestAdapter.makeUserOrdersRequest(userID, transactionId);
        return null;
    }

    @Override
    public UUID purchaseItem(PurchaseDTO purchaseDTO) {
        return null;
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
