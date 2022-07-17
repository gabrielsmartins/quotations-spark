package br.gasmartins.quotations.adapters.messaging.mapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder(setterPrefix = "with")
public class StockQuoteMessage {

    @JsonProperty(value = "id", required = true)
    private UUID id;

    @JsonProperty(value = "stock_id", required = true)
    private String stockId;

    @JsonProperty(value = "quotes", required = true)
    @Builder.Default
    private final Map<LocalDate, BigDecimal> quotes = new LinkedHashMap<>();

    public Integer addQuote(LocalDate date, BigDecimal amount){
        this.quotes.put(date, amount);
        return this.quotes.size();
    }

}
