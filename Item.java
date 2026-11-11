/**
 * Represents the items in the game
 * Item have a name, description, and weight
 * 
 * @author William Harvey
 * @version 2026.11.10
 */

public class Item
{
    private String name;
    private String description;
    private int weight;

    /**
     * Create an item with a name, description, and height
     */
    public Item(String name, String description, int weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * Displays items name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Displays items description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Displays items weight
     */
    public int getWeight()
    {
        return weight;
    }
}