package gob.dgs.dgs.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import com.itextpdf.text.Rectangle;

import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;

import com.itextpdf.text.pdf.PdfWriter;

import gob.dgs.dgs.model.ListaAsistencia;


public class HeaderFooter extends PdfPageEventHelper{
	
	private Font fH14 = new Font(Font.FontFamily.HELVETICA, 14F, Font.NORMAL);
	private Font fHB14 = new Font(Font.FontFamily.HELVETICA, 14F, Font.BOLD);
	private Font fH12 = new Font(Font.FontFamily.HELVETICA, 12F, Font.NORMAL);
	private Font fHB12 = new Font(Font.FontFamily.HELVETICA, 12F, Font.BOLD);
	private BaseColor dgsColor;
	
	
	 public void onStartPage (PdfWriter writer, Document document) {
		  Rectangle rect = writer.getBoxSize("art");
	    	PdfPTable tablaEncabezado = new PdfPTable(1);
			tablaEncabezado.setWidthPercentage(100);
		//	tablaEncabezado.setWidths(new float[] { 100 });

			 Paragraph h1= new Paragraph("Secretaría de Finanzas",fHB14);
	          PdfPCell v1= new PdfPCell(h1);
	          v1.setHorizontalAlignment(Element.ALIGN_CENTER);
	         // v1.setColspan(6);v1.setRowspan(1);
	          v1.setBorder(Rectangle.NO_BORDER);
	          tablaEncabezado.addCell(v1);
	          
	          Paragraph p1 = new Paragraph("Unidad de Apoyo Institucional\r\n"
	          		+ "Dirección General de Seguimiento\r\n"
	          		+ "\r\n"
	          		+ "LISTA DE ASISTENCIA\r\n"
	          		+ "Reunión de Trabajo para la Entrega y Recepción de Documentación\r\n",fH14);
	            PdfPCell c1 = new PdfPCell(p1);
	            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	            c1.setBorder(Rectangle.NO_BORDER);
	        //    cx.setColspan(4);
	        
	            tablaEncabezado.addCell(c1);
	            try {
					document.add(tablaEncabezado);
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	 }
	 

    public void onEndPage (PdfWriter writer, Document document) {
       // Rectangle rect = writer.getBoxSize("art");
//    
//        Rectangle rect = new Rectangle(5, 5, 100, 600);
//        ColumnText.showTextAligned(writer.getDirectContent(),
//
//                Element.ALIGN_RIGHT, new Phrase(String.format("page %d", writer.getPageNumber())),
//
//                (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase("Página:"+writer.getCurrentPageNumber()), 780, 22, 0);
      
        PdfPTable tablaPie = new PdfPTable(1);
    	tablaPie.setWidthPercentage(100);
		
    	 Paragraph h1= new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n",fHB14);
         PdfPCell v1= new PdfPCell(h1);
         v1.setHorizontalAlignment(Element.ALIGN_CENTER);
         //v1.setRowspan(10);
         v1.setBorder(Rectangle.NO_BORDER);
         tablaPie.addCell(v1);tablaPie.addCell(v1);
         
		PdfPCell celdaLogo = new PdfPCell();
		celdaLogo.setBorder(PdfPCell.NO_BORDER);
		Image imgLogo;
		
		try {
			imgLogo = Image.getInstance("WEB-INF/Images/Pie de PaginaDGS.png");
			//celdaLogo.setPadding(180);
			imgLogo.scalePercent(100);
			celdaLogo.addElement(imgLogo);
			

			tablaPie.addCell(celdaLogo);
		        
		    try {
				document.add(tablaPie);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
    
    
    
	
}
