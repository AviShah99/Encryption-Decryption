package encryptdecrypt;
import com.sun.nio.sctp.AbstractNotificationHandler;
 
import javax.swing.*;
import java.util.*;
import java.io.*;
public class Encryption_Decryption {
    public static void main(String[] args) throws IOException,FileNotFoundException {
        int i;
        String op = "";
        int key = 0;
        String str = "";
        int flag = 0;
 
        String opFile = "";
        String alg = "shift";
 
 
        for (i = 0; i < args.length; i++) {
            if (args[i].equals("-mode")) {
                op = args[i + 1];
            } else if (args[i].equals("-key")) {
                key = Integer.parseInt(args[i + 1]);
            } else if (args[i].equals("-data")) {
                str = str + args[i + 1];
 
   } else if (args[i].equals("-in")) {
                File input = new File(args[i + 1]);
                Scanner sc = new Scanner(input);
                while (sc.hasNext()) {
                    str = str + sc.nextLine();
                }
 
                sc.close();
 
            } else if (args[i].equals("-out")) {
                opFile = args[i + 1];
 
                flag = 1;
            } else if (args[i].equals("-alg")) {
                alg = args[i + 1];
            }
 
 
        }
		
		 if (alg.equals("unicode")) {
            File out = new File(opFile);
            if (op.equals("")) {
                op = "enc";
            }
 
            char ch[] = str.toCharArray();
 
 
            if (op.equals("enc")) {
                ch = encrypt(ch, key);
                if (flag == 0) {
                    for (i = 0; i < ch.length; i++) {
                        System.out.print(ch[i]);
                    }
                } else if (flag == 1) {
                    FileWriter writer = new FileWriter(out);
 
                    for (i = 0; i < ch.length; i++) {
 
                        writer.write(ch[i]);
 
                    }
                    writer.close();
 
                }
            } else if (op.equals("dec")) {
                ch = decrypt(ch, key);
                if (flag == 0) {
                    for (i = 0; i < ch.length; i++) {
                        System.out.print(ch[i]);
                    }
 
                } else {
                    FileWriter writer = new FileWriter(out);
 
                    for (i = 0; i < ch.length; i++) {
                        writer.write(ch[i]);
                    }
                    writer.close();
 
 
                }
            }
        } 
		else
		{
 
            char ch[] = str.toCharArray();
 
            String alpha = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
            if(op.equals("enc"))
            {
                for (i = 0; i < ch.length; i++) {
                    if (Character.isLetter(ch[i])) {
                        ch[i] = alpha.charAt(alpha.indexOf(str.charAt(i)) + key);
                    }
                }
            }else{
                for (i = 0; i < ch.length; i++) {
                    if (Character.isLetter(ch[i])) {
                        ch[i] = alpha.charAt(alpha.lastIndexOf(str.charAt(i)) - key);
                    }
            }
 
            }
            if(flag==1)
            {
                File file=new File(opFile);
                FileWriter writer=new FileWriter(file);
                for(i=0;i<ch.length;i++)
                {
                    writer.write(ch[i]);
                }
                writer.close();
            }
            else {
                for (i = 0; i < ch.length; i++) {
                    System.out.print(ch[i]);
                }
            }
        }
    }
 
 
    public static char[] encrypt(char ch1[], int k) {
        int i;
        for (i = 0; i < ch1.length; i++) {
            ch1[i] = (char) ((int) ch1[i] + k);
        }
        return ch1;
 
    }
 
    public static char[] decrypt(char ch1[], int k) {
        int i;
        for (i = 0; i < ch1.length; i++) {
            ch1[i] = (char) ((int) ch1[i] - k);
        }
        return ch1;
    }
}