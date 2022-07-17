package br.gasmartins.quotations.adapters.messaging;

import br.gasmartins.quotations.common.ApplicationPropertiesLoader;
import br.gasmartins.quotations.application.StockQuote;
import br.gasmartins.quotations.application.publisher.StockQuotePublisher;
import br.gasmartins.quotations.adapters.messaging.mapper.SqsStockQuotePublisherMapper;
import br.gasmartins.quotations.adapters.messaging.mapper.dto.StockQuoteMessage;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.Inject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
public class SqsStockQuotePublisher implements StockQuotePublisher, Serializable {

    private static final long serialVersionUID = 1L;
    private final SqsStockQuotePublisherMapper mapper;

    @Inject
    public SqsStockQuotePublisher(SqsStockQuotePublisherMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void publish(StockQuote stockQuote) {
       log.info("Mapping stock quote: {}", stockQuote);
        var stockQuoteMessage = this.mapper.mapToMessage(stockQuote);
        log.info("Stock quote was mapped successfully: {}", kv("stockQuote", stockQuoteMessage));
        var message = convertToString(stockQuoteMessage);
        var properties = ApplicationPropertiesLoader.loadProperties();
        var quotationQueue = properties.getQuotationQueue();
        log.info("Sending stock quote message: {}", kv("message", message));
        var amazonSQS = this.buildAmazonSqs();
        var result = amazonSQS.sendMessage(quotationQueue, message);
        log.info("Stock Quote was sent successfully: {}, {}", kv("message_id", result.getMessageId()), kv("message", message));
    }

    @SneakyThrows
    private String convertToString(StockQuoteMessage stockQuoteMessage){
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(stockQuoteMessage);
    }

    //Building instance to avoid Task not serializable on SparkException
    private AmazonSQS buildAmazonSqs(){
        var properties = ApplicationPropertiesLoader.loadProperties();
        var awsProperties = properties.getAwsProperties();
        var sqsProperties = awsProperties.getSqsProperties();
        var endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(sqsProperties.getEndpoint(), sqsProperties.getRegion());
        var credentials = new BasicAWSCredentials(sqsProperties.getAccessKey(),sqsProperties.getSecretKey());
        var awsCredentialsProvider = new AWSStaticCredentialsProvider(credentials);
        return AmazonSQSClientBuilder.standard()
                                     .withEndpointConfiguration(endpointConfiguration)
                                     .withCredentials(awsCredentialsProvider)
                                     .build();
    }

}
