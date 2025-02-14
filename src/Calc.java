import java.util.Scanner;

public class Calc {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);


        System.out.println("Please type how many numbers do you want to get calculated: ");
        int num = scnr.nextInt();


        System.out.println("Please type in the numbers you want to calculate: ");
        double[] userNum = new double[num]; // Array to store the numbers
        for (int i = 0; i < num; ++i) {
            userNum[i] = scnr.nextDouble();
        }


        System.out.println("Please enter the operators of your choice (+, -, *, /): ");
        char[] userOp = new char[num - 1]; // Array to store the operators
        for (int i = 0; i < num - 1; ++i) {
            userOp[i] = scnr.next().charAt(0); // Read each operator
        }

        // Evaluate multiplication and division first (order of operations)
        for (int i = 0; i < userOp.length; ) {
            if (userOp[i] == '*' || userOp[i] == '/') {

                double result = performOperation(userNum[i], userNum[i + 1], userOp[i]);

                // Update the numbers array
                userNum[i] = result;

                // Shift the remaining numbers to the left
                for (int j = i + 1; j < userNum.length - 1; j++) {
                    userNum[j] = userNum[j + 1];
                }

                // Shift the remaining operators to the left
                for (int j = i; j < userOp.length - 1; j++) {
                    userOp[j] = userOp[j + 1];
                }

                // Resize the arrays to remove the used number and operator
                userNum = resizeArray(userNum, userNum.length - 1);
                userOp = resizeArray(userOp, userOp.length - 1);
            } else {
                i++; // Move to the next operator if the current one is not * or /
            }
        }


        double finalResult = userNum[0]; // Start with the first number
        for (int i = 0; i < userOp.length; i++) {
            // Perform the operation and update the result
            finalResult = performOperation(finalResult, userNum[i + 1], userOp[i]);
        }


        System.out.println("The result of the calculation is: " + finalResult);
    }

    // Helper method to perform an operation
    private static double performOperation(double a, double b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b != 0) {
                    return a / b;
                } else {
                    System.out.println("Error: Division by zero!");
                    System.exit(1);
                }
            default:
                System.out.println("Error: Invalid operator '" + op + "'");
                System.exit(1);
        }
        return 0;
    }

    // Helper method to resize an array (for numbers)
    private static double[] resizeArray(double[] array, int newSize) {
        double[] newArray = new double[newSize];
        System.arraycopy(array, 0, newArray, 0, newSize); // Copy elements to the new array
        return newArray;
    }

    // Helper method to resize an array (for operators)
    private static char[] resizeArray(char[] array, int newSize) {
        char[] newArray = new char[newSize];
        System.arraycopy(array, 0, newArray, 0, newSize); // Copy elements to the new array
        return newArray;
    }
}