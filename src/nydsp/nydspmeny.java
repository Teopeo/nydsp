package nydsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ToolTipManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.Element;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFFirstHeader;
import org.apache.poi.xssf.usermodel.XSSFOddHeader;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.SystemColor;
import javax.swing.JMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JLayeredPane;
import java.awt.FlowLayout;
import java.awt.FocusTraversalPolicy;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;

import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Dimension;

public class nydspmeny {
	private static JFrame huvudmeny = new JFrame("Dokumentskaparen");
	private static JMenuBar menuBar = new JMenuBar();
	private static String veraDokMapp;
	private static String systemMapp;
	private static String dataMapp;
	private static JTextField tfmalnr = new JTextField();
	private static JTextField tfStarttid = new JTextField();
	private static JTextField tfSluttid = new JTextField();
	private static JTextField tfAntPart = new JTextField();
	private static JTextField tfAntNarvaro = new JTextField();
	private static JTextField tfAntRoll = new JTextField();
	private static JTextField tfAntForhorNamn = new JTextField();
	private static JTextField tfNarstaendeTill = new JTextField();
	private static JTextField tfAntUtrAktbilS = new JTextField();
	private static JTextField tfAntPersutrAktbil = new JTextField();
	private static JTextField tfPausStart = new JTextField();
	private static JTextField tfPausSlut = new JTextField();
	private static JTextField tfSaAb = new JTextField();
	public static ArrayList<Dag> daglista = new ArrayList<>();
	public static ArrayList<ProtAktor> aktorlista = new ArrayList<>();
	public static List<UtvaldErsatt> listaUtvaldaErsatt = new ArrayList<>();
	private static ArrayList<JComboBox> cbListaNollstall = new ArrayList<JComboBox>();
	private static ArrayList<JComboBox> cbListaDokNamn = new ArrayList<JComboBox>();
	private static ArrayList<JComboBox> cbListaStandardMall = new ArrayList<JComboBox>();
	private static ArrayList<JPanel> pnlListaNollstall = new ArrayList<JPanel>();
	private static ArrayList<DefaultListModel> listmodelListaNollstall = new ArrayList<DefaultListModel>();
	private static ArrayList<DefaultComboBoxModel> cbmodelListaNollstall = new ArrayList<DefaultComboBoxModel>();
	private static ArrayList<DefaultListModel> listmodelListaDagar = new ArrayList<DefaultListModel>();
	private static ArrayList<JList> listListaNollstall = new ArrayList<JList>();
	private static ArrayList<JTextArea> listTaNollstall = new ArrayList<JTextArea>();
	private static ArrayList<String> datumArray = new ArrayList<String>();
	private static DefaultComboBoxModel modeldoktypbrottmal = new DefaultComboBoxModel(
			new Object[] { "Anteckningar, huvudförhandling", "Beslut, ej särskilt uppsatt", "Beslut, särskilt uppsatt",
					"Dom", "Protokoll" });
	private static DefaultComboBoxModel modeldoktyptvistemal = new DefaultComboBoxModel(new Object[] {
			"Anteckningar, huvudförhandling", "Beslut", "Dom", "Protokoll", "Protokoll med beslut", "Tredskodom" });
	private static DefaultComboBoxModel modeldoktyparende = new DefaultComboBoxModel(
			new Object[] { "Beslut", "Protokoll", "Protokoll med beslut", "Slutligt beslut" });
	private static DefaultComboBoxModel modelprotbmal = new DefaultComboBoxModel(new Object[] { "Häktningsförhandling",
			"Huvudförhandling", "Kvarstadsförhandling", "Omhäktningsförhandling" });
	private static DefaultComboBoxModel modelhaktad = new DefaultComboBoxModel(
			new Object[] { "på sannolika skäl misstänkt för", "skäligen misstänkt för" });
	private static DefaultComboBoxModel modelfrifot = new DefaultComboBoxModel(
			new Object[] { "Ej sannolika skäl för brottsmisstanken", "Ej sannolika skäl för brottsmisstankarna",
					"på sannolika skäl misstänkt för " });
	private static DefaultComboBoxModel modelAktor = new DefaultComboBoxModel(new Object[] { "Domare", "Nämndeman",
			"Ansvarig för anteckningarna", "Part/ombud (annat än närvarande)", "Övrig" });
	private static DefaultComboBoxModel modelForhorRollT = new DefaultComboBoxModel(
			new Object[] { "ställföreträdare, käranden", "ställföreträdare, svaranden", "vittne", "vittne, närstående",
					"kärande", "svarande" });
	private static DefaultComboBoxModel modelForhorRollB = new DefaultComboBoxModel(
			new Object[] { "målsägande", "tilltalad", "vittne", "vittne, närstående" });
	private static DefaultComboBoxModel modelBevisningB = new DefaultComboBoxModel(
			new Object[] { "Förhör", "Annan bevisning/utredning", "Personutredning" });
	private static DefaultComboBoxModel modelBevisningT = new DefaultComboBoxModel(
			new Object[] { "Förhör", "Annan bevisning/utredning" });
	private static DefaultComboBoxModel modelAnnUtrB = new DefaultComboBoxModel(new Object[] { "åklagaren" });
	private static DefaultComboBoxModel modelAnnUtrT = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelPersutr = new DefaultComboBoxModel(new Object[] {
			"Belastningsregisterutdrag", "Yttrande från Kriminalvården",
			"Läkarintyg enligt 7 § lagen(1991:2041) om särskildpersonutredning i brottmål, m.m.", "Uppger följande" });
	private static DefaultComboBoxModel modelTilltalad = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelBeslutB = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelBeslutT = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelBeslAvs = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelAntB = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelAntT = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelAntAvs = new DefaultComboBoxModel();
	private static DefaultListModel modelAntAktorer = new DefaultListModel();
	private static DefaultListModel modelAntForhor = new DefaultListModel();
	private static DefaultListModel modelAntAnnUtr = new DefaultListModel();
	private static DefaultListModel modelAntPersutr = new DefaultListModel();
	private static DefaultListModel modelAntTid = new DefaultListModel();
	private static DefaultListModel modelPaus = new DefaultListModel();
	private static DefaultListModel modelAntDatum = new DefaultListModel();
	private static DefaultListModel modelValdaAlt = new DefaultListModel();
	private static JPanel pnl1 = new JPanel(new GridLayout(3, 1, 3, 3));
	private static JList listAntTid = new JList();
	private static JPanel pnldokalt = new JPanel();
	private static JScrollPane scrDokAlt = new JScrollPane();
	private static JList listDokAlt = new JList();
	private static JComboBox cbDokAlt = new JComboBox();
	private static ArrayList<String> blista = new ArrayList<String>();
	private static ArrayList<String> nlista = new ArrayList<String>();
	private static DefaultComboBoxModel modelNamndeman = new DefaultComboBoxModel();
	private static JComboBox cbprotokollforare = new JComboBox(blista.toArray());
	private static JPanel pnlmalnr = new JPanel();
	private static JComboBox cbbta = new JComboBox(new Object[] { "B", "T", "FT", "Ä" });
	private static JPanel pnldoktyp = new JPanel();
	private static JComboBox cbdoktyp = new JComboBox();
	private static JComboBox cbunder1 = new JComboBox();
	private static JPanel pnlGaVidare = new JPanel();
	private static JButton btnGaVidare = new JButton("Gå vidare");
	private static JPanel pnlAnt = new JPanel();
	private static JPanel pnlAntPartOvr = new JPanel();
	private static JLabel lblPartNarvaro = new JLabel("Närvaro:");
	private static JLabel lblAntRoll = new JLabel("Roll:");
	private static JPanel pnlAntBeslut = new JPanel();
	private static JCheckBox boxTolk = new JCheckBox("Tolk");
	private static JScrollPane scrAntBeslut1 = new JScrollPane();
	private static JPanel pnlBeslAvs = new JPanel();
	private static JComboBox cbAntBeslAvs = new JComboBox();
	private static JTextArea taAntBeslut1 = new JTextArea();
	private static JPanel pnlAntPaus = new JPanel();
	private static JLabel lblAntPaus = new JLabel("Paus:");
	private static JLabel lblstrecknyh_1 = new JLabel("–");
	private static JLabel lbltidnyh = new JLabel("Start:");
	private static JComboBox cbAnt2 = new JComboBox();
	private static JLabel lblBeslAvs = new JLabel("Avseende:");
	private static JComboBox cbAnt1 = new JComboBox(
			new Object[] { "Aktör", "Tid", "Bevisning", "Beslut", "Övrig anteckning" });
	private static JButton btnlaggtillalt = new JButton("Lägg till");
	private static JButton btntabortalt = new JButton("Ta bort");
	private static JButton btnoppnamall = new JButton("Öppna mall");
	private static JButton btnHamtaMall = new JButton("Hämta mall");
	private static JPanel pnlratten = new JPanel();
	private static JComboBox cbratten = new JComboBox();
	private static JButton btnSparaBeslut = new JButton("Spara");
	private static JPanel pnlAntPersForh = new JPanel();
	private static JComboBox cbPersForh = new JComboBox();
	private static JLabel lblPersForh = new JLabel("uppger följande om sina personliga förhållanden.");
	private static JScrollPane scrPersForh1 = new JScrollPane();
	private static JTextArea taPersForh1 = new JTextArea();
	private static JPanel pnlAntPersutr = new JPanel();
	private static JLabel lblAntPersutrAktbil = new JLabel("Aktbilaga:");
	private static JPanel pnlAnnUtr = new JPanel();
	private static JLabel lblAntUtrAktbil = new JLabel("Aktbilaga");
	private static JLabel lblAntUtrAktbilS = new JLabel("sida");
	private static JComboBox cbAnt3 = new JComboBox();
	private static JButton btnAntLaggTill = new JButton("Lägg till");
	private static JComboBox cbAntDag = new JComboBox();
	private static JPanel pnlAntForhor = new JPanel();
	private static JComboBox cbAntHorsPer = new JComboBox(
			new Object[] { "Hörs på plats", "Hörs per videolänk", "Hörs per telefon" });
	private static JLabel lblAntTill = new JLabel("till");
	private static JCheckBox boxBegarErsattning = new JCheckBox("Begär ersättning");
	private static JPanel pnlAntNarstaende = new JPanel();
	private static JComboBox cbAntNarstaende = new JComboBox(new Object[] { "Make", "Maka", "Sambo", "Son", "Dotter",
			"Mamma", "Pappa", "Syster", "Bror", "Svåger", "Svägerska" });
	private static JPanel pnlSa = new JPanel();
	private static JLabel lblSaAb = new JLabel("Stämningsansökan, ab:");
	private static JPanel pnlSkapaOppnaDok = new JPanel();
	private static JButton btnSkapaDok = new JButton("Skapa dokument");
	private static JButton btnOppnaDok = new JButton("Öppna skapat dokument");
	private static JPanel pnlAktorer = new JPanel(new BorderLayout(4, 4));
	private static JPanel pnlB = new JPanel(new BorderLayout(4, 4));
	private static JPanel pList = new JPanel(new GridBagLayout());
	private static JPanel mallList = new JPanel(new GridBagLayout());
	private static JButton btnRensa = new JButton("Rensa");
	private static DefaultComboBoxModel modelratten = new DefaultComboBoxModel(blista.toArray());
	private static DefaultComboBoxModel modelprotokollforare = new DefaultComboBoxModel(blista.toArray());
	private static JButton btnbestavd = new JButton("Bestäm avdelning");
	private static JButton btnstandardmall = new JButton("Standardmall");
	private static JButton btnLaggTillPerson = new JButton("Lägg till domstolsperson");
	private static Aktorer aktor = new Aktorer();
	private static String mallPlats = new String();
	private static String stndMallNamn = new String();
	private static String stndMall = new String();
	private static String mallNamn = new String();
	private static String mall = new String();
	private static boolean boolAnteckningar;
	private static String sDatum = new String();
	private static String sOk3v = new String();
	private static String sSaken = new String();
	private static String btfta = new String();
	private static String dokTyp = new String();
	private static String underDokTyp = new String();
	private static String dokumentKategori = new String();
	private static String standardskrivningar = new String();
	private static String dokspecskrivningar = new String();
	private static ArrayList<String> aktorLista = new ArrayList<>();
	private static ArrayList<JComboBox> cbListaStndErsattPanel = new ArrayList<>();
	private static ArrayList<JComboBox> cbListaSpecErsattPanel = new ArrayList<>();
	private static ArrayList<JTextArea> taListaErsattPanel = new ArrayList<>();
	private static final JTextField tfAnnUtrAktb = new JTextField();
	private static final JButton btnBestVeraDok = new JButton("Plats för veradokument");
	private static DefaultComboBoxModel modelDokTyp = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelDokAlt = new DefaultComboBoxModel();
	private static DefaultComboBoxModel modelCbUnder1 = new DefaultComboBoxModel();

	private static void bestamCbunder1(ItemEvent event) {
		Object item = event.getItem();
		if (event.getStateChange() == ItemEvent.SELECTED) {
			dokTyp = cbdoktyp.getSelectedItem().toString();
			if (cbbta.getSelectedItem() != null && item.toString().length() > 0
					&& item.toString() != "Anteckningar, huvudförhandling"
					&& !item.toString().equals("Dokumenttyp..")) {
				cbunder1.removeAllItems();
				cbunder1.setVisible(true);
				skapaCbmodel(modelCbUnder1, btfta, dokTyp, cbunder1);
			} else {
				cbunder1.removeAllItems();
				cbunder1.setVisible(false);
			}
			if (item.toString().contains("Dokumenttyp")) {
				cbunder1.removeAllItems();
				cbunder1.setVisible(false);
			}
		}
	}

	private static void bestamInnehallCbAnt1(ItemEvent event) {
		Object item = event.getItem();
		if (event.getStateChange() == ItemEvent.SELECTED && item != null) {
			cbAnt2.setVisible(true);
			cbAnt2.setEditable(true);
			pnlAntBeslut.setVisible(false);
			pnlAntPaus.setVisible(false);
			pnlAnt.setFocusCycleRoot(true);
			if ((event.getStateChange() == ItemEvent.SELECTED) && (item.toString() == "Tid")) {
				pnlAntPaus.setVisible(true);
				cbAnt2.setVisible(false);
				cbAnt2.setSelectedItem(null);
			} else if ((event.getStateChange() == ItemEvent.SELECTED) && (item.toString() == "Aktör")) {
				cbAnt2.setModel(modelAktor);
				cbAnt2.setSelectedItem("Välj aktör..");
				cbAnt2.setEditable(false);
			} else if ((event.getStateChange() == ItemEvent.SELECTED) && (item.toString() == "Bevisning")) {
				if (btfta == "B") {
					cbAnt2.setModel(modelBevisningB);
				}
				if (btfta == "T") {
					cbAnt2.setModel(modelBevisningT);
				}
				cbAnt2.setSelectedItem("Lägg till bevisning..");
				cbAnt2.setEditable(false);
			} else if ((event.getStateChange() == ItemEvent.SELECTED) && (item.toString() == "Beslut")) {
				pnlAntBeslut.setVisible(true);
				if (btfta == "B") {
					cbAnt2.setModel(modelBeslutB);
					cbAnt2.removeAllItems();
					try {
						Scanner scBrottmalsbeslut = new Scanner(new File(dataMapp + "brottmålsbeslut.txt"), "utf-8");
						while (scBrottmalsbeslut.hasNextLine()) {
							String line = scBrottmalsbeslut.nextLine();
							String[] words = line.split("%");
							cbAnt2.addItem(new ToolTipWrapper(words[0], words[1]));
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}

				if (btfta == "T") {
					cbAnt2.setModel(modelBeslutT);
					cbAnt2.removeAllItems();
					try {
						Scanner scTvistemalsbeslut = new Scanner(new File(dataMapp + "tvistemålsbeslut.txt"), "utf-8");
						while (scTvistemalsbeslut.hasNextLine()) {
							String line = scTvistemalsbeslut.nextLine();
							String[] words = line.split("%");
							cbAnt2.addItem(new ToolTipWrapper(words[0], words[1]));
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				cbAnt2.setSelectedItem("Beslut..");
				pnlAntBeslut.setVisible(true);
			} else if ((event.getStateChange() == ItemEvent.SELECTED) && (item.toString() == "Övrig anteckning")) {
				if (btfta == "B") {
					cbAnt2.setModel(modelAntB);
					cbAnt2.removeAllItems();
					try {
						Scanner scBrottmalsanteckningar = new Scanner(new File(dataMapp + "brottmålsanteckningar.txt"),
								"utf-8");
						while (scBrottmalsanteckningar.hasNextLine()) {
							String line = scBrottmalsanteckningar.nextLine();
							String[] words = line.split("%");
							cbAnt2.addItem(new ToolTipWrapper(words[0], words[1]));
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				if (btfta == "T") {
					cbAnt2.setModel(modelAntT);
					cbAnt2.removeAllItems();
					try {
						Scanner scTvistemalsanteckningar = new Scanner(
								new File(dataMapp + "tvistemålsanteckningar.txt"), "utf-8");
						while (scTvistemalsanteckningar.hasNextLine()) {
							String line = scTvistemalsanteckningar.nextLine();
							String[] words = line.split("%");
							cbAnt2.addItem(new ToolTipWrapper(words[0], words[1]));
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				cbAnt2.setSelectedItem("Anteckning..");
				pnlAntBeslut.setVisible(true);
			} else {
				cbAnt2.setVisible(false);
				cbAnt2.setSelectedItem(null);
			}
		}
	}

	private static void lasInFranTxtMedToolTip(String dokumentnamn, JComboBox cb) {
		try {
			Scanner scBrottmalsanteckningar = new Scanner(new File(dokumentnamn), "utf-8");
			while (scBrottmalsanteckningar.hasNextLine()) {
				String line = scBrottmalsanteckningar.nextLine();
				String[] words = line.split("%");
				cb.addItem(new ToolTipWrapper(words[0], words[1]));
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	private static void sattSkrivningar() {
		if (dokTyp.contains("Protokoll")) {
			dokumentKategori = "standardskrivningar_protokoll.txt";
		} else {
			dokumentKategori = "standardskrivningar_" + dokTyp + ".txt";
		}
		standardskrivningar = dataMapp + btfta + "\\" + dokumentKategori;
		dokspecskrivningar = dataMapp + btfta + "\\" + dokTyp + "\\" + underDokTyp + "\\typspecifika_skrivningar.txt";
		File fStndSkr = new File(standardskrivningar);
		File fDokspecSkr = new File(dokspecskrivningar);
		if (!fStndSkr.exists()) {
			try {
				FileOutputStream skapadok = new FileOutputStream(standardskrivningar);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
		}
		if (!fDokspecSkr.exists()) {
			try {
				FileOutputStream skapadok = new FileOutputStream(dokspecskrivningar);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
		}
	}

	private static void bestamInnehallCbAnt3(ItemEvent event) {
		Object item = event.getItem();
		if (event.getStateChange() == ItemEvent.SELECTED && item != null) {
			if (event.getStateChange() == ItemEvent.SELECTED && item.toString() == "vittne, närstående") {
				pnlAntNarstaende.setVisible(true);
			} else {
				pnlAntNarstaende.setVisible(false);
			}
			if (event.getStateChange() == ItemEvent.SELECTED && item.toString() != "tilltalad"
					&& item.toString() != "ställföreträdare, käranden"
					&& item.toString() != "ställföreträdare, svaranden" && item.toString() != "svarande"
					&& item.toString() != "kärande") {
				boxBegarErsattning.setVisible(true);
				if (cbAnt3.getSelectedItem().toString().contains("med..")) {
					boxBegarErsattning.setVisible(false);
				}
			} else {
				boxBegarErsattning.setVisible(false);
			}
			if (event.getStateChange() == ItemEvent.SELECTED && item.toString() == "Uppger följande") {
				pnlAntPersForh.setVisible(true);
				pnlAntPersutr.setVisible(false);
			} else if (event.getStateChange() == ItemEvent.SELECTED && item.toString() != "Uppger följande"
					&& cbAnt2.getSelectedItem().toString() == "Personutredning") {
				pnlAntPersForh.setVisible(false);
				pnlAntPersutr.setVisible(true);
			} else {
				pnlAntPersForh.setVisible(false);
				pnlAntPersutr.setVisible(false);
			}
			if (event.getStateChange() == ItemEvent.SELECTED && item.toString() == "åklagaren") {
				pnlSa.setVisible(true);
			} else {
				pnlSa.setVisible(false);
			}
		}
	}

	private static void bestamInnehallCbAnt2(ItemEvent event) {
		Object item = event.getItem();
		if (event.getStateChange() == ItemEvent.SELECTED && item != null) {
			cbAnt3.setVisible(true);
			cbAnt3.setEditable(true);
			pnlAntForhor.setVisible(false);
			pnlAnnUtr.setVisible(false);
			pnlAntPartOvr.setVisible(false);
			taAntBeslut1.setText(null);
			pnlBeslAvs.setVisible(false);
			if ((event.getStateChange() == ItemEvent.SELECTED)
					&& (item.toString() == "Domare") | (item.toString() == "Ansvarig för anteckningarna")) {
				cbAnt3.setModel(modelratten);
				cbAnt3.setSelectedItem("Domstolsperson..");
				cbAnt3.setEditable(false);
			} else if ((event.getStateChange() == ItemEvent.SELECTED) && (item.toString() == "Nämndeman")) {
				cbAnt3.setModel(modelNamndeman);
				cbAnt3.setSelectedItem("Nämndeman..");
			} else if (event.getStateChange() == ItemEvent.SELECTED && item.toString() == "Förhör") {
				pnlAntForhor.setVisible(true);
				if (btfta == "B") {
					cbAnt3.setModel(modelForhorRollB);
				}
				if (btfta == "T") {
					cbAnt3.setModel(modelForhorRollT);
				}
				cbAnt3.setSelectedItem("med..");
			} else if (event.getStateChange() == ItemEvent.SELECTED && item.toString() == "Annan bevisning/utredning") {
				if (btfta == "B") {
					cbAnt3.setModel(modelAnnUtrB);
				}
				if (btfta == "T") {
					cbAnt3.setModel(modelAnnUtrT);
				}
				pnlAnnUtr.setVisible(true);
				cbAnt3.setSelectedItem("Åberopad av..");
			} else if (event.getStateChange() == ItemEvent.SELECTED && item.toString() == "Personutredning") {
				cbAnt3.setModel(modelPersutr);
				cbAnt3.setSelectedItem("Välj utredning..");
				cbPersForh.setModel(modelTilltalad);
			} else if ((event.getStateChange() == ItemEvent.SELECTED)
					&& (item.toString() == "Part/ombud (annat än närvarande)" | item.toString() == "Övrig")) {
				boxTolk.setSelected(false);
				tfAntRoll.setEnabled(true);
				cbAnt3.setVisible(false);
				cbAnt3.setSelectedItem(null);
				pnlAntPartOvr.setVisible(true);
				if (item.toString() == "Part/ombud (annat än närvarande)") {
					boxTolk.setVisible(false);
				} else {
					boxTolk.setVisible(true);
				}
			} else if (event.getStateChange() == ItemEvent.SELECTED
					&& cbAnt1.getSelectedItem().toString() == "Beslut") {
				cbAnt3.setVisible(false);
				cbAnt3.setSelectedItem(null);
				cbAntBeslAvs.setModel(modelBeslAvs);
				if (btfta == "B") {
					try {
						Scanner scBrottmalsbeslut = new Scanner(new File(dataMapp + "brottmålsbeslut.txt"), "utf-8");
						ArrayList<String> brlista = new ArrayList<String>();
						while (scBrottmalsbeslut.hasNextLine()) {
							String line = scBrottmalsbeslut.nextLine();
							String[] words = line.split("%");
							if (words[0].contains(cbAnt2.getSelectedItem().toString())) {
								taAntBeslut1.setText(words[1]);
							}
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				if (btfta == "T") {
					try {
						Scanner scTvistemalsbeslut = new Scanner(new File(dataMapp + "tvistemålsbeslut.txt"), "utf-8");
						ArrayList<String> brlista = new ArrayList<String>();
						while (scTvistemalsbeslut.hasNextLine()) {
							String line = scTvistemalsbeslut.nextLine();
							String[] words = line.split("%");

							if (words[0].contains(cbAnt2.getSelectedItem().toString())) {

								taAntBeslut1.setText(words[1]);
							}
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				ComboBoxToolTipRenderer renderer = new ComboBoxToolTipRenderer();
				cbAnt2.setRenderer(renderer);
				if (taAntBeslut1.getText().contains("@")) {
					pnlBeslAvs.setVisible(true);
				}
			} else if (event.getStateChange() == ItemEvent.SELECTED
					&& cbAnt1.getSelectedItem().toString() == "Övrig anteckning") {
				cbAnt3.setVisible(false);
				cbAnt3.setSelectedItem(null);
				cbAntBeslAvs.setModel(modelBeslAvs);
				if (btfta == "B") {
					try {
						Scanner scBrottmalsanteckningar = new Scanner(new File(dataMapp + "brottmålsanteckningar.txt"),
								"utf-8");
						ArrayList<String> brlista = new ArrayList<String>();
						while (scBrottmalsanteckningar.hasNextLine()) {
							String line = scBrottmalsanteckningar.nextLine();
							String[] words = line.split("%");
							if (words[0].contains(cbAnt2.getSelectedItem().toString())) {
								taAntBeslut1.setText(words[1]);
							}
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				if (btfta == "T") {
					try {
						Scanner scTvistemalsanteckningar = new Scanner(
								new File(dataMapp + "tvistemålsanteckningar.txt"), "utf-8");
						ArrayList<String> brlista = new ArrayList<String>();
						while (scTvistemalsanteckningar.hasNextLine()) {
							String line = scTvistemalsanteckningar.nextLine();
							String[] words = line.split("%");
							if (words[0].contains(cbAnt2.getSelectedItem().toString())) {
								taAntBeslut1.setText(words[1]);
							}
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				}
				ComboBoxToolTipRenderer renderer = new ComboBoxToolTipRenderer();
				cbAnt2.setRenderer(renderer);
				if (taAntBeslut1.getText().contains("@")) {
					pnlBeslAvs.setVisible(true);
				}
			} else {
				cbAnt3.setVisible(false);
				cbAnt3.setSelectedItem(null);
			}
		}
	}

	private static void hamtaTextFranFil(String fil, String item, JTextArea ta) {
		try {
			Scanner scBrottmalsbeslut = new Scanner(new File(fil), "utf-8");
			ArrayList<String> brlista = new ArrayList<String>();
			while (scBrottmalsbeslut.hasNextLine()) {
				String line = scBrottmalsbeslut.nextLine();
				String[] words = line.split("%");

				if (words[0].contains(item)) {

					ta.setText(words[1]);
				}
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	private static void antLaggTill() {

		String[] dagSplit = cbAntDag.getSelectedItem().toString().split(" ");
		int intDag = Integer.parseInt(dagSplit[1]) - 1;

		Dag dag = daglista.get(intDag);

		StringBuilder sbDag = new StringBuilder();

		if (cbAnt1.getSelectedItem().toString() == "Tid") {

			String sPaus = new String(tfPausStart.getText() + "–" + tfPausSlut.getText());

			if (tfStarttid.getText().length() > 0) {
				dag.starttid = tfStarttid.getText();
			}
			if (tfSluttid.getText().length() > 0) {
				dag.sluttid = tfSluttid.getText();
			}

			if (!sPaus.equals("–")) {
				dag.paus = uppdateraNamn(dag.paus, sPaus);
			}
			dag.aktiv = true;
		}

		else if (cbAnt1.getSelectedItem().toString() == "Aktör") {

			if (cbAnt2.getSelectedItem().toString() == "Domare"
					&& cbAnt3.getSelectedItem().toString() != "Domstolsperson..") {

				aktor.domare = uppdateraNamn(aktor.domare, namnBeslutsfattare(cbAnt3));

			}

			if (cbAnt2.getSelectedItem().toString() == "Ansvarig för anteckningarna"
					&& cbAnt3.getSelectedItem().toString() != "Domstolsperson..") {

				aktor.ansvarig = uppdateraNamn(aktor.ansvarig, namnBeslutsfattare(cbAnt3));
			}

			if (cbAnt2.getSelectedItem().toString() == "Nämndeman"
					&& cbAnt3.getSelectedItem().toString() != "Nämndeman..") {

				aktor.namndeman = uppdateraNamn(aktor.namndeman, cbAnt3.getSelectedItem().toString());

				laggTillCbAlt(modelNamndeman, cbAnt3, "nämndemän");
			}

			if (cbAnt2.getSelectedItem().toString() == "Part/ombud (annat än närvarande)"
					&& tfAntNarvaro.getText().length() > 0) {

				aktor.part = uppdateraNamn2(aktor.part,
						tfAntPart.getText() + "; " + tfAntRoll.getText() + ", " + tfAntNarvaro.getText());
			}

			if (cbAnt2.getSelectedItem().toString() == "Övrig" && tfAntNarvaro.getText().length() > 0) {

				StringBuilder sbAkt = new StringBuilder(tfAntPart.getText() + "; ");
				if (boxTolk.isSelected()) {
					sbAkt.append(
							"tolk. Har avlagt generell tolked. Påminns om att eden fortfarande gäller samt om edens betydelse");
				} else {
					sbAkt.append(tfAntRoll.getText());
				}
				sbAkt.append(", " + tfAntNarvaro.getText());
				aktor.ovrig = uppdateraNamn2(aktor.ovrig, sbAkt.toString());
			}
			aktor.aktiv = true;
		}

		else if (cbAnt1.getSelectedItem().toString() == "Bevisning") {

			dag.bevisning = true;

			if (cbAnt2.getSelectedItem().toString() == "Förhör"
					&& !cbAnt3.getSelectedItem().toString().equals("med..")) {

				StringBuilder sbForhor = new StringBuilder(tfAntForhorNamn.getText() + "; ");

				if (cbAnt3.getSelectedItem().toString() == "vittne, närstående") {
					sbForhor.append("vittne");
				} else {
					sbForhor.append(cbAnt3.getSelectedItem().toString());
				}

				if (cbAntHorsPer.getSelectedItem().toString() != "Hörs på plats") {

					sbForhor.append(". " + cbAntHorsPer.getSelectedItem().toString());
				}

				if (cbAnt3.getSelectedItem().toString() == "vittne") {
					sbForhor.append(". Avlägger ed och påminns om edens vikt");
				}

				if (cbAnt3.getSelectedItem().toString().contains("kärande")) {
					sbForhor.append(". Avlägger sanningsförsäkran och påminns om vikten av denna");
				}

				if (cbAnt3.getSelectedItem().toString() == "vittne, närstående") {

					sbForhor.append(". " + cbAntNarstaende.getSelectedItem().toString() + " till "
							+ tfNarstaendeTill.getText() + "och vill vittna");
				}

				if (boxBegarErsattning.isSelected()) {

					sbForhor.append(". Begär ersättning för sin inställelse");
				}

				dag.forhor = uppdateraNamn2(dag.forhor, sbForhor.toString());

				if (cbAnt3.getSelectedItem().toString() == "tilltalad"
						| cbAnt3.getSelectedItem().toString() == "målsägande"
						| cbAnt3.getSelectedItem().toString() == "kärande"
						| cbAnt3.getSelectedItem().toString() == "svarande") {

					laggTillNamnCbModel(modelAnnUtrB, tfAntForhorNamn.getText());
					laggTillNamnCbModel(modelAnnUtrT, tfAntForhorNamn.getText());
					laggTillNamnCbModel(modelBeslAvs, tfAntForhorNamn.getText());

				}

				if (cbAnt3.getSelectedItem().toString() == "tilltalad") {

					laggTillNamnCbModel(modelTilltalad, tfAntForhorNamn.getText());
				}
			}

			if (cbAnt2.getSelectedItem().toString() == "Annan bevisning/utredning"
					&& cbAnt3.getSelectedItem().toString() != "Åberopad av..") {

				StringBuilder sbAktbil = new StringBuilder(tfAnnUtrAktb.getText());

				if (tfAntUtrAktbilS.getText().length() > 0) {

					sbAktbil.append(", s. " + tfAntUtrAktbilS.getText().replaceAll("-", "–"));
				}

				StringBuilder sbAberopad = new StringBuilder();

				if (tfSaAb.getText().length() > 0) {
					sbAberopad.append("Åberopad av " + cbAnt3.getSelectedItem().toString()
							+ "; se stämningsansökan, aktbil. " + tfSaAb.getText().replaceAll("-", "–"));
					if (tfAnnUtrAktb.getText().length() > 0) {
						sbAberopad.append(", samt aktbil. " + sbAktbil.toString());
					}
				} else if (tfAnnUtrAktb.getText().length() > 0) {
					sbAberopad.append(
							"Åberopad av " + cbAnt3.getSelectedItem().toString() + "; aktbil. " + sbAktbil.toString());
				}
				dag.annBev = uppdateraNamn2(dag.annBev, sbAberopad.toString());

				laggTillNamnCbModel(modelAnnUtrT, cbAnt3.getSelectedItem().toString());
				laggTillNamnCbModel(modelBeslAvs, cbAnt3.getSelectedItem().toString());
			}

			if (cbAnt2.getSelectedItem().toString() == "Personutredning"
					&& cbAnt3.getSelectedItem().toString() != "Välj utredning..") {

				if (cbAnt3.getSelectedItem().toString() == "Uppger följande") {

					dag.uppg = uppdateraNamn2(dag.uppg, cbPersForh.getSelectedItem().toString()
							+ " uppger följande om sina personliga förhållanden. " + taPersForh1.getText());
				} else {

					dag.persUtr = uppdateraNamn(dag.persUtr,
							cbAnt3.getSelectedItem().toString() + ", aktbil. " + tfAntPersutrAktbil.getText());
				}

				if (cbAnt3.getSelectedItem().toString() == "Uppger följande") {

					laggTillNamnCbModel(modelTilltalad, cbPersForh.getSelectedItem().toString());
					laggTillNamnCbModel(modelBeslAvs, cbPersForh.getSelectedItem().toString());
				}
			}
			dag.aktiv = true;
		}

		else if (cbAnt1.getSelectedItem().toString() == "Beslut" && taAntBeslut1.getText().length() > 0) {
			
			dag.beslut = uppdateraNamn2(dag.beslut, antBeslMedNamn());

			cbAnt2.setSelectedItem("Beslut..");

			if (cbAntBeslAvs.getSelectedItem() != null) {
				laggTillNamnCbModel(modelBeslAvs, cbAntBeslAvs.getSelectedItem().toString());
			}

			cbAntBeslAvs.setModel(modelBeslAvs);
			cbAntBeslAvs.setSelectedIndex(cbAntBeslAvs.getItemCount() - 1);
			dag.aktiv = true;
		}

		else if (cbAnt1.getSelectedItem().toString() == "Övrig anteckning" && taAntBeslut1.getText().length() > 0) {

			dag.ant = uppdateraNamn2(dag.ant, antBeslMedNamn());

			cbAnt2.setSelectedItem("Anteckning..");
			
			if (cbAntBeslAvs.getSelectedItem() != null) {
				laggTillNamnCbModel(modelBeslAvs, cbAntBeslAvs.getSelectedItem().toString());
			}

			cbAntBeslAvs.setModel(modelBeslAvs);
			cbAntBeslAvs.setSelectedIndex(cbAntBeslAvs.getItemCount() - 1);
			dag.aktiv = true;
		}

		daglista.set(intDag, dag);

		uppdateraDagdelVy();

	}

	private static void gaVidare() {
		utgangspunkt();
		if (tfmalnr.getText().length() > 0 && !dokTyp.equals("Dokumenttyp..")) {

			if (dokTyp.equals("Anteckningar, huvudförhandling")) {
				boolAnteckningar = true;
			} else {
				boolAnteckningar = false;
			}

			pnlSkapaOppnaDok.setVisible(true);
			datumArray.clear();

			if (!dokTyp.contains("Anteckningar")) {

				laggTillCbAlt(modelCbUnder1, cbunder1, btfta + "\\" + dokTyp);

				skapaCbmodel(modelDokAlt, btfta + "\\" + dokTyp + "\\" + cbunder1.getSelectedItem().toString(),
						"dokumentalternativ", cbDokAlt);
			}
			for (JComboBox b : cbListaNollstall) {
				b.setSelectedItem(null);
			}
			for (JPanel pnl : pnlListaNollstall) {
				pnl.setVisible(false);
			}
			for (DefaultListModel m : listmodelListaNollstall) {
				m.removeAllElements();
			}
			for (DefaultComboBoxModel cb : cbmodelListaNollstall) {
				cb.removeAllElements();
			}
			for (JList l : listListaNollstall) {

				DefaultListModel modelNollstall = new DefaultListModel();
				l.setModel(modelNollstall);
			}
			for (JTextArea ta : listTaNollstall) {
				ta.setText(null);
			}
			pnlB.removeAll();
			pnlAktorer.removeAll();

			// sattSkrivningar();

			cbAntDag.removeAllItems();

			StringBuilder sbDokText = new StringBuilder();
			if (!dokTyp.equals("Dokumenttyp..")) {
				try {
					XWPFDocument xdocoppnadok = new XWPFDocument(OPCPackage.open(veraDok()));
					XWPFWordExtractor exttaframtext = new XWPFWordExtractor(xdocoppnadok);
					String doktext = new String(exttaframtext.getText());
					sbDokText.append(doktext);
					xdocoppnadok.close();
					exttaframtext.close();

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(huvudmeny,
							"Det finns inga dokumentuppgifter att hämta för det angivna målnumret. Kontrollera att dokumentet är låst i Vera och inte öppet i Word.");
				}
			}
			String doktext = sbDokText.toString();

			if (doktext.length() > 1) {

				if (!dokTyp.equals("Anteckningar, huvudförhandling") && !doktext.contains("ANTECKNINGAR")) {

					mallPlats = dataMapp + btfta + "\\" + dokTyp + "\\" + cbunder1.getSelectedItem().toString() + "\\";
					stndMallNamn = cbunder1.getSelectedItem().toString() + "_standardmall.docx";
					mallNamn = cbunder1.getSelectedItem().toString() + "_";
					sattStandardMall();

					aktorlista.clear();
					pList.removeAll();
					pnlAktorer.removeAll();
					cbDokAlt.setSelectedItem("Dokumentalternativ..");
					modelValdaAlt.removeAllElements();
					listDokAlt.setModel(modelValdaAlt);
					pnldokalt.setVisible(true);
					pnlAktorer.setVisible(true);

					sDatum = doktext.split("	")[3];
					if (sDatum.length() != 10) {
						sDatum = null;
					}
					if (sDatum != null) {
						sOk3v = datumTillText(datumOk());
					}
					if (!doktext.contains("SLUTLIGT BESLUT")) {
						sSaken = sbDokText.toString().split("SAKEN\n")[1].split("\n")[0];
					}

					String[] partsplit1 = sbDokText.toString()
							.split("PARTER|SAKEN|ÖVERKLAGAT BESLUT|______________________");
					String[] partsplit2 = partsplit1[1].split("\n \n");
					List<String> listPartsplit2 = Arrays.asList(partsplit2);
					for (int parti = 0; parti < listPartsplit2.size(); parti++) {
						String s = listPartsplit2.get(parti);
						List<String> listAktor = new ArrayList<String>();
						String[] ss = s.split("\n");
						List<String> list2 = Arrays.asList(ss);
						for (int i = 0; i < list2.size(); i++) {
							if (!list2.get(i).equals("")) {
								if (i == 0 && list2.get(i).contains(": ")) {
									String[] ss2 = list2.get(i).split(": ");
									listAktor.add(ss2[0] + ":");
									listAktor.add(ss2[1]);
								} else {
									listAktor.add(list2.get(i));
									if (i == 1) {
									}
								}
							}
						}
						StringBuilder sbUppg = new StringBuilder();
						for (int i = 1; i < listAktor.size(); i++) {

							if (i != 1) {
								sbUppg.append("\n");
							}

							sbUppg.append(listAktor.get(i));
						}
						GridBagConstraints gbc = new GridBagConstraints();
						gbc.insets = new Insets(5, 5, 5, 5);
						gbc.anchor = GridBagConstraints.WEST;
						gbc.weightx = 1;
						gbc.weighty = 1;
						gbc.gridx = 0;
						gbc.gridy = GridBagConstraints.RELATIVE;
						gbc.fill = GridBagConstraints.HORIZONTAL;

						ProtAktor aktor = new ProtAktor();
						aktor.titel = listAktor.get(0);
						aktor.aktor = sbUppg.toString();
						aktorlista.add(aktor);
						pList.add(aktorPanel(aktor.titel, aktor.aktor, parti), gbc);
					}

					JScrollPane scrErsFork = new JScrollPane(pList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
							JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					scrErsFork.setBounds(0, 275, 309, 408);
					scrErsFork.getVerticalScrollBar().setBackground(SystemColor.control);

					pnlAktorer.add(scrErsFork);

					pnlAktorer.revalidate();
					pnlAktorer.repaint();
					pList.revalidate();
					pList.repaint();

					if (dokTyp.contains("Protokoll") | dokTyp == "Beslut" | dokTyp == "Beslut, ej särskilt uppsatt"
							| dokTyp == "Protokoll med beslut") {

						pnlratten.setVisible(true);
					}
					laggTillAktorIAktorLista();
				}

				else if (dokTyp.contains("Anteckningar") && doktext.contains("ANTECKNINGAR")) {

					String[] datumsplit = doktext.split("ANTECKNINGAR	|	vid huvudförhandling");
					String[] datumsplit2 = datumsplit[1].split("\n|	");
					List<String> datumList = Arrays.asList(datumsplit2);
					int dagar = 1;

					for (int datumi = 0; datumi < datumList.size(); datumi++) {

						modelAntDatum.addElement(datumList.get(datumi));

						if (datumList.size() > 1) {
							if (datumList.get(datumi).contains("--")) {
								String[] ssplit = datumList.get(datumi).split("-| -- ");
								int dagStart = Integer.parseInt(ssplit[2]);
								int dagSlut = Integer.parseInt(ssplit[5]);
								int manStart = Integer.parseInt(ssplit[1]);
								int manSlut = Integer.parseInt(ssplit[4]);
								if (manStart == manSlut) {
									dagar = dagar + dagSlut - dagStart;
									dagar = dagar;
								} else {
									if (manStart == 1 | manStart == 3 | manStart == 5 | manStart == 7 | manStart == 8
											| manStart == 10) {
										dagar = dagar + (31 - dagStart + dagSlut);
									} else if (manStart == 4 | manStart == 6 | manStart == 9 | manStart == 11) {
										dagar = dagar + (30 - dagStart + dagSlut);
									} else if (manStart == 2) {
										dagar = dagar + (28 - dagStart + dagSlut);
									}
									dagar = dagar;
								}
							} else {
								dagar = dagar + 1;
							}
						}
					}
					fyllDatumArray();

					for (int i = 1; i < dagar + 1; i++) {

						cbAntDag.addItem("Dag " + i);
					}

					daglista.clear();

					for (int i = 0; i < dagar; i++) {

						Dag dag = new Dag();
						dag.aktiv = false;
						daglista.add(dag);
					}

					aktor.aktiv = false;

					uppdateraDagdelVy();

					if (modelAntDatum.size() > 1) {
						cbAntDag.setVisible(true);
					} else {
						cbAntDag.setVisible(false);
					}

					JScrollPane scrDagar = new JScrollPane(pList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
							JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					scrDagar.setBounds(0, 275, 309, 408);
					scrDagar.getVerticalScrollBar().setBackground(SystemColor.control);

					pnlB.add(scrDagar);
					pnlB.revalidate();

					cbAnt1.setEditable(true);
					cbAnt1.setSelectedItem("Lägg till ny(tt)..");
					cbAnt1.setEditable(false);

					pnlAnt.setVisible(true);
				}
			}
		}
	}

	private static void sparaBeslut() {
		if (taAntBeslut1.getText().length() > 0 && cbAnt2.getSelectedItem().toString() != "Beslut.."
				&& cbAnt2.getSelectedItem().toString() != "Anteckning..") {
			if (btfta == "B") {
				if (cbAnt1.getSelectedItem().toString() == "Beslut") {
					sparaBeslAnt(dataMapp + "brottmålsbeslut.txt", cbAnt2, taAntBeslut1, modelBeslutB);
				}
				if (cbAnt1.getSelectedItem().toString() == "Övrig anteckning") {
					sparaBeslAnt(dataMapp + "brottmålsanteckningar.txt", cbAnt2, taAntBeslut1, modelAntB);
				}
			}
			if (btfta == "T") {
				if (cbAnt1.getSelectedItem().toString() == "Beslut") {
					sparaBeslAnt(dataMapp + "tvistemålsbeslut.txt", cbAnt2, taAntBeslut1, modelBeslutT);
				}
				if (cbAnt1.getSelectedItem().toString() == "Övrig anteckning") {
					sparaBeslAnt(dataMapp + "tvistemålsanteckningar.txt", cbAnt2, taAntBeslut1, modelAntT);
				}
			}
		}
	}

	private static void skapaDokument() {

		if (cbdoktyp.getSelectedItem() != null) {

			String sAktbil = JOptionPane.showInputDialog(null, "Aktbilaga");

			StringBuilder sbDokNamn = new StringBuilder();

			if (boolAnteckningar == true) {
				sbDokNamn.append(dataMapp + "anteckningar_template.docx");
			} else {
				if (mall == null) {
					File fStndMall = new File(stndMall);
					skapaStndMallOmEjFinns(fStndMall);
					sbDokNamn.append(stndMall);
				} else {
					sbDokNamn.append(mall);
				}
			}

			try {
				FileInputStream hamtaTemplate = new FileInputStream(sbDokNamn.toString());
				FileOutputStream skapaTemp = new FileOutputStream(systemMapp + "temp.docx");
				byte[] buffer = new byte[1024];
				int length;
				while ((length = hamtaTemplate.read(buffer)) > 0) {
					skapaTemp.write(buffer, 0, length);
				}
				hamtaTemplate.close();
				skapaTemp.close();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
			try {
				XWPFDocument doc = new XWPFDocument(OPCPackage.open(systemMapp + "temp.docx"));

				if (dokTyp == "Anteckningar, huvudförhandling") {

					if (daglista.size() == 1) {
						XWPFParagraph parTid = doc.createParagraph();
						parTid.setIndentationLeft(3912);
						XWPFRun runTid1 = parTid.createRun();
						if (daglista.get(0).starttid != null | daglista.get(0).sluttid != null) {
							runTid1.setText("Tid: " + daglista.get(0).starttid + "–" + daglista.get(0).sluttid);
						}
						if (daglista.get(0).paus != null) {
							StringBuilder sbPaus = new StringBuilder("Paus: ");
							XWPFRun runTid2 = parTid.createRun();
							runTid2.addBreak();
							runTid2.setItalic(true);
							for (int i = 0; i < Arrays.asList(daglista.get(0).paus.split("\n")).size(); i++) {
								if (i > 0) {
									sbPaus.append(", ");
								}
								sbPaus.append(Arrays.asList(daglista.get(0).paus.split("\n")).get(i));
							}
							runTid2.setText(sbPaus.toString());
						}
						XWPFParagraph parNy = doc.createParagraph();
					}

					XWPFParagraph parRatten = doc.createParagraph();
					XWPFRun runRatten1 = parRatten.createRun();
					runRatten1.setBold(true);
					runRatten1.setText("RÄTTEN");
					XWPFRun runRatten2 = parRatten.createRun();
					runRatten2.addBreak();
					StringBuilder sbRatten = new StringBuilder();
					domareAnsvIDok(sbRatten, aktor.domare);
					if (aktor.namndeman != null) {
						if (Arrays.asList(aktor.namndeman.split("\n")).size() > 0) {
							sbRatten.append(" samt nämndemännen ");
						}
						for (int i = 0; i < Arrays.asList(aktor.namndeman.split("\n")).size(); i++) {
							if (i > 0) {
								if (i != Arrays.asList(aktor.namndeman.split("\n")).size() - 1) {
									sbRatten.append(", ");
								} else {
									sbRatten.append(" och ");
								}
								sbRatten.append(Arrays.asList(aktor.namndeman.split("\n")).get(i));
							} else {
								sbRatten.append(Arrays.asList(aktor.namndeman.split("\n")).get(i));
							}
						}
					}
					runRatten2.setText(sbRatten.toString());

					XWPFParagraph parAnsv0 = doc.createParagraph();
					XWPFParagraph parAnsv = doc.createParagraph();
					XWPFRun runAnsv1 = parAnsv.createRun();
					runAnsv1.setBold(true);
					runAnsv1.setText("ANSVARIG FÖR ANTECKNINGARNA");
					XWPFRun runAnsv2 = parAnsv.createRun();
					runAnsv2.addBreak();
					StringBuilder sbAnsv = new StringBuilder();
					domareAnsvIDok(sbAnsv, aktor.ansvarig);
					runAnsv2.setText(sbAnsv.toString());

					XWPFParagraph parPart0 = doc.createParagraph();
					XWPFParagraph parPart = doc.createParagraph();
					XWPFRun runPart1a = parPart.createRun();
					runPart1a.setBold(true);
					runPart1a.setText("PARTER");
					XWPFRun runPart1b = parPart.createRun();
					runPart1b.setText(" jämte försvarare/ombud/biträde");
					runPart1b.addBreak();
					runPart1b.setText("se dom (närvarande om inte annat anges)");
					if (aktor.part != null) {
						for (int i = 0; i < Arrays.asList(aktor.part.split("\n\n")).size(); i++) {
							String s = new String(Arrays.asList(aktor.part.split("\n\n")).get(i));
							String ss[] = s.split("; |, ");
							XWPFRun runPart2 = parPart.createRun();
							runPart2.addBreak();
							runPart2.addBreak();
							runPart2.setText(ss[0] + "; " + ss[1]);
							XWPFRun runPart3 = parPart.createRun();
							runPart3.setItalic(true);
							runPart3.addBreak();
							runPart3.setText(ss[2]);
						}
					}

					if (aktor.ovrig != null) {
						for (int i = 0; i < Arrays.asList(aktor.ovrig.split("\n\n")).size(); i++) {
							if (i == 0) {
								XWPFParagraph parOvr0 = doc.createParagraph();
								XWPFParagraph parOvr = doc.createParagraph();
								XWPFRun runOvr1a = parOvr.createRun();
								runOvr1a.setBold(true);
								runOvr1a.setText("ÖVRIGA");
								XWPFRun runOvr1b = parOvr.createRun();
								runOvr1b.setText(" (närvarande om inte annat anges)");
							}
							String s = new String(Arrays.asList(aktor.ovrig.split("\n\n")).get(i));
							String ss[] = s.split("; |, ");
							XWPFParagraph parOvr1 = doc.createParagraph();
							XWPFRun runOvr2 = parOvr1.createRun();
							if (i != 0) {
								runOvr2.addBreak();
							}
							runOvr2.setText(ss[0] + "; " + ss[1]);
							XWPFRun runOvr3 = parOvr1.createRun();
							runOvr3.setItalic(true);
							runOvr3.addBreak();
							runOvr3.setText(ss[2]);
						}
					}

					XWPFParagraph parYrkanden0 = doc.createParagraph();
					XWPFParagraph parYrkanden = doc.createParagraph();
					XWPFRun runYrkanden1 = parYrkanden.createRun();
					runYrkanden1.setBold(true);
					runYrkanden1.setText("YRKANDEN M.M.");
					XWPFParagraph parYrkanden1 = doc.createParagraph();
					XWPFRun runYrkanden2 = parYrkanden1.createRun();
					runYrkanden2.setText("Se dom");

					for (int i = 0; i < datumArray.size(); i++) {
						if (datumArray.size() > 1) {
							XWPFParagraph parDag0 = doc.createParagraph();
							XWPFParagraph parDag = doc.createParagraph();
							XWPFRun runDag1 = parDag.createRun();
							runDag1.setUnderline(UnderlinePatterns.SINGLE);
							runDag1.setText("Dag " + String.valueOf(i + 1) + " – " + datumTillText(datumArray.get(i)));
							XWPFRun runDag2 = parDag.createRun();
							if (daglista.get(i).starttid != null | daglista.get(i).sluttid != null) {
								runDag2.setText("	Tid: " + daglista.get(i).starttid + "–" + daglista.get(i).sluttid);
							}
							if (daglista.get(i).paus != null) {
								StringBuilder sbPaus = new StringBuilder("Paus: ");
								XWPFParagraph parDag1 = doc.createParagraph();
								parDag1.setIndentationLeft(3912);
								XWPFRun runDag11 = parDag1.createRun();
								runDag11.setItalic(true);
								for (int ii = 0; ii < Arrays.asList(daglista.get(i).paus.split("\n")).size(); ii++) {
									if (ii > 0) {
										sbPaus.append(", ");
									}
									sbPaus.append(Arrays.asList(daglista.get(i).paus.split("\n")).get(ii));
								}
								runDag11.setText(sbPaus.toString());
							}
						}

						if (daglista.get(i).bevisning == true) {
							XWPFParagraph parBevisning0 = doc.createParagraph();
							XWPFParagraph parBevisning = doc.createParagraph();
							XWPFRun runBevisning1 = parBevisning.createRun();
							runBevisning1.setBold(true);
							runBevisning1.setText("BEVISNING");
						}

						if (daglista.get(i).forhor != null) {
							for (int ii = 0; ii < Arrays.asList(daglista.get(i).forhor.split("\n\n")).size(); ii++) {

								if (ii == 0) {
									XWPFParagraph parForhor0 = doc.createParagraph();
									XWPFParagraph parForhor = doc.createParagraph();
									XWPFRun runForhor1 = parForhor.createRun();
									runForhor1.setBold(true);
									runForhor1.setText("Förhör:");
									XWPFRun runForhor2 = parForhor.createRun();
									runForhor2.setText(" (upptagna i Vera)");
								}
								XWPFParagraph parForhor1 = doc.createParagraph();
								String s = new String(Arrays.asList(daglista.get(i).forhor.split("\n\n")).get(ii));
								XWPFRun runForhor3 = parForhor1.createRun();
								if (ii != 0) {
									runForhor3.addBreak();
								}
								runForhor3.setText(s);
							}
						}

						if (daglista.get(i).annBev != null) {

							for (int ii = 0; ii < Arrays.asList(daglista.get(i).annBev.split("\n\n")).size(); ii++) {

								if (ii == 0) {
									XWPFParagraph parAnnUtr0 = doc.createParagraph();
									XWPFParagraph parAnnUtr = doc.createParagraph();
									XWPFRun runAnnUtr1 = parAnnUtr.createRun();
									runAnnUtr1.setBold(true);
									runAnnUtr1.setText("Annan bevisning/utredning som lagts fram:");
								}
								XWPFParagraph parAnnUtr1 = doc.createParagraph();
								String s = new String(Arrays.asList(daglista.get(i).annBev.split("\n\n")).get(ii));
								XWPFRun runAnnUtr2 = parAnnUtr1.createRun();
								if (ii != 0) {
									runAnnUtr2.addBreak();
								}
								runAnnUtr2.setText(s);
							}
						}

						if (daglista.get(i).persUtr != null) {
							for (int ii = 0; ii < Arrays.asList(daglista.get(i).persUtr.split("\n")).size(); ii++) {

								if (ii == 0) {
									XWPFParagraph parPersutr0 = doc.createParagraph();
									XWPFParagraph parPersutr = doc.createParagraph();
									XWPFRun runPersutr1 = parPersutr.createRun();
									runPersutr1.setBold(true);
									runPersutr1.setText("Personutredning som föredragits:");
								}
								XWPFParagraph parPersutr1 = doc.createParagraph();
								String s = new String(Arrays.asList(daglista.get(i).persUtr.split("\n")).get(ii));
								XWPFRun runPersutr2 = parPersutr1.createRun();
								if (ii != 0) {
									runPersutr2.addBreak();
								}
								runPersutr2.setText(s);
							}
						}

						if (daglista.get(i).uppg != null) {
							for (int ii = 0; ii < Arrays.asList(daglista.get(i).uppg.split("\n\n")).size(); ii++) {
								XWPFParagraph parPersForh0 = doc.createParagraph();
								XWPFParagraph parPersForh = doc.createParagraph();
								String s = new String(Arrays.asList(daglista.get(i).uppg.split("\n\n")).get(ii));
								XWPFRun runPersForh1 = parPersForh.createRun();
								runPersForh1.setText(s);
							}
						}

						if (daglista.get(i).beslut != null) {
							XWPFParagraph parBeslut0 = doc.createParagraph();
							XWPFParagraph parBeslut = doc.createParagraph();
							XWPFRun runBeslut1 = parBeslut.createRun();
							runBeslut1.setBold(true);
							runBeslut1.setText("BESLUT");
						}

						if (daglista.get(i).beslut != null) {
							for (int ii = 0; ii < Arrays.asList(daglista.get(i).beslut.split("\n\n")).size(); ii++) {
								if (ii != 0) {
									XWPFParagraph parBeslut0 = doc.createParagraph();
								}
								XWPFParagraph parBeslut = doc.createParagraph();
								String s = new String(Arrays.asList(daglista.get(i).beslut.split("\n\n")).get(ii));
								XWPFRun runBeslut1 = parBeslut.createRun();
								runBeslut1.setText(s);
							}
						}

						if (daglista.get(i).ant != null) {
							XWPFParagraph parOvrAnt0 = doc.createParagraph();
							XWPFParagraph parOvrAnt = doc.createParagraph();
							XWPFRun runOvrAnt1 = parOvrAnt.createRun();
							runOvrAnt1.setBold(true);
							runOvrAnt1.setText("ÖVRIGA ANTECKNINGAR");
						}

						if (daglista.get(i).ant != null) {
							for (int ii = 0; ii < Arrays.asList(daglista.get(i).ant.split("\n\n")).size(); ii++) {
								if (ii != 0) {
									XWPFParagraph parovrAnt0 = doc.createParagraph();
								}
								XWPFParagraph parovrAnt = doc.createParagraph();
								String s = new String(Arrays.asList(daglista.get(i).ant.split("\n\n")).get(ii));
								XWPFRun runOvrAnt1 = parovrAnt.createRun();
								runOvrAnt1.setText(s);
							}
						}
					}
					XWPFParagraph parAnsvarig0 = doc.createParagraph();
					XWPFParagraph parAnsvarig00 = doc.createParagraph();
					XWPFParagraph parAnsvarig000 = doc.createParagraph();
					XWPFParagraph parAnsvarig = doc.createParagraph();
					XWPFRun runAnsvarig = parAnsvarig.createRun();

					StringBuilder sbNamn = new StringBuilder();
					if (aktor.ansvarig != null) {
						for (int i = 0; i < Arrays.asList(aktor.ansvarig.split("\n")).size(); i++) {
							if (i > 0) {
								sbNamn.append(", ");
							}
							String s = new String(Arrays.asList(aktor.ansvarig.split("\n")).get(i));
							for (int ii = 1; ii < Arrays.asList(s.split(" ")).size(); ii++) {
								if (ii != 1) {
									sbNamn.append(" ");
								}
								sbNamn.append(Arrays.asList(s.split(" ")).get(ii));
							}
						}
					}
					runAnsvarig.setText(sbNamn.toString());

					for (Dag dag : daglista) {
						if (dag.beslut != null) {
							XWPFParagraph parAntUppv = doc.createParagraph();
							XWPFRun runAntUppv = parAntUppv.createRun();
							runAntUppv.setText("Anteckningarna uppvisade/");
							break;
						}
					}

					StringBuilder sbDatum = new StringBuilder();
					for (int ii = 0; ii < modelAntDatum.size(); ii++) {
						if (ii > 0) {
							sbDatum.append(", ");
						}
						sbDatum.append(modelAntDatum.getElementAt(ii));
					}
					ersattVariabel(doc, "@datum@", sbDatum.toString());
				} else {

					for (UtvaldErsatt ue : listaUtvaldaErsatt) {
						if (ue.ersatt != null) {
							ersattVariabel(doc, "#" + ue.attErsatta + "#", ue.ersatt);
						}
					}

					laggTillDomstolsaktor(doc);
					laggTillAktorsuppg(doc);
					XWPFParagraph AnsvPar0 = doc.createParagraph();
					XWPFParagraph AnsvPar00 = doc.createParagraph();
					XWPFParagraph AnsvPar000 = doc.createParagraph();
					XWPFParagraph AnsvPar = doc.createParagraph();
					XWPFRun AnsvRun = AnsvPar.createRun();
					StringBuilder sbAnsv = new StringBuilder();
					if (cbratten.getSelectedItem().toString() != "Rätten") {
						if (cbprotokollforare.getSelectedItem().toString() != "prot.förare") {
							for (int ii = 1; ii < Arrays.asList(namnBeslutsfattare(cbprotokollforare).split(" "))
									.size(); ii++) {
								if (ii != 1) {
									sbAnsv.append(" ");
								}
								sbAnsv.append(Arrays.asList(namnBeslutsfattare(cbprotokollforare).split(" ")).get(ii));
							}
							AnsvRun.setText(sbAnsv.toString());
							AnsvRun.addBreak();
							AnsvRun.setText("Protokollet uppvisat/");
						} else {
							for (int ii = 1; ii < Arrays.asList(namnBeslutsfattare(cbratten).split(" ")).size(); ii++) {
								if (ii != 1) {
									sbAnsv.append(" ");
								}
								sbAnsv.append(Arrays.asList(namnBeslutsfattare(cbratten).split(" ")).get(ii));
							}
							AnsvRun.setText(sbAnsv.toString());
						}
					}
				}
				ersattVariabel(doc, "@avdelning@", hamtaAvdelning());
				ersattVariabel(doc, "@målnr@", btfta + " " + tfmalnr.getText());
				ersattVariabel(doc, "@dokab@", sAktbil);
				sattDokID(doc, cbdoktyp, cbunder1);

				FileOutputStream textut = new FileOutputStream(veraDok());

				doc.write(textut);

				textut.close();
				doc.close();

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
		}
		huvudmeny.setFocusCycleRoot(true);
	}

	public static void uppdateraDagdelVy() {

		pList.removeAll();

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(3, 3, 3, 3);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		if (aktor.aktiv) {
			pList.add(antAktorPanel("Aktörer"), gbc);
		}

		for (int dagindex = 0; dagindex < daglista.size(); dagindex++) {
			Dag dag = daglista.get(dagindex);
			if (dag.aktiv) {
				StringBuilder sbDag1 = new StringBuilder();
				if (daglista.size() > 1) {
					sbDag1.append(
							"Dag " + String.valueOf(dagindex + 1) + " – " + datumTillText(datumArray.get(dagindex)));
				}
				JPanel dagen = dagPanel(sbDag1.toString());
				pList.add(dagen, gbc);
				laggTillDagDel(dag, dagen, dagindex);
			}
		}
		pList.revalidate();
		pList.repaint();

		antCleanup();
	}

	private static String uppdateraNamn(String sFinns, String sLaggTill) {

		StringBuilder sb = new StringBuilder();
		if (sFinns != null) {
			sb.append(sFinns + "\n");
		}
		sb.append(sLaggTill);
		return sb.toString();
	}

	private static String uppdateraNamn2(String sFinns, String sLaggTill) {

		StringBuilder sb = new StringBuilder();
		if (sFinns != null) {
			sb.append(sFinns + "\n\n");
		}
		sb.append(sLaggTill);
		return sb.toString();
	}

	private static void laggTillDagDel(Dag dag, JPanel dagen, int dagindex) {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;

		if (dag.starttid != (null) | dag.sluttid != (null) | dag.paus != null) {
			dagen.add(dagUnderPanelTid(dagindex, "Tid", dag), gbc);
		}

		if (dag.bevisning == true) {
			dagen.add(dagUnderPanelBev(dag, "Bevisning", dagindex), gbc);
		}

		if (dag.beslut != null) {
			dagen.add(dagUnderPanelBesl(dagindex, "Beslut", dag), gbc);
		}

		if (dag.ant != null) {
			dagen.add(dagUnderPanelAnt(dagindex, "Övriga anteckningar", dag), gbc);
		}
	}

	private static void antCleanup() {
		tfStarttid.setText(null);
		tfSluttid.setText(null);
		tfPausStart.setText(null);
		tfPausSlut.setText(null);

		tfAntPart.setText(null);
		tfAntRoll.setText(null);
		tfAntNarvaro.setText(null);

		tfNarstaendeTill.setText(null);
		tfAntForhorNamn.setText(null);
		boxBegarErsattning.setSelected(false);
		tfAntUtrAktbilS.setText(null);
		tfSaAb.setText(null);
		tfAnnUtrAktb.setText(null);

		taPersForh1.setText(null);
		tfAntPersutrAktbil.setText(null);
	}

	private static void laggTillPerson() {

		JDialog laggtillny = new JDialog(huvudmeny);
		laggtillny.setBounds(110, 80, 300, 190);
		laggtillny.getContentPane().setLayout(null);

		laggtillny.getContentPane().add(UIhelp.nyLabel("Titel:", 10, 11, 46, 14));
		laggtillny.getContentPane().add(UIhelp.nyLabel("Namn:", 10, 44, 46, 14));
		laggtillny.getContentPane().add(UIhelp.nyLabel("Förkortning:", 10, 74, 73, 14));

		JTextField tftitel = UIhelp.nyTextField(82, 7, 156, 22, laggtillny);
		JTextField tfnamn = UIhelp.nyTextField(82, 40, 156, 22, laggtillny);
		JTextField tfforkortning = UIhelp.nyTextField(82, 73, 156, 22, laggtillny);

		JButton btnspara = new JButton("Spara");
		btnspara.setBounds(93, 120, 89, 23);
		laggtillny.getContentPane().add(btnspara);

		laggtillny.setVisible(true);
		btnspara.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				sparaBeslutsfattare(tfforkortning, tftitel, tfnamn, cbratten, cbprotokollforare, laggtillny,
						modelratten, modelprotokollforare);

			}
		});
	}

	private static void laggTillCbAlt(DefaultComboBoxModel model, JComboBox cb, String doknamn) {
		try {

			boolean b;
			b = false;
			String s = new String(cb.getSelectedItem().toString());
			for (int i = 0; i < model.getSize(); i++) {

				String s1 = model.getElementAt(i).toString();
				if (s.equals(s1)) {

					b = true;
					break;
				}
			}

			if (b == false) {

				int d = JOptionPane.showConfirmDialog(null,
						"Det valda alternativet finns inte sparat. Vill du lägga till det i listan över sparade alternativ?",
						"Lägg till alternativ", JOptionPane.YES_NO_OPTION);
				if (d == JOptionPane.YES_OPTION) {

					FileWriter nybeslutsfattare = new FileWriter(dataMapp + doknamn + ".txt", true);
					PrintWriter skiljetecken = new PrintWriter(nybeslutsfattare);

					BufferedReader reader = new BufferedReader(new FileReader(dataMapp + doknamn + ".txt"));
					int lines = 0;
					while (reader.readLine() != null)
						lines++;
					reader.close();

					if (lines > 0) {
						skiljetecken.write("\n");
					}
					nybeslutsfattare.write(s);
					nybeslutsfattare.close();
					model.addElement(s);
					cb.setModel(model);
				}
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	private static void skapaCbmodel(DefaultComboBoxModel model, String sMapp, String sFil, JComboBox cb) {
		StringBuilder sparaFil = new StringBuilder(dataMapp + "\\" + sMapp + "\\");
		File dokTyp = new File(sparaFil.toString());
		boolean skpstndmapp = dokTyp.mkdirs();
		sparaFil.append(sFil + ".txt");
		File dokTyp2 = new File(sparaFil.toString());
		model.removeAllElements();
		if (!dokTyp2.exists()) {
			try {
				FileOutputStream skapaDokTyp = new FileOutputStream(sparaFil.toString());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
		} else {
			try {
				Scanner scDokTyp = new Scanner(dokTyp2, "utf-8");
				while (scDokTyp.hasNextLine()) {
					String line = scDokTyp.nextLine();
					model.addElement(line);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
		}
		cb.setModel(model);
	}

	private static void sparaBeslutsfattare(JTextField tfforkortning, JTextField tftitel, JTextField tfnamn,
			JComboBox cbratten, JComboBox cbprotokollforare, JDialog dialog, DefaultComboBoxModel modelratten,
			DefaultComboBoxModel modelprotokollforare) {
		try {
			FileWriter nybeslutsfattare = new FileWriter(dataMapp + "beslutsfattare.txt", true);
			PrintWriter skiljetecken = new PrintWriter(nybeslutsfattare);
			BufferedReader reader = new BufferedReader(new FileReader(dataMapp + "beslutsfattare.txt"));
			int lines = 0;
			while (reader.readLine() != null)
				lines++;
			reader.close();
			if (lines > 0) {
				skiljetecken.write("\n");
			}
			tfforkortning.write(nybeslutsfattare);
			skiljetecken.write("%");
			tftitel.write(nybeslutsfattare);
			skiljetecken.write(" ");
			tfnamn.write(nybeslutsfattare);
			nybeslutsfattare.close();
			modelratten.addElement(tfforkortning.getText());
			modelprotokollforare.addElement(tfforkortning.getText());
			cbratten.setModel(modelratten);
			cbratten.setSelectedItem("Rätten");
			cbprotokollforare.setModel(modelprotokollforare);
			cbprotokollforare.setSelectedItem("prot.förare");

			dialog.dispose();

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	private static void bestInnehCbox(ItemEvent event, String cbalt, JComboBox cb, DefaultComboBoxModel cbmodel) {

		Object item = event.getItem();
		if ((event.getStateChange() == ItemEvent.SELECTED) && (item.toString() == cbalt)) {
			cb.setModel(cbmodel);
			cb.setSelectedItem(null);
			cb.setVisible(true);
		}
		if ((event.getStateChange() == ItemEvent.SELECTED) && (item.toString() == null)) {
			cb.setVisible(false);
			cb.setSelectedItem(null);
		}
	}

	private static void sorteraLista(JList lista) {
		ListModel model = lista.getModel();
		int n = model.getSize();
		String[] ord = new String[n];
		for (int i = 0; i < n; i++) {
			ord[i] = (String) model.getElementAt(i);
		}
		Arrays.sort(ord);
		lista.setListData(ord);
	}

	private static String skapaDokNamn(JList lista) {
		ListModel model = lista.getModel();
		int n = model.getSize();
		String[] ord = new String[n];
		StringBuilder dokNamn = new StringBuilder();

		for (int i = 0; i < n; i++) {
			ord[i] = (String) model.getElementAt(i);
			if (i > 0) {
				dokNamn.append("_");
			}
			if (ord[i].toString().length() > 5) {
				dokNamn.append(ord[i].substring(0, 5));
			} else {
				dokNamn.append(ord[i]);
			}
		}
		return dokNamn.toString() + ".docx";
	}

	private static void sattStandardMall() {
		File skapastndmappar = new File(mallPlats);
		boolean skpstndmapp = skapastndmappar.mkdirs();
		String stnddokumentnamn = new String(mallPlats + stndMallNamn);
		stndMall = stnddokumentnamn;
	}

	private static void skapaTomtDokOmInteFinns() {
		try {
			FileInputStream hamtatemplate = new FileInputStream(new File(dataMapp + "\\" + "protokoll_template.docx"));
			FileOutputStream skapadok = new FileOutputStream(stndMall);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = hamtatemplate.read(buffer)) > 0) {
				skapadok.write(buffer, 0, length);
			}
			hamtatemplate.close();
			skapadok.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	private static void oppnaMall(File mall) {
		try {
			Desktop oppnadok = Desktop.getDesktop();
			oppnadok.open(mall);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	private static void skapaMallFranStndMall(File fran, File till) {
		try {
			FileInputStream hamtamall = new FileInputStream(fran);
			FileOutputStream skapadok = new FileOutputStream(till);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = hamtamall.read(buffer)) > 0) {
				skapadok.write(buffer, 0, length);
			}
			hamtamall.close();
			skapadok.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	private static void skapaStndMallOmEjFinns(File fStndMall) {

		if (!fStndMall.exists()) {
			skapaTomtDokOmInteFinns();
		}
	}

	private static void ersattVariabel(XWPFDocument doc, String strAttErsatta, String strErsattning) {
		if (strErsattning != null) {
			for (XWPFParagraph p : doc.getParagraphs()) {
				List<XWPFRun> runs = p.getRuns();
				if (runs != null) {
					for (XWPFRun r : runs) {
						String text = r.getText(0);
						if (text != null && text.contains(strAttErsatta)) {
							text = text.replace(strAttErsatta, strErsattning);
							r.setText(text, 0);
						}
					}
				}
			}
			XWPFHeader h = doc.getHeaderFooterPolicy().getFirstPageHeader();
			for (XWPFTable t : h.getTables()) {
				for (XWPFTableRow row : t.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						for (XWPFParagraph p : cell.getParagraphs()) {
							for (XWPFRun r : p.getRuns()) {
								String text = r.getText(0);
								if (text != null && text.contains(strAttErsatta)) {
									text = text.replace(strAttErsatta, strErsattning);
									r.setText(text, 0);
								}
							}
						}
					}
				}
			}
			XWPFHeader h2 = doc.getHeaderFooterPolicy().getDefaultHeader();
			for (XWPFTable t : h2.getTables()) {
				for (XWPFTableRow row : t.getRows()) {
					for (XWPFTableCell cell : row.getTableCells()) {
						for (XWPFParagraph p : cell.getParagraphs()) {
							for (XWPFRun r : p.getRuns()) {
								String text = r.getText(0);
								if (text != null && text.contains(strAttErsatta)) {
									text = text.replace(strAttErsatta, strErsattning);
									r.setText(text, 0);
								}
							}
						}
					}
				}
			}
		}
	}

	private static void sattDokID(XWPFDocument doc, JComboBox cbdoktyp, JComboBox cbunder1) {

		try {
			XWPFDocument veraDok = new XWPFDocument(OPCPackage.open(veraDok()));
			List<XWPFTable> f1 = veraDok.getHeaderFooterPolicy().getFirstPageFooter().getTables();
			List<XWPFTable> f2 = doc.getHeaderFooterPolicy().getFirstPageFooter().getTables();
			List<XWPFRun> t1 = f1.get(0).getRows().get(0).getTableCells().get(0).getParagraphs().get(0).getRuns();
			List<XWPFRun> t2 = f2.get(0).getRows().get(0).getTableCells().get(0).getParagraphs().get(0).getRuns();
			StringBuilder sbT = new StringBuilder();
			for (XWPFRun t : t1) {
				sbT.append(t.getText(0));
			}
			for (XWPFRun t : t2) {
				t.setText("", 0);
			}
			t2.get(0).setText(sbT.toString());
			veraDok.close();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	private static void bestamDokTyp(ItemEvent event) {
		Object item = event.getItem();

		if (event.getStateChange() == ItemEvent.SELECTED) {

			if (item.toString() == "B") {
				btfta = "B";
				cbdoktyp.setModel(modeldoktypbrottmal);
			}

			if (item.toString() == "T" | item.toString() == "FT") {
				btfta = "T";
				cbdoktyp.setModel(modeldoktyptvistemal);
			}

			if (item.toString() == "Ä") {
				btfta = "Ä";
				cbdoktyp.setModel(modeldoktyparende);
			}

			cbdoktyp.setEditable(true);
			cbdoktyp.setSelectedItem("Dokumenttyp..");
			cbdoktyp.setEditable(false);
		}
	}

	private static void hamtaMall() {
		pnlB.setVisible(true);
		pnlB.removeAll();
		mallList.removeAll();
		listaUtvaldaErsatt.clear();
		laggTillAttErsatta("datum", sDatum);
		laggTillAttErsatta("överklagande senast", sOk3v);
		if (sSaken != null) {
			laggTillAttErsatta("saken", sSaken);
		}
		StringBuilder sbDokText = new StringBuilder();
		try {
			XWPFDocument xdocoppnadok = new XWPFDocument(OPCPackage.open(mall));
			XWPFWordExtractor exttaframtext = new XWPFWordExtractor(xdocoppnadok);
			String doktext = new String(exttaframtext.getText());
			sbDokText.append(doktext);

			xdocoppnadok.close();
			exttaframtext.close();

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(huvudmeny, e1);
		}

		String[] split = sbDokText.toString().split("#");

		List<String> listaAllaErsatt = Arrays.asList(split);

		for (int i = 0; i < listaAllaErsatt.size(); i++) {

			if (i % 2 != 0) {

				boolean b;
				b = false;

				for (int ii = 0; ii < listaUtvaldaErsatt.size(); ii++) {

					if (listaUtvaldaErsatt.get(ii).attErsatta.equals(listaAllaErsatt.get(i))) {
						b = true;
						break;
					}
				}
				if (b == false) {
					UtvaldErsatt ue = new UtvaldErsatt();
					ue.attErsatta = listaAllaErsatt.get(i);
					listaUtvaldaErsatt.add(ue);
				}
			}
		}

		mallList.setBorder(new TitledBorder(""));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		for (int i = 0; i < listaUtvaldaErsatt.size(); i++) {

			mallList.add(ersattPanel(listaUtvaldaErsatt.get(i).attErsatta, i), gbc);
		}

		JScrollPane scrErsFork = new JScrollPane(mallList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrErsFork.getVerticalScrollBar().setBackground(SystemColor.control);

		pnlB.add(scrErsFork);
		pnlB.revalidate();
		pnlB.repaint();

		huvudmeny.setFocusCycleRoot(true);
	}

	private static void laggTillDokumentalternativ() {
		if (cbDokAlt.getSelectedItem().toString().length() > 0
				&& !cbDokAlt.getSelectedItem().toString().equals("Dokumentalternativ..")) {

			laggTillCbAlt(modelDokAlt, cbDokAlt,
					btfta + "\\" + dokTyp + "\\" + cbunder1.getSelectedItem().toString() + "\\dokumentalternativ");
			String nyttalt = new String(cbDokAlt.getSelectedItem().toString());
			modelValdaAlt.addElement(nyttalt);
			listDokAlt.setModel(modelValdaAlt);
			cbDokAlt.removeItem(cbDokAlt.getSelectedItem());
			cbDokAlt.setSelectedItem("Dokumentalternativ..");
			sorteraLista(listDokAlt);
		}
	}

	private static void taBortDokumentalternativ() {

		if (listDokAlt.getSelectedValue() != null) {
			String valtalt = new String(listDokAlt.getSelectedValue().toString());
			cbDokAlt.addItem(valtalt);
			modelValdaAlt.removeElement(valtalt);
			listDokAlt.setModel(modelValdaAlt);
			sorteraLista(listDokAlt);
		}
	}

	private static ArrayList<String> dokAltLista() {

		StringBuilder sbDokAltDok = new StringBuilder(dataMapp);

		for (JComboBox cb : cbListaDokNamn) {
			if (cb.getSelectedItem().toString() != null) {

				sbDokAltDok.append(cb.getSelectedItem().toString() + "\\");
			}
		}
		File fSkapaMappar = new File(sbDokAltDok.toString());
		boolean skapaMapp = fSkapaMappar.mkdirs();
		sbDokAltDok.append("dokumentalternativ.txt");

		File dokFinns = new File(sbDokAltDok.toString());
		ArrayList<String> dokAltLista = new ArrayList<String>();
		if (dokFinns.exists()) {
			try {
				Scanner scDokAlt = new Scanner(dokFinns, "utf-8");
				while (scDokAlt.hasNextLine()) {
					String line = scDokAlt.nextLine();
					dokAltLista.add(line);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);
			}
		}
		return dokAltLista;
	}

	private static void bestamAvdelning() {
		JDialog bestamavd = new JDialog(huvudmeny);
		bestamavd.setBounds(110, 80, 170, 125);
		bestamavd.getContentPane().setLayout(null);

		JLabel lblavd = new JLabel("Avdelning:");
		lblavd.setBounds(10, 11, 134, 14);
		bestamavd.getContentPane().add(lblavd);

		JTextField tfbestamavd = new JTextField();
		tfbestamavd.setBounds(10, 26, 134, 20);
		bestamavd.getContentPane().add(tfbestamavd);
		tfbestamavd.setColumns(10);
		tfbestamavd.setText(hamtaAvdelning());

		JButton btnsparaavd = new JButton("Spara");
		btnsparaavd.setBounds(10, 53, 89, 23);
		bestamavd.getContentPane().add(btnsparaavd);

		bestamavd.setVisible(true);

		btnsparaavd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileWriter avdelning = new FileWriter(dataMapp + "avdelning.txt");
					tfbestamavd.write(avdelning);
					bestamavd.dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
	}

	private static void sattVeraDokMappSokvag() {
		JDialog bestamavd = new JDialog(huvudmeny);
		bestamavd.setBounds(110, 80, 170, 125);
		bestamavd.getContentPane().setLayout(null);

		JLabel lblavd = new JLabel("Sökväg, veradokument:");
		lblavd.setBounds(10, 11, 134, 14);
		bestamavd.getContentPane().add(lblavd);

		JTextField tfbestamavd = new JTextField();
		tfbestamavd.setBounds(10, 26, 134, 20);
		bestamavd.getContentPane().add(tfbestamavd);
		tfbestamavd.setColumns(10);
		tfbestamavd.setText(hamtaVeraDokMapp());

		JButton btnsparaavd = new JButton("Spara");
		btnsparaavd.setBounds(10, 53, 89, 23);
		bestamavd.getContentPane().add(btnsparaavd);

		bestamavd.setVisible(true);

		btnsparaavd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FileWriter avdelning = new FileWriter(dataMapp + "veraDokMapp.txt");
					tfbestamavd.write(avdelning);
					bestamavd.dispose();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
	}

	private static String veraDok() {

		File filmapp = new File(veraDokMapp);
		StringBuilder sbDokNamn = new StringBuilder();
		for (String fileName : filmapp.list()) {
			if (fileName.contains(tfmalnr.getText()) && fileName.startsWith("~") != true) {
				if (dokTyp.contains("Dom") | dokTyp.contains("Tredskodom") | dokTyp.contains("Beslut")
						| dokTyp.contains("beslut")) {
					if (fileName.contains("Avgörandedokument")) {
						sbDokNamn.replace(0, 255, veraDokMapp + fileName);
					}

				} else if (cbunder1.getSelectedItem() != null) {
					if (cbunder1.getSelectedItem().toString().contains("Häktningsförhandling")
							| cbunder1.getSelectedItem().toString().contains("Omhäktningsförhandling")
							| cbunder1.getSelectedItem().toString().contains("Kvarstadsförhandling")) {
						if (fileName.contains("Avgörandedokument")) {
							sbDokNamn.replace(0, 255, veraDokMapp + fileName);
						}

					} else {
						if (fileName.contains("Mötesdokument")) {
							sbDokNamn.replace(0, 255, veraDokMapp + fileName);
						}
					}

				} else {
					if (fileName.contains("Mötesdokument")) {
						sbDokNamn.replace(0, 255, veraDokMapp + fileName);
					}
				}
			}
		}
		return sbDokNamn.toString();
	}

	private static String hamtaAvdelning() {
		StringBuilder sbAvdelning = new StringBuilder();
		try {
			Scanner scavdelning = new Scanner(new File(dataMapp + "avdelning.txt"), "utf-8");
			sbAvdelning.append(scavdelning.nextLine());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		return sbAvdelning.toString();
	}

	private static String hamtaVeraDokMapp() {
		StringBuilder sbAvdelning = new StringBuilder();
		try {
			Scanner scavdelning = new Scanner(new File(dataMapp + "veraDokMapp.txt"), "utf-8");
			sbAvdelning.append(scavdelning.nextLine());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		return sbAvdelning.toString();
	}

	private static String namnBeslutsfattare(JComboBox cbratten) {

		StringBuilder sbBeslutsfattare = new StringBuilder();
		try {
			Scanner scbeslutsfattare = new Scanner(new File(dataMapp + "beslutsfattare.txt"), "utf-8");
			ArrayList<String> blista = new ArrayList<String>();
			while (scbeslutsfattare.hasNextLine()) {
				String line = scbeslutsfattare.nextLine();
				blista.add(line);
			}
			for (String b : blista) {
				if (cbratten.getSelectedItem() != null && b.contains(cbratten.getSelectedItem().toString())) {
					String[] bsplit = b.split("%");
					sbBeslutsfattare.append(bsplit[1]);
				}
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
		return sbBeslutsfattare.toString();
	}

	private static String protokollforare(XWPFDocument doc, JComboBox cbprotokollforare, JComboBox cbratten) {

		StringBuilder sbProt = new StringBuilder();
		if (cbprotokollforare.getSelectedItem().toString() != "även protokollförare") {
			String[] namnsplit = namnBeslutsfattare(cbprotokollforare).split(" ");
			sbProt.append(namnsplit[1] + " ");
			sbProt.append(namnsplit[2]);
			for (XWPFParagraph p : doc.getParagraphs()) {
				if (p.getText().contains("@protokollforarenamn@")) {
					XWPFRun r = p.createRun();
					r.addBreak();
					r.setText("Protokollet uppvisat/");
				}
			}
		}

		else if (cbratten.getSelectedItem().toString() != null) {
			String[] namnsplit = namnBeslutsfattare(cbratten).split(" ");
			List<String> listaHelaNamnet = Arrays.asList(namnsplit);
			for (int i = 1; i < listaHelaNamnet.size(); i++) {
				sbProt.append(namnsplit[i] + " ");
			}
		}
		return sbProt.toString();
	}

	private static void laggTillDomstolsaktor(XWPFDocument doc) {

		for (XWPFParagraph p : doc.getParagraphs()) {
			if (p.getText().contains("RÄTTEN") && p.getRuns().get(0).isBold()) {
				XWPFRun r = p.createRun();
				r.setBold(false);
				r.addBreak();
				r.setText(namnBeslutsfattare(cbratten));
				if (cbprotokollforare.getSelectedItem().toString() == "prot.förare") {
					r.setText("; även protokollförare");
				} else {
					r.addBreak();
					r.addBreak();
					XWPFRun r2 = p.createRun();
					r2.setBold(true);
					r2.setText("PROTOKOLLFÖRARE");
					XWPFRun r3 = p.createRun();
					r3.addBreak();
					r3.setText(namnBeslutsfattare(cbprotokollforare));
				}
			}
		}
	}

	private static void laggTillAktorsuppg(XWPFDocument doc) {
		for (XWPFParagraph p : doc.getParagraphs()) {
			if (p.getText().contains("PARTER") && p.getRuns().get(0).isBold()) {
				for (int i = 0; i < aktorlista.size(); i++) {
					XWPFRun r1 = p.createRun();
					r1.addBreak();
					r1.addBreak();
					if (aktorlista.get(i).titel.contains(":")) {
						r1.setText(aktorlista.get(i).titel + " ");
					} else {
						r1.setBold(true);
						r1.setText(aktorlista.get(i).titel);
						r1.addBreak();
					}
					for (int ii = 0; ii < Arrays.asList(aktorlista.get(i).aktor.split("\n")).size(); ii++) {
						String s = Arrays.asList(aktorlista.get(i).aktor.split("\n")).get(ii);
						if (!s.startsWith("Frihetsberövande") && !s.startsWith("Medborgare i")) {
							if (aktorlista.get(i).aktor.contains("Frihetsberövande") && ii == 0) {
								XWPFRun r2 = p.createRun();
								if (ii > 0) {
									r2.addBreak();
								}
								r2.setText(s);
								XWPFRun r4 = p.createRun();
								if (s.length() >= 28 && s.length() <= 42) {
									r4.setText("          ");
								} else if (s.length() < 28) {
									r4.addTab();
								}
								r4.addTab();
								r4.setItalic(true);
								for (String a : Arrays.asList(aktorlista.get(i).aktor.split("\n"))) {
									if (a.startsWith("Frihetsberövande")) {
										r4.setText(a);
									}
								}
							} else {
								XWPFRun r2 = p.createRun();
								if (ii > 0) {
									r2.addBreak();
								}
								r2.setText(s);
							}
						}
					}
					if (aktorlista.get(i).narvaro != null) {
						XWPFRun r3 = p.createRun();
						r3.setItalic(true);
						r3.addBreak();
						r3.setText(aktorlista.get(i).narvaro);
					}
				}
			}
		}
	}
	
	private static String antBeslMedNamn() {
		ArrayList<String> namnlista = new ArrayList<String>();

		StringBuilder sbErsatt = new StringBuilder();

		if (taAntBeslut1.getText().contains("@") && cbAntBeslAvs.getSelectedItem() != null) {

			if (cbAntBeslAvs.getSelectedItem().toString().contains(";")) {

				String[] words = cbAntBeslAvs.getSelectedItem().toString().split(";");

				for (String s : words) {

					namnlista.add(s);
				}

				for (int i = 0; i < namnlista.size(); i++) {

					String ersatt = taAntBeslut1.getText();

					ersatt = ersatt.replace("@namn" + String.valueOf(i + 1) + "@", namnlista.get(i));

					taAntBeslut1.setText(ersatt);
				}

			} else {
				String ersatt = taAntBeslut1.getText();

				ersatt = ersatt.replace("@namn@", cbAntBeslAvs.getSelectedItem().toString());

				sbErsatt.append(ersatt);
			}
		} else {
			sbErsatt.append(taAntBeslut1.getText());
		}
		return sbErsatt.toString();
	}
	

	private static void laggTillNamnCbModel(DefaultComboBoxModel model, String s) {

		boolean b;
		b = false;

		if (model.getSize() > 0) {
			for (int i = 0; i < model.getSize(); i++) {

				if (model.getElementAt(i).toString().equals(s)) {

					b = true;

					break;
				}
			}
		}

		if (b == false) {

			model.addElement(s);
		}

	}

	private static void sparaSkrivning(String textfil, JComboBox cb, JTextArea taAntBeslut1) {
		try {

			FileWriter nyttBBeslut = new FileWriter(textfil, true);
			PrintWriter skiljetecken = new PrintWriter(nyttBBeslut);

			BufferedReader reader = new BufferedReader(new FileReader(textfil));
			int lines = 0;
			while (reader.readLine() != null)
				lines++;
			reader.close();

			if (lines > 0) {
				skiljetecken.write("\n");
			}

			nyttBBeslut.append(cb.getSelectedItem().toString());
			nyttBBeslut.append("%");
			taAntBeslut1.write(nyttBBeslut);

			nyttBBeslut.close();

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	private static void sparaBeslAnt(String textfil, JComboBox cb, JTextArea taAntBeslut1, DefaultComboBoxModel model) {

		try {
			boolean b = false;
			BufferedReader reader = new BufferedReader(new FileReader(textfil));
			StringBuffer inputBuffer = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.split("%")[0].equals(cb.getSelectedItem().toString())) {
					line = cb.getSelectedItem().toString() + "%" + taAntBeslut1.getText();
					b = true;
				}
				inputBuffer.append(line);
				inputBuffer.append('\n');
			}
			reader.close();

			if (b == false) {
				inputBuffer.append(cb.getSelectedItem().toString() + "%" + taAntBeslut1.getText());
			}

			FileOutputStream fileOut = new FileOutputStream(textfil);
			fileOut.write(inputBuffer.toString().getBytes());
			fileOut.close();

			cb.setModel(model);
			cb.removeAllItems();

			Scanner scBrottmalsbeslut = new Scanner(new File(textfil), "utf-8");
			while (scBrottmalsbeslut.hasNextLine()) {
				String line1 = scBrottmalsbeslut.nextLine();
				String[] words = line1.split("%");
				cb.addItem(new ToolTipWrapper(words[0], words[1]));
			}

			cb.setSelectedIndex(cb.getItemCount() - 1);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1);
		}
	}

	private static JPanel ersattPanel(String attErsatta, int i) {

		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(new TitledBorder(attErsatta));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JTextArea text = new JTextArea(1, 0);
		text.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text.setLineWrap(true);
		taListaErsattPanel.add(text);

		gbc.gridwidth = 2;
		gbc.gridx = 1;
		p.add(text, gbc);

		for (int ii = 0; ii < aktorlista.size(); ii++) {
			if (aktorlista.get(ii).titel.startsWith(attErsatta)) {
				listaUtvaldaErsatt.get(i).ersatt = aktorLista.get(ii);
			}
		}

		if (listaUtvaldaErsatt.get(i).ersatt != null) {
			text.setText(listaUtvaldaErsatt.get(i).ersatt);
		}

		// JComboBox cbDokTypSkr = UIhelp.cBox("Standardskrivning..", 0, 18);
		// lasInFranTxtMedToolTip(standardskrivningar, cbDokTypSkr);
		// cbListaStndErsattPanel.add(cbDokTypSkr);
		// gbc.gridy = 1;
		// gbc.gridx = 1;
		// gbc.gridwidth = 1;
		// p.add(cbDokTypSkr, gbc);

		// JComboBox cbDokSpecSkr = UIhelp.cBox("Typspecifik skrivning..", 0, 18);
		// lasInFranTxtMedToolTip(dokspecskrivningar, cbDokSpecSkr);
		// cbListaSpecErsattPanel.add(cbDokSpecSkr);
		// gbc.gridy = 1;
		// gbc.gridx = 2;
		// gbc.gridwidth = 1;
		// p.add(cbDokSpecSkr, gbc);

		// cbDokTypSkr.addItemListener(new ItemListener() {
		// @Override
		// public void itemStateChanged(ItemEvent e) {
		// Object item = e.getItem();
		// if (e.getStateChange() == ItemEvent.SELECTED) {
		// hamtaTextFranFil(standardskrivningar, item.toString(), text);
		// cbDokSpecSkr.setSelectedItem("Typspecifik skrivning..");
		// }
		// }
		// });

		// cbDokSpecSkr.addItemListener(new ItemListener() {
		// @Override
		// public void itemStateChanged(ItemEvent e) {
		// Object item = e.getItem();
		// if (e.getStateChange() == ItemEvent.SELECTED) {
		// hamtaTextFranFil(dokspecskrivningar, item.toString(), text);
		// cbDokTypSkr.setSelectedItem("Standardskrivning..");
		// }
		// }
		// });

		// JComboBox cbSkrAvs = UIhelp.cBox("Avseende..", 0, 18);
		// for (String s : aktorLista) {
		// cbSkrAvs.addItem(s);
		// }
		// cbSkrAvs.addItemListener(new ItemListener() {
		// @Override
		// public void itemStateChanged(ItemEvent e) {
		// if (e.getStateChange() == ItemEvent.SELECTED) {
		// Object item = e.getItem();
		// String ersattning = text.getText().replace("@namn@", item.toString());
		// text.setText(ersattning);
		// }
		// }
		// });

		text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				listaUtvaldaErsatt.get(i).ersatt = text.getText();
				// if (text.getText().contains("@")) {
				// gbc.gridx = 1;
				// gbc.gridy = 2;
				// p.add(cbSkrAvs, gbc);
				// } else {
				// p.remove(cbSkrAvs);
				// }
				// if (text.getText().length() <= 0) {
				// cbDokTypSkr.setSelectedItem("Standardskrivning..");
				// cbDokSpecSkr.setSelectedItem("Typspecifik skrivning..");
				// text.setText(null);
				// }
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					e.consume();
					KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
				}
				if (e.getKeyCode() == KeyEvent.VK_TAB && e.isShiftDown()) {
					e.consume();
					KeyboardFocusManager.getCurrentKeyboardFocusManager().focusPreviousComponent();

				}
			}
		});

		return p;
	}

	private static void laggTillSkrivning() {
		for (int i = 0; i < taListaErsattPanel.size(); i++) {
			if (!cbListaStndErsattPanel.get(i).getSelectedItem().toString().equals("Standardskrivning..")) {
				sparaSkrivning(standardskrivningar, cbListaStndErsattPanel.get(i), taListaErsattPanel.get(i));
			} else if (!cbListaSpecErsattPanel.get(i).getSelectedItem().toString().equals("Typspecifik skrivning..")) {
				sparaSkrivning(dokspecskrivningar, cbListaSpecErsattPanel.get(i), taListaErsattPanel.get(i));
			}
		}
	}

	private static void laggTillAktorIAktorLista() {
		for (ProtAktor a : aktorlista) {
			String sAktor = a.aktor.split("\n")[0];

			if (sAktor.startsWith("Advokat ") | sAktor.startsWith("Jur.kand. ") | sAktor.startsWith("Jurist ")) {
				sAktor = sAktor.replace("Advokat ", "");
				sAktor = sAktor.replace("Jur.kand. ", "");
				sAktor = sAktor.replace("Jurist ", "");
			}
			sAktor = sAktor.split(",")[0];
			String[] namnSplit2 = sAktor.split(" ");
			List<String> listaHelaNamnet = Arrays.asList(namnSplit2);
			StringBuilder sbNamn = new StringBuilder();
			for (int i = 0; i < listaHelaNamnet.size() - 1; i++) {
				sbNamn.append(listaHelaNamnet.get(i) + " ");
			}
			for (int i = 0; i < listaHelaNamnet.size() - 1; i++) {
				String s = listaHelaNamnet.get(i);
				StringBuffer sb = new StringBuffer(s);
				if (StringUtils.isAllUpperCase(sb)) {
					for (int ii = 1; ii < sb.length(); ii++) {
						if (Character.isUpperCase(s.charAt(ii))) {
							sb.replace(ii, ii + 1, Character.toLowerCase(sb.charAt(ii)) + "");
						}
					}
					sbNamn.replace(0, 255, sb.toString() + " ");
				}
			}
			StringBuilder sbEfternamn = new StringBuilder();
			for (String s : listaHelaNamnet) {
				sbEfternamn.replace(0, 255, s);
			}
			sbNamn.append(sbEfternamn.toString());

			aktorLista.add(sbNamn.toString());
		}
	}

	private static JPanel aktorPanel(String titel, String uppg, int parti) {

		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(new TitledBorder(titel));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JTextArea text = new JTextArea(1, 0);
		text.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text.setLineWrap(true);
		p.add(text, gbc);
		text.append(uppg);

		text.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {

				aktorlista.get(parti).aktor = text.getText();

			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					e.consume();
					KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
				}
				if (e.getKeyCode() == KeyEvent.VK_TAB && e.isShiftDown()) {
					e.consume();
					KeyboardFocusManager.getCurrentKeyboardFocusManager().focusPreviousComponent();

				}
			}
		});

		if (dokTyp.equals("Protokoll")) {

			JComboBox cbNarv = new JComboBox(
					new Object[] { "Närvarande", "Personligen närvarande", "Närvarande per videolänk",
							"Närvarande per telefon", "Närvarande genom ombud", "Närvarande genom substitution" });
			cbNarv.setFont(new Font("Tahoma", Font.PLAIN, 11));
			cbNarv.setEditable(true);
			cbNarv.setPreferredSize(new Dimension(200, 18));
			cbNarv.setBackground(SystemColor.control);

			gbc.gridy = 1;
			gbc.gridx = 0;
			p.add(cbNarv, gbc);
			aktorlista.get(parti).narvaro = cbNarv.getSelectedItem().toString();
			cbNarv.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					Object item = e.getItem();
					if (e.getStateChange() == ItemEvent.SELECTED) {
						aktorlista.get(parti).narvaro = item.toString();
					}
				}
			});
		}

		return p;
	}

	private static JPanel antAktorPanel(String titel) {
		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(new TitledBorder(titel));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		if (aktor.domare != null) {
			laggTillAktorIDag("Domare:", aktor.domare, gbc, p);
			JButton btnRensa = UIhelp.rensaButton("Rensa");
			p.add(btnRensa, gbc);
			btnRensa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aktor.domare = null;
					uppdateraDagdelVy();
				}
			});
		}
		if (aktor.namndeman != null) {
			laggTillAktorIDag("Nämndemän:", aktor.namndeman, gbc, p);
			JButton btnRensa = UIhelp.rensaButton("Rensa");
			p.add(btnRensa, gbc);
			btnRensa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aktor.namndeman = null;
					uppdateraDagdelVy();
				}
			});
		}
		if (aktor.ansvarig != null) {
			laggTillAktorIDag("Ansvarig för anteckningarna:", aktor.ansvarig, gbc, p);
			JButton btnRensa = UIhelp.rensaButton("Rensa");
			p.add(btnRensa, gbc);
			btnRensa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aktor.ansvarig = null;
					uppdateraDagdelVy();
				}
			});
		}
		if (aktor.part != null) {
			laggTillAktorIDag("Part/ombud:", aktor.part, gbc, p);
			JButton btnRensa = UIhelp.rensaButton("Rensa");
			p.add(btnRensa, gbc);
			btnRensa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aktor.part = null;
					uppdateraDagdelVy();
				}
			});
		}
		if (aktor.ovrig != null) {
			laggTillAktorIDag("Övrig:", aktor.ovrig, gbc, p);
			JButton btnRensa = UIhelp.rensaButton("Rensa");
			p.add(btnRensa, gbc);
			btnRensa.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					aktor.ovrig = null;
					uppdateraDagdelVy();
				}
			});
		}
		return p;
	}

	public static void laggTillAktorIDag(String titel, String aktor, GridBagConstraints gbc, JPanel p) {
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridy = gbc.gridy + 1;
		gbc.gridx = 0;
		p.add(antUnderLabel(titel), gbc);
		gbc.gridy = gbc.gridy + 1;
		p.add(antUnderTa(aktor), gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.EAST;
	}

	private static JLabel antUnderLabel(String lblText) {
		JLabel lbl = new JLabel(lblText);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		return lbl;
	}

	private static JTextArea antUnderTa(String taText) {
		JTextArea ta = new JTextArea(1, 40);
		ta.setText(taText);
		ta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ta.setLineWrap(true);
		return ta;
	}

	private static JTextArea antUnderTaBeslAnt(String taText) {
		JTextArea ta = new JTextArea(1, 40);
		ta.setText(taText);
		ta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ta.setLineWrap(true);
		return ta;
	}

	private static JPanel dagPanel(String titel) {

		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(new TitledBorder(titel));

		return p;
	}

	private static JPanel dagUnderPanelBev(Dag dag, String titel, int dagindex) {

		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(new TitledBorder(titel));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		if (dag.forhor != null) {
			p.add(antUnderLabel("Förhör:"), gbc);
			gbc.gridy = 1;
			JTextArea ta = antUnderTa(dag.forhor);
			p.add(ta, gbc);
			gbc.gridx = 1;
			gbc.anchor = GridBagConstraints.EAST;
			JButton btnRensa = UIhelp.rensaButton("Rensa");
			p.add(btnRensa, gbc);
			RensaForhor r = new RensaForhor();
			r.rensa = dagindex;
			btnRensa.addActionListener(r);
			ta.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					dag.forhor = ta.getText();
				}
			});
		}
		if (dag.annBev != null) {
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx = 0;
			gbc.gridy = gbc.gridy + 1;
			p.add(antUnderLabel("Annan bevisning/utredning:"), gbc);
			gbc.gridy = gbc.gridy + 1;
			JTextArea ta = antUnderTa(dag.annBev);
			p.add(ta, gbc);
			gbc.gridx = 1;
			gbc.anchor = GridBagConstraints.EAST;
			JButton btnRensa = UIhelp.rensaButton("Rensa");
			p.add(btnRensa, gbc);
			RensaAnnUtr r = new RensaAnnUtr();
			r.rensa = dagindex;
			btnRensa.addActionListener(r);
			ta.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					dag.annBev = ta.getText();
				}
			});
		}
		if (dag.persUtr != null) {
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx = 0;
			gbc.gridy = gbc.gridy + 1;
			p.add(antUnderLabel("Personutredning:"), gbc);
			gbc.gridy = gbc.gridy + 1;
			JTextArea ta = antUnderTa(dag.persUtr);
			p.add(ta, gbc);
			gbc.gridx = 1;
			gbc.anchor = GridBagConstraints.EAST;
			JButton btnRensa = UIhelp.rensaButton("Rensa");
			p.add(btnRensa, gbc);
			RensaPersUtr r = new RensaPersUtr();
			r.rensa = dagindex;
			btnRensa.addActionListener(r);
			ta.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					dag.persUtr = ta.getText();
				}
			});
		}
		if (dag.uppg != null) {
			gbc.anchor = GridBagConstraints.WEST;
			gbc.gridx = 0;
			gbc.gridy = gbc.gridy + 1;
			p.add(antUnderLabel("Uppger följande:"), gbc);
			gbc.gridy = gbc.gridy + 1;
			JTextArea ta = antUnderTa(dag.uppg);
			p.add(ta, gbc);
			gbc.gridx = 1;
			gbc.anchor = GridBagConstraints.EAST;
			JButton btnRensa = UIhelp.rensaButton("Rensa");
			p.add(btnRensa, gbc);
			RensaUppg r = new RensaUppg();
			r.rensa = dagindex;
			btnRensa.addActionListener(r);
			ta.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					dag.uppg = ta.getText();
				}
			});
		}
		return p;
	}

	private static JPanel dagUnderPanelBesl(int dagindex, String titel, Dag dag) {

		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(new TitledBorder(titel));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		JTextArea ta = antUnderTaBeslAnt(dag.beslut);
		p.add(ta, gbc);
		ta.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				dag.beslut = ta.getText();
			}
		});

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.EAST;

		JButton btnRensa = UIhelp.rensaButton("Rensa");
		p.add(btnRensa, gbc);
		RensaBeslut r = new RensaBeslut();
		r.rensa = dagindex;
		btnRensa.addActionListener(r);

		return p;
	}

	private static JPanel dagUnderPanelAnt(int dagindex, String titel, Dag dag) {

		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(new TitledBorder(titel));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;

		JTextArea ta = antUnderTaBeslAnt(dag.ant);
		p.add(ta, gbc);
		ta.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				dag.ant = ta.getText();
			}
		});

		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.EAST;

		JButton btnRensa = UIhelp.rensaButton("Rensa");
		p.add(btnRensa, gbc);
		RensaAnt r = new RensaAnt();
		r.rensa = dagindex;
		btnRensa.addActionListener(r);

		return p;
	}

	private static JPanel dagUnderPanelTid(int dagindex, String titel, Dag dag) {

		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(new TitledBorder(titel));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridy = 0;
		gbc.gridx = 0;

		JLabel lblStarttid = new JLabel("Start:");
		lblStarttid.setFont(new Font("Tahoma", Font.PLAIN, 11));
		p.add(lblStarttid, gbc);

		gbc.gridy = 1;

		JTextArea taStart = new JTextArea(1, 0);
		taStart.setFont(new Font("Tahoma", Font.PLAIN, 11));
		taStart.setLineWrap(true);
		p.add(taStart, gbc);
		taStart.setText(dag.starttid);
		taStart.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				dag.starttid = taStart.getText();
			}
		});

		gbc.gridy = 0;
		gbc.gridx = 1;

		JLabel lblSluttid = new JLabel("Slut:");
		lblSluttid.setFont(new Font("Tahoma", Font.PLAIN, 11));
		p.add(lblSluttid, gbc);

		gbc.gridy = 1;

		JTextArea taSlut = new JTextArea(1, 0);
		taSlut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		taSlut.setLineWrap(true);
		p.add(taSlut, gbc);
		taSlut.setText(dag.sluttid);
		taSlut.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				dag.sluttid = taSlut.getText();
			}
		});

		gbc.gridy = 0;
		gbc.gridx = 2;

		JLabel lblPaus = new JLabel("Paus:");
		lblPaus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		p.add(lblPaus, gbc);

		gbc.gridy = 1;

		JTextArea text2 = new JTextArea(1, 0);
		text2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text2.setLineWrap(true);
		p.add(text2, gbc);
		text2.setText(dag.paus);
		text2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				dag.paus = text2.getText();
			}
		});

		gbc.gridx = 3;

		gbc.anchor = GridBagConstraints.EAST;

		JButton btnRensa = UIhelp.rensaButton("Rensa");
		p.add(btnRensa, gbc);
		RensaTid r = new RensaTid();
		r.rensa = dagindex;
		btnRensa.addActionListener(r);

		return p;
	}

	private static void laggTillAttErsatta(String titel, String text) {
		if (titel != null) {
			UtvaldErsatt ue = new UtvaldErsatt();
			ue.attErsatta = titel;
			ue.ersatt = text;
			listaUtvaldaErsatt.add(ue);
		}
	}

	private static String datumOk() {

		StringBuilder sbDatum = new StringBuilder();
		if (sDatum != null) {
			String[] datumSplit = sDatum.split("-");
			int i0 = Integer.parseInt(datumSplit[0]);
			StringBuilder sbManad = new StringBuilder();
			if (datumSplit[1].startsWith("0")) {
				sbManad.append(datumSplit[1].charAt(1));
			} else {
				sbManad.append(datumSplit[1]);
			}
			int i1 = Integer.parseInt(sbManad.toString());
			StringBuilder sbDag = new StringBuilder();
			if (datumSplit[2].startsWith("0")) {
				sbDag.append(datumSplit[2].charAt(1));
			} else {
				sbDag.append(datumSplit[2]);
			}
			int i2 = Integer.parseInt(sbDag.toString());
			if (i1 == 12 && i2 > 10) {

				int i01 = i0 + 1;
				sbDatum.append(String.valueOf(i01) + "-");
			} else {
				sbDatum.append(String.valueOf(i0) + "-");
			}
			if (i1 == 1 | i1 == 3 | i1 == 5 | i1 == 7 | i1 == 8 | i1 == 10 && i2 > 10) {

				int i11 = i1 + 1;
				int i21 = i2 - 10;
				sbDatum.append(String.valueOf(i11) + "-" + String.valueOf(i21));
			} else if (i1 == 4 | i1 == 6 | i1 == 9 | i1 == 11 && i2 > 9) {

				int i11 = i1 + 1;
				int i21 = i2 - 9;
				sbDatum.append(String.valueOf(i11) + "-" + String.valueOf(i21));
			} else if (i1 == 2 && i2 > 7) {

				int i11 = i1 + 1;
				int i21 = i2 - 7;
				sbDatum.append(String.valueOf(i11) + "-" + String.valueOf(i21));
			} else if (i1 == 12 && i2 > 10) {

				int i11 = i1 - 11;
				int i21 = i2 - 10;
				sbDatum.append(String.valueOf(i11) + "-" + String.valueOf(i21));
			} else {
				sbDatum.append(String.valueOf(i1) + "-" + String.valueOf(i2));
			}
			if (sbDatum.toString().split("-")[1].length() == 1) {
				sbDatum.insert(5, "0");
			}
			if (sbDatum.toString().split("-")[2].length() == 1) {
				sbDatum.insert(8, "0");
			}
		}
		return sbDatum.toString();
	}

	private static String datumTillText(String s) {

		StringBuilder sbDatum = new StringBuilder();
		if (s != null) {
			sbDatum.append("den ");
			String[] datumSplit = s.split("-");
			if (datumSplit[2].startsWith("0")) {
				sbDatum.append(datumSplit[2].charAt(1) + " ");
			} else {
				sbDatum.append(datumSplit[2] + " ");
			}
			if (datumSplit[1].contains("01")) {
				sbDatum.append("januari");
			}
			if (datumSplit[1].contains("02")) {
				sbDatum.append("februari");
			}
			if (datumSplit[1].contains("03")) {
				sbDatum.append("mars");
			}
			if (datumSplit[1].contains("04")) {
				sbDatum.append("april");
			}
			if (datumSplit[1].contains("05")) {
				sbDatum.append("maj");
			}
			if (datumSplit[1].contains("06")) {
				sbDatum.append("juni");
			}
			if (datumSplit[1].contains("07")) {
				sbDatum.append("juli");
			}
			if (datumSplit[1].contains("08")) {
				sbDatum.append("augusti");
			}
			if (datumSplit[1].contains("09")) {
				sbDatum.append("september");
			}
			if (datumSplit[1].contains("10")) {
				sbDatum.append("oktober");
			}
			if (datumSplit[1].contains("11")) {
				sbDatum.append("november");
			}
			if (datumSplit[1].contains("12")) {
				sbDatum.append("december");
			}
			sbDatum.append(" " + datumSplit[0]);
		}
		return sbDatum.toString();
	}

	private static void fyllDatumArray() {
		for (int i = 0; i < modelAntDatum.size(); i++) {
			if (modelAntDatum.getElementAt(i).toString().contains("--")) {
				String[] s = modelAntDatum.getElementAt(i).toString().split(" -- |-");
				int dagar = 1;
				int dagStart = Integer.parseInt(s[2]);
				int dagSlut = Integer.parseInt(s[5]);
				int manStart = Integer.parseInt(s[1]);
				int manSlut = Integer.parseInt(s[4]);
				if (manStart == manSlut) {
					dagar = dagar + dagSlut - dagStart;
					for (int ii = 0; ii < dagar; ii++) {
						StringBuilder sbDatum = new StringBuilder();
						sbDatum.append(s[0] + "-" + s[1] + "-" + String.valueOf(dagStart + ii));
						if (sbDatum.toString().split("-")[2].length() == 1) {
							sbDatum.insert(8, "0");
						}
						datumArray.add(sbDatum.toString());
					}
				} else {
					if (manStart == 1 | manStart == 3 | manStart == 5 | manStart == 7 | manStart == 8
							| manStart == 10) {
						dagar = dagar + (31 - dagStart);
					} else if (manStart == 4 | manStart == 6 | manStart == 9 | manStart == 11) {
						dagar = dagar + (30 - dagStart);
					} else if (manStart == 2) {
						dagar = dagar + (28 - dagStart);
					}
					for (int ii = 0; ii < dagar; ii++) {
						StringBuilder sbDatum = new StringBuilder();
						sbDatum.append(s[0] + "-" + s[1] + "-" + String.valueOf(dagStart + ii));
						if (sbDatum.toString().split("-")[2].length() == 1) {
							sbDatum.insert(8, "0");
						}
						datumArray.add(sbDatum.toString());
					}
					for (int iii = 1; iii < dagSlut; iii++) {
						StringBuilder sbDatum = new StringBuilder();
						sbDatum.append(s[0] + "-" + s[4] + "-" + String.valueOf(iii));
						if (sbDatum.toString().split("-")[2].length() == 1) {
							sbDatum.insert(8, "0");
						}
						datumArray.add(sbDatum.toString());
					}
				}
			} else {
				datumArray.add(modelAntDatum.getElementAt(i).toString());
			}
		}
	}

	public static void domareAnsvIDok(StringBuilder sbRatten, String sAktor) {
		if (sAktor != null) {

			for (int i = 0; i < Arrays.asList(sAktor.split("\n")).size(); i++) {
				String s = new String(Arrays.asList(sAktor.split("\n")).get(i));
				if (i > 0) {
					StringBuffer sb = new StringBuffer(s);
					sb.replace(0, 1, Character.toLowerCase(sb.charAt(0)) + "");

					if (i != Arrays.asList(sAktor.split("\n")).size() - 1) {
						sbRatten.append(", ");
					} else {
						sbRatten.append(" och ");
					}
					sbRatten.append(sb.toString());
				} else {

					sbRatten.append(s);
				}
			}
		}
	}

	public static void utgangspunkt() {
		listAntTid.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY), new EmptyBorder(4, 4, 4, 4)));
		listAntTid.setFont(new Font("Tahoma", Font.PLAIN, 11));

		pnlAnt.setBounds(10, 98, 308, 374);
		huvudmeny.getContentPane().add(pnlAnt);
		pnlAnt.setBackground(SystemColor.control);
		pnlAnt.setLayout(null);
		pnlAnt.setVisible(false);
		pnlListaNollstall.add(pnlAnt);

		pnlAntPaus.setBounds(0, 54, 308, 143);
		pnlAnt.add(pnlAntPaus);
		pnlAntPaus.setLayout(null);

		lblAntPaus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAntPaus.setBounds(0, 53, 27, 14);
		pnlAntPaus.add(lblAntPaus);

		tfPausStart.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfPausStart.setColumns(10);
		tfPausStart.setBounds(35, 50, 44, 20);
		pnlAntPaus.add(tfPausStart);

		tfPausSlut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfPausSlut.setColumns(10);
		tfPausSlut.setBounds(89, 50, 44, 20);
		pnlAntPaus.add(tfPausSlut);

		lblstrecknyh_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblstrecknyh_1.setBounds(81, 53, 6, 14);
		pnlAntPaus.add(lblstrecknyh_1);

		lbltidnyh.setBounds(0, 3, 34, 14);
		pnlAntPaus.add(lbltidnyh);
		lbltidnyh.setFont(new Font("Tahoma", Font.PLAIN, 11));

		tfStarttid.setBounds(35, 0, 44, 20);
		pnlAntPaus.add(tfStarttid);
		tfStarttid.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfStarttid.setColumns(10);

		tfSluttid.setBounds(35, 25, 44, 20);
		pnlAntPaus.add(tfSluttid);
		tfSluttid.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfSluttid.setColumns(10);

		JLabel lblSlut = new JLabel("Slut:");
		lblSlut.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSlut.setBounds(0, 28, 27, 14);
		pnlAntPaus.add(lblSlut);

		pnlAntForhor.setBounds(0, 108, 298, 77);
		pnlAnt.add(pnlAntForhor);
		pnlAntForhor.setLayout(null);
		pnlAntForhor.setVisible(false);

		tfAntForhorNamn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfAntForhorNamn.setBounds(0, 0, 143, 20);
		pnlAntForhor.add(tfAntForhorNamn);
		tfAntForhorNamn.setColumns(10);

		cbAntHorsPer.setBackground(SystemColor.control);
		cbAntHorsPer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbAntHorsPer.setEditable(true);
		cbAntHorsPer.setBounds(0, 25, 143, 22);
		pnlAntForhor.add(cbAntHorsPer);
		AutoCompleteDecorator.decorate(cbAntHorsPer);

		pnlAntNarstaende.setBounds(153, 0, 135, 50);
		pnlAntForhor.add(pnlAntNarstaende);
		pnlAntNarstaende.setLayout(null);
		pnlAntNarstaende.setVisible(false);

		cbAntNarstaende.setBackground(SystemColor.control);
		cbAntNarstaende.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbAntNarstaende.setEditable(true);
		cbAntNarstaende.setBounds(0, 0, 114, 22);
		pnlAntNarstaende.add(cbAntNarstaende);
		AutoCompleteDecorator.decorate(cbAntNarstaende);

		lblAntTill.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAntTill.setBounds(119, 4, 16, 14);
		pnlAntNarstaende.add(lblAntTill);

		tfNarstaendeTill.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfNarstaendeTill.setBounds(0, 27, 135, 20);
		pnlAntNarstaende.add(tfNarstaendeTill);
		tfNarstaendeTill.setColumns(10);

		boxBegarErsattning.setBackground(SystemColor.control);
		boxBegarErsattning.setFont(new Font("Tahoma", Font.PLAIN, 11));
		boxBegarErsattning.setBounds(0, 57, 143, 23);
		pnlAntForhor.add(boxBegarErsattning);

		pnlAntPartOvr.setBounds(0, 81, 209, 86);
		pnlAnt.add(pnlAntPartOvr);
		pnlAntPartOvr.setLayout(null);
		pnlAntPartOvr.setVisible(false);

		tfAntPart.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfAntPart.setBounds(0, 0, 143, 20);
		pnlAntPartOvr.add(tfAntPart);
		tfAntPart.setColumns(10);

		lblPartNarvaro.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPartNarvaro.setBounds(0, 52, 43, 14);
		pnlAntPartOvr.add(lblPartNarvaro);

		tfAntNarvaro.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfAntNarvaro.setColumns(10);
		tfAntNarvaro.setBounds(0, 64, 143, 20);
		pnlAntPartOvr.add(tfAntNarvaro);

		lblAntRoll.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAntRoll.setBounds(0, 21, 46, 14);
		pnlAntPartOvr.add(lblAntRoll);

		tfAntRoll.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfAntRoll.setBounds(0, 33, 143, 20);
		pnlAntPartOvr.add(tfAntRoll);
		tfAntRoll.setColumns(10);

		boxTolk.setBackground(SystemColor.control);
		boxTolk.setFont(new Font("Tahoma", Font.PLAIN, 11));
		boxTolk.setBounds(149, 32, 54, 23);
		pnlAntPartOvr.add(boxTolk);

		cbAnt3.setBackground(SystemColor.control);
		cbAnt3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbAnt3.setEditable(true);
		cbAnt3.setBounds(0, 81, 143, 22);
		pnlAnt.add(cbAnt3);
		AutoCompleteDecorator.decorate(cbAnt3);
		cbAnt3.setVisible(false);

		cbAnt1.setBackground(SystemColor.control);
		cbAnt1.setBounds(0, 27, 143, 22);
		pnlAnt.add(cbAnt1);
		cbAnt1.setEditable(true);
		cbAnt1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		AutoCompleteDecorator.decorate(cbAnt1);
		cbListaNollstall.add(cbAnt1);
		cbAnt1.setSelectedItem("Lägg till ny(tt)..");
		cbAnt1.setEditable(false);

		cbAnt2.setBackground(SystemColor.control);
		cbAnt2.setEditable(true);
		cbAnt2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbAnt2.setBounds(0, 54, 143, 22);
		pnlAnt.add(cbAnt2);
		AutoCompleteDecorator.decorate(cbAnt2);
		cbAnt2.setVisible(false);

		pnlAnnUtr.setBounds(0, 108, 151, 22);
		pnlAnt.add(pnlAnnUtr);
		pnlAnnUtr.setLayout(null);
		pnlAnnUtr.setVisible(false);

		lblAntUtrAktbil.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAntUtrAktbil.setBounds(0, 3, 46, 14);
		pnlAnnUtr.add(lblAntUtrAktbil);
		tfAnnUtrAktb.setBounds(49, 0, 25, 20);

		pnlAnnUtr.add(tfAnnUtrAktb);

		lblAntUtrAktbilS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAntUtrAktbilS.setBounds(84, 3, 19, 14);
		pnlAnnUtr.add(lblAntUtrAktbilS);

		tfAntUtrAktbilS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfAntUtrAktbilS.setColumns(10);
		tfAntUtrAktbilS.setBounds(106, 0, 37, 20);
		pnlAnnUtr.add(tfAntUtrAktbilS);

		pnlAntBeslut.setBounds(0, 81, 308, 269);
		pnlAnt.add(pnlAntBeslut);
		pnlAntBeslut.setLayout(null);
		pnlAntBeslut.setVisible(false);
		pnlListaNollstall.add(pnlAntBeslut);

		scrAntBeslut1.setBounds(0, 0, 298, 206);
		pnlAntBeslut.add(scrAntBeslut1);

		pnlBeslAvs.setBounds(0, 210, 298, 23);
		pnlAntBeslut.add(pnlBeslAvs);
		pnlBeslAvs.setLayout(null);

		cbAntBeslAvs.setBackground(SystemColor.control);
		cbAntBeslAvs.setBounds(62, 0, 236, 23);
		pnlBeslAvs.add(cbAntBeslAvs);
		cbAntBeslAvs.setToolTipText(
				"Alla @namn@ i beslutet ersätts med namnet i den här rutan. Om det finns flera @namn1@, @namn2@ osv. i beslutet särskiljs namnen i rutan med \";\"");
		cbAntBeslAvs.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbAntBeslAvs.setEditable(true);

		taAntBeslut1.setLineWrap(true);
		taAntBeslut1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrAntBeslut1.setViewportView(taAntBeslut1);

		lblBeslAvs.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBeslAvs.setBounds(0, 4, 52, 14);
		pnlBeslAvs.add(lblBeslAvs);

		btnSparaBeslut.setBounds(0, 244, 104, 23);
		pnlAntBeslut.add(btnSparaBeslut);
		btnSparaBeslut.setBackground(new Color(240, 240, 240));
		btnSparaBeslut.setFont(new Font("Tahoma", Font.PLAIN, 11));

		pnlAntPersForh.setBounds(0, 108, 298, 212);
		pnlAnt.add(pnlAntPersForh);
		pnlAntPersForh.setLayout(null);

		cbPersForh.setBackground(SystemColor.control);
		cbPersForh.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbPersForh.setEditable(true);
		cbPersForh.setBounds(0, 0, 143, 22);
		pnlAntPersForh.add(cbPersForh);
		AutoCompleteDecorator.decorate(cbPersForh);

		lblPersForh.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPersForh.setBounds(0, 23, 278, 14);
		pnlAntPersForh.add(lblPersForh);

		scrPersForh1.setBounds(0, 43, 298, 167);
		pnlAntPersForh.add(scrPersForh1);

		taPersForh1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		taPersForh1.setLineWrap(true);
		scrPersForh1.setViewportView(taPersForh1);
		pnlAntPersForh.setVisible(false);

		pnlAntPersutr.setBounds(0, 108, 104, 22);
		pnlAnt.add(pnlAntPersutr);
		pnlAntPersutr.setLayout(null);
		pnlAntPersutr.setVisible(false);

		tfAntPersutrAktbil.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfAntPersutrAktbil.setBounds(57, 0, 46, 20);
		pnlAntPersutr.add(tfAntPersutrAktbil);
		tfAntPersutrAktbil.setColumns(10);

		lblAntPersutrAktbil.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAntPersutrAktbil.setBounds(0, 3, 48, 14);
		pnlAntPersutr.add(lblAntPersutrAktbil);

		btnAntLaggTill.setBackground(new Color(240, 240, 240));
		btnAntLaggTill.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAntLaggTill.setBounds(0, 350, 104, 23);
		pnlAnt.add(btnAntLaggTill);

		cbAntDag.setBackground(SystemColor.control);
		cbAntDag.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbAntDag.setBounds(0, 0, 102, 22);
		pnlAnt.add(cbAntDag);
		cbAntDag.setVisible(false);
		cbListaNollstall.add(cbAntDag);

		pnlSa.setBounds(153, 81, 151, 22);
		pnlAnt.add(pnlSa);
		pnlSa.setLayout(null);
		pnlSa.setVisible(false);

		tfSaAb.setBounds(122, 0, 26, 20);
		pnlSa.add(tfSaAb);
		tfSaAb.setColumns(10);

		lblSaAb.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSaAb.setBounds(0, 3, 112, 14);
		pnlSa.add(lblSaAb);
		pnldokalt.setBackground(SystemColor.control);

		pnldokalt.setBounds(10, 98, 308, 219);
		huvudmeny.getContentPane().add(pnldokalt);
		pnldokalt.setLayout(null);
		pnldokalt.setVisible(false);
		pnlListaNollstall.add(pnldokalt);

		scrDokAlt.setBounds(0, 60, 188, 155);
		pnldokalt.add(scrDokAlt);

		scrDokAlt.setViewportView(listDokAlt);
		listDokAlt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listDokAlt.setFocusable(false);

		cbDokAlt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbDokAlt.setBackground(SystemColor.control);
		cbDokAlt.setBounds(0, 27, 188, 22);
		pnldokalt.add(cbDokAlt);
		cbDokAlt.setEditable(true);
		AutoCompleteDecorator.decorate(cbDokAlt);
		cbListaNollstall.add(cbDokAlt);
		cbDokAlt.setSelectedItem("Dokumentalternativ..");

		btnlaggtillalt.setBackground(new Color(240, 240, 240));
		btnlaggtillalt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnlaggtillalt.setBounds(198, 27, 100, 23);
		pnldokalt.add(btnlaggtillalt);

		btntabortalt.setBackground(new Color(240, 240, 240));
		btntabortalt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btntabortalt.setBounds(198, 58, 100, 23);
		pnldokalt.add(btntabortalt);

		btnoppnamall.setBounds(198, 158, 100, 23);
		pnldokalt.add(btnoppnamall);
		btnoppnamall.setBackground(new Color(240, 240, 240));
		btnoppnamall.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnoppnamall.setEnabled(false);

		btnHamtaMall.setBounds(198, 192, 100, 23);
		btnHamtaMall.setBackground(SystemColor.control);
		btnHamtaMall.setFont(new Font("Tahoma", Font.PLAIN, 11));
		pnldokalt.add(btnHamtaMall);
		btnHamtaMall.setEnabled(false);

		pnlratten.setBounds(0, 0, 390, 20);
		pnldokalt.add(pnlratten);
		pnlratten.setLayout(null);
		pnlratten.setVisible(false);
		pnlListaNollstall.add(pnlratten);

		cbratten.setModel(modelratten);
		cbratten.setBackground(SystemColor.control);
		cbratten.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbratten.setBounds(0, 0, 91, 20);
		pnlratten.add(cbratten);
		cbratten.setEditable(true);
		cbratten.setSelectedItem("Rätten");
		AutoCompleteDecorator.decorate(cbratten);

		cbprotokollforare.setModel(modelprotokollforare);
		cbprotokollforare.setBackground(SystemColor.control);
		cbprotokollforare.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbprotokollforare.setBounds(97, 0, 91, 20);
		pnlratten.add(cbprotokollforare);
		cbprotokollforare.setEditable(true);
		cbprotokollforare.setSelectedItem("prot.förare");
		pnlmalnr.setBackground(SystemColor.control);
		AutoCompleteDecorator.decorate(cbprotokollforare);

		pnlSkapaOppnaDok.setBounds(10, 762, 298, 23);
		huvudmeny.getContentPane().add(pnlSkapaOppnaDok);
		pnlSkapaOppnaDok.setLayout(null);
		pnlSkapaOppnaDok.setVisible(false);

		btnSkapaDok.setBounds(0, 0, 139, 23);
		pnlSkapaOppnaDok.add(btnSkapaDok);
		btnSkapaDok.setBackground(new Color(240, 240, 240));
		btnSkapaDok.setFont(new Font("Tahoma", Font.PLAIN, 11));

		btnOppnaDok.setBackground(SystemColor.control);
		btnOppnaDok.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnOppnaDok.setBounds(138, 0, 160, 23);
		pnlSkapaOppnaDok.add(btnOppnaDok);

		btnstandardmall.setBounds(198, 124, 100, 23);
		pnldokalt.add(btnstandardmall);

		btnbestavd.setBackground(new Color(240, 240, 240));
		btnbestavd.setFont(new Font("Tahoma", Font.PLAIN, 11));
		menuBar.add(btnbestavd);
		btnbestavd.setFocusable(false);

		btnstandardmall.setBackground(new Color(240, 240, 240));

		btnstandardmall.setFont(new Font("Tahoma", Font.PLAIN, 11));
		pnlB.setBackground(SystemColor.control);
		pnlB.setBounds(328, 98, 448, 653);
		huvudmeny.getContentPane().add(pnlB);
		pnlListaNollstall.add(pnlB);
		pnlAktorer.setBounds(10, 327, 308, 424);
		huvudmeny.getContentPane().add(pnlAktorer);
		pnlAktorer.setBackground(SystemColor.control);
		pnlListaNollstall.add(pnlAktorer);

		pList.setBorder(new TitledBorder(""));
	}

	public static void main(String[] args) throws IOException {

		huvudmeny.getContentPane().setBackground(SystemColor.control);
		huvudmeny.setBounds(100, 30, 803, 859);
		huvudmeny.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		huvudmeny.getContentPane().setLayout(null);

		huvudmeny.setJMenuBar(menuBar);

		systemMapp = System.getProperty("user.dir") + "\\";
		dataMapp = systemMapp + "data\\";

		Scanner scbeslutsfattare = new Scanner(new File(dataMapp + "beslutsfattare.txt"), "utf-8");
		while (scbeslutsfattare.hasNextLine()) {
			String line = scbeslutsfattare.nextLine();
			String[] words = line.split("%");
			blista.add(words[0]);
		}

		for (String s : blista) {
			modelratten.addElement(s);
		}
		for (String s : blista) {
			modelprotokollforare.addElement(s);
		}
		Scanner scNamndeman = new Scanner(new File(dataMapp + "nämndemän.txt"), "utf-8");
		while (scNamndeman.hasNextLine()) {
			String line = scNamndeman.nextLine();
			String[] words = line.split("%");
			nlista.add(words[0]);
		}
		for (String s : nlista) {
			modelNamndeman.addElement(s);
		}

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (!(evt.getNewValue() instanceof JComponent)) {
					return;
				}
				JViewport viewport = (JViewport) mallList.getParent();
				JComponent focused = (JComponent) evt.getNewValue();
				if (mallList.isAncestorOf(focused)) {
					((JComponent) focused.getParent()).scrollRectToVisible(focused.getBounds());
				}
			}
		});

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (!(evt.getNewValue() instanceof JComponent)) {
					return;
				}
				JViewport viewport = (JViewport) pList.getParent();
				JComponent focused = (JComponent) evt.getNewValue();
				if (pList.isAncestorOf(focused)) {
					((JComponent) focused.getParent()).scrollRectToVisible(focused.getBounds());
				}
			}
		});

		KeyboardFocusManager.getCurrentKeyboardFocusManager().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if (!(evt.getNewValue() instanceof JComponent)) {
					return;
				}
				JViewport viewport = (JViewport) mallList.getParent();
				JComponent focused = (JComponent) evt.getNewValue();
				if (mallList.isAncestorOf(focused)) {
					((JComponent) focused.getParent()).scrollRectToVisible(focused.getBounds());
				}
			}
		});

		cbmodelListaNollstall.add(modelAnnUtrT);
		cbmodelListaNollstall.add(modelTilltalad);
		cbmodelListaNollstall.add(modelBeslutB);
		cbmodelListaNollstall.add(modelBeslutT);
		cbmodelListaNollstall.add(modelBeslAvs);
		cbmodelListaNollstall.add(modelAntB);
		cbmodelListaNollstall.add(modelAntT);
		cbmodelListaNollstall.add(modelAntT);

		listmodelListaNollstall.add(modelAntAktorer);
		listmodelListaNollstall.add(modelAntForhor);
		listmodelListaDagar.add(modelAntForhor);
		listmodelListaNollstall.add(modelAntAnnUtr);
		listmodelListaDagar.add(modelAntAnnUtr);
		listmodelListaNollstall.add(modelAntPersutr);
		listmodelListaDagar.add(modelAntPersutr);
		listmodelListaNollstall.add(modelPaus);
		listmodelListaNollstall.add(modelAntTid);
		listmodelListaNollstall.add(modelAntDatum);

		listmodelListaNollstall.add(modelValdaAlt);
		tfAnnUtrAktb.setColumns(10);

		boxTolk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (boxTolk.isSelected() == true) {
					tfAntRoll.setText(null);
					tfAntRoll.setEnabled(false);
				}
				if (boxTolk.isSelected() == false) {
					tfAntRoll.setEnabled(true);
				}
			}
		});

		taPersForh1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					taPersForh1.transferFocus();
					e.consume();
				}
			}
		});
		boxBegarErsattning.setVisible(false);

		btnSparaBeslut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sparaBeslut();
			}
		});

		cbAnt1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {

				bestamInnehallCbAnt1(event);
			}
		});

		cbAnt2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {

				bestamInnehallCbAnt2(event);
			}
		});

		cbAnt3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {

				bestamInnehallCbAnt3(event);
			}
		});

		btnAntLaggTill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				pnlB.setVisible(true);
				antLaggTill();
			}
		});

		taAntBeslut1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					taAntBeslut1.transferFocus();
					e.consume();
				}

				if (taAntBeslut1.getText().contains("@")) {

					pnlBeslAvs.setVisible(true);
					cbAntBeslAvs.setModel(modelBeslAvs);
				} else {
					pnlBeslAvs.setVisible(false);
				}
			}
		});

		pnl1.setBounds(10, 11, 298, 76);
		huvudmeny.getContentPane().add(pnl1);
		huvudmeny.setFocusCycleRoot(true);

		pnlmalnr.setBounds(495, 34, 127, 20);
		pnl1.add(pnlmalnr);
		pnlmalnr.setLayout(null);

		cbbta.setBackground(SystemColor.control);
		cbbta.setBounds(0, 0, 42, 20);
		pnlmalnr.add(cbbta);
		cbbta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbbta.setEditable(true);
		cbbta.setSelectedItem(null);
		AutoCompleteDecorator.decorate(cbbta);
		cbbta.setEditable(false);
		cbListaDokNamn.add(cbbta);
		tfmalnr.setBounds(52, 0, 70, 20);
		tfmalnr.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tfmalnr.setColumns(10);
		pnlmalnr.add(tfmalnr);
		pnldoktyp.setBackground(SystemColor.control);

		pnl1.add(pnldoktyp);
		pnldoktyp.setLayout(null);

		cbdoktyp.setBackground(SystemColor.control);
		cbdoktyp.setBounds(0, 0, 143, 20);
		pnldoktyp.add(cbdoktyp);
		cbdoktyp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbdoktyp.setEditable(true);
		cbdoktyp.setSelectedItem("Dokumenttyp..");
		AutoCompleteDecorator.decorate(cbdoktyp);
		cbListaStandardMall.add(cbdoktyp);
		cbListaDokNamn.add(cbdoktyp);
		cbdoktyp.setEditable(false);

		cbunder1.setBounds(155, 0, 143, 20);
		pnldoktyp.add(cbunder1);
		cbunder1.setBackground(SystemColor.control);
		cbunder1.setEditable(true);
		cbunder1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbunder1.setSelectedItem(null);
		AutoCompleteDecorator.decorate(cbunder1);
		cbListaStandardMall.add(cbunder1);
		cbListaDokNamn.add(cbunder1);
		cbunder1.setVisible(false);
		pnlGaVidare.setBackground(SystemColor.control);

		pnl1.add(pnlGaVidare);
		pnlGaVidare.setLayout(null);

		btnGaVidare.setBounds(0, 0, 143, 23);
		pnlGaVidare.add(btnGaVidare);
		btnGaVidare.setMinimumSize(null);
		btnGaVidare.setBackground(new Color(240, 240, 240));
		btnGaVidare.setFont(new Font("Tahoma", Font.PLAIN, 11));

		veraDokMapp = hamtaVeraDokMapp();

		utgangspunkt();

		int dismissDelay = Integer.MAX_VALUE;
		ToolTipManager.sharedInstance().setDismissDelay(dismissDelay);

		cbunder1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Object item = e.getItem();
				if (e.getStateChange() == ItemEvent.SELECTED) {
					underDokTyp = item.toString();
				}
			}
		});

		btnstandardmall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File fStndMall = new File(stndMall);
				if (!fStndMall.exists()) {
					skapaTomtDokOmInteFinns();
				}
				oppnaMall(fStndMall);
			}
		});

		btnOppnaDok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop oppnadok = Desktop.getDesktop();
					oppnadok.open(new File(veraDok()));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});

		cbdoktyp.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {

				bestamCbunder1(event);
			}
		});

		btnSkapaDok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				skapaDokument();
				for (Dag d : daglista) {
					d.bevisning = false;
				}
				// laggTillSkrivning();
			}
		});

		btnGaVidare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				gaVidare();
			}
		});

		// Visa innehåll i Ärendetyp/Måltyp och bestäm innehåll i Ärendetyp/Måltyp//

		cbbta.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				bestamDokTyp(event);
			}
		});

		// Öppna/Skapa mall för valda alternativ//
		btnoppnamall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				File fMall = new File(mall);
				if (!fMall.exists()) {
					File fStndMall = new File(stndMall);
					skapaStndMallOmEjFinns(fStndMall);
					skapaMallFranStndMall(fStndMall, fMall);
				}
				oppnaMall(fMall);

			}
		});

		btnHamtaMall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				File fMall = new File(mall);
				if (!fMall.exists()) {
					File fStndMall = new File(stndMall);
					skapaStndMallOmEjFinns(fStndMall);
					skapaMallFranStndMall(fStndMall, fMall);
				}
				hamtaMall();
			}
		});

		// Lägg till dokumentalternativ//
		btnlaggtillalt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				laggTillDokumentalternativ();
				mall = mallPlats + mallNamn + skapaDokNamn(listDokAlt);
				if (modelValdaAlt.size() > 0) {
					btnHamtaMall.setEnabled(true);
					btnoppnamall.setEnabled(true);
				} else {
					btnHamtaMall.setEnabled(false);
					btnoppnamall.setEnabled(false);
				}
			}
		});

		cbDokAlt.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					btnlaggtillalt.doClick();
				}
			}
		});

		btntabortalt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				taBortDokumentalternativ();
				mall = mallPlats + mallNamn + skapaDokNamn(listDokAlt);
				if (modelValdaAlt.size() > 0) {
					btnHamtaMall.setEnabled(true);
					btnoppnamall.setEnabled(true);
				} else {
					btnHamtaMall.setEnabled(false);
					btnoppnamall.setEnabled(false);
				}
			}
		});

		btnbestavd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				bestamAvdelning();
			}
		});

		btnBestVeraDok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				sattVeraDokMappSokvag();
			}
		});

		btnLaggTillPerson.setBackground(new Color(240, 240, 240));
		btnLaggTillPerson.setFont(new Font("Tahoma", Font.PLAIN, 11));
		menuBar.add(btnLaggTillPerson);
		btnLaggTillPerson.setFocusable(false);
		btnBestVeraDok.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBestVeraDok.setFocusable(false);
		btnBestVeraDok.setBackground(SystemColor.menu);

		menuBar.add(btnBestVeraDok);

		btnLaggTillPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				laggTillPerson();
			}
		});

		huvudmeny.setVisible(true);
	}
}
