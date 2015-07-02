/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymgenerator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 * FXML Controller class
 *
 * @author lukasz
 */
public class FXMLPlanGeneratorController implements Initializable {

    Plan plan;
    boolean gym;
    // Whole menu with what user want to achive.
    @FXML
    private MenuItem powerButton;
    @FXML
    private MenuItem gainButton;
    @FXML
    private MenuItem conditionButton;
    @FXML
    private MenuButton typeMenuButton;
    @FXML
    private Button planGeneratorButton;

    // initialize columns in plan table
    @FXML
    private TableView<Exercise> trainingTable;

    //private ObservableList<Exercise> tmp = FXCollections.observableArrayList();
    private ObservableList<List<Exercise>> tmp = FXCollections.observableArrayList();
    private ObservableList<ObservableList<Exercise>> tmpx = FXCollections.observableArrayList();
    private ObservableList<Exercise> test = FXCollections.observableArrayList();
    // trainingTable.set
    @FXML
    private TableColumn mondayColumn;
    @FXML
    private TableColumn wednesdayColumn;
    @FXML
    private TableColumn fridayColumn;
    @FXML
    private TextField daysTextField;
    @FXML
    private TextField hoursTextField;
    @FXML
    private GridPane planGridPane;
    @FXML
    private Label restLabel;
    @FXML
    private Button backButton;

    // Radio button (if user go to professional gym or no (home stuff).
    @FXML
    private RadioButton gymRadioButton;

    public FXMLPlanGeneratorController() {
        gym = true;
    }

    @FXML
    public void powerIsClicked(ActionEvent event) {
        System.out.println("Picked: " + event.getSource());
        typeMenuButton.setText("Power");
    }

    @FXML
    public void gainIsClicked(ActionEvent event) {
        System.out.println("Picked: " + this);
        typeMenuButton.setText("Gain");
    }

    @FXML
    public void conditionIsClicked(ActionEvent event) {
        System.out.println("Picked: " + this);
        typeMenuButton.setText("Look");

        // typeMenuButton.get
    }

    @FXML
    private void backButtonClicked(ActionEvent event) {
        Parent Menu = null;
        try {
            Menu = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDietCalculatorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene MenuScene = new Scene(Menu);
        Stage MenuStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MenuStage.hide();
        MenuStage.setScene(MenuScene);
        MenuStage.show();
    }

   

// Generate plan if user wants to.
    @FXML
    private void generatePlan() throws IOException {
         List<List<Label>> data = new ArrayList();
        //trainingTable.setItems(null);
        // Gettin type of achivement user want.
        String achivement = typeMenuButton.getText();      
        // Checking if user can go to professional gym or not.
        if (gymRadioButton.isSelected()) {
            gym = true;
        } else {
            gym = false;
        }
        this.plan = new Plan("FBW", Integer.parseInt(daysTextField.getCharacters().toString()), achivement, gym);
        //plan.test();
        restLabel.setText(plan.info);
        // Putting data on grid;
        for (int i = 0; i < plan.exerciseListBeta.size(); i++) {
            data.add(new ArrayList());
        //    tmpx.add(FXCollections.observableArrayList(plan.exerciseListBeta.get(i)));
            for (int j = 0; j < plan.exerciseListBeta.get(i).size(); j++) {
                test.add(plan.exerciseListBeta.get(i).get(j));
                data.get(data.size() - 1).add(new Label(plan.exerciseListBeta.get(i).get(j).getInfo()));
                planGridPane.add(plan.exerciseListBeta.get(i).get(j).label, (plan.exerciseListBeta.get(i).get(j).dayInfo - 1), j + 1);

            }

        }
        try {
            trainingTable.setItems(test);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // Class that we put into gridpande.
    public static class Exercise {

        private final SimpleStringProperty exercise;
        private final SimpleStringProperty repeats;
        private final SimpleStringProperty series;
        private final SimpleStringProperty info;
        private SimpleStringProperty mondayInfo;
        private SimpleStringProperty tuesdayInfo;
        private final int dayInfo;
        public final Label label;

        private SimpleStringProperty wednesdayInfo;
        private SimpleStringProperty thursdayInfo;
        private SimpleStringProperty fridayInfo;

        Exercise(String exercise, String repeats, String series, int day) {
            System.out.println("DAY: " + day);
            dayInfo = day;
            this.exercise = new SimpleStringProperty(exercise);
            this.repeats = new SimpleStringProperty(repeats);
            this.series = new SimpleStringProperty(series);
            this.info = new SimpleStringProperty(this.exercise.getValue()
                    + "[" + this.series.getValue() + "X" + this.repeats.getValue() + "]");
            this.label = new Label(getInfo());
            mondayInfo = info(day, 1);
            tuesdayInfo = info(day, 2);
            wednesdayInfo = info(day, 3);
            thursdayInfo = info(day, 4);
            fridayInfo = info(day, 5);

        }

        private SimpleStringProperty info(int Day, int ID) {
            if (Day == ID) {
                return new SimpleStringProperty(this.exercise.getValue()
                        + "[" + this.series.getValue() + "X" + this.repeats.getValue() + "]");
            } else {
                return new SimpleStringProperty(null);
            }

        }
        /*   private SimpleStringProperty info(int day,int ID) {
         if (day == ID)
         
         }*/

        public void setInfo(String infos) {
            info.set(infos);
        }

        public String getInfo() {
            return info.get();
        }

        public String getExercise() {
            return exercise.get();
        }

        public void setExercise(String fName) {
            exercise.set(fName);
        }

        public String getRepeats() {
            return repeats.get();
        }

        public void setRepeats(String fName) {
            repeats.set(fName);
        }

        public String getSeries() {
            return series.get();
        }

        public void setseries(String fName) {
            series.set(fName);
        }

        public void setMondayInf(String mondayInf) {
            mondayInfo.set(mondayInf);
        }

        public void setTuesdayInf(String tuesdayyInf) {
            tuesdayInfo.set(tuesdayyInf);
        }

        public void setWednesdayInf(String wednesdayInf) {
            wednesdayInfo.set(wednesdayInf);
        }

        public void setThursdayInf(String thursdayInf) {
            thursdayInfo.set(thursdayInf);
        }

        public void setfridayInf(String fridayInf) {
            fridayInfo.set(fridayInf);
        }

        public String getMondayInfo() {
            return mondayInfo.get();
        }

        public String getTuesdayInfo() {
            return tuesdayInfo.get();
        }

        public String getWednesdayInfo() {
            return wednesdayInfo.get();
        }

        public String getThursdayInfo() {
            return thursdayInfo.get();
        }

        public String getFridayInfo() {
            return fridayInfo.get();
        }

    }

}
