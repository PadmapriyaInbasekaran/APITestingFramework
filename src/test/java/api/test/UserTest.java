package api.test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;

public class UserTest {
    User userData;

    @BeforeTest
    public void userTest() throws FileNotFoundException {
        userData = new User();
        userData.setId(2);
        userData.setName("abc");
        userData.setJob("abc");


    }

    @Test
    public void testPostUser() throws FileNotFoundException {
        UserEndpoints.createUser(userData);
    }

    @Test(dependsOnMethods = "testPostUser")
    public void testGetUser() throws FileNotFoundException {
        UserEndpoints.getUser(this.userData.getId());

    }


    @Test(dependsOnMethods = "testPostUser")
    public void testUpdateUser() throws FileNotFoundException {
        this.userData.setName("xyz");
        UserEndpoints.updateUser(userData);

    }

    @Test(dependsOnMethods = "testUpdateUser")
    public void testDeleteUser() throws FileNotFoundException {
        UserEndpoints.deleteUser(this.userData.getId());
    }

}