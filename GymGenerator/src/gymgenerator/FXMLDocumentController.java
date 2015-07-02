/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymgenerator;

import ExcelParser.ExcelParser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeDebug.getClass;

/**
 *
 * @author lukasz
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
            //ExcelParser ex = new ExcelParser();
            System.out.println("You clicked me!");
            label.setText("Hello World!");
            Parent dietCalculator = FXMLLoader.load(getClass().getResource
            ("FXMLDietCalculator.fxml"));
            Scene dietGeneratorScene = new Scene(dietCalculator);
            Stage dietGeneratorStage = (Stage) ((Node) event.getSource()).getScene().getWindow();  
            dietGeneratorStage.hide();
            dietGeneratorStage.setScene(dietGeneratorScene);
             dietGeneratorStage.show();
    }
    @FXML
    private void PlanButtonClicked(ActionEvent event) throws IOException {
        Parent planGeneratorParent = FXMLLoader.load(getClass().getResource
        ("FXMLPlanGenerator.fxml"));
        Scene planGeneratorScene = new Scene(planGeneratorParent);
        Stage planGeneratorStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        planGeneratorStage.hide();
        planGeneratorStage.setScene(planGeneratorScene);
        planGeneratorStage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
