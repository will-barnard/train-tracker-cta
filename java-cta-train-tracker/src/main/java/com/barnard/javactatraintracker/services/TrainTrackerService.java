package com.barnard.javactatraintracker.services;

import com.barnard.javactatraintracker.model.ArrivalsParams;
import com.barnard.javactatraintracker.model.FollowThisTrainParams;
import com.barnard.javactatraintracker.model.LocationsParams;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TrainTrackerService {

    private final String BASE_URL = "http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?outputType=JSON&key=3efac3820cd94733b557824997e39048";
    private RestTemplate restTemplate = new RestTemplate();

    public Object getArrivals(ArrivalsParams arrivalsParams) {
        String url = BASE_URL + arrivalsParams.getRequestParams();
        return restTemplate.getForObject(url, Object.class);
    }

    public Object getFollowThisTrain(FollowThisTrainParams followThisTrainParams) {
        String url = BASE_URL + followThisTrainParams.getRequestParams();
        return restTemplate.getForObject(url, Object.class);
    }

    public Object getLocations(LocationsParams locationsParams) {
        String url = BASE_URL + locationsParams.getRequestParams();
        return restTemplate.getForObject(url, Object.class);
    }


}
