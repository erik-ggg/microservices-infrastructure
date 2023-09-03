package com.training.user.application.service;

import com.training.user.application.dto.UserDTO;
import com.training.user.application.mapper.UserMapper;
import com.training.user.application.usecases.UserUseCase;
import com.training.user.domain.model.User;
import com.training.user.infrastructure.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserUseCase {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

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
