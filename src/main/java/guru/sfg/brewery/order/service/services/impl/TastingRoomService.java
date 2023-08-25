package guru.sfg.brewery.order.service.services.impl;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.model.BeerOrderLineDto;
import guru.sfg.brewery.order.service.bootstrap.OrderServiceBootstrap;
import guru.sfg.brewery.order.service.domain.Customer;
import guru.sfg.brewery.order.service.repositories.BeerOrderRepository;
import guru.sfg.brewery.order.service.repositories.CustomerRepository;
import guru.sfg.brewery.order.service.services.BeerOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
public class TastingRoomService {

    private final CustomerRepository customerRepository;
    private final BeerOrderService beerOrderService;
    private final BeerOrderRepository beerOrderRepository;
    private final List<String> beerUpcs = new ArrayList<>();


    public TastingRoomService(CustomerRepository customerRepository, BeerOrderService beerOrderService,
                              BeerOrderRepository beerOrderRepository) {
        this.customerRepository = customerRepository;
        this.beerOrderService = beerOrderService;
        this.beerOrderRepository = beerOrderRepository;

        beerUpcs.add(OrderServiceBootstrap.BEER_1_UPC);
        beerUpcs.add(OrderServiceBootstrap.BEER_2_UPC);
        beerUpcs.add(OrderServiceBootstrap.BEER_3_UPC);
    }


    @Transactional
    @Scheduled(fixedRate = 2000) //run every 2 seconds
    public void placeTastingRoomOrder() {
        List<Customer> customerList = customerRepository.findAllByCustomerNameLike(OrderServiceBootstrap.TASTING_ROOM);
        if (customerList.size() == 1) {
            doPlaceOrder(customerList.get(0));
        } else {
            log.error("Too many or too few tasting room customers found");
            customerList.forEach(customer -> {
                log.debug(customer.toString());
            });
        }
    }


    private void doPlaceOrder(Customer customer) {
        String beerUpcToOrder = getRandomBeerUpc();

        BeerOrderLineDto beerOrderLine = BeerOrderLineDto.builder()
                .upc(beerUpcToOrder)
                .orderQuantity(new Random().nextInt(10))
                .build();

        List<BeerOrderLineDto> beerOrderLineList = new ArrayList<>();
        beerOrderLineList.add(beerOrderLine);

        BeerOrderDto beerOrder = BeerOrderDto.builder()
                .customerId(customer.getId())
                .customerRef(UUID.randomUUID().toString())
                .beerOrderLines(beerOrderLineList)
                .build();

        BeerOrderDto savedOrder = beerOrderService.placeOrder(customer.getId(), beerOrder);
    }



    private String getRandomBeerUpc(){
        return beerUpcs.get(new Random().nextInt(beerUpcs.size()));
    }




}
