import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class ResourceNotFoundTest {
    private final String BASE_URL = "https://reqres.in/api/unknown";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testResourceNotFound() throws Exception {
        //Отправка GET-запроса
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/99999999")
                .then()
                .statusCode(404)
                .extract()
                .response();

        //Проверка, что тело ответа пустое
        // Создаем переменную, в которой получаем ответ из response и преобразуем  его в строку
        String responseBody = response.getBody().asString();
        assertEquals("{}", responseBody, "Тело ответа не является пустым");

    }

}