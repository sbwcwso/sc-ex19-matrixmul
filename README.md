# Matrix Multiplication Expression Optimizer

This project provides a Java implementation of an Abstract Data Type (ADT) for representing and optimizing matrix multiplication expressions. The implementation is based on the concepts and design patterns discussed in MIT 6.031's "Reading 19: Writing a Program with Abstract Data Types".

The core idea is to represent a sequence of matrix and scalar multiplications as an expression tree. This allows for optimizations, such as grouping scalar multiplications together, before performing the expensive matrix computations.

## Features

- **Recursive ADT**: The `MatrixExpression` is defined as a recursive ADT with four variants:
    - `Identity`: The multiplicative identity.
    - `Scalar`: A scalar value (double).
    - `Matrix`: A 2D matrix of doubles.
    - `Product`: The product of two `MatrixExpression`s.
- **Immutability**: All types in the ADT are immutable, ensuring thread safety and predictable behavior.
- **Expression Optimization**: A simple `optimize()` method is provided, which rearranges the expression tree to group all scalar values and all matrix values together. For an expression like `(a * X) * b`, it produces an equivalent expression `(a * b) * X`, which can be computed more efficiently.
- **Static Factory Methods**: The `MatrixExpression` interface provides static factory methods (`make()`, `times()`) for creating expressions, hiding the concrete implementation classes from the client.
- **Unit Tests**: The project includes a suite of JUnit 5 tests to verify the correctness of the implementation, particularly the `optimize()` method.

## Project Structure

```
.
├── pom.xml                 # Maven Project Configuration
├── README.md               # This file
├── .gitignore              # Git ignore file
└── src
    ├── main
    │   └── java
    │       └── matrixmultiplication
    │           ├── Identity.java
    │           ├── Matrix.java
    │           ├── MatrixExpression.java
    │           ├── Product.java
    │           └── Scalar.java
    └── test
        └── java
            └── matrixmultiplication
                └── MatrixExpressionTest.java
```

## How to Build and Run

### Prerequisites

- Java Development Kit (JDK) 25 or later
- Apache Maven

### Build the Project

To compile the source code, run the tests, and package the project into a JAR file, execute the following command from the root directory:

```sh
mvn clean install
```

### Run Tests

To run the JUnit tests, use the following Maven command:

```sh
mvn test
```

## Example Usage

Here is a simple example of how to create and optimize a matrix expression:

```java
// In your own main method or test

// Create expressions for scalars and matrices
MatrixExpression a = MatrixExpression.make(2.0);
MatrixExpression b = MatrixExpression.make(3.0);
MatrixExpression X = MatrixExpression.make(new double[][] {{1, 2}, {3, 4}});

// Create the expression: (a * X) * b
MatrixExpression originalExpr = MatrixExpression.times(MatrixExpression.times(a, X), b);

// Optimize the expression
MatrixExpression optimizedExpr = originalExpr.optimize();

// The optimized expression will have scalars grouped together
// The output will show the structural difference

// Original: (((2.0 * Matrix[[1.0, 2.0]; [3.0, 4.0]]) * 3.0))
System.out.println("Original expression: " + originalExpr);

// Optimized: (((2.0 * I) * 3.0) * ((I * Matrix[[1.0, 2.0]; [3.0, 4.0]]) * I))
System.out.println("Optimized expression: " + optimizedExpr);
```
