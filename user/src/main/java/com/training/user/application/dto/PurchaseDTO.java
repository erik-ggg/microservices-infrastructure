package com.training.user.application.dto;

import java.util.UUID;

public record PurchaseDTO(UUID userId, UUID itemId, int quantity) {
}
