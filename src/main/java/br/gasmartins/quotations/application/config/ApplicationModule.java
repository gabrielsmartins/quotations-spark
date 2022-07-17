package br.gasmartins.quotations.application.config;

import br.gasmartins.quotations.application.service.StockQuoteService;
import br.gasmartins.quotations.application.service.StockQuoteServiceImpl;
import com.google.inject.AbstractModule;

public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StockQuoteService.class).to(StockQuoteServiceImpl.class);
    }
}
