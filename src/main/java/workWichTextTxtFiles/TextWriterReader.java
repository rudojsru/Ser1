package workWichTextTxtFiles;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TextWriterReader {


    public List listModifiPhone() {

        String track= trackToFiles(); // Stiezka do faila gdzie liezy program

        final String TEXT = track+"Text.txt";
        final String TEXTmodified = track+"modifiedText.txt";

        BufferedReader br;
        List<String> lines = new LinkedList<>();
        String line = null;

        try {
            br = new BufferedReader(new FileReader(TEXT));

            while ((line = br.readLine()) != null) {

                if (!line.equals("") ) {  // что бы небыло скопировано пустой строчки
                    line=newPhoneNumber(line);  // obrobka numeru telefona
                    lines.add(line);
                }
            }
            br.close();
       //     System.out.println("input file: "+lines);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(TEXTmodified);
            BufferedWriter bw = new BufferedWriter(fileWriter);
             for (int i =0; i < lines.size(); i++) {
                String myString=lines.get(i);
                char[] myCharArray =myString.toCharArray();

                bw.write(lines.get(i) + "\r\n");

            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return lines;
    }

    public static String trackToFiles() {
        String track=new File(".").getAbsolutePath();
       // System.out.println(track);

        String [] splitTrack =track.split("");
     //   System.out.println(splitTrack.length);
        track="";


        for (int i=0; i<splitTrack.length-1; i++){
            track+=splitTrack[i];

        }


        System.out.println(track);
        return track;

    }

    private static String newPhoneNumber(String line) {
        String [] ifNumver=line.split("");
        String backLine=null;
        List<String> arrayList = new ArrayList<>();
        if(ifNumver[0].equals("+"))  {

            arrayList.addAll(Arrays.asList(line.split("")).subList(2,line.length()));
            arrayList.set(0,"0");
            arrayList.add(arrayList.size(),";");
            backLine=arrayList.toString().replaceAll("[,\\s\\[\\],[?]]", "");
        }else
        if (((ifNumver[0].equals("0"))&&(ifNumver[1].equals("0")))){
            arrayList.addAll(Arrays.asList(line.split("")).subList(3,line.length()));
            arrayList.set(0,"0");
            arrayList.add(arrayList.size(),";");
            backLine=arrayList.toString().replaceAll("[,\\s\\[\\],[?]]", "");
        }
         else {
            backLine=line;
        }
        return backLine;
    }
}
//Iloveyou