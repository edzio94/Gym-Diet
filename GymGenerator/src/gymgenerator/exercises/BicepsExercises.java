/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymgenerator.exercises;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lukasz
 */
public class BicepsExercises extends ExercisesBase {

    public BicepsExercises() {
        bigPart = false;
        exercises = new ArrayList<>();
        exercises.add(new Attributes("Uginanie", 0, 0, 0, 0, 0, 10, 0, false));
        exercises.add(new Attributes("Uginanie supinacja", 0, 0, 0, 0, 0, 10, 0, false));
        exercises.add(new Attributes("Podciąganie podchwyt", 0, 5, 0, 0, 0, 10, 0, false));
        exercises.add(new Attributes("Uginanie nadchwyt", 0, 0, 0, 0, 0, 10, 0, false));
        exercises.add(new Attributes("Siódemkowanie", 0, 0, 0, 0, 0, 10, 0, false));
        exercises.add(new Attributes("Podciąganie Trójkat", 0, 4, 0, 0, 0, 10, 0, false));
        exercises.add(new Attributes("Młotki", 0, 0, 0, 0, 0, 10, 0, false));
        exercises.add(new Attributes("Atlas dół", 0, 0, 0, 0, 0, 10, 0, true));
    }
}
