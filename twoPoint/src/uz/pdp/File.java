package uz.pdp;

import java.util.Formatter;
import java.util.Scanner;

public class File {

    public static String Read(){
        String s = "";
        try {
            Scanner scanner = new Scanner(new java.io.File("C:\\Users\\User\\IdeaProjects\\Two Point\\two-point\\twoPoint\\best.txt"));

            while (scanner.hasNextLine()){
                s = s.concat(scanner.nextLine());
            }
        } catch (Exception e){
            System.out.println("File isn't found");
        }
        return s;
    }

    public static void Write(String best) {
        Formatter file;
        java.io.File file2=new java.io.File("C:\\Users\\User\\IdeaProjects\\Two Point\\two-point\\twoPoint\\best.txt");
        if(file2.exists()){
            try {
                file = new Formatter( "C:\\Users\\User\\IdeaProjects\\Two Point\\two-point\\twoPoint\\best.txt");
                file.format(best);
                file.close();
            } catch (Exception e){
                System.out.println("Error...");
            }
        } else System.out.println("File isn't found.");
    }

}
