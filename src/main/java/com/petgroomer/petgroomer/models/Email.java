package com.petgroomer.petgroomer.models;

import lombok.Data;

@Data
public class Email {

    private String address;
    private String subject;
    private String body;
}
