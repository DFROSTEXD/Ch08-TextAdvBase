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
     * Create an item with a name, description, and weight.
     * @param name The name of the item.
     * @param description The description of the item.
     * @param weight The weight of the item.
     */
    public Item(String name, String description, int weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /**
     * Displays items name.
     * @return The name of the item.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Displays items description.
     * @return The description of the item.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Displays items weight.
     * @return The weight of the item.
     */
    public int getWeight()
    {
        return weight;
    }
}