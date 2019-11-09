package br.com.marinho.producer.controller;

import br.com.marinho.producer.MessageProducer;
import br.com.marinho.producer.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/message")
@Slf4j
public class MessageController {

    private final MessageProducer messageProducer;

    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void send(@RequestBody Message message) {
        messageProducer.send(message);
    }
}
