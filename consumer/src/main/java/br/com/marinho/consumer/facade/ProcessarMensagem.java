package br.com.marinho.consumer.facade;

import br.com.marinho.consumer.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProcessarMensagem {

    public void process(Message message){
       log.info(message.toString());
    }
}
