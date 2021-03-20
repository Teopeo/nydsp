package nydsp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RensaTid implements ActionListener {

	public int rensa;

	public void actionPerformed(ActionEvent e) {

		nydspmeny.daglista.get(rensa).starttid = null;
		nydspmeny.daglista.get(rensa).sluttid = null;
		nydspmeny.daglista.get(rensa).paus = null;
		nydspmeny.uppdateraDagdelVy();

	}
}
