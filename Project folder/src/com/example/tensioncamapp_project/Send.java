/*package com.example.tensioncamapp_project;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;


public class Send{
	private static final SendTask SendTask = null;

	public Send(){
	}
	
	private class SendTask extends AsyncTask<String, String, String>{
		private String textView;
		
		@Override
		protected String doInBackground(String ... params) {
			String filePath = params[0];
			String answer = "3";
			
			System.out.println("test3");
			
			HttpClient httpclient = new DefaultHttpClient();//Creates a HttpClient
			
			try {
				HttpPost httppost = new HttpPost("http://192.168.43.79:8080/Analyse/upload"); //Creates a post header
				System.out.println("test4");
				FileBody pic = new FileBody(new File(filePath)); //Creates a FileBody
				System.out.println("test5");
				MultipartEntity requestEntity = new MultipartEntity(); //Creates a MultipartEntity
				requestEntity.addPart("file", pic);//Adds the picture to the MultipartEntity

				httppost.setEntity(requestEntity);//Sets the entity of the HttpPost

				System.out.println("executing request " + httppost.getRequestLine());//Prints what is requested
				HttpResponse response = httpclient.execute(httppost);//Retrieves the response
				HttpEntity responseEntity = response.getEntity();//Retrieves the entity from the response

				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());//Prints the status line from the request
				if (responseEntity != null) {
					System.out.println("Response content length: " + responseEntity.getContentLength());
				}
				
				ByteArrayOutputStream outstream = new ByteArrayOutputStream();//Creates a ByteArrayOutPutStream
				response.getEntity().writeTo(outstream);//Writes the response to the outstream
				byte [] responseBody = outstream.toByteArray();//Puts the outstream to a byte array
				
				answer = new String(responseBody);
				System.out.println(answer);//Prints the response, in this case the result
							
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					httpclient.getConnectionManager().shutdown();//Shuts down the httpclient
				} 
				catch (Exception ignore) {
				}
			}
			return answer;
		}
	
		@Override
	    protected void onPostExecute(String result) {
	      textView = result;
	    }
		
	}
		
	public static String send(String path) throws Exception {
		System.out.println(path);
		SendTask task = Send.SendTask;
	    task.execute(path);
	    String answer = task.get();
	    
	    return answer;
		
		//String svar;
		//String picName = "Bild.bmp";
			

		/*try {
			HttpPost httppost = new HttpPost("http://192.168.43.79:8080/Analyse/upload"); //Creates a post header
			System.out.println("test4");
			FileBody pic = new FileBody(new File(filePath)); //Creates a FileBody
			System.out.println("test5");
			MultipartEntity requestEntity = new MultipartEntity(); //Creates a MultipartEntity
			requestEntity.addPart("file", pic);//Adds the picture to the MultipartEntity

			httppost.setEntity(requestEntity);//Sets the entity of the HttpPost

			System.out.println("executing request " + httppost.getRequestLine());//Prints what is requested
			HttpResponse response = httpclient.execute(httppost);//Retrieves the response
			HttpEntity responseEntity = response.getEntity();//Retrieves the entity from the response

			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());//Prints the status line from the request*/
		/*	if (responseEntity != null) {
				System.out.println("Response content length: " + responseEntity.getContentLength());
			}*/

			
			/*ByteArrayOutputStream outstream = new ByteArrayOutputStream();//Creates a ByteArrayOutPutStream
			response.getEntity().writeTo(outstream);//Writes the response to the outstream
			byte [] responseBody = outstream.toByteArray();//Puts the outstream to a byte array
			
			svar = new String(responseBody);
			System.out.println(svar);//Prints the response, in this case the result
						
		} finally {
			try {
				httpclient.getConnectionManager().shutdown();//Shuts down the httpclient
			} 
			catch (Exception ignore) {
			}
		}
		return svar;
	}*/
/*	}
}*/

