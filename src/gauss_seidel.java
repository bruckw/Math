/**
 * Solves for vector x in Ax = b
 * Using the Gauss Seidel Iteration method.
 *
 * @author Bruck Woldeyes
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
    public static Vector gs_iter(Vector x, double tol, int max) {
        Matrix a = new Matrix(3,3);
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
                sum = b.getVectorEntry(i);
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
        return xVector;
    }
    public static int getIterations() {
        return iterations;
    }
}
