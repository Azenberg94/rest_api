package com.example.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ConfigPropertiesITTests extends AbstractControllerTests {

    @Test
    public void should_get_all_properties(){
        when()
                .get("/properties")
        .then()
                .statusCode(200)
                .body("name", is("Awesome"))
                .body("server", notNullValue())
                .body("server.host", is("localhost"))
                .body("server.port", is(80));
    }
}
