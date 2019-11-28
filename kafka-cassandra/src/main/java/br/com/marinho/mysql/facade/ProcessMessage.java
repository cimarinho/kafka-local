package br.com.marinho.mysql.facade;

import br.com.marinho.mysql.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProcessMessage {

    public void process(Message message){
       log.info(message.toString());
    }
}
