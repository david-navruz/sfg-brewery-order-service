package guru.sfg.brewery.order.service.services.listeners;

import guru.sfg.brewery.model.events.BeerOrderValidationResult;
import guru.sfg.brewery.model.events.ValidateBeerOrderRequest;
import guru.sfg.brewery.order.service.services.BeerOrderManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import guru.sfg.brewery.order.service.config.JmsConfig;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class ValidationResultListener {

    private final BeerOrderManager beerOrderManager;


    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_RESULT_QUEUE)
    public void listen(BeerOrderValidationResult result){
        final UUID beerOrderId = result.getBeerOrderId();
        log.debug("Validation Result for Order Id: " + beerOrderId);
        beerOrderManager.processvalidationResult(beerOrderId, result.getIsValid());
    }



}
