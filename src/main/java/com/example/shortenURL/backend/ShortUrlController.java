package com.example.shortenURL.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class ShortUrlController {

    public ShortUrlService shortUrlService;

    @PostMapping("api/shorten")
    public ResponseEntity<Map<String, String>> shortenUrl (@RequestParam("longUrl") String longUrl)
    {
        Map<String, String> map = new HashMap<>();
        try{
            String newShortUrl = shortUrlService.createShortUrl(longUrl);

            map.put("shortUrl", newShortUrl);
            map.put("longUrl", longUrl);

            return ResponseEntity.ok(map);
        }catch(Exception e){
            map.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }
    @GetMapping("/{shortUrl}")
    public ResponseEntity<Map<String, String>> getLongUrl (@PathVariable String shortUrl)
    {
        Map<String, String> map = new HashMap<>();
        try{
            String newLongUrl = shortUrlService.getLongUrl(shortUrl);
            map.put("shortUrl", newLongUrl);
            return ResponseEntity.ok(map);
        }catch(Exception e){
            map.put("Error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
        }
    }
}
