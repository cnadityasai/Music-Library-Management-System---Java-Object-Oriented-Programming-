public class Artists {
    // Artist's name
    private String name;
    // Artist's band name
    private String bandName;

    /* Constructor for Artists class
    * @param name Artist's name
     */
    public Artists(String name) {
        this.name = name;
    }

    /* Sets the band name
    * @param bandName artist's band name
     */
    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    /* Returns the artist's name
    * @return the name
     */
    public String getName() {
        return name;
    }

    /* Returns the artist's band name
    * @return The bandName
     */
    public String getBandName() {
        return bandName;
    }


}
