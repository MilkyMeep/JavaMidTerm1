package medicationtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * The Doctor class represents a doctor in the pharmacy system.
 * It extends the Person class and adds doctor-specific attributes
 * such as specialization and a list of patients they are managing.
 * 
 * @author Your Name
 * @version 1.0
 */
public class Doctor extends Person {
    private String specialization;
    private List<Patient> patientList;

    /**
     * Default constructor for Doctor class.
     * Initializes an empty list for patients.
     */
    public Doctor() {
        super();
        this.patientList = new ArrayList<>();
        this.specialization = "General Practice";
    }

    /**
     * Parameterized constructor for Doctor class.
     * 
     * @param id the unique identifier for the doctor
     * @param name the name of the doctor
     * @param age the age of the doctor
     * @param phoneNumber the contact information of the doctor
     * @param specialization the medical specialization of the doctor
     */
    public Doctor(int id, String name, int age, String phoneNumber, String specialization) {
        super(id, name, age, phoneNumber);
        this.specialization = specialization;
        this.patientList = new ArrayList<>();
    }

    // Business Methods

    /**
     * Adds a patient to the doctor's patient list.
     * 
     * @param patient the patient to add
     * @return true if patient was added, false if patient was already in the list or null
     */
    public boolean addPatient(Patient patient) {
        if (patient != null && !patientList.contains(patient)) {
            patientList.add(patient);
            return true;
        }
        return false;
    }

    /**
     * Removes a patient from the doctor's patient list.
     * 
     * @param patient the patient to remove
     * @return true if patient was removed, false otherwise
     */
    public boolean removePatient(Patient patient) {
        return patientList.remove(patient);
    }

    /**
     * Checks if the doctor is managing a specific patient.
     * 
     * @param patient the patient to check
     * @return true if patient is in the doctor's list, false otherwise
     */
    public boolean hasPatient(Patient patient) {
        return patientList.contains(patient);
    }

    /**
     * Gets the number of patients under this doctor's care.
     * 
     * @return the number of patients
     */
    public int getPatientCount() {
        return patientList.size();
    }

    /**
     * Finds a patient by their ID in the doctor's patient list.
     * 
     * @param patientId the ID of the patient to find
     * @return the Patient object if found, null otherwise
     */
    public Patient findPatientById(int patientId) {
        for (Patient patient : patientList) {
            if (patient.getId() == patientId) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Finds a patient by their name in the doctor's patient list.
     * 
     * @param patientName the name of the patient to find
     * @return the Patient object if found, null otherwise
     */
    public Patient findPatientByName(String patientName) {
        for (Patient patient : patientList) {
            if (patient.getName().equalsIgnoreCase(patientName)) {
                return patient;
            }
        }
        return null;
    }

    // Getters and Setters

    /**
     * Gets the medical specialization of the doctor.
     * 
     * @return the doctor's specialization
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the medical specialization of the doctor.
     * 
     * @param specialization the specialization to set
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Gets the list of patients managed by this doctor.
     * 
     * @return list of patients
     */
    public List<Patient> getPatientList() {
        return new ArrayList<>(patientList); // Return copy to preserve encapsulation
    }

    /**
     * Sets the list of patients managed by this doctor.
     * 
     * @param patientList the list of patients to set
     */
    public void setPatientList(List<Patient> patientList) {
        this.patientList = new ArrayList<>(patientList);
    }

    /**
     * Returns a string representation of the Doctor object.
     * 
     * @return a string containing the doctor's details and patient count
     */
    @Override
    public String toString() {
        return super.toString() + 
               ", Specialization: " + specialization + 
               ", Patients: " + patientList.size();
    }

    /**
     * Displays detailed doctor information including patient list.
     */
    public void displayDetailedInfo() {
        System.out.println("=== Doctor Details ===");
        super.displayInfo();
        System.out.println("Specialization: " + specialization);
        
        System.out.println("\nPatients Under Care (" + patientList.size() + "):");
        if (patientList.isEmpty()) {
            System.out.println("  No patients currently assigned");
        } else {
            for (int i = 0; i < patientList.size(); i++) {
                Patient patient = patientList.get(i);
                System.out.println("  " + (i + 1) + ". " + patient.getName() + 
                                 " (ID: " + patient.getId() + ", Age: " + patient.getAge() + ")");
            }
        }
        System.out.println("======================");
    }

    /**
     * Displays a summary of the doctor's information.
     */
    public void displaySummary() {
        System.out.println("Dr. " + getName() + " - " + specialization + 
                          " (" + patientList.size() + " patients)");
    }
}