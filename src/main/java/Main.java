import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField inputField = new TextField();
    private Label resultLabel = new Label();
    private double lastResult;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        inputField.setPromptText("Enter value");

        // Nút chuyển đổi
        Button cToF = new Button("Celsius → Fahrenheit");
        cToF.setOnAction(e -> convertCtoF());

        Button fToC = new Button("Fahrenheit → Celsius");
        fToC.setOnAction(e -> convertFtoC());

        Button kToC = new Button("Kelvin → Celsius");
        kToC.setOnAction(e -> convertKtoC());

        Button cToK = new Button("Celsius → Kelvin");
        cToK.setOnAction(e -> convertCtoK());

        // save DB
        Button saveButton = new Button("Save to DB");
        saveButton.setOnAction(e -> {
            try {
                double input = Double.parseDouble(inputField.getText());
                Database.saveTemperature(input, lastResult, resultLabel);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input!");
            }
        });

        VBox root = new VBox(10,
                inputField,
                cToF, fToC, kToC, cToK,
                resultLabel,
                saveButton
        );

        Scene scene = new Scene(root, 350, 300);
        stage.setTitle("Temperature Converter");
        stage.setScene(scene);
        stage.show();
    }

    private void convertCtoF() {
        try {
            double c = Double.parseDouble(inputField.getText());
            lastResult = (c * 9 / 5) + 32;
            resultLabel.setText(String.format("Fahrenheit: %.2f", lastResult));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }

    private void convertFtoC() {
        try {
            double f = Double.parseDouble(inputField.getText());
            lastResult = (f - 32) * 5 / 9;
            resultLabel.setText(String.format("Celsius: %.2f", lastResult));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }

    private void convertKtoC() {
        try {
            double k = Double.parseDouble(inputField.getText());
            lastResult = k - 273.15;
            resultLabel.setText(String.format("Celsius: %.2f", lastResult));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }

    private void convertCtoK() {
        try {
            double c = Double.parseDouble(inputField.getText());
            lastResult = c + 273.15;
            resultLabel.setText(String.format("Kelvin: %.2f", lastResult));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }
}
