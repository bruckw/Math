/**
 * Class that solves Ax = b using LU Factorization
 *
 * @author Lily Lau
 * @version 1.0
 */
public class solve_lu_b {

    /**
     * Uses LU factored matrix to solve for vector x.
     * Assumes there is a solution.
     * @param l The lower triangular matrix
     * @param u The upper triangular matrix
     * @param b The vector b in Ax = b
     * @return The solution vector
     */
    public static Vector solve_lu_b(Matrix l, Matrix u, Vector b) {
        Vector x = new Vector(l.getMatrixColumns());
        Vector y = new Vector(l.getMatrixColumns());
        // Ly = b
        // Solve for y. L is in Lower triangular with ones along diagonal
        // y(n) = (b(n) - sum of products) / l(n) where
        // sum of products = for (i = 0; i < n; i++) sum += l(i) * y(i)
        for (int i = 0; i < y.getVectorRows(); i++) {
            double sumOfProducts = 0;
            for (int j = 0; j < i; j++) {
                sumOfProducts += l.getMatrixEntry(i, j) * y.getVectorEntry(j);
            }
            // double value = (b.get(i) - sumOfProducts).divide(l.get(i, i));
            double value = b.getVectorEntry(i) - sumOfProducts;
            y.setVectorEntry(i, value);
        }
        // Ux = Y
        // Solve for x, U is upper triangular
        for (int i = y.getVectorRows() - 1; i >= 0; i--) {
            double sumOfProducts = 0;
            for (int j = u.getMatrixRows() - 1; j > i; j--) {
                sumOfProducts += u.getMatrixEntry(i, j) * x.getVectorEntry(j);
            }
            double value = (y.getVectorEntry(i) - sumOfProducts) / u.getMatrixEntry(i, i);
            x.setVectorEntry(i, value);
        }
        return x;
    }
}