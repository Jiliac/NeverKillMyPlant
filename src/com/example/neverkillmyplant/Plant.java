package com.example.neverkillmyplant;

import android.os.Parcel;
import android.os.Parcelable;

public class Plant implements Parcelable {

	private String name, espece, sticker;

	public int describeContents() {
		return 0;
	}

	/********* Plant est un parcelable ************/
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(espece);
		dest.writeString(sticker);
	}

	public static final Parcelable.Creator<Plant> CREATOR = new Parcelable.Creator<Plant>() {
		public Plant createFromParcel(Parcel in) {
			return new Plant(in);
		}

		public Plant[] newArray(int size) {
			return new Plant[size];
		}
	};
	
	/***** constructeur *****/
	public Plant(String name, String espece, String sticker) {
		this.name = name;
		this.espece = espece;
		this.sticker = sticker;
	}
	
	private Plant(Parcel in) {
		this.name = in.readString();
		this.espece = in.readString();
		this.sticker = in.readString();
	}
	
	/***** getter *********/

	public String getName() {
		return name;
	}
	
	public String getEspece(){
		return espece;
	}

	public void setName(String name) {
		this.name = name;
	}
}
