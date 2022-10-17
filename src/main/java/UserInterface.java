import java.util.Scanner;

/*
 * @Author: Harald Ederer
 * @Date: 16.10.2022
 */
public class UserInterface {
    private static final DB_Persistence db = new DB_Persistence();
    private static final Scanner scanner = new Scanner(System.in);

    public UserInterface() throws InvalidInputException {
        startMenue();
    }

    /**
     * Main start Menue for User selection and interaction
     */
    private static void startMenue() throws InvalidInputException {
        try {
            String menue = """
                    *********************************
                    * Willkommen                    *
                    * Bitte waehlen Sie eine Option *
                    * (C) Create                    *
                    * (R) Read                      *
                    * (U) Update                    *
                    * (D) Delete                    *
                    * (E) Exit                      *
                    *********************************
                    Auswahl:
                    """;
            while (true) {
                System.out.println(menue);
                String input = scanner.nextLine();
                if (input == null || input.isBlank()) {
                    throw new InvalidInputException();
                }
                switch (input.toUpperCase()) {
                    case "C" -> createPerson();
                    case "R" -> readPerson();
                    case "U" -> updatePerson();
                    case "D" -> deletePerson();
                    case "E" -> exit();
                    default -> System.exit(0);
                }
            }
        } catch (Exception e) {
            throw new InvalidInputException();
        }
    }

    /**
     * Create a new Person to persist
     */
    private static void createPerson() throws InvalidInputException {
        System.out.println("Erstelle Person: ");
        Person person = new Person();
        System.out.println("Bitte geben Sie den Vornamen ein: ");
        String vname = scanner.nextLine();
        person.setVname(vname);
        System.out.println("Bitte geben Sie den Nachnahmen ein: ");
        String nname = scanner.nextLine();
        person.setNname(nname);
        System.out.println("Bitte geben Sie das Gehalt ein: ");
        int gehalt = Integer.parseInt(scanner.nextLine());
        person.setGehalt(gehalt);
        db.addPerson(person);
        System.out.println("Eine weitere Person eingeben? (y or n)");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("Y")) {
            createPerson();
        } else if (input.equalsIgnoreCase("N")) {
            startMenue();
        } else {
            System.out.println("Falsche Eingabe!");
            startMenue();
        }
    }

    /**
     * Select all Person or one person to read
     */
    private static void readPerson() throws InvalidInputException {
        try {
            System.out.println("Lese Person: ");
            String submenue = """
                    **************************************
                    *                                    *
                    * Bitte waehlen Sie eine Option      *
                    * (1) alle Personen lesen            *
                    * (2) eine bestimmte Person lesen    *
                    * (0) zurueck zum Hauptmenue         *
                    *                                    *
                    **************************************
                    """;
            while (true) {
                System.out.println(submenue);
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= 0 && 2 >= input) {
                    switch (input) {
                        case 0 -> startMenue();
                        case 1 -> readAllPersons();
                        case 2 -> readOnePerson();
                    }
                } else {
                    System.out.println("Nr 0,1 oder 2 waehlen!");
                    readPerson();
                }
            }
        } catch (Exception ex) {
            throw new InvalidInputException();
        }
    }

    /**
     * Read and show all Persons
     */
    private static void readAllPersons() throws InvalidInputException {
        System.out.println(db.readAll().toString());
        startMenue();
    }

    /**
     * Select a number for one Person to read and show
     */
    private static void readOnePerson() throws InvalidInputException {
        System.out.println("Bitte geben Sie die Nummer der gewuenschten Person ein: ");
        int nr = Integer.parseInt(scanner.nextLine());
        System.out.println(db.readPerson(nr).toString());
        System.out.println("Eine weitere Person lesen? (y or n)");
        String selection = scanner.nextLine();
        if (selection.equalsIgnoreCase("Y")) {
            readOnePerson();
        } else if (selection.equalsIgnoreCase("N")) {
            startMenue();
        } else {
            System.out.println("Falsch eingabe: ");
            System.out.println("Eine weitere Person aktualisieren? (y or n)");
            if (selection.equalsIgnoreCase("Y")) {
                readOnePerson();
            } else if (selection.equalsIgnoreCase("N")) {
                startMenue();
            } else {
                System.out.println("Falsche Eingabe!");
                startMenue();
            }
        }
    }

    /**
     * Update an existing Persons data
     */
    private static void updatePerson() throws InvalidInputException {
        System.out.println("Aktualisiere Person: ");
        System.out.println("Bitte geben Sie die Nummer der gewuenschten Person ein: ");
        int input = Integer.parseInt(scanner.nextLine());

        Person person = db.readPerson(input).get(0);
        System.out.println("Vorname (ALT): " + person.getVname());
        System.out.println("Vorname (Neu): ");
        String vnameNew = scanner.nextLine();
        person.setVname(vnameNew);

        System.out.println("Nachname (ALT): " + person.getNname());
        System.out.println("Nachname (Neu): ");
        String nnameNew = scanner.nextLine();
        person.setNname(nnameNew);

        System.out.println("Gehalt (ALT): " + person.getGehalt());
        System.out.println("Gehalt (Neu): ");
        int gehaltNew = Integer.parseInt(scanner.nextLine());
        person.setGehalt(gehaltNew);

        db.updatePerson(person);

        System.out.println("Eine weitere Person aktualisieren? (y or n)");
        String selection = scanner.nextLine();
        if (selection.equalsIgnoreCase("Y")) {
            updatePerson();
        } else if (selection.equalsIgnoreCase("N")) {
            startMenue();
        } else {
            System.out.println("Falsche eingabe: ");
            System.out.println("Eine weitere Person aktualisieren? (y or n)");
            if (selection.equalsIgnoreCase("Y")) {
                updatePerson();
            }else if (selection.equalsIgnoreCase("N")) {
                startMenue();
            }else{
                System.out.println("Falsche Eingabe!");
                startMenue();
            }
        }
    }

    /**
     * Delete an existing Person
     */
    private static void deletePerson() throws InvalidInputException {
        System.out.println("Delete Person: ");
        System.out.println("Bitte geben Sie die Nummer der zu loeschenden Person ein: ");
        int input = Integer.parseInt(scanner.nextLine());
        db.deletePerson(input);
        System.out.println("Eine weitere Person loeschen? (y or n)");
        String selection = scanner.nextLine();
        if (selection.equalsIgnoreCase("Y")) {
            deletePerson();
        } else if (selection.equalsIgnoreCase("N")) {
            startMenue();
        } else {
            System.out.println("Falsch eingabe: ");
            System.out.println("Eine weitere Person aktualisieren? (y or n)");
            if (selection.equalsIgnoreCase("Y")) {
                deletePerson();
            }
            if (selection.equalsIgnoreCase("N")) {
                startMenue();
            }
        }
    }

    /**
     * Correct Exit
     */
    private static void exit() {
        System.out.println("Thank you for joining! :-)");
        System.exit(0);
    }

}
