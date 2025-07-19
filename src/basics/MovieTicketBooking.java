package basics;

import java.util.Scanner;

public class MovieTicketBooking {
    static int available = 50;

    public static void main(String[] args) throws InterruptedException {
        Scanner r = new Scanner(System.in);
        System.out.println("Enter number of tickets each user wants to book:");
        int n = r.nextInt();

        Thread user1 = new Thread(() -> {
            System.out.println("User1 started booking");
            String result = book(n);
            System.out.println("User1: " + result);
        });

        Thread user2 = new Thread(() -> {
            System.out.println("User2 started booking");
            String result = book(n);
            System.out.println("User2: " + result);
        });

        user1.start();
        user2.start();
        user1.join();
        user2.join();

        System.out.println("Booking completed. Remaining tickets: " + available);
    }

    private synchronized static String book(int n) {
        if (available >= n) {
            System.out.println("Booking " + n + " tickets...");
            available -= n;
            return n + " tickets booking confirmed";
        } else {
            return "Booking failed: only " + available + " tickets available";
        }
    }
}
