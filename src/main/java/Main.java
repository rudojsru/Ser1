import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import workWichTextTxtFiles.TextWriterReader;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        TextWriterReader text = new TextWriterReader();
        File track = new File(text.trackToFiles() + "ex3.xls");
        File track2 = new File(text.trackToFiles() + "copiEx3.xls");
        Main.copyFileUsingStream(track, track2); // - creirt copi of files


        FileInputStream fis = new FileInputStream(track2);
        Workbook wb = new HSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(0);
        int numerCell = sheet.getRow(0).getLastCellNum();
        Row row = sheet.getRow(0);

        TextWriterReader textWriterReader = new TextWriterReader();
        List l = textWriterReader.listModifiPhone(); // List from TextWriter   просто вытягивает список телефонов из текстового файла
        System.out.println(l);
        for (int i = 0; i < l.size(); i++) {     // List size переберает каждый элемент из листа (текстового файла)
            String listWordText = (String) l.get(i);
            //       System.out.println(listWordText+"-------------------");
            String numberOrTextFromList[] = listWordText.split("");
            if ((numberOrTextFromList[0].equals("0") != true) && (numberOrTextFromList[1].equals("7") != true)) { // если это не цифра то ищем столбец в экселе таблице
                for (int c = 0; c < numerCell; c++) {   // потом в С поставить 1   с=1
                    String nameList = String.valueOf(sheet.getRow(0).getCell(c));
                    if (nameList.equals(listWordText)) {
                        int r = 0;
                        String s="";
                        while ((sheet.getRow(r).getCell(c) != null)
                                ||(sheet.getRow(r)!=null)) {
                             s= String.valueOf(sheet.getRow(r).getCell(c));
                             System.out.println(s);

                            r++;
                            i++;
                        }
                        i--;
                        System.out.println(r + " - количество рядов");
                      break;
                    }
                }
            }
        }
    }


    private static void copyFileUsingStream(File source, File dest) throws IOException {   // создает копию файла


        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}
