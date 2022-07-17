package br.gasmartins.quotations.adapters.file.config;

import br.gasmartins.quotations.adapters.file.StockQuoteFileProcessor;
import br.gasmartins.quotations.adapters.file.mapper.StockQuoteFileMapper;
import br.gasmartins.quotations.common.ApplicationProperties;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FileModule extends AbstractModule {

    private final ApplicationProperties properties;

    @Override
    protected void configure() {
        bind(StockQuoteFileMapper.class);
        bind(StockQuoteFileProcessor.class);
        bind(AmazonS3.class).toInstance(amazonS3());
    }

    private AmazonS3 amazonS3(){
        var awsProperties = this.properties.getAwsProperties();
        var s3Properties = awsProperties.getS3Properties();
        var endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(s3Properties.getEndpoint(), s3Properties.getRegion());
        return AmazonS3ClientBuilder.standard()
                                    .withEndpointConfiguration(endpointConfiguration)
                                    .build();
    }
}
