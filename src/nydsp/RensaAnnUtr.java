package nydsp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RensaAnnUtr implements ActionListener {
	
	public int rensa;
	
	public void actionPerformed(ActionEvent e) { 

		nydspmeny.daglista.get(rensa).annBev = null;
		nydspmeny.uppdateraDagdelVy();
		
	}
}
