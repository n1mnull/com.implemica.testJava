package task02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*2. You are given a list of cities. Each direct connection between two cities has its transportation cost.
 The goal is to find the paths of minimum cost between pairs of cities.
*/
public class Traveling {

    public static int MAX_TESTS = 10;
    public static int MAX_CITIES = 10000;
    public static int MAX_ROUTES = 100;
    private static List<City> tests = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("Y:\\com.implemica.testJava\\src\\task02\\source\\inputData_.txt"));

        int numberTests = Integer.parseInt(bufferedReader.readLine()); // read how much test will be written in input-file
        if (numberTests > MAX_TESTS || numberTests < 1) { // check is valid data read
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < numberTests; i++) {
            int numberCities = Integer.parseInt(bufferedReader.readLine());// read number of cities
            if (numberCities > MAX_CITIES || numberCities < 2) {
                throw new IllegalArgumentException();
            }
            City city = new City(numberCities);

            for (int cityIndex = 0; cityIndex < numberCities; cityIndex++) {
                city.addCity(bufferedReader.readLine());  // read name current city
                int numberNeighbours = Integer.parseInt(bufferedReader.readLine()); // read number of neighbours

                for (int neighboursIndex = 0; neighboursIndex < numberNeighbours; neighboursIndex++) {
                    String[] tempStr = bufferedReader.readLine().split(" ");
                    city.addRouteCost(cityIndex, Integer.parseInt(tempStr[0])-1, Integer.parseInt(tempStr[1])); // fill cost route to neighbours
                }
            }

            int numberRoutes = Integer.parseInt(bufferedReader.readLine()); // read number of route
            if (numberRoutes < 1 || numberRoutes > MAX_ROUTES) {
                throw new IllegalArgumentException();
            }

            String[][] paths = new String[numberRoutes][2];
            for (int routeIndex = 0; routeIndex < numberRoutes; routeIndex++) { // parsing string to cities of departure and destination
                String[] tempStr = bufferedReader.readLine().split(" ");
                paths[routeIndex][0] = tempStr[0];
                paths[routeIndex][1] = tempStr[1];
            }
            city.addPaths(paths);
            tests.add(city);
            bufferedReader.readLine();
        }

        int[][] answer = new int[numberTests][]; // in variable answer we saved result search algorithm
        for(int i = 0; i < numberTests; i++){
            System.out.println((i+1) + " test:");
            answer[i] = tests.get(i).getCalculatePath();
            for (int j = 0; j < answer[i].length; j++) {
                System.out.println("Minimal cost of route " + (j+1) + " = " + answer[i][j]);
            }
        }


    }


}
