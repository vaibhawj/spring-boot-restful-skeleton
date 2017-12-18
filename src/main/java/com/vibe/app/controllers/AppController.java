package com.vibe.app.controllers;

import com.vibe.app.dto.Person;
import com.vibe.app.dto.PersonResponse;
import com.vibe.app.util.Helper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Api(value = "API", description = "API for greet")
@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/greet/person", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Greet the person and tell his/her age",
            produces = "application/json",
            httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Fields are with validation errors"),
            @ApiResponse(code = 202, message = "")})
    public @ResponseBody
    ResponseEntity<PersonResponse> greetPerson(@RequestBody @Valid @ApiParam(name = "body", value = "body") Person p, BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<>(new PersonResponse(result.getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
        }

        PersonResponse response = new PersonResponse();
        String dob = p.getDob();
        LocalDate dobDate = LocalDate.parse(dob, Helper.getFormatter(environment.getProperty("date.format")));
        LocalDate currentDate = LocalDate.now();
        long age = ChronoUnit.YEARS.between(dobDate, currentDate);
        response.setMessage("Hello " + p.getName() + ", your age is " + age);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
