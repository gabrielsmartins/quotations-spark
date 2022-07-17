package br.gasmartins.quotations.adapters.file.mapper;

import br.gasmartins.quotations.adapters.file.mapper.dto.StockQuoteRecord;
import br.gasmartins.quotations.application.StockQuote;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
public class StockQuoteFileMapper implements Serializable {

    private static final long serialVersionUID = 1L;

    @SneakyThrows
    public StockQuoteRecord mapToRecord(String json){
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(json, StockQuoteRecord.class);
    }

    public StockQuote mapToDomain(StockQuoteRecord record){
        var stockQuote = new StockQuote();
        stockQuote.setId(record.getId());
        stockQuote.setStockId(record.getStockId());
        record.getQuotes().forEach(stockQuote::addQuote);
        return stockQuote;
    }

}
