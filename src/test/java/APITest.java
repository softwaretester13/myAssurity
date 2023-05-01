import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APITest {

    @Test
    public void test_Name_Should_Be_HomeAndGarden() {
                boolean setAnswser = false;

                given().
                queryParam("catalogue",setAnswser).
                when().get("https://api.tmsandbox.co.nz/v1/Categories/6329/Details.json").
                then().
                body("Name",equalTo("Home & garden"));
    }

    @Test
    public void test_CanReList_Should_Be_True() {
        boolean setAnswser = false;

                given().
                queryParam("catalogue",setAnswser).
                when().get("https://api.tmsandbox.co.nz/v1/Categories/6329/Details.json").
                then().
                body("CanRelist",equalTo(true));
    }


    @Test
    public void test_Promotions_Name_Should_Be_Feature() {

        RestAssured.baseURI = "https://api.tmsandbox.co.nz/v1";
        RequestSpecification httpRequest = given();
        Response res = httpRequest.queryParam("catalogue","false").get("/Categories/6329/Details.json");
        ResponseBody body = res.body();
        String responseBody = body.asString();

        JSONObject obj = new JSONObject(responseBody);
        JSONArray arr = obj.getJSONArray("Promotions");
        assert (arr.getJSONObject(2).getString("Name").equals("Feature"));
    }

    @Test
    public void test_Promotions_Description_Should_Be_BetterPositionInCategory() {

        RestAssured.baseURI = "https://api.tmsandbox.co.nz/v1";
        RequestSpecification httpRequest = given();
        Response res = httpRequest.queryParam("catalogue","false").get("/Categories/6329/Details.json");
        ResponseBody body = res.body();
        String responseBody = body.asString();

        JSONObject obj = new JSONObject(responseBody);
        JSONArray arr = obj.getJSONArray("Promotions");
        assert (arr.getJSONObject(2).getString("Description").equals("Better position in category"));
    }

}
