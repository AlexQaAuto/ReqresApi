import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.SingleResourceResponse;
import org.junit.jupiter.api.Test;


public class GetSingleResourceTest {
    private final String BASE_URL = "https://reqres.in/api/unknown";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetSingleResourceTest() throws Exception {
        //Отправка GET-запроса
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/2")
                .then()
                .statusCode(200)
                .extract()
                .response();

        //Десериализация JSON-ответа в объект SingleResourceResponse
        SingleResourceResponse singleResourceResponse = objectMapper.readValue(response.asString(), SingleResourceResponse.class);

    }

}