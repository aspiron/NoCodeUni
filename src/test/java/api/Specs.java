package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specs {
    public static RequestSpecification requestSpec (String url, String apiKey, String domain){
        return new RequestSpecBuilder()
                .setUrlEncodingEnabled(false)
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .addHeader("Softr-Api-Key", apiKey)
                .addHeader("Softr-Domain", domain)
                .build();
    }

    public static ResponseSpecification responseSpec200 (){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpec400 (){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }

    public static ResponseSpecification responseSpecUnique (int status){
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }
}
