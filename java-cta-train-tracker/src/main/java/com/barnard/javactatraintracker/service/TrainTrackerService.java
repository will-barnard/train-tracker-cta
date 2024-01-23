package com.barnard.javactatraintracker.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TrainTrackerService {

    private final String BASE_URL = "http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?outputType=JSON&key=3efac3820cd94733b557824997e39048";

    private RestTemplate restTemplate = new RestTemplate();

    public Object getTrainStatus() {
        String url = BASE_URL + "&stpid=30197";
        return restTemplate.getForObject(url, Object.class);
    }


}
