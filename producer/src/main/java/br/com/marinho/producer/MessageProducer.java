package br.com.marinho.producer;

import br.com.marinho.producer.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Component
public class MessageProducer {

    @Value("${message.topic}")
    private String messagetopic;

    private final KafkaTemplate<String, Message> kafkaTemplate;

    public MessageProducer(final KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(final @RequestBody Message message) {
        final String mensageKey = UUID.randomUUID().toString();
        kafkaTemplate.send(messagetopic, mensageKey,  message);
    }

}
