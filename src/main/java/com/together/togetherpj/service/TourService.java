package com.together.togetherpj.service;

import com.together.togetherpj.api.TourApiClient;
import org.springframework.stereotype.Service;

@Service
public class TourService {
    private final TourApiClient tourApiClient;

    public TourService(TourApiClient tourApiClient) {
        this.tourApiClient = tourApiClient;
    }

    public String getTourList() {
        return tourApiClient.getTourList();
    }
}
