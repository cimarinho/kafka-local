package br.com.marinho.mysql.facade;

import br.com.marinho.mysql.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Slf4j
public class ProcessFile {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private List<String> messagetopic;

    public void process(String file) {
        String[] token = file.split(";");

        Long numJorney = Long.parseLong(token[0]);
        Double rate = Double.parseDouble(token[1]);

        Message message = new Message(numJorney, rate, LocalDate.now());

        kafkaTemplate.send(messagetopic, message);
    }
}
