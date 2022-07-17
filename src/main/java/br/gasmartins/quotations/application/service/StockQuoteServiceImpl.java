package br.gasmartins.quotations.application.service;

import br.gasmartins.quotations.application.StockQuote;
import br.gasmartins.quotations.application.publisher.StockQuotePublisher;
import com.google.inject.Inject;

import java.io.Serializable;


public class StockQuoteServiceImpl implements StockQuoteService, Serializable {

    private static final long serialVersionUID = 1L;
    private final StockQuotePublisher publisher;

    @Inject
    public StockQuoteServiceImpl(StockQuotePublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void notify(StockQuote stockQuote) {
        this.publisher.publish(stockQuote);
    }

}
