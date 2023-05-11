package api.endpoints;

import api.payload.User;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class UserEndpoints {
    public static RequestLoggingFilter requestLoggingFilter;
    public static ResponseLoggingFilter responseLoggingFilter;

//    public UserEndpoints() throws FileNotFoundException {
//        OutputStream outputStream =new FileOutputStream("FilteredData.txt"); //use your OutputStream that will write where you need it
//        PrintStream printStream = new PrintStream(outputStream, true);
//        requestLoggingFilter = new RequestLoggingFilter(printStream);
//        responseLoggingFilter = new ResponseLoggingFilter(printStream);
//        }

    public static Response createUser(User Payload) throws FileNotFoundException {

        OutputStream outputStream =new FileOutputStream("PostData.txt",true); //use your OutputStream that will write where you need it
        PrintStream printStream = new PrintStream(outputStream,true);
        requestLoggingFilter = new RequestLoggingFilter(printStream);
        responseLoggingFilter = new ResponseLoggingFilter(printStream);
        Response response = given().log().all()
                .filter(requestLoggingFilter)
                .filter(responseLoggingFilter)
                .contentType("application/json")
                .body(Payload)
                .when()
                .post(Routes.post_url);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 201);
        return response;
    }

    public static Response getUser(int id) throws FileNotFoundException {
        OutputStream outputStream =new FileOutputStream("GetData.txt",true); //use your OutputStream that will write where you need it
        PrintStream printStream = new PrintStream(outputStream,false);
        requestLoggingFilter = new RequestLoggingFilter(printStream);
        responseLoggingFilter = new ResponseLoggingFilter(printStream);
        Response response = given()
                .filter(requestLoggingFilter)
                .filter(responseLoggingFilter)
                // .pathParam("userid",id)
                .when()
                .get(Routes.get_url + id);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        return response;
    }

    public static Response updateUser(User Payload) throws FileNotFoundException {
        OutputStream outputStream =new FileOutputStream("UpdateData.txt",true); //use your OutputStream that will write where you need it
        PrintStream printStream = new PrintStream(outputStream,false);
        requestLoggingFilter = new RequestLoggingFilter(printStream);
        responseLoggingFilter = new ResponseLoggingFilter(printStream);
        Response response = given().log().all()
                .filter(requestLoggingFilter)
                .filter(responseLoggingFilter)
                .contentType("application/json")
                .body(Payload)
                .when()
                .put(Routes.put_url + Payload.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        return response;
    }

    public static Response deleteUser(int id) throws FileNotFoundException {
        OutputStream outputStream =new FileOutputStream("DeleteData.txt",true); //use your OutputStream that will write where you need it
        PrintStream printStream = new PrintStream(outputStream,false);
        requestLoggingFilter = new RequestLoggingFilter(printStream);
        responseLoggingFilter = new ResponseLoggingFilter(printStream);
        Response response = given()
                .filter(requestLoggingFilter)
                .filter(responseLoggingFilter)
//                .pathParam("userid",id)
                .when()
                .delete(Routes.delete_url + id);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 204);
        return response;
    }

}
