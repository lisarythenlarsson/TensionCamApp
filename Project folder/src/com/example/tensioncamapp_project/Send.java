package com.example.tensioncamapp_project;

import java.io.ByteArrayOutputStream;
import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;


public class Send {
	public static void main(String[] args) throws Exception {
		String filePath = "C:\\Users\\Martin\\Desktop\\Bild.bmp";//Puts the path to which picture should be analysed 
		//String picName = "Bild.bmp";
		
		HttpClient httpclient = new DefaultHttpClient();//Creates a HttpClient

		try {
			HttpPost httppost = new HttpPost("http://localhost:8080/Analyse/upload"); //Creates a post header

			FileBody pic = new FileBody(new File(filePath)); //Creates a FileBody
			//StringBody name = new StringBody(picName);

			MultipartEntity requestEntity = new MultipartEntity(); //Creates a MultipartEntity
			//requestEntity.addPart("text", name);
			requestEntity.addPart("file", pic);//Adds the picture to the MultipartEntity

			httppost.setEntity(requestEntity);//Sets the entity of the HttpPost

			System.out.println("executing request " + httppost.getRequestLine());//Prints what is requested
			HttpResponse response = httpclient.execute(httppost);//Retrieves the response
			HttpEntity responseEntity = response.getEntity();//Retrieves the entity from the response

			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());//Prints the status line from the request
		/*	if (responseEntity != null) {
				System.out.println("Response content length: " + responseEntity.getContentLength());
			}*/

			
			ByteArrayOutputStream outstream = new ByteArrayOutputStream();//Creates a ByteArrayOutPutStream
			response.getEntity().writeTo(outstream);//Writes the response to the outstream
			byte [] responseBody = outstream.toByteArray();//Puts the outstream to a byte array
			
			String svar = new String(responseBody);
			System.out.println(svar);//Prints the response, in this case the result
						
		} finally {
			try {
				httpclient.getConnectionManager().shutdown();//Shuts down the httpclient
			} 
			catch (Exception ignore) {
			}
		}
	}
}
