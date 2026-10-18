package ru.kiprin.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Systems {

    ERP("Enterprise Resource Planning"),
    CRM("Customer Relationship Management"),
    WMS("Warehouse Management System"),
    SERVICE_1("Service 1");

    private final String name;

    Systems(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static Systems fromName(String name) {
        for (Systems system : values()) {
            if (system.name.equals(name) || system.name().equals(name)) {
                return system;
            }
        }
        throw new IllegalArgumentException("Unknown system: " + name);
    }

    @Override
    public String toString() {
        return name;
    }
}
