package com.example.lifewaydemo.controller;

import com.example.lifewaydemo.dto.PersonCreateDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonsControllerTest {

    @LocalServerPort
    private int port;

    static HttpHeaders headers;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeTestClass
    public static void runBeforeAllTestMethods() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    ResponseEntity<String> helper_createHaggar() throws URISyntaxException {
        PersonCreateDto person = new PersonCreateDto("Haggar", "Horrible");
        person.setEmail("hhorrible@vikings.org");
        person.setPhone("218-224-9764");
        final String baseUrl = "http://localhost:" + port + "/api/persons";
        URI uri = new URI(baseUrl);

        HttpEntity<PersonCreateDto> request = new HttpEntity<>(person, headers);
        return this.restTemplate.postForEntity(uri, request, String.class);
    }

    @Test
    void withValidInput_thenCreatePersonReturns201() throws URISyntaxException {
        ResponseEntity<String> result = this.helper_createHaggar();
        assertEquals(201, result.getStatusCodeValue());
    }

    // TODO: withMissingFirstname_thenCreatePersonReturns500()
    // TODO: withMissingLastname_thenCreatePersonReturns500()

    @Test
    void withValidId_thenGetPersonReturns200() throws URISyntaxException, JsonProcessingException {
        ResponseEntity<String> createResult = this.helper_createHaggar();
        assertEquals(201, createResult.getStatusCodeValue());
        JsonNode jRoot = objectMapper.readTree(createResult.getBody());
        assertNotNull(jRoot);
        assertNotNull(jRoot.path("id").asText());
        String createdId = jRoot.path("id").asText();
        final String baseUrl = "http://localhost:" + port + "/api/persons/" + createdId;
        URI uri = new URI(baseUrl);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> getResponse = this.restTemplate.getForEntity(uri, String.class);
        assertEquals(200, getResponse.getStatusCodeValue());
    }

    // TODO: withInvalidId_thenGetPersonReturns404

    @Test
    @Disabled("TODO")
    void withEmptyData_thenGetPersonsReturns204() {
    }

    @Test
    @Disabled("TODO")
    void withOneEntry_thenGetPersonsReturns200() {
    }

    // TODO: withMultipleEntry_thenGetPersonsReturns200

    @Test
    @Disabled("TODO")
    void withValidId_thenUpdatePersonReturns201() {
    }

    // TODO: withInvalidId_thenUpdatePersonReturns404

    @Test
    @Disabled("TODO")
    void withValidId_thenDeletePersonReturns204 () {
    }

    // TODO: withInvalidId_thenDeletePersonReturns500

}