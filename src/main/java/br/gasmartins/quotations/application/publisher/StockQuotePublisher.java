package br.gasmartins.quotations.application.publisher;

import br.gasmartins.quotations.application.StockQuote;

public interface StockQuotePublisher {

    void publish(StockQuote stockQuote);

}
