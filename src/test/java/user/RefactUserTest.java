package user;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import user.models.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class RefactUserTest {
    @Test
    public void anverifyUserByName(){
        new UserEndPoint()
                .getUserByName("Andrii")
                .then()
                .assertThat()
                .log().body()
                .body(Matchers.notNullValue());
    }
    @Test
    public  void bverifyStatusCodeByName(){
        new UserEndPoint()
                .getUserByName("Andrii")
                .then()
                .log().status()
                .statusCode(200);
    }

    @Test
    public void cverifyIdIsNotEmpty(){
        Response userResponse = new UserEndPoint()
                .getUserByName("Andrii");
        User user = userResponse
                .body()
                .as(User.class);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void dverifyDeleteUser(){
        new UserEndPoint()
                .deleteByName("Andrii")
                .then()
                .log().status()
                .statusCode(200);
    }

    @Test
    public void everifyNotExistingPetReturn404() {
        new UserEndPoint()
                .getUserByName("Andrii")
                .then()
                .log().body()
                .statusCode(404);
    }
}
