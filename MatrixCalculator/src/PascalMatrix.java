/**
 * Pascal Matrix class generates a symmetric Pascal Matrix.
 *
 * @author Lily Lau
 * @version 1.0
 */
public class PascalMatrix {
    /**
     * Generates a Symmetric Pascal Matrix
     * @param n Number of rows/columns in the matrix
     * @return A square matrix nxn
     */
    public static Matrix generatePascalMatrix(int n) {
        double sum;
        Matrix a = new Matrix(n, n);
        for (int i = 0; i < a.getMatrixRows(); i++) {
            for (int j = 0; j < a.getMatrixColumns(); j++) {
                if (i == 0 || j == 0) {
                    a.setMatrixEntry(i, j, 1);
                } else {
                    sum = a.getMatrixEntry(i, j - 1) + a.getMatrixEntry(i - 1, j);
                    a.setMatrixEntry(i, j, sum);
                }
            }
        }
        return a;
    }
}
