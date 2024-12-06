package com.example.demo.service;

import com.example.demo.model.LinkModel;
import com.google.common.hash.Hashing;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class UrlManagerImpl implements UrlManager {

    @Autowired
    private RedisTemplate<String, LinkModel> redisTemplate;
    private QrCodeGenerator qrCodeGeneratorService;

    public UrlManagerImpl (QrCodeGenerator qrCodeGeneratorService, RedisTemplate<String, LinkModel> redisTemplate) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String getLinkById(String id) {
        LinkModel url = redisTemplate.opsForValue().get(id);
        return url.getLinkUrl();
    }

    @Override
    public String getQrCodeById(String id){
        LinkModel qr = redisTemplate.opsForValue().get(id);
        String qrCode = Base64.getEncoder().encodeToString(qr.getQrCode());
        return  qrCode;
    }

    @Override
    public LinkModel shortenUrl(String url) throws Exception {
        String key = Hashing.murmur3_128()
                .hashString(url, Charset.defaultCharset())
                .toString();

        String shortenedUrl = key;

        byte[] qrCode = qrCodeGeneratorService.generateQRCodeImage(shortenedUrl,300,300);

        LinkModel urlEntry = LinkModel.builder()
                .linkId(key)
                .createdOn(LocalDateTime.now())
                .qrCode(qrCode)
                .linkUrl(url)
                .build();

        redisTemplate.opsForValue().set(key, urlEntry);

        return urlEntry;
    }


}