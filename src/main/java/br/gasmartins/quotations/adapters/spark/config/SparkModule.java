package br.gasmartins.quotations.adapters.spark.config;

import br.gasmartins.quotations.common.ApplicationProperties;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;


public class SparkModule extends AbstractModule {

    private final ApplicationProperties properties;

    @Inject
    public SparkModule(ApplicationProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void configure() {
        var conf = new SparkConf().setAppName(properties.getName());
        var session = SparkSession.builder()
                .appName(properties.getName())
                .config(conf)
                .getOrCreate();
        bind(SparkSession.class).toInstance(session);
    }



}
