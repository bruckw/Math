import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Created by bruckw on 11/17/15.
 */
public class Jacobi {



    private static int iterations;

    public static Vector jacobi_iter(Matrix a, Vector x, Vector b, double tolerance, int max) {
        final int MAX_ITER = max;
        iterations = 0;
        double diff = tolerance + 1;
        Vector xVec = new Vector(x.getVectorRows());
        Vector oldXVec = new Vector(x.getVectorRows());
        double total;

        for (int i = 0; i < oldXVec.getVectorRows(); i++) {
            oldXVec.setVectorEntry(i, x.getVectorEntry(i));
        }

        while(!(diff < tolerance)) {
            iterations++;

            if (iterations > MAX_ITER) {
                throw new RuntimeException("The system doesn't converge after " + max + " iterations.");
            }

            for (int i = 0; i < oldXVec.getVectorRows(); i++) {
                oldXVec.setVectorEntry(i, xVec.getVectorEntry(i));
            }

            for (int i = 0; i < a.getMatrixRows(); i++) {
                total = b.getVectorEntry(i);
                for (int j = 0; j < a.getMatrixColumns(); j++) {
                    if (i != j) {

                        total -= a.getMatrixEntry(i, j) * oldXVec.getVectorEntry(j);
                    }
                }
                total = total / a.getMatrixEntry(i, i);

                xVec.setVectorEntry(i, total);
            }

            if (iterations > 1) {
                diff = xVec.getVectorEntry(0) - oldXVec.getVectorEntry(0);
                diff = Math.abs(diff);
            }

        }

        System.out.println(iterations + " iterations made.");
        return xVec;
    }


    public static int getIterations() {
        return iterations;
    }




    public static void main(String[] args) {
        double [][] matrix = new double[][]{
                {1, .5, .33},
                {.5, 1, .5},
                {.33, .25, 1}
        };


        Matrix a = new Matrix(matrix);
        System.out.println("Matrix A: ");
        System.out.println(a);


        a.setMatrixEntry(0,0,1);
        a.setMatrixEntry(0,1,.5);
        a.setMatrixEntry(0,2,(double)1/3);
        a.setMatrixEntry(1,0,.5);
        a.setMatrixEntry(1,1,1);
        a.setMatrixEntry(1,2,.25);
        a.setMatrixEntry(2,0,(double)1/3);
        a.setMatrixEntry(2,1,.25);
        a.setMatrixEntry(2,2,1);


        double[] vecb = {0.1,0.1,0.1};
        Vector b = new Vector(vecb);
        double[] vecx = {-0.11322206013515546, -0.5964437991210172, -0.15884922313274652};
        Vector x = new Vector(vecx);
        Vector toReturn = jacobi_iter(a, x, b, .00005, 100);
        System.out.println(toReturn.toString());


        double[] vecc = {5,4,3};
        Vector c = new Vector(vecc);
        System.out.println(c);
        MatrixCalculator.divide(c, 100.0);
        System.out.println(c);
        double[] vecd = {2, 3, 4};
        Vector d = new Vector(vecd);
    }

}
