package br.com.marinho.mysql;

import br.com.marinho.mysql.facade.ProcessFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Component
@Slf4j
public class FileConsumer {

    @Autowired
    ProcessFile processarMensagem;

    @KafkaListener(topics = "${file.topic}", groupId = "${spring.kafka.consumer.group-file-id}")
    public void consumer(final String consumerRecord) {
        processarMensagem.process(consumerRecord);
    }
}
