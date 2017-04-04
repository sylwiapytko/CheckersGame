
import java.awt.Color;

/**
 * Klasa Logika - Klasa zajmuj¹ca siê poprawnymi ruchami pionków
 * 
 * @author Sylwia
 *
 */
public class Logika {

	/**
	 * Kolor oznaczaj¹cy które pionki mog¹ siê teraz poruszaæ
	 */
	static Color czyjaKolej = Gra.c3;

	/**
	 * Kolor oznaczaj¹cy pionki przeciwnikia. Pionki które mo¿na biæ
	 */
	static Color przeciwnik = Gra.c4;

	/**
	 * Czy wystêpuje wielokrotne bicie pionkami
	 */
	static boolean podbicie = false;

	/**
	 * Wspó³rzêda i pionka który mo¿e wykonywaæ bicie z powodu bicia
	 * wielokrotnego
	 */
	static int podbiciei;

	/**
	 * Wspó³rzêda j pionka który mo¿e wykonywaæ bicie z powodu bicia
	 * wielokrotnego
	 */
	static int podbiciej;

	/**
	 * Tablica przechowuj¹ca informacje które pionki Zbi³a damka w czasie
	 * swojego ruchu
	 */
	static boolean doZbicia[][] = new boolean[Gra.iloscPol][Gra.iloscPol];

	/**
	 * Metoda sprawdzaj¹ca czy pole o danych wspo³rzêdnych jest zajête przez
	 * pionek
	 * 
	 * @param i
	 *            wspó³rzêdna pola i
	 * @param j
	 *            wspó³rzêdna pola j
	 * @return true - jeœli na polu znajduje siê pionek, false - w przeciwnym
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
	 * Metoda sprawdzaj¹ca czy pole o danych wspólrzêdnych jest zajête przez
	 * pionek któy jest pionkiem przeciwnika
	 * 
	 * @param i
	 *            wspó³rzêdna pola i
	 * @param j
	 *            wspó³rzêdna pola j
	 * @return true - jeœli na polu znajduje siê pionek przeciwnika, false - w
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
	 * Metoda sprawdzaj¹ca dla wszystkich pionków których jest kolej czy
	 * istnieje Bicie dopóki takowego nie znajdzie. Metoda s³u¿y do wymuszenia
	 * bicia pionkiem jeœli takie istnieje.
	 * 
	 * @return true - jeœli dla jakiegoœ pionka koloru którego jest kolej
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
	 * Metoda sprawdzajaca czy dla pionka stoj¹cego na podanych wspó³rzêdnych
	 * istnieje Bicie. Czyli czy w odleg³oœci jednego pola po przek¹tnej we
	 * wszystkich kierunkach znajduje siê pionek przeciwnika a za nim znajduje
	 * siê puste pole
	 * 
	 * @param ip
	 *            wspó³rzêdna i na jakiej stoi pionek
	 * @param jp
	 *            wspó³rzêdna j na jakiej stoi pionek
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
	 * Metoda wykonuj¹ca ruch Bicia dla pionka. Przesuwa pionek którego by³a
	 * teraz kolej zgodnie z ruchem myszy i usuwa przeskoczony pionek
	 * przeciwnika
	 * 
	 * @param ip
	 *            wspó³rzêdna i na której znajdowa³ siê pionek
	 * @param jp
	 *            wspó³rzêdna j na której znajdowa³ siê pionek
	 * @param ir
	 *            wspó³rzêdna i na któr¹ ma byæ przesuniêty pionek
	 * @param jr
	 *            wspó³rzêdna j na któr¹ na byæ przesuniety pionek
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
	 * Metoda sprawdzaj¹ca czy z miejsca gdzie pionek zosta³ upuszczony
	 * wystênpuje mo¿liwoœæ kolejnego bicia (bicie wielokrotne). Zmienia watoœæ
	 * podbicie na true jeœli istnieje bicie, na false - w przeciwnym przypadku
	 * 
	 * @param ir
	 *            wspó³rzêdna i na której znajduje siê pionek
	 * @param jr
	 *            wspó³rzêdna j na której znajduje siê pionek
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
	 * Przesuniêcie pionka ze wspó³rzêdnej ip jp na ir, jr
	 * 
	 * @param ip
	 *            wspó³rzêdna i na której znajdowa³ siê pionek
	 * @param jp
	 *            wspó³rzêdna j na której znajdowa³ siê pionek
	 * @param ir
	 *            wspó³rzêdna i na której ma znajdowaæ siê pionek
	 * @param jr
	 *            wspó³rzêdna j na której ma znajdowaæ siê pionek
	 */
	static void przesun(int ip, int jp, int ir, int jr) {
		Gra.pionki[ir][jr] = Gra.pionki[ip][jp];
		Gra.pionki[ip][jp] = null;

	}

	/**
	 * Przesuniêcie pionka ¿ó³tego - mo¿e siê poruszaæ tylko w dó³
	 * 
	 * @param ip
	 *            wspó³rzêdna i na której znajdowa³ siê pionek
	 * @param jp
	 *            wspó³rzêdna j na której znajdowa³ siê pionek
	 * @param ir
	 *            wspó³rzêdna i na której ma znajdowaæ siê pionek
	 * @param jr
	 *            wspó³rzêdna j na której ma znajdowaæ siê pionek
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
	 * Przesuniêcie pionka czerwonego - mo¿e siê poruszaæ tylko w górê
	 * 
	 * @param ip
	 *            wspó³rzêdna i na której znajdowa³ siê pionek
	 * @param jp
	 *            wspó³rzêdna j na której znajdowa³ siê pionek
	 * @param ir
	 *            wspó³rzêdna i na której ma znajdowaæ siê pionek
	 * @param jr
	 *            wspó³rzêdna i na której ma znajdowaæ siê pionek
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
	 * Przesuniêcie pionka
	 * 
	 * @param ip
	 *            wspó³rzêdna i na której znajdowa³ siê pionek
	 * @param jp
	 *            wspó³rzêdna j na której znajdowa³ siê pionek
	 * @param ir
	 *            wspó³rzêdna i na której ma znajdowaæ siê pionek
	 * @param jr
	 *            wspó³rzêdna j na której ma znajdowaæ siê pionek
	 */
	static void przestawPionek(int ip, int jp, int ir, int jr) {
		if (czyjaKolej == Gra.c3) {
			przestawYellow(ip, jp, ir, jr);
		} else {
			przestawRed(ip, jp, ir, jr);
		}
	}

	/**
	 * Wykonanie mo¿liwego ruchu Pionkiem
	 * 
	 * @param ip
	 *            ip wspó³rzêdna i na której znajdowa³ siê pionek
	 * @param jp
	 *            ip wspó³rzêdna i na której znajdowa³ siê pionek
	 * @param ir
	 *            wspó³rzêdna i na której ma znajdowaæ siê pionek
	 * @param jr
	 *            wspó³rzêdna j na której ma znajdowaæ siê pionek
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
	 * Zmiana ktory z kolorów ma byæ teraz przesuwany
	 */
	static void zamiana() {
		Color temp;
		temp = czyjaKolej;
		czyjaKolej = przeciwnik;
		przeciwnik = temp;

		if (czyjaKolej == Color.yellow) {
			System.out.println("Kolej ¿ó³tych");
		} else {
			System.out.println("Kolej czerwonych");
		}
	}

	/**
	 * Pionek staje siê dam¹. Jeœli Zosta³ postawiony na ostatnim rzêdzie
	 * swojego przeciwnika.
	 * 
	 * @param ir
	 *            wspó³rzêdna i pionka gdzie zosta³ postawiony
	 * @param jr
	 *            wspó³rzêdna j pionka gdzie zosta³ postawiony
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
	 * Wykonanie mo¿liwego ruchu Damk¹
	 * 
	 * @param ip
	 *            wspó³rzêdna i na której znajdowa³ siê pionek
	 * @param jp
	 *            wspó³rzêdna j na której znajdowa³ siê pionek
	 * @param ir
	 *            wspó³rzêdna i na której ma znajdowaæ siê pionek
	 * @param jr
	 *            wspó³rzêdna j na której ma znajdowaæ siê pionek
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
	 * Sprawdzenie czy mo¿na przesun¹æ Damkê z miejsca ip, jp, na miejsce ir, jr
	 * 
	 * @param ip
	 *            wspó³rzêdna i na której znajdowa³ siê pionek
	 * @param jp
	 *            wspó³rzêdna i na której znajdowa³ siê pionek
	 * @param ir
	 *            wspó³rzêdna i na której ma znajdowaæ siê pionek
	 * @param jr
	 *            wspó³rzêdna i na której ma znajdowaæ siê pionek
	 * @param roznicai
	 *            ró¿nica ir i ip
	 * @param roznicaj
	 *            ró¿nica jr i ir
	 * @return true - Jeœli taki ruch jest zgodny z logik¹ gry (pomiedzy nie
	 *         wystêpuje pionek tego samego koloru lub dwa pionki pod rz¹d),
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
	 * Usuniêcie pionków przeciwnika które by³y na drodze ruchu Damki
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
