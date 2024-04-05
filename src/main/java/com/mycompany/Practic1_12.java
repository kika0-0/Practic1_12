/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class Practic1_12 {
    public static void main(String[] args) {
        System.out.println("Коржавина Виктория Рибо-03-22 вариант 2");
        
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Укажите путь к файлу: ");
        String pathToFile;
        try {
            pathToFile = fileReader.readLine();
        } catch (IOException ex) {
            System.err.println("Ошибка");
            return;
        }
        
        File inputFile = new File(pathToFile);
        if (!inputFile.exists() || !inputFile.isFile()) {
            System.err.println("Файл не найден");
            return;
        }
        new Thread(() -> {
            try (ObjectInputStream inputObject = new ObjectInputStream(new FileInputStream(inputFile))) {
                long serialVersionUID = inputObject.readLong();
                if (serialVersionUID != -3380157693869190848L) {
                    System.err.println("Несовместимая версия");
                    return;
                }
                Message message = (Message) inputObject.readObject();
                System.out.println("id: " + message.getId());
                System.out.println("body: " + message.getBody());
                System.out.println("type: " + message.getType());
                System.out.println("hasAttachments: " + message.isHasAttachments());
                System.out.println("timestamp:" + message.getTimestamp());
            } catch (IOException | ClassNotFoundException ex) { 
                System.err.println("Ошибка!!");   
            }
        }).start();
    }
}

    
