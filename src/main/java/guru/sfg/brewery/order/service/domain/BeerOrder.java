package guru.sfg.brewery.order.service.domain;

import lombok.*;
import org.hibernate.annotations.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Proxy(lazy = false)
public class BeerOrder {

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

    private String customerRef;

    @ManyToOne(fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private Customer customer;

    @OneToMany(mappedBy = "beerOrder", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    private Set<BeerOrderLine> beerOrderLines;

    @Enumerated(EnumType.STRING)
    private BeerOrderStatusEnum orderStatus = BeerOrderStatusEnum.NEW;

    private String orderStatusCallbackUrl;
}