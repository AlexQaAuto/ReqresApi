import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.*;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.UserCredetials;
import models.UserModelResponse;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@Epic("Работа с пользователями")
@Feature("Удаление пользователя")
public class DeleteUserTest {

    private final String BASE_URL = "https://reqres.in/api/users/2";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Story("Удаление пользователя")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCreateUserWithNameJob() throws JsonProcessingException, com.fasterxml.jackson.core.JsonProcessingException {
        UserCredetials user = new UserCredetials("morpheus", "leader");

        step("Отправка DELETE-запроса");
        Response response = RestAssured
                .given()
                .when()
                .delete(BASE_URL)
                .then()
                .statusCode(204)
                .extract()
                .response();


    }
}
