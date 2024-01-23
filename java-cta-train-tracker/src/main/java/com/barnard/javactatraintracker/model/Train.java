package com.barnard.javactatraintracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Train {

    private String staNm;
    private String stpDe;
    private String rn;
    private String rt;
    private String destNm;
    // train direction
    private String trDr;
    // time of prediction
    @JsonFormat(pattern="yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime prdt;
    // time of arrival
    @JsonFormat(pattern="yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime arrT;
    // is approaching or due
    private String isApp;
    // is live or scheduled
    private String isSch;
    // potential fault
    private String isFlt;
    // is delayed
    private String isDly;

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

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String getRt() {
        return rt;
    }

    public void setRt(String rt) {
        this.rt = rt;
    }

    public String getDestNm() {
        return destNm;
    }

    public void setDestNm(String destNm) {
        this.destNm = destNm;
    }

    public String getTrDr() {
        return trDr;
    }

    public void setTrDr(String trDr) {
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

    public String getIsApp() {
        return isApp;
    }

    public void setIsApp(String isApp) {
        this.isApp = isApp;
    }

    public String getIsSch() {
        return isSch;
    }

    public void setIsSch(String isSch) {
        this.isSch = isSch;
    }

    public String getIsFlt() {
        return isFlt;
    }

    public void setIsFlt(String isFlt) {
        this.isFlt = isFlt;
    }

    public String getIsDly() {
        return isDly;
    }

    public void setIsDly(String isDly) {
        this.isDly = isDly;
    }
}
