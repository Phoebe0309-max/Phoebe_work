public class Box
{
    /**
     * A box class which holds bytecoins amounts 
     * and have open/unopen states as true/false.
     */
    private int amount;
    private boolean opened;

    /**
     * initializing box amount to 0 and open to false
     * default constructor
     */
    public Box()
    {
        //default 
        amount = 0;
        opened = false;
    }

    /**
     * initializing box with a given amount and state
     * non-default constructor
     */
    public Box(int amount)
    {
        this.amount = amount;
        opened = false;
    }
    
    /**
     * get the amount in the box
     */
    public int getAmount()
    {
        return amount;
    }

    /**
     * check if the box is opened
     */
    public boolean isOpened()
    {
        return opened;
    }

    /**
     * set the amount in the box
     */
    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    /**
     * set the state of the box
     */
     public void setOpened(boolean opened)
     {
        this.opened = opened;
     }

    /**
     * open the box
     */
     public void open()
     {
        opened = true;
     }
    
    /**
     * returns a string
     */
     public String toString()
     {
        return "Amount: " + amount + ", Opened: " + opened;
     }


}
