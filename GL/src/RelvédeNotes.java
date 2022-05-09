import java.awt.EventQueue;

import javax.swing.JFrame;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class Relv�deNotes {
	ConnexionBasedonne cnx = new ConnexionBasedonne() ;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	private JFrame frame;
	public String e;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relv�deNotes window = new Relv�deNotes();
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
	public Relv�deNotes() {
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
				
		//	genererrelver(17021310);
				 
				
				
			}
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.CENTER);
	}
	public void genererrelver(int id) {
		Document doc = new Document();
		String sql = "Select * from notes join student on id_etudiant = idstudent where apogee="+id;
		try {
			prepared = cnx.obtenirconnexion().prepareStatement(sql);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			resultat = prepared.executeQuery();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 com.itextpdf.text.Font title =  FontFactory.getFont(FontFactory.COURIER_BOLD, 18, BaseColor.BLACK);
	        com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 12, BaseColor.BLACK);
	        com.itextpdf.text.Font niveau = FontFactory.getFont(FontFactory.COURIER_BOLD, 10, BaseColor.RED);

		try {
			PdfWriter.getInstance(doc, new FileOutputStream("src/fichiers/ReleveNote.pdf"));
			doc.open();
			Image img = Image.getInstance("src/image/ra.png");
			img.setAlignment(30);
			img.scalePercent(40);
			doc.add(img);
			doc.add(new Paragraph("Universite Abdelmalek Essaadi \n Ecole Nationale des Sciences \n Appliquees de Tetouan ",font));
			 Paragraph par = new Paragraph("\n \n Relev� des Notes",title);
			 Paragraph niv = new Paragraph("   Fili�re: premiere annee genie informatique",niveau);
			 Paragraph par1 = new Paragraph("  Ann�e Universitaire   2020/2021  ",font);
			par1.setAlignment(Element.ALIGN_CENTER);
			par.setAlignment(Element.ALIGN_CENTER);
			niv.setAlignment(Element.ALIGN_CENTER);

			doc.add(par);
			doc.add(niv);
			doc.add(par1);
			//  	          paragraph.add(new Chunk("Nom complet : "+res.getString("Nom_complet")+"\n"));

			doc.add(new Paragraph("L'�l�ve Ing�nieur:"));
			if(resultat.next()) {
				e=resultat.getString("Email").toString();
				doc.add(new Paragraph(" Nom et Pr�nom : "+resultat.getString("fullname").toString()));
				doc.add(new Paragraph("CNE: "+resultat.getString("cne").toString()));
				doc.add(new Paragraph("Code Apog�e:"+ resultat.getString("apogee").toString()));
				doc.add(new Paragraph("A obtenu les r�sultats suivants pour la deuxi�me de la fili�re G�nie Informatique \n \n"));
				

			}
			
			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(100);
			PdfPCell cell;
	
			cell = new PdfPCell(new Phrase("Intitul� du Module", FontFactory.getFont(FontFactory.COURIER_BOLD,10)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Note/20", FontFactory.getFont(FontFactory.COURIER_BOLD,10)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Resultat", FontFactory.getFont(FontFactory.COURIER_BOLD,10)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			table.addCell(cell);
			
		///REMPLISSAGE DE LA TABLE
			double somme=0;
			while(resultat.next())
			{
				
				//doc.add(new Paragraph(resultat.getString("fullname").toString()));
			

			cell = new PdfPCell(new Phrase(resultat.getString("module").toString(), FontFactory.getFont(FontFactory.COURIER_BOLD,12)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			
			cell = new PdfPCell(new Phrase(resultat.getString("valeur").toString(), FontFactory.getFont("Ariel",12)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			double num;
			num=Double.parseDouble(resultat.getString("valeur").toString());
			somme+=num;
			if(num>=12) {
				cell = new PdfPCell(new Phrase("valid�", FontFactory.getFont("Ariel",12)));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}
			else {
	cell = new PdfPCell(new Phrase("non valid�", FontFactory.getFont("Ariel",12)));
	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(cell);
}
			}
		
			  double moy=somme/12;
			  String ans;
				ans=String.format("%.2f",moy);

			doc.add(table);
			doc.add(new Paragraph("\n \n"));
			
			PdfPTable resultat = new PdfPTable(2);
			resultat.setWidthPercentage(50);
			PdfPCell cel;
			cel = new PdfPCell(new Phrase("Moyenne du l'ann�e   ", FontFactory.getFont(FontFactory.COURIER_BOLD,10)));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			resultat.addCell(cel);
			cel = new PdfPCell(new Phrase(ans, FontFactory.getFont(FontFactory.COURIER_BOLD,10)));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBackgroundColor(BaseColor.YELLOW);
			resultat.addCell(cel);
			
			cel = new PdfPCell(new Phrase("R�sultat de l'ann�e   ", FontFactory.getFont(FontFactory.COURIER_BOLD,10)));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
		if(moy>=12) {
			resultat.addCell(cel);
			cel = new PdfPCell(new Phrase("  Admis ", FontFactory.getFont(FontFactory.COURIER_BOLD,10)));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBackgroundColor(BaseColor.YELLOW);
		}
		
		else {
			resultat.addCell(cel);
			cel = new PdfPCell(new Phrase("  Ajourn� ", FontFactory.getFont(FontFactory.COURIER_BOLD,10)));
			cel.setHorizontalAlignment(Element.ALIGN_CENTER);
			cel.setBackgroundColor(BaseColor.YELLOW);
		}

			resultat.addCell(cel);
			resultat.setHorizontalAlignment(Element.ALIGN_RIGHT);
			doc.add(resultat);
			
			doc.add(new Paragraph(" \n \n La pr�sente attestation est d�livr�e � l'int�ress�(e) pour servir et valoir ce que de droit."));
			String s= LocalDate.now().toString();
			DateFormat to   = new SimpleDateFormat("dd/MM/yyyy"); // wanted format
			DateFormat from = new SimpleDateFormat("yyyy-MM-dd"); // current format
			
			
			DateFormat oFormatter = new SimpleDateFormat("dd-MM-yyyy");

			doc.add(new Paragraph("Fait � T�touan,le:"+to.format(from.parse(s))));
			doc.add(new Paragraph("\n  AC: Acquis par Compensation           					NV: Non Valid�"));
			doc.add(new Paragraph("	 N.B. Le pr�sent document n'est d�livr� qu'en un seul exemplaire. Il appartient � l'�tudiant d'en faire des photocopies certifi�es conformes."));

			
			doc.close();
			Desktop.getDesktop().open(new File ("src/fichiers/ReleveNote.pdf"));
			System.out.println(e);
			String file="src/fichiers/ReleveNote.pdf";
				DocumentMail em = new DocumentMail();
				em.email(e,file);
				System.out.println("Email envoy� avec succ�s");				
			 
		} catch (IOException | DocumentException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}