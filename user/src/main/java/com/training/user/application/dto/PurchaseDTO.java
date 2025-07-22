package com.training.user.application.dto;

import java.util.UUID;

public record PurchaseDTO(Long userId, Long itemId, int quantity) {
}
