package com.barnard.javactatraintracker.model;

import java.time.LocalDateTime;

public class Arrival {

    private int arrivalId;
    private int staId;
    private int stpId;
    private String staNm;
    private String stpDe;
    private int rn;
    private String rt;
    private int destSt;
    private String destNm;
    private int trDr;
    private LocalDateTime prdt;
    private LocalDateTime arrT;
    private int isApp;
    private int isSch;
    private int isDly;
    private int isFlt;
    // flags not currently in use
    private String flags;
    private double lat;
    private double lon;
    private int heading;
    private int trainRunId;

    public int getStaId() {
        return staId;
    }

    public void setStaId(int staId) {
        this.staId = staId;
    }

    public int getStpId() {
        return stpId;
    }

    public void setStpId(int stpId) {
        this.stpId = stpId;
    }

    public String getStaNm() {
        return staNm;
    }

    public void setStaNm(String staNm) {
        this.staNm = staNm;
    }

    public String getStpDe() {
        return stpDe;
    }

    public void setStpDe(String stpDe) {
        this.stpDe = stpDe;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public int getDestSt() {
        return destSt;
    }

    public void setDestSt(int destSt) {
        this.destSt = destSt;
    }

    public String getDestNm() {
        return destNm;
    }

    public void setDestNm(String destNm) {
        this.destNm = destNm;
    }

    public int getTrDr() {
        return trDr;
    }

    public void setTrDr(int trDr) {
        this.trDr = trDr;
    }

    public LocalDateTime getPrdt() {
        return prdt;
    }

    public void setPrdt(LocalDateTime prdt) {
        this.prdt = prdt;
    }

    public LocalDateTime getArrT() {
        return arrT;
    }

    public void setArrT(LocalDateTime arrT) {
        this.arrT = arrT;
    }

    public int getIsApp() {
        return isApp;
    }

    public void setIsApp(int isApp) {
        this.isApp = isApp;
    }

    public int getIsSch() {
        return isSch;
    }

    public void setIsSch(int isSch) {
        this.isSch = isSch;
    }

    public int getIsDly() {
        return isDly;
    }

    public void setIsDly(int isDly) {
        this.isDly = isDly;
    }

    public int getIsFlt() {
        return isFlt;
    }

    public void setIsFlt(int isFlt) {
        this.isFlt = isFlt;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public int getArrivalId() {
        return arrivalId;
    }

    public void setArrivalId(int arrivalId) {
        this.arrivalId = arrivalId;
    }

    public int getTrainRunId() {
        return trainRunId;
    }

    public void setTrainRunId(int trainRunId) {
        this.trainRunId = trainRunId;
    }
}
