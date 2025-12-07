package matrixmultiplication;

import java.util.Arrays;

/**
 * Represents a matrix in a matrix expression.
 * This is an immutable type.
 */
class Matrix implements MatrixExpression {
    
    private final double[][] array;
    
    // RI: array.length > 0, and all array[i] are equal nonzero length
    // AF(array) = the matrix with array.length rows and array[0].length columns
    //             whose (row,column) entry is array[row][column]
    // Safety from rep exposure:
    //   - all fields are private and final
    //   - array is mutable, so constructor and getArray() make defensive copies
    
    /**
     * Creates a matrix expression.
     * @param array the matrix as a 2D array
     */
    public Matrix(double[][] array) {
        // Defensive copy
        this.array = new double[array.length][];
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i].clone();
        }
        checkRep();
    }
    
    /**
     * Checks the rep invariant.
     */
    private void checkRep() {
        assert array.length > 0 : "Matrix must have at least one row";
        int expectedCols = array[0].length;
        assert expectedCols > 0 : "Matrix must have at least one column";
        for (int i = 0; i < array.length; i++) {
            assert array[i].length == expectedCols : "All rows must have the same length";
        }
    }
    
    /**
     * Gets a defensive copy of the matrix array.
     * @return a copy of the matrix array
     */
    public double[][] getArray() {
        double[][] copy = new double[array.length][];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i].clone();
        }
        return copy;
    }
    
    /**
     * Gets the number of rows in this matrix.
     * @return the number of rows
     */
    public int getRows() {
        return array.length;
    }
    
    /**
     * Gets the number of columns in this matrix.
     * @return the number of columns
     */
    public int getCols() {
        return array[0].length;
    }
    
    @Override
    public boolean isIdentity() {
        if (array.length != array[0].length) {
            return false;
        }
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[row].length; col++) {
                double expected = (row == col) ? 1 : 0;
                if (array[row][col] != expected) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public MatrixExpression optimize() {
        return this;
    }
    
    @Override
    public MatrixExpression scalars() {
        return MatrixExpression.I;
    }
    
    @Override
    public MatrixExpression matrices() {
        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matrix[");
        for (int i = 0; i < array.length; i++) {
            if (i > 0) sb.append("; ");
            sb.append(Arrays.toString(array[i]));
        }
        sb.append("]");
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Matrix)) return false;
        Matrix that = (Matrix) obj;
        return Arrays.deepEquals(this.array, that.array);
    }
    
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(array);
    }
}
