package integration;

import com.example.moneytransferapp.MoneyTransferApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MoneyTransferApplication.class)
@Testcontainers
class IntegrationTest {
    public static final String IMAGE_NAME = "money-transfer-service:latest";
    public static final int PORT = 9000;
    TestRestTemplate restTemplate = new TestRestTemplate();
    private static final GenericContainer<?> myApp = new GenericContainer<>(IMAGE_NAME)
            .withExposedPorts(PORT);

    @BeforeAll
    public static void setUp() {
        myApp.start();
    }

    @Test
    void contextLoads() {

        String fooResourceUrl
                = "http://localhost:" + myApp.getMappedPort(PORT);
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl + "/transfer", String.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
