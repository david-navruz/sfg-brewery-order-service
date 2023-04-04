package guru.sfg.brewery.order.service.domain;

public enum BeerOrderStatusEnum {
    NEW, CANCELLED, PENDING_VALIDATION, VALIDATED, VALIDATION_EXCEPTION,
    PENDING_ALLOCATION, ALLOCATED, ALLOCATION_EXCEPTION, PENDING_INVENTORY,
    PICKED_UP, DELIVERED, DELIVERY_EXCEPTION
}
