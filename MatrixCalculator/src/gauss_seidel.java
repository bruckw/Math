/**
 * Created by bruckw on 11/22/15.
 */
public class gauss_seidel {


    /**
     * Method for Gauss-Seidel iteration.
     * @param a Matrix n x n
     * @param y Vector n x 1
     * @param x Initial guess vector
     * @param tol Error tolerance number
     * @return x vector approximation
     * @throws RuntimeException if iterations > MAX_ITER
     */
    private static int iterations = 0;
    public static Vector gauss_seidel(Matrix a, Vector y, Vector x, double tol, int max) {
        final int MAX_ITER = max;
        iterations = 0;
        double difference = tol + 1;
        Vector xVector = new Vector(x.getVectorRows());
        Vector oldXVector = new Vector(x.getVectorRows());
        double sum;

        for (int i = 0; i < oldXVector.getVectorRows(); i++) {
            oldXVector.setVectorEntry(i, x.getVectorEntry(i));
        }

        while(!(difference < tol)) {

            iterations++;

            if (iterations > MAX_ITER) {
                throw new RuntimeException("Doesn't converge after 100 iterations.");
            }

            for (int i = 0; i < oldXVector.getVectorRows(); i++) {
                oldXVector.setVectorEntry(i, xVector.getVectorEntry(i));
            }

            for (int i = 0; i < a.getMatrixRows(); i++) {
                sum = y.getVectorEntry(i);
                for (int j = 0; j < a.getMatrixColumns(); j++) {
                    if (i != j) {
                        sum -= a.getMatrixEntry(i, j) * x.getVectorEntry(j);
                    }
                }
                sum = sum / a.getMatrixEntry(i, i);

                xVector.setVectorEntry(i, sum);
                x.setVectorEntry(i, sum);
            }

            if (iterations > 1) {
                difference = xVector.getVectorEntry(0) - oldXVector.getVectorEntry(0);
                difference = Math.abs(difference);
            }

            for (int i = 0; i < x.getVectorRows(); i++) {
                x.setVectorEntry(i, xVector.getVectorEntry(i));
            }
        }

        System.out.println(iterations + " iterations made.");
        return xVector;
    }

    public static int getIterations() {
        return iterations;
    }





    public static void main(String[] args) {
        double [][] matrix = new double[][]{
                {1, 1/2, 1/3},
                {1/2, 1, 1/4},
                {1/3, 1/4, 1}
        };

        Matrix a = new Matrix(3,3);
        a.setMatrixEntry(0,0,1);
        a.setMatrixEntry(0,1,.5);
        a.setMatrixEntry(0,2,.33);
        a.setMatrixEntry(1,0,.5);
        a.setMatrixEntry(1,1,1);
        a.setMatrixEntry(1,2,.25);
        a.setMatrixEntry(2,0,.33);
        a.setMatrixEntry(2,1,.25);
        a.setMatrixEntry(2,2,1);
        System.out.println("Matrix A: ");
        System.out.println(a.toString());

        double[] vecb = {0.1,0.1,0.1};
        Vector b = new Vector(vecb);
        double[] vecx = {0.386103259640306, 0.12546455399768308, -0.9462979707489672};
        Vector x = new Vector(vecx);
        Vector toReturn = gauss_seidel(a, b, x, .00001, 100);


        System.out.println(toReturn.toString());
        System.out.println(getIterations());


    }

}
