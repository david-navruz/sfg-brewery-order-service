package guru.sfg.brewery.order.service.services.impl;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.model.BeerOrderPagedList;
import guru.sfg.brewery.order.service.repositories.BeerOrderRepository;
import guru.sfg.brewery.order.service.repositories.CustomerRepository;
import guru.sfg.brewery.order.service.services.BeerOrderManager;
import guru.sfg.brewery.order.service.services.BeerOrderService;
import guru.sfg.brewery.order.service.web.mappers.BeerOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class BeerOrderServiceImpl implements BeerOrderService {

    private BeerOrderRepository beerOrderRepository;
    private CustomerRepository customerRepository;
    private BeerOrderMapper beerOrderMapper;
    private final BeerOrderManager beerOrderManager;


    @Override
    public BeerOrderPagedList listOrdersByCustomerId(UUID customerId, Pageable pageable) {
        return null;
    }

    @Override
    public BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto) {
        return null;
    }

    @Override
    public BeerOrderDto getOrderById(UUID customerId, UUID orderId) {
        return null;
    }

    @Override
    public void pickupOrder(UUID customerId, UUID orderId) {

    }
}
