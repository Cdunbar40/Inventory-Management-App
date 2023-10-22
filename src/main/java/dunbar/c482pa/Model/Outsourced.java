package dunbar.c482pa.Model;

/** The Outsourced class is a subclass of the abstract class Part that tracks outsourced items.
 *  It adds a field for the company used to manufacture a part, as well as the other Part fields.
 *  Provides methods for accessing and setting the outsource company. */
public class Outsourced extends Part{
    /**Name of the company that manufactures an outsourced part*/
    private String companyName;

    /**Constructor for Outsourced Objects.
     * Invokes the Part constructor and sets the initial value for the companyName field.
     * @param id ID number of the part
     * @param name Name of the part
     * @param price Price of the part
     * @param stock Number of parts in inventory
     * @param min Minimum number of parts required to be kept in inventory
     * @param max Maximum number of parts allowed to be kept in inventory
     * @param companyName Name of the company used to manufacture the part*/
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**Retrieves the name of the company used to manufacture the part.
     * @return Returns the name of the company used to manufacture the part*/
    public String getCompanyName() {
        return companyName;
    }

    /** Sets the name of the company used to manufacture the part.
     * @param companyName the name of the company used to manufacture the part*/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}

