package bestroute.service;

import javafx.util.Pair;
import bestroute.dto.DeliveryPartnerNode;
import bestroute.dto.MinimumTimePathInfo;
import bestroute.dto.Node;
import bestroute.dto.RestaurantNode;
import bestroute.utility.HaversineDistanceCalculationUtility;

import java.util.*;


public class MinimumTimeCalculatorService {
    private static final double AVERAGE_SPEED = 20;

    //Function to get minimum time of route for the given set of restaurants, customers and delivery partner
    public MinimumTimePathInfo getMinimumTime(List<RestaurantNode> restaurantNodeList, DeliveryPartnerNode deliveryPartnerNode) {
        Set<Node> visitedSet = new HashSet<>();
        List<Node> path = new ArrayList<>();
        path.add(deliveryPartnerNode);
        List<Double> timeReached = new ArrayList<>();
        Map<Pair<Node, Node>, Double> distanceMap = new HashMap<>();
        MinimumTimePathInfo minimumTimePathInfo = new MinimumTimePathInfo(new ArrayList<>(), Double.MAX_VALUE);
        dfs(restaurantNodeList, visitedSet, path, minimumTimePathInfo, deliveryPartnerNode, 0, timeReached, distanceMap);
        return minimumTimePathInfo;
    }

    //Perform recursion search in graph to cover all possibilities
    public void dfs(List<RestaurantNode> restaurantNodeList,
                    Set<Node> visitedSet, List<Node> path, MinimumTimePathInfo minimumTimePathInfo, Node currentNode, double currentTime, List<Double> timeReached, Map<Pair<Node, Node>, Double> distanceMap) {
        timeReached.add(currentTime);
        //Check if all restaurants and customers have been visited
        if (visitedSet.size() == restaurantNodeList.size() * 2) {

            //Print path and time of finish at each node
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i).getName() + (i!=path.size()-1 ? "->" : ""));
            }
            System.out.println();
            for (int i = 0; i < path.size(); i++) {
                System.out.print(timeReached.get(i) + (i!=path.size()-1 ? "->" : ""));
            }
            System.out.println();
            //Update minimumValue if better route is found
            if (currentTime < minimumTimePathInfo.getMinimumTime()) {
                minimumTimePathInfo.setMinimumTime(currentTime);
                minimumTimePathInfo.setMinimumTimePath(new ArrayList<>(path));
            }
            System.out.println();
            return;
        }
        for (RestaurantNode restaurantNode : restaurantNodeList) {
            //Visit restaurant if not visited
            if (!visitedSet.contains(restaurantNode)) {
                path.add(restaurantNode);
                visitedSet.add(restaurantNode);
//                double travelTime = HaversineDistanceCalculationUtility.getHaversineDistance(restaurantNode.getLocation(), currentNode.getLocation()) / AVERAGE_SPEED * 60; //in mins
                double travelTime = getTravelTimeBetweenPair(distanceMap, restaurantNode, currentNode);
                //Go to next restaurant
                dfs(restaurantNodeList, visitedSet, new ArrayList<>(path), minimumTimePathInfo, restaurantNode, Math.max(restaurantNode.getPreparationTime(), currentTime + travelTime), new ArrayList<>(timeReached), distanceMap);
                path.remove(path.size() - 1);
                visitedSet.remove(restaurantNode);
            }
            else {
                //Can only visit customer if restaurant has been visited
                if (!visitedSet.contains(restaurantNode.getDeliverTo())) {
                    path.add(restaurantNode.getDeliverTo());
                    visitedSet.add(restaurantNode.getDeliverTo());
//                    double travelTime = HaversineDistanceCalculationUtility.getHaversineDistance(restaurantNode.getDeliverTo().getLocation(), currentNode.getLocation()) / AVERAGE_SPEED * 60;//in mins
                    double travelTime = getTravelTimeBetweenPair(distanceMap, restaurantNode.getDeliverTo(), currentNode);
                    //Go to customer if restaurant is already visited
                    dfs(restaurantNodeList, visitedSet, new ArrayList<>(path), minimumTimePathInfo, restaurantNode, currentTime + travelTime, new ArrayList<>(timeReached), distanceMap);
                    path.remove(path.size() - 1);
                    visitedSet.remove(restaurantNode.getDeliverTo());
                }
            }
        }
    }

    private double getTravelTimeBetweenPair(Map<Pair<Node, Node>, Double> distanceMap, Node node1, Node node2) {
        Pair<Node, Node> pair = new Pair<>(node1, node2);
        if(distanceMap.containsKey(new Pair<>(node1, node2))){
            return distanceMap.get(pair);
        } else{
            double travelTime = HaversineDistanceCalculationUtility.getHaversineDistance(node1.getLocation(), node2.getLocation()) / AVERAGE_SPEED * 60;//in mins
            distanceMap.put(pair, travelTime);
            distanceMap.put(new Pair<>(node2, node1), travelTime);
            return travelTime;
        }
    }
}
