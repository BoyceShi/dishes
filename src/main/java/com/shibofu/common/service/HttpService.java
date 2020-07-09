package com.shibofu.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author potter.fu
 * @date 2018-12-25 14:07
 */
@Service
public class HttpService {
    @Autowired
    private RestTemplate restTemplate;

    public String get(String uri){
        return restTemplate.getForObject(uri,String.class);
    }
}
