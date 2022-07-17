package br.gasmartins.quotations.adapters.messaging.mapper;

import br.gasmartins.quotations.adapters.messaging.mapper.dto.StockQuoteMessage;
import br.gasmartins.quotations.application.StockQuote;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

public class SqsStockQuotePublisherMapper implements Serializable {

    private static final long serialVersionUID = 1L;

    public StockQuoteMessage mapToMessage(StockQuote stockQuote){
        var mapper = new ModelMapper();
        return mapper.map(stockQuote, StockQuoteMessage.class);
    }

}
