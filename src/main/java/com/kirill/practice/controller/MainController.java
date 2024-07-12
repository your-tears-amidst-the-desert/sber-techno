package com.kirill.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kirill.practice.DTO.*;
import com.kirill.practice.entity.*;
import com.kirill.practice.repository.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "main_methods")
@RestController
@RequiredArgsConstructor
public class MainController {
    Logger logger = LoggerFactory.getLogger(MainController.class);
    private final ObjectMapper objectMapper;
    private final TransactionRepository transRepo;
    private final BankRepository bankRepo;
    private final CurrencyRepository currRepo;
    private final SenderRepository senderRepo;
    private final RecipientRepository recipRepo;

    @Operation(
            summary = "Помещает новую транзакцию в базу.",
            description = "Получает DTO транзакции и с помощью builder собирает и сохраняет сущность в базу."
    )
    @PostMapping("/api/add")
    public String addTransaction(@RequestBody TransactionDTO transDTO) {
        /*if(transRepo.findAll().stream().parallel().anyMatch(
                transaction -> transaction.getAccountNumber() == (transDTO.getAccountNumber()))) {
                logger.info("There is already a row with this account number!");
                return "There is already a row with this account number!";
        }
        else {*/
            logger.info(
                    "New row: " + transRepo.save(
                            Transaction.builder()
                                    .product(transDTO.getProduct())
                                    .subProduct(transDTO.getSubProduct())
                                    .summa(transDTO.getSumma())
                                    .transferDate(transDTO.getTransferDate())
                                    .payToolType(transDTO.getPayToolType())
                                    .payToolName(transDTO.getPayToolName())
                                    .status(transDTO.getStatus())
                                    .currency(currRepo.save(
                                            Currency.builder()
                                                    .id(transDTO.getCurrency().getId())
                                                    .name(transDTO.getCurrency().getName())
                                                    .isoCode(transDTO.getCurrency().getIsoCode())
                                                    .code(transDTO.getCurrency().getCode())
                                                    .build()))
                                    .recipient(recipRepo.save(
                                                    Recipient.builder()
                                                            .accountNumber(transDTO.getRecipient().getAccountNumber())
                                                            //.bank(createBank(transDTO.getRecipient().getBank()))
                                                            .bank(bankRepo.save(
                                                                    Bank.builder()
                                                                            .accountNumber(transDTO.getRecipient().getBank().getAccountNumber())
                                                                            .bic(transDTO.getRecipient().getBank().getBic())
                                                                            .id(transDTO.getRecipient().getBank().getId())
                                                                            .inn(transDTO.getRecipient().getBank().getInn())
                                                                            .name(transDTO.getRecipient().getBank().getName())
                                                                            .build()
                                                            ))
                                                            .firstName(transDTO.getRecipient().getFirstName())
                                                            .id(transDTO.getRecipient().getId())
                                                            .lastName(transDTO.getRecipient().getLastName())
                                                            .middleName(transDTO.getRecipient().getMiddleName())
                                                            .build()))
                                    .sender(senderRepo.save(
                                                    Sender.builder()
                                                            .id(transDTO.getSender().getId())
                                                            .accountNumber(transDTO.getSender().getAccountNumber())
                                                            .firstName(transDTO.getSender().getFirstName())
                                                            .lastName(transDTO.getSender().getLastName())
                                                            .middleName(transDTO.getSender().getMiddleName())
                                                            .bank(bankRepo.save(
                                                                    Bank.builder()
                                                                            .accountNumber(transDTO.getSender().getBank().getAccountNumber())
                                                                            .bic(transDTO.getSender().getBank().getBic())
                                                                            .id(transDTO.getSender().getBank().getId())
                                                                            .inn(transDTO.getSender().getBank().getInn())
                                                                            .name(transDTO.getSender().getBank().getName())
                                                                            .build()
                                                            ))
                                                            //.bank(createBank(transDTO.getSender().getBank()))
                                                            .build()))
                                    .build()
                    )
            );
            return "Row added.";
        }
    //}

    @Operation(
            summary = "Выдаёт все транзакции из базы.",
            description = "Выдаёт все транзакции из базы в виде строки."
    )
    @SneakyThrows
    @GetMapping("/api/get/all")
    public String getAllTransactions() {
        List<Transaction> trans = transRepo.findAll();
        logger.info("All transactions were required.");
        return objectMapper.writeValueAsString(trans);
    }

    @Operation(
            summary = "Выдаёт конкретную транзакцию из базы.",
            description = "Выдаёт запрошенную транзакцию из базы по её ID."
    )
    @GetMapping("/api/get")
    public Transaction getTransaction(@RequestParam int id) {
        if(transRepo.existsById(id)) {
            return transRepo.findById(id).orElseThrow();
        }
        else {
            logger.info("Error: No such row!");
            return null;
        }
    }

    @Operation(
            summary = "Удаляет конкретную транзакцию из базы.",
            description = "Удаляет запрошенную транзакцию из базы по её ID."
    )
    @DeleteMapping("/api/del")
    public String deleteTransaction(@RequestParam int id) {
        if(transRepo.existsById(id)) {
            logger.info("Delete row: " + transRepo.findById(id));
            bankRepo.deleteById(transRepo.getReferenceById(id).getSender().getBank().getId());
            bankRepo.deleteById(transRepo.getReferenceById(id).getRecipient().getBank().getId());
            currRepo.deleteById(transRepo.getReferenceById(id).getCurrency().getId());
            recipRepo.deleteById(transRepo.getReferenceById(id).getRecipient().getId());
            senderRepo.deleteById(transRepo.getReferenceById(id).getSender().getId());
            transRepo.deleteById(id);
            return "Delete row with ID = " + id;
        }
        else {
            logger.info("No such row!");
            return "Error: No such row!";
        }

    }

    @Operation(
            summary = "Изменяет конкретную транзакцию из базы.",
            description = "Изменяет запрошенную транзакцию из базы по её ID."
    )
    @PutMapping("/api/put")
    public String changeTransaction(@RequestBody Transaction trans) {
        if(!transRepo.existsById(trans.getID())) {
            return "Error: No such row!";
        }
        logger.info("Change row: " + transRepo.save(trans));
        return transRepo.save(trans).toString();
    }
}