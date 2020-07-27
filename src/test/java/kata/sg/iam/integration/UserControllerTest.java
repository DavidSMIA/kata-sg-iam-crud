package kata.sg.iam.integration;


import io.restassured.RestAssured;
import kata.sg.iam.database.AbstractDatabaseTest;
import kata.sg.iam.model.dto.user.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest extends AbstractDatabaseTest {

    @LocalServerPort
    private int port;

    private String uri;

    @PostConstruct
    public void init() {
        uri = "https://localhost:" + port;
    }

    @Test
    public void get_employee_should_return_status_200() {
       RestAssured.given().relaxedHTTPSValidation().
                when().
                get(uri + "/api/v1/employees").
                then().
                assertThat().
                statusCode(200).
                extract().
                asString();

    }

    @Test
    public void get_unknown_employee_should_return_status_404() {
        String body = RestAssured.given().relaxedHTTPSValidation().
                when().
                get(uri + "/api/v1/employees/1").
                then().
                assertThat().
                statusCode(404).
                extract().
                asString();

        assertThat(body).isEqualTo("Could not find employee 1");
    }

    @Test
    public void create_employee() {
        EmployeeDTO expectedEmployee = EmployeeDTO.builder()
                .id("123456")
                .firstname("new")
                .lastname("employee")
                .build();

        EmployeeDTO actualEmployee = RestAssured.given().
                relaxedHTTPSValidation().
                contentType("application/json").
                body(expectedEmployee).
                when().
                post(uri + "/api/v1/employees").
                then().
                assertThat().
                statusCode(HttpStatus.OK.value()).
                extract().
                as(EmployeeDTO.class);

        assertThat(actualEmployee).isEqualTo(expectedEmployee);
    }

}
