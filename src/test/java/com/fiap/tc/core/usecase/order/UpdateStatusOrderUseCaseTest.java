package com.fiap.tc.core.usecase.order;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.core.domain.requests.OrderStatusRequest;
import com.fiap.tc.core.port.out.order.UpdateStatusOrderOutputPort;
import com.fiap.tc.util.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UpdateStatusOrderUseCaseTest extends BaseTest {

    @Mock
    private UpdateStatusOrderOutputPort updateStatusOrderOutputPort;

    @InjectMocks
    private UpdateStatusOrderUseCase updateStatusOrderUseCase;

    private OrderStatusRequest request;

    @BeforeEach
    public void setUp() {
        request = Fixture.from(OrderStatusRequest.class).gimme("valid");
    }

    @Test
    public void updateTest() {

        updateStatusOrderUseCase.update(request);

        verify(updateStatusOrderOutputPort).update(request.getId(), request.getStatus());
    }

}