package matrixmultiplication;

/**
 * Represents an immutable expression of matrix and scalar products.
 * 
 * Datatype definition:
 *   MatExpr = Identity + Scalar(value:double)
 *             + Matrix(array:double[][]) + Product(m1:MatExpr, m2:MatExpr)
 */
public interface MatrixExpression {
    
    /** Identity for all matrix computations. */
    public static final MatrixExpression I = new Identity();
    
    /**
     * Creates a matrix expression consisting of just the scalar value.
     * @param value the scalar value
     * @return a matrix expression consisting of just the scalar value
     */
    public static MatrixExpression make(double value) {
        return new Scalar(value);
    }
    
    /**
     * Creates a matrix expression consisting of just the matrix given.
     * @param array the matrix as a 2D array
     * @return a matrix expression consisting of just the matrix given
     */
    public static MatrixExpression make(double[][] array) {
        return new Matrix(array);
    }
    
    /**
     * Creates a product of two matrix expressions.
     * @param m1 first matrix expression
     * @param m2 second matrix expression
     * @return m1 × m2
     */
    public static MatrixExpression times(MatrixExpression m1, MatrixExpression m2) {
        return new Product(m1, m2);
    }
    
    /**
     * Checks if this expression is the multiplicative identity.
     * @return true only if the expression is the multiplicative identity
     */
    public boolean isIdentity();
    
    /**
     * Returns an optimized version of this expression.
     * @return an expression with the same value, but which may be faster to compute
     */
    public MatrixExpression optimize();
    
    /**
     * Returns the product of all the scalars in this expression.
     * @return the product of all the scalars in this expression
     */
    public MatrixExpression scalars();
    
    /**
     * Returns the product of all the matrices in this expression in order.
     * times(scalars(), matrices()) is equivalent to this expression.
     * @return the product of all the matrices in this expression
     */
    public MatrixExpression matrices();
}
