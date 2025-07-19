package org.example.test_atipera.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnNonForkedRepositoriesWithBranches() {
        String username = "MCMisha";

        ResponseEntity<String> response = restTemplate.getForEntity("/api/github/" + username, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("DigitalTools");
    }

    @Test
    void shouldReturnNotFoundForInvalidUser() {
        String username = "this-user-does-not-exist-1234567890";

        ResponseEntity<String> response = restTemplate.getForEntity("/api/github/" + username, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).contains("status").contains("message");
    }
}
