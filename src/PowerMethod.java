import java.util.Random;

/**
 * Created by Lily on 11/15/2015.
 */
public class PowerMethod {
    /**
     * Gets the absolute largest value in a vector
     * @param v vector
     * @return The absolute largest entry in vector
     */
    public static double getLargest(Vector v) {
        double largest;
        largest = v.getVectorEntry(0);
        for (int i = 1; i < v.getVectorRows(); i++) {
            if (Math.abs(v.getVectorEntry(i)) - Math.abs(largest) > 0) {
                largest = v.getVectorEntry(i);
            }
        }
        return largest;
    }

    /**
     * Find approximation of largest eigenvalue/eigenvector
     * @param a The nxn matrix
     * @param tol The error tolerance
     * @param v An initial approximation vector
     * @return The approximated eigenvalue, eigenvector, and number of iterations for desired tolerance
     */
    public static PowerObject power_method(Matrix a, Vector v, double tol, int n) {
        // Largest value in the initial approx vector
        double previous = getLargest(v);
        v = MatrixCalculator.multiply(v, 1 / getLargest(v)); // Scale v
        PowerObject powObj = new PowerObject(getLargest(v), v);
        // First iteration - necessary for recursive method
        Vector x1 = MatrixCalculator.multiply(a, v); // Multiply matrix A and vector v
        v = MatrixCalculator.multiply(x1, 1 / getLargest(x1)); // Scale x1
        powObj.setEigenvalue(getLargest(x1));
        powObj.setEigenvector(v);
        powObj.incrementIterations();
        powerHelper(powObj, a, v, tol, previous, n);
        return powObj;
    }

    /**
     * Recursively calculates new eigenvalue/eigenvector
     * @param a The nxn matrix
     * @param tol The error tolerance
     * @param v An initial approximation vector
     * @param prev The previous eigenvalue
     * @param powObj The PowerObject containing return data
     * @return The approximated eigenvalue, eigenvector, and number of iterations for desired tolerance
     */
    private static void powerHelper(PowerObject powObj, Matrix a, Vector v, double tol, double prev, int n) {
        double thisPrevious = powObj.getEigenvalue();
        // If we've reached N iterations, exit recursion
        if (powObj.getIterations() >= n) {
            powObj.setConverges(false);
        // If difference between current largest eigenvalue and previous eigenvalue is greater than tol
        // Call function again
        } else if (Math.abs(Math.abs(thisPrevious) - Math.abs(prev)) - tol > 0) {
            Vector av = MatrixCalculator.multiply(a, v);
            v = MatrixCalculator.multiply(av, 1 / getLargest(av)); // Scale av
            powObj.setEigenvalue(getLargest(av));
            powObj.setEigenvector(v);
            powObj.incrementIterations();
            powerHelper(powObj, a, v, tol, thisPrevious, n);
        }
    }
}
