import java.util.Scanner;
import java.util.Random;

/**
 * Players choose one box to keep, open others in rounds, 
 * and accept or reject the banker's offer and get their prize
 */
public class Game
{
    //fields
    private Player player;
    private Box[] gameBoxes;
    private int gamesPlayed;
    private double totalWinning;
    private double maxWinning;
    

    /**
     * Default constructor.
     * Initializes a player, boxes, and gamesplayed, total winning, max winning.
     */
    public Game()
    {
        player = new Player();
        gameBoxes = new Box[12];
        gamesPlayed = 0;
        totalWinning = 0;
        maxWinning = 0;

    }

    /**
     * non-Default constructor.
     */
    public Game(Player player, Box[] gameBoxes)
    {
        this.player = player;
        this.gameBoxes = gameBoxes;
        this.gamesPlayed = 0;
        this.totalWinning = 0;
        this.maxWinning = 0;
        
    }

    /**
     * player choose the player's box
     */
    public void chooseBox(Scanner scanner)
    {
        displayBoxes();
        boolean validChoice = false;

        do{
            System.out.println("Choose your box to keep: ");
            // check if choice is integar
            if(scanner.hasNextInt())
            {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= 12)
                {
                    player.setPlayerBox(gameBoxes[choice - 1]);
                    System.out.println("You have Chosen Box " + choice + " to keep!");
                    validChoice = true;
                }
                else
                {
                    System.out.println("Please enter a number between 1 and 12.");
                }
            }
            else
            {
                System.out.println("Please enter a valid number between 1-12.");
                scanner.nextLine();

            }
        
        }while(!validChoice);
    }

    /**
     * Display boxes and their state
     */
    public void displayBoxes()
    {
        System.out.println("Boxes status: ");
        for (int i = 0; i < 12; i ++)
        {
            if (gameBoxes[i] == player.getPlayerBox())
            {
                System.out.println("Box " + (i + 1) + ": ---" + player.getName() + "'s box---" );
            }
            else if(gameBoxes[i].isOpened())
            {
                System.out.println("Box " + (i + 1) + ": opened (" + gameBoxes[i].getAmount() + ")");
                
            }
            else
            {
                System.out.println("Box " + (i + 1) + ": " + "?");
            }
        }
    }

    /**
     * Display final results
     */
    public void displayFinalResult()
    {
        //Expilicit turning integar to double
        double aveWinning  = (double) totalWinning / gamesPlayed;
        System.out.println("------Final Game Results--------");
        System.out.println("Games Played: " + gamesPlayed);
        System.out.println("Max Prize " + maxWinning + " ByteCoins");
        System.out.println("Average Winning " + aveWinning + " ByteCoins");
        System.out.println("Thanks for Playing This Game!");

    }

    /**
     * Display game results
     */
    public void displayGameResult()
    {
        System.out.println("----Game Result----");
        int playerBoxAmount = player.getPlayerBox().getAmount();
        System.out.println("Your box have " + playerBoxAmount + " ByteCoins");
        System.out.println("You have won " + playerBoxAmount + " ByteCoins");
        
        // update the variable in the Result
        gamesPlayed = gamesPlayed + 1;
        totalWinning = totalWinning + playerBoxAmount;
        if (playerBoxAmount > maxWinning)
        {
            maxWinning = playerBoxAmount;
        }

        //Show the remaining box
        for (int i = 0; i < 12; i ++)
        {
            boolean unOpened = !gameBoxes[i].isOpened();
            boolean notPlayerBox = gameBoxes[i] != player.getPlayerBox();
            if (unOpened && notPlayerBox)
            {
                int amount = gameBoxes[i].getAmount();
                System.out.println("The remaining box have: " + amount + " ByteCoins");
            }
        }
    }

    /**
     * Display game rules
     */
    public static void displayGameRule()
    {
        System.out.println("Game rules");
        System.out.println("- Player name contains 4-10 characters ");
        System.out.println("- Player name does not start or end with space");  
        System.out.println("- There are 12 boxes, each box contains a ByteCoin amount"); 
        System.out.println("- All boxes are closed at the beginning of the game");  
        System.out.println("- When the game begins, the player will choose and open the boxes");  
        System.out.println("- There are 4 rounds: ");  
        System.out.println("  - Round one: the player choose 4 boxes to open");  
        System.out.println("  - Round two: the player choose 3 boxes to open");  
        System.out.println("  - Round three: the player choose 2 boxes to open");  
        System.out.println("  - Round four: the player choose 1 boxes to open");  
        System.out.println("- At the end of each round, the banker will make an offer");  
        System.out.println("- The player will decide to take the deal or not");
        System.out.println("- If the player does not accept the banker's offers, the amount of their own box  will be their prize");  
    }

    /**
     * Display welcome mesage
     */
    public static void displayWelcomeMessage()
    {
        System.out.println("Welcome to the Array of Fortune game");
    }

    /**
     * get the array of boxes
     */
    public Box[] getGameBoxes()
    {
        return gameBoxes;
    }

    public Player getPlayer()
    {
        return player;
    }

    /**
     * Initializes 12 empty boxes
     * and randomly assigning ByteCoin amounts.
     */
    public void initializeBoxes()
    {
        int[] amounts = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};

        for (int i = 0; i < 12; i++)
        {
            //generate 12 empty boxes
            gameBoxes[i] = new Box();
        }
        Random r = new Random();
        for (int i = 0; i < 12; i++)
        {
            //Declaration + generate random number between 1-12 as the index used to get the number 
            int randomIndex = r.nextInt(12);

            // if it's selected. choose another one 
            while (amounts[randomIndex] == 0)
            {
                //a new value
                randomIndex = r.nextInt(12);
            }
            //put this amount into each box
            gameBoxes[i].setAmount(amounts[randomIndex]);
            // used amount changes to 0, 0 is a symbol of this amount is used

            amounts[randomIndex] = 0;
        }

    }

    /**
     * ask if player want to play another round 
     */
    public boolean isPlayAgain(Scanner scanner)
    {

        while(true){
            System.out.println("Do you want to play another round?");
            String decision = scanner.nextLine().trim().toLowerCase();

            if (decision.equals("yes") || decision.equals("y"))
            {
                return true;
            }
            else if (decision.equals("no") || decision.equals("n"))
            {
                return false;
            }
            else 
            {
                System.out.println("Please enter 'yes' or 'no'.");
            }
           }
    }

    /**
     * the entry of game.
     */
    public static void main(String[] args)
    {
        Game game = new Game();
        game.runGame();
    }

    /**
     * banker make an offer
     * and ask the player want to accept it or not
     */
    public boolean makeBankOffer(Scanner scanner)
    {
        // The boxes that haven't been opened and their amount
        int totalAmount = 0;
        int boxUnopened = 0;
        for(int i = 0; i < 12; i ++)
        {
            if(!gameBoxes[i].isOpened())
            {
                totalAmount = totalAmount + gameBoxes[i].getAmount();
                boxUnopened ++;
            }
        }
        // count the banker's offer
        double average = totalAmount / boxUnopened;
        Random r = new Random();
        double percentage = Math.random() * 0.4 + 0.8;
        int offer = (int)(average * percentage);
        System.out.println("------Banker's Offer------");
        System.out.println("The Banker offers " + offer + " ByteCoins for your box!");

        //Deal or No Deal
        String decision;
        boolean validDecision;

        do
        {
            validDecision = false;
            System.out.println("Do you take the deal or not?(Deal/No deal): ");
            decision = scanner.nextLine().toLowerCase();

            //equals is used for objective type
            if (decision.equals("deal"))
            {
                System.out.println("Deal! You have won " + offer + " BtyeCoins!");
                gamesPlayed += 1;
                totalWinning += offer;
                if(offer > maxWinning)
                {
                    maxWinning = offer;
                }
                return true;
            }
            else if (decision.equals("no deal"))
            {
                System.out.println("No deal! Let's play the next round!");
                return false;
            }
            else
            {
                System.out.println("Please enter 'Deal' or 'No deal'.");
            }
        }while (! validDecision);
        return false;
    } 

    /**
     * player choose to open a box which isn't opened
     */
    public void openABox(int boxNumber)
    {
        if (boxNumber >= 0 && boxNumber <= 11)
        {
            //the box has to be not opened before
            if(!gameBoxes[boxNumber].isOpened())
            {
                gameBoxes[boxNumber].open();
                System.out.println("Box " + (boxNumber + 1) + " contains " + gameBoxes[boxNumber].getAmount() + " ByteCoins!");
            }   
        }
    }

    /**
     * go through everything if a round starts
     */
    public boolean playARound(int roundNumber, int boxToOpen, Scanner scanner)
    {
        System.out.println("-----ROUND " + roundNumber + "-----");
        System.out.println("Please Open " + boxToOpen + " boxes");
        //display current the states of boxes
        displayBoxes();
        // player choose which box to open 
        for(int i = 1; i < (boxToOpen + 1); i ++)
        {        
            boolean validChoice = false;

            do
            {
                System.out.println("Please choose " + i + " box to open(1-12): ");
                if (scanner.hasNextInt())
                {
                    int playerChoice = scanner.nextInt();
                    // remove the "\n"
                    scanner.nextLine();

                    if (playerChoice <= 12 && playerChoice >= 1)
                    {
                        //box index is 1 less than the choice between1-12
                        int boxI = playerChoice - 1;

                        if (gameBoxes[boxI].isOpened())
                        {
                            System.out.println("This box is opened, choose another one.");
                        }
                        else if (gameBoxes[boxI] == player.getPlayerBox())
                        {
                            //player box cannot be opened until the game's over
                            System.out.println("It's your own box, choose another one.");
                        }
                        else
                        // valid choice
                        {
                            validChoice = true;
                            openABox(boxI);
                        }

                    }
                    else
                    {
                        System.out.println("Please enter numbers between 1-12:");
                    }
                }
                else
                {
                    System.out.println("Invalid! please enter number between 1-12:");
                    scanner.nextLine();
                }

        }while (!validChoice);
       }
       return makeBankOffer(scanner);
    }

    /**
     * request player to enter name
     */
    public void requestPlayerName(Scanner scanner)
    {
        do{
            System.out.println("Please enter player's name(4-10 characters): ");
            
            String playerName = scanner.nextLine();
            //call the setname method on playerclass to verify validation 
            //invaild -> "Default Player", valid -> playerName = user's input
            player.setName(playerName);
            if (player.getName().equals("Default Player"))
            {
                System.out.println("Please enter a valid name from 4-10 characters.");
            }

        }while (player.getName().equals("Default Player"));

        System.out.println("Welcome " + player.getName());
    }

    /**
     * start the whole game
     */
    public void runGame()
    {
        displayWelcomeMessage();
        displayGameRule();
        Scanner scanner = new Scanner(System.in);
        requestPlayerName(scanner);
        do
        {
            
            initializeBoxes();
            chooseBox(scanner);

            boolean gameEnded = false;
            
            if (!gameEnded)
            {
                gameEnded = playARound(1, 4, scanner);
            }

            if (!gameEnded)
            {
                gameEnded = playARound(2, 3, scanner);
            }
            
            if (!gameEnded)
            {
                gameEnded = playARound(3, 2, scanner);
            }

            if (!gameEnded)
            {
                gameEnded = playARound(4, 1, scanner);
            }

            if (!gameEnded)
            {
                displayGameResult();
            }

        }
        while(isPlayAgain(scanner));
        displayFinalResult();

    }


    /**
     * set the boxes.
     */
    public void setGameBoxes(Box[] gameBoxes)
    {
        this.gameBoxes = gameBoxes;
    }

    /**
     * set the player.
     */
    public void setPlayer(Player player)
    {
        this.player = player;
    }

    /**
     * return a string
     */
    public String toString()
    {
        return "Player: " + player.toString() + "Game Boxes: " + gameBoxes.toString();
    }

}
