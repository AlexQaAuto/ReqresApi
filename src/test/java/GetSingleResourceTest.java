import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.SingleResourceResponse;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;


@Epic("Работа с ресурсами")
@Feature("Получение доступа к ресурсу")
public class GetSingleResourceTest {
    private final String BASE_URL = "https://reqres.in/api/unknown";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Story("Получение доступа ко всем ресурсам")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testGetSingleResourceTest() throws Exception {

        step("Отправка GET-запроса");
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/2")
                .then()
                .statusCode(200)
                .extract()
                .response();


        step("Десериализация JSON-ответа в объект SingleResourceResponse");
        SingleResourceResponse singleResourceResponse = objectMapper.readValue(response.asString(), SingleResourceResponse.class);

    }

}