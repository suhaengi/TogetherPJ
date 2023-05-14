/*
package com.together.togetherpj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@RestController
public class KakaoMapController2 {

    private final RestTemplate restTemplate;

    @Autowired
    public KakaoMapController2(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/map/test2map")
    public String showMap(Model model) {
        final String uri = "https://dapi.kakao.com/v2/local/search/category.json?categories=AT4,AD5,FD6,CE7&rect=127.1054321,37.3360453,127.1354321,37.3560453";

        String REST_API_KEY = "e57cfb39f51e9c4b332ee7564881ebd7";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + REST_API_KEY);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            String result = response.getBody();
            model.addAttribute("touristAttractions", result);
        } catch (RestClientException e) {
            // Handle RestClientException
            e.printStackTrace();
        }

        return "map/test2map";
    }

}


}
*/
