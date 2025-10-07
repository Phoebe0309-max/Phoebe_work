/**
 * A class holds a player's name and box choice 
 */

public class Player
{
    private String name;
    private Box playerBox;

    /**
     * Default constructor 
     * Initializes player with default name and empty box.
     */
    public Player()
    {
        name = "Default Player";
        playerBox = new Box();
    }

    /**
     * Non - default constructor 
     */
    public Player(String name, Box playerBox)
    {
        this.name = name;
        this.playerBox = playerBox;
    }

    /**
     * Gets the player's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the player's box choice.
     */
    public Box getPlayerBox()
    {
        return playerBox;
    }

    /**
     * Sets the player's name.
     */
    public void setName(String name)
    {
        if (name.trim().equals(name) && name.length() >= 4 && name.length() <= 10)
        {
            // fields, parameter
            this.name = name;
        }
        else
        {
            this.name = "Default Player";
        }
        
    }

    /**
     * sets the player's box choice.
     */
    public void setPlayerBox(Box playerBox)
    {
        this.playerBox = playerBox;
    }

    /**
     * Returns a String.
     */
    public String toString()
    {
        return "Name: " + name + ", Box: " + playerBox.toString();
    }
}
