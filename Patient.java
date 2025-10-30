package medicationtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * The Patient class represents a patient in the pharmacy system.
 * It extends the Person class and adds patient-specific attributes
 * such as a list of current medications and active prescriptions.
 * 
 * @author Your Name
 * @version 1.0
 */
public class Patient extends Person {
    private List<Medication> currentMedications;
    private List<Prescription> activePrescriptions;

    /**
     * Default constructor for Patient class.
     * Initializes empty lists for medications and prescriptions.
     */
    public Patient() {
        super();
        this.currentMedications = new ArrayList<>();
        this.activePrescriptions = new ArrayList<>();
    }

    /**
     * Parameterized constructor for Patient class.
     * 
     * @param id the unique identifier for the patient
     * @param name the name of the patient
     * @param age the age of the patient
     * @param phoneNumber the contact information of the patient
     */
    public Patient(int id, String name, int age, String phoneNumber) {
        super(id, name, age, phoneNumber);
        this.currentMedications = new ArrayList<>();
        this.activePrescriptions = new ArrayList<>();
    }

    // Business Methods

    /**
     * Adds a medication to the patient's current medications list.
     * 
     * @param medication the medication to add
     */
    public void addMedication(Medication medication) {
        if (medication != null && !currentMedications.contains(medication)) {
            currentMedications.add(medication);
        }
    }

    /**
     * Removes a medication from the patient's current medications list.
     * 
     * @param medication the medication to remove
     * @return true if medication was removed, false otherwise
     */
    public boolean removeMedication(Medication medication) {
        return currentMedications.remove(medication);
    }

    /**
     * Adds a prescription to the patient's active prescriptions list.
     * 
     * @param prescription the prescription to add
     */
    public void addPrescription(Prescription prescription) {
        if (prescription != null && !activePrescriptions.contains(prescription)) {
            activePrescriptions.add(prescription);
            // Also add the medication from the prescription to current medications
            if (prescription.getMedication() != null) {
                addMedication(prescription.getMedication());
            }
        }
    }

    /**
     * Removes a prescription from the patient's active prescriptions list.
     * 
     * @param prescription the prescription to remove
     * @return true if prescription was removed, false otherwise
     */
    public boolean removePrescription(Prescription prescription) {
        return activePrescriptions.remove(prescription);
    }

    /**
     * Gets all medications that are about to expire based on a threshold.
     * 
     * @return list of expiring medications
     */
    public List<Medication> getExpiringMedications() {
        List<Medication> expiringMeds = new ArrayList<>();
        for (Medication med : currentMedications) {
            if (med.isExpired()) {
                expiringMeds.add(med);
            }
        }
        return expiringMeds;
    }

    // Getters and Setters

    /**
     * Gets the list of current medications for the patient.
     * 
     * @return list of current medications
     */
    public List<Medication> getCurrentMedications() {
        return new ArrayList<>(currentMedications); // Return copy to preserve encapsulation
    }

    /**
     * Sets the list of current medications for the patient.
     * 
     * @param currentMedications the list of medications to set
     */
    public void setCurrentMedications(List<Medication> currentMedications) {
        this.currentMedications = new ArrayList<>(currentMedications);
    }

    /**
     * Gets the list of active prescriptions for the patient.
     * 
     * @return list of active prescriptions
     */
    public List<Prescription> getActivePrescriptions() {
        return new ArrayList<>(activePrescriptions); // Return copy to preserve encapsulation
    }

    /**
     * Sets the list of active prescriptions for the patient.
     * 
     * @param activePrescriptions the list of prescriptions to set
     */
    public void setActivePrescriptions(List<Prescription> activePrescriptions) {
        this.activePrescriptions = new ArrayList<>(activePrescriptions);
    }

    /**
     * Returns a string representation of the Patient object.
     * 
     * @return a string containing the patient's details and medication count
     */
    @Override
    public String toString() {
        return super.toString() + 
               ", Medications: " + currentMedications.size() + 
               ", Active Prescriptions: " + activePrescriptions.size();
    }

    /**
     * Displays detailed patient information including medications and prescriptions.
     */
    public void displayDetailedInfo() {
        System.out.println("=== Patient Details ===");
        super.displayInfo();
        
        System.out.println("\nCurrent Medications (" + currentMedications.size() + "):");
        if (currentMedications.isEmpty()) {
            System.out.println("  No current medications");
        } else {
            for (int i = 0; i < currentMedications.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + currentMedications.get(i).getName());
            }
        }
        
        System.out.println("\nActive Prescriptions (" + activePrescriptions.size() + "):");
        if (activePrescriptions.isEmpty()) {
            System.out.println("  No active prescriptions");
        } else {
            for (int i = 0; i < activePrescriptions.size(); i++) {
                Prescription script = activePrescriptions.get(i);
                System.out.println("  " + (i + 1) + ". " + script.getMedication().getName() + 
                                 " (Prescribed by Dr. " + script.getDoctor().getName() + ")");
            }
        }
        System.out.println("=======================");
    }
}