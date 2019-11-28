package br.com.marinho.mysql;

import br.com.marinho.mysql.facade.ProcessMessage;
import br.com.marinho.mysql.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    @Autowired
    ProcessMessage processarMensagem;

    @KafkaListener(topics = "${message.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(final ConsumerRecord<String, Message> consumerRecord) {
        log.info("key: " + consumerRecord.key());
        log.info("Headers: " + consumerRecord.headers());
        log.info("Partion: " + consumerRecord.partition());
        log.info("Message: " + consumerRecord.value());

        processarMensagem.process(consumerRecord.value());

    }

}