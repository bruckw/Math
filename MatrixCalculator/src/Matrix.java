/**
 * Created by Lily on 11/15/2015.
 */
public class Matrix {
    double[][] matrix;

    /**
     * Constructs a matrix from the specified 2D array
     * @param matrix The matrix in 2D array form
     */
    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Constructs matrix with specified number of rows/columns
     * Sets all the entries to 0
     * @param rows Number of rows
     * @param columns Number of columns
     */
    public Matrix(int rows, int columns) {
        this.matrix = new double[rows][columns];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }
    /**
     * Retrieves an element in the matrix
     * @param row The row of the element
     * @param column The column of the element
     * @return The element in the specified row and column
     * @throws IndexOutOfBoundsException if row and column
     * 		   are not valid in the matrix
     */
    public double getMatrixEntry(int row, int column) {
        if (row < 0 || column < 0  || row >= getMatrixRows() || column >= getMatrixColumns()) {
            throw new IndexOutOfBoundsException();
        }
        return matrix[row][column];
    }
    /**
     * Gets the number of rows in the matrix
     * @return The number of rows
     */
    public int getMatrixRows() {
        return matrix.length;
    }

    /**
     * Gets the number of columns in the matrix
     * @return The number of columns
     */
    public int getMatrixColumns() {
        return matrix[0].length;
    }

    /**
     * Returns an array of the elements in the specified row.
     * @param row The desired row of elements
     * @return An array with the data from the row
     * @throws IndexOutOfBoundsException if row and column are not valid in the matrix
     */
    public double[] getMatrixRow(int row) {
        if (row < 0 || row >= getMatrixRows()) {
            throw new IndexOutOfBoundsException();
        }
        return matrix[row];
    }
    /**
     * Sets an element in the matrix
     * @param row The row of the element
     * @param column The column of the element
     * @param value The value to be set
     * @throws IndexOutOfBoundsException if row and column
     * 		   are not valid in the matrix
     */
    public void setMatrixEntry(int row, int column, double value) {
        if (row < 0 || column < 0  || row >= getMatrixRows() || column >= getMatrixColumns()) {
            throw new IndexOutOfBoundsException();
        }
        matrix[row][column] = value;
    }
}
