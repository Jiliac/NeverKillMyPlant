package javaClass;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.provider.Settings.Secure;

public class Serveur {
	public static void sendID() {
		String adresse = ComExt.getServeurURL();
		adresse += "newUser/";
		adresse += Secure.ANDROID_ID;
		adresse += "?method=plain";
		System.out.println("depart");

		class TheTask extends AsyncTask<String, String, String> {

			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				System.out.println(result);
				ComExt.setUserId(result);
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

		new TheTask().execute(adresse);
	}

	public static String getSante(Plant plant) {
		String url = ComExt.getAdresseSante(plant);

		class TheTask extends AsyncTask<String, String, String> {

			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				ComExt.setSanteInter(result);
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
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ComExt.getSanteInter();
	}
}
