package guru.sfg.brewery.order.service.web.mappers;

import guru.sfg.brewery.model.BeerOrderLineDto;
import guru.sfg.brewery.order.service.domain.BeerOrderLine;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

/**
 * If we want to customize a generated mapping implementation by adding our custom logic.
 * MapStruct allows to define a Decorator class and annotate it with @DecoratedWith annotation.
 */
@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerOrderLineMapperDecorator.class)
public interface BeerOrderLineMapper {

    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);

}
