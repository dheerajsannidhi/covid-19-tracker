package com.dheeraj.coronavirustracker.model;

public class CoronaModel {

    private String state;
    private String country;
    private int totalCases;
    private int diffCases;

    public int getDiffCases() {
        return diffCases;
    }

    public void setDiffCases(int diffCases) {
        this.diffCases = diffCases;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    @Override
    public String toString() {
        return "CoronaModel{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", totalCases=" + totalCases +
                '}';
    }
}
