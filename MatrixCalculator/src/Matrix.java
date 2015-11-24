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
     * Constructs a matrix with the specified vector.
     * @param vector The vector to be represented as a matrix
     */
    public Matrix(Vector vector) {
        this.matrix = new double[vector.getVectorRows()][1];
        for (int i = 0; i < vector.getVectorRows(); i++) {
            matrix[i][0] = vector.getVectorEntry(i);
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
     * Returns the backing array for this matrix
     * @return matrix The backing array for this matrix
     */
    public double[][] getArray() {
        return matrix;
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

    /**
     * Returns a Matrix of the elements in
     * the specified column.
     * @param column The desired column of elements
     * @return An array with the data from the column
     * @throws IndexOutOfBoundsException if row and column
     *         are not valid in the matrix
     */
    public Matrix particularColumn(int column) {
        if (column < 0  || column >= getMatrixColumns()) {
            throw new IndexOutOfBoundsException();
        }
        double[][] desiredColumn = new double[getMatrixRows()][1];
        for (int row = 0; row < getMatrixRows(); row++) {
            desiredColumn[row][0] = matrix[row][column];
        }
        return new Matrix(desiredColumn);
    }

    /**
     * Returns the transpose of this matrix.
     * @return The transpose of the original matrix
     */
    public Matrix transpose() {
        Matrix transpose = new Matrix(getMatrixColumns(), getMatrixRows());
        int newRow = 0;
        int newColumn = 0;
        for (int row = 0; row < getMatrixRows(); row++) {
            newRow = 0;
            for (int column = 0; column < getMatrixColumns(); column++) {
                transpose.setMatrixEntry(newRow, newColumn, getMatrixEntry(row, column));
                newRow++;
            }
            newColumn++;
        }
        return transpose;
    }

    /**
     * Returns an identity matrix with n rows and n columns
     * @param n The number of rows/columns
     * @return The identity matrix
     */
    public static Matrix identity(int n) {
        Matrix identity = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            identity.setMatrixEntry(i, i, 1);
        }
        return identity;
    }

    /**
     * Returns a sub-vector from this matrix
     * @param rowStart The row to start at
     * @param rowEnd The row to end at (inclusive)
     * @param column The column this sub-vector is in
     * @return The sub-vector
     */
    public Vector getSubVector(int rowStart, int rowEnd, int column) {
        Vector subVector = new Vector(rowEnd + 1 - rowStart);
        int subRow = 0;
        for (int i = rowStart; i <= rowEnd; i++) {
            subVector.setVectorEntry(subRow++, getMatrixEntry(i, column));
        }
        return subVector;
    }

    /**
     * Returns the frobenius norm of the matrix
     * This norm is the square root of sum of squares of elements.
     * @return The largest value in the matrix
     */
    public double normF() {
        double sum = 0;
        for (int i = 0; i < getMatrixRows(); i++) {
            for (int j = 0; j < getMatrixColumns(); j++) {
                sum += Math.pow(getMatrixEntry(i, j), 2);
            }
        }
        return Math.sqrt(sum);
    }

    /**
     * Returns the norm of the matrix as defined in the PDF
     * This norm is the largest of the elements in the matrix.
     * @return The norm of the matrix
     */
    public double norm() {
        double largest = getMatrixEntry(0, 0);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] - largest > 0) {
                    largest = matrix[i][j];
                }
            }
        }
        return largest;
    }

    /**
     * Sets data from a matrix to a sub-matrix in this matrix
     * @param rowStart The row to start at
     * @param rowEnd The row to end at (inclusive)
     * @param colStart The column to start at
     * @param colEnd The column to end at (inclusive)
     */
    public void setMatrix(int rowStart, int rowEnd, int colStart, int colEnd, Matrix subMatrix) {
        int subRow = 0;
        int subColumn = 0;
        for (int i = rowStart; i <= rowEnd; i++) {
            subColumn = 0;
            for (int j = colStart; j <= colEnd; j++) {
                setMatrixEntry(i, j, subMatrix.getMatrixEntry(subRow, subColumn++));
            }
            subRow++;
        }
    }

    /* Basic toString method, just for running purposes.
     *
     *
     **/
    @Override
    public String toString() {
        String output = " ";
        for (int i = 0; i < getMatrixRows(); i++) {
            for (int j = 0; j < getMatrixColumns(); j++) {
                output += matrix[i][j] + " ";
            }
            output +="\n";
        }
        return output;
    }
}
