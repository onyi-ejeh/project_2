package ng.onyi.adventureMain;

import ng.onyi.adventureProject.model.*;

import java.util.Scanner;

public class Game {

    private static final String ROOM_KITCHEN = "Kitchen";
    private static final String ROOM_HALL = "Hall";
    private static final String ROOM_OFFICE = "Office";
    private static final String ROOM_BEDROOM = "Bedroom";
    private static final String ROOM_LIVING_ROOM = "Living room";

    private Scanner scanner = new Scanner(System.in);
    private Resident resident;
    private Burglar burglar;
    private boolean running;
    private boolean fryingPanFound = false;

    public Game() {
        resident = new Resident("Player", 12, 3);
        burglar = new Burglar("Enemy", 12, 4);
        running = true;
    }

    public void start() {
        displayWelcomeMessage();
        while (running && resident.isConscious()) {
            handleUserInput();
        }
        displayFarewellMessage();
        scanner.close();
    }

    private void handleUserInput() {
        System.out.print("Enter a room: ");
        String room = scanner.nextLine();
        processRoomChoice(room);
    }

    private void processRoomChoice(String room) {
        switch (room) {
            case ROOM_KITCHEN -> enterKitchen();
            case ROOM_HALL -> enterHall();
            case ROOM_OFFICE -> enterOffice();
            case ROOM_BEDROOM -> displayMessage("You enter the bedroom. Nothing of interest here.");
            case ROOM_LIVING_ROOM -> displayMessage("You're in the living room where you started.");
            default -> displayInvalidRoomMessage();
        }
    }

    private void enterKitchen() {
        if (!fryingPanFound) {
            displayMessage("You found a frying pan! Your damage is now increased.");
            fryingPanFound = true;
            resident.addDamage (3);
        } else {
            displayMessage("The kitchen is empty. You've already taken the frying pan.");
        }
    }

    private void enterHall() {
        displayMessage("You enter the hall and encounter a burglar!");
        initiateFight();
    }

    private void enterOffice() {
        if (!burglar.isConscious()) {
            displayMessage("You called the police. The game is over.");
            running = false;
        } else {
            displayMessage("Since the burglar is still conscious, you became too anxious to act.");
        }
    }

    private void initiateFight() {
        displayMessage("You encountered the burglar! The fight begins!");

        while (resident.isConscious() && burglar.isConscious()) {
            performResidentAttack();
            if (!burglar.isConscious()) break;

            performBurglarAttack();
        }

        concludeFight();
    }

    private void performResidentAttack() {
        resident.punch(burglar);
        displayMessage("You hit the burglar! Burglar's health: " + burglar.getHealth());
    }

    private void performBurglarAttack() {
        burglar.punch(resident);
        displayMessage("The burglar hits you back! Your health: " + resident.getHealth());
    }

    private void concludeFight() {
        if (!resident.isConscious()) {
            displayMessage("Game Over! You lost the fight.");
            running = false;
        } else {
            displayMessage("You knocked out the burglar! You can now proceed to the office to call the police.");
        }
    }

    private void displayWelcomeMessage() {
        displayMessage("Hi! Explore the rooms and try to stay safe.");
    }

    private void displayFarewellMessage() {
        displayMessage("Thank you for playing!");
    }

    private void displayInvalidRoomMessage() {
        displayMessage("Invalid room choice. Please choose one of the following: " +
                ROOM_KITCHEN + ", " + ROOM_HALL + ", " + ROOM_OFFICE + ", " +
                ROOM_BEDROOM + ", " + ROOM_LIVING_ROOM);
    }

    private void displayMessage(String message) {
        System.out.println(message);
    }
}
