package com.kirill.practice.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotificationService {
    public static String sendNotificationToRecipient(String json) {
        json = json.replace("Optional[", "").replace("]", "");
        String recipientNotification = null;
        try {
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

            String transferDate = jsonObject.get("transferDate").getAsString();
            LocalDateTime dateTime = LocalDateTime.parse(transferDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            String formattedDate = dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yy 'в' HH:mm"));
            String payToolName = jsonObject.get("payToolName").getAsString();
            JsonObject senderObject = jsonObject.getAsJsonObject("sender");
            String senderFirstName = senderObject.get("firstName").getAsString();
            String senderMiddleName = senderObject.get("middleName").getAsString();
            JsonObject recipientObject = jsonObject.getAsJsonObject("recipient");
            String senderAccountNumber = senderObject.get("accountNumber").getAsString();
            String recipientAccountNumber = recipientObject.get("accountNumber").getAsString();
            double summa = jsonObject.get("summa").getAsDouble();
            DecimalFormat df = new DecimalFormat("#.00");
            String formattedSumma = df.format(summa);

            recipientNotification = String.format("Перевод от %s %s. на сумму %sр зачислен на счёт ***%s.",
                    senderFirstName, senderMiddleName.charAt(0), formattedSumma, recipientAccountNumber.substring(recipientAccountNumber.length() - 3));
        }
        catch (JsonSyntaxException e) {
            System.err.println("Ошибка при парсинге JSON: " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("Возникла ошибка: " + e.getMessage());
        }
        return recipientNotification;
    }

    public static String sendNotificationToSender(String json) {
        json = json.replace("Optional[", "").replace("]", "");
        String senderNotification = null;
        try {
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();

            String transferDate = jsonObject.get("transferDate").getAsString();
            LocalDateTime dateTime = LocalDateTime.parse(transferDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            String formattedDate = dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yy 'в' HH:mm"));
            String payToolName = jsonObject.get("payToolName").getAsString();
            JsonObject senderObject = jsonObject.getAsJsonObject("sender");
            String senderFirstName = senderObject.get("firstName").getAsString();
            String senderMiddleName = senderObject.get("middleName").getAsString();
            JsonObject recipientObject = jsonObject.getAsJsonObject("recipient");
            String senderAccountNumber = senderObject.get("accountNumber").getAsString();
            String recipientAccountNumber = recipientObject.get("accountNumber").getAsString();
            double summa = jsonObject.get("summa").getAsDouble();
            DecimalFormat df = new DecimalFormat("#.00");
            String formattedSumma = df.format(summa);

            senderNotification = String.format("Перевод %s с карты %s-%s на сумму %sр. выполнен.",
                    formattedDate, payToolName, senderAccountNumber.substring(senderAccountNumber.length()-4), formattedSumma);
        }
        catch (JsonSyntaxException e) {
            System.err.println("Ошибка при парсинге JSON: " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("Возникла ошибка: " + e.getMessage());
        }
        return senderNotification;
    }
}