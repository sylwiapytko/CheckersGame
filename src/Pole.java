import java.awt.Color;
import java.awt.Rectangle;

/**
 * Klasa Pole - reprezentuj¹ca czarne i bia³e pola na szachownicy, posiada
 * atrybut klasy Rectangle posiadaj¹cy punkt zaczepienia szerokoœc i wysokoœæ
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
	 * Prostok¹t s³u¿¹cy do rysowania pola, Klasa Rectangle posiada atrybuty int
	 * x, int y, int width, int hight i metodê setBounds(int x, int y, int
	 * width, int hight) ustawiaj¹ca te zmienne
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
