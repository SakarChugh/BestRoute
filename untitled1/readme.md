# Problem Statement
Imagine a delivery executive called Aman standing idle in Koramangala somewhere when suddenly his
phone rings and notifies that he’s just been assigned a batch of 2 orders meant to be delivered in the
shortest possible timeframe.\
All the circles in the figure above represent geolocations :\
● C1 : Consumer 1\
● C2 : Consumer 2\
● R1 : Restaurant C1 has ordered from. Average time it takes to prepare a meal is pt1\
● R2 : Restaurant C2 has ordered from. Average time it takes to prepare a meal is pt2\
Since there are multiple ways to go about delivering these orders, your task is to help Aman figure out
the best way to finish the batch in the shortest possible time.\
For the sake of simplicity, you can assume that Aman, R1 and R2 were informed about these orders at
the exact same time and all of them confirm on doing it immediately.\
Also, for travel time between any two geolocations, you can use the haversine formula with an average speed of 20km/hr (
basically ignore actual road distance or confirmation delays everywhere although the real world is
hardly that simple ;) )\
Note: Code should be of production quality and should take into consideration the best practices of
the language chosen

# Assumptions
## Provided Assumptions
- Speed will be 20km/hr for the delivery partner.
- Restaurants will confirm and start the orders at the same time.
## Assumptions Made
- Delivery partner can carry any number of orders at once.
- Customer's location will only be visited after their order has been picked up.
- Restaurants, Customers and Delivery Partner will be present in 10x10 km square with Kormangala as center point.

# Approach
- We calculate minimum time for all possible combinations in which orders can be delivered.
- Optimization is done by storing distances between 2 nodes once that path has been taken.
- Multiple subclasses are defined for Nodes to keep room for expansion in the future to different types of nodes.