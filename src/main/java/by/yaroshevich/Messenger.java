package by.yaroshevich;

public class Messenger {
    public static void printStartMessage() {
        System.out.print("\033\143");
        System.out.println("This is simple project for editing MySql DB.");
        System.out.println("For displaying commands list use --help.");
        System.out.println("Type --exit to close the app. \n\n");
    }

    public static void printExitMessage() {
        System.out.println("Closing the app.");
    }
}
