package com.fiap.tc.core.usecase.order;

import com.fiap.tc.core.domain.model.enums.OrderStatus;
import com.fiap.tc.core.port.out.order.ListOrdersReadyPreparingOutputPort;
import com.fiap.tc.util.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ListOrdersReadyPreparingUseCaseTest extends BaseTest {

    @Mock
    private ListOrdersReadyPreparingOutputPort listOrdersReadyPreparingOutputPort;

    @InjectMocks
    private ListOrdersReadyPreparingUseCase listOrdersReadyPreparingUseCase;

    @Test
    public void listOrdersTest() {
        var pageable = Mockito.mock(Pageable.class);
        listOrdersReadyPreparingUseCase.list(pageable);
        verify(listOrdersReadyPreparingOutputPort).list(List.of(OrderStatus.PREPARING, OrderStatus.READY), pageable);
    }
}