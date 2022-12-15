package java.spring.rabitmqproducer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.spring.rabitmqproducer.configuration.MqConfiguration;
import java.spring.rabitmqproducer.dto.CustomMessage;
import java.util.Date;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class MessagePublisher {

    private final RabbitTemplate template;


    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomMessage message) {
        message.setId(UUID.randomUUID().toString());
        message.setDate(new Date());

        template.convertAndSend(MqConfiguration.EXCHANGE, MqConfiguration.ROUTING_KEY, message);

        return "Message has been published!";
    }

}
