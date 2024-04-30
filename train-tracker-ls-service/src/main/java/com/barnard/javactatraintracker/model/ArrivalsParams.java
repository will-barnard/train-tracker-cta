package com.barnard.javactatraintracker.model;

public class ArrivalsParams {

    // map id param
    private String mapid;
    // stop id param
    private String stpid;
    // max results
    private String max;
    // route code ( for stations with multiple train lines )
    private String rt;
    // request params
    private String requestParams;

    public String getMapid() {
        return mapid;
    }

    public void setMapid(String mapid) {
        this.mapid = mapid;
    }

    public String getStpid() {
        return stpid;
    }

    public void setStpid(String stpid) {
        this.stpid = stpid;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getRequestParams() {
        mapParamsToString();
        return requestParams;
    }

    private void mapParamsToString() {

        String result = "";

        if (this.mapid != null) {
            result += "&mapid=" + mapid;
        }
        if (this.stpid != null) {
            result += "&stpid=" + stpid;
        }
        if (this.rt != null) {
            result += "&rt=" + rt;
        }
        if (this.max != null) {
            result += "&max=" + max;
        }

        this.requestParams = result;

    }
}
