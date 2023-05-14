/*
package com.together.togetherpj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class KakaoMapController {

    @Autowired
    private  RestTemplate restTemplate;

    @GetMapping("/map/test2map")
    public String callExternalApi() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK {e57cfb39f51e9c4b332ee7564881ebd7}");

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("https://dapi.kakao.com/v2/local/search/category.json")
                .queryParam("category_group_code", "AT4")
                .queryParam("page", "1")
                .queryParam("size", "15")
                .queryParam("sort", "accuracy");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        String result = response.getBody();
        return result;
    }
}
*/
