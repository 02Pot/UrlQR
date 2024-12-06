package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("LinkModel")
public class LinkModel implements Serializable {

    @Id
    private String linkId;
    private String linkUrl;
    private byte[] qrCode;

    private LocalDateTime createdOn;
}
