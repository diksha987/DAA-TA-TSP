
import java.util.*;
import java.text.DecimalFormat;

public class TEST {
    static DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) throws Exception {

        System.out.println("                    ********************************************");
        System.out.println("                    *                                          *");
        System.out.println("                    *      TRAVELLING SALESMAN PROBLEM         *");
        System.out.println("                    *                                          *");
        System.out.println("                    ********************************************");
        double[][][] matrix =
        { { { 0, 0 }, { 4, 12 }, { 1, 6 }, { 3, 10 } },
        { { 4, 8 }, { 0, 0 }, { 2, 3 }, { 1, 7} },
        { { 1, 5 }, { 2, 20 }, { 0, 0 }, { 5, 6 } },
        { { 3, 15 }, { 1, 11 }, { 5, 12 }, { 0, 0 } } };
        //Part 1: Best Distance

        System.out.println("\nTRAVELLING SALESMAN PROBLEM BASED ON BEST DISTANCE ");

        TSPDistance distanceSolver = new TSPDistance(0,matrix);

        List<Integer> pathDistance = new ArrayList<>();
        pathDistance = distanceSolver.getPath();

        double timeCost = 0;
        int i = 0;

        System.out.println("\nPath: " + pathDistance);


        System.out.println("Distance cost: " + distanceSolver.getPathCost());

        for (i = 0; i < 3; i++) {
            timeCost += matrix[pathDistance.get(i)][pathDistance.get(i + 1)][1];
        }

        timeCost += matrix[pathDistance.get(i)][0][1];

        
        System.out.println("Time cost: " + timeCost);
        
        System.out.println("____________________________________________________________________________________________\n");
        System.out.println("\nTRAVELLING SALESMAN PROBLEM BASED ON BEST TIME \n");

        TSP_Time timeSolver = new TSP_Time(matrix);

        List<Integer> pathTime = new ArrayList<>();
        pathTime = timeSolver.getPath();

        double distanceCost = 0;
        int j = 0;

        System.out.println("Path: " + pathTime);

        System.out.println("Time cost: " + timeSolver.getPathCost());

        for (j = 0; j < 3; j++) {
            distanceCost += matrix[pathTime.get(j)][pathTime.get(j + 1)][0];
        }
        distanceCost += matrix[pathTime.get(j)][0][0];

        
        System.out.println("Disatnce cost: " + distanceCost);

        System.out.println("____________________________________________________________________________________________\n");

        System.out.println("\nTRAVELLING SALESMAN PROBLEM BASED ON BEST DISTANCE AND BEST TIME \n");

        double[][] speed = new double[4][4];


        System.out.println("Speed Matrix:");
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {

                if (matrix[i][j][1] == 0) {
                    speed[i][j] = 0;
                    System.out.print(df.format(speed[i][j]) + "\t");
                    continue;
                }

                speed[i][j] = matrix[i][j][0] / matrix[i][j][1];
                System.out.print(df.format(speed[i][j]) + "\t");

            }
            System.out.println("\n");
        }

        TSPDistanceTime speedSolver = new TSPDistanceTime(speed);

        List<Integer> pathSpeed = new ArrayList<>();
        pathSpeed = speedSolver.getPath();

        
        System.out.println("Path: " + pathSpeed);

        double speedDistanceCost = 0;
        double speedTimeCost = 0;

        for (j = 0; j < 3; j++) {
            speedDistanceCost += matrix[pathSpeed.get(j)][pathSpeed.get(j + 1)][0];
        }
        speedDistanceCost += matrix[pathTime.get(j)][0][0];

        
        System.out.println("Disatnce cost: " + speedDistanceCost);

        for (j = 0; j < 3; j++) {
            speedTimeCost += matrix[pathSpeed.get(j)][pathSpeed.get(j + 1)][1];
        }
        speedTimeCost += matrix[pathTime.get(j)][0][1];

        
        System.out.println("Time cost: " + speedTimeCost);

    }

}

class TSPDistance {
     int N;
     int START_NODE;
     int FINISHED_STATE;

     double[][][] distance;
     double minPathCost = Integer.MAX_VALUE;

     List<Integer> path = new ArrayList<>();
     boolean runSolver = false; // Was the solver run?

    

    public TSPDistance(int start_Node, double[][][] distance) throws Exception {

        this.distance = distance;
        N = distance.length;
        START_NODE = start_Node;

        if (START_NODE < 0 || START_NODE >= N) {
            throw new Exception("Illegal Input Data");
        }

        FINISHED_STATE = (1 << N) - 1;
        // This means that all the nodes have been visited

    }

    // Returns the optimal path for the traveling salesman problem
    public List<Integer> getPath() {
        if (runSolver==false) {
            solve();
        }
        return path;
    }

    // Returns the minimal path cost
    public double getPathCost() {
        if (runSolver==false)
            solve();

        return minPathCost;
    }

    public void solve() {
        // Run the solver
        int state = 1 << START_NODE;
        Double[][] memory = new Double[N][1 << N]; 
        Integer[][] prev = new Integer[N][1 << N];

        minPathCost = tsp(START_NODE, state, memory, prev);

        // Regenerate path
        int index = START_NODE;

        while (true) {
            path.add(index);
            Integer nextIndex = prev[index][state];

            if (nextIndex == null)
                break;

            int nextState = state | (1 << nextIndex);
            state = nextState;
            index = nextIndex;
        }

        path.add(START_NODE);
        runSolver = true;
    }

    private double tsp(int i, int state, Double[][] memo, Integer[][] prev) {

        // Path Done
        if (state == FINISHED_STATE)
            return distance[i][START_NODE][0];

        // Return answer if already calculated
        if (memo[i][state] != null)
            return memo[i][state];

        double minCost = Integer.MAX_VALUE;
        int index = -1;

        for (int next = 0; next < N; next++) {
            if ((state & (1 << next)) != 0)
                continue;

            int nextState = state | (1 << next);

            double newCost = distance[i][next][0] + tsp(next, nextState, memo, prev);

            if (newCost < minCost) {
                minCost = newCost;
                index = next;
            }
        }

        prev[i][state] = index;
        memo[i][state] = minCost;
        return minCost;
    }
}


class TSP_Time {
     final int N;
     final int START_NODE;
     final int FINISHED_STATE;

     double[][][] time;
     double minPathCost = Integer.MAX_VALUE;

     List<Integer> path = new ArrayList<>();
     boolean runSolver = false;

    public TSP_Time(double[][][] time) throws Exception {
        this(0, time);
    }

    public TSP_Time(int startNode, double[][][] time) throws Exception {

        this.time = time;
        N = time.length;
        START_NODE = startNode;

        if (START_NODE < 0 || START_NODE >= N) {
            throw new Exception("Illegal Input Data");
        }

        FINISHED_STATE = (1 << N) - 1;

    }

    public List<Integer> getPath() {
        if (runSolver==false) {
            solve();
        }
        return path;
    }

    public double getPathCost() {
        if (runSolver==false)
            solve();
        return minPathCost;
    }

    public void solve() {

        int state = 1 << START_NODE;
        Double[][] memory = new Double[N][1 << N];
        Integer[][] prev = new Integer[N][1 << N];

        minPathCost = tsp(START_NODE, state, memory, prev);

        int index = START_NODE;

        while (true) {
            path.add(index);
            Integer nextIndex = prev[index][state];

            if (nextIndex == null)
                break;

            int nextState = state | (1 << nextIndex);
            state = nextState;
            index = nextIndex;
        }

        path.add(START_NODE);
        runSolver = true;

    }

    private double tsp(int i, int state, Double[][] memo, Integer[][] prev) {

        if (state == FINISHED_STATE)
            return time[i][START_NODE][1];

        if (memo[i][state] != null)
            return memo[i][state];

        double minCost = Integer.MAX_VALUE;
        int index = -1;

        for (int next = 0; next < N; next++) {

            if ((state & (1 << next)) != 0)
                continue;

            int nextState = state | (1 << next);

            double newCost = time[i][next][1] + tsp(next, nextState, memo, prev);

            if (newCost < minCost) {
                minCost = newCost;
                index = next;
            }
        }

        prev[i][state] = index;
        return memo[i][state] = minCost;

    }

}

class TSPDistanceTime {

     int N;
     int START_NODE;
     int FINISHED_STATE;

     double[][] distance;
     double minPathCost = Integer.MAX_VALUE;

     List<Integer> path = new ArrayList<>();
     boolean runSolver = false;

    int timeCost;

    public TSPDistanceTime(double[][] distance) throws Exception {
        this(0, distance);
    }

    public TSPDistanceTime(int startNode, double[][] distance) throws Exception {

        this.distance = distance;
        N = distance.length;
        START_NODE = startNode;

        if (START_NODE < 0 || START_NODE >= N) {
            throw new Exception("Illegal Input Data");
        }

        FINISHED_STATE = (1 << N) - 1;

    }

    public List<Integer> getPath() {
        if (runSolver==false) {
            solve();
        }
        return path;
    }

    public double getPathCost() {
        if (runSolver==false)
            solve();

        return minPathCost;
    }

    public void solve() {
        int state = 1 << START_NODE;
        Double[][] memo = new Double[N][1 << N];
        Integer[][] prev = new Integer[N][1 << N];

        minPathCost = tsp(START_NODE, state, memo, prev);

        int index = START_NODE;

        while (true) {
            path.add(index);
            Integer nextIndex = prev[index][state];

            if (nextIndex == null)
                break;

            int nextState = state | (1 << nextIndex);
            state = nextState;
            index = nextIndex;
        }

        path.add(START_NODE);
        runSolver = true;
    }

    private double tsp(int i, int state, Double[][] memo, Integer[][] prev) {

        if (state == FINISHED_STATE)
            return distance[i][START_NODE];

        if (memo[i][state] != null)
            return memo[i][state];

        double minCost = Integer.MAX_VALUE;
        int index = -1;

        for (int next = 0; next < N; next++) {

            if ((state & (1 << next)) != 0)
                continue;

            int nextState = state | (1 << next);

            double newCost = distance[i][next] + tsp(next, nextState, memo, prev);

            if (newCost < minCost) {
                minCost = newCost;
                index = next;
            }
        }

        prev[i][state] = index;
        memo[i][state] = minCost;
        return minCost;

    }
}

