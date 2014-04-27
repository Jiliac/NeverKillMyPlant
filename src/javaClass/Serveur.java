package javaClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.provider.Settings.Secure;
import android.widget.TextView;

public class Serveur {
	
	/************* envoie des info ************/
	
	static public void sendID(String url) {
		class TheTask extends AsyncTask<String, String, String> {
			String result = "";
			protected void onPostExecute(String result1) {
				System.out.println(result1);
				System.out.println("DONE");
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
			}

			@Override
			protected String doInBackground(String... params) {
				// Create a new HttpClient and Post Header
			    HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost(params[0]);

			    try {
			        // Add your data
			        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			        String id =Secure.ANDROID_ID;
			        nameValuePairs.add(new BasicNameValuePair("id", id));
			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			        // Execute HTTP Post Request
			        HttpResponse response = httpclient.execute(httppost);
			        HttpEntity entity = response.getEntity();
					if (entity != null)
						result = EntityUtils.toString(entity);
			    } catch (ClientProtocolException e) {
			        // TODO Auto-generated catch block
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			    }
			    
			    return result;
			}
		}
		
		new TheTask().execute(url);

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
			sante = "Network problem : " + e.toString();
		}
			
		return sante;
	}
	public static String getSanteInBackground(String url){
		class TheTask extends AsyncTask<String, String, String> {

			protected void onPostExecute(String result) {
				sante = result;
				System.out.println(sante);
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
	
	static public void getSantePlant(String url) {
		class TheTask extends AsyncTask<String, String, String> {

			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				int res = Integer.parseInt(result);
				String str;
				if (res == 1)
					str = "bonne";
				else
					str = "mauvaise";
				System.out.println("La sante de cette plante est: " + str);
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
			}

			@Override
			protected String doInBackground(String... params) {
				try {
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost method = new HttpPost(params[0]);
					HttpResponse response = httpclient.execute(method);
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						return EntityUtils.toString(entity);
					} else {
						return "No string";
					}
				} catch (Exception e) {
					return "Network problem";
				}
			}
		}

		new TheTask().execute(url);
	}
}
