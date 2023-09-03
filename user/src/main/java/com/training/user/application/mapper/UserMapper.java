package com.training.user.application.mapper;

import com.training.user.application.dto.UserDTO;
import com.training.user.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
}
