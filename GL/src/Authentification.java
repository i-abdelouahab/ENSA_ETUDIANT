import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;

import java.sql.*;
import java.util.*;
import java.util.List;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Authentification {
	
	private Connection con;
	private PreparedStatement Pst;
	private ResultSet rs;

	private JFrame frmAuthentification;
	private JTextField Identifiant;
	private JPasswordField Password;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification window = new Authentification();
					window.frmAuthentification.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentification() {
		Authentification();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void Authentification() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/serviceetudiant","root","");
		}
		catch(ClassNotFoundException ex) {
			
		}
		catch(SQLException ex) {
			
		}
		
		frmAuthentification = new JFrame();
		frmAuthentification.setIconImage(Toolkit.getDefaultToolkit().getImage(Authentification.class.getResource("/image/charte_graphique.png")));
		frmAuthentification.setTitle("Service Etudiants");
		frmAuthentification.getContentPane().setBackground(new Color(255, 255, 255));
		frmAuthentification.setBounds(0, 0, 900, 650);
		frmAuthentification.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAuthentification.getContentPane().setLayout(null);
		
		Identifiant = new JTextField();
		Identifiant.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		Identifiant.setBounds(298, 314, 310, 44);
		Identifiant.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		Identifiant.setBackground(new Color(255, 255, 255));
		frmAuthentification.getContentPane().add(Identifiant);
		Identifiant.setColumns(10);
		
		Password = new JPasswordField();
		Password.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		Password.setBounds(298, 412, 310, 44);
		Password.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		Password.setBackground(new Color(255, 255, 255));
		frmAuthentification.getContentPane().add(Password);
		
		JLabel lblIdentifiant = new JLabel("Identifiant");
		lblIdentifiant.setBounds(298, 284, 310, 44);
		lblIdentifiant.setForeground(new Color(0, 0, 0));
		lblIdentifiant.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		frmAuthentification.getContentPane().add(lblIdentifiant);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(298, 382, 310, 44);
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		frmAuthentification.getContentPane().add(lblPassword);
		
		JLabel lblError = new JLabel("SERVICES POUR LES ETUDIANTS");
		lblError.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(223, 143, 463, 97);
		lblError.setForeground(new Color(0, 0, 0));
		frmAuthentification.getContentPane().add(lblError);
		
		JButton Login = new JButton("Se connecter");
		Login.setBounds(463, 518, 145, 44);
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = null,mot_de_passe = null;
				try {
					Pst=con.prepareStatement("select name,password from login where id= ?");
					int id = 1;
					
					Pst.setInt(1, id);
					ResultSet rs = Pst.executeQuery();
					if(rs.next()==true) {
						nom=rs.getString(1);
						mot_de_passe=rs.getString(2);
					}
					}
				 catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(nom.equals(Identifiant.getText()) && mot_de_passe.equals(Password.getText()) ) {
					Admin window = new Admin();
					window.frmServiceEtudiants.setVisible(true);
					frmAuthentification.setVisible(false);
				}
				else if(!nom.equals(Identifiant.getText()) && mot_de_passe.equals(Password.getText()) ){
					JOptionPane.showMessageDialog(null,"L'identifiant est incorrect !");
					Identifiant.setText("");
					Password.setText("");
				}
				else if(nom.equals(Identifiant.getText()) && !mot_de_passe.equals(Password.getText()) ){
					JOptionPane.showMessageDialog(null,"Mot de passe incorrect !");
					Identifiant.setText("");
					Password.setText("");
				}
				else if(!nom.equals(Identifiant.getText()) && !mot_de_passe.equals(Password.getText()) ){
					JOptionPane.showMessageDialog(null,"Les données sont incorrects !");
					Identifiant.setText("");
					Password.setText("");
				}
			}
		});
		
		Login.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(30, 144, 255)));
		Login.setForeground(new Color(255, 255, 255));
		Login.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		Login.setBackground(new Color(30, 144, 255));
		Login.setFocusable(false);
		frmAuthentification.getContentPane().add(Login);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Authentification.class.getResource("/image/logo_ensa_tetouan.png")));
		lblNewLabel.setBounds(0, 0, 884, 120);
		frmAuthentification.getContentPane().add(lblNewLabel);
		
	}
}