package javaClass;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class Serveur {
	
	/************* envoie des info ************/
	
	public static void send(String url, String message){
		class RequestTask extends AsyncTask<String, String, String>{

		    @Override
		    protected String doInBackground(String... uri) {
		        HttpClient httpclient = new DefaultHttpClient();
		        HttpResponse response;
		        String responseString = null;
		        try {
		            response = httpclient.execute(new HttpGet(uri[0]));
		            StatusLine statusLine = response.getStatusLine();
		            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
		                ByteArrayOutputStream out = new ByteArrayOutputStream();
		                response.getEntity().writeTo(out);
		                out.close();
		                responseString = out.toString();
		            } else{
		                //Closes the connection.
		                response.getEntity().getContent().close();
		                throw new IOException(statusLine.getReasonPhrase());
		            }
		        } catch (ClientProtocolException e) {
		            //TODO Handle problems..
		        } catch (IOException e) {
		            //TODO Handle problems..
		        }
		        return responseString;
		    }

		    @Override
		    protected void onPostExecute(String result) {
		        super.onPostExecute(result);
		        //Do anything with response..
		    }
		}
		
		new RequestTask().execute(url);
	}
	
	/********* recupere des info *******/
	private static String sante = null;
	public static String getSante(String url){
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost method = new HttpPost(url);
			HttpResponse response = httpclient.execute(method);
			HttpEntity entity = response.getEntity();
			int res = -1;
			if (entity != null)
				res = Integer.parseInt(EntityUtils.toString(entity));
			String str;
			if (res == 1)
				str = "bonne";
			else if (res == 0)
				str = "mauvaise";
			else
				str = "Problem";
			sante = str;
		} catch (Exception e) {
			sante = "Network problem";
		}
			
		return sante;
	}
	public static String getSanteInBackground(String url){
		class TheTask extends AsyncTask<String, String, String> {

			protected void onPostExecute(String result) {
				sante = result;
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
			}

			@Override
			protected String doInBackground(String... params) {
				return Serveur.getSante(params[0]);
			}
		}
		
		new TheTask().execute(url);
		return sante;
	}
}
