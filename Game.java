import java.util.Stack;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling, David J. Barnes, and William Harvey
 * @version 2026.11.10
 */

public class Game 
{
    private Parser parser;
    private Stack<Room> roomHistory;
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        roomHistory = new Stack<>();
    }

    /**
     * Create all the rooms and link their exits together.
     * Made all 
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office, library, closet, cafe;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        library = new Room("in the library");
        closet = new Room("in the closet");
        cafe = new Room ("in the cafe");
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        
        outside.setExit("north", library);
        library.setExit("south", outside);

        library.setExit("east", cafe);
        cafe.setExit("west", library);
        
        cafe.setExit("north", closet);
        closet.setExit("south", cafe);
        
        library.addItem(new Item("book", "An old textbook", 2));
        library.addItem(new Item("pen", "A blue ballpoint pen", 1));
        cafe.addItem(new Item("coffee", "A hot cup of coffee", 1));
        closet.addItem(new Item("Key", "A small rusty key", 1));

        player = new Player(outside); // start game outside
    }

    /**
     *  Main play routine. Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
            
            case LOOK:
                System.out.println(player.getCurrentRoom().getLongDescription());
                break;
            
            case BACK:
                goBack();
                break;
                
            case TAKE:
                takeItem(command);
                break;
                
            case DROP:
                dropItem(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param command The command containing the direction to move.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            roomHistory.push(player.getCurrentRoom());
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Start the game outside of BlueJ.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args)
    {
        Game game = new Game();
        game.play();
    }
    
    /**
     * Allows the player to go back to previously visited rooms 
     * until they can't go back anymore, and prints a message that 
     * corresponds to the room you're in.
     */
    private void goBack()
    {
        if (roomHistory.empty()) {
            System.out.println("You cannot go back anymore.");
        }
        else {
            player.setCurrentRoom(roomHistory.pop());
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
    }
    
    /**
     * Gives the player the ability to take an item from the room they are in.
     * @param command The command specifying what item to pick up.
     */
    private void takeItem(Command command)
    {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        
        String itemName = command.getSecondWord();
        Room room = player.getCurrentRoom();
        Item item = room.getItem(itemName);
        
        if (item == null) {
            System.out.println("The item isn't in this room.");
        }
        else {
            room.removeItem(item);
            player.addItem(item);
            System.out.println("You picked up the " + item.getName() + ".");
        }
    }
    
    /**
     * Gives the player the ability to drop items in their inventory
     * into the room they are in.
     * @param command The command specifying what item to drop.
     */
    private void dropItem(Command command)
    {
        if (!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return;
        }
        
        String itemName = command.getSecondWord();
        Item item = player.getItem(itemName);
        
        if (item == null) {
            System.out.println("The item isn't in your inventory");
        }
        else {
            player.removeItem(item);
            player.getCurrentRoom().addItem(item);
            System.out.println("You dropped " + item.getName() + ".");
        }
    }
}
