package bestroute;

import bestroute.dto.*;
import bestroute.service.MinimumTimeCalculatorService;
import bestroute.utility.HaversineDistanceCalculationUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static int numberOfOrders = 2;
    static double maxPreparationTime = 45; //in minutes
    static double minPreparationTime = 15; //in minutes
    static double minLongitude = 77.67;
    static double maxLongitude = 77.58;
    static double minLatitude = 12.88;
    static double maxLatitude = 12.97;


    public static void main(String[] args) {
        MinimumTimeCalculatorService minimumTimeCalculatorService = new MinimumTimeCalculatorService();
        Random random = new Random();
        List<RestaurantNode> restaurantNodes = new ArrayList<>();
        List<Node> allNodes = new ArrayList<>();

        //Initialize all nodes for given number of restaurants and customers
        System.out.println("\nWaiting time for Restaurants: ");
        for (int i = 0; i < numberOfOrders; i++) {
            Location location = new Location(minLatitude + (maxLatitude - minLatitude) * random.nextDouble(),
                    minLongitude + (maxLongitude - minLongitude) * random.nextDouble());
            CustomerNode customerNode = new CustomerNode(location, "C" + (i + 1));
            Location restaurantLocation = new Location(minLatitude + (maxLatitude - minLatitude) * random.nextDouble(),
                    minLongitude + (maxLongitude - minLongitude) * random.nextDouble());
            RestaurantNode restaurantNode = new RestaurantNode(restaurantLocation, "R" + (i + 1),
                    customerNode, minPreparationTime + (maxPreparationTime - minPreparationTime) * random.nextDouble());
            restaurantNodes.add(restaurantNode);
            System.out.println(restaurantNode.getLocation().toString());
            System.out.println(customerNode.getLocation().toString());
            allNodes.add(restaurantNode);
            allNodes.add(customerNode);
            //Print waiting time for all restaurants
            System.out.println(restaurantNode.getName() + " " + restaurantNode.getPreparationTime());
        }

        //Initialize initial DeliveryPartner location
        Location deliveryPartnerLocation = new Location(minLatitude + (maxLatitude - minLatitude) * random.nextDouble(),
                minLongitude + (maxLongitude - minLongitude) * random.nextDouble());
        DeliveryPartnerNode deliveryPartnerNode = new DeliveryPartnerNode(deliveryPartnerLocation, "Aman");
        allNodes.add(deliveryPartnerNode);
        System.out.println(deliveryPartnerNode.getLocation().toString());

        System.out.println("\nDistance between Nodes: ");
        //Print all distances between the nodes
        for (int i = 0; i < allNodes.size(); i++) {
            for (int j = 0; j < i; j++) {
                double distance = HaversineDistanceCalculationUtility.getHaversineDistance(allNodes.get(i).getLocation(), allNodes.get(j).getLocation());
                System.out.println(allNodes.get(i).getName() + "->" + allNodes.get(j).getName() + " = " + distance);
            }
        }
        System.out.println();

        //Get minimum distance path for given Combination of customers and restaurants for the Delivery Partner
        MinimumTimePathInfo minimumTimePathInfo = minimumTimeCalculatorService.getMinimumTime(restaurantNodes, deliveryPartnerNode);

        System.out.println("Final Result:\n" + minimumTimePathInfo.toString());
    }
    /*
        Possible combinations for numberOfOrders = 2
        Single order picked up at once:
            R1C1|R2C2
            R2C2|R1C1
        Multiple Orders picked up at once:
            R1R2|C1C2
            R1R2|C2C1
            R2R1|C1C2
            R2R1|C2C1
    */
}