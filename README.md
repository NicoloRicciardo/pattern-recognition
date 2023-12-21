# <strong>Pattern Recognition</strong>
WellD programming test.

## Problem resolution
I created a class 'Point' which represents a point on the Cartesian plane. I also created a class 'Line' which is a collection of points and has a vector (startingPoint - endingPoint).
I used the vector to determine whether one line is equal to another but more importantly to determine whether a given point is part of the line, using the equation: <b><i>α*vector = pointToAdd - startingPoint</i></b>.

I created a Main class to fulfil the request: 'Given a set of N feature points in the plane, determine every line that contains N or more of the points, and return all points involved'.
I created two logics to test the plane:
- using points created within the programme <i>(commented part)</i>
- using points entered at runtime by the user

When a point is created, the 'createPoint' method is called, which checks whether the point can be added to the plane or not. If the point belongs to the plane, it is added to the list of points.
A number of lines equal to the number of points -1 are also created.

The PatternService class is used to implement 'dependency injection' in order to separate the 'Point' and 'Line' classes from the controller.
In the PatternService, methods are implemented which are used to run the API defined in the controller:
- <b>getPointList:</b> returns all points on the plane
- <b>getLineList:</b> returns all lines on the plane
- <b>createPoint:</b> each time a point is posted, if it does not already exist in the list of points, then it is added and a number of lines equal to the number of points -1 are created. For each line in the plane, it is also checked whether the point is part of the line.
- <b>getLinesWithNPoints:</b> returns the list of lines that have at least N points
- <b>deleteAllPoints:</b> delete all points from the plan and empty all lists

APIs are defined in the PatterController.
