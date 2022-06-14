package gob.dgs.dgs.util;





	import com.itextpdf.text.BaseColor;
	import com.itextpdf.text.Document;
	import com.itextpdf.text.DocumentException;
	import com.itextpdf.text.Element;
	import com.itextpdf.text.Font;
	//import com.itextpdf.text.FontFactory;
	import com.itextpdf.text.Image;
	import com.itextpdf.text.PageSize;
	//import com.itextpdf.text.PageSize;
	import com.itextpdf.text.Paragraph;
	import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ExtendedColor;
import com.itextpdf.text.pdf.PdfPCell;
	import com.itextpdf.text.pdf.PdfPTable;
	import com.itextpdf.text.pdf.PdfWriter;

import gob.dgs.dgs.model.ListaAsistencia;
import gob.dgs.dgs.model.Persona;

import java.io.*;
	import java.net.MalformedURLException;
	import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

	import javax.print.Doc;
	import javax.print.DocFlavor;
	import javax.print.DocPrintJob;
	import javax.print.PrintException;
	import javax.print.PrintService;
	import javax.print.PrintServiceLookup;
	import javax.print.SimpleDoc;
	import javax.servlet.ServletOutputStream;
	//import java.util.Date;	
	//import java.util.List; 

	public class PdfLista {
		
		private Font fH14 = new Font(Font.FontFamily.HELVETICA, 14F, Font.NORMAL);
		private Font fHB14 = new Font(Font.FontFamily.HELVETICA, 14F, Font.BOLD);
		private Font fH12 = new Font(Font.FontFamily.HELVETICA, 12F, Font.NORMAL);
		private Font fHB12 = new Font(Font.FontFamily.HELVETICA, 12F, Font.BOLD);
		private BaseColor dgsColor;
		

		public PdfLista(ListaAsistencia l,OutputStream ops) throws MalformedURLException, IOException {
		
			dgsColor = new CustomColor(ExtendedColor.TYPE_RGB, 95F / 255F, 34F / 255F, 100F / 255F);
		 	try {
	    		Document document = new Document(PageSize.LETTER.rotate(),10,10,20,10); 
//	    	
//		        PdfWriter.getInstance(document,ops);
//		        document.open();
//	    	
//		        PdfWriter.getInstance(document,ops);
//		        document.open();
	    		 PdfWriter writer=PdfWriter.getInstance(document,ops);

	                //     new FileOutputStream("C:\\testHeaderAndFooter.pdf") );

//	             Rectangle rect = new Rectangle(1, 1, 33, 33);
////
//	             rect.setBorderColor(BaseColor.BLACK);
////
//	             writer.setBoxSize("art", rect);
//
//	             HeaderFooter header=new HeaderFooter();
//
//	             writer.setPageEvent(header);

	             document.open();


	        //    document.newPage();

	    		
	    		
	    		
		        
		       construirEncabezado(l, document);
		       
        
	            PdfPTable tabla = new PdfPTable(3);        
	      
				tabla.setWidthPercentage(100);
				tabla.setWidths(new float[] { 40,40,20 });
	            
	            
	            agregarCeldaConFondo("Titular / Representante\r\n"
	            		+ "Nombre\r\n",fHB12,tabla,true, 1,3);
	        
	            agregarCeldaConFondo("Cargo / Proveedor de Servicio Externo",fHB12,tabla,true,1,3);
	            agregarCeldaConFondo("Firma",fHB12,tabla,true,1,3);
	            
	            for(Persona p:l.getAsistentes()) {
	            	agregarCeldaConBorde(p.getTitulo()+" "+p.getNombre(), fH12,tabla, false);
	            	agregarCeldaConBorde(p.getCargo(), fH12,tabla, false);

	            	agregarCeldaConBorde("", fH12,tabla, false);
	            }
	            for (int i=l.getAsistentes().size();i<7;i++) {
	            	agregarCeldaConBorde("", fH12,tabla, false);
	            	agregarCeldaConBorde("", fH12,tabla, false);
	            	agregarCeldaConBorde("", fH12,tabla, false);
	            }
	            
	            document.add(tabla);
	            construirPieDePagina(l,document);
	            document.close();
	    	    System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
	    	} catch (DocumentException documentException) {
	    	    System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
	    	}
	    }
		
		
		
		private void construirEncabezado(ListaAsistencia l,Document document) throws DocumentException {
			
			PdfPTable tablaEncabezado = new PdfPTable(1);
			tablaEncabezado.setWidthPercentage(100);
			tablaEncabezado.setWidths(new float[] { 100 });
	
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
	          
	          Paragraph px = new Paragraph("Proyecto: \""+l.getProyecto()+"\"",fHB14);
	            PdfPCell cx = new PdfPCell(px);
	            cx.setHorizontalAlignment(Element.ALIGN_CENTER);
	        //    cx.setColspan(4);
	            cx.setBorder(Rectangle.NO_BORDER);
	            tablaEncabezado.addCell(cx);
			
			// ENCABEZADO DEL COMPROBANTE
			 String fecha=dameFecha(l.getFecha());
			
			 Paragraph p2 = new Paragraph(fecha + ", "+l.getHora()+" horas"+"           \r\n",fH12);
		            PdfPCell c2 = new PdfPCell(p2);
		            c2.setHorizontalAlignment(Element.ALIGN_RIGHT);
		            c2.setBorder(Rectangle.NO_BORDER);
		        //    cx.setColspan(4);
		        
		            tablaEncabezado.addCell(c2);
		        
		            document.add(tablaEncabezado);
		}
		
private void construirPieDePagina(ListaAsistencia l,Document document) throws DocumentException, MalformedURLException, IOException {
			
			PdfPTable tablaPie = new PdfPTable(1);
			tablaPie.setWidthPercentage(110);
			tablaPie.setWidths(new float[] { 100 });
			
			PdfPCell celdaLogo = new PdfPCell();
			celdaLogo.setBorder(PdfPCell.NO_BORDER);
			Image imgLogo;
			imgLogo = Image.getInstance("WEB-INF/Images/PieDGS.jpg");
			celdaLogo.addElement(imgLogo);
	
			tablaPie.addCell(celdaLogo);
		        
		            document.add(tablaPie);
		}
		public String dameFecha(Date fecha) {
			String dia= String.valueOf(fecha.getDay());
			 String mes= String.valueOf(fecha.getMonth());
			 String year=fecha.toLocaleString().substring(6, 10);
			 System.out.println("kjhgh"+fecha.toLocaleString());
			 switch(mes) {
			 case "0":mes="Enero "; break;
			 case "1":mes="Febrero "; break;
			 case "2":mes="Marzo "; break;
			 case "3":mes="Abril "; break;
			 case "4":mes="Mayo "; break;
			 case "5":mes="Junio "; break;
			 case "6":mes="Julio "; break;
			 case "7":mes="Agosto "; break;
			 case "8":mes="Septiembre "; break;
			 case "9":mes="Octubre "; break;
			 case "10":mes="Noviembre"; break;
			 case "11":mes="Diciembre "; break;
			 
			 }
			 String newfecha= dia+" de "+mes+"de "+year;
			return newfecha;
					 
		}
		
			private void agregarCeldaSinBorde(String contenidoCelda, Font fuente, PdfPTable tabla, boolean centrado) {
			PdfPCell celda = new PdfPCell(new Paragraph(contenidoCelda, fuente));
			celda.setBorder(PdfPCell.NO_BORDER);
			//celda.setBorderColor(BaseColor.GRAY);
			celda.setPadding(8);
	
			if (centrado) {
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			}
			tabla.addCell(celda);
			}
			
			private void agregarCeldaConBorde(String contenidoCelda, Font fuente, PdfPTable tabla, boolean centrado) {
			PdfPCell celda = new PdfPCell(new Paragraph(contenidoCelda, fuente));
			celda.setBorderWidth(0.25f);
			celda.setBorderColor(dgsColor);
			//celda.setRowspan(5);
			celda.setMinimumHeight(48); 
			celda.setPadding(5);
	
			if (centrado) {
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			}
			tabla.addCell(celda);
		}
		
		private void agregarCeldaConFondo(String contenidoCelda, Font fuente, PdfPTable tabla, boolean centrado, int col, int ren) {
			PdfPCell celda = new PdfPCell(new Paragraph(contenidoCelda, fuente));
			celda.setBorderWidth(0.25f);
			fuente.setColor(BaseColor.WHITE);
		//	celda.setBorderColor(new BaseColor(246,123,29)); naranja
			celda.setBorderColor(BaseColor.WHITE);//gris
			celda.setPadding(5);
			 celda.setColspan(col);
			 
			 celda.setRowspan(ren);
			 celda.setVerticalAlignment(Element.ALIGN_CENTER);
		//	tikalColor = new CustomColor(ExtendedColor.TYPE_RGB, 143F / 255F, 135F / 255F, 56F / 255F);
		//	celda.setBackgroundColor(new BaseColor(246,123,29)); naranja
			celda.setBackgroundColor(dgsColor);  //gris
			if (centrado) {
				celda.setHorizontalAlignment(Element.ALIGN_CENTER);
			}
			tabla.addCell(celda);
		}
	}

