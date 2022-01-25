package duke;

import java.util.Scanner;

public class Ui {
    private static Scanner sc = new Scanner(System.in);

    public static Command getCommand() {
        Command actionType = null;
        while (actionType == null) {
            try {
                String command = sc.next();
                actionType = Parser.parseCommand(command);
            } catch (CommandNotFoundException e) {
                System.out.println("Sorry, i don't understand what you are saying");
                actionType = null;
                sc.nextLine();
            }
        }
        return actionType;
    }

    public static String[] getInputs() {
        String input = sc.nextLine();
        return Parser.parseInput(input);
    }

    public static void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Welcome to Duke! \nWhat can i do for you?\n");
    }
}
