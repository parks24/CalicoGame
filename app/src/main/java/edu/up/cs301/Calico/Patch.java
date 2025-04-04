package edu.up.cs301.Calico;

public class Patch{
    protected int patchPattern;
    protected int patchColor;
    protected boolean hasCat;
    protected boolean hasButton;

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
}