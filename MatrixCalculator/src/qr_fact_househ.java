public class qr_fact_househ {

    public static void main(String[] args) {//Main method for testing.
//        double[][] matrixA = {
//                { 1, 2, 3 },
//                { 2, -4, 6 },
//                { 3, -9, -3 }
//        };
        Matrix b = PascalMatrix.generatePascalMatrix(4);
        System.out.println(qr_fact_househ(b)[0]);
        System.out.println(qr_fact_househ(b)[1]);
    }

    /**
     * Returns a Matrix array containing the QR factorization
     * done using HouseHolder reflections.
     * Assumes the matrix is factorable.
     * @param matrix The matrix to factor
     * @return matrix array, where Q = array[0] and R = array[1]
     */
    public static Matrix[] qr_fact_househ(Matrix matrix) {
        // Make identity matrix
        Matrix Q = Matrix.identity(matrix.getMatrixRows());
        Matrix R = matrix;
        for (int n = 0; n < matrix.getMatrixColumns() - 1; n++) {
            // Calculates H for this column
            Matrix Hn = calculateH(R, n);

            // If H's dimensions are not that of the original matrix,
            // insert it into the identity matrix so that it is.
            int diff = matrix.getMatrixRows() - Hn.getMatrixRows();
            if (diff != 0) {
                // Create necessary portion of identity matrix
                Matrix temp = new Matrix(matrix.getMatrixRows(), matrix.getMatrixRows());
                for (int i = 0; i < diff; i++) {
                    temp.setMatrixEntry(i, i, 1);
                }
                // Insert Hn into relevant areas
                temp.setMatrix(diff, matrix.getMatrixRows() - 1, diff, matrix.getMatrixRows() - 1, Hn);
                Hn = temp;
            }
            // Q = H1 H2 H3 H4 .... Hm-1
            Q = MatrixCalculator.multiply(Q, Hn.transpose());
            R = MatrixCalculator.multiply(Hn, R);
        }
        // Make R Upper triangular to account for errors
        for (int row = 0; row < R.getMatrixRows(); row++) {
            for (int column = 0; column < R.getMatrixColumns(); column++) {
                if (row > column) {
                    if (Math.abs(R.getMatrixEntry(row, column)) <= Math.pow(10, -15)) {
                        R.setMatrixEntry(row, column, 0);
                    }
                }
            }
        }

        // We can factor out -1 from both Q and R
        for (int i = 0; i < Q.getMatrixRows(); i++) {
            for (int j = 0; j < Q.getMatrixColumns(); j++) {
                Q.setMatrixEntry(i, j, Q.getMatrixEntry(i, j) * -1);
            }
        }

        for (int i = 0; i < R.getMatrixRows(); i++) {
            for (int j = 0; j < R.getMatrixColumns(); j++) {
                R.setMatrixEntry(i, j, R.getMatrixEntry(i, j) * -1);
            }
        }
        Matrix[] A = { Q, R };
        return A;
    }

    /**
     * Calculates H-hat, a submatrix used in householder reflections.
     * @param matrix The matrix that is being factored
     * @param rowStart The row to begin fixing H at.
     * @return The matrix containing H-hat
     */
    private static Matrix calculateH(Matrix matrix, int rowStart) {
        // The column of the matrix including the diagonal and below
        // We want zeros below the diagonal
        Vector a = matrix.getSubVector(rowStart, matrix.getMatrixRows() - 1, rowStart);
        // Get the magnitude of the subvector
        double magnitudeA = a.normF();

        // Create vector e1
        Vector e = new Vector(matrix.getMatrixRows() - rowStart);
        e.setVectorEntry(0, 1);

        // V = a + e1 * ||a1|| where ||a1|| is magnitudeA
        Vector v = MatrixCalculator.add(a, MatrixCalculator.multiply(e, magnitudeA));

        // U = V normalized
        Vector u = MatrixCalculator.multiply(v, 1. / v.normF());

        // Create Identity matrix
        Matrix I = Matrix.identity(matrix.getMatrixRows() - rowStart);

        // H = I - 2uu^t
        Matrix H = MatrixCalculator.subtract(I, MatrixCalculator.multiply(MatrixCalculator.multiply(u, u.transpose()), 2));
        return H;
    }
}