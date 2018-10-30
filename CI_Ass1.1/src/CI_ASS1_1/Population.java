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
import java.util.ArrayList;
import java.util.List;

public class Population {

    ProjectConfig proj_conf[];

    public Population(int population_size, boolean initialise)
    {

        proj_conf = new ProjectConfig[population_size];

        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < population_size; i++)
            {
                ProjectManager newManager = new ProjectManager();
                proj_conf[i] = new ProjectConfig();
                proj_conf[i].generateIndividual(newManager);
                System.out.println("initial proj_config :"+proj_conf[i].getFitnessValue());
                saveProj(i, proj_conf[i]);
            }
        }
    }

    public void saveProj(int index, ProjectConfig proj) {
        proj_conf[index]=new ProjectConfig(proj);
    }

    public ProjectConfig getProj_Config(int index) {
        return proj_conf[index];
    }

    public void getAllFitness()
    {
        for (int i=0;i<populationSize();i++)
        {
            System.out.println("Individual gene ["+ i + "] = " + proj_conf[i].getFitnessValue());
         
        }
    }
    public ProjectConfig getFittest() {

        ProjectConfig fittest = proj_conf[0];
        int fittest_index = 0;
        // Loop through individuals to find fittest
        for (int i = 0; i < populationSize(); i++)
        {
            /*
            if(fittest.getPassStatus()==true)
            {
                if ((fittest.getEarnings() <= getProj_Config(i).getEarnings())&&( getProj_Config(i).getPassStatus()==true))
                {
                    fittest = getProj_Config(i);
                    System.out.println("The fittest is proj_config :"+i);
                }
            }
            else
            {
                fittest = getProj_Config(i);
            }*/
            //System.out.println("Fittest: "+fittest.getFitnessValue() + "Proj: " + proj_conf[i].getFitnessValue());
            if(fittest.getFitnessValue() < proj_conf[i].getFitnessValue())
            {
                fittest = proj_conf[i];
                fittest_index = i;
            }
        }
        //System.out.println("Fittest index is "+fittest_index);
        return fittest;
    }

    public int populationSize() {
        //System.out.println("Population size " + proj_conf.length);
        return proj_conf.length;
    }

    public double get_proj_earning(int index,int proj_index)
    {
        Project my_proj =(Project) proj_conf[index].getProject(proj_index);
        return my_proj.profit;

    }
}


