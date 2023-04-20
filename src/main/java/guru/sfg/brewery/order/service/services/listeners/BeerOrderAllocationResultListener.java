package guru.sfg.brewery.order.service.services.listeners;

import guru.sfg.brewery.model.events.AllocateBeerOrderResult;
import guru.sfg.brewery.order.service.config.JmsConfig;
import guru.sfg.brewery.order.service.services.BeerOrderManager;
import guru.sfg.brewery.order.service.services.impl.BeerOrderManagerImpl;
import guru.sfg.brewery.model.events.AllocateBeerOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class BeerOrderAllocationResultListener {

    private final BeerOrderManager beerOrderManager;


    @JmsListener(destination = JmsConfig.ALLOCATE_ORDER_RESULT_QUEUE)
    public void listen(AllocateBeerOrderResult result) {

        if (!result.getAllocationError() && !result.getPendingInventory()) {
            //allocated normally
            beerOrderManager.beerOrderAllocationPassed(result.getBeerOrderDto());
        } else if (!result.getAllocationError() && result.getPendingInventory()) {
            // pending inventory
            beerOrderManager.beerOrderAllocationPendingInventory(result.getBeerOrderDto());
        } else if (result.getAllocationError()) {
            // allocation error
            beerOrderManager.beerOrderAllocationFailed(result.getBeerOrderDto());
        }
    }


    }
