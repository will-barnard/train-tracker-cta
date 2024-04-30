package com.barnard.traintrackerbackend.model;

import java.time.LocalDateTime;

public class RunRequestDto {
    private int trainRun;
    private LocalDateTime start;
    private LocalDateTime end;

    public int getTrainRun() {
        return trainRun;
    }

    public void setTrainRun(int trainRun) {
        this.trainRun = trainRun;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
