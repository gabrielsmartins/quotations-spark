package br.gasmartins.quotations;

import br.gasmartins.quotations.adapters.file.StockQuoteFileProcessor;
import br.gasmartins.quotations.adapters.file.config.FileModule;
import br.gasmartins.quotations.adapters.spark.config.SparkModule;
import br.gasmartins.quotations.common.ApplicationPropertiesLoader;
import br.gasmartins.quotations.application.config.ApplicationModule;
import br.gasmartins.quotations.adapters.messaging.config.MessagingModule;
import com.google.inject.Guice;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class StockQuoteApplication {

    public static void main(String[] args) {
        var properties = ApplicationPropertiesLoader.loadProperties();
        var injector = Guice.createInjector(new ApplicationModule(), new SparkModule(properties), new FileModule(properties), new MessagingModule(properties));
        var processor = injector.getInstance(StockQuoteFileProcessor.class);
        String fileName = args[0];
        log.info("Processing file: {}", fileName);
        processor.process(fileName);
        log.info("File was processed successfully");
    }

}
