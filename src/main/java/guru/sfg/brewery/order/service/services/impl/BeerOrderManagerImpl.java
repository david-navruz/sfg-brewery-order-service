package guru.sfg.brewery.order.service.services.impl;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.order.service.domain.BeerOrder;
import guru.sfg.brewery.order.service.domain.BeerOrderEventEnum;
import guru.sfg.brewery.order.service.domain.BeerOrderStatusEnum;
import guru.sfg.brewery.order.service.repositories.BeerOrderRepository;
import guru.sfg.brewery.order.service.services.BeerOrderManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class BeerOrderManagerImpl implements BeerOrderManager {

    public static final String ORDER_ID_HEADER = "ORDER_ID";
    public static final String ORDER_OBJECT_HEADER = "BEER_ORDER";


    private final StateMachineFactory<BeerOrderStatusEnum, BeerOrderEventEnum> stateMachineFactory;
    private final BeerOrderRepository beerOrderRepository;




    @Override
    public BeerOrder newBeerOrder(BeerOrder beerOrder) {
        return null;
    }

    @Override
    public void beerOrderPassedValidation(UUID beerOrderId) {

    }

    @Override
    public void beerOrderFailedValidation(UUID beerOrderId) {

    }

    @Override
    public void beerOrderAllocationPassed(BeerOrderDto beerOrder) {

    }

    @Override
    public void beerOrderAllocationPendingInventory(BeerOrderDto beerOrder) {

    }

    @Override
    public void beerOrderAllocationFailed(BeerOrderDto beerOrder) {

    }

    @Override
    public void pickupBeerOrder(UUID beerOrderId) {

    }

    @Override
    public void cancelOrder(UUID beerOrderId) {

    }
}
