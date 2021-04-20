import java.util.ArrayList;

import static java.lang.Math.abs;

public class ComputationMatrix {
    private static MessageMatrixPrinter printer = new MessageMatrixPrinter();
    private static double[][] val;

    public static void findSolution(Matrix matrix, double eps) {
        printer.printMatrix(matrix);
        if (checkCondition(matrix.getMatrix(), matrix.getSize())) {
            solve(matrix, eps);
            return;
        }
        permuteMatrixRows(matrix, 0);
        if (val != null) {
            Matrix matrix1 = new Matrix(val);
            System.out.println("Матрица после перестановки строк");
            printer.printMatrix(matrix1);
            solve(matrix1, eps);
        } else {
            System.out.println("Impossible to perform diagonal dominance");
        }
        val = null;
    }

    //Проверка условия преобладания
    public static boolean checkCondition(double[][] matrix, int size) {
        int i, j, k = 1;
        double sum = 0;
        for (i = 0; i < size; i++) {
            sum = 0;
            for (j = 0; j < size; j++) {
                sum += abs(matrix[i][j]);
            }
            sum -= abs(matrix[i][i]);
            if (sum >= abs(matrix[i][i])) {
                k = 0;
            }
        }
        return (k == 1);
    }

    //Переставим уравнения
    //местами так, чтобы выполнялось условие преобладания диагональных
    //элементов
    private static void permuteMatrixRows(Matrix matrix, int index) {
        if (index >= matrix.getMatrix().length - 1) {
            if (checkCondition(matrix.getMatrix(), matrix.getSize())) {
                val = new double[matrix.getSize()][matrix.getSize() + 1];
                for (int i = 0; i < matrix.getSize(); i++) {
                    for (int j = 0; j < matrix.getSize() + 1; j++) {
                        val[i][j] = matrix.getMatrix()[i][j];
                    }
                }
            }
            return;
        } else {
            for (int i = index; i < matrix.getMatrix().length; i++) {
                double[] t = matrix.getMatrix()[index];
                matrix.getMatrix()[index] = matrix.getMatrix()[i];
                matrix.getMatrix()[i] = t;

                permuteMatrixRows(matrix, index + 1);

                t = matrix.getMatrix()[index];
                matrix.getMatrix()[index] = matrix.getMatrix()[i];
                matrix.getMatrix()[i] = t;
            }
        }
    }

    private static void solve(Matrix matrix, double eps) {
        double[] x = matrix.getVector();
        for (int i = 0; i < matrix.getSize(); i++){
            x[i] = x[i] / matrix.getMatrix()[i][i];
        }
        double maxEps = 0;
        double temp, sum;
        int k = 1;
        do {
            ArrayList<Double> epss = new ArrayList<>();
            maxEps = 0;
            for (int i = 0; i < matrix.getSize(); i++) {
                temp = x[i];
                sum = 0;

                for (int j = 0; j < matrix.getSize(); j++) {
                    if (j != i)
                        sum += matrix.getMatrix()[i][j] * x[j];
                }
                x[i] = (matrix.getVector()[i] - sum) / matrix.getMatrix()[i][i];
                epss.add(abs(x[i] - temp));
                if (abs(x[i] - temp) > maxEps)
                    maxEps = abs(x[i] - temp);
            }
            printer.printIter(k, matrix.getSize(), x, epss);
            k++;
        }
        while (maxEps > eps);
    }
}
