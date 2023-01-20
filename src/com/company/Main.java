import java.util.Scanner;
import java.util.*;

public class NeedForSpeed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int carsNumber = Integer.parseInt(scanner.nextLine());

        TreeMap<String, Integer> carsDistances = new TreeMap<>();
        TreeMap<String, Integer> carsTanks = new TreeMap<>();

        while (carsNumber >= 1) {

            String[] carsData = scanner.nextLine().split("\\|");

            String carModel = carsData[0];
            int carMileage = Integer.parseInt(carsData[1]);
            int carFuel = Integer.parseInt(carsData[2]);

            carsDistances.put(carModel, carMileage);
            carsTanks.put(carModel, carFuel);

            carsNumber--;
        }
        String inputTwo = scanner.nextLine();
        while (!inputTwo.equals("Stop")) {

            String[] commands = inputTwo.split(" : ");
            String swCases = commands[0];
            String currentCar = commands[1];

            switch (swCases) {
                case "Drive":

                    int distance = Integer.parseInt(commands[2]);
                    int fuelF = Integer.parseInt(commands[3]);
                    int currentDistance = carsDistances.get(currentCar);
                    int currentFuel = carsTanks.get(currentCar);

                    if (fuelF > currentFuel) {
                        System.out.print("Not enough fuel to make that ride");
                        System.out.println();
                    } else {
                        carsDistances.put(currentCar, (distance + currentDistance));
                        carsTanks.put(currentCar, (currentFuel - fuelF));
                        System.out.printf("%s driven for %d kilometers. %d liters of fuel consumed."
                                , currentCar, (distance), fuelF);
                        System.out.println();
                    }

                    if ((distance + currentDistance) >= 100000) {//////////////////TODO/////
                        carsDistances.remove(currentCar);
                        carsTanks.remove(currentCar);
                        System.out.printf("Time to sell the %s!", currentCar);
                        System.out.println();
                    }

                    break;
                case "Refuel":

                    int refuelFuel = Integer.parseInt(commands[2]);
                    int border = 75;
                    int currentFFref = carsTanks.get(currentCar);
                    int difference=0;

                    if (currentFFref + refuelFuel >= border) {
                        difference = border - currentFFref;
                        carsTanks.put(currentCar,75);
                        System.out.printf("%s refueled with %d liters"
                                , currentCar, difference);
                        System.out.println();
                    } else {
                        carsTanks.put(currentCar,refuelFuel+currentFFref);
                        System.out.printf("%s refueled with %d liters", currentCar, refuelFuel);
                        System.out.println();
                    }
                    break;
                case "Revert":

                    int kmsTOrevert = Integer.parseInt(commands[2]);
                    int currentKM = carsDistances.get(currentCar);

                    if ((currentKM - kmsTOrevert) < 10000) {
                        carsDistances.put(currentCar, 10000);
                    } else {
                        carsDistances.put(currentCar, (currentKM - kmsTOrevert));
                        System.out.printf("%s mileage decreased by %d kilometers"
                                , currentCar, (kmsTOrevert));
                        System.out.println();
                    }
                    break;
                default:
                    throw new IllegalStateException("kyr");
            }
            inputTwo = scanner.nextLine();
        }
        carsDistances
                .entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .forEach(n -> {
                    String nameCar = n.getKey();
                    int distanceCarss = n.getValue();
                    int fff = carsTanks.get(nameCar);
                    System.out.printf
                            ("%s -> Mileage: %d kms, Fuel in the tank: %d lt."
                                    , nameCar, distanceCarss, fff);
                    System.out.println();
                });
    }
}
