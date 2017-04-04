import java.awt.Color;
import java.awt.Rectangle;

/**
 * Klasa Pole - reprezentuj�ca czarne i bia�e pola na szachownicy, posiada
 * atrybut klasy Rectangle posiadaj�cy punkt zaczepienia szeroko�c i wysoko��
 * 
 * @author Sylwia
 *
 */
public class Pole {
	/**
	 * Kolor pola
	 */
	Color c;

	/**
	 * Prostok�t s�u��cy do rysowania pola, Klasa Rectangle posiada atrybuty int
	 * x, int y, int width, int hight i metod� setBounds(int x, int y, int
	 * width, int hight) ustawiaj�ca te zmienne
	 */
	Rectangle r;

	/**
	 * Konstruktor. Nadaje nowemu obiektowi kolor i nowy obiekt Rectangle o
	 * atrybutach (0,0,0,0)
	 */
	public Pole(Color c) {
		this.c = c;
		r = new Rectangle();

	}
}
