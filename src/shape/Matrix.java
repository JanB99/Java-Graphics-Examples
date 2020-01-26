package shape;

public class Matrix {

    public double[][] matrix;

    public Matrix(double[][] matrix){
        this.matrix = matrix;
    }

    public Matrix(){ }

    public void setMatrix(double[][] m){
        this.matrix = m;
    }

    public static Vector3d matrixToVector(Matrix m) {
        Vector3d v = new Vector3d(0, 0, 0);
        v.x = (float)m.matrix[0][0];
        v.y = (float)m.matrix[1][0];
        if (m.matrix.length > 2){
            v.z = (float)m.matrix[2][0];
        }
        return v;
    }

    public static double[][] vectorToMatrix(Vector3d vec) {
        return new double[][] {
                new double[]{ vec.x},
                new double[]{ vec.y},
                new double[]{ vec.z},
        };
    }

    public static Vector3d matmul(Matrix a, Vector3d b){
        double[][] m = Matrix.vectorToMatrix(b);
        Matrix res = Matrix.matmul(a.matrix, m);
        return Matrix.matrixToVector(res);
    }

    public static Matrix matmul(double[][] a, double[][] b){

        int rowsA = a.length;
        int colsA = a[0].length;
        int rowsB = b.length;
        int colsB = b[0].length;

        if (rowsB != colsA){
            System.out.println("rijen en colommen moeten evengroot zijn");
            return null;
        }

        double[][] result = new double[rowsA][colsB];

        for (int i = 0; i < rowsA; i++){
            for (int j = 0; j < colsB; j++){
                double sum = 0;
                for (int k = 0; k < colsA; k++){
                    sum += a[i][k] * b[k][j];
                }

                result[i][j] = sum;
            }
        }

        return new Matrix(result);
    }
}
