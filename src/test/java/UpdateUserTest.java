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


   @Epic("")
    @Feature("")
    public class UpdateUserTest {

        private final String BASE_URL = "https://reqres.in/api/users/2";
        private final ObjectMapper objectMapper = new ObjectMapper();

        @Story("")
        @Severity(SeverityLevel.CRITICAL)
        @Test
        public void testUpdateUserPut() throws JsonProcessingException, com.fasterxml.jackson.core.JsonProcessingException {
            UserCredetials user = new UserCredetials("morpheus");

            step("Отправка PUT-запроса");
            Response response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(user)
                    .when()
                    .put(BASE_URL)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();



            step("Десериализация JSON-ответа в объект UserModelResponse");
            UserModelResponse userModelResponse = objectMapper.readValue(response.asString(), UserModelResponse.class);

            step("Проверяем, что в ответе присутствует createdAt");
            assertNotNull(userModelResponse.getUpdatedAt(), "UpdatedAt не должен быть null");

            step("Проверяем, что имя и работа совпадают с теми, что были отправлены");
            assertEquals(user.getName(), userModelResponse.getName(), "Имя пользователя не совпадает");

        }


       @Story("")
       @Severity(SeverityLevel.CRITICAL)
       @Test
       public void testUpdateUserPatch() throws JsonProcessingException, com.fasterxml.jackson.core.JsonProcessingException {
           UserCredetials user = new UserCredetials("morpheus");

           step("Отправка PATCH-запроса");
           Response response = RestAssured
                   .given()
                   .contentType(ContentType.JSON)
                   .body(user)
                   .when()
                   .patch(BASE_URL)
                   .then()
                   .statusCode(200)
                   .extract()
                   .response();



           step("Десериализация JSON-ответа в объект UserModelResponse");
           UserModelResponse userModelResponse = objectMapper.readValue(response.asString(), UserModelResponse.class);

           step("Проверяем, что в ответе присутствует createdAt");
           assertNotNull(userModelResponse.getUpdatedAt(), "UpdatedAt не должен быть null");

           step("Проверяем, что имя и работа совпадают с теми, что были отправлены");
           assertEquals(user.getName(), userModelResponse.getName(), "Имя пользователя не совпадает");

       }

    }


