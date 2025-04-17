package edu.up.cs301.Calico;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Patch implements Serializable
{
    protected static final long serialVersionUID = 170425;
    protected int patchPattern;
    protected int patchColor;
    protected boolean hasCat;
    protected boolean hasButton;
    protected int goal;
    protected boolean selectedPatch;

    /*
        Number Codes

        Color Code:
            Blank: 0
            Red: 1
            Orange: 2
            Yellow: 3
            Green: 4
            Blue: 5
            Pink: 6

        Pattern Code:
            Blank: 0
            dots: 1
            fractal: 2
            heart: 3
            lines: 4
            smile: 5
            star: 6

            goal: 7
     */

    public Patch()
    {
        patchPattern = 0;
        patchColor = 0;
        hasCat = false;
        hasButton = false;
        selectedPatch = false;
    }

    public Patch(int _patchPattern, int _patchColor)
    {
        patchPattern = _patchPattern;
        patchColor = _patchColor;
    }
    public Patch(Patch other)
    {
        this.patchPattern = other.patchPattern;
        this.patchColor = other.patchColor;
        this.hasCat = other.hasCat;
        this.hasButton = other.hasButton;
        this.goal = other.goal;
    }

    public int getPatchPattern(){
        return patchPattern;
    }
    public int getPatchColor(){
        return patchColor;
    }
    public boolean isNotGoal(){return true;}
    public int getGoalPatchPoints(int[][] x) { return 0;}
    public void selectPatch()
    {
        selectedPatch = true;
    }
    public int getGoal()
    {
        return goal;
    }
    public void setGoal(int _goal)
    {
        goal = _goal;
    }

    @NonNull
    @Override
    public String toString() {
        return "Pattern: "+patchPattern+", Color: "+patchColor+" goal: "+goal;
    }
}