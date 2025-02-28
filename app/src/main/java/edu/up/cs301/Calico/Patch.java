package edu.up.cs301.Calico;

public class Patch{
    protected int patchPattern;
    protected int patchColor;
    protected boolean hasCat;
    protected boolean hasButton;
    public Patch()
    {
        patchPattern = 0;
        patchColor = 0;
        hasCat = false;
        hasButton = false;
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
}