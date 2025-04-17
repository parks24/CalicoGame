package edu.up.cs301.Calico;

import java.io.Serializable;
import java.util.Arrays;

public class PlayerScore implements Serializable {
    protected int[] buttonCount = new int[7];
    protected static final long serialVersionUID = 170425;
    protected int[] catCount = new int[3];

    //default constructor
    //initilizes all catCounts and buttonCount to 0;
    public PlayerScore()
    {
        Arrays.fill(buttonCount, 0);
        Arrays.fill(catCount,0);
    }

    //copy constructor
    public PlayerScore(PlayerScore other)
    {
        System.arraycopy(other.buttonCount, 0, this.buttonCount, 0, buttonCount.length);
        System.arraycopy(other.catCount, 0, this.catCount, 0, catCount.length);
    }

    public int getPlayerScore(Cat[] cats)
    {
        return 3*Arrays.stream(this.buttonCount).sum() + cats[0].points*catCount[0] +
                cats[1].points*catCount[1] + cats[2].points*catCount[2];
    }

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

    public void increaseCatCount(int catNumber)
    {
        this.catCount[catNumber]++;
    }
}
