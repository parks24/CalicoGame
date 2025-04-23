package edu.up.cs301.Calico;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The {@code Cat} class represents a cat in the Calico game.
 * Each cat has a name (ID), a pair of pattern preferences, and an associated point value.
 * The cat awards points based on specific pattern matches found in the game.
 * This class implements {@code Serializable} to allow network play.
 *
 * @author Luca Sburlino
 * @version 2025
 */
public class Cat implements Serializable
{
    //Instance Variables
    protected static final long serialVersionUID = 170425;
    protected int name;
    protected int[] patterns = new int[2]; //Patterns on tile
    protected int points;


    /**
     * Default constructor.
     * Initializes the cat with default values:
     * name = 0, patterns = [0, 0], points = 0.
     */
    public Cat()
    {
        name = 0;
        patterns[0] = 0;
        patterns[1] = 0;
        points = 0;
    }

    /**
     * Constructs a {@code Cat} with a specified name and pattern preferences.
     * Also assigns a point value based on the cat's name.
     *
     * @param cat the identifier for the cat (1 = Cuddles, 2 = Smokey, 3 = Stripe)
     * @param patterns an array of two integers representing preferred patterns
     */
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

    /**
     * Copy constructor.
     * Creates a new {@code Cat} by copying another {@code Cat} instance.
     *
     * @param other the {@code Cat} instance to copy
     */
    public Cat(Cat other)
    {
        this.points=other.points;
        this.name = other.name;
        this.patterns[0] = other.patterns[0];
        this.patterns[1] = other.patterns[1];
    }


    /**
     * Determines if the cat's score should be increased based on pattern matches.
     *
     * @param patches a list of pattern arrays to check against the cat's preferences
     * @param pattern the specific pattern to check
     * @return {@code true} if the cat's point conditions are met, {@code false} otherwise
     */
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

    /**
     * Returns the pattern preferences of the cat.
     *
     * @return an array containing two integers representing preferred patterns
     */
    public int[] getPatterns() {
        return patterns;
    }
}

/**
 External Citation
 Date: 23 April 2025
 Problem: Javadocs
 Resource: https://chatgpt.com
 Solution: Used chatGPT to generate Javadocs
 */