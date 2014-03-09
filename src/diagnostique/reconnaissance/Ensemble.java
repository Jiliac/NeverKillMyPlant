package diagnostique.reconnaissance;

import java.util.ArrayList;

import com.example.neverkillmyplant.DiagHyd;

import android.graphics.Bitmap;
import diagnostique.segmentation.Global;
import diagnostique.reconnaissance.ListeEqui;

public class Ensemble {
	private Class diag = null;
	public Class diagnostique() {
		return diag;
	}

	/*********** constructeur *************/

	private Bitmap img;
	private Point[][] premiereEntree;
	private ArrayList<Point> premiereCollection;
	private int width, height;

	public Ensemble(Bitmap img, Point[][] premiereEntree) {
		// initialisation des variables
		this.img = img;
		width = img.getWidth();
		height = img.getHeight();
		this.premiereEntree = premiereEntree;
		premiereCollection = new ArrayList<Point>();

		this.mainAlgo();
	}

	/**************** premiere etage de developpement *******************/

	private ArrayList<Collection> ensemble = new ArrayList<Collection>();
	private ArrayList<Collection> newEnsemble = new ArrayList<Collection>();

	public void mainAlgo() {
		//pour la detection de jaunissement
		int compteurJaunePix = 0;
		
		// donne valeur a grIn de tous les points
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (x != 0 && y != 0) {
					int myGr = premiereEntree[x][y].getGrOut();
					if (premiereEntree[x - 1][y].getGrOut() != myGr)
						premiereEntree[x][y].setGrIn(premiereEntree[x - 1][y]
								.getGrOut());
					else if (premiereEntree[x][y - 1].getGrOut() != myGr)
						premiereEntree[x][y].setGrIn(premiereEntree[x][y - 1]
								.getGrOut());
					else
						premiereEntree[x][y]
								.setGrIn(premiereEntree[x - 1][y - 1]
										.getGrOut());
				} else if (x != 0)
					premiereEntree[x][y].setGrIn(premiereEntree[x - 1][y]
							.getGrOut());
				else if (y != 0)
					premiereEntree[x][y].setGrIn(premiereEntree[x][y - 1]
							.getGrOut());
				else
					premiereEntree[x][y].setGrIn(premiereEntree[x][y]
							.getGrOut());
			}
		}

		// il faut copier premiereEntree dans premiereCollection
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				premiereCollection.add(premiereEntree[x][y]);
			}
		}

		// on rassemble les points frontieres entre memes regions
		for (Point point : premiereCollection)
			this.collectionExistance(point);

		// on supprime les collections "inutile", c'est-a-dire celle qui ne
		// sont
		// pas frontiere entre deux regions
		ArrayList<Collection> ensembleCopie = new ArrayList<Collection>();
		for (Collection collec : ensemble) {
			Point pivot = collec.get(0);
			if (pivot.getGrIn() != pivot.getGrOut())
				ensembleCopie.add(collec);
			else if(pivot.getGrOut()==2)
				compteurJaunePix += collec.size();
		}
		ensemble = ensembleCopie;

		// division des collections en composantes connexes
		for (Collection myCollec : ensemble)
			this.composantesConnexes2(myCollec);

		/*
		 * on enleve les collections de rayon trop grand et les collection
		 * d'ecart type trop grand
		 */

		ArrayList<Collection> aSupprimer = new ArrayList<Collection>();
		int size = newEnsemble.size();
		if (Global.supprimer) {
			for (int i = 0; i < size; i++) {
				Collection collec = newEnsemble.get(i);
				if (collec.size() < 20 || collec.getRayon() > width / 5
						|| collec.getRayon() > height / 5
						|| collec.getEcartType() > 2)
					aSupprimer.add(collec);
			}
		} else {
			for (int i = 0; i < size; i++) {
				Collection collec = newEnsemble.get(i);
				if (collec.size() < 20)
					aSupprimer.add(collec);
			}
		}

		for (Collection collec : aSupprimer)
			newEnsemble.remove(collec);

		// pour compter l'air d'une région
		if (Global.compterZones) {
			int compteur = 0;
			for (Collection collec : newEnsemble)
				if (collec.get(0).getGrOut() == 2)
					compteur++;
			System.out.println("Il y a " + compteur
					+ " zone(s) de cet intervalle");
			if(compteurJaunePix>this.height*this.width/3)
				if(compteur<3)
					this.diag=DiagHyd.class;
				else
					this.diag=DiagHyd.class;
		}

		// on imprime en console les collections selectionnees
		for (Collection collec : newEnsemble)
			if (collec.size() != 0)
				System.out.println(collec);
		System.out.println("fini");
	}

	/********* deuxieme etage de developpement *************/

	private void composantesConnexes2(Collection myCollec) {
		// on initialise la objet necessaire a la methode
		ListeEqui listeEqui = new ListeEqui();
		int maxEtiquette = 0;
		ArrayList<Point> pastPts = new ArrayList<Point>();

		// boucle d'analyse principale
		for (Point ptSetEtiquette : myCollec) {
			// on determine les voisins parmis les points passï¿½s et on set
			// l'etiquette
			ptSetEtiquette.setEtiquette(maxEtiquette);
			ArrayList<Point> voisins = new ArrayList<Point>();
			for (Point past : pastPts) {
				if (myCollec.isVoisin(ptSetEtiquette, past)) {
					voisins.add(past);
				}
			}

			// on set les classes d'equivalence
			if (!voisins.isEmpty()) {
				ptSetEtiquette.setEtiquette(voisins.get(0).getEtiquette());
				ArrayList<Integer> aAjouter = new ArrayList<Integer>();
				for (Point voisin : voisins)
					aAjouter.add(voisin.getEtiquette());
				listeEqui.add(aAjouter);

				// debug
				if (listeEqui.size() == 2)
					System.out.println("debug");

				System.out.print("");
			}

			// fin de boucle
			if (ptSetEtiquette.getEtiquette() == maxEtiquette) {
				ArrayList<Integer> aAjouter = new ArrayList<Integer>();
				aAjouter.add(maxEtiquette);
				listeEqui.add(aAjouter);
				maxEtiquette++;
			}
			pastPts.add(ptSetEtiquette);
		}

		// on rassemble les points selon leur classe d'equivalence
		for (Point point : myCollec)
			listeEqui.setMinEtiquette(point);
		maxEtiquette = listeEqui.getMaxDesMin();

		// ici c'est simplement la copie du resultat de l'etiquetage dans
		// newEnsemble
		Collection newCollec[] = new Collection[maxEtiquette + 1];
		for (int i = 0; i <= maxEtiquette; i++)
			newCollec[i] = new Collection();
		for (Point ptSetEtiquette : myCollec)
			newCollec[ptSetEtiquette.getEtiquette()].add(ptSetEtiquette);
		for (int i = 0; i <= maxEtiquette; i++)
			newEnsemble.add(newCollec[i]);
	}

	private void composantesConnexes(Collection myCollec) {
		/***************** analyse ***************/

		Collection pointPasses = new Collection();
		int maxEtiquette = 0;
		for (Point pointSetEtiquette : myCollec) {
			pointSetEtiquette.setEtiquette(maxEtiquette);
			// les voisin, qui ont le meme groupe ont la meme etiquette
			for (Point pointRef : pointPasses) {
				if (myCollec.isVoisin(pointSetEtiquette, pointRef)
						&& pointRef.equal(pointSetEtiquette))
					pointSetEtiquette.setEtiquette(pointRef);
			}

			if (pointSetEtiquette.getEtiquette() == maxEtiquette)
				maxEtiquette++;
			pointPasses.add(pointSetEtiquette);
		}

		/******** scinder les collections *******/

		// initialise
		Collection myCollecScinde[] = new Collection[maxEtiquette + 1];
		for (int i = 0; i <= maxEtiquette; i++)
			myCollecScinde[i] = new Collection();

		// ajout
		for (Point point : myCollec) {
			myCollecScinde[point.getEtiquette()].add(point);
		}

		// on ajoute le resultat de l'analyse
		for (Collection collec : myCollecScinde)
			if (collec.size() > 5)
				newEnsemble.add(collec);

	}

	private Collection collectionExistance(Point point) {
		for (Collection myCollec : ensemble) {
			if (myCollec.equal(point)) {
				myCollec.add(point);
				return myCollec;
			}
		}

		Collection newCollec = new Collection();
		newCollec.add(point);
		ensemble.add(newCollec);
		return newCollec;
	}

	public ArrayList<Collection> getCollec() {
		return this.newEnsemble;
	}

}