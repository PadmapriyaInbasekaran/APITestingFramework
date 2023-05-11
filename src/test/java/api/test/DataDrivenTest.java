package api.test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.XLDataProvider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class DataDrivenTest {
    User userData;
    @Test(priority = 1, dataProvider = "Data", dataProviderClass = XLDataProvider.class)
    public void testPostUser(String id, String name, String job) throws FileNotFoundException {

        userData = new User();
        userData.setId(Integer.parseInt(id));
        userData.setName(name);
        userData.setJob(job);
        Response response = UserEndpoints.createUser(userData);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 201);
    }

   @Test(priority = 2, dataProvider = "UserId", dataProviderClass = XLDataProvider.class)
    public void testGetUser(String id) throws FileNotFoundException {
       userData = new User();
       userData.setId(Integer.parseInt(id));
        Response response = UserEndpoints.getUser(userData.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3, dataProvider = "UserId", dataProviderClass = XLDataProvider.class)
    public void testDeleteUser(String id) throws FileNotFoundException {
        userData = new User();
        userData.setId(Integer.parseInt(id));
        Response response = UserEndpoints.deleteUser(userData.getId());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
