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
}
