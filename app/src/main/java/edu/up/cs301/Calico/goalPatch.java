package edu.up.cs301.Calico;

public class GoalPatch extends Patch
{
    //Instance Variables
    protected int goal;

    //goalPatch
    public GoalPatch()
    {
        super();
        goal = 0;
    }

    //Overloaded Constructor
    public GoalPatch(int _goal)
    {
        super();
        goal = _goal;
    }


    //Getter and Setter Methods
    public int getGoal()
    {
        return goal;
    }

    public void setGoal(int _goal)
    {
        goal = _goal;
    }

}
