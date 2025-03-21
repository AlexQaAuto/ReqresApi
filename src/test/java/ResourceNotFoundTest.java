import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;



@Epic("Работа с ресурсами")
@Feature("Отсутствие ресурса")
public class ResourceNotFoundTest {
    private final String BASE_URL = "https://reqres.in/api/unknown";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Story("Отсутствие ресурса у всех пользователей")
    @Test
    @Severity(SeverityLevel.MINOR)
    public void testResourceNotFound() throws Exception {

        step("Отправка GET-запроса");
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/99999999")
                .then()
                .statusCode(404)
                .extract()
                .response();

        step("Проверяем, что тело ответа пустое + создаем переменную, в которой получаем ответ из response  преобразуем его в строку");
        String responseBody = response.getBody().asString();
        assertEquals("{}", responseBody, "Тело ответа не является пустым");

    }

}