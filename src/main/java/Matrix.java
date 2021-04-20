import lombok.Data;

@Data
public class Matrix {

    private final double [][] matrix;
    private final int size;

    public Matrix(double [][] matrix) {
        this.matrix = matrix;
        this.size = matrix.length;
    }

    public double[] getVector(){
        double [] vector = new double[size];
        for(int i = 0; i < this.size; i++){
            vector[i]=matrix[i][size];
        }
        return vector;
    }
}