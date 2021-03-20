package nydsp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public abstract class UIhelp {
	
	public static JLabel nyLabel(String text, int x, int y, int w, int h) {
			
			JLabel lbl = new JLabel(text);
			lbl.setBounds(x, y, w, h);
			
			return lbl;
		}
		
	public static JTextField nyTextField(int x, int y, int w, int h, JDialog dialog) {
		
		JTextField tf = new JTextField();
		tf.setEditable(true);
		tf.setBounds(x, y, w, h);
		dialog.getContentPane().add(tf);
		
		return tf;
	}
	
	public static JComboBox nyComboBox () {
		
		JComboBox cb = new JComboBox();
		AutoCompleteDecorator.decorate(cb);
		cb.setSelectedItem("");
		cb.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cb.setBackground(SystemColor.control);
		cb.setEditable(true);
		
		return cb;
	}
	
	public static JButton rensaButton(String text) {
		
		JButton btn = new JButton(text);
		btn.setBackground(new Color(240, 240, 240));
		btn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btn.setBounds(198, 27, 100, 23);
		
		return btn;
	}
	
	public static JComboBox cBox(String setText, int w, int h) {
		
		JComboBox cb = new JComboBox();
		cb.setBackground(SystemColor.control);
		cb.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cb.setEditable(true);
		cb.setSelectedItem(setText);
		AutoCompleteDecorator.decorate(cb);
		cb.setPreferredSize(new Dimension(w, h));
		
		return cb;
	}
	
}

