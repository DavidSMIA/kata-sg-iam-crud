package kata.sg.iam.integration;


import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import javax.annotation.PostConstruct;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    private String uri;

    @PostConstruct
    public void init() {
        uri = "http://localhost:" + port;
    }

    @Test
    public void get_users_should_return_status_200() {
        RestAssured.given().
                when().
                get(uri+"/api/v1/users").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void get_user_should_return_status_200() {
        RestAssured.given().
                when().
                get(uri+"/api/v1/users/1").
                then().
                assertThat().
                statusCode(200);
    }

}
