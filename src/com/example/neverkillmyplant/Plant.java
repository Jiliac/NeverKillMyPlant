package com.example.neverkillmyplant;

import android.os.Parcel;
import android.os.Parcelable;

public class Plant implements Parcelable{

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
	
	/*********** c'est parcelable **************/ 

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		arg0.writeString(name);
	}
	
	public static final Parcelable.Creator<Plant> CREATOR = new Parcelable.Creator<Plant>() {
		  @Override
		  public Plant createFromParcel(Parcel source) {
		    return new Plant(source);
		  }

		  @Override
		  public Plant[] newArray(int size) {
		    return new Plant[size];
		  }
		};

		public Plant(Parcel in) {
		  this.name = in.readString();
		}

}
