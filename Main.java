//package encryptdecrypt;
import java.util.Scanner;
import java.io.*;
public class Main {

    public static void main(String[] args) {
                /*Scanner scanner = new Scanner(System.in);
                String dORe = scanner.nextLine();
                String str = scanner.nextLine();
                int number = scanner.nextInt();*/
        String mode="enc";
        int key= 0;
        String data="";
        String filePathIn="";
        String filePathOut="";
        boolean writeToFile=false;
        String dataIn="";
        String alg="shift";

        for (int i = 0; args.length > i; i++){
            if (args[i].equals("-mode")){
                mode=args[i+1];
            }
            if (args[i].equals("-key")){
                key=Integer.parseInt(args[i+1]);
            }
            if (args[i].equals("-data")){
                data=args[i+1];
            }
            if (args[i].equals("-alg")) {
                alg = args[i + 1];
            }

            if (args[i].equals("-in")){
                filePathIn=args[i+1];
                File file = new File(filePathIn);

                try (Scanner scanner = new Scanner(file)) {
                    dataIn=scanner.nextLine();
                } catch (FileNotFoundException e) {
                    System.out.println("Error. No file found: " + filePathIn);
                }

            }
            if (args[i].equals("-out")){
                filePathOut=args[i+1];
                writeToFile=true;
            }

        }
        if ("".equals(data)&&!dataIn.equals("")){
            data=dataIn;
        }
        if (mode.equals("enc")) {

            if  (!writeToFile)
            {
                System.out.println(encript(data,key, alg));
            }
            else{
                File file = new File(filePathOut);
                try (FileWriter writer = new FileWriter(file, false)) {
                    writer.write(encript(data,key,alg));

                } catch (IOException e) {
                    System.out.println("Error. An exception occurs ");
                }
            }

        }
        else {
            if  (!writeToFile){
                System.out.println(decript(data, key,alg));
            } else {
                File file = new File(filePathOut);
                try (FileWriter writer = new FileWriter(file, false)) {
                    writer.write(decript(data,key, alg));

                } catch (IOException e) {
                    System.out.println("Error. An exception occurs ");
                }
            }


        }


    }

    //funcs
    public static String encript(String estr, int enumber, String alg) {
        String newStr="";
        int i=0;
        if (alg.equals("unicode")) {
            while (i < estr.length()) {
                int ch = estr.charAt(i) + enumber;
                newStr += Character.toString(ch);
                i++;
            }
        } else {
            char a = 'a';
            char z = 'z';
            char A = 'A';
            char Z = 'Z';
            int size = 26;
            char[] chars = estr.toCharArray();

            for (char item : chars) {
                if ((item >= a && item <= z)||(item >= A && item <= Z)) {
                    char shiftItem = (char) (((item - a + enumber) % size) + a);
                    System.out.print(shiftItem);
                } else {
                    newStr+=item;
                }
            }

        }

        return newStr;
    }

    public static String decript(String dstr, int enumber, String alg) {
        String newStr="";
        int i=0;
        if (alg.equals("unicode")) {
            while (i < dstr.length()) {
                newStr += Character.toString(dstr.charAt(i) - enumber);
                i++;
            }
        }
        else {
            char a = 'a';
            char z = 'z';
            char A = 'A';
            char Z = 'Z';
            int size = 26;
            char[] chars = dstr.toCharArray();

            for (char item : chars) {
                if ((item >= a && item <= z)||(item >= A && item <= Z)) {
                    char shiftItem = (char) (((item - a + enumber) % size) + a);
                    System.out.print(shiftItem);
                } else {
                    newStr+=item;
                }
            }

        }
        return newStr;
    }

    public  static String readFile(String pathToFile){
        File file = new File(pathToFile);
        String rf="";

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                rf=  scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + pathToFile);
        }
        return rf;
    }

    public  static String writeFile(String pathToFile){
        File file = new File(pathToFile);
        String rf="";

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                rf=  scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + pathToFile);
        }
        return rf;
    }

}
