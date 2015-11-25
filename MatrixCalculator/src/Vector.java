import java.util.Arrays;

/**
 * Created by Lily on 11/15/2015.
 */
public class Vector {
    double[] vector;

    /**
     * Constructs a vector with the double array's data
     * @param vector An array of data for the vector
     */
    public Vector(double[] vector) {
        this.vector = vector;
    }

    /**
     * Constructs a vector with specified number of rows
     * Sets each entry to 0
     * @param rows Number of rows
     */
    public Vector(int rows) {
        this.vector = new double[rows];
        for (int i = 0; i < rows; i++) {
            vector[i] = 0;
        }
    }

    /**
     * Gets the number of entries in the vector
     * @return The number of rows
     */
    public int getVectorRows() {
        return vector.length;
    }

    /**
     * Retrieves an element in the vector
     * @param row The row of the element
     * @return The element in the specified row
     * @throws IndexOutOfBoundsException if row is not valid in the vector
     */
    public double getVectorEntry(int row) {
        if (row < 0 || row >= getVectorRows()) {
            throw new IndexOutOfBoundsException();
        }
        return vector[row];
    }

    /**
     * Sets an element in the vector
     * @param row The row of the element
     * @param value The value to be set
     * @throws IndexOutOfBoundsException if row is not valid in the vector
     */
    public void setVectorEntry(int row, double value) {
        if (row < 0 || row >= getVectorRows()) {
            throw new IndexOutOfBoundsException();
        }
        vector[row] = value;
    }

    /**
     * Returns the frobenius norm of the vector
     * This norm is the square root of sum of squares of the elements in the vector.
     * @return The norm of the matrix
     */
    public double normF() {
        double sum = 0;
        for (int i = 0; i < getVectorRows(); i++) {
            sum += Math.pow(getVectorEntry(i), 2);
        }
        return Math.sqrt(sum);
    }

    /**
     * The number of elements in the vector
     * @return The number of elements
     */
    public int numberOfElements() {
        return getVectorRows();
    }

    /**
     * Returns the transpose of the given vector.
     * @return The transpose of the original vector
     */
    public Matrix transpose() {
        Matrix transpose = new Matrix(1, numberOfElements());
        for (int column = 0; column < getVectorRows(); column++) {
            transpose.setMatrixEntry(0, column, getVectorEntry(column));
        }
        return transpose;
    }

    public static double normINfinity(Vector a) {
        double[] answer = new double[a.getVectorRows()];
        for(int i = 0; i < answer.length; i++) {
            for(int j = 0; j <answer.length; j++) {
                answer[i] += Math.abs(a.getVectorEntry(i));
            }
        }
        double ans = answer[0];
        for(int i = 1; i < answer.length; i++) {
            if(answer[i] > ans) {
                ans = answer[i];
            }
        }
        return ans;
    }




    public static double computeError(Vector a, Vector b) {
        Vector x = MatrixCalculator.subtract(a,b);
        return x.normF();
    }



    @Override
    public String toString() {
        return Arrays.toString(vector);
    }


}
