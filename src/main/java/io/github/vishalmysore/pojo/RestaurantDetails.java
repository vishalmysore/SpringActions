package io.github.vishalmysore.pojo;

/**
 *  * These are all your pojos that are used to send data to the AI processor.
 *  * They will get automatically populated based on the NLP
 */
public class RestaurantDetails {
    String name;
    String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "RestaurantDetails{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
