package main.java.Controllers;

import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductsSoldAnchorController implements Initializable {

    @FXML
    private LineChart<Number, Number> graphView;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private JFXCheckBox dailyCheck;

    @FXML
    private JFXCheckBox weeklyCheck;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        XYChart.Series series = new XYChart.Series();
        series.setName("Revenue");
        series.getData().add(new XYChart.Data(1, 100));
        series.getData().add(new XYChart.Data(2, 300));
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(3);
        xAxis.setTickUnit(1);

        graphView.getData().add(series);


    }
}
