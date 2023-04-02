package guru.sfg.brewery.order.service.web.mappers;

import guru.sfg.brewery.model.BeerOrderDto;
import guru.sfg.brewery.order.service.domain.BeerOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {

    @Mapping(source = "customer.id", target = "customerId")
    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);

    BeerOrder dtoToBeerOrder(BeerOrderDto dto);

}
