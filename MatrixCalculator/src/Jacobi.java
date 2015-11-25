/**
 * Created by bruckw on 11/17/15.
 */
public class Jacobi {



    private static int iterations=0;

    public static Vector jacobi_iter(Vector x, double tolerance, int max) {
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
        double difference = tolerance + 1;
        Vector xVector = new Vector(x.getVectorRows());
        Vector oldXVector = new Vector(x.getVectorRows());
        double sum;

        for (int i = 0; i < oldXVector.getVectorRows(); i++) {
            oldXVector.setVectorEntry(i, x.getVectorEntry(i));
        }

        while(!(difference < tolerance)) {

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
                        sum -= a.getMatrixEntry(i, j) * oldXVector.getVectorEntry(j);
                    }
                }
                sum = sum / a.getMatrixEntry(i, i);

                xVector.setVectorEntry(i, sum);
            }

            if (iterations > 1) {
                difference = xVector.getVectorEntry(0) - oldXVector.getVectorEntry(0);
                difference = Math.abs(difference);
            }

        }

        System.out.println(iterations + " iterations made.");
        return xVector;
    }


    public static int getIterations() {
        return iterations;
    }




    public static void main(String[] args) {
        double[] vecx = {0,0,0};
        Vector x = new Vector(vecx);


        Vector toReturn = jacobi_iter(x, .00005, 100);
        System.out.println(toReturn.toString());
    }
}





