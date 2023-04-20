package guru.sfg.brewery.order.service.statemachine.actions;

import guru.sfg.brewery.model.events.AllocateBeerOrderRequest;
import guru.sfg.brewery.order.service.config.JmsConfig;
import guru.sfg.brewery.order.service.domain.BeerOrder;
import guru.sfg.brewery.order.service.domain.BeerOrderEventEnum;
import guru.sfg.brewery.order.service.domain.BeerOrderStatusEnum;
import guru.sfg.brewery.order.service.repositories.BeerOrderRepository;
import guru.sfg.brewery.order.service.services.impl.BeerOrderManagerImpl;
import guru.sfg.brewery.order.service.web.mappers.BeerOrderMapper;
import guru.sfg.brewery.order.service.web.mappers.BeerOrderMapperImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class AllocateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {


    private final JmsTemplate jmsTemplate;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;


    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        Optional<BeerOrder> beerOrderOptional = beerOrderRepository.findById(UUID.fromString(beerOrderId));

        beerOrderOptional.ifPresentOrElse(beerOrder -> {
            jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_QUEUE, AllocateBeerOrderRequest.builder().beerOrder(beerOrderMapper.beerOrderToDto(beerOrder)).build());

        }, () -> log.error("Order Not Found. Id: " + beerOrderId));

        log.debug("Sent Allocatiob request to queue for order id " + beerOrderId);
    }



}
