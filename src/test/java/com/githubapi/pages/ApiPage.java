package com.githubapi.pages;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import java.io.IOException;
import java.util.Map;

import com.githubapi.framework.webdriver.WebDriverBase;

public class ApiPage extends BasePage{
	
	private Response response = null;	

	public String getApiBaseUrl() throws IOException {
		String baseUrl = WebDriverBase.appProperties().getProperty("api_base_url");
		return baseUrl;
	}
	
	public Response GetCall(String cookievalue, String endpointUrl) {
		boolean apiCallStatus = true;
		Response response = null;
		try {
			response = given().header("Cookie", cookievalue).contentType("application/json").when().get(endpointUrl);
			return response;
		} catch (Throwable em) {
			em.printStackTrace();
		}
		return response;
	}
	
	public void getStatusCode(String cookievalue, String endpointUrl) {
		Logger = WebDriverBase.getLogger();
		Response response = GetCall(cookievalue, endpointUrl);
		int statuscode = response.statusCode();
		if (response.statusCode() == 200) 
			Logger.pass("Response Status Code : "+response.statusCode());
		else
			Logger.fail("Response Status Code : "+response.statusCode());
	}
	
	public void getEndPointUrlForQuerySearch(Map<String, String> data) {
		Logger = WebDriverBase.getLogger();
		Logger.info("TEST DATA:    "+convertHashmapToString(data));
		String endpoint = data.get("ServiceEndpointURL") + data.get("KeyWord") + "+language:" + data.get("Language")
		+ "&sort=" + data.get("SortBy") + "&order=" + data.get("OrderBy");
		data.replace("ServiceEndpointURL", endpoint);
		Logger.info("End Point Url = "+endpoint);
	}
	
	public void getResponseBody(String cookievalue, String endpointUrl) {
		Logger = WebDriverBase.getLogger();
		Response response = GetCall(cookievalue, endpointUrl);
		String responsebody = response.getBody().asString();
		if(!(responsebody==null))
			Logger.pass("Response  : "+responsebody);
		else
			Logger.fail("No Response");
	}
	
	public void getValueFromResponse(String cookievalue, String endpointUrl, String key) {
		Logger = WebDriverBase.getLogger();
		Response response = GetCall(cookievalue, endpointUrl);
		String value = response.getBody().jsonPath().getString(key);
		if(!(value==null))
			Logger.pass(key+" = "+value);
		else
			Logger.fail("No Response From Api");
	}
	
}
