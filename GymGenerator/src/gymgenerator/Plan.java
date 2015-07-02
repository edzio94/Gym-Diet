/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymgenerator;

import com.sun.javafx.collections.ElementObservableListDecorator;
import com.sun.javafx.collections.ObservableListWrapper;
import gymgenerator.FXMLPlanGeneratorController.Exercise;
import gymgenerator.exercises.Attributes;

import gymgenerator.exercises.BackExercises;
import gymgenerator.exercises.BicepsExercises;

import gymgenerator.exercises.ChestExercises;
import gymgenerator.exercises.ExercisesBase;
import gymgenerator.exercises.LegsExercises;

import gymgenerator.exercises.ShoulderExercises;
import gymgenerator.exercises.TrapsExercises;
import gymgenerator.exercises.TricepsExercises;
import java.io.IOException;
import static java.lang.Math.E;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import static javafx.scene.input.KeyCode.E;

/**
 *
 * @author lukasz
 */
public class Plan {

    // TODO: Create something to build it.
    public enum Muscles {

        LEGS, BACK, CHEST, SHOULDERS, TRICEPS, BICEPS, TRAPS
    }
    /*
     Index of first array is  part of muscles
     0 - Legs
     1 - Back
     2 - Chest
     3 - Shoulders
     4 - Triceps
     5 - Biceps
     6 - Traps
     */
    /*  LegsExercises legs;
     BackExcercises back;
     ChestExcercises chest;
     ShoulderExcercises shoulders;
     TricepsExercises triceps;
     BicepsExercises biceps;
     Traps traps; */
    int LEGS = 0;
    int BACK = 1;
    int CHEST = 2;
    int SHOULDERS = 3;
    int TRICEPS = 4;
    int BICEPS = 5;
    int TRAPS = 6;

    public ObservableList<String> data;
    public TableView<List<String>> table;
    public List<String> partsOfMuscles;
    public List<ExercisesBase> MusclesExercises;
    //List of exercises for this plan
    public List<FXMLPlanGeneratorController.Exercise> exercise;
    public List<List<FXMLPlanGeneratorController.Exercise>> exerciseListBeta;
    List<Attributes> muscleExercises;
    private int trainingDays;
    public String info;
    private boolean gym;

    // goal - what we want to achive
    private final String goal;

    public Plan(String planType, int daysPerWeek, String getGoal,
            boolean g) throws IOException {
        this.gym = g;
        goal = getGoal;
        trainingDays = daysPerWeek;

        //
        exerciseListBeta = new ArrayList<>();

        //List of Exercise we are going to use in 1 day 
        MusclesExercises = new ArrayList<>();
        MusclesExercises.add(new LegsExercises());
        MusclesExercises.add(new BackExercises());
        MusclesExercises.add(new ChestExercises());
        MusclesExercises.add(new ShoulderExercises());
        MusclesExercises.add(new TricepsExercises());
        MusclesExercises.add(new BicepsExercises());
        MusclesExercises.add(new TrapsExercises());
        partsOfMuscles = new ArrayList<>();
        partsOfMuscles.add("Legs");
        partsOfMuscles.add("Back");
        partsOfMuscles.add("Chest");
        partsOfMuscles.add("Shoulders");
        partsOfMuscles.add("Triceps");
        partsOfMuscles.add("Biceps");
        partsOfMuscles.add("Traps");
        exercise = new ArrayList();

        // Creating instance of TableVies List where have List of string.
        // whre every index of tableView is another day of training
        // and String list is exercises.
        table = new TableView();
        data = FXCollections.observableArrayList();
        if (goal.equals("Gain")) {
            info = new String("Rest between series: 3+ min.");

        } else if (goal.equals("Power")) {
            info = new String("Break between series : Less than a minute");
        } else {
            info = new String("Rest between sereies : ~2 min");
        }

        // Tworzenie planu w zależności od typu treningu
        if (trainingDays > 2) {
            createSplit();
            //CreateHit;

        } else if (trainingDays <= 2) {

            createFBW();
        }
    }

    private void createSplit() {
        int i = 0;
        int[] days = null;
        // In tab i have parts of muscle in List
        int[] tab = {2, 4, 1, 5, 0, 3, 6};
        List<List<Integer>> partsPerDay = new ArrayList<>();
        //TODO: CHANGE tab[] for 2d List<day<parts>>
        switch (trainingDays) {
            case 3:
                days = new int[]{1, 3, 5};
                partsPerDay.add(new ArrayList<>(Arrays.asList(CHEST, TRICEPS)));
                partsPerDay.add(new ArrayList<>(Arrays.asList(BACK, BICEPS)));
                partsPerDay.add(new ArrayList<>(Arrays.asList(LEGS, SHOULDERS)));

                break;
            case 4:
                days = new int[]{1, 2, 3, 5};
                partsPerDay.add(new ArrayList<>(Arrays.asList(CHEST, BICEPS)));
                partsPerDay.add(new ArrayList<>(Arrays.asList(BACK)));
                partsPerDay.add(new ArrayList<>(Arrays.asList(SHOULDERS, TRICEPS)));
                partsPerDay.add(new ArrayList<>(Arrays.asList(LEGS)));
                break;
            case 5:
                days = new int[]{1, 2, 3, 4, 5};
                partsPerDay.add(new ArrayList<>(Arrays.asList(CHEST)));
                partsPerDay.add(new ArrayList<>(Arrays.asList(BACK)));
                partsPerDay.add(new ArrayList<>(Arrays.asList(TRICEPS)));
                partsPerDay.add(new ArrayList<>(Arrays.asList(BICEPS)));
                partsPerDay.add(new ArrayList<>(Arrays.asList(LEGS, SHOULDERS)));

                break;
                
        }

        int range;
        Random generator = new Random();
        ExercisesBase tmp;

        for (int x = 0; x < days.length/*partsPerDay.size()*/; x++) { // LOOP FOR DAYS
            // new List for new day of exercise
            exerciseListBeta.add(new ArrayList<>());
            for (int y = 0; y < partsPerDay.get(x).size(); y++) {
                // LOOP FOR PARTS
                int numberOfExercises = 3;
                if (MusclesExercises.get(partsPerDay.get(x).get(y)).bigPart) {
                    numberOfExercises = 4;
                } else {
                    numberOfExercises = 3;
                }
                for (int z = 0; z < numberOfExercises; z++) { // LOOP FOR NUMBER OF EXERCISES
                    int power = 0;
                    String ID = null;
                    int series = 0;
                    int repeats = 0;
                   
                    if (goal.equals("Power"))
                        repeats = 5;
                    else if (goal.equals("Power"))
                        repeats = 5;
                    else
                        repeats = 12;

                    boolean used = false;
                    do {
                        System.out.println("TEST !!!");
                        power = 0;
                        //Getting range of exercises for part of muscles
                        range = generator.nextInt(MusclesExercises.get(partsPerDay.get(x).get(y)).exercises.size());
                        tmp = (ExercisesBase) MusclesExercises.get(partsPerDay.get(x).get(y));
                        if (!tmp.isUsed) {
                            power = (int) tmp.exercises.get(range).muscles.get(partsOfMuscles.get(partsPerDay.get(x).get(y)));
                            ID = tmp.exercises.get(range).ID;
                            used = tmp.exercises.get(range).isUsed;
                            System.out.println("ID: " + ID);
                            System.out.println("Power: " + power);
                            if (!gym) {
                                if (tmp.exercises.get(range).gym) {
                                    System.out.println("SSS");
                                    power = 0;
                                }
                            }
                        }

                    } while (power != 10);                 
                    System.out.println("Outuje exercise: " + MusclesExercises.get(partsPerDay.get(x).get(y)).exercises.get(range).ID);
                    MusclesExercises.get(partsPerDay.get(x).get(y)).exercises.get(range).isUsed = true;
                    if (tmp.bigPart) {
                        series = 4;
                    } else {
                        series = 3;
                    } try{
                       exerciseListBeta.get(x).add(new Exercise(ID, String.valueOf(series), String.valueOf(repeats), days[x]));
                    }
                    catch (NullPointerException e)
                    {
                        System.out.println(e);
                    }
                   
                }
                //i++;
            }
            
        }
    }

    private void createFBW() {
        Random generator = new Random();
        boolean good = false;
        int[] days = null;
        switch (trainingDays) {
            case 1:
                days = new int[]{1};
                break;
            case 2:
                days = new int[]{1, 5};
                break;
        }
        for (int x = 0; x < days.length; x++) {
            System.out.println("DLA DNIA: "+days[x]);
            exerciseListBeta.add(new ArrayList<>());
            for (int i = 0; i < MusclesExercises.size(); i++) {
                System.out.println("Generic for : " + partsOfMuscles.get(i));
                ExercisesBase tmp = MusclesExercises.get(i);
                // Get range for exercises for this part of muscle
                int power;
                int range;
                String ID;
                int series;
                int repeats;
                do {
                    
                    range = generator.nextInt(tmp.exercises.size());
                    // Getting power for this part of muscle with this exercise 
                    // from rand.                              
                    power = (int) tmp.exercises.get(range).muscles.get(partsOfMuscles.get(i));
                    ID = tmp.exercises.get(range).ID;
                    // If exercise can be done without professional gym equipment.
                    if (gym == false) {
                        if (tmp.exercises.get(range).gym) {
                            power = 0;
                        }
                    }
                } while (power != 10);
                if (tmp.bigPart) {
                    series = 4;
                } else {
                    series = 3;
                }
                //TODO: if power or something else
                if (goal.equals("Look"))
                repeats = 12;
                else if (goal.equals("Power"))
                repeats = 5;
                else
                    repeats = 10;
                exerciseListBeta.get((exerciseListBeta.size() - 1)).add(new Exercise(ID, String.valueOf(series),
                        String.valueOf(repeats), days[x]));
                System.out.println("SSSQQQQQ");
            }
        }
    }
}
