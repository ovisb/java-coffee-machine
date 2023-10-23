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
        int value = -1;
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

    public static void printCoffeDetails(int cups) {
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
            System.out.println("Write action (buy, fill, take): ");
            String option = scanner.next();
            switch (option) {
                case "buy":
                    coffeeMenu();
                    break loop;
                case "fill":
                    fillMachine();
                    break loop;
                case "take":
                    takeMoney();
                    break loop;
                default:
                    System.out.println("Machine option not available. Please choose from available ones.");
            }
        }
    }

    public static void coffeeMenu() {
        loop: while (true) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
            String option = scanner.next();
            switch (option) {
                case "1":
                    System.out.println("Prepping espresso..");
                    makeCoffee(250, 0, 16, 4);
                    break loop;
                case "2":
                    makeCoffee(350, 75, 20, 7);
                    System.out.println("Prepping latte..");
                    break loop;
                case "3":
                    System.out.println("Prepping cappuccino..");
                    makeCoffee(200, 100, 12, 6);
                    break loop;
                default:
                    System.out.println("Menu option not available. Please choose from available ones.");
            }
        }
    }

    public static void makeCoffee(int amountOfWater, int amountOfMilk, int amountOfBeans, int price) {
        availableMachineWater -= amountOfWater;
        availableMachineMilk -= amountOfMilk;
        availableMachineBeans -= amountOfBeans;
        availableMachineCups -= 1;
        availableMachineMoney += price;
        System.out.println();
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
        System.out.println();
    }

    public static void main(String[] args) {

//        int availableWater = getInput("Write how many ml of water the coffee machine has: ");
//        int availableMilk = getInput("Write how many ml of milk the coffee machine has: ");
//        int availableBeans = getInput("Write how many grams of coffee beans the coffee machine has: ");
//        int cups = getInput("Write how many cups of coffee you will need: ");
//        int amountOfCups = calculateAmountOfCups(availableWater, availableMilk, availableBeans);
//
//        if (
//                ((availableWater < WATER_PER_COFFEE || availableMilk < MILK_PER_COFFEE || availableBeans < BEANS_PER_COFFEE) && cups == 0)
//                || (amountOfCups == cups)
//        ) {
//            System.out.println("Yes, I can make that amount of coffee");
//        } else if (amountOfCups < cups) {
//            System.out.printf("No, I can make only %d cup(s) of coffee%n", amountOfCups);
//        } else {
//            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)", amountOfCups - cups);
//        }
        printMachineStatus();
        actionMenu();
        printMachineStatus();
    }
}
