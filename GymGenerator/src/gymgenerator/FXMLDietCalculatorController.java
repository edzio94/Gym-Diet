/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymgenerator;

import gymgenerator.diet.Diet;
import gymgenerator.diet.Food;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lukasz
 */
public class FXMLDietCalculatorController implements Initializable {

    /**
     * Initializes the controller class. 00
     */
    public int counter = 1;
    @FXML
    public Label bLabel;
    @FXML
    public Label wLabel;
    @FXML
    public Label tLabel;
    @FXML
    public Label kcalLabel;
    @FXML
    public Label bmiLabel;
    @FXML
    public ListView<String> dataList;
    @FXML
    public Button addDataButton;
    @FXML
    private TableView<productOnDiet> dietTable;
    @FXML
    private TableColumn dietColumn1;
    @FXML
    private TableColumn dietColumn2;
    @FXML
    private TableColumn bTableColumn;
    @FXML
    private TableColumn tTableColumn;
    @FXML
    private TableColumn wTableColumn;
    @FXML
    private TableColumn kcalTableColumn;

    @FXML
    public TextField gramsTextField;
    //Creating data from dataPane
    @FXML
    public Button generateDataButton;
    @FXML
    public TextField weightTextField;
    @FXML
    public TextField heightTextField;
    @FXML
    public CheckBox gainWeightCheckBox;
    @FXML
    public CheckBox loseWeightCheckBox;
    @FXML
    public Button deleteProductButton;
    @FXML
    public Button backButton;
    @FXML
    public Button nextMealButton;

    private Thread searchThread;

    Diet diet;
    private ObservableList<productOnDiet> data
            = FXCollections.observableArrayList();
    private ObservableList<String> items
            = FXCollections.observableArrayList();
    private int kCalMax;
    private int bMax;
    private int tMax;
    private int wMax;

    @FXML
    private void deleteButtonClicked(ActionEvent event) {
        data.remove(dietTable.getSelectionModel().getFocusedIndex());
    }

    @FXML
    private void nextMealClicked(ActionEvent event) {

        data.add(new productOnDiet("Posiłek nr ", String.valueOf(counter++), null, null, null, null));
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

    @FXML
    private void addButtonClicked(ActionEvent event) {

        String check = dataList.getSelectionModel().getSelectedItem();
        System.out.println("Jedziemy: " + check);
        Food tmp;
        for (Food x : diet.food) {
            if (x.Name.equals(check)) {

                int g = Integer.parseInt(gramsTextField.getText());
                float test = (Float.parseFloat(gramsTextField.getText()) / (float) 100);

                System.out.println("float test: " + test);
                tmp = x;

                System.out.println("tmp.b  dla tylu gram = " + (tmp.b * test));
                data.add(new productOnDiet(tmp.Name, String.valueOf(g),
                        String.valueOf(String.valueOf((int) (float) tmp.b * test)),
                        String.valueOf(String.valueOf((int) (float) tmp.t * test)),
                        String.valueOf(String.valueOf((int) (float) tmp.w * test)),
                        String.valueOf(String.valueOf((int) (float) tmp.kcal * test))));
                System.out.println("Size: " + data.size());
                dietColumn1.setCellValueFactory(new PropertyValueFactory<>("Name"));
                dietColumn2.setCellValueFactory(new PropertyValueFactory<>("Grams"));
                try {
                    bTableColumn.setCellValueFactory(new PropertyValueFactory<>("B"));
                    tTableColumn.setCellValueFactory(new PropertyValueFactory<>("T"));
                    wTableColumn.setCellValueFactory(new PropertyValueFactory<>("W"));
                    kcalTableColumn.setCellValueFactory(new PropertyValueFactory<>("Kcal"));
                } catch (NullPointerException e) {
                    System.out.println("NULL kurde");
                }
                dietTable.setItems(data);
                float b = 0;
                float t = 0;
                float w = 0;
                float kcal = 0;
                for (productOnDiet pod : data) {
                    try{
                    b += Float.parseFloat(pod.getB());
                    t += Float.parseFloat(pod.getT());
                    w += Float.parseFloat(pod.getW());
                    kcal += Float.parseFloat(pod.getKcal());
                    }
                    catch (NullPointerException e)
                    {
                        
                    }

                }
                bLabel.setText("B: " + String.valueOf(b) + "/" + bMax);
                tLabel.setText("T: " + String.valueOf(t) + "/" + tMax);
                wLabel.setText("W: " + String.valueOf(w) + "/" + wMax);
                kcalLabel.setText("Kcal: " + String.valueOf(kcal) + "/" + kCalMax);
                checkData(b, t, w, kcal);

                break;
            }
        }

    }

    @FXML
    private void dataButtonClicked(ActionEvent event) {

        int weight = Integer.parseInt(weightTextField.getText());
        kCalMax = weight * 24;
        if (gainWeightCheckBox.isSelected()) {
            kCalMax += 300;
            float tmp = (float) ((float) weight * 2.5);
            bMax = (int) tmp;
            tmp = (float) ((float) weight * 3.5);
            wMax = (int) tmp;
            tmp = (float) ((float) weight * 0.8);
            tMax = (int) tmp;
        } else if (loseWeightCheckBox.isSelected()) {
            kCalMax -= 300;
            float tmp = (float) ((float) weight * 2.0);
            bMax = (int) tmp;
            tmp = (float) ((float) weight * 2.5);
            wMax = (int) tmp;
            tmp = (float) ((float) weight * 0.5);
            tMax = (int) tmp;
        } else {
            bMax = (int) ((int) weight * 2.2);
            wMax = (int) ((int) weight * 3.0);
            tMax = 60;
        }
        float b = 0;
        float t = 0;
        float w = 0;
        float kcal = 0;
        if (data.size() > 1) {

            for (productOnDiet pod : data) {
                try{
                b += Float.parseFloat(pod.getB());
                t += Float.parseFloat(pod.getT());
                w += Float.parseFloat(pod.getW());
                kcal += Float.parseFloat(pod.getKcal());
                }
                catch(NullPointerException e)
                {
                    System.out.println("NUll");
                }

            }

        }
        bLabel.setText("B: " + String.valueOf(b) + "/" + bMax);
        tLabel.setText("T: " + String.valueOf(t) + "/" + tMax);
        wLabel.setText("W: " + String.valueOf(w) + "/" + wMax);
        kcalLabel.setText("Kcal: " + String.valueOf(kcal) + "/" + kCalMax);
        float height = (float) Float.parseFloat(heightTextField.getText())/(float)100;
        float mass = Float.parseFloat(weightTextField.getText());
        System.out.println("Height: "+height);
        System.out.println("mass: "+mass);
        System.out.println("BMI: "+returnBMI(mass,height));
        String tmpBMI = new String(returnBMI(mass,height));
        System.out.println("TMP: "+tmpBMI);
        bmiLabel.setText("BMI: "+tmpBMI);

    }

    /**/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Just cheking");

        diet = new Diet();
        for (int i = 0; i < diet.food.size(); i++) {
            items.add(diet.food.get(i).Name);
        }
        dataList.setItems(items);
        data.add(new productOnDiet("Posiłek nr " + String.valueOf(counter++), null, null, null, null, null));
        dietTable.setItems(data);

    }

    public static class productOnDiet {

        private final SimpleStringProperty Name;
        private final SimpleStringProperty Grams;
        private final SimpleStringProperty B;
        private final SimpleStringProperty T;
        private final SimpleStringProperty W;
        private final SimpleStringProperty Kcal;

        private productOnDiet(String name, String grams, String b,
                String t, String w, String kcal) {
            System.out.println("Konstruktor name: " + name);
            this.Name = new SimpleStringProperty(name);
            System.out.println("Name: " + this.Name);
            this.Grams = new SimpleStringProperty(grams);
            this.B = new SimpleStringProperty(b);
            this.T = new SimpleStringProperty(t);
            this.W = new SimpleStringProperty(w);
            this.Kcal = new SimpleStringProperty(kcal);
            System.out.println("Dla B: " + this.B);
        }

        public void setName(String name) {
            Name.set(name);
        }

        public String getName() {
            System.out.println("Jestem w Get");
            return Name.get();
        }

        public void setGrams(String grams) {
            Grams.set(grams);
        }

        public String getGrams() {
            return Grams.get();
        }

        // TODO: CHeck for BTW and kcal is you need get set;
        public void setB(String b) {
            B.set(b);
        }

        public String getB() {
            return B.get();
        }

        public void setT(String t) {
            T.set(t);
        }

        public String getT() {
            return T.get();
        }

        public void setW(String w) {
            W.set(w);
        }

        public String getW() {
            return W.get();
        }

        public void setKcal(String kcal) {
            Kcal.set(kcal);
        }

        public String getKcal() {
            return Kcal.get();
        }

    }

    private void checkData(float b, float t, float w, float kcal) {
        if (b > bMax) {
            System.out.println("POWINNO BYĆ czerwone !");
            bLabel.setStyle("-fx-background-color: red;");
        } else {
            bLabel.setStyle("");
        }
        if (t > tMax) {
            System.out.println("POWINNO BYĆ czerwone !");
            tLabel.setStyle("-fx-background-color: red;");
        } else {
            tLabel.setStyle("");
        }
        if (w > wMax) {
            System.out.println("POWINNO BYĆ czerwone !");
            wLabel.setStyle("-fx-background-color: red;");
        } else {
            wLabel.setStyle("");
        }
        if (kcal > kCalMax) {
            System.out.println("POWINNO BYĆ czerwone !");
            kcalLabel.setStyle("-fx-background-color: red;");
        } else {
            kcalLabel.setStyle("");
        }

    }

    private String returnBMI(float mass, float height) {
        if (mass / (height * height) < 18.5) {
            return ("Niedowaga");
        } else if ((mass / (height * height) > 18.5) && (mass / (height * height) < 25.0)) {
            return ("Wartość prawidłowa");
        } else {
            return("Nadwaga");
        }
    }

}
