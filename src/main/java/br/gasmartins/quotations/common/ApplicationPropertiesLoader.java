package br.gasmartins.quotations.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationPropertiesLoader {

    private static ApplicationProperties properties;

    public static ApplicationProperties loadProperties() {
        try {
            if(properties == null){
                var mapper = new ObjectMapper(new YAMLFactory());
                var inputStream = ApplicationPropertiesLoader.class.getClassLoader().getResourceAsStream("application.yml");
                properties = mapper.readValue(inputStream, ApplicationProperties.class);
            }
           return properties;
        } catch (Exception e) {
            log.error("Error reading application properties file", e);
            throw new UnsupportedOperationException("Error reading application properties file");
        }
    }
}
