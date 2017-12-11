package com.vibe.app.dto;

import com.vibe.app.validation.DateConstraint;
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
    @DateConstraint
    private String dob;
}
