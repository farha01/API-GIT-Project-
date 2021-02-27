package com.githubapi.scripts;

import java.util.Map;

import org.testng.annotations.Test;

import com.githubapi.framework.webdriver.WebDriverBase;
import com.githubapi.pages.ApiPage;
import com.githubapi.pages.BasePage;

import io.restassured.RestAssured;

public class GithubApiTest extends WebDriverBase {
	
	String uniqueId = BasePage.getuniqueIDByCurrentTime();
	
	@Test(priority = 1, dataProvider = "ApplicationTestData")
	public void searchUserRepositories(Map<String, String> data)
			throws InterruptedException, Exception {
		initiateTestReport("searchUserRepositories");
		ApiPage apiPage = new ApiPage();
		RestAssured.baseURI =apiPage.getApiBaseUrl();
		String cookievalue = "";
		apiPage.getEndPointUrlForQuerySearch(data);
		apiPage.getStatusCode(cookievalue, data.get("ServiceEndpointURL"));
		apiPage.getValueFromResponse(cookievalue, data.get("ServiceEndpointURL"), data.get("OutputKey"));
		apiPage.getResponseBody(cookievalue, data.get("ServiceEndpointURL"));
	}

}
