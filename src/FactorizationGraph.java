//import jxl.Workbook;
//import jxl.write.Number;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;
//import jxl.write.WriteException;

import java.io.*;

/**
 * Generates 10 Pascal Matrices and runs
 * LU Factorization and QR House/Givens on each.
 * It then writes the data to a .txt and .xsl file.
 *
 * @author Lily Lau
 */
public class FactorizationGraph {
    public static void main(String[] args) {
        FactorizationGraph.writeGraphs(12);
    }
    public static void writeGraphs(int iterations) {
        int count = 2;
        File file = new File("FactorizationData.txt");
        while (count <= iterations) {
            PascalMatrix pascal = new PascalMatrix(count);
            Matrix p = pascal.getMatrix();
            Vector b = pascal.getVector();
            Matrix l = new Matrix(1, 1);
            Matrix u = new Matrix(1, 1);
            Vector x = new Vector(1);

            try {
                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(count + "\n");
                //LU
                l = MatrixCalculator.lu_fact(p)[0];
                u = MatrixCalculator.lu_fact(p)[1];
                x = solve_lu_b.solve_lu_b(l, u, b);
                //||LU-P||
                bw.write(MatrixCalculator.subtract(MatrixCalculator.multiply(l, u), p).normF() + "\n");
                //||PX-b||
                bw.write(MatrixCalculator.subtract(MatrixCalculator.multiply(p, x), b).normF() + "\n");
                //House
                l = qr_fact_househ.qr_fact_househ(p)[0];
                u = qr_fact_househ.qr_fact_househ(p)[1];
                x = solve_qr_b.solve_qr_b(l, u, b);
                //||QR-P||
                bw.write(MatrixCalculator.subtract(MatrixCalculator.multiply(l, u), p).normF() + "\n");
                //||PX-b||
                bw.write(MatrixCalculator.subtract(MatrixCalculator.multiply(p, x), b).normF() + "\n");
                //Givens
                l = qr_fact_givens.qr_fact_givens(p)[0];
                u = qr_fact_givens.qr_fact_givens(p)[1];
                x = solve_qr_b.solve_qr_b(l, u, b);
                //||QR-P||
                bw.write(MatrixCalculator.subtract(MatrixCalculator.multiply(l, u), p).normF() + "\n");
                //||PX-b||
                bw.write(MatrixCalculator.subtract(MatrixCalculator.multiply(p, x), b).normF() + "\n");
                bw.close();
            } catch (IOException e) {
                System.out.println("Could not write.");
            }
            count++;
        }
    }
//    public static void writeExcel() {
//        int count = 0;
//        int cols = 7;
//        try {
//            WritableWorkbook workbook = Workbook.createWorkbook(new File("FactorizationExcel.xls"));
//            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
//            try (BufferedReader br = new BufferedReader(new FileReader(new File("FactorizationData.txt")))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    try {
//                        if (count % cols == 0) {
//                            sheet.addCell(new Number(0, count/cols, Double.parseDouble(line)));
//                        } else if (count % cols == 1) {
//                            sheet.addCell(new Number(1, count/cols, Double.parseDouble(line)));
//                        } else if (count % cols == 2) {
//                            sheet.addCell(new Number(2, count/cols, Double.parseDouble(line)));
//                        } else if (count % cols == 3) {
//                            sheet.addCell(new Number(3, count/cols, Double.parseDouble(line)));
//                        } else if (count % cols == 4) {
//                            sheet.addCell(new Number(4, count/cols, Double.parseDouble(line)));
//                        } else if (count % cols == 5) {
//                            sheet.addCell(new Number(5, count/cols, Double.parseDouble(line)));
//                        } else if (count % cols == 6) {
//                            sheet.addCell(new Number(6, count / cols, Double.parseDouble(line)));
//                        }
//                        count++;
//                    } catch (jxl.write.WriteException e) {
//                        System.out.println("Cannot write.");
//                    }
//                }
//            }
//            workbook.write();
//            workbook.close();
//        } catch (IOException e) {
//            System.out.println("op");
//        } catch (WriteException e) {
//            e.printStackTrace();
//        }
//    }
}
