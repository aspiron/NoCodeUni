package api;

import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {
    private final static String URL = "https://studio-api.softr.io/v1";
    final static String API_KEY = "khIbAyJIU5CIuh1oDuBRx1s49";
    final static String DOMAIN = "jere237.softr.app";

    @Test
    public void createUserTest() {
        Specs.installSpecification(Specs.requestSpec(URL, API_KEY, DOMAIN), Specs.responseSpecUnique(201));

        UserData userReg = new UserData();
        userReg.setEmail("johnr@gmail.com");
        userReg.setFull_name("John Richardson");
        userReg.setPassword("12345678");

        SuccessReg successReg = given()
                .body(userReg)
                .when()
                .post("/api/users")
                .then().log().all()
                .extract().as(SuccessReg.class);

        Assert.assertNotNull(successReg.getFull_name());
        Assert.assertNotNull(successReg.getEmail());

        Assert.assertEquals(userReg.getFull_name(), successReg.getFull_name());
        Assert.assertEquals(userReg.getEmail(), successReg.getEmail());
    }

    @Test
    public void getUserNameTest() {
        Specs.installSpecification(Specs.requestSpec(URL, API_KEY, DOMAIN), Specs.responseSpec200());
        UserData user1 = (UserData) given()
                .when()
                .get("/api/users")
                .then().log().all();

        Assert.assertNotNull(user1.getFull_name());
        Assert.assertEquals(user1.getFull_name(), "John Richardson");
    }

}
