package matrixmultiplication;

/**
 * Represents the identity matrix in a matrix expression.
 * This is an immutable type.
 */
class Identity implements MatrixExpression {
    
    /**
     * Creates an identity matrix expression.
     */
    public Identity() {
    }
    
    @Override
    public boolean isIdentity() {
        return true;
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
        return this;
    }
    
    @Override
    public String toString() {
        return "I";
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Identity;
    }
    
    @Override
    public int hashCode() {
        return 1;
    }
}
