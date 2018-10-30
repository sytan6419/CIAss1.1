/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CI_ASS1_1;

import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author senyan
 */
public class Main {
    
    public static void main(String[] args) {
        String file_name = "result.txt";
        for(int iteration = 0 ;iteration<20;iteration++)
        {
            try{
                FileWriter fw = new FileWriter(file_name,true);
                Population pop = new Population(6, true);
                fw.write("Interation : "+iteration+"\n");
                fw.write("Initial Fitness: " + pop.getFittest().getEarnings()+"\n");
                System.out.println("Initial Fitness: " + pop.getFittest().getEarnings()+"\n");
                GA myGA = new GA();
                for (int i = 0; i < 10; i++) {
                    pop = myGA.evolvePopulation(pop);
                    fw.write("fitness after" + i + " loops: " + pop.getFittest().getEarnings()+"\n");
                    System.out.println("fitness after" + i + " loops: " + pop.getFittest().getEarnings());
                    pop.getAllFitness();
                }
                fw.write("After evolution :" + pop.getFittest().getEarnings()+"\n");
                System.out.println("After evolution :" + pop.getFittest().getEarnings());
                fw.write("Orientation of proj ");
                System.out.print("Orientation of proj ");
                for(int i = 0; i < pop.getFittest().getProjList().size();i++)
                {
                    fw.write(pop.getFittest().getProject(i).present+" ");
                    System.out.print("Proj " + (i+1) + ": " + pop.getFittest().getProject(i).present+"\n");
                }
                fw.write("\n-------------------------------------------------------\n");
                System.out.println(" ");
                System.out.println("----------------------------------------------------------------------------------------");
                fw.close();
            }catch(IOException ioe)
            { System.err.println("IOException "+ ioe.getMessage());
            }
        }
    }
}
