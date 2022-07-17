package br.gasmartins.quotations.adapters.file;

import br.gasmartins.quotations.adapters.file.mapper.StockQuoteFileMapper;
import br.gasmartins.quotations.adapters.file.mapper.dto.StockQuoteRecord;
import br.gasmartins.quotations.application.StockQuote;
import br.gasmartins.quotations.application.service.StockQuoteService;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

@Slf4j
public class StockQuoteFileProcessor {

    private final SparkSession session;
    private final StockQuoteFileMapper mapper;
    private final StockQuoteService service;

    @Inject
    public StockQuoteFileProcessor(SparkSession session, StockQuoteFileMapper mapper, StockQuoteService service) {
        this.session = session;
        this.mapper = mapper;
        this.service = service;
    }

    public void process(String fileName){
        log.info("Reading file: {}", fileName);
        Dataset<String> dataset = session.read().option("multiline", true).json(fileName).toJSON();
        Dataset<StockQuote> stockQuoteDataset = dataset.map((MapFunction<String, StockQuoteRecord>) mapper::mapToRecord, Encoders.bean(StockQuoteRecord.class))
                                                       .map((MapFunction<StockQuoteRecord, StockQuote>) mapper::mapToDomain, Encoders.bean(StockQuote.class));
        stockQuoteDataset.foreach((ForeachFunction<StockQuote>) service::notify);
        log.info("Closing session");
        session.close();
        log.info("Session was closed successfully");
    }

}
