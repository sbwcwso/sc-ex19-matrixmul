package matrixmultiplication;

/**
 * Represents a product of two matrix expressions.
 * This is an immutable type.
 */
class Product implements MatrixExpression {
    
    private final MatrixExpression m1;
    private final MatrixExpression m2;
    
    // RI: m1's column count == m2's row count, or m1 or m2 is scalar
    // AF(m1, m2) = the matrix product m1*m2
    // Safety from rep exposure:
    //   - all fields are private and final
    //   - m1 and m2 are immutable types (MatrixExpression is immutable)
    
    /**
     * Creates a product of two matrix expressions.
     * @param m1 first matrix expression
     * @param m2 second matrix expression
     */
    public Product(MatrixExpression m1, MatrixExpression m2) {
        this.m1 = m1;
        this.m2 = m2;
        checkRep();
    }
    
    /**
     * Checks the rep invariant.
     */
    private void checkRep() {
        // Check compatibility for multiplication
        // This is simplified - in a real implementation, we'd need to track dimensions
        assert m1 != null : "m1 must not be null";
        assert m2 != null : "m2 must not be null";
    }
    
    /**
     * Gets the first operand.
     * @return the first matrix expression
     */
    public MatrixExpression getM1() {
        return m1;
    }
    
    /**
     * Gets the second operand.
     * @return the second matrix expression
     */
    public MatrixExpression getM2() {
        return m2;
    }
    
    @Override
    public boolean isIdentity() {
        return m1.isIdentity() && m2.isIdentity();
    }
    
    @Override
    public MatrixExpression optimize() {
        return MatrixExpression.times(scalars(), matrices());
    }
    
    @Override
    public MatrixExpression scalars() {
        return MatrixExpression.times(m1.scalars(), m2.scalars());
    }
    
    @Override
    public MatrixExpression matrices() {
        return MatrixExpression.times(m1.matrices(), m2.matrices());
    }
    
    @Override
    public String toString() {
        return "(" + m1.toString() + " * " + m2.toString() + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product)) return false;
        Product that = (Product) obj;
        return this.m1.equals(that.m1) && this.m2.equals(that.m2);
    }
    
    @Override
    public int hashCode() {
        return m1.hashCode() * 31 + m2.hashCode();
    }
}
