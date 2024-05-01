package com.barnard.javactatraintracker.services;

import com.barnard.javactatraintracker.dao.ArrivalDao;
import com.barnard.javactatraintracker.model.Arrival;
import com.barnard.javactatraintracker.model.ArrivalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GetArrivalService {

    private final String BASE_URL = "http://lapi.transitchicago.com/api/1.0/ttarrivals.aspx?outputType=JSON&key=3efac3820cd94733b557824997e39048";
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private ArrivalDao arrivalDao;

    public ArrivalResult getTest() {
        String url = BASE_URL + "&stpid=30197";
        return restTemplate.getForObject(url, ArrivalResult.class);
    }

    @Scheduled(fixedRate = 30000)
    public void saveArrivalResult() {
        String url = BASE_URL + "&stpid=30197";
        ArrivalResult ar = restTemplate.getForObject(url, ArrivalResult.class);
        for (Arrival arrival : ar.getCtatt().getEta()) {
            arrivalDao.createArrival(arrival);
            System.out.println("saving arrivals");
        }
    }

}
