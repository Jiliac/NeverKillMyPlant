package com.example.neverkillmyplant;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import android.os.Parcel;
import android.os.Parcelable;

public class Plant implements Parcelable, Externalizable {
	private String name, espece, sticker;
	private int id = -1;

	/***** constructeur *****/
	public Plant() {

	}

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

	/*********** methode auxiliare ***********/

	public boolean equal(Plant plant) {
		boolean retour = this.getId() == plant.getId();
		return retour;
	}

	/************ Plant est externalizable *********/

	@Override
	public void readExternal(ObjectInput input) throws IOException,
			ClassNotFoundException {
		this.name = (String) input.readObject();
		this.espece = (String) input.readObject();
		this.sticker = (String) input.readObject();
		this.id = input.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput output) throws IOException {
		output.writeObject(name);
		output.writeObject(espece);
		output.writeObject(sticker);
		output.writeInt(id);
	}

	/***** getter *********/

	public String getName() {
		return name;
	}

	public String getEspece() {
		return espece;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int describeContents() {
		return 0;
	}
}
