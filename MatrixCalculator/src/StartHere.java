import java.io.*;
import java.util.Scanner;

/**
 * Controller class to allow users to select which operation
 * they want to use.
 *
 * @author Lily Lau
 * @version 1.0
 */
public class StartHere {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("----------------------------------");
        System.out.println("Welcome to the Matrix Calculator!");
        System.out.println("----------------------------------");
        System.out.println("(0) Exit");
        System.out.println("(1) LU Factorization");
        System.out.println("(2) QR Factorization");
        System.out.println("(3) Jacobi's Method");
        System.out.println("(4) Gauss Seidel's Method");
        System.out.println("(5) Power Method");
        System.out.println("");
        System.out.print("Enter the number corresponding to an operation: ");
        String input = scan.next();

        if (input.equals("1")) {
            System.out.println("");
            System.out.println("-----------------------------");
            System.out.println("You chose LU Factorization!");
            System.out.println("-----------------------------");
            System.out.println("(1) Enter your own matrix");
            System.out.println("(2) Enter a filename to read from");
            System.out.println("(3) Generate a Pascal Matrix");
            System.out.println("");
            System.out.println("Pick a method of input: ");
        } else if (input.equals("2")) {
            System.out.println("");
            System.out.println("-----------------------------");
            System.out.println("You chose QR Factorization!");
            System.out.println("-----------------------------");
            System.out.println("(1) Enter your own matrix");
            System.out.println("(2) Enter a filename to read from");
            System.out.println("(3) Generate a Pascal Matrix");
            System.out.println("");
        } else if (input.equals("3")) {
            System.out.println("");
            System.out.println("-----------------------------");
            System.out.println("You chose Jacobi's Method!");
            System.out.println("-----------------------------");
        } else if (input.equals("4")) {
            System.out.println("");
            System.out.println("-----------------------------");
            System.out.println("You chose Gauss' Method!");
            System.out.println("-----------------------------");
        } else if (input.equals("5")) {
            System.out.println("");
            System.out.println("-----------------------------");
            System.out.println("You chose the Power Method!");
            System.out.println("-----------------------------");
        } else {
            String fileName = "matrix.dat";
            String line;
            try {
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
                bufferedReader.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to open file '" + fileName + "'");
            } catch (IOException ex) {
                System.out.println("Error reading file '" + fileName + "'");
            }
        }
    }
}
