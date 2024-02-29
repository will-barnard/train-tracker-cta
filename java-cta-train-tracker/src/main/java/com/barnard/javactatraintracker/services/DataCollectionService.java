package com.barnard.javactatraintracker.services;

import com.barnard.javactatraintracker.model.ArrivalResult;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DataCollectionService {

    private final String BASE_URL = "http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?outputType=JSON&key=3efac3820cd94733b557824997e39048";
    private RestTemplate restTemplate = new RestTemplate();

    public ArrivalResult getTest() {
        String url = BASE_URL + "&stpid=30197";
        return restTemplate.getForObject(url, ArrivalResult.class);
    }

}
