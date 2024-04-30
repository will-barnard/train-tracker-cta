package com.barnard.javactatraintracker.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.List;

public class ArrivalResult {

    private Ctatt ctatt;

    public Ctatt getCtatt() {
        return ctatt;
    }

    public void setCtatt(Ctatt ctatt) {
        this.ctatt = ctatt;
    }
}
