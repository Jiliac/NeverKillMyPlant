package javaClass;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.os.Environment;

public class PlantArray extends ArrayList<Plant> implements Externalizable {
	private int size;

	/******************* constructeurs ************************/
	public PlantArray() {
		super();
	}

	public PlantArray(String str) {
		this();
		this.load(str);
	}

	/************ methode de sauvegarde de donnée *************/
	public void load(String str) {
		PlantArray listeIntermediaire = new PlantArray();
		try {
			File storageFile = new File(Environment
					.getExternalStorageDirectory().getAbsolutePath()
					+ File.separator + str);
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					storageFile));
			listeIntermediaire = (PlantArray) ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Plant plant : listeIntermediaire)
			if (!this.isIn(plant))
				this.add(plant);
	}

	public void save(String str) {
		try {
			File storageFile = new File(Environment
					.getExternalStorageDirectory().getAbsolutePath()
					+ File.separator + str);
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream(storageFile));
			oos.writeObject(this);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/********* methode auxiliaire **********/

	public boolean isIn(Plant plant) {
		boolean retour = false;
		for (Plant maPlante : this)
			if (maPlante.equal(plant))
				retour = true;
		return retour;
	}

	public String toString() {
		String str = "";
		int i = 0;
		for (Plant plant : this) {
			str += "plante(" + i + ") : " + plant.toString() + "\n";
			i++;
		}
		return str;
	}

	/***** PlantArray est externalizable ********/
	@Override
	public void readExternal(ObjectInput arg0) throws IOException,
			ClassNotFoundException {
		size = arg0.readInt();
		for (int i = 0; i < size; i++)
			this.add((Plant) arg0.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput arg0) throws IOException {
		size = this.size();
		arg0.writeInt(size);
		for (int i = 0; i < size; i++)
			arg0.writeObject(this.get(i));
	}
}
