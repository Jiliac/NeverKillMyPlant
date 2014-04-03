package com.example.neverkillmyplant;

import java.io.IOException;
import javaClass.Plant;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

public class CheckHealth extends IntentService {
	private final static String TAG = "CheckHealth";
	Plant plant;

	public CheckHealth() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent startIntent) {
		// récupère la plante dont on crée le service
		plant = (Plant) startIntent.getParcelableExtra("plant");

		String adresse = null; // à faire à partir de la plante...
		String retour = getRetourServeur(adresse);

		// CREATION ET ENVOI DE LA NOTIF
		notificationCreator();
	}

	private String getRetourServeur(String adresse) {

		String retour = "PROBLEME";
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost method = new HttpPost(adresse);
			HttpResponse response;
			response = httpclient.execute(method);
			HttpEntity entity = response.getEntity();
			if (entity != null)
				retour = EntityUtils.toString(entity);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}

	/* variable pour le test des notifications */
	public int ID_NOTIFICATION = 0;

	private void notificationCreator() {
		Intent notificationIntent = new Intent(CheckHealth.this,
				CheckHealth.class);
		notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent contentIntent = PendingIntent.getActivity(
				CheckHealth.this, 0, notificationIntent, 0);

		Notification notification = new Notification(R.drawable.ficus, "Titre",
				System.currentTimeMillis());
		notification.setLatestEventInfo(CheckHealth.this, "Titre", "Texte",
				contentIntent);

		NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(ID_NOTIFICATION, notification);
	}
}
