import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.ResourceResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GetListResourceTest {
    private final String BASE_URL = "https://reqres.in/api/unknown";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetListResourceTest() throws Exception {
        //Отправка GET-запроса
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();

        //Десериализация JSON-ответа в объект ResourceResponse
        ResourceResponse usersResponse = objectMapper.readValue(response.asString(), ResourceResponse.class);

        //Проверяем, что количество пользователей равно 6
        assertEquals(6, usersResponse.getData().size(), "Количество пользователей не совпадает");
        assertEquals(1, usersResponse.getPage(), "Неверная страница");
        assertEquals(2, usersResponse.getTotal_pages(), "Неверное общее количество страниц");
        assertEquals(12, usersResponse.getTotal(), "Неверное количество страниц");

    }

}