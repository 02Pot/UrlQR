package com.example.demo.controller;

import com.example.demo.dto.LinkDTO;
import com.example.demo.model.LinkModel;
import com.example.demo.service.UrlManager;
import com.example.demo.service.UrlManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/shorten")
public class UrlController {

    @Autowired
    private UrlManager urlManager;

    private final UrlManagerImpl urlService;

    public UrlController(UrlManagerImpl urlService) {
        this.urlService = urlService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Map<String,Object>> shortenUrl(@RequestBody LinkDTO linkDTO) throws Exception {
        String url = linkDTO.getLinkUrl();
        LinkModel urlEntry = urlManager.shortenUrl(url);

        String shortenedUrl = "https://shrtk.com/" + urlEntry.getLinkId().substring(0,6);

        Map<String, Object> response = new HashMap<>();
        response.put("url",shortenedUrl);
        response.put("qrcode",urlEntry.getQrCode());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{linkId}")
    public ResponseEntity<String> getUrl(@PathVariable String linkId){
        String url = urlManager.getLinkById(linkId);
        return ResponseEntity.ok(url);
    }

    @GetMapping(value = "/qr/{linkId}")
    public ResponseEntity<String> getQrCode(@PathVariable String linkId){
        String qrCode = urlManager.getQrCodeById(linkId);
        return ResponseEntity.ok(qrCode);
    }

    @GetMapping(value = "/redirect/{linkId}")
    public ResponseEntity<Void> redirectToURL(@PathVariable String linkId){

        String origUrl = urlManager.getLinkById(linkId);

        if (origUrl != null){
            return ResponseEntity.status(302)
                    .header("Location",origUrl)
                    .build();
        }

        return ResponseEntity.notFound().build();
    }
}
