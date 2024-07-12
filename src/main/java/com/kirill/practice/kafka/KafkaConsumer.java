package com.kirill.practice.kafka;

import com.kirill.practice.controller.MainController;
import com.kirill.practice.controller.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {
    Logger logger = LoggerFactory.getLogger(MainController.class);
    @KafkaListener(topics = "transactions", groupId = "my_consumer")
    public void listen(String message) {
        //logger.info("Received message = " + message);
        System.out.println(NotificationService.sendNotificationToRecipient(message));
    }
}