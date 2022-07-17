package br.gasmartins.quotations.adapters.messaging.config;

import br.gasmartins.quotations.adapters.messaging.SqsStockQuotePublisher;
import br.gasmartins.quotations.common.ApplicationProperties;
import br.gasmartins.quotations.application.publisher.StockQuotePublisher;
import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessagingModule extends AbstractModule {

    private final ApplicationProperties properties;

    @Override
    protected void configure() {
        bind(StockQuotePublisher.class).to(SqsStockQuotePublisher.class);
    }


}
