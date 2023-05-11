package api.test;
import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserTest2 {
    User userData;

    @BeforeTest
    public void userTest() {
        userData = new User();
        userData.setId(2);
        userData.setName("abc");
        userData.setJob("abc");
    }

    @Test
    public void testPostUser() {
        Response response = UserEndpoints2.createUser(userData);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test(dependsOnMethods = "testPostUser")
    public void testGetUser() {
        Response response = UserEndpoints2.getUser(this.userData.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test(dependsOnMethods = "testPostUser")
    public void testUpdateUser() {
        this.userData.setName("xyz");
        Response response = UserEndpoints2.updateUser(userData);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(dependsOnMethods = "testUpdateUser")
    public void testDeleteUser() {
        Response response = UserEndpoints2.deleteUser(this.userData.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}