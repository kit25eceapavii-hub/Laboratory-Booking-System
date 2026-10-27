import java.util.ArrayList;
import java.util.Scanner;

class Booking {
    int bookingId;
    String studentName;
    String labName;
    String timeSlot;

    Booking(int id, String student, String lab, String time) {
        bookingId = id;
        studentName = student;
        labName = lab;
        timeSlot = time;
    }

    void display() {
        System.out.println(bookingId + "\t" + studentName + "\t"
                + labName + "\t" + timeSlot);
    }
}

public class LaboratoryBookingSystem {

    static ArrayList<Booking> bookings = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextId = 1;

    static void bookLab() {
        System.out.print("Enter student name: ");
        String student = sc.nextLine();

        System.out.println("Available Labs:");
        System.out.println("1. Computer Lab");
        System.out.println("2. Electronics Lab");
        System.out.println("3. Physics Lab");

        System.out.print("Choose lab (1-3): ");
        int choice;

        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid lab choice!");
            return;
        }

        String lab;
        switch (choice) {
            case 1: lab = "Computer Lab"; break;
            case 2: lab = "Electronics Lab"; break;
            case 3: lab = "Physics Lab"; break;
            default:
                System.out.println("Invalid lab choice!");
                return;
        }

        System.out.println("Time Slots:");
        System.out.println("1. 09:00 AM - 10:00 AM");
        System.out.println("2. 10:00 AM - 11:00 AM");
        System.out.println("3. 11:00 AM - 12:00 PM");

        System.out.print("Choose time slot (1-3): ");
        int timeChoice;

        try {
            timeChoice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid time slot!");
            return;
        }

        String time;
        switch (timeChoice) {
            case 1: time = "09-10 AM"; break;
            case 2: time = "10-11 AM"; break;
            case 3: time = "11-12 PM"; break;
            default:
                System.out.println("Invalid time slot!");
                return;
        }

        for (Booking b : bookings) {
            if (b.labName.equals(lab) &&
                b.timeSlot.equals(time)) {
                System.out.println(
                    "Booking failed! This lab is already booked.");
                return;
            }
        }

        Booking b = new Booking(nextId++, student, lab, time);
        bookings.add(b);

        System.out.println("Lab booked successfully!");
        System.out.println("Your Booking ID: " + b.bookingId);
    }

    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        System.out.println("\\nID\\tStudent\\t\\tLab\\t\\tTime Slot");
        System.out.println("------------------------------------------------");

        for (Booking b : bookings) {
            b.display();
        }
    }

    static void cancelBooking() {
        System.out.print("Enter Booking ID to cancel: ");
        int id;

        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid booking ID!");
            return;
        }

        for (Booking b : bookings) {
            if (b.bookingId == id) {
                bookings.remove(b);
                System.out.println("Booking cancelled successfully!");
                return;
            }
        }

        System.out.println("Booking ID not found!");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\\n===== LABORATORY BOOKING SYSTEM =====");
            System.out.println("1. Book a Laboratory");
            System.out.println("2. View All Bookings");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    bookLab();
                    break;
                case 2:
                    viewBookings();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    System.out.println("Thank you for using the system!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
