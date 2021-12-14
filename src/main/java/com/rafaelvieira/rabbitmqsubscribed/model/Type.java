package com.rafaelvieira.rabbitmqsubscribed.model;

public enum Type {

    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private final String name;

    Type(String value) {
        this.name = value;
    }

    public String value() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
