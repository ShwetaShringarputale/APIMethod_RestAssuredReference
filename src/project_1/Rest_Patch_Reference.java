package project_1;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class Rest_Patch_Reference {

	public static void main(String[] args) {
		// Declare the base URL
         RestAssured.baseURI="https://reqres.in/";
         
        // Declare the request string Variable (Request in JSON)
         String requestBody="{\r\n"
         		+ "    \"name\": \"morpheus\",\r\n"
         		+ "    \"job\": \"leader\"\r\n"
         		+ "}";
         JsonPath jspRequest = new JsonPath(requestBody);
         String req_name = jspRequest.getString("name");
         String req_job = jspRequest.getString("job");
         
         LocalDateTime currentdate = LocalDateTime.now();
     	 String expecteddate=currentdate.toString().substring(0, 11);
     	 
        //Declare the Given,When,Then Method
         String responseBody=given().header("Content-Type","application/json").
         body(requestBody).when().patch("api/users/2").
         then().extract().response().asString();
         
         //System.out.println(responseBody);
         //Create an object of json path to parse the response body
         JsonPath jspResponse=new JsonPath(responseBody);
         String res_name = jspResponse.getString("name");
         String res_job = jspResponse.getString("job");
         System.out.println(responseBody);
         System.out.println(res_name);
         System.out.println(res_job);
         
        //System.out.println(res_job);
         String res_updatedAt = jspResponse.getString("updatedAt");
         res_updatedAt = res_updatedAt.substring(0,11);
         
         //
         Assert.assertEquals(res_name,req_name);
         Assert.assertEquals(res_job, req_job);
         Assert.assertEquals(res_updatedAt, expecteddate);
         
	}
         

}
