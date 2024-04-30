package com.barnard.traintrackerbackend.model;

public class FollowThisTrainParams {

    private String runnumber;
    private String requestParams;

    public String getRunnumber() {
        return runnumber;
    }

    public void setRunnumber(String runnumber) {
        this.runnumber = runnumber;
    }

    public String getRequestParams() {
        mapParamsToString();
        return requestParams;
    }

    private void mapParamsToString() {
        if (this.runnumber != null) {
            this.requestParams = "&runnumber=" + runnumber;
        }
    }
}
