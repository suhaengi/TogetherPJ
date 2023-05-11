package com.together.togetherpj.api;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TourApiClient {

    private final String BASE_URL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/";

    private final String API_KEY = "eThZ9urwXFl7JObboUExL3GaPJrIG9v7aQGH5yrw9VAHYf0YEE1MU4AQMDU11Kd9HYIHqHorghq%2FmNISxvm3uw%3D%3D";

    private RestTemplate restTemplate;

    public TourApiClient() {
        this.restTemplate = new RestTemplate();
    }

    public String getTourList() {
        String url = BASE_URL + "areaBasedList";
        String queryParams = "?ServiceKey=" + API_KEY + "&areaCode=1&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest";

        String response = restTemplate.getForObject(url + queryParams, String.class);

        return response;
    }
}