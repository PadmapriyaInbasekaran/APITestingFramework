package api.endpoints;

import api.payload.User;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndpoints2 {

    static ResourceBundle getURL()
    {
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }
    public static Response createUser(User Payload)

    {
        String post_url = getURL().getString("post_url");
       Response response =  given().log().all()
                .contentType("application/json")
                .body(Payload)
                .when()
                .post(post_url);
               return response;
    }

    public static Response getUser(int id)
    {
        String get_url = getURL().getString("get_url");
        Response response =  given()
               // .pathParam("userid",id)
                .when()
                .get(get_url);
        return response;
    }

    public static Response updateUser(User Payload)
    {
        String put_url = getURL().getString("update_url");
        Response response =  given().log().all()
                .contentType("application/json")
                .body(Payload)
                .when()
                .put(put_url);
        return response;
    }

    public static Response deleteUser(int id)
    {
        String delete_url = getURL().getString("delete_url");
        Response response =  given()
//                .pathParam("userid",id)
                .when()
                .delete(delete_url);
        return response;
    }

}
