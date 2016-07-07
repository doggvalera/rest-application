package lv.ctco.student;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class StudentControllerTest {

    public static final int OK = HttpStatus.OK.value();
    public static final int NOT_FOUND = HttpStatus.NOT_FOUND.value();
    public static final int CREATED = HttpStatus.CREATED.value();
    public static final int INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR.value();

    @Test
    public void testStudents() throws Exception {
        get("/students").then().statusCode(OK);
    }

    @Test
    public void testGetNotFound() {
        get("/something").then().statusCode(NOT_FOUND);
    }

    @Test
    public void testStudentById() throws Exception {
        get("/students/1").then().statusCode(OK);
        get("/students/1345").then().statusCode(NOT_FOUND);

    }

    @Test
    public void testStudentRemoveAll() throws Exception {
    }

    @Test
    public void testStudentDeleteById() throws Exception {

    }

    @Test
    public void testStudentsPost() throws Exception {

    }

    @Test
    public void testStudentChangeById() throws Exception {

    }

    @Test
    public void testPostOK() {
    }
}