package guru.sfg.brewery.order.service.domain;

import lombok.*;
import org.hibernate.annotations.*;
import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@ToString(exclude = "beerOrder")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Proxy(lazy = false)
public class BeerOrderLine {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    private UUID id;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    public boolean isNew() {
        return this.id == null;
    }

    @ManyToOne
    private BeerOrder beerOrder;

    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)")
    private UUID beerId;

    private Integer orderQuantity = 0;
    private Integer quantityAllocated = 0;
}
