import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Toolkit;


public class Historique {
	ConnexionBasedonne cnx = new ConnexionBasedonne() ;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	public int id;
	public int numapogee;
	private JFrame frmServiceEtudiants;
	private JTable table;
	private JTextField textField;
	ConventionPDF cnv = new ConventionPDF();
	DocumentPDF attest = new DocumentPDF();
	RelvédeNotes rel = new RelvédeNotes();
	private JTextField textField_1;
	public JTextField textField_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	public JComboBox comboBox;

	/**
	 * Launch the application.
}

	/**
	 * Create the application.
	 */
	public Historique() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServiceEtudiants = new JFrame();
		frmServiceEtudiants.setIconImage(Toolkit.getDefaultToolkit().getImage(Historique.class.getResource("/image/charte_graphique.png")));
		frmServiceEtudiants.setTitle("Service Etudiants");
		frmServiceEtudiants.getContentPane().setBackground(SystemColor.textHighlightText);
		frmServiceEtudiants.setBounds(0, 0, 900, 650);
		
		frmServiceEtudiants.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServiceEtudiants.setVisible(true);
				frmServiceEtudiants.getContentPane().setLayout(null);
		
	
		
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 157, 864, 339);
		frmServiceEtudiants.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.black);
		table.setBackground(Color.white);
		JTableHeader Theader = table.getTableHeader();
		Theader.setBackground(new Color(240, 240, 240));
		Theader.setForeground(new Color(30, 144, 255));
		Theader.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
			
		scrollPane.setViewportView(table);
		
		
		
		JButton btnNewButton_5 = new JButton("Trier");
		btnNewButton_5.setBorder(null);
		btnNewButton_5.setForeground(new Color(30, 144, 255));
		btnNewButton_5.setBackground(new Color(245, 245, 245));
		btnNewButton_5.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnNewButton_5.setBounds(429, 0, 209, 51);
		btnNewButton_5.setFocusable(false);

		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = comboBox.getSelectedItem().toString();
				if(type.equals("Convention de stage")) {
					String s =  "Select apogee,cin,Email,fullname,typedemande,etat from gl_demandescolarite  where typedemande='Convention de stage' && traitee='1'";;
					try {
						prepared = cnx.obtenirconnexion().prepareStatement(s);
						resultat=prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				else if(type.equals("Attestation de scolarité")) {
					String s =  "Select apogee,cin,Email,fullname,typedemande,etat from gl_demandescolarite  where typedemande='Attestation de scolarité' && traitee='1'";;
					try {
						prepared = cnx.obtenirconnexion().prepareStatement(s);
						resultat=prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				else if(type.equals("Relevé de notes")) {
					String s =  "Select apogee,cin,Email,fullname,typedemande,etat from gl_demandescolarite  where typedemande='Relevé de notes' && traitee='1'";;
					try {
						prepared = cnx.obtenirconnexion().prepareStatement(s);
						resultat=prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
	

				
			}
		});
		frmServiceEtudiants.getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Rechercher");
		btnNewButton_6.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_6.setForeground(new Color(30, 144, 255));
		btnNewButton_6.setBorder(null);
		btnNewButton_6.setBackground(new Color(255, 255, 255));
		btnNewButton_6.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnNewButton_6.setIcon(new ImageIcon(Historique.class.getResource("/image/trouver.png")));
		btnNewButton_6.setBounds(251, 89, 131, 41);
		btnNewButton_6.setFocusable(false);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(textField_2.getText().equals("")) {
	        		 JOptionPane.showMessageDialog(null, "entrez un numero apogee");
	        	 }
	        	 else {
				String appoge = textField_2.getText().toString();
				double id = Integer.parseInt(appoge);
			String sql = "Select apogee,cin,Email,fullname,typedemande,etat from gl_demandescolarite where traitee ='1' and  apogee="+id;
			try {
				prepared = cnx.obtenirconnexion().prepareStatement(sql);
				resultat = prepared.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(resultat));
				textField_2.setText("");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        	 }
			}
		});
		frmServiceEtudiants.getContentPane().add(btnNewButton_6);
		
		textField_2 = new JTextField();
		textField_2.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(30, 144, 255)));
		textField_2.setBounds(32, 87, 220, 41);
		textField_2.addKeyListener(new KeyAdapter() {
		         public void keyPressed(KeyEvent e) {                
		            if(e.getKeyCode() == KeyEvent.VK_ENTER){
		            	 if(textField_2.getText().equals("")) {
			        		 JOptionPane.showMessageDialog(null, "entrez un numero apogee");
			        	 }
			        	 else {
		            	String appoge = textField_2.getText().toString();
						double id = Integer.parseInt(appoge);
					String sql = "Select apogee,cin,Email,fullname,typedemande,etat from gl_demandescolarite where traitee ='1' and apogee="+id;
					try {
						prepared = cnx.obtenirconnexion().prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						textField_2.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        	 }
		            }
		         }        
		      });
		frmServiceEtudiants.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton = new JButton("D\u00E9connexion");
		btnNewButton.setBorder(null);
		btnNewButton.setIcon(new ImageIcon(Historique.class.getResource("/image/se-deconnecter.png")));
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnNewButton.setForeground(new Color(30, 144, 255));
		btnNewButton.setBackground(new Color(245, 245, 245));
		btnNewButton.setBounds(636, 0, 248, 51);
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Authentification auth = new Authentification();
				auth.main(null);
				frmServiceEtudiants.setVisible(false);

			}
		});
		frmServiceEtudiants.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnNewButton_1.setForeground(new Color(30, 144, 255));
		btnNewButton_1.setBackground(new Color(245, 245, 245));
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Admin home =new Admin();
				home.main(null);
				frmServiceEtudiants.setVisible(false);

			}
		});
		btnNewButton_1.setBounds(0, 0, 209, 51);
		frmServiceEtudiants.getContentPane().add(btnNewButton_1);
		
		comboBox = new JComboBox();
		comboBox.setBorder(null);
		comboBox.setBounds(209, 0, 220, 51);
		comboBox.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		comboBox.setForeground(new Color(30, 144, 255));
		comboBox.setBackground(new Color(245, 245, 245));
		comboBox.setFocusable(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Type de demande","Attestation de scolarité","Relevé de notes","Convention de stage"}));
		frmServiceEtudiants.getContentPane().add(comboBox);
		
		textField_1 = new JTextField();
		textField_1.setBounds(235, 408, 96, 19);
		//frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(218, 366, 140, 27);
		//frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
	public void afficher() {
		String s =  "Select apogee,cin,Email,fullname,typedemande,etat from gl_demandescolarite where traitee='1' ";;
		try {
			prepared = cnx.obtenirconnexion().prepareStatement(s);
			resultat=prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
