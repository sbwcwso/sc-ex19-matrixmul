package matrixmultiplication;

/**
 * Represents a scalar value in a matrix expression.
 * This is an immutable type.
 */
class Scalar implements MatrixExpression {
    
    private final double value;
    
    // RI: true
    // AF(value) = the real scalar represented by value
    // Safety from rep exposure:
    //   - all fields are private and final
    //   - value is immutable (double is primitive)
    
    /**
     * Creates a scalar matrix expression.
     * @param value the scalar value
     */
    public Scalar(double value) {
        this.value = value;
        checkRep();
    }
    
    /**
     * Checks the rep invariant.
     */
    private void checkRep() {
        // RI is always true
    }
    
    /**
     * Gets the value of this scalar.
     * @return the scalar value
     */
    public double getValue() {
        return value;
    }
    
    @Override
    public boolean isIdentity() {
        return value == 1;
    }
    
    @Override
    public MatrixExpression optimize() {
        return this;
    }
    
    @Override
    public MatrixExpression scalars() {
        return this;
    }
    
    @Override
    public MatrixExpression matrices() {
        return MatrixExpression.I;
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Scalar)) return false;
        Scalar that = (Scalar) obj;
        return this.value == that.value;
    }
    
    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
