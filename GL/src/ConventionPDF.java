import java.awt.EventQueue;

import javax.swing.JFrame;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Desktop;
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

public class ConventionPDF {
public int id;
	private JFrame frame;
	ConnexionBasedonne cnx = new ConnexionBasedonne() ;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
public String e;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConventionPDF window = new ConventionPDF();
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
	public ConventionPDF() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generer(18043980);
						}
				
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.CENTER);
	}
	public void generer(int id) {
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
		PdfWriter.getInstance(doc,new FileOutputStream("src/fichiers/Convention.pdf"));
		doc.open();
		
		try {
			Image img = Image.getInstance("src/image/ra.png");
			img.setAlignment(30);
			img.scalePercent(40);
			doc.add(img);
			doc.add(new Paragraph("Universite Abdelmalek Essaadi \n Ecole Nationale des Sciences \n Appliquees de Tetouan ",font));
			 Paragraph par = new Paragraph("\n \n CONVENTION DE STAGE",title);
			 Paragraph par1 = new Paragraph("ENTRE",title);
			par.setAlignment(Element.ALIGN_CENTER);
			par1.setAlignment(Element.ALIGN_CENTER);
			doc.add(par);
			 doc.add(par1);
			doc.add(new Paragraph("\n L'Ecole Nationale des Sciences Appliqu�es, Universit� Abdelmalek Essa�di-T�touan \n B.P.2222,Mhannech II, T�touan, Maroc"));
	        doc.add(new Paragraph("T�l. +212 5 39 68 80 27; Fax. +212 39 99 46 24.Web:https://ensa-tetouan.ac.ma"));
	        	 
	        doc.add(new Paragraph("\n Repr�sent� par le Professeur Mostafa STITOU en qualit� de Directeur."));
	        Paragraph par2 = new Paragraph("ci-apr�s,d�nomm� l'Etablissement");
	        par2.setAlignment(Element.ALIGN_RIGHT);
	        doc.add(par2);
	        
	        Paragraph par3 = new Paragraph("ET");
	        par3.setAlignment(Element.ALIGN_CENTER);
	        doc.add(par3);
	        doc.add(new Paragraph("La Soci�t�:................................................................................."));
	        doc.add(new Paragraph("Adresse:...................................................................................."));
	        doc.add(new Paragraph("T�l:...............                                                Email:...................."));
	        doc.add(new Paragraph("\n Repr�sent�e par Monsieur................................. en qualit� ...................."));
	        Paragraph par4 = new Paragraph("ci-apr�s,d�nomm� l'ENTREPRISE");
	        par4.setAlignment(Element.ALIGN_RIGHT);
	        doc.add(par2);
	        Paragraph article1 = new Paragraph("Article 1: Engagement",font);
	        Paragraph article2 = new Paragraph("Article 2 : Objet",font);
	        Paragraph article3 = new Paragraph("Article 3: Encadrement et suivi",font);
	        Paragraph article4 = new Paragraph("\n Article 4: Programme",font);
	        Paragraph article5 = new Paragraph("Article 5: Idemenit� de Stage",font);
	        Paragraph article6 = new Paragraph("Article 6: R�glement",font);
	        Paragraph article7 = new Paragraph("Article 7: Confidentialit�",font);
	        Paragraph article8 = new Paragraph("Article 8: Assurance accident de travail",font);
	        Paragraph article9 = new Paragraph("Article 9: Evaluation de l'entreprise",font);
	        Paragraph article10 = new Paragraph("Article 10: R�glement",font);

	        doc.add(article1);
	        try {
				if(resultat.next()) {
				doc.add(new Paragraph("L'ENTREPRISE accepte de recevoir � titre de stagiaire " +resultat.getString("fullname").toString()+" �tudiante de la fili�re du "+resultat.getString("filiere").toString()+" de l'ENSA de T�touan, Universit� Abdelmalek Essa�di	(T�touan), pour une p�riode allant du ....... au ...... En aucun cas, cette convention ne pourra autoriser les �tudiants � s'absenter durant la p�riode des contr�les ou des enseignements."));
				doc.add(article2);
				doc.add(new Paragraph("Le stage aura pour objet essentiel d'assurer l'application pratique de l'enseignement donn� par l'Etablissement, et ce, en organisant des visites sur les installations et en r�alisant des �tudes propos�es par L'ENTREPRISE."));
				doc.add(article3);
				doc.add(new Paragraph("Pour accompagner le Stagiaire durant son stage, et ainsi instaurer une v�ritable collaboration L'ENTREPRISE/Stagiaire/Etablissement L'ENTREPRISE d�signe Mme/Mr encadrant(e)................. et parrain (e), pour superviser et assurer la qualit� du travail fourni par le Stagiaire.L'Etablissement d�signe.......... en tant que tuteur qui procurera une assistance p�dagogique."));
				doc.add(article4);
doc.add(new Paragraph("Le th�me du stage est .....................Ce programme a �t� d�fini conjointement par l'Etablissement, L'ENTREPRISE et le Stagiaire. Le contenu de ce programme doit permettre au Stagiaire une r�flexion en relation avec les enseignements ou le projet de fin d'�tudes qui s'inscrit dans le programme de formation de l'Etablissement."));
doc.add(article5);
doc.add(new Paragraph("Au cours du stage, l'�tudiant ne pourra pr�tendre � aucun salaire de la part de l'ENTREPRISE. Cependant, si l'ENTREPRISE et l'�tudiant le conviennent, ce dernier pourra recevoir une indemnit� forfaitaire de la part de l'ENTREPRISE des frais occasionn�s par la mission confi�e � l'�tudiant."));
				doc.add(article6);
				doc.add(new Paragraph("Pendant la dur�e du stage, le Stagiaire reste plac� sous la responsabilit� de l'Etablissement. Cependant, l'�tudiant est tenu d'informer l'�cole dans un d�lai de 24h sur toute modification portant sur la convention d�j� sign�e, sinon il en assumera toute sa responsabilit� sur son non respect de la convention sign�e par l'�cole.\n Toutefois, le Stagiaire est soumis � la discipline et au r�glement int�rieur de L'ENTREPRISE. En cas de manquement, L'ENTREPRISE se r�serve le droit de mettre fin au stage apr�s en avoir convenu avec le Directeur de l'Etablissement."));
				doc.add(article7);
				doc.add(new Paragraph("Le Stagiaire et l'ensemble des acteurs li�s � son travail (l'administration de l'Etablissement, le parrain p�dagogique...) sont tenus au secret professionnel. Ils s'engagent � ne pas diffuser les informations recueillies � des fins de publications, conf�rences, communications, sans raccord pr�alable de L'ENTREPRISE. Cette obligation demeure valable apr�s l'expiration du stage"));
				doc.add(article8);	
				doc.add(new Paragraph("L'Etablissement devra obligatoirement souscrire une assurance couvrant la Responsabilit� Civile et Accident de Travail du Stagiaire, durant les stages et trajets effectu�s. En cas d'accident de travail survenant durant la p�riode du stage, L'ENTREPRISE s'engage � faire parvenir imm�diatement � l'Etablissement toutes les informations indispensables � la d�claration dudit accident."));
				doc.add(article9);
				doc.add(new Paragraph("Le stage accompli, le parrain �tablira un rapport d'appr�ciations g�n�rales sur le travail effectu� et le comportement du Stagiaire durant son s�jour chez L'ENTREPRISE L'ENTREPRISE remettra au Stagiaire une attestation indiquant la nature et la dur�e des travaux effectu�s."));
				doc.add(article10);
				doc.add(new Paragraph("A l'issue de chaque stage, le Stagiaire r�digera un rapport de stage faisant �tat de ses travaux et de son v�cu au sein de L'ENTREPRISE. Ce rapport sera communiqu� � L'ENTREPRISE et restera strictement confidentiel."));
				Paragraph fait = new Paragraph("Fait � T�touan le ..............");
				 fait.setAlignment(Element.ALIGN_RIGHT);

				doc.add(fait);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		Paragraph prgp = new Paragraph("\n\n Mr Sttitou Mostafa " , font);
		 prgp.setAlignment(Element.ALIGN_RIGHT);
		 doc.add(prgp);
			doc.close();
			Desktop.getDesktop().open(new File ("src/fichiers/Convention.pdf"));
			
			try {
				e=resultat.getString("Email").toString();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println(e);
		String file="src/fichiers/Convention.pdf";
			DocumentMail em = new DocumentMail();
			em.email(e,file);
			System.out.println("Email envoy� avec succ�s");

			} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
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
