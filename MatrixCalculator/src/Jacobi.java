/**
 * Created by bruckw on 11/17/15.
 */
public class Jacobi {
    private static final int MAX_ITER = 100;
    private static int iterations;

    public static Vector jacobi_iter(Matrix a, Vector y, Vector x, double tolerance) {
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
                throw new RuntimeException("The system doesn't converge after 100 iterations.");
            }

            for (int i = 0; i < oldXVec.getVectorRows(); i++) {
                oldXVec.setVectorEntry(i, xVec.getVectorEntry(i));
            }

            for (int i = 0; i < a.getMatrixRows(); i++) {
                total = y.getVectorEntry(i);
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
}
