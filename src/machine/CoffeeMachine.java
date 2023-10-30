package machine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CoffeeMachine {
    public static final Scanner scanner = new Scanner(System.in);
    private int availableMachineWater;
    private int availableMachineMilk;
    private int availableMachineBeans;
    private int availableMachineCups;
    private int availableMachineMoney;

    private CoffeeMachine() {
        availableMachineWater = 400;
        availableMachineMilk = 540;
        availableMachineBeans = 120;
        availableMachineCups = 9;
        availableMachineMoney = 550;
    }

    private int getIntInput(String message) {
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

    private void printMachineStatus() {
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

    public void actionMenu() {
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

    private void coffeeMenu() {
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

    private boolean checkResources(int amountOfWater, int amountOfMilk, int amountOfBeans) {
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

    private void makeCoffee(int amountOfWater, int amountOfMilk, int amountOfBeans, int price) {
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

    private void fillMachine() {
        availableMachineWater += getIntInput("Write how many ml of water you want to add: ");
        availableMachineMilk += getIntInput("Write how many ml of milk you want to add: ");
        availableMachineBeans += getIntInput("Write how many grams of coffee beans you want to add: ");
        availableMachineCups += getIntInput("Write how many disposable cups you want to add: ");
        System.out.println();
    }

    private void takeMoney() {
        System.out.printf("I gave you $%d%n", availableMachineMoney);
        System.out.println();
        availableMachineMoney = 0;
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.actionMenu();
    }
}
