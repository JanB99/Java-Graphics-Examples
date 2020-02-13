package shape;

public class Matrix4d {

    public double[][] matrix;

    public Matrix4d(double[][] matrix){
        this.matrix = matrix;
    }

    public Matrix4d(){ }

    public void setMatrix(double[][] m){
        this.matrix = m;
    }

    public static Vector4d matrixToVector(Matrix4d m) {
        Vector4d v = new Vector4d(0, 0, 0, 0);
        v.x = (float)m.matrix[0][0];
        v.y = (float)m.matrix[1][0];
        if (m.matrix.length > 3){
            v.z = (float)m.matrix[2][0];
        }
        if (m.matrix.length > 3){
            v.w = (float)m.matrix[3][0];
        }
        return v;
    }

    public static double[][] vectorToMatrix(Vector4d vec) {
        return new double[][] {
                new double[]{ vec.x},
                new double[]{ vec.y},
                new double[]{ vec.z},
                new double[]{ vec.w},
        };
    }

    public static Vector4d matmul(Matrix4d a, Vector4d b){
        double[][] m = Matrix4d.vectorToMatrix(b);
        Matrix4d res = Matrix4d.matmul(a.matrix, m);
        return Matrix4d.matrixToVector(res);
    }

    public static Matrix4d matmul(double[][] a, double[][] b){

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

        return new Matrix4d(result);
    }
}
