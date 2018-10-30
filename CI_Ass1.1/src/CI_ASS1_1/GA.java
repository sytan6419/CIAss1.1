/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CI_ASS1_1;

/**
 *
 * @author senyan
 */
import java.util.Random;

public class GA {

    private static final double mutationRate = 0.15;
    private static final int tournamentSize = 2;
    private static final boolean elitism = true;

    // Evolves a population over one generation
    public Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.populationSize(), false);

        // Keep our best individual if elitism is enabled
        int projOffset = 0;
  
        if (elitism) {
            ProjectConfig prev_fittest = new ProjectConfig(pop.getFittest());
            newPopulation.saveProj(0, prev_fittest);
            projOffset = 1;
        }

        for(int i = projOffset; i < 6;i++) {

            // Select parents
            ProjectConfig parent1 = FPS(pop);
            ProjectConfig parent2 = FPS(pop);
            // Crossover parents
            ProjectConfig child = new ProjectConfig(uniformCrossOver(parent1, parent2));

            mutate(child);
            //System.out.println("fitness of mutated child :" +mutateChild.getFitnessValue());
            //child = mutate(child);
            
            //debugging purpose
            System.out.println("Child "+child.getFitnessValue() + " Parent1: "+parent1.getFitnessValue() + " Parent2: "+parent2.getFitnessValue());
            // Generational Based
            // Add child to new population
            newPopulation.saveProj(i, child);
            //end of generational based
            
            // if child fitter than parent1, keep child and replace 1 of the parent
            // else, discard child.
            /*
            if (child.getFitnessValue() > (parent1.getFitnessValue()))
            {
                if (parent2.getFitnessValue() > child.getFitnessValue())
                {
                    newPopulation.saveProj(i, parent2);
                }
                else
                {
                    newPopulation.saveProj(i, child);
                }
            }
            else
            {
                if (parent1.getFitnessValue() > parent2.getFitnessValue())
                {
                    newPopulation.saveProj(i, parent1);
                }
                else
                {
                    newPopulation.saveProj(i, parent2);
                }
            }
            *///end of steady-state model
        }

        return newPopulation;
    }

    private ProjectConfig FPS(Population pop)
    {
        int TotalPopFitness = 0;
        double accumulatefitness = 0.0;
        int index = 0;
        Random rand = new Random();
        double AnchorPoint = rand.nextDouble();
        for (int i = 0; i < pop.populationSize(); i++)
        {
            ProjectConfig proj_conf = pop.getProj_Config(i);
            TotalPopFitness += proj_conf.getFitnessValue();
        }
        for (int j = 0; j < pop.populationSize(); j++)
        {
            ProjectConfig proj_conf = pop.getProj_Config(j);
            accumulatefitness += proj_conf.getFitnessValue();
            if ((AnchorPoint - (accumulatefitness / TotalPopFitness)) <= 0)
            {
                index = j;
                break;
            }
        }
        ProjectConfig returnIndividual = pop.getProj_Config(index);
        return returnIndividual;
    }

    private ProjectConfig OnePointcrossover(ProjectConfig parent1, ProjectConfig parent2)
    {
        ProjectConfig proj_conf = new ProjectConfig();

        // Get start and end sub tour positions for parent1's tour
        //int startPos = (int) (Math.random() * parent1.getProj_conf_size());
        //int endPos = (int) (Math.random() * parent1.getProj_conf_size());
        Random rand = new Random();
        int point = rand.nextInt(5);
        Project proj;
        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < proj_conf.getProj_conf_size(); i++) {
            if(i<point)
            {
                proj = new Project(parent1.getProject(i));
                proj_conf.setProject(i, proj);
            }
            else
            {
                proj = new Project(parent2.getProject(i));
                proj_conf.setProject(i, proj);
            }
        }

        return proj_conf;
    }

    private ProjectConfig uniformCrossOver(ProjectConfig parent1, ProjectConfig parent2)
    {
        ProjectConfig proj_conf = new ProjectConfig();
        Project proj;
        
        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < proj_conf.getProj_conf_size(); i++) {
            Random rand = new Random();
            double point = rand.nextDouble();
            if(point > 0.5)
            {
                proj = new Project(parent1.getProject(i));
                proj_conf.setProject(i, proj);
            }
            else
            {
                proj = new Project(parent2.getProject(i));
                proj_conf.setProject(i, proj);
            }
        }
        return proj_conf;
    }
 
    private void mutate(ProjectConfig preMutate)
    {
        Random rand = new Random();
        double pM = 0;

        for(int i = 0;i<preMutate.getProj_conf_size();i++)
        {
            pM=rand.nextDouble();
            if(pM < mutationRate)
            {
                if(preMutate.getProject(i).present == true)
                {
                    preMutate.getProject(i).present = false;
                }
                else {
                    preMutate.getProject(i).present = true;
                }
            }
        }
    }
        /*Project_Config postMutate = new Project_Config();
        Project proj;
        Random rand = new Random();
        double pM = 0;

        for(int i = 0 ;i < preMutate.getProj_conf_size();i++)
        {
           pM = rand.nextDouble();
           proj = preMutate.getProject(i);
           if(pM < mutationRate)
           {
               if(proj.present == true)
               {
                   proj.present = false;
               }
               else {
                   proj.present = true;
               }
           }
           postMutate.setProject(i,proj);
        }

        return postMutate;
    }*/
}
