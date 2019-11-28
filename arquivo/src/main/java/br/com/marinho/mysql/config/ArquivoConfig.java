package br.com.marinho.mysql.config;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.Properties;

public class ArquivoConfig {

    @Value("${mysql.topic}")
    private String topicMysql;

    @Value("${cassandra.topic}")
    private String topicCassandra;

    @Bean
    public KafkaStreams kafkaStreams(KafkaProperties kafkaProperties,
                                     @Value("${spring.application.name}") String appName) {
        final Properties props = new Properties();

        // inject SSL related properties
        props.putAll(kafkaProperties.getSsl().buildProperties());
        props.putAll(kafkaProperties.getProperties());
        // stream config centric ones
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, appName);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class);
        props.put(StreamsConfig.STATE_DIR_CONFIG, "data");
        // others
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, JsonNode.class);

        final KafkaStreams kafkaStreams = new KafkaStreams(kafkaStreamTopology(), props);
        kafkaStreams.start();

        return kafkaStreams;
    }

    @Bean
    public Topology kafkaStreamTopology() {
        final StreamsBuilder streamsBuilder = new StreamsBuilder();
        streamsBuilder.stream(topicMysql);
        streamsBuilder.stream(topicCassandra);
        return streamsBuilder.build();
    }

}

