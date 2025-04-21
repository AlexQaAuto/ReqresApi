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
import static org.junit.jupiter.api.Assertions.*;


@Epic("Работа с пользователями")
@Feature("Создание пользователя")
public class CreateUserTest {

    private final String BASE_URL = "https://reqres.in/api/users";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Story("Создание пользователя только с работой")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testCreateUserWithoutJob() throws JsonProcessingException, com.fasterxml.jackson.core.JsonProcessingException {
        UserCredetials user = new UserCredetials("leader");

        step("Отправка POST-запроса");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201)
                .extract()
                .response();


        step("Десериализация JSON-ответа в объект UserModelResponse");
        UserModelResponse userModelResponse = objectMapper.readValue(response.asString(), UserModelResponse.class);

        step("Проверяем, что в ответе присутствуют id и createdAt");
        assertNotNull(userModelResponse.getId(), "ID не должен быть null");
        assertNotNull(userModelResponse.getCreatedAt(), "createdAt не должен быть null");

        step("Проверяем, что работа совпадает с тем, что были отправлено");
        assertEquals(user.getJob(), userModelResponse.getJob(), "Профессия пользователя не совпадает");
    }



    @Story("Создание пользователя только с работой и именем")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void testCreateUserWithNameJob() throws JsonProcessingException, com.fasterxml.jackson.core.JsonProcessingException {
        UserCredetials user = new UserCredetials("morpheus", "leader");

        step("Отправка POST-запроса");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201)
                .extract()
                .response();


        step("Десериализация JSON-ответа в объект UserModelResponse");
        UserModelResponse userModelResponse = objectMapper.readValue(response.asString(), UserModelResponse.class);

        step("Проверяем, что в ответе присутствуют id и createdAt");
        assertNotNull(userModelResponse.getId(), "ID не должен быть null");
        assertNotNull(userModelResponse.getCreatedAt(), "createdAt не должен быть null");

        step("Проверяем, что имя и работа совпадают с теми, что были отправлены");
        assertEquals(user.getName(), userModelResponse.getName(), "Имя пользователя не совпадает");
        assertEquals(user.getJob(), userModelResponse.getJob(), "Профессия пользователя не совпадает");
    }
}
