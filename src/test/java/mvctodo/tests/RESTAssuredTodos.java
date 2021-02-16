package mvctodo.tests;


import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Test;
import testObject.Todo;

public class RESTAssuredTodos extends FunctionalTest {

  @Test
  public void getTodos() {
    given().when().get("/todos").then().statusCode(200);
  }

  @Test
  public void getTodosWrongURI() {
    given().when().get("/todoos").then().statusCode(404);
  }

  @Test
  public void postNewTodo() {
    Todo todo = new Todo();
    todo.setTitle("Finish tests");
    todo.setCompleted(false);
    todo.setId(1);

    given()
        .contentType("application/json")
        .body(todo)
        .when().post("/todos").then()
        .body("completed", equalTo(false))
        .body("title", equalTo("Finish tests"));

  }

  @Test
  public void deleteTodo() {
    Todo todo = new Todo();
    todo.setTitle("Write the test");
    todo.setCompleted(false);
    todo.setId(5);

    Integer todoId = given()
        .contentType("application/json")
        .body(todo)
        .when().post("/todos").then()
        .body("title", equalTo("Write the test"))
        .extract().path("id");

    given().pathParam("id", todoId)
        .when().delete("/todos/{id}").then()
        .statusCode(200);
  }

  @Test
  public void deleteAllTodos(){
    given().when().delete("/todos").then()
        .statusCode(204);
  }

  @After
  public void close(){
    given().when().delete("/todos");
  }
}
