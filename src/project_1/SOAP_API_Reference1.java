package project_1;
import io.restassured.RestAssured;
import io.restassured.path.xml.*;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

public class SOAP_API_Reference1 {

	public static void main(String[] args) {
		// Declare Base URL
		RestAssured.baseURI = "https://www.dataaccess.com/";
				//Declare Request Body
		String requestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>185</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		//Extract response Body
		String responseBody=given().header("Content-Type","text/xml; charset=utf-8").
		body(requestBody).when().post("webservicesserver/NumberConversion.wso").
		then().extract().response().asString();
		System.out.println(responseBody);
		//Parse the Response Body
		XmlPath res_xml = new XmlPath(responseBody); 
		//String response_Parameter = res_xml.getString("NumberToWordsResult");
		String res_par = res_xml.getString("NumberToWordsResult");
		System.out.println(res_par);
			//Validate the Response Body
		Assert.assertEquals(res_par,"one hundred and eighty five ");
	}
}
