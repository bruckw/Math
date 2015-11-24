import java.util.Random;
import java.io.*;

/**
 * Created by bruckw on 11/23/15.
 */
public class jacobi_gauss_graph {
    //method to generate 100 x's
    public static Vector generateXk() {
        Random rand = new Random();
        double min = -1;
        double max = 1;
        int i = 0;
        Vector vec = new Vector(3);
        for(int j = 0; j < 3; j++) {
            vec.setVectorEntry(j,min + (max - min) * rand.nextDouble());
        }
        return vec;
    }

    public static void writeGraphs(int iterations) {
        int count = 0;
        double tol = .00005;
        File file = new File("Jacobi_Gauss_Data.txt");

        double [][] matrix = new double[][]{
                {1, .5, .33},
                {.5, 1, .5},
                {.33, .25, 1}
        };
        // the matrix we will be working with
        Matrix A = new Matrix(matrix);

        // the b vector
        double[] vecb = {0.1,0.1,0.1};
        Vector b = new Vector(vecb);

        // the exact solution
        double[] vecE = {(double)9/190, (double)28/475, (double) 33/475};
        Vector exact = new Vector(vecE);

        //approximation for Jacobi
        Vector approxJ = new Vector(3);
        //approximation fo gauss
        Vector approxG = new Vector(3);
        while (count < iterations) {
            Vector xk = generateXk();
            System.out.print("Xk: ");
            System.out.println(xk);
            //solution using Jacobi
            System.out.println("Jacobi Solution: ");
            Vector JacobiXn = Jacobi.jacobi_iter(A, xk, b, .00005, 100);
            approxJ = MatrixCalculator.add(approxJ, JacobiXn);
            System.out.println(JacobiXn);
            System.out.println("");
            System.out.println("gauss_seidel Solution: ");
            Vector gaussXn = gauss_seidel.gauss_seidel(A, xk, b, .00005, 100);
            System.out.println(gaussXn);
            approxG = MatrixCalculator.add(approxG, gaussXn);
            System.out.println("");
            try {
                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bw = new BufferedWriter(fw);
                //x-axis: ||Xo-Xexact||
                bw.write(Vector.computeError(xk, exact) + "\n");
                //gauss iterations
                bw.write(gauss_seidel.getIterations()  + "\n");
                //jacobi iterations
                bw.write(Jacobi.getIterations() + "\n");
                bw.close();
            } catch (IOException e) {
                System.out.println("Could not write.");
            }
            count++;
        }
        //approximate solution from adding up all of the Xn's
        MatrixCalculator.divide(approxJ, 100.0);
        System.out.print("Approximate Solution using Jacobi: ");
        System.out.println(approxJ);
        System.out.println("");
        System.out.print("Approximate Solution using Gauss: ");
        MatrixCalculator.divide(approxG, 100.0);
        System.out.println(approxG);

    }

    public static void main(String[] args) {
        writeGraphs(100);
    }
}
