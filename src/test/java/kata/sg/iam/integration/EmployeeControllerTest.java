package kata.sg.iam.integration;


import io.restassured.RestAssured;
import kata.sg.iam.database.AbstractDatabaseTest;
import kata.sg.iam.model.dto.user.EmployeeDTO;
import kata.sg.iam.model.dto.user.EmployeePayload;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EmployeeControllerTest extends AbstractDatabaseTest {

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
        String uuid = "7e9c3828-1cc9-4713-9cb2-e566b6b87d75";
        String body = RestAssured.given().relaxedHTTPSValidation().
                when().
                get(uri + "/api/v1/employees/"+uuid).
                then().
                assertThat().
                statusCode(404).
                extract().
                asString();

        assertThat(body).isEqualTo("Could not find employee "+uuid);
    }

    @Test
    public void create_employee() {
        EmployeePayload expectedEmployee = EmployeePayload.builder()
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

        assertThat(actualEmployee.getFirstname()).isEqualTo(expectedEmployee.getFirstname());
        assertThat(actualEmployee.getLastname()).isEqualTo(expectedEmployee.getLastname());
    }

}
