package com.fiap.tc.core.usecase.order;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.adapters.driven.infrastructure.persistence.entities.OrderEntity;
import com.fiap.tc.adapters.driven.infrastructure.persistence.mappers.base.MapperConstants;
import com.fiap.tc.core.application.utils.QRCodeGenerator;
import com.fiap.tc.core.application.usecase.order.LoadOrderUseCase;
import com.fiap.tc.core.domain.model.enums.OrderStatus;
import com.fiap.tc.core.application.port.out.order.LoadOrderOutputPort;
import com.fiap.tc.fixture.FixtureTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoadOrderUseCaseTest extends FixtureTest {

    public static final UUID UUID = java.util.UUID.randomUUID();
    @Mock
    private LoadOrderOutputPort loadOrderOutputPort;

    @Mock
    private QRCodeGenerator qrCodeGenerator;

    @InjectMocks
    private LoadOrderUseCase loadOrderUseCase;

    private OrderEntity orderEntity;

    @BeforeEach
    public void setUp() {
        orderEntity = Fixture.from(OrderEntity.class).gimme("valid");
    }

    @Test
    public void loadOrderTest() {
        var order = MapperConstants.ORDER_MAPPER.fromEntity(orderEntity);
        order.setStatus(OrderStatus.FINISHED);

        when(loadOrderOutputPort.load(UUID)).thenReturn(order);
        var orderResult = loadOrderUseCase.load(UUID);

        assertEquals(order, orderResult.getOrder());
        verify(loadOrderOutputPort).load(UUID);
        verify(qrCodeGenerator, times(0)).generate(Mockito.anyString());

    }

    @Test
    public void loadOrderWithPaymentLinkTest() {
        var order = MapperConstants.ORDER_MAPPER.fromEntity(orderEntity);
        order.setStatus(OrderStatus.RECEIVED);

        when(loadOrderOutputPort.load(UUID)).thenReturn(order);
        var orderResult = loadOrderUseCase.load(UUID);

        assertEquals(order, orderResult.getOrder());
        verify(loadOrderOutputPort).load(UUID);
        verify(qrCodeGenerator, times(1)).generate(Mockito.anyString());

    }
}
