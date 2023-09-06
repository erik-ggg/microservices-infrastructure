package com.training.user.application.usecases;

import com.training.user.application.dto.PurchaseDTO;
import com.training.user.application.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserUseCase {

    UUID getOrders(UUID userID);

    UUID purchaseItem(PurchaseDTO purchaseDTO);

    UserDTO addUser(UserDTO userDTO);

    List<UserDTO> getAll();
}
