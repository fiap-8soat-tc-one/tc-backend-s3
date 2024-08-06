package com.fiap.tc.core.usecase.payment;

import br.com.six2six.fixturefactory.Fixture;
import com.fiap.tc.core.domain.model.OrderPayment;
import com.fiap.tc.core.domain.requests.OrderPaymentRequest;
import com.fiap.tc.core.port.out.order.UpdateStatusOrderOutputPort;
import com.fiap.tc.core.port.out.payment.RegisterPaymentOutputPort;
import com.fiap.tc.util.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterPaymentUseCaseTest extends BaseTest {

    @Mock
    private RegisterPaymentOutputPort registerPaymentOutputPort;

    @Mock
    private UpdateStatusOrderOutputPort updateStatusOrderOutputPort;

    @InjectMocks
    private RegisterPaymentUseCase registerPaymentUseCase;

    private OrderPaymentRequest orderPaymentRequest;
    private OrderPayment orderPayment;

    @BeforeEach
    public void setUp() {
        orderPaymentRequest = Fixture.from(OrderPaymentRequest.class).gimme("valid");
        orderPayment = Fixture.from(OrderPayment.class).gimme("valid");
    }

    @Test
    public void registerPaymentTest() {
        when(registerPaymentOutputPort.saveOrUpdate(any())).thenReturn(orderPayment);

        registerPaymentUseCase.register(orderPaymentRequest);
        verify(registerPaymentOutputPort).saveOrUpdate(any());
        verify(updateStatusOrderOutputPort).update(any(), any());
    }


}