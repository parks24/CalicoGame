package edu.up.cs301.Calico;

public class Patch
{
    protected int patchPattern;
    protected int patchColor;

    public Patch()
    {
        patchPattern = 0;
        patchColor = 0;
    }

    public Patch(int _patchPattern, int _patchColor)
    {
        patchPattern = _patchPattern;
        patchColor = _patchColor;
    }
}
