package guru.sfg.brewery.order.service.web.mappers;

import guru.sfg.brewery.model.CustomerDto;
import guru.sfg.brewery.order.service.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface CustomerMapper {

    CustomerDto customerToDto(Customer customer);

    Customer dtoToCustomer(Customer customerDto);

}
