
import java.awt.Color;

/**
 * Klasa Pionek - reprezentuj¹ca pionki na szachownicy kóre maj¹ swój kolor,
 * mo¿e byæ zwyk³ym Pionem lub dam¹
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
	 * Wartoœæ logiczna czy Pionek jest Damk¹
	 */
	Boolean czyDamka;

	/**
	 * Konstruktor Pionka. Nadanie mu koloru, Stwarzany pionek nigdy nie jest
	 * Damk¹.
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
