package com.example.democlient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Amount {
    private String value;
    private String precision;
    private String currency;

    public Amount(String value, String precision, String currency) {
        this.value = value;
        this.precision = precision;
        this.currency = currency;
    }
    public Amount() {
    }
}
