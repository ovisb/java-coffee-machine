package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static final Scanner scanner = new Scanner(System.in);
    public static final int WATER_PER_COFFEE = 200;
    public static final int MILK_PER_COFFEE = 50;
    public static final int BEANS_PER_COFFEE = 15;

    public static int getInput(String message) {
        System.out.println(message);
        return scanner.nextInt();
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


    public static void main(String[] args) {

        int availableWater = getInput("Write how many ml of water the coffee machine has: ");
        int availableMilk = getInput("Write how many ml of milk the coffee machine has: ");
        int availableBeans = getInput("Write how many grams of coffee beans the coffee machine has: ");
        int cups = getInput("Write how many cups of coffee you will need: ");
        int amountOfCups = calculateAmountOfCups(availableWater, availableMilk, availableBeans);

        if (
                ((availableWater < WATER_PER_COFFEE || availableMilk < MILK_PER_COFFEE || availableBeans < BEANS_PER_COFFEE) && cups == 0)
                || (amountOfCups == cups)
        ) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (amountOfCups < cups) {
            System.out.printf("No, I can make only %d cup(s) of coffee%n", amountOfCups);
        } else {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)", amountOfCups - cups);
        }
    }
}
