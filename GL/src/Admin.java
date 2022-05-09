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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class Admin {
	ConnexionBasedonne cnx = new ConnexionBasedonne() ;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	public int id;
	public int numapogee;
	JFrame frmServiceEtudiants;
	private JTable table;
	private JTextField textField;
	public JButton btnNewButton_3,btnNewButton_4;
	ConventionPDF cnv = new ConventionPDF();
	DocumentPDF attest = new DocumentPDF();
	RelvédeNotes rel = new RelvédeNotes();
	private JTextField textField_1;
	public JTextField field_rechercher;
	private JButton relevenote;
public String rec;
public 	String m;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
					window.frmServiceEtudiants.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		
				
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Admin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmServiceEtudiants = new JFrame();
		frmServiceEtudiants.setTitle("Service Etudiants");
		frmServiceEtudiants.setIconImage(Toolkit.getDefaultToolkit().getImage(Admin.class.getResource("/image/charte_graphique.png")));
		frmServiceEtudiants.getContentPane().setBackground(new Color(255, 255, 255));
		frmServiceEtudiants.setBounds(0, 0, 900, 650);
		
		frmServiceEtudiants.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton deconnecter;
		frmServiceEtudiants.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 170, 864, 296);
		frmServiceEtudiants.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.black);
		table.setBackground(Color.white);
		JTableHeader Theader = table.getTableHeader();
		Theader.setBackground(new Color(240, 240, 240));
		Theader.setForeground(new Color(30, 144, 255));
		Theader.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		String s="Select * from gl_demandescolarite where traitee ='0'";
		try {
			prepared = cnx.obtenirconnexion().prepareStatement(s);
			resultat=prepared.executeQuery();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setModel(DbUtils.resultSetToTableModel(resultat));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent args) {
				int ligne = table.getSelectedRow();
				String iddemande= table.getModel().getValueAt(ligne, 0).toString();
				String apogee= table.getModel().getValueAt(ligne, 6).toString();
				textField_1.setText(apogee);
				textField.setText(iddemande);
				id = Integer.parseInt(textField.getText()); 
				numapogee = Integer.parseInt(textField_1.getText()); 
			 
			}
		});
		scrollPane.setViewportView(table);
		
		btnNewButton_3 = new JButton("Accepter");
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setForeground(new Color(30, 144, 255));
		btnNewButton_3.setBackground(new Color(245, 245, 245));
		btnNewButton_3.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnNewButton_3.setIcon(new ImageIcon(Admin.class.getResource("/image/ok_accept_16747.png")));
		btnNewButton_3.setBounds(225, 491, 188, 44);
		btnNewButton_3.setFocusable(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frmServiceEtudiants.getContentPane().add(btnNewButton_3);
		
		
		btnNewButton_4 = new JButton("Refuser");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_4.setBorder(null);
		btnNewButton_4.setForeground(new Color(30, 144, 255));
		btnNewButton_4.setBackground(new Color(245, 245, 245));
		btnNewButton_4.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnNewButton_4.setIcon(new ImageIcon(Admin.class.getResource("/image/delete_remove_16744.png")));
		btnNewButton_4.setBounds(406, 491, 188, 44);
		btnNewButton_4.setFocusable(false);
		frmServiceEtudiants.getContentPane().add(btnNewButton_4);
		JButton recherche = new JButton("Rechercher");
		recherche.setForeground(new Color(30, 144, 255));
		recherche.setBorder(null);
		recherche.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		recherche.setBackground(new Color(255, 255, 255));
		recherche.setIcon(new ImageIcon(Admin.class.getResource("/image/trouver.png")));
		recherche.setBounds(329, 107, 133, 36);
		recherche.setFocusable(false);
		recherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if(field_rechercher.getText().equals("")) {
	        		 JOptionPane.showMessageDialog(null, "entrez un numero apogee");
	        	 }
	        	 else {
				String appoge = field_rechercher.getText().toString();
				double id = Integer.parseInt(appoge);
			String sql = "Select * from gl_demandescolarite where traitee ='0' and  apogee="+id;
			try {
				prepared = cnx.obtenirconnexion().prepareStatement(sql);
				resultat = prepared.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(resultat));
				field_rechercher.setText("");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        	 }
			
			}
		});
		frmServiceEtudiants.getContentPane().add(recherche);
		
		field_rechercher = new JTextField();
		field_rechercher.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(30, 144, 255)));
		field_rechercher.setBounds(80, 107, 250, 36);
		field_rechercher.setBackground(new Color(255, 255, 255));
		field_rechercher.addKeyListener(new KeyAdapter() {
		         public void keyPressed(KeyEvent e) {  
		            if(e.getKeyCode() == KeyEvent.VK_ENTER){
			        	 if(field_rechercher.getText().equals("")) {
			        		 JOptionPane.showMessageDialog(null, "entrez un numero apogee");
			        	 }
			        	 else {

		            	String appoge = field_rechercher.getText().toString();
						double id = Integer.parseInt(appoge);
					String sql = "Select * from gl_demandescolarite where traitee ='0' and  apogee="+id;
					try {
						prepared = cnx.obtenirconnexion().prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						field_rechercher.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        	 }
		            }
		         }        
		      });
		frmServiceEtudiants.getContentPane().add(field_rechercher);
		field_rechercher.setColumns(10);
		
		JButton attestation = new JButton("Attestation de scolarit\u00E9");
		attestation.setForeground(new Color(30, 144, 255));
		attestation.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		attestation.setFocusable(false);
		attestation.setBorder(null);
		attestation.setBackground(new Color(245, 245, 245));
		attestation.setBounds(0, 0, 182, 72);
		frmServiceEtudiants.getContentPane().add(attestation);
		attestation.setPreferredSize(new Dimension(100, attestation.getPreferredSize().height));
		
		relevenote = new JButton("Relev\u00E9 de notes");
		relevenote.setForeground(new Color(30, 144, 255));
		relevenote.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		relevenote.setBorder(null);
		relevenote.setBackground(new Color(245, 245, 245));
		relevenote.setBounds(180, 0, 182, 72);
		frmServiceEtudiants.getContentPane().add(relevenote);
		relevenote.setPreferredSize(new Dimension(100, 60));
		relevenote.setFocusable(false);

		
		JButton convention = new JButton("Convention de stage");
		convention.setForeground(new Color(30, 144, 255));
		convention.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		convention.setBackground(new Color(245, 245, 245));
		convention.setBorder(null);
		convention.setBounds(361, 0, 182, 72);
		frmServiceEtudiants.getContentPane().add(convention);
		convention.setPreferredSize(new Dimension(100, 60));
		convention.setFocusable(false);

		JButton historique = new JButton("Historique");
		historique.setForeground(new Color(30, 144, 255));
		historique.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		historique.setBackground(new Color(245, 245, 245));
		historique.setBorder(null);
		historique.setBounds(543, 0, 182, 72);
		frmServiceEtudiants.getContentPane().add(historique);
		historique.setPreferredSize(new Dimension(100, 60));
		historique.setFocusable(false);

		
		deconnecter = new JButton("Déconnexion");
		deconnecter.setIcon(new ImageIcon(Admin.class.getResource("/image/se-deconnecter.png")));
		deconnecter.setForeground(new Color(30, 144, 255));
		deconnecter.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		deconnecter.setBackground(new Color(245, 245, 245));
		deconnecter.setBorder(null);
		deconnecter.setLocation(724, 0);
		frmServiceEtudiants.getContentPane().add(deconnecter);
		deconnecter.setSize(new Dimension(162, 72));
		deconnecter.setPreferredSize(new Dimension(100, 60));
		deconnecter.setFocusable(false);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(30, 144, 255)));
		lblNewLabel.setBounds(0, 0, 886, 72);
		frmServiceEtudiants.getContentPane().add(lblNewLabel);
		deconnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Authentification auth = new Authentification();
				auth.main(null);
				frmServiceEtudiants.setVisible(false);
				}
		});
		
		historique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Historique h = new Historique();
				h.afficher();
				frmServiceEtudiants.setVisible(false);

					
			}
		});
		
		convention.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Convention();
			}
		});
		relevenote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relve();
			}
		});
		attestation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Attestation();
			}
		});
		
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
	public void Attestation() {
		afficher_attest();
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null,"Vous avez accépté la demande de l'étudiant avec apogée :"+numapogee);
				String sql1 = "UPDATE gl_demandescolarite SET traitee = '1', etat = 'Acceptée' WHERE idDemande ="+id;
				try {
					prepared = cnx.obtenirconnexion().prepareStatement(sql1);
					prepared.execute();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		attest.genererattestation(numapogee);
		afficher_attest() ;

			}
			
		});
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				JOptionPane.showMessageDialog(null,"Vous avez refusé la demande de l'étudiant avec apogée :"+numapogee);
				m=send(numapogee);
				EnvoiEmail envoi = new EnvoiEmail(m, "Réponse à votre demande", "Bonjour,désolé votre demande a été refusée"); 

				String sql1 = "UPDATE gl_demandescolarite SET traitee = 1, etat = 'Refusée' WHERE idDemande ="+id;
				try {
					prepared = cnx.obtenirconnexion().prepareStatement(sql1);
					prepared.execute();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				afficher_attest() ;

			}
		});

	}
	
	public void relve() {
		afficher_relve();
	
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Vous avez accépté la demande de l'étudiant avec apogée :"+numapogee);
				String sql1 = "UPDATE gl_demandescolarite SET traitee = '1', etat = 'Acceptée' WHERE idDemande ="+id;
				try {
					prepared = cnx.obtenirconnexion().prepareStatement(sql1);
					prepared.execute();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			
			rel.genererrelver(numapogee);
			afficher_relve();
			}
		});
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Vous avez refusé la demande de l'étudiant avec apogée :"+numapogee);
				m=send(numapogee);
				EnvoiEmail envoi = new EnvoiEmail(m, "Réponse à votre demande", "Bonjour,désolé votre demande a été refusée"); 

				String sql1 = "UPDATE gl_demandescolarite SET traitee = 1, etat = 'Rufusée' WHERE idDemande ="+id;
				try {
					prepared = cnx.obtenirconnexion().prepareStatement(sql1);
					prepared.execute();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				afficher_relve();
			
			}
		});
		
	}
	
	public void Convention() {
		afficher_cnv();
	
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Vous avez accépté la demande de l'étudiant avec apogée :"+numapogee);
				
				String sql1 = "UPDATE gl_demandescolarite SET traitee = '1', etat = 'Acceptée' WHERE idDemande ="+id;
				try {
					prepared = cnx.obtenirconnexion().prepareStatement(sql1);
					prepared.execute();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cnv.generer(numapogee);
			afficher_cnv();
			}
		});
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"Vous avez refusé la demande de l'étudiant avec apogée :"+numapogee);
				m=send(numapogee);
				EnvoiEmail envoi = new EnvoiEmail(m, "Réponse à votre demande", "Bonjour,désolé votre demande a été refusée"); 

				String sql1 = "UPDATE gl_demandescolarite SET traitee = 1, etat = 'Refusée' WHERE idDemande ="+id;
				try {
					prepared = cnx.obtenirconnexion().prepareStatement(sql1);
					prepared.execute();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				afficher_cnv();
			}
		});
	}
	
	public void afficher_attest() {
		String sql = "Select * from gl_demandescolarite where typedemande='Attestation de scolarité' && traitee='0'";
		try {
			prepared = cnx.obtenirconnexion().prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void afficher_relve(){
		String sql = "Select * from gl_demandescolarite where typedemande='Relevé de notes' && traitee='0'";
		try {
			prepared = cnx.obtenirconnexion().prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void afficher_cnv() {
		String sql = "Select * from gl_demandescolarite where typedemande='Convention de stage' && traitee='0'";
		try {
			prepared = cnx.obtenirconnexion().prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String send(int id) {
		String c = null;
		String sql = "Select * from student where apogee="+id;
		try {
			prepared = cnx.obtenirconnexion().prepareStatement(sql);
			resultat = prepared.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(resultat.next()) {
				c=resultat.getString("Email").toString();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	
}