package edu.up.cs301.Calico;

import java.io.Serializable;
import java.util.Arrays;

/**
 * The {@code PlayerScore} class tracks a player's score-related data in the game.
 * This includes how many of each type of button and cat the player has collected.
 * It also provides logic to calculate the final score based on collected elements.
 * @author Luca Sburlino
 * @version 2025
 */
public class PlayerScore implements Serializable {
    /** Stores the count of each button collected. Index 0 is for the rainbow button. */
    protected int[] buttonCount = new int[7];
    protected static final long serialVersionUID = 170425;

    /** Stores the count of cats collected, indexed by cat ID. */
    protected int[] catCount = new int[3];

    /**
     * Default constructor.
     * Initializes all button and cat counts to 0.
     */
    public PlayerScore()
    {
        Arrays.fill(buttonCount, 0);
        Arrays.fill(catCount,0);
    }

    /**
     * Copy constructor.
     * Creates a deep copy of another {@code PlayerScore} instance.
     *
     * @param other the {@code PlayerScore} instance to copy
     */
    public PlayerScore(PlayerScore other)
    {
        System.arraycopy(other.buttonCount, 0, this.buttonCount, 0, buttonCount.length);
        System.arraycopy(other.catCount, 0, this.catCount, 0, catCount.length);
    }

    /**
     * Calculates the player's total score based on collected buttons and cats.
     * - Each button is worth 3 points.
     * - Each cat's score contribution is determined by its point value
     *
     * @param cats an array of {@code Cat} objects used to determine scoring
     * @return the total player score
     */
    public int getPlayerScore(Cat[] cats)
    {
        return 3*Arrays.stream(this.buttonCount).sum() + cats[0].points*catCount[0] +
                cats[1].points*catCount[1] + cats[2].points*catCount[2];
    }

    /**
     * Increases the count of a specific button type.
     * If the player has at least one of each non-rainbow button (types 1-6),
     * the rainbow button (index 0) is activated and set to 1.
     *
     * @param buttonNumber the index of the button (0–6) to increment
     */
    public void increaseButtonCount(int buttonNumber)
    {
        buttonCount[buttonNumber]++;
        boolean rainbow = true;
        for(int k = 1; k < buttonCount.length; k++)
        {
            rainbow = rainbow && buttonCount[k]>0;
        }
        if(rainbow) buttonCount[0] = 1;
    }

    /**
     * Increases the count for a specific cat.
     *
     * @param catNumber the index of the cat (0–2) to increment
     */
    public void increaseCatCount(int catNumber)
    {
        this.catCount[catNumber]++;
    }
}

/**
 External Citation
 Date: 23 April 2025
 Problem: Javadocs
 Resource: https://chatgpt.com
 Solution: Used chatGPT to generate Javadocs
 */