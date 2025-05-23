package com.example.sistemas.ovasistemaso.dto;

public class SJFRequest {

    private int[] ids;
    private int[] arrivals;
    private int[] bursts;

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public int[] getArrivals() {
        return arrivals;
    }

    public void setArrivals(int[] arrivals) {
        this.arrivals = arrivals;
    }

    public int[] getBursts() {
        return bursts;
    }

    public void setBursts(int[] bursts) {
        this.bursts = bursts;
    }
}
