package com.vibe.app.controllers;

import com.vibe.app.dto.Person;
import com.vibe.app.dto.PersonResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import static com.vibe.app.util.Helper.formatter;

@Api(value = "API", description = "API for greet")
@RestController
@RequestMapping("/api")
public class AppController {

    @RequestMapping(value = "/greet/person", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Greet the person and tell his/her age", notes = "Enter dob in MM/dd/yyyy format",
            produces = "application/json",
            httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Fields are with validation errors"),
            @ApiResponse(code = 201, message = "")})
    public @ResponseBody
    ResponseEntity<PersonResponse> greetPerson(@RequestBody @Valid @ApiParam(name = "body", value = "body") Person p, BindingResult result) {

        if(result.hasErrors()){
            return new ResponseEntity<PersonResponse>(new PersonResponse("Invalid input"), HttpStatus.BAD_REQUEST);
        }

        PersonResponse response = new PersonResponse();
        String dob = p.getDob();
        formatter.withLocale(Locale.ENGLISH);
        LocalDate dobDate = LocalDate.parse(dob, formatter);
        LocalDate currentDate = LocalDate.now();
        long age = ChronoUnit.YEARS.between(dobDate, currentDate);
        response.setMessage("Hello " + p.getName() + ", your age is " + age);

        return new ResponseEntity<PersonResponse>(response, HttpStatus.ACCEPTED);
    }
}
