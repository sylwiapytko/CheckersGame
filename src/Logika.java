
import java.awt.Color;

/**
 * Klasa Logika - Klasa zajmuj�ca si� poprawnymi ruchami pionk�w
 * 
 * @author Sylwia
 *
 */
public class Logika {

	/**
	 * Kolor oznaczaj�cy kt�re pionki mog� si� teraz porusza�
	 */
	static Color czyjaKolej = Gra.c3;

	/**
	 * Kolor oznaczaj�cy pionki przeciwnikia. Pionki kt�re mo�na bi�
	 */
	static Color przeciwnik = Gra.c4;

	/**
	 * Czy wyst�puje wielokrotne bicie pionkami
	 */
	static boolean podbicie = false;

	/**
	 * Wsp�rz�da i pionka kt�ry mo�e wykonywa� bicie z powodu bicia
	 * wielokrotnego
	 */
	static int podbiciei;

	/**
	 * Wsp�rz�da j pionka kt�ry mo�e wykonywa� bicie z powodu bicia
	 * wielokrotnego
	 */
	static int podbiciej;

	/**
	 * Tablica przechowuj�ca informacje kt�re pionki Zbi�a damka w czasie
	 * swojego ruchu
	 */
	static boolean doZbicia[][] = new boolean[Gra.iloscPol][Gra.iloscPol];

	/**
	 * Metoda sprawdzaj�ca czy pole o danych wspo�rz�dnych jest zaj�te przez
	 * pionek
	 * 
	 * @param i
	 *            wsp�rz�dna pola i
	 * @param j
	 *            wsp�rz�dna pola j
	 * @return true - je�li na polu znajduje si� pionek, false - w przeciwnym
	 *         przypadku
	 */
	static boolean czyZajete(int i, int j) {
		if (Gra.pionki[i][j] == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Metoda sprawdzaj�ca czy pole o danych wsp�lrz�dnych jest zaj�te przez
	 * pionek kt�y jest pionkiem przeciwnika
	 * 
	 * @param i
	 *            wsp�rz�dna pola i
	 * @param j
	 *            wsp�rz�dna pola j
	 * @return true - je�li na polu znajduje si� pionek przeciwnika, false - w
	 *         przeciwnym przypadku
	 */
	static boolean czyZajetePrzeciwnikiem(int i, int j) {
		if (czyZajete(i, j)) {
			if (Gra.pionki[i][j].c == przeciwnik) {
				return true;
			}
			return false;
		}
		return false;
	}

	/**
	 * Metoda sprawdzaj�ca dla wszystkich pionk�w kt�rych jest kolej czy
	 * istnieje Bicie dop�ki takowego nie znajdzie. Metoda s�u�y do wymuszenia
	 * bicia pionkiem je�li takie istnieje.
	 * 
	 * @return true - je�li dla jakiego� pionka koloru kt�rego jest kolej
	 *         istnieje Bicie, false - w przeciwnym przypadku
	 */
	static boolean czyIstniejeJakiekolwiekBicie() {
		for (int i = 0; i < Gra.iloscPol; i++) {
			for (int j = 0; j < Gra.iloscPol; j++) {
				if (Gra.pionki[i][j] != null && Gra.pionki[i][j].c == czyjaKolej) {
					if (czyIstniejeBicie(i, j)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Metoda sprawdzajaca czy dla pionka stoj�cego na podanych wsp�rz�dnych
	 * istnieje Bicie. Czyli czy w odleg�o�ci jednego pola po przek�tnej we
	 * wszystkich kierunkach znajduje si� pionek przeciwnika a za nim znajduje
	 * si� puste pole
	 * 
	 * @param ip
	 *            wsp�rz�dna i na jakiej stoi pionek
	 * @param jp
	 *            wsp�rz�dna j na jakiej stoi pionek
	 * @return true - gdy istnieje bicie dla tego pionka, false - w przeciwnym
	 *         przypadku
	 */
	static boolean czyIstniejeBicie(int ip, int jp) {

		if (ip > 1 && jp > 1 && Logika.czyZajetePrzeciwnikiem(ip - 1, jp - 1) && !Logika.czyZajete(ip - 2, jp - 2)) {
			return true;
		}
		if (ip > 1 && jp < 6 && Logika.czyZajetePrzeciwnikiem(ip - 1, jp + 1) && !Logika.czyZajete(ip - 2, jp + 2)) {
			return true;
		}
		if (ip < 6 && jp > 1 && Logika.czyZajetePrzeciwnikiem(ip + 1, jp - 1) && !Logika.czyZajete(ip + 2, jp - 2)) {
			return true;
		}
		if (ip < 6 && jp < 6 && Logika.czyZajetePrzeciwnikiem(ip + 1, jp + 1) && !Logika.czyZajete(ip + 2, jp + 2)) {
			return true;
		}
		return false;
	}

	/**
	 * Metoda wykonuj�ca ruch Bicia dla pionka. Przesuwa pionek kt�rego by�a
	 * teraz kolej zgodnie z ruchem myszy i usuwa przeskoczony pionek
	 * przeciwnika
	 * 
	 * @param ip
	 *            wsp�rz�dna i na kt�rej znajdowa� si� pionek
	 * @param jp
	 *            wsp�rz�dna j na kt�rej znajdowa� si� pionek
	 * @param ir
	 *            wsp�rz�dna i na kt�r� ma by� przesuni�ty pionek
	 * @param jr
	 *            wsp�rz�dna j na kt�r� na by� przesuniety pionek
	 */
	static void bij(int ip, int jp, int ir, int jr) {
		if (ir == (ip - 2) && jr == (jp - 2)) {
			if (Logika.czyZajetePrzeciwnikiem(ip - 1, jp - 1) && !Logika.czyZajete(ir, jr)) {
				przesun(ip, jp, ir, jr);
				Gra.pionki[ip - 1][jp - 1] = null;
				czyIstniejeKolejneBicie(ir, jr);
			}
		}
		if (ir == (ip - 2) && jr == (jp + 2)) {
			if (Logika.czyZajetePrzeciwnikiem(ip - 1, jp + 1) && !Logika.czyZajete(ir, jr)) {
				przesun(ip, jp, ir, jr);
				Gra.pionki[ip - 1][jp + 1] = null;
				czyIstniejeKolejneBicie(ir, jr);
			}
		}
		if (ir == (ip + 2) && jr == (jp - 2)) {

			if (Logika.czyZajetePrzeciwnikiem(ip + 1, jp - 1) && !Logika.czyZajete(ir, jr)) {
				przesun(ip, jp, ir, jr);
				Gra.pionki[ip + 1][jp - 1] = null;
				czyIstniejeKolejneBicie(ir, jr);
			}
		}
		if (ir == (ip + 2) && jr == (jp + 2)) {
			if (Logika.czyZajetePrzeciwnikiem(ip + 1, jp + 1) && !Logika.czyZajete(ir, jr)) {
				przesun(ip, jp, ir, jr);
				Gra.pionki[ip + 1][jp + 1] = null;
				czyIstniejeKolejneBicie(ir, jr);
			}
		}

	}

	/**
	 * Metoda sprawdzaj�ca czy z miejsca gdzie pionek zosta� upuszczony
	 * wyst�npuje mo�liwo�� kolejnego bicia (bicie wielokrotne). Zmienia wato��
	 * podbicie na true je�li istnieje bicie, na false - w przeciwnym przypadku
	 * 
	 * @param ir
	 *            wsp�rz�dna i na kt�rej znajduje si� pionek
	 * @param jr
	 *            wsp�rz�dna j na kt�rej znajduje si� pionek
	 * 
	 */
	static void czyIstniejeKolejneBicie(int ir, int jr) {
		if (czyIstniejeBicie(ir, jr)) {
			System.out.println("Istnieje kolejne bicie dla " + ir + " " + jr);
			podbicie = true;
			podbiciei = ir;
			podbiciej = jr;
		} else {
			podbicie = false;
			zamiana();
		}
	}

	/**
	 * Przesuni�cie pionka ze wsp�rz�dnej ip jp na ir, jr
	 * 
	 * @param ip
	 *            wsp�rz�dna i na kt�rej znajdowa� si� pionek
	 * @param jp
	 *            wsp�rz�dna j na kt�rej znajdowa� si� pionek
	 * @param ir
	 *            wsp�rz�dna i na kt�rej ma znajdowa� si� pionek
	 * @param jr
	 *            wsp�rz�dna j na kt�rej ma znajdowa� si� pionek
	 */
	static void przesun(int ip, int jp, int ir, int jr) {
		Gra.pionki[ir][jr] = Gra.pionki[ip][jp];
		Gra.pionki[ip][jp] = null;

	}

	/**
	 * Przesuni�cie pionka ��tego - mo�e si� porusza� tylko w d�
	 * 
	 * @param ip
	 *            wsp�rz�dna i na kt�rej znajdowa� si� pionek
	 * @param jp
	 *            wsp�rz�dna j na kt�rej znajdowa� si� pionek
	 * @param ir
	 *            wsp�rz�dna i na kt�rej ma znajdowa� si� pionek
	 * @param jr
	 *            wsp�rz�dna j na kt�rej ma znajdowa� si� pionek
	 */
	static void przestawYellow(int ip, int jp, int ir, int jr) {
		if (ir == (ip + 1) && (jr == (jp - 1) || jr == (jp + 1))) {
			if (Gra.pionki[ir][jr] == null) {
				przesun(ip, jp, ir, jr);
				zamiana();
			}
		}
	}

	/**
	 * Przesuni�cie pionka czerwonego - mo�e si� porusza� tylko w g�r�
	 * 
	 * @param ip
	 *            wsp�rz�dna i na kt�rej znajdowa� si� pionek
	 * @param jp
	 *            wsp�rz�dna j na kt�rej znajdowa� si� pionek
	 * @param ir
	 *            wsp�rz�dna i na kt�rej ma znajdowa� si� pionek
	 * @param jr
	 *            wsp�rz�dna i na kt�rej ma znajdowa� si� pionek
	 */
	static void przestawRed(int ip, int jp, int ir, int jr) {
		if (ir == (ip - 1) && (jr == (jp - 1) || jr == (jp + 1))) {
			if (Gra.pionki[ir][jr] == null) {
				przesun(ip, jp, ir, jr);
				zamiana();
			}
		}
	}

	/**
	 * Przesuni�cie pionka
	 * 
	 * @param ip
	 *            wsp�rz�dna i na kt�rej znajdowa� si� pionek
	 * @param jp
	 *            wsp�rz�dna j na kt�rej znajdowa� si� pionek
	 * @param ir
	 *            wsp�rz�dna i na kt�rej ma znajdowa� si� pionek
	 * @param jr
	 *            wsp�rz�dna j na kt�rej ma znajdowa� si� pionek
	 */
	static void przestawPionek(int ip, int jp, int ir, int jr) {
		if (czyjaKolej == Gra.c3) {
			przestawYellow(ip, jp, ir, jr);
		} else {
			przestawRed(ip, jp, ir, jr);
		}
	}

	/**
	 * Wykonanie mo�liwego ruchu Pionkiem
	 * 
	 * @param ip
	 *            ip wsp�rz�dna i na kt�rej znajdowa� si� pionek
	 * @param jp
	 *            ip wsp�rz�dna i na kt�rej znajdowa� si� pionek
	 * @param ir
	 *            wsp�rz�dna i na kt�rej ma znajdowa� si� pionek
	 * @param jr
	 *            wsp�rz�dna j na kt�rej ma znajdowa� si� pionek
	 */
	static void mozliweRuchyPionem(int ip, int jp, int ir, int jr) {
		if (podbicie) {
			if (ip == podbiciei && jp == podbiciej) {
				Logika.bij(ip, jp, ir, jr);
			}
		} else if (czyIstniejeJakiekolwiekBicie()) {
			bij(ip, jp, ir, jr);
		} else {
			przestawPionek(ip, jp, ir, jr);
		}
	}

	/**
	 * Zmiana ktory z kolor�w ma by� teraz przesuwany
	 */
	static void zamiana() {
		Color temp;
		temp = czyjaKolej;
		czyjaKolej = przeciwnik;
		przeciwnik = temp;

		if (czyjaKolej == Color.yellow) {
			System.out.println("Kolej ��tych");
		} else {
			System.out.println("Kolej czerwonych");
		}
	}

	/**
	 * Pionek staje si� dam�. Je�li Zosta� postawiony na ostatnim rz�dzie
	 * swojego przeciwnika.
	 * 
	 * @param ir
	 *            wsp�rz�dna i pionka gdzie zosta� postawiony
	 * @param jr
	 *            wsp�rz�dna j pionka gdzie zosta� postawiony
	 */
	static void stanSieDamka(int ir, int jr) {
		if (!podbicie && Gra.pionki[ir][jr] != null) {
			if (ir == 0 && Gra.pionki[ir][jr].c == Color.red) {
				Gra.pionki[ir][jr].czyDamka = true;
			}
			if (ir == 7 && Gra.pionki[ir][jr].c == Color.yellow) {
				Gra.pionki[ir][jr].czyDamka = true;
			}
		}
	}

	/**
	 * Wykonanie mo�liwego ruchu Damk�
	 * 
	 * @param ip
	 *            wsp�rz�dna i na kt�rej znajdowa� si� pionek
	 * @param jp
	 *            wsp�rz�dna j na kt�rej znajdowa� si� pionek
	 * @param ir
	 *            wsp�rz�dna i na kt�rej ma znajdowa� si� pionek
	 * @param jr
	 *            wsp�rz�dna j na kt�rej ma znajdowa� si� pionek
	 */
	static void ruchDamka(int ip, int jp, int ir, int jr) {
		int roznicai, roznicaj;
		roznicai = (ir - ip);
		roznicaj = (jr - jp);

		if (roznicai == roznicaj || roznicai == (-roznicaj)) {
			if (Gra.pionki[ir][jr] == null) {
				if (czyMoznaPrzesunacDamke(ip, jp, ir, jr, roznicai, roznicaj)) {
					bicieDamka();
					przesun(ip, jp, ir, jr);
					zamiana();
				}
			}
		}
	}

	/**
	 * Sprawdzenie czy mo�na przesun�� Damk� z miejsca ip, jp, na miejsce ir, jr
	 * 
	 * @param ip
	 *            wsp�rz�dna i na kt�rej znajdowa� si� pionek
	 * @param jp
	 *            wsp�rz�dna i na kt�rej znajdowa� si� pionek
	 * @param ir
	 *            wsp�rz�dna i na kt�rej ma znajdowa� si� pionek
	 * @param jr
	 *            wsp�rz�dna i na kt�rej ma znajdowa� si� pionek
	 * @param roznicai
	 *            r�nica ir i ip
	 * @param roznicaj
	 *            r�nica jr i ir
	 * @return true - Je�li taki ruch jest zgodny z logik� gry (pomiedzy nie
	 *         wyst�puje pionek tego samego koloru lub dwa pionki pod rz�d),
	 *         false- w przeciwnym przypadku
	 */
	static boolean czyMoznaPrzesunacDamke(int ip, int jp, int ir, int jr, int roznicai, int roznicaj) {
		int tymi = ip, tymj = jp;

		wynulowanieDoZbicia();

		if (roznicai < 0 && roznicaj < 0) {
			while (tymi != ir && tymj != jr) {
				tymi--;
				tymj--;
				if (czyZajete(tymi, tymj)) {
					if (!czyZajetePrzeciwnikiem(tymi, tymj)) {
						return false;
					} else if (czyZajete(tymi - 1, tymj - 1)) {
						return false;
					} else {
						doZbicia[tymi][tymj] = true;
					}
				}
			}
		}
		if (roznicai < 0 && roznicaj > 0) {
			while (tymi != ir && tymj != jr) {
				tymi--;
				tymj++;
				if (czyZajete(tymi, tymj)) {
					if (!czyZajetePrzeciwnikiem(tymi, tymj)) {
						return false;
					} else if (czyZajete(tymi - 1, tymj + 1)) {
						return false;
					} else {
						doZbicia[tymi][tymj] = true;
					}
				}
			}
		}
		if (roznicai > 0 && roznicaj < 0) {
			while (tymi != ir && tymj != jr) {
				tymi++;
				tymj--;
				if (czyZajete(tymi, tymj)) {
					if (!czyZajetePrzeciwnikiem(tymi, tymj)) {
						return false;
					} else if (czyZajete(tymi + 1, tymj - 1)) {
						return false;
					} else {
						doZbicia[tymi][tymj] = true;
					}
				}
			}
		}
		if (roznicai > 0 && roznicaj > 0) {
			while (tymi != ir && tymj != jr) {
				tymi++;
				tymj++;
				if (czyZajete(tymi, tymj)) {
					if (!czyZajetePrzeciwnikiem(tymi, tymj)) {
						return false;
					} else if (czyZajete(tymi + 1, tymj + 1)) {
						return false;
					} else {
						doZbicia[tymi][tymj] = true;
					}
				}
			}
		}

		return true;

	}

	/**
	 * Usuni�cie pionk�w przeciwnika kt�re by�y na drodze ruchu Damki
	 */
	static void bicieDamka() {
		for (int i = 0; i < Gra.iloscPol; i++) {
			for (int j = 0; j < Gra.iloscPol; j++) {
				if (doZbicia[i][j] == true) {
					Gra.pionki[i][j] = null;
				}
			}
		}
	}

	/**
	 * Wynulowanie tablicy doZbicia
	 */
	static void wynulowanieDoZbicia() {
		for (int i = 0; i < Gra.iloscPol; i++) {
			for (int j = 0; j < Gra.iloscPol; j++) {
				doZbicia[i][j] = false;
			}
		}
	}

}
