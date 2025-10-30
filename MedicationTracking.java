package medicationtracking;

import java.util.List;
import java.util.Scanner;

/**
 * The MedicationTracking class serves as the main menu and user interface
 * for the Pharmacy Medication Tracking System.
 * It provides an interactive menu system for managing patients, doctors,
 * medications, and prescriptions.
 * 
 * @author Your Name
 * @version 1.0
 */
public class MedicationTracking {
    private MedicationTrackingSystem system;
    private Scanner scanner;

    /**
     * Constructor for MedicationTracking class.
     * Initializes the system and scanner.
     */
    public MedicationTracking() {
        this.system = new MedicationTrackingSystem();
        this.scanner = new Scanner(System.in);
        initializeSampleData(); // Add some sample data for testing
    }

    /**
     * Initializes the system with some sample data for testing.
     */
    private void initializeSampleData() {
        // Add sample doctors
        system.addDoctor("Sarah Johnson", 45, "555-0101", "Cardiology");
        system.addDoctor("Michael Chen", 38, "555-0102", "Pediatrics");
        system.addDoctor("Emily Davis", 52, "555-0103", "Internal Medicine");

        // Add sample medications
        system.addMedication("Lisinopril", "10mg", 100);
        system.addMedication("Amoxicillin", "500mg", 50);
        system.addMedication("Metformin", "850mg", 75);
        system.addMedication("Atorvastatin", "20mg", 60);

        // Add sample patients
        system.addPatient("John Smith", 65, "555-0201");
        system.addPatient("Maria Garcia", 42, "555-0202");
        system.addPatient("Robert Brown", 58, "555-0203");

        // Add some patient-doctor relationships
        system.addPatientToDoctor(1, 1);
        system.addPatientToDoctor(1, 2);
        system.addPatientToDoctor(2, 3);

        // Add sample prescriptions
        system.acceptPrescription(1, 1, 1); // Dr. Johnson prescribes Lisinopril to John Smith
        system.acceptPrescription(2, 3, 2); // Dr. Chen prescribes Amoxicillin to Robert Brown
        
        System.out.println("Sample data initialized successfully!\n");
    }

    /**
     * Main method that starts the application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        MedicationTracking medicationTracking = new MedicationTracking();
        medicationTracking.startMenu();
    }

    /**
     * Starts the main menu loop.
     */
    public void startMenu() {
        boolean exit = false;

        while (!exit) {
            displayMainMenu();
            int option = getIntInput("Choose an option: ");
            
            switch (option) {
                case 1:
                    addANewPatient();
                    break;
                case 2:
                    addANewDoctor();
                    break;
                case 3:
                    addNewMedicationToPharmacy();
                    break;
                case 4:
                    printPharmacyReport();
                    break;
                case 5:
                    checkExpiredMeds();
                    break;
                case 6:
                    processANewScript();
                    break;
                case 7:
                    printScriptsForSpecificDoctor();
                    break;
                case 8:
                    restockPharmacyDrugs();
                    break;
                case 9:
                    printAllScriptsForPatientByName();
                    break;
                case 10:
                    generatePastYearPrescriptionsReport();
                    break;
                case 11:
                    searchEntities();
                    break;
                case 12:
                    exit = true;
                    System.out.println("Exiting The System! Good Bye!");
                    break;
                default:
                    System.out.println("Invalid option! Please choose 1-12.");
            }
            
            if (!exit) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine(); // Wait for user to press Enter
            }
        }
        
        scanner.close();
    }

    /**
     * Displays the main menu options.
     */
    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(55));
        System.out.println("    WELCOME TO THE PHARMACY MED TRACKING SYSTEM");
        System.out.println("=".repeat(55));
        System.out.println("1:  Add A New Patient");
        System.out.println("2:  Add A New Doctor");
        System.out.println("3:  Add A New Medication To The Pharmacy");
        System.out.println("4:  Print System Report");
        System.out.println("5:  Check If Meds Are Expired");
        System.out.println("6:  Process A New Prescription");
        System.out.println("7:  Print All Scripts For Specific Doctor");
        System.out.println("8:  Restock the drugs in the pharmacy");
        System.out.println("9:  Print all scripts for specific patient");
        System.out.println("10: Generate past year prescriptions report");
        System.out.println("11: Search for patients, doctors, or medications");
        System.out.println("12: Exit");
        System.out.println("=".repeat(55));
    }

    // ===== MENU OPTION IMPLEMENTATIONS =====

    private void addANewPatient() {
        System.out.println("\n--- ADD NEW PATIENT ---");
        String name = getStringInput("Enter patient name: ");
        int age = getIntInput("Enter patient age: ");
        String phone = getStringInput("Enter patient phone number: ");
        
        Patient patient = system.addPatient(name, age, phone);
        System.out.println("Patient added successfully!");
        patient.displayInfo();
    }

    private void addANewDoctor() {
        System.out.println("\n--- ADD NEW DOCTOR ---");
        String name = getStringInput("Enter doctor name: ");
        int age = getIntInput("Enter doctor age: ");
        String phone = getStringInput("Enter doctor phone number: ");
        String specialization = getStringInput("Enter doctor specialization: ");
        
        Doctor doctor = system.addDoctor(name, age, phone, specialization);
        System.out.println("Doctor added successfully!");
        doctor.displayInfo();
    }

    private void addNewMedicationToPharmacy() {
        System.out.println("\n--- ADD NEW MEDICATION ---");
        String name = getStringInput("Enter medication name: ");
        String dose = getStringInput("Enter medication dose (e.g., 10mg): ");
        int quantity = getIntInput("Enter initial quantity in stock: ");
        
        Medication medication = system.addMedication(name, dose, quantity);
        System.out.println("Medication added successfully!");
        medication.displayInfo();
    }

    private void printPharmacyReport() {
        system.generateSystemReport();
    }

    private void checkExpiredMeds() {
        system.checkExpiredMedications();
    }

    private void processANewScript() {
        System.out.println("\n--- PROCESS NEW PRESCRIPTION ---");
        
        // Show available doctors
        System.out.println("Available Doctors:");
        system.getDoctors().forEach(d -> System.out.println("  ID: " + d.getId() + " - Dr. " + d.getName()));
        
        // Show available patients
        System.out.println("\nAvailable Patients:");
        system.getPatients().forEach(p -> System.out.println("  ID: " + p.getId() + " - " + p.getName()));
        
        // Show available medications
        System.out.println("\nAvailable Medications:");
        system.getMedications().forEach(m -> System.out.println("  ID: " + m.getId() + " - " + m.getName() + " (" + m.getDose() + ")"));
        
        int doctorId = getIntInput("\nEnter doctor ID: ");
        int patientId = getIntInput("Enter patient ID: ");
        int medicationId = getIntInput("Enter medication ID: ");
        
        system.acceptPrescription(doctorId, patientId, medicationId);
    }

    private void printScriptsForSpecificDoctor() {
        System.out.println("\n--- PRESCRIPTIONS BY DOCTOR ---");
        System.out.println("Available Doctors:");
        system.getDoctors().forEach(d -> System.out.println("  ID: " + d.getId() + " - Dr. " + d.getName()));
        
        int doctorId = getIntInput("Enter doctor ID: ");
        system.printPrescriptionsByDoctor(doctorId);
    }

    private void restockPharmacyDrugs() {
        System.out.println("\n--- RESTOCK MEDICATIONS ---");
        System.out.println("1: Restock all medications randomly");
        System.out.println("2: Restock specific medication");
        
        int choice = getIntInput("Choose option: ");
        
        if (choice == 1) {
            system.restockAllMedications();
        } else if (choice == 2) {
            System.out.println("Available Medications:");
            system.getMedications().forEach(m -> 
                System.out.println("  ID: " + m.getId() + " - " + m.getName() + " (Current stock: " + m.getQuantityInStock() + ")"));
            
            int medId = getIntInput("Enter medication ID: ");
            int amount = getIntInput("Enter amount to add: ");
            
            if (system.restockMedication(medId, amount)) {
                System.out.println("Restocking successful!");
            } else {
                System.out.println("Restocking failed! Check medication ID.");
            }
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private void printAllScriptsForPatientByName() {
        System.out.println("\n--- PRESCRIPTIONS FOR PATIENT ---");
        String patientName = getStringInput("Enter patient name to search: ");
        
        List<Patient> patients = system.searchPatientsByName(patientName);
        if (patients.isEmpty()) {
            System.out.println("No patients found with that name.");
            return;
        }
        
        System.out.println("Found patients:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println((i + 1) + ": " + patients.get(i).getName() + " (ID: " + patients.get(i).getId() + ")");
        }
        
        int choice = getIntInput("Select patient (enter number): ");
        if (choice > 0 && choice <= patients.size()) {
            Patient patient = patients.get(choice - 1);
            System.out.println("\nPrescriptions for " + patient.getName() + ":");
            patient.getActivePrescriptions().forEach(Prescription::displayInfo);
        } else {
            System.out.println("Invalid selection!");
        }
    }

    private void generatePastYearPrescriptionsReport() {
        system.generatePastYearPrescriptionsReport();
    }

    private void searchEntities() {
        System.out.println("\n--- SEARCH ---");
        System.out.println("1: Search patients");
        System.out.println("2: Search doctors");
        System.out.println("3: Search medications");
        
        int choice = getIntInput("Choose option: ");
        String searchTerm = getStringInput("Enter search term: ");
        
        switch (choice) {
            case 1:
                List<Patient> patients = system.searchPatientsByName(searchTerm);
                System.out.println("Found " + patients.size() + " patient(s):");
                patients.forEach(Patient::displayInfo);
                break;
            case 2:
                List<Doctor> doctors = system.searchDoctorsByName(searchTerm);
                System.out.println("Found " + doctors.size() + " doctor(s):");
                doctors.forEach(Doctor::displayInfo);
                break;
            case 3:
                List<Medication> medications = system.searchMedicationsByName(searchTerm);
                System.out.println("Found " + medications.size() + " medication(s):");
                medications.forEach(Medication::displayInfo);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    // ===== HELPER METHODS =====

    /**
     * Gets integer input from user with validation.
     * 
     * @param prompt the prompt to display
     * @return the integer value entered by user
     */
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    /**
     * Gets string input from user.
     * 
     * @param prompt the prompt to display
     * @return the string value entered by user
     */
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}