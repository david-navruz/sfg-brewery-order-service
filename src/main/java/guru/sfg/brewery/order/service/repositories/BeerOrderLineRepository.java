package guru.sfg.brewery.order.service.repositories;

import guru.sfg.brewery.order.service.domain.BeerOrderLine;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerOrderLineRepository extends PagingAndSortingRepository<BeerOrderLine, UUID> {

    
}
