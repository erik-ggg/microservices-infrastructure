package com.training.user.application.usecases;

import com.training.user.application.dto.UserDTO;

import java.util.List;

public interface UserUseCase {

    UserDTO addUser(UserDTO userDTO);

    List<UserDTO> getAll();
}
