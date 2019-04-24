package com.example.httpclient;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.example.entity.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HttpClient {
	private static final String DEFAULT_REQUEST_URL = "http://localhost:8081/feeds/v1/getAllFeeds";
	// Create a new instance of http client which will connect to REST api over
	// network
	private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	private static String REQUEST_URL = System.getenv().get("POST_SERVICE_URL");
	
	

	public static void demoGetRESTAPI() throws Exception {
		try {
			// Define a HttpGet request; You can choose between HttpPost, HttpDelete or
			// HttpPut also.
			// Choice depends on type of method you will be invoking.
			if(REQUEST_URL == null)
			{
				REQUEST_URL = DEFAULT_REQUEST_URL;
			}
			HttpGet getRequest = new HttpGet(REQUEST_URL);

			// Set the API media type in http accept header
			getRequest.addHeader("accept", "application/json");

			// Send the request; It will immediately return the response in HttpResponse
			// object
			HttpResponse response = httpClient.execute(getRequest);

			// verify the valid error code first
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}

			// Now pull back the response object
			HttpEntity httpEntity = response.getEntity();
			String apiOutput = EntityUtils.toString(httpEntity);

			// Lets see what we got from API
			System.out.println(apiOutput); // <Post id="10"><firstName>demo</firstName><lastName>Post</lastName></Post>

			// In realtime programming, you will need to convert this http response to some
			// java object to re-use it.
			// Lets see how to jaxb unmarshal the api response content
			/*
			 * JAXBContext jaxbContext = JAXBContext.newInstance(Post.class); Unmarshaller
			 * jaxbUnmarshaller = jaxbContext.createUnmarshaller(); Post Post = (Post)
			 * jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
			 */
			ObjectMapper objMap = new ObjectMapper();
			List<Post> posts = (List<Post>) objMap.readValue(apiOutput, new TypeReference<List<Post>>() {
			});
			// Verify the populated object
			System.out.println(posts);
		} finally {
			// Important: Close the connect
			httpClient.close();
		}
	}

}
