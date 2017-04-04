
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Klasa rejestruj�ca przerwania od dzia�a� myszy
 * 
 * @author Sylwia
 *
 */
public class MouseSpy implements MouseListener {

	/**
	 * Wci�ni�to mysz na wierszu ip
	 */
	private int ip;

	/**
	 * Wci�ni�to mysz na kolumnie jp
	 */
	private int jp;

	/**
	 * Wyci�ni�to mysz na wierszu ir
	 */
	private int ir;
	/**
	 * Wyci�ni�to mysz na kolumnie jr
	 */
	private int jr;

	public MouseSpy() {

	}

	public void mousePressed(MouseEvent event) {

		jp = (int) event.getX() / Gra.poleSize;
		ip = (int) event.getY() / Gra.poleSize;

	}

	public void mouseReleased(MouseEvent event) {
		jr = (int) event.getX() / Gra.poleSize;
		ir = (int) event.getY() / Gra.poleSize;
		Gra szachownica = (Gra) event.getSource();

		if (ip < 8 && jp < 8 && ir < 8 && jr < 8) {
			if (Gra.pionki[ip][jp] == null) {
				return;
			} else if (Gra.pionki[ip][jp].c == Logika.czyjaKolej) {
				if (Gra.pionki[ip][jp].czyDamka) {
					Logika.ruchDamka(ip, jp, ir, jr);
				} else {
					Logika.mozliweRuchyPionem(ip, jp, ir, jr);
					Logika.stanSieDamka(ir, jr);
				}
			}
			szachownica.repaint();
		}
	}

	public void mouseClicked(MouseEvent event) {

	}

	public void mouseEntered(MouseEvent event) {

	}

	public void mouseExited(MouseEvent event) {

	}
}
