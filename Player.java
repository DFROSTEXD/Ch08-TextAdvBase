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
     * Creates the player in the starting room.
     * @param startingRoom The room where the player starts at.
     */
    public Player(Room startingRoom)
    {
        currentRoom = startingRoom;
        inventory = new ArrayList<>();
    }

    /**
     * Returns the players current room.
     * @return The name of the current room.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * Move the player into another room.
     * @param room The room the player moves into.
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }
    /**
     * Adds item to players inventory.
     * @param item Adds the item to the inventory.
     */
    public void addItem (Item item)
    {
        inventory.add(item);
    }
    /**
     * Removes item from players inventory.
     * @param item Removes the item from the inventory.
     */
    public void removeItem(Item item)
    {
        inventory.remove(item);
    }
    /**
     * Find an item in the player's inventory.
     * @paramname The name of the item to find.
     * @return The matching item, or null if not found.
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