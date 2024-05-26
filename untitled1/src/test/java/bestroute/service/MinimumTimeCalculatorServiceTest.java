package bestroute.service;

import bestroute.dto.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MinimumTimeCalculatorServiceTest {

    @Test
    public void testGetMinimumTime() {
        // Create sample nodes
        Location deliveryPartnerLocation = new Location(12.880744853358252, 77.63034397675911);
        DeliveryPartnerNode deliveryPartnerNode = new DeliveryPartnerNode(deliveryPartnerLocation, "Delivery Partner");

        List<RestaurantNode> restaurantNodes = getRestaurantNodes();

        // Create the service instance
        MinimumTimeCalculatorService service = new MinimumTimeCalculatorService();

        // Call the method under test
        MinimumTimePathInfo result = service.getMinimumTime(restaurantNodes, deliveryPartnerNode);

        // Assertions
        assertNotNull(result);
        assertNotNull(result.getMinimumTimePath());
        assertFalse(result.getMinimumTimePath().isEmpty());
        assertNotNull(result.getMinimumTime());
        assertEquals(79.8710690256535, result.getMinimumTime());
        // Add more specific assertions as needed
    }

    private static List<RestaurantNode> getRestaurantNodes() {
        Location restaurant1Location = new Location(12.885328176923013, 77.64802944744922);
        Location customer1Location = new Location(12.945738726706908, 77.64679774884638);
        RestaurantNode restaurantNode1 = new RestaurantNode(restaurant1Location, "Restaurant 1", new CustomerNode(customer1Location, "Customer 1"), 39.56427068565695);

        Location restaurant2Location = new Location(12.944348620875632, 77.6441468801867);
        Location customer2Location = new Location(12.913355001341229, 77.59293453603527 );
        RestaurantNode restaurantNode2 = new RestaurantNode(restaurant2Location, "Restaurant 2", new CustomerNode(customer2Location, "Customer 2"), 42.48740853785287);

        List<RestaurantNode> restaurantNodes = new ArrayList<>();
        restaurantNodes.add(restaurantNode1);
        restaurantNodes.add(restaurantNode2);
        return restaurantNodes;
    }
}