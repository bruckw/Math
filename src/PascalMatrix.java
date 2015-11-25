/**
 * Pascal Matrix class generates a symmetric Pascal Matrix.
 *
 * @author Lily Lau
 * @version 1.0
 */
public class PascalMatrix {
    public static void main(String[] args) {//Main method for testing.
        PascalMatrix p = new PascalMatrix(5);
        Matrix a = p.getMatrix();
        Vector b = p.getVector();
        System.out.println(a);
        System.out.println(b);
    }
    Matrix a;
    Vector b;
    public PascalMatrix(int n) {
        this.a = generatePascalMatrix(n);
        this.b = generatePascalVector(n);
    }
    public Matrix getMatrix() {
        return this.a;
    }
    public Vector getVector() {
        return this.b;
    }
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

    /**
     * Generates a corresponding vector for Pascal's Matrix
     * @param n Number of rows in the vector
     * @return Pascal's Vector
     */
    public static Vector generatePascalVector(int n) {
        double[] arr = new double[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0 / (i + 1);
        }
        return new Vector(arr);
    }
}
