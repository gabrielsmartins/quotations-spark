package br.gasmartins.quotations.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationProperties {

    @JsonProperty("name")
    private String name;

    @JsonProperty("quotations-bucket-url")
    private String quotationBucket;

    @JsonProperty("quotations-queue")
    private String quotationQueue;

    @JsonProperty("aws")
    private ApplicationAwsProperties awsProperties;


    @Getter
    @Setter
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ApplicationAwsProperties{

        @JsonProperty("s3")
        private ApplicationS3Properties s3Properties;

        @JsonProperty("sqs")
        private ApplicationSqsProperties sqsProperties;

    }

    @Getter
    @Setter
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ApplicationS3Properties{

        @JsonProperty("region")
        private String region;

        @JsonProperty("endpoint")
        private String endpoint;

    }


    @Getter
    @Setter
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ApplicationSqsProperties{

        @JsonProperty("region")
        private String region;

        @JsonProperty("endpoint")
        private String endpoint;

        @JsonProperty("access-key")
        private String accessKey;

        @JsonProperty("secret-key")
        private String secretKey;

    }

}
