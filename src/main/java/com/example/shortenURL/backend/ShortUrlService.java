package com.example.shortenURL.backend;

import com.example.shortenURL.backend.database.DBQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class ShortUrlService {

    private static DBQueries dbQueries;

    @Autowired
    public ShortUrlService() {
        this.dbQueries = dbQueries;
    }

    public static String getLongUrl(String shortUrl) {
        ShortUrl url = dbQueries.getLongUrl(shortUrl);
        return url.getLongURL();
    }

    public static String createShortUrl(String longUrl) {
        String shortUrl;

        if(dbQueries.getExistingUrl(longUrl) != null){
            shortUrl = dbQueries.getExistingUrl(longUrl);
        }else{
            shortUrl = randomUrl();
            dbQueries.saveUrl(new ShortUrl(0, shortUrl, longUrl));
        }

        return "http://localhost:3000/" + shortUrl;
    }

    public static String randomUrl() {

        Random random = new Random();
        StringBuilder shortURL = new StringBuilder();

        for(int i = 0; i < 3; i++){
            int randomNumber = random.nextInt(10);
            char randomCharacter = (char) ('a' + random.nextInt(26));
            shortURL.append(randomCharacter);
            shortURL.append(randomNumber);
        }

        if (!checkURLIsUnique(shortURL.toString())){
            randomUrl();
        }
        return String.valueOf(shortURL);
    }


    private static boolean checkURLIsUnique(String shortUrl) {
        try{
            ShortUrl url = dbQueries.getLongUrl(shortUrl);
            //does exist
            return false;
        }catch(Exception e){
            return true;
        }
    }

}
