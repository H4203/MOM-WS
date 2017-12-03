package dbpedia;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpHost;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

public class DBpediaSpotlightClient {

	private final static String API_URL = "http://model.dbpedia-spotlight.org/en/annotate/?";
	private static final double CONFIDENCE = 0.3;
	private static final int SUPPORT = 30;
	private static HttpClient client = new HttpClient();

	public List<String> extract(String text) {
		String spotlightResponse;
		GetMethod getMethod = null;
		try {
			getMethod = new GetMethod(API_URL + "confidence=" + CONFIDENCE + "&support="
					+ SUPPORT + "&text=" + URLEncoder.encode(text, "utf-8"));
			System.out.println(getMethod.getQueryString());
			
			getMethod.addRequestHeader(new Header("Accept", "application/json"));
			request(getMethod);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			spotlightResponse = getMethod.getResponseBodyAsString();
			System.out.println(spotlightResponse);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String request(HttpMethod method) {

        String laresponse = null;

        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        

        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            

            // Read the response body.
            byte[] responseBody = method.getResponseBody(); //TODO Going to buffer response body of large or unknown size. Using getResponseBodyAsStream instead is recommended.

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            laresponse = new String(responseBody);

        } catch (HttpException e) {
            
        } catch (IOException e) {
            
        } finally {
            // Release the connection.
            method.releaseConnection();
        }
        return laresponse;
	}
	
	 public static void main(String[] args) {
		 String request = "Adolf Hitler Paris Mexique zubg zeiubvz vijb Youtube";
		 DBpediaSpotlightClient d = new DBpediaSpotlightClient();
		 d.extract(request);
	 }
}
