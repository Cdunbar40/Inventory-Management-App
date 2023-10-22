package dunbar.c482pa.Model;

/** The InHouse class is a subclass of the abstract class Part that tracks items manufactured in house.
 *  It adds a field for the machine ID used to manufacture a part, as well as the other Part fields.
 *  Provides methods for accessing and setting the machine ID. */
public class InHouse extends Part {
    /**ID number of the machine used to manufacture a part*/
    private int machineID;

    /** Constructor for InHouse Objects.
     * Invokes the Part constructor and sets the initial value for the machine ID field.
     * @param id ID number of the part
     * @param name Name of the part
     * @param price Price of the part
     * @param stock Number of parts in inventory
     * @param min Minimum number of parts required to be kept in inventory
     * @param max Maximum number of parts allowed to be kept in inventory
     * @param machineID ID number of the machine used to manufacture the part*/
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /** Sets the machine ID of a part
     * @param machineID ID number of the machine used to manufacture the part*/
    public void setMachineID(int machineID) {

        this.machineID = machineID;
    }

    /** Retrieves the machine ID of a Part.
     * @return Returns the ID of the machine used to manufacture the part*/
    public int getMachineID() {

        return machineID;
    }

}
