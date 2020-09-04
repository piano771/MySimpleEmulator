package ru.pflb.emulator.service.impl;

import org.springframework.stereotype.Service;
import ru.pflb.emulator.model.dto.ClientDto;
import ru.pflb.emulator.service.ClientService;

import java.io.File;
import java.util.Random;

@Service
public class ClientServiceImpl implements ClientService {

    @Override
    public ClientDto getUserById(long id) {
        ClientDto client = ClientDto.builder()
                .id(id)
                .cardNumber(generateRandomCardNumber())
                .firstName(generateRandomName())
                .lastName(generateRandomName())
                .itn(generateRandomItn())
                .img(getRandomImg())
                .isActive(generateRandomStatus())
                .build();

        return client;
    }

    private String getRandomImg() {
        String list[] = new File("./img").list();
        String randomImg = list[getRandomInt(0, 2)];

        return randomImg;
    }

    private String generateRandomItn() {
        StringBuilder itn = new StringBuilder();
        for(int i = 0; i < 10; i++) {
            itn.append(getRandomInt(0, 9));
        }

        return itn.toString();
    }

    private boolean generateRandomStatus() {
        if(getRandomInt(0, 10) % 2 > 0) {
            return false;
        } else return true;
    }

    private String generateRandomName() {
        Random random = new Random();
        char[] word = new char[random.nextInt(8) + 3];
        for(int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        String newName = new String(word);

        return newName;
    }

    private String generateRandomCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        for(int i = 0; i < 16; i++) {
            cardNumber.append(getRandomInt(0, 9));
        }

        return cardNumber.toString();
    }

    private int getRandomInt(int min, int max) {
        int randomInt = (int) ((Math.random() * ((max - min) + 1)) + min);

        return randomInt;
    }
}
