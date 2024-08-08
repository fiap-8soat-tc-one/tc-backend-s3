package com.fiap.tc.core.domain.model.enums;

import com.fiap.tc.adapters.driven.infrastructure.output.validator.order.OrderStatusValidator;
import com.fiap.tc.adapters.driven.infrastructure.output.validator.order.concrete.*;
import com.fiap.tc.adapters.repository.output.validator.order.*;
import com.fiap.tc.adapters.repository.output.validator.order.concrete.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    RECEIVED("order received", new ReceivedOrderStatusValidator()),
    PENDING("pending payment order", new PendingOrderStatusValidator()),
    PREPARING("order being prepared", new PreparingOrderStatusValidator()),
    READY("order ready", new ReadyOrderStatusValidator()),
    FINISHED("order finished", new FinishedOrderStatusValidator()),
    CANCELED("order canceled", new CanceledOrderStatusValidator());

    private final String description;
    private final OrderStatusValidator validator;
}
