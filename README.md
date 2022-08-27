### MovieTheaterProblem

#### Assumptions:

1 - First movie goers do not mind sitting in the from and in the corner.
2 - People are willing to fill up the furthest row forward if they can.
3 - If someone is in a row, the entire back row will be blocked for safety purposes.
4 - Being 3 seats away from another group is already maximum safety.
5 - All requests have unique request numbers.

#### Run Instructions:

1 - Add a text file in the format of "R001 <Number of seats>" on individual lines with a different request number for each line.
2 - Run "gradle build" on the same level as the "build.gradle" file.
3 - Run "gradle run -PfileName<filePath>" to execute the simulation.
4 - Run "cat out.txt" to see the results.

#### Ideal Implementation:

1 - Requests can have requested starting seat number.
2 - Block off only a small area behind a taken seat rather than the whole row.
3 - Distribute requests around the center for better viewing experience.
