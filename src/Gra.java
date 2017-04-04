import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Klasa Gra - rysuj¹ca szachownicê i pionki na niej
 * 
 * @author Sylwia Pytko
 * 
 *
 */
public class Gra extends Applet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Szerokoœæ okna
	 */
	private int wi;

	/**
	 * Wysokoœæ okna
	 */
	private int he;

	/**
	 * Wymiary pola
	 */
	static int poleSize;

	/**
	 * Iloœæ wierszy i kolumn na szachownicy
	 */
	static int iloscPol = 8;

	/**
	 * Tablica obiektów pole - plansza szachownicy
	 */
	static Pole[][] pole = new Pole[iloscPol][iloscPol];

	/**
	 * Tablica obiektów pionek
	 */
	static Pionek[][] pionki = new Pionek[iloscPol][iloscPol];
	/**
	 * Kolor pola bia³y
	 */
	Color c1 = Color.WHITE;
	/**
	 * Kolor pola czarny
	 */
	Color c2 = Color.BLACK;
	/**
	 * Kolor pionka ¿ó³ty
	 */
	static Color c3 = Color.yellow;
	/**
	 * Kolor pionka czerwony
	 */
	static Color c4 = Color.red;

	public void init() {
		stworzeniePol();
		wynulowaniePionkow();
		stworzeniePionkow();

		setBackground(Color.gray);
		setSize(400, 400);
		addMouseListener(new MouseSpy());
	}

	public void paint(Graphics g) {

		obliczPoleSize();
		paintPol(g);
		paintPionkow(g);

	}

	/**
	 * Metoda obliczaj¹ca wielkoœæ pola na podstawie: szerokosci okna wi,
	 * wysokosci oknahe i iloscPol
	 * 
	 */
	public void obliczPoleSize() {
		wi = getSize().width;
		he = getSize().height;
		if (he < wi) {
			he -= he % iloscPol;
			poleSize = he;
		} else {
			wi -= wi % iloscPol;
			poleSize = wi;
		}
		poleSize = poleSize / iloscPol;
	}

	/**
	 * Zmienia kolor pola aby wysz³a szachownica
	 * 
	 * @param c
	 *            jaki kolor pola by³ wlasnie rysowany
	 * @return ten drugi kolor pola
	 */

	public Color zmienColor(Color c) {
		if (c == c1)
			return c2;
		else
			return c1;
	}

	/**
	 * Stworzenie 64 obiektów pole z odpowiednim atrybutem kolor Obiekty
	 * przechowywane s¹ w tablicy dwuwymiarowej
	 */
	public void stworzeniePol() {
		Color c = c1;
		for (int i = 0; i < iloscPol; i++) {
			for (int j = 0; j < iloscPol; j++) {
				pole[i][j] = new Pole(c);
				c = zmienColor(c);
			}
			c = zmienColor(c);
		}
	}

	/**
	 * Rysowanie pol na podstawie atrybutu kolor i mo¿liwej do zmiany wraz ze
	 * zmian¹ wielkoœci okna wielkoœci pól
	 * 
	 * @param g
	 */

	public void paintPol(Graphics g) {

		for (int i = 0; i < iloscPol; i++) {
			for (int j = 0; j < iloscPol; j++) {
				g.setColor(pole[i][j].c);
				pole[i][j].r.setBounds(j * poleSize, i * poleSize, poleSize, poleSize);
				g.fillRect(pole[i][j].r.x, pole[i][j].r.y, pole[i][j].r.width, pole[i][j].r.height);
			}
		}
	}

	/**
	 * Wyulowanie tablicy maj¹cej przechowywaæ obiekty pionek
	 */
	public void wynulowaniePionkow() {
		for (int i = 0; i < iloscPol; i++) {
			for (int j = 0; j < iloscPol; j++) {
				pionki[i][j] = null;
			}
		}
	}

	/**
	 * Wywo³anie konstruktorów pionek z atrybutami odpowiednich kolorów w
	 * tablicy w miejscach odpowiadaj¹cych pocz¹tkowemu ustawieniu pionków na
	 * szachownicy 12 pionków ¿ó³tych w górnej czêœci 12 pionków czerwonych w
	 * dolnej czêœci
	 */
	public void stworzeniePionkow() {
		for (int i = 0; i < 3; i++) {
			for (int j = 1 - (i % 2); j < iloscPol; j += 2) {
				pionki[i][j] = new Pionek(c3);
			}
		}
		for (int i = 5; i < iloscPol; i++) {
			for (int j = 1 - (i % 2); j < iloscPol; j += 2) {
				pionki[i][j] = new Pionek(c4);
			}
		}

	}

	/**
	 * Narysowanie ustawienia pionków na szachownicy
	 */
	public void paintPionkow(Graphics g) {
		for (int i = 0; i < iloscPol; i++) {
			for (int j = 0; j < iloscPol; j++) {
				if (pionki[i][j] != null) {
					g.setColor(pionki[i][j].c);
					g.fillOval(pole[i][j].r.x, pole[i][j].r.y, pole[i][j].r.width, pole[i][j].r.height);
					if (pionki[i][j].czyDamka) {
						g.setColor(Color.cyan);
						g.fillOval(pole[i][j].r.x + poleSize / 5, pole[i][j].r.y + poleSize / 5, pole[i][j].r.width / 2,
								pole[i][j].r.height / 2);
					}
				}
			}
		}
	}

}
