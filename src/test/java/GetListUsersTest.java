import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserData;
import models.ResourceResponse;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Работа со списком пользователей")
@Feature("Получение списка пользователей")
public class GetListUsersTest {

    private final String BASE_URL = "https://reqres.in/api/users";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Story("Получение списка всех пользователей")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testGetListUsers() throws Exception {

        step("Отправка GET-запроса");
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();

        step("Десериализация JSON-ответа в объект ResourceResponse");
        ResourceResponse resourceResponse = objectMapper.readValue(response.asString(), ResourceResponse.class);

        step("Проверяем, что количество пользователей равно 6");
        assertEquals(6, resourceResponse.getData().size(), "Количество пользователей не совпадает");
        assertEquals(1, resourceResponse.getPage(), "Неверная страница");
        assertEquals(2, resourceResponse.getTotal_pages(), "Неверное общее количество страниц");
        assertEquals(12, resourceResponse.getTotal(), "Неверное количество страниц");


        step("Проверяем, что email каждого пользователя оканчивается на @regres.in");
        for (UserData user : resourceResponse.getData()) {
            assertTrue(user.getEmail().endsWith("@reqres.in"), "Email пользователя " + user.getId() + " не оканчивается на @reqres.in");
        }
    }

}
