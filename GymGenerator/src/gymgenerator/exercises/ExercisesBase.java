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
public class ExercisesBase {
    // Big part of muscle.
    public boolean bigPart;
    // Is workout already used in plan.
    public boolean isUsed;
    // List of exercises for muscle. 
   public List<Attributes> exercises;
    public ExercisesBase(){
    exercises = new ArrayList();
    };
}
