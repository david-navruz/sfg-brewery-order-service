package guru.sfg.brewery.order.service.repositories;

import guru.sfg.brewery.order.service.domain.BeerOrder;
import guru.sfg.brewery.order.service.domain.BeerOrderStatusEnum;
import guru.sfg.brewery.order.service.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {

    Page<BeerOrder> findAllByCustomer(Customer customer, Pageable pageable);

    List<BeerOrder> findAllByOrderStatus(BeerOrderStatusEnum orderStatusEnum);

    @Query("select b from BeerOrder where b.id=?1")
    Optional<BeerOrder> findOrderUsingStringId(String id);

}
