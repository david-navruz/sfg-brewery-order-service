package guru.sfg.brewery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerOrderLineDto {

    @Null
    private UUID id = null;

    @Null
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer version = null;

    @Null
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate = null;

    @Null
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate = null;

    @Null
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID beerId;

    @NotNull
    private String upc;
    private String beerName;
    private String beerStyle;

    @NotNull
    @Positive
    private Integer orderQuantity;

    @Null
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer quantityAllocated;

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private BigDecimal price;
}
