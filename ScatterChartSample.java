package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ScatterChartSample extends Application {

    @Override public void start(Stage stage) {
        stage.setTitle("Power Method Plot");
        // Change the axes of your graph
        final NumberAxis xAxis = new NumberAxis(-8, 8, 1);
        final NumberAxis yAxis = new NumberAxis(-5, 5, 1);
        final ScatterChart<Number,Number> sc = new ScatterChart<>(xAxis,yAxis);
        xAxis.setLabel("Determinant");
        yAxis.setLabel("Trace");
        // Change title of your graph
        sc.setTitle("Power Method on Matrix A");
        XYChart.Series series1 = new XYChart.Series();
        // Optional: Change legend name on your graph
        series1.setName("Power Method on Inverse of Matrix A");
        // Enter the desired file name
        String fileName = "PowerMethodData.txt";
        String line;
        int count = 0;
        int cols = 10;
        double xPoint = 0;
        double yPoint = 0;
        double opacity;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                // Change what column count % cols should equal
                // ex. "count % cols == 0" will print the first column of data
                if (count % cols == 0) {
                    xPoint = Double.parseDouble(line);
                } else if (count % cols == 1) {
                    yPoint = Double.parseDouble(line);
                } else if (count % cols == 2) {
                    opacity = Double.parseDouble(line) / 100;
                    XYChart.Data dt=  new XYChart.Data(xPoint, yPoint);
                    Rectangle rect1 = new Rectangle(5, 5);
                    rect1.setFill(Color.GREEN);
                    dt.setNode(rect1);
                    dt.getNode().setOpacity(opacity + .2);
                    series1.getData().add(dt);
                }
                count++;
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.getData().addAll(series1);
        Scene scene  = new Scene(sc, 500, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}