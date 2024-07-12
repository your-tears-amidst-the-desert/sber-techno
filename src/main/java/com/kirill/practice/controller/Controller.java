package com.kirill.practice.controller;

import com.kirill.practice.kafka.KafkaProducer;
import com.kirill.practice.repository.TransactionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "kafka")
@RestController
public class Controller {
    private final KafkaProducer kfkProd;
    private final TransactionRepository transRepo;

    public Controller(KafkaProducer kfkProd, TransactionRepository transRepo) {
        this.kfkProd = kfkProd;
        this.transRepo = transRepo;
    }

    @Operation(
            summary = "Взаимодействие Producer и Consumer в Kafka.",
            description = "По заданному ID ищет транзакцию, если она существует, то печатает её."
    )
    @PostMapping("/kafka/send")
    public String send(@RequestParam int id) {
        var trans = transRepo.findById(id);
        String mes = trans.toString();
        if(mes.equals("Optional.empty")) {
            return "Error: There is no transaction with such ID!";
        }
        else {
            kfkProd.sendMessage(mes);
            return NotificationService.sendNotificationToSender(mes);
        }
    }
}