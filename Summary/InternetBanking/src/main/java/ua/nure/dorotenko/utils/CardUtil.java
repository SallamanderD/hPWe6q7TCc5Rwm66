package ua.nure.dorotenko.utils;

import java.util.concurrent.ThreadLocalRandom;

public class CardUtil {
    /**
     * Generate a card number
     * @return card number as string
     */
    public static String generateNumber(){
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= 16; i++){
            sb.append(ThreadLocalRandom.current().nextInt(0, 10));
        }
        return sb.toString();
    }

    public static String generatePIN(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 4; i++){
            sb.append(ThreadLocalRandom.current().nextInt(0, 10));
        }
        return sb.toString();
    }
}
