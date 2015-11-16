/**
 * Created by Lily on 11/15/2015.
 */
public class MatrixCalculator {

    /**
     * Approximates largest eigenvalue/corresponding eigenvector using the power method
     * @param a An nxn matrix
     * @param v Initial approximation vector
     * @param tol Tolerance error
     * @param n Max number of iterations
     * @return Returns a PowerObject that holds resulting eigenvalue, eigenvector, and iterations
     */
    public static PowerObject power_method(Matrix a, Vector v, double tol, int n) {
        return PowerMethod.power_method(a, v, tol, n);
    }
    /**
     * Multiplies a matrix by a vector
     *
     * @param a The matrix
     * @param x The vector
     * @return The vector containing the product
     * @throws IllegalArgumentException if the given matrix's and vector's dimensions cannot be multiplied
     */
    public static Vector multiply(Matrix a, Vector x) {
        if (a.getMatrixColumns() != x.getVectorRows()) {
            throw new IllegalArgumentException("A's columns must be the same as B's rows");
        }
        Vector product = new Vector(a.getMatrixRows());
        double sum;
        double[] row;
        for (int rowA = 0; rowA < a.getMatrixRows(); rowA++) {
            sum = 0;
            row = a.getMatrixRow(rowA);
            for (int i = 0; i < row.length; i++) {
                sum += row[i] * x.getVectorEntry(i);
            }
            product.setVectorEntry(rowA, sum);
        }
        return product;
    }

    /**
     * Multiplies a vector by a scalar
     * @param a The vector
     * @param scalar The constant to multiply by
     * @return The vector containing the product
     */
    public static Vector multiply(Vector a, double scalar) {
        Vector product = new Vector(a.getVectorRows());
        for (int i = 0; i < a.getVectorRows(); i++) {
            product.setVectorEntry(i, scalar * a.getVectorEntry(i));
        }
        return product;
    }
}
