package br.gasmartins.quotations.application;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder(setterPrefix = "with")
public class StockQuote {


    private UUID id;
    private String stockId;

    @Builder.Default
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private final Map<LocalDate, BigDecimal> quotes = new LinkedHashMap<>();

    public Map<LocalDate, BigDecimal> getQuotes() {
        return Collections.unmodifiableMap(quotes);
    }

    public Integer addQuote(LocalDate date, BigDecimal amount){
        this.quotes.put(date, amount);
        return this.quotes.size();
    }

}
