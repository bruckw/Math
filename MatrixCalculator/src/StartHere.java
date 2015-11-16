import java.util.Scanner;

/**
 * Created by Lily on 11/15/2015.
 */
public class StartHere {
    public static void main(String[] args) {
        PowerGraph.writeGraphs(1000);
        double[][] arr1 = new double[2][2];
        arr1[0][0] = 4;
        arr1[0][1] = 3;
        arr1[1][0] = 3;
        arr1[1][1] = 2;
        Matrix b = new Matrix(arr1);
        Matrix a = PowerGraph.computeInverse(b);
        for (int i = 0; i < a.getMatrixRows(); i++) {
            for (int j = 0; j < a.getMatrixColumns(); j++) {
                System.out.println(a.getMatrixEntry(i, j));
            }
        }

        System.out.print("Say something: ");
        //Scanner sc = new Scanner(System.in);
        //String i = sc.next();
        double[][] arr = new double[3][3];
        arr[0][0] = 1;
        arr[0][1] = 1;
        arr[0][2] = -1;
        arr[1][0] = 1;
        arr[1][1] = 2;
        arr[1][2] = 1;
        arr[2][0] = 2;
        arr[2][1] = -1;
        arr[2][2] = 1;
        double[] varr = new double[3];
        varr[0] = 1;
        varr[1] = 1;
        varr[2] = 1;
        Matrix testM = new Matrix(arr);
        Vector testV = new Vector(varr);
        double tol = .01;
        PowerObject ans = PowerMethod.power_method(testM, testV, tol, 100);
        System.out.println(ans.getEigenvalue());
        for (int i = 0; i < ans.getEigenvector().getVectorRows(); i++) {
            System.out.println(ans.getEigenvector().getVectorEntry(i));
        }
        System.out.println(ans.getIterations());
    }
}
