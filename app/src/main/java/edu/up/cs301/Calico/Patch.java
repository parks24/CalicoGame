package edu.up.cs301.Calico;

import androidx.annotation.NonNull;

import java.io.Serializable;


/**
 * The {@code Patch} class represents a single tile on the Calico game board.
 * Each patch has a pattern and color, and may optionally contain a cat, a button,
 * or serve as a goal tile.
 *
 * @author Joseph Early
 * @version 2025
 */

    /**
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

public class Patch implements Serializable
{
    protected static final long serialVersionUID = 170425;


    /** The pattern of this patch. */
    protected int patchPattern;

    /** The color of this patch. */
    protected int patchColor;

    /** Whether this patch currently has a cat on it. */
    protected boolean hasCat;


    /** Indicates whether this patch has a button. */
    protected boolean hasButton;

    /** Goal value */
    protected int goal;

    /** Indicates whether this patch has been selected. */
    protected boolean selectedPatch;


    /**
     * Default constructor that initializes a patch with default values.
     */
    public Patch()
    {
        patchPattern = 0;
        patchColor = 0;
        hasCat = false;
        hasButton = false;
        selectedPatch = false;
    }

    /**
     * Constructor that initializes the patch with a specific pattern and color.
     *
     * @param _patchPattern The pattern of the patch.
     * @param _patchColor The color of the patch.
     */
    public Patch(int _patchPattern, int _patchColor)
    {
        patchPattern = _patchPattern;
        patchColor = _patchColor;
    }

    /**
     * Copy constructor that creates a new patch by copying attributes from another.
     *
     * @param other The patch to copy from.
     */
    public Patch(Patch other)
    {
        this.patchPattern = other.patchPattern;
        this.patchColor = other.patchColor;
        this.hasCat = other.hasCat;
        this.hasButton = other.hasButton;
        this.goal = other.goal;
    }

    /**
     * Gets the patch pattern.
     *
     * @return The patch pattern.
     */
    public int getPatchPattern(){
        return patchPattern;
    }

    /**
     * Gets the patch color.
     *
     * @return The patch color.
     */
    public int getPatchColor(){
        return patchColor;
    }

    /**
     * Indicates whether this patch is not a goal patch.
     *
     * @return Always returns true. Overridden in subclass.
     */
    public boolean isNotGoal(){return true;}

    /**
     * Overridden in subclass
     *
     * @param x A 2D int array.
     * @return 0
     */
    public int getGoalPatchPoints(int[][] x) { return 0;}


    /**
     * Marks this patch as selected.
     */
    public void selectPatch()
    {
        selectedPatch = true;
    }

    /**
     * Gets the goal value of this patch.
     *
     * @return The goal value.
     */
    public int getGoal()
    {
        return goal;
    }

    /**
     * Sets the goal value for this patch.
     *
     * @param _goal The goal value to set.
     */
    public void setGoal(int _goal)
    {
        goal = _goal;
    }


    /**
     * Returns a string representation of the patch including pattern, color, and goal.
     *
     * @return A string representing this patch.
     */
    @NonNull
    @Override
    public String toString() {
        return "Pattern: "+patchPattern+", Color: "+patchColor+" goal: "+goal;
    }
}

/**
 External Citation
 Date: 23 April 2025
 Problem: Javadocs
 Resource: https://chatgpt.com
 Solution: Used chatGPT to generate Javadocs
 */