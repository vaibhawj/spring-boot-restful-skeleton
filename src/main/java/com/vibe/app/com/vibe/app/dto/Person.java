package com.vibe.app.com.vibe.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @NotNull
    private String name;

    @NotNull
    private String dob;
}
