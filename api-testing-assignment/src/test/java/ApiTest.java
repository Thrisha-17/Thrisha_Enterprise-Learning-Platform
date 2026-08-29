import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

public class ApiTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    // Test 1: GET request - verify status code and response body
    @Test
    void testGetPostById() {
        given()
            .pathParam("id", 1)
        .when()
            .get("/posts/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("userId", notNullValue())
            .body("title", notNullValue());
    }

    // Test 2: GET all posts - verify list is returned
    @Test
    void testGetAllPosts() {
        given()
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    // Test 3: POST request - create new resource
    @Test
    void testCreatePost() {
        String requestBody = """
                {
                    "title": "Infosys Internship",
                    "body": "Testing API using RestAssured",
                    "userId": 5
                }
                """;

        given()
            .header("Content-Type", "application/json")
            .body(requestBody)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("Infosys Internship"))
            .body("userId", equalTo(5));
    }

    // Test 4: PUT request - update existing resource
    @Test
    void testUpdatePost() {
        String requestBody = """
                {
                    "id": 1,
                    "title": "Updated Title",
                    "body": "Updated body content",
                    "userId": 1
                }
                """;

        given()
            .header("Content-Type", "application/json")
            .body(requestBody)
        .when()
            .put("/posts/1")
        .then()
            .statusCode(200)
            .body("title", equalTo("Updated Title"));
    }

    // Test 5: DELETE request
    @Test
    void testDeletePost() {
        given()
        .when()
            .delete("/posts/1")
        .then()
            .statusCode(200);
    }

    // Test 6: Negative test - invalid endpoint should return 404
    @Test
    void testInvalidPostReturns404() {
        given()
        .when()
            .get("/posts/9999999")
        .then()
            .statusCode(404);
    }
}
