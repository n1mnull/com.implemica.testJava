package task02;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Alenka on 07.07.2016.
 */
public class City {

    private int numberCities = 0;
    private ArrayList<String> cities;
    private int[][] routeCost;
    private int[][] pathsToFind;

    /* Constructor which create empty List cities and fill all cells of array routeCost by INFINITY */
    public City(int numberCities) {
        this.numberCities = numberCities;
        cities = new ArrayList<>(numberCities);
        routeCost = new int[numberCities][numberCities];
        for (int i = 0; i < numberCities; i++) {
            Arrays.fill(routeCost[i], Integer.MAX_VALUE);
        }
    }

    /* add city to List */
    public void addCity(String name) {
        cities.add(name);
    }

    /* using input parameters departure, destination to change routeCost from INFINITY to real cost*/
    public void addRouteCost(int departure, int destination, int cost) {
        if (departure < 0 || destination < 0 || cost <= 0) {
            throw new IllegalArgumentException();
        }
        routeCost[departure][destination] = cost;
    }


    /* convert city name from array path to int indexCity */
    public void addPaths(String paths[][]) {
        pathsToFind = new int[paths.length][2];
        for (int i = 0; i < paths.length; i++) {
            pathsToFind[i][0] = cities.indexOf(paths[i][0]);
            pathsToFind[i][1] = cities.indexOf(paths[i][1]);
        }
    }
    /* Calculate all way from input param "cityIndex" to another cities
    * and return array of moving costs
    * */
    public int[] calculatePath(int cityIndex) {
        int[] cost = new int[numberCities]; // costs way to another city
        Arrays.fill(cost, Integer.MAX_VALUE); // fill by default all cells of array by INFINITY
        cost[cityIndex] = 0; // cost from city to itself = '0'
        boolean[] visitedCity = new boolean[numberCities]; // mark cities which is already visited

        while (true) {
            int currentCity = -1; // flag which signed about finding cheaper way

            for (int i = 0; i < numberCities; i++) {
                if (!visitedCity[i] && cost[i] < Integer.MAX_VALUE && (currentCity == -1 || cost[currentCity] > cost[i]) ) {
                    currentCity = i;
                }
            }

            if (currentCity == -1)  // break if not founded cheaper way
                break;

            visitedCity[currentCity] = true; // mark city as visited

            for (int i = 0; i < numberCities; i++) {
                if (!visitedCity[i] && routeCost[currentCity][i] < Integer.MAX_VALUE) {
                    cost[i] = Math.min(cost[i], routeCost[currentCity][i] + cost[currentCity]);
                }
            }
        }
        return cost;
    }

    public int[] getCalculatePath() { // for each path will start finding the cheaper way
        int[] answer = new int[pathsToFind.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = calculatePath(pathsToFind[i][0])[pathsToFind[i][1]];
        }
        return answer;
    }

}
