package com.barnard.javactatraintracker.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.List;

public class Ctatt {

    @JsonDeserialize
    private LocalDateTime tmst;
    private int errCd;
    private String errNM;
    private List<Arrival> eta;

    public LocalDateTime getTmst() {
        return tmst;
    }

    public void setTmst(LocalDateTime tmst) {
        this.tmst = tmst;
    }

    public int getErrCd() {
        return errCd;
    }

    public void setErrCd(int errCd) {
        this.errCd = errCd;
    }

    public String getErrNM() {
        return errNM;
    }

    public void setErrNM(String errNM) {
        this.errNM = errNM;
    }

    public List<Arrival> getEta() {
        return eta;
    }

    public void setEta(List<Arrival> eta) {
        this.eta = eta;
    }
}
