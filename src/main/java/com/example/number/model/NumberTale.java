package com.example.number.model;

public class NumberTale {

    private Integer id;
    private int number;
    private int request;
    private String description;
    private int latency;

    public NumberTale() {
    }

    public NumberTale(Integer id, int number, int request, String description, int latency) {
        this.id = id;
        this.number = number;
        this.request = request;
        this.description = description;
        this.latency = latency;
    }

    public NumberTale(int number, int request, String description, int latency) {
        this.number = number;
        this.request = request;
        this.description = description;
        this.latency = latency;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLatency() {
        return latency;
    }

    public void setLatency(int latency) {
        this.latency = latency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NumberTale that = (NumberTale) o;
        return this.id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }

    @Override
    public String toString() {
        return "NumberTale{" +
                "id=" + id +
                ", number=" + number +
                ", request=" + request +
                ", description='" + description + '\'' +
                ", latency=" + latency +
                '}';
    }
}
