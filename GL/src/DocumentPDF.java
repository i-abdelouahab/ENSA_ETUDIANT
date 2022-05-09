import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

//import Projet.list_attestation;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Desktop;



public class DocumentPDF {
	//ajout des variables
	ConnexionBasedonne cnx = new ConnexionBasedonne() ;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	private JFrame frame;
	public int id;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DocumentPDF window = new DocumentPDF();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DocumentPDF() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id=18043980;
			genererattestation(id);
		
			}
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.CENTER);
	}
	public void genererattestation(int id) {
		Document doc = new Document();
		String sql = "SELECT * FROM student WHERE apogee="+id;
		try {
			prepared = cnx.obtenirconnexion().prepareStatement(sql);
			resultat = prepared.executeQuery();
		
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
		 com.itextpdf.text.Font title =  FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK);
	        com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 12, BaseColor.BLACK);
		try {
			PdfWriter.getInstance(doc,new FileOutputStream("src/attestation.pdf"));
			doc.open();
			
			try {
				Image img = Image.getInstance("src/image/ra.png");
				img.setAlignment(30);
				img.scalePercent(40);
				doc.add(img);
				doc.add(new Paragraph("ROYAUME DU MAROC \n Universite Abdelmalek Essaadi \n Ecole Nationale des Sciences \n Appliquees de Tetouan ",font));
				 Paragraph par = new Paragraph("\n \n ATTESTATION DE SCOLARITE",title);
				par.setAlignment(Element.ALIGN_CENTER);
				doc.add(par);
				doc.add(new Paragraph("\n \n Le directeur de l'Ecole Nationale des Sciences Appliquées de Tétouan atteste que l'étudiant: "));
		  
				if(resultat.next()) {
		        doc.add(new Paragraph(resultat.getString("fullname").toString()));
		        doc.add(new Paragraph("\n Numéro de la carte Nationale: "+resultat.getString("cin").toString()));
		        doc.add(new Paragraph("\n Code National de l'étudiant: "+resultat.getString("apogee").toString()));
		        doc.add(new Paragraph("\n né(e) le "+resultat.getString("date_naiss").toString()+" a "+resultat.getString("lieu").toString()));
				}
		        

		        doc.add(new Paragraph("\n \n Poursuit ses études à l'école Nationale des Sciences Appliquées de Tétouan pour l'année universitaire 2021/2022 \n\n", font));
		        doc.add(new Paragraph("\n Dipôlome: Diplôme d'ingénieur-"+resultat.getString("filiere").toString()));
		        doc.add(new Paragraph("\n Année : "+resultat.getString("niveau").toString()+" "+resultat.getString("filiere").toString()));

		     Paragraph prgp = new Paragraph("\n\n\n\n Mr Sttitou Mostafa " , font);
			 prgp.setAlignment(Element.ALIGN_RIGHT);
			 doc.add(prgp);
				doc.close();
			Desktop.getDesktop().open(new File ("src/attestation.pdf"));
				String e;
				e=resultat.getString("Email").toString();
				System.out.println(e);
			String file="src/attestation.pdf";

				
				DocumentMail em = new DocumentMail();
				em.email(e,file);
				System.out.println("Email envoyé avec succés");

				} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
							
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
