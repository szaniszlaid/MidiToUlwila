package hu.szaniszlaid.ulwila.view;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;

public class ExportHelper {

    public static void exportComponents(List<? extends JComponent> components) {

        try (XWPFDocument doc = new XWPFDocument(); FileOutputStream out = new FileOutputStream("images.docx")) {

           
            
           XWPFTable table =  doc.createTable(2, 8);
           table.setInsideHBorder(XWPFBorderType.NIL, 0, 0, null);
           table.setInsideVBorder(XWPFBorderType.NIL, 0, 0, null);
           table.getCTTbl().getTblPr().unsetTblBorders();
            
           for(int i = 0; i<components.size(); i++) {
                JComponent component = components.get(i);
                BufferedImage bi = getImage(component);                

                ByteArrayOutputStream os = new ByteArrayOutputStream();

                ImageIO.write(bi, "png", os);

                InputStream is = new ByteArrayInputStream(os.toByteArray());
                
                XWPFParagraph p = table.getRow(0).getCell(i).getParagraphs().get(0);
                p.setAlignment(ParagraphAlignment.CENTER);
                XWPFRun r = p.createRun();

                r.addPicture(is, XWPFDocument.PICTURE_TYPE_PNG, "id", Units.toEMU(component.getWidth() / 2), Units.toEMU(component.getHeight() / 2));   

                table.getRow(1).getCell(i).setText("Egy kicsit hosszabb szöveg: " + i);
                table.getRow(1).getCell(i).getParagraphs().get(0).setAlignment(ParagraphAlignment.CENTER);
                
            }

            doc.write(out);
        } catch (IOException | InvalidFormatException e) {
            // TODO handling, logging
            e.printStackTrace();
        }

    }

    private static BufferedImage getImage(Component c) {
        BufferedImage bi = null;
        try {
            bi = new BufferedImage(c.getWidth(), c.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bi.createGraphics();
            c.print(g2d);
            g2d.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bi;
    }

}
