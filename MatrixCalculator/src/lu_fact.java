/**
 * Representation of LU Factorizations of a matrix
 *
 * @author Samuel Isang
 * @version 1.0
 */
public class lu_fact {
    public static void main(String[] args) {//Main method for testing.
//        double[][] matrixA = {
//        { 1, 2, 3 },
//        { 2, -4, 6 },
//        { 3, -9, -3 }
//    };
        double[] vectorA = {1, 1.0/2, 1.0/3, 1.0/4};
        //Matrix b = new Matrix(matrixA);
        Matrix b = PascalMatrix.generatePascalMatrix(4);
        System.out.println(b);
        Vector c = new Vector(vectorA);
        System.out.println("Lower:\n" + lu_fact(b)[0]);
        System.out.println("Upper:\n" + lu_fact(b)[1]);
        Vector v = solve_lu_b.solve_lu_b(lu_fact(b)[0], lu_fact(b)[1], c);
        for (int i = 0; i < v.getVectorRows(); i++) {
            System.out.println(v.getVectorEntry(i));
        }
    }

    /**
     * Calculates the LU factorization of a matrix
     * Algorithm learned from http://rosettacode.org/wiki/LU_decomposition
     * @param a The matrix to be factored
     * @return The matrices L and U
     */
    public static Matrix[] lu_fact(Matrix a) {
        Matrix lowerTriangle = new Matrix(a.getMatrixRows(), a.getMatrixColumns());
        Matrix upperTriangle = new Matrix(a.getMatrixRows(), a.getMatrixColumns());

        for (int i = 0; i < a.getMatrixRows(); i++) {
            for (int j = 0; j < a.getMatrixColumns(); j++) {
                if (i == j) {
                    lowerTriangle.setMatrixEntry(i, j, 1);
                }
            }
        }

        for (int i = 0; i < a.getMatrixColumns(); i++) {
            upperTriangle.setMatrixEntry(0, i, a.getMatrixEntry(0, i));
        }

        for (int i = 1; i < a.getMatrixRows(); i++) {
            double value = a.getMatrixEntry(i, 0) / upperTriangle.getMatrixEntry(0, 0);
            lowerTriangle.setMatrixEntry(i, 0, value);
        }

        for (int i = 1; i < a.getMatrixRows(); i++) {
            for (int j = 1; j < a.getMatrixColumns(); j++) {
                if (i <= j) {
                    //Upper triangle
                    double sum = 0;
                    for (int k = 0; k <= i - 1; k++) {
                        sum += upperTriangle.getMatrixEntry(k, j) * lowerTriangle.getMatrixEntry(i, k);
                    }
                    upperTriangle.setMatrixEntry(i, j, a.getMatrixEntry(i, j) - sum);
                } else {
                    //Lower Triangle
                    double sum = 0;
                    for (int k = 0; k <= j - 1; k++) {
                        sum += upperTriangle.getMatrixEntry(k, j) * lowerTriangle.getMatrixEntry(i, k);
                    }
                    lowerTriangle.setMatrixEntry(i, j, (a.getMatrixEntry(i, j) - sum) / upperTriangle.getMatrixEntry(j, j));
                }
            }
        }
        Matrix[] matrices = {lowerTriangle, upperTriangle};
        return matrices;
    }
    // System.out.println("||LU - A||: " + subtract(multiply(lowerTriangle, upperTriangle), a).norm());
}