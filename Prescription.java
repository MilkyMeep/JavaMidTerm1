package medicationtracking;

import java.time.LocalDate;

/**
 * The Prescription class represents a prescription issued by a doctor for a patient.
 * It links together a Doctor, Patient, and Medication with prescription-specific
 * information including issuance date and expiration date.
 * 
 * @author Your Name
 * @version 1.0
 */
public class Prescription {
    private int id;
    private Doctor doctor;
    private Patient patient;
    private Medication medication;
    private LocalDate issueDate;
    private LocalDate prescriptionExpiry;
    private boolean isActive;

    /**
     * Default constructor for Prescription class.
     * Sets issue date to current date and expiry to one year from now.
     */
    public Prescription() {
        this.issueDate = LocalDate.now();
        this.prescriptionExpiry = issueDate.plusYears(1);
        this.isActive = true;
    }

    /**
     * Parameterized constructor for Prescription class.
     * Automatically sets issue date to current date and expiry to one year from now.
     * 
     * @param id the unique identifier for the prescription
     * @param doctor the prescribing doctor
     * @param patient the patient receiving the prescription
     * @param medication the prescribed medication
     */
    public Prescription(int id, Doctor doctor, Patient patient, Medication medication) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.issueDate = LocalDate.now();
        this.prescriptionExpiry = issueDate.plusYears(1);
        this.isActive = true;
    }

    /**
     * Full parameterized constructor for Prescription class.
     * 
     * @param id the unique identifier for the prescription
     * @param doctor the prescribing doctor
     * @param patient the patient receiving the prescription
     * @param medication the prescribed medication
     * @param issueDate the date the prescription was issued
     * @param prescriptionExpiry the expiration date of the prescription
     */
    public Prescription(int id, Doctor doctor, Patient patient, Medication medication, 
                       LocalDate issueDate, LocalDate prescriptionExpiry) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.medication = medication;
        this.issueDate = issueDate;
        this.prescriptionExpiry = prescriptionExpiry;
        this.isActive = true;
    }

    // Business Methods

    /**
     * Checks if the prescription is expired.
     * 
     * @return true if expired, false otherwise
     */
    public boolean isExpired() {
        return LocalDate.now().isAfter(prescriptionExpiry);
    }

    /**
     * Checks if the prescription is valid (not expired and active).
     * 
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        return isActive && !isExpired();
    }

    /**
     * Activates the prescription.
     */
    public void activate() {
        this.isActive = true;
    }

    /**
     * Deactivates the prescription.
     */
    public void deactivate() {
        this.isActive = false;
    }

    /**
     * Renews the prescription for another year from today.
     */
    public void renew() {
        this.issueDate = LocalDate.now();
        this.prescriptionExpiry = issueDate.plusYears(1);
        this.isActive = true;
    }

    /**
     * Fills the prescription by reducing medication stock by 1.
     * Only fills if prescription is valid and medication is in stock.
     * 
     * @return true if prescription was filled successfully, false otherwise
     */
    public boolean fillPrescription() {
        if (!isValid()) {
            System.out.println("Cannot fill - prescription is not valid.");
            return false;
        }
        
        if (medication == null) {
            System.out.println("Cannot fill - no medication specified.");
            return false;
        }
        
        if (!medication.isInStock()) {
            System.out.println("Cannot fill - medication out of stock.");
            return false;
        }
        
        // Reduce stock by 1 (assuming one unit per prescription fill)
        if (medication.reduceStock(1)) {
            System.out.println("Prescription filled successfully for " + patient.getName());
            return true;
        } else {
            System.out.println("Failed to fill prescription - stock reduction failed.");
            return false;
        }
    }

    /**
     * Checks if this prescription was issued within the past year.
     * 
     * @return true if issued within past year, false otherwise
     */
    public boolean isFromPastYear() {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        return !issueDate.isBefore(oneYearAgo);
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the prescription.
     * 
     * @return the prescription's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the prescription.
     * 
     * @param id the prescription's ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the prescribing doctor.
     * 
     * @return the doctor object
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets the prescribing doctor.
     * 
     * @param doctor the doctor to set
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Gets the patient receiving the prescription.
     * 
     * @return the patient object
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the patient receiving the prescription.
     * 
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Gets the prescribed medication.
     * 
     * @return the medication object
     */
    public Medication getMedication() {
        return medication;
    }

    /**
     * Sets the prescribed medication.
     * 
     * @param medication the medication to set
     */
    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    /**
     * Gets the issue date of the prescription.
     * 
     * @return the issue date
     */
    public LocalDate getIssueDate() {
        return issueDate;
    }

    /**
     * Sets the issue date of the prescription.
     * 
     * @param issueDate the issue date to set
     */
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Gets the prescription expiry date.
     * 
     * @return the expiry date
     */
    public LocalDate getPrescriptionExpiry() {
        return prescriptionExpiry;
    }

    /**
     * Sets the prescription expiry date.
     * 
     * @param prescriptionExpiry the expiry date to set
     */
    public void setPrescriptionExpiry(LocalDate prescriptionExpiry) {
        this.prescriptionExpiry = prescriptionExpiry;
    }

    /**
     * Checks if the prescription is active.
     * 
     * @return true if active, false otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Sets the active status of the prescription.
     * 
     * @param isActive the active status to set
     */
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Returns a string representation of the Prescription object.
     * 
     * @return a string containing the prescription details
     */
    @Override
    public String toString() {
        String status = isValid() ? "Active" : (isExpired() ? "Expired" : "Inactive");
        return "Prescription ID: " + id + 
               ", Medication: " + (medication != null ? medication.getName() : "None") +
               ", Patient: " + (patient != null ? patient.getName() : "None") +
               ", Doctor: " + (doctor != null ? "Dr. " + doctor.getName() : "None") +
               ", Status: " + status +
               ", Expires: " + prescriptionExpiry;
    }

    /**
     * Displays the prescription's information to the console.
     */
    public void displayInfo() {
        System.out.println(this.toString());
    }

    /**
     * Displays detailed prescription information.
     */
    public void displayDetailedInfo() {
        System.out.println("=== Prescription Details ===");
        System.out.println("Prescription ID: " + id);
        System.out.println("Status: " + (isValid() ? "VALID" : "INVALID"));
        System.out.println("Issue Date: " + issueDate);
        System.out.println("Expiry Date: " + prescriptionExpiry);
        System.out.println("Active: " + (isActive ? "Yes" : "No"));
        
        if (doctor != null) {
            System.out.println("Prescribing Doctor: Dr. " + doctor.getName() + " (" + doctor.getSpecialization() + ")");
        } else {
            System.out.println("Prescribing Doctor: Not specified");
        }
        
        if (patient != null) {
            System.out.println("Patient: " + patient.getName() + " (ID: " + patient.getId() + ")");
        } else {
            System.out.println("Patient: Not specified");
        }
        
        if (medication != null) {
            System.out.println("Medication: " + medication.getName() + " - " + medication.getDose());
            System.out.println("Medication in stock: " + (medication.isInStock() ? "Yes" : "No"));
            System.out.println("Medication expiry status: " + (medication.isExpired() ? "EXPIRED" : "Valid"));
        } else {
            System.out.println("Medication: Not specified");
        }
        System.out.println("============================");
    }

    /**
     * Checks equality based on prescription ID.
     * 
     * @param obj the object to compare
     * @return true if prescriptions have the same ID
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Prescription that = (Prescription) obj;
        return id == that.id;
    }

    /**
     * Generates hash code based on prescription ID.
     * 
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}