package machine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CoffeeMachine {
    public static final Scanner scanner = new Scanner(System.in);
    public static final int WATER_PER_COFFEE = 550;
    public static final int MILK_PER_COFFEE = 50;
    public static final int BEANS_PER_COFFEE = 15;
    public static int availableMachineWater = 400;
    public static int availableMachineMilk = 540;
    public static int availableMachineBeans = 120;
    public static int availableMachineCups = 9;
    public static int availableMachineMoney = 550;

    public static int getIntInput(String message) {
        int value;
        while (true) {
            System.out.println(message);
            try {
                value = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number.");
                scanner.next();
                continue;
            }

            if (value < 0) {
                System.out.println("Value cannot be negative.");
                continue;
            }
            return value;
        }
    }

    public static void printCoffeeDetails(int cups) {
        System.out.printf("For %d cups of coffee you will need: %n", cups);
        System.out.printf("""
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                """,
                WATER_PER_COFFEE * cups,
                MILK_PER_COFFEE * cups,
                BEANS_PER_COFFEE * cups
        );
    }

    public static void printMachineStatus() {
        System.out.printf("""
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money
                """,
                availableMachineWater,
                availableMachineMilk,
                availableMachineBeans,
                availableMachineCups,
                availableMachineMoney
                );
        System.out.println();
    }

    public static int calculateAmountOfCups(int machineWater, int machineMilk, int machineBeans) {
        int amount = 0;

        while (machineWater >= WATER_PER_COFFEE && machineMilk >= MILK_PER_COFFEE && machineBeans >= BEANS_PER_COFFEE) {
            machineWater -= WATER_PER_COFFEE;
            machineMilk -= MILK_PER_COFFEE;
            machineBeans -= BEANS_PER_COFFEE;
            amount += 1;
        }
        return amount;
    }

    public static void actionMenu() {
        loop: while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String option = scanner.next();
            switch (option) {
                case "buy":
                    System.out.println();
                    coffeeMenu();
                    break;
                case "fill":
                    System.out.println();
                    fillMachine();
                    break;
                case "take":
                    System.out.println();
                    takeMoney();
                    break;
                case "remaining":
                    System.out.println();
                    printMachineStatus();
                    break;
                case "exit":
                    break loop;
                default:
                    System.out.println("Machine option not available. Please choose from available ones.");
            }
        }
    }

    public static void coffeeMenu() {
        loop: while (true) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
            String option = scanner.next();
            switch (option) {
                case "1":
                    makeCoffee(250, 0, 16, 4);
                    break loop;
                case "2":
                    makeCoffee(350, 75, 20, 7);
                    break loop;
                case "3":
                    makeCoffee(200, 100, 12, 6);
                    break loop;
                case "back":
                    System.out.println("Going back to main menu");
                    break loop;
                default:
                    System.out.println("Menu option not available. Please choose from available ones.");
            }
        }
    }

    public static boolean checkResources(int amountOfWater, int amountOfMilk, int amountOfBeans) {
        boolean canMake = false;
        if (amountOfWater > availableMachineWater) {
            System.out.println("Sorry, not enough water!");
        } else if (amountOfMilk > availableMachineMilk) {
            System.out.println("Sorry, not enough milk");
        } else if (amountOfBeans > availableMachineBeans) {
            System.out.println("Sorry, not enough beans");
        } else {
            canMake = true;
        }
        return canMake;
    }

    public static void makeCoffee(int amountOfWater, int amountOfMilk, int amountOfBeans, int price) {
        if (checkResources(amountOfWater, amountOfMilk, amountOfBeans)) {
            System.out.println("I have enough resources, making you a coffee!");
            availableMachineWater -= amountOfWater;
            availableMachineMilk -= amountOfMilk;
            availableMachineBeans -= amountOfBeans;
            availableMachineCups -= 1;
            availableMachineMoney += price;
            System.out.println();
        }
    }

    public static void fillMachine() {
        availableMachineWater += getIntInput("Write how many ml of water you want to add: ");
        availableMachineMilk += getIntInput("Write how many ml of milk you want to add: ");
        availableMachineBeans += getIntInput("Write how many grams of coffee beans you want to add: ");
        availableMachineCups += getIntInput("Write how many disposable cups you want to add: ");
        System.out.println();
    }

    public static void takeMoney() {
        System.out.printf("I gave you $%d%n", availableMachineMoney);
        System.out.println();
        availableMachineMoney -= availableMachineMoney;
    }

    public static void main(String[] args) {
        actionMenu();
    }
}
