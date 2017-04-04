
import java.awt.Color;

/**
 * Klasa Pionek - reprezentuj�ca pionki na szachownicy k�re maj� sw�j kolor,
 * mo�e by� zwyk�ym Pionem lub dam�
 * 
 * @author Sylwia
 *
 */
public class Pionek {

	/**
	 * Kolor Pionka
	 */
	Color c;

	/**
	 * Warto�� logiczna czy Pionek jest Damk�
	 */
	Boolean czyDamka;

	/**
	 * Konstruktor Pionka. Nadanie mu koloru, Stwarzany pionek nigdy nie jest
	 * Damk�.
	 * 
	 * @param c
	 *            kolor pionka
	 * 
	 */
	public Pionek(Color c) {
		this.c = c;
		czyDamka = false;
	}

}
