import java.util.ArrayList;
/**
 * This is the player in the game
 * Stores the current location of the plauer
 * 
 * @author William Harvey
 * @version 2026.11.10
 */
public class Player
{
    private Room currentRoom;
    private ArrayList<Item> inventory;

    /**
     * Creates the player in the starting room
     */
    public Player(Room startingRoom)
    {
        currentRoom = startingRoom;
        inventory = new ArrayList<>();
    }

    /**
     * Displays the players current room
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * Move the player into another room
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }
    /**
     * Adds item to players inventory
     */
    public void addItem (Item item)
    {
        inventory.add(item);
    }
    /**
     * Removes item from players inventory
     */
    public void removeItem(Item item)
    {
        inventory.remove(item);
    }
    /**
     * Find an item in the player's inventory
     */
    public Item getItem(String name)
    {
      for (Item item : inventory) {
          if (item.getName().equalsIgnoreCase(name)) {
              return item;
          }
      }
      return null;
    }
}