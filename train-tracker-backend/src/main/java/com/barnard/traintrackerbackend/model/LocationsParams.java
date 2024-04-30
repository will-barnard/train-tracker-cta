package com.barnard.traintrackerbackend.model;

import java.util.List;

public class LocationsParams {

    //list of routes
    private List<String> rt;
    private String requestParams;

    public List<String> getRt() {
        return rt;
    }

    public void setRt(List<String> rt) {
        this.rt = rt;
    }

    public String getRequestParams() {
        mapParamsToString();
        return requestParams;
    }

    private void mapParamsToString() {
        if (this.rt != null) {
            this.requestParams = "&rt=" + rt;
        }
    }
}
