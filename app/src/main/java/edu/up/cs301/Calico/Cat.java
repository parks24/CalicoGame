package edu.up.cs301.Calico;

import java.util.ArrayList;

public class Cat
{
    //Instance Variables
    protected int name;
    protected int[] patterns = new int[2]; //Patterns on tile
    protected int points;


    //default constructor
    public Cat()
    {
        name = 0;
        patterns[0] = 0;
        patterns[1] = 0;
        points = 0;
    }

    //cat creator
    public Cat(int cat, int[] patterns)
    {
        //stores patterns for each cat
        this.patterns[0] = patterns[0];
        this.patterns[1] = patterns[1];
        name = cat;

        //each integer will correspond to a cat below
        //cuddles is cat 1
        if (cat == 1)
        {
            points = 3;
        }
        //smokey is cat 2
        else if(cat == 2)
        {
            points = 5;
        }
        //stripe is cat 3
        else if(cat == 3)
        {
            points = 7;
        }
        else
        {
            points = 0;
        }
    }

    //copy constructor
    public Cat(Cat other)
    {
        this.points=other.points;
        this.name = other.name;
        this.patterns[0] = other.patterns[0];
        this.patterns[1] = other.patterns[1];
    }


    //returns true if the cat score should be increased
    public boolean addCat(ArrayList<int[]> patches, int pattern)
    {
        if (pattern == patterns[0] || pattern == patterns[1]) {
            //cuddles is cat 1
            if (name == 1) {
                return patches.size()>=3;
            }
            //smokey is cat 2
            else if (name == 2) {
                return patches.size()>=4;
            }
            //stripe is cat 3
            else if (name == 3) {
                return patches.size() >= 5;
            }
        }
        return false;
    }
}
