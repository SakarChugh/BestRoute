package bestroute.dto;

public class RestaurantNode extends Node{
    private Node deliverTo;

    public Double getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Double preparationTime) {
        this.preparationTime = preparationTime;
    }

    private Double preparationTime;


    public RestaurantNode(Location location, String name, Node deliverTo, Double preparationTime) {
        super(location, name);
        this.deliverTo = deliverTo;
        this.preparationTime = preparationTime;
    }

    public Node getDeliverTo() {
        return deliverTo;
    }

    public void setDeliverTo(Node deliverTo) {
        this.deliverTo = deliverTo;
    }
}
