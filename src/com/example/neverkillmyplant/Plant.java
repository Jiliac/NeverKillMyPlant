package com.example.neverkillmyplant;

import android.os.Parcel;
import android.os.Parcelable;

public class Plant implements Parcelable {

	private String name;

	public Plant(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
	}

	public static final Parcelable.Creator<Plant> CREATOR = new Parcelable.Creator<Plant>() {
		public Plant createFromParcel(Parcel in) {
			return new Plant(in);
		}

		public Plant[] newArray(int size) {
			return new Plant[size];
		}
	};

	private Plant(Parcel in) {
		name = in.readString();
	}
}
