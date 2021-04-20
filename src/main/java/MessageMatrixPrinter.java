import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

@NoArgsConstructor
public class MessageMatrixPrinter {
    private final MathContext context = new MathContext(3, RoundingMode.HALF_UP);

    void printMatrix(Matrix matrix) {
        System.out.println("Матрица:");
        if (matrix != null) {
            for (int i = 0; i < matrix.getSize(); i++) {
                for (int j = 0; j < matrix.getSize() + 1; j++) {
                    System.out.print(new BigDecimal(matrix.getMatrix()[i][j], context));
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    public void printIter(int step, int size, double[] vectorResult, ArrayList<Double> errorVector) {
        StringBuilder result = new StringBuilder("Step № " + step + "####### \n");

        for (int i = 1; i <= size; i++) {
            result.append("x").append(i).append(" = ").append(new BigDecimal(vectorResult[i - 1],context)).append("; ")
                    .append("s").append(i).append(" = ").append(new BigDecimal(errorVector.get(i-1),context)).append("\n");
        }

        System.out.println(result);
    }
}
