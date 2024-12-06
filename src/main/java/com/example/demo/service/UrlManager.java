package com.example.demo.service;

import com.example.demo.model.LinkModel;

public interface UrlManager {
    public String getLinkById(String id);
    public String getQrCodeById(String id);
    public LinkModel shortenUrl(String id) throws Exception;
}
