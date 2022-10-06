# DAA-TA-TSP
<H1>DAA(TA) TRAVELLING SALESMAN PROBLEM(TSP)</H1>

<B>TRAVELLING SALESMAN PROBLEM USING DYNAMIC PROGRAMMING</B>
<P>TRAVELLING SALESMAN PROBLEM USING DYNAMIC PROGRAMMING IN WHICH THE NODE CONSIST OF THE COST AND THE TIME THAT IS REQUIRED TO TRAVEL PATH FROM ONE NODE TO OTHERS
WHICH CONSIST OF THE FOLLOWING CONDITIONS :-</P>
<P>1. BEST DISTANCE WITH THE TIME AND PATH</P>
<P>2. BEST TIME WITH THE DISTANCE(COST) ANT D PATH</P>
<P>3. BEST TIME AS WELL AS DISTANCE WITH PATH</P>
____________________________________________________________________________________________________________________________________

<H1><P><P><B>EXPLANATION:</P></B></H1>

<P>The traveling salesman problems abide by a salesman and a set of cities. The salesman has to visit every one of the cities starting from a certain one and to return to the same city. The challenge of the problem is that the traveling salesman needs to minimize the total length of the trip.</P>

<P>Initially, all cities are unvisited, and the visit starts from the city s. We assume that the initial travelling cost is equal to \mathsf{0}. Next, the TSP distance value is calculated based on a recursive function. If the number of cities in the subset is two, then the recursive function returns their distance as a base case.

On the other hand, if the number of cities is greater than is greater than 2 , then we’ll calculate the distance from the current city to the nearest city, and the minimum distance among the remaining cities is calculated recursively.

Finally, the algorithm returns the minimum distance as a TSP solution.</P>
<P></P>
<B>BEST TIME AS WELL AS DISTANCE WITH PATH IS CALCULATED USING THE BELOW FORMULA OF SPEED : </B>

![image](https://user-images.githubusercontent.com/56405230/194323382-f8cc19f0-9f7d-45c6-83e4-29717390fb21.png)


________________________________________________________________________________________________________________________________________________________
<H1><P><P><B>TESTCASES :</P></B></H1>

<H3><P>TESTCASE 01 : </P></H3>

![image](https://user-images.githubusercontent.com/56405230/194320621-c8a7e54f-7aeb-43a5-8e3a-fc921e914cb4.png)

<H4><P> INPUT : </P></H4>
       <B><P>{ { { 0, 0 }, { 10, 12 }, { 15, 6 }, { 20, 10 } },</P>
        <P>{ { 5, 8 }, { 0, 0 }, { 9, 3 }, { 10, 7} },</P>
        <P>{ { 6, 5 }, { 13, 20 }, { 0, 0 }, { 12, 6 } },</P>
        <P>{ { 8, 15 }, { 8, 11 }, { 9, 12 }, { 0, 0 } } };</P></B>
<H4><P> OUTPUT : </P></H4>

![image](https://user-images.githubusercontent.com/56405230/194325275-ee78c3ef-ae24-48b9-9a55-bbb402b5f81c.png)

________________________________________________________________________________________________________________________________________________________


<H3><P>TESTCASE 02 : </P></H3>

![image](https://user-images.githubusercontent.com/56405230/194326910-c3335330-85ac-41df-8524-1592e26b7ede.png)
<H4><P> INPUT : </P></H4>
        <B><P>{ { { 0, 0 }, { 4, 7 }, { 1, 1.2 }, { 3, .1765 } },</P>
        <P>{ { 4, 2 }, { 0, 0 }, { 2, 2.3 }, { 1, 1.67} },</P>
        <P>{ { 1, 0.24 }, { 2, 1.2 }, { 0, 0 }, { 5, 0.23 } },</P>
        <P>{ { 3, 3.3 }, { 1, 1.1 }, { 5, 3.2 }, { 0, 0 } } }</P></B>
<H4><P> OUTPUT : </P></H4>

![image](https://user-images.githubusercontent.com/56405230/194327872-40e13847-9dc0-4918-ac0b-e9684c793182.png)
