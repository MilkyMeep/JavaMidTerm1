package medicationtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The MedicationTrackingSystem class manages the entire pharmacy system.
 * It contains lists of patients, medications, and doctors, and provides
 * comprehensive functionality for managing the pharmacy operations.
 * 
 * @author Your Name
 * @version 1.0
 */
public class MedicationTrackingSystem {
    private List<Patient> patients;
    private List<Medication> medications;
    private List<Doctor> doctors;
    private List<Prescription> prescriptions;
    private int nextPatientId;
    private int nextMedicationId;
    private int nextDoctorId;
    private int nextPrescriptionId;

    /**
     * Default constructor for MedicationTrackingSystem.
     * Initializes all lists and ID counters.
     */
    public MedicationTrackingSystem() {
        this.patients = new ArrayList<>();
        this.medications = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
        this.nextPatientId = 1;
        this.nextMedicationId = 1;
        this.nextDoctorId = 1;
        this.nextPrescriptionId = 1;
    }

    // ===== PATIENT MANAGEMENT =====

    /**
     * Adds a new patient to the system.
     * 
     * @param name the patient's name
     * @param age the patient's age
     * @param phoneNumber the patient's phone number
     * @return the created Patient object
     */
    public Patient addPatient(String name, int age, String phoneNumber) {
        Patient patient = new Patient(nextPatientId++, name, age, phoneNumber);
        patients.add(patient);
        return patient;
    }

    /**
     * Adds an existing patient object to the system.
     * 
     * @param patient the patient to add
     * @return true if added successfully, false if patient already exists
     */
    public boolean addPatient(Patient patient) {
        if (patient != null && !patients.contains(patient)) {
            patients.add(patient);
            return true;
        }
        return false;
    }

    /**
     * Deletes a patient from the system by ID.
     * 
     * @param patientId the ID of the patient to delete
     * @return true if deleted, false if not found
     */
    public boolean deletePatient(int patientId) {
        Patient patient = findPatientById(patientId);
        if (patient != null) {
            // Remove patient from all doctors' lists
            for (Doctor doctor : doctors) {
                doctor.removePatient(patient);
            }
            // Remove patient's prescriptions
            prescriptions.removeIf(p -> p.getPatient() != null && p.getPatient().equals(patient));
            return patients.remove(patient);
        }
        return false;
    }

    /**
     * Edits a patient's information.
     * 
     * @param patientId the ID of the patient to edit
     * @param name the new name (null to keep current)
     * @param age the new age (-1 to keep current)
     * @param phoneNumber the new phone number (null to keep current)
     * @return true if edited successfully, false if patient not found
     */
    public boolean editPatient(int patientId, String name, int age, String phoneNumber) {
        Patient patient = findPatientById(patientId);
        if (patient != null) {
            if (name != null) patient.setName(name);
            if (age != -1) patient.setAge(age);
            if (phoneNumber != null) patient.setPhoneNumber(phoneNumber);
            return true;
        }
        return false;
    }

    // ===== DOCTOR MANAGEMENT =====

    /**
     * Adds a new doctor to the system.
     * 
     * @param name the doctor's name
     * @param age the doctor's age
     * @param phoneNumber the doctor's phone number
     * @param specialization the doctor's specialization
     * @return the created Doctor object
     */
    public Doctor addDoctor(String name, int age, String phoneNumber, String specialization) {
        Doctor doctor = new Doctor(nextDoctorId++, name, age, phoneNumber, specialization);
        doctors.add(doctor);
        return doctor;
    }

    /**
     * Deletes a doctor from the system by ID.
     * 
     * @param doctorId the ID of the doctor to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteDoctor(int doctorId) {
        Doctor doctor = findDoctorById(doctorId);
        if (doctor != null) {
            // Remove doctor from prescriptions
            for (Prescription prescription : prescriptions) {
                if (prescription.getDoctor() != null && prescription.getDoctor().equals(doctor)) {
                    prescription.setDoctor(null);
                }
            }
            return doctors.remove(doctor);
        }
        return false;
    }

    /**
     * Edits a doctor's information.
     * 
     * @param doctorId the ID of the doctor to edit
     * @param name the new name (null to keep current)
     * @param age the new age (-1 to keep current)
     * @param phoneNumber the new phone number (null to keep current)
     * @param specialization the new specialization (null to keep current)
     * @return true if edited successfully, false if doctor not found
     */
    public boolean editDoctor(int doctorId, String name, int age, String phoneNumber, String specialization) {
        Doctor doctor = findDoctorById(doctorId);
        if (doctor != null) {
            if (name != null) doctor.setName(name);
            if (age != -1) doctor.setAge(age);
            if (phoneNumber != null) doctor.setPhoneNumber(phoneNumber);
            if (specialization != null) doctor.setSpecialization(specialization);
            return true;
        }
        return false;
    }

    // ===== MEDICATION MANAGEMENT =====

    /**
     * Adds a new medication to the system.
     * 
     * @param name the medication name
     * @param dose the medication dose
     * @param quantityInStock the initial quantity in stock
     * @return the created Medication object
     */
    public Medication addMedication(String name, String dose, int quantityInStock) {
        Medication medication = new Medication(nextMedicationId++, name, dose, quantityInStock);
        medications.add(medication);
        return medication;
    }

    /**
     * Deletes a medication from the system by ID.
     * 
     * @param medicationId the ID of the medication to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteMedication(int medicationId) {
        Medication medication = findMedicationById(medicationId);
        if (medication != null) {
            // Remove medication from prescriptions
            for (Prescription prescription : prescriptions) {
                if (prescription.getMedication() != null && prescription.getMedication().equals(medication)) {
                    prescription.setMedication(null);
                }
            }
            // Remove medication from patients
            for (Patient patient : patients) {
                patient.removeMedication(medication);
            }
            return medications.remove(medication);
        }
        return false;
    }

    /**
     * Edits a medication's information.
     * 
     * @param medicationId the ID of the medication to edit
     * @param name the new name (null to keep current)
     * @param dose the new dose (null to keep current)
     * @param quantityInStock the new quantity (-1 to keep current)
     * @return true if edited successfully, false if medication not found
     */
    public boolean editMedication(int medicationId, String name, String dose, int quantityInStock) {
        Medication medication = findMedicationById(medicationId);
        if (medication != null) {
            if (name != null) medication.setName(name);
            if (dose != null) medication.setDose(dose);
            if (quantityInStock != -1) medication.setQuantityInStock(quantityInStock);
            return true;
        }
        return false;
    }

    // ===== PRESCRIPTION MANAGEMENT =====

    /**
     * Accepts and processes a new prescription from a doctor.
     * 
     * @param doctorId the ID of the prescribing doctor
     * @param patientId the ID of the patient
     * @param medicationId the ID of the medication
     * @return the created Prescription object, or null if failed
     */
    public Prescription acceptPrescription(int doctorId, int patientId, int medicationId) {
        Doctor doctor = findDoctorById(doctorId);
        Patient patient = findPatientById(patientId);
        Medication medication = findMedicationById(medicationId);

        if (doctor == null || patient == null || medication == null) {
            System.out.println("Error: Doctor, patient, or medication not found.");
            return null;
        }

        Prescription prescription = new Prescription(nextPrescriptionId++, doctor, patient, medication);
        prescriptions.add(prescription);
        
        // Add prescription to patient
        patient.addPrescription(prescription);
        
        System.out.println("Prescription accepted successfully!");
        prescription.displayInfo();
        
        return prescription;
    }

    /**
     * Adds a patient to a doctor's patient list.
     * 
     * @param doctorId the ID of the doctor
     * @param patientId the ID of the patient
     * @return true if successful, false if doctor or patient not found
     */
    public boolean addPatientToDoctor(int doctorId, int patientId) {
        Doctor doctor = findDoctorById(doctorId);
        Patient patient = findPatientById(patientId);
        
        if (doctor != null && patient != null) {
            return doctor.addPatient(patient);
        }
        return false;
    }

    // ===== SEARCH FUNCTIONALITY =====

    /**
     * Searches for patients by name (case-insensitive partial match).
     * 
     * @param name the name to search for
     * @return list of matching patients
     */
    public List<Patient> searchPatientsByName(String name) {
        return patients.stream()
                .filter(patient -> patient.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Searches for doctors by name (case-insensitive partial match).
     * 
     * @param name the name to search for
     * @return list of matching doctors
     */
    public List<Doctor> searchDoctorsByName(String name) {
        return doctors.stream()
                .filter(doctor -> doctor.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Searches for medications by name (case-insensitive partial match).
     * 
     * @param name the name to search for
     * @return list of matching medications
     */
    public List<Medication> searchMedicationsByName(String name) {
        return medications.stream()
                .filter(medication -> medication.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    // ===== REPORT GENERATION =====

    /**
     * Generates a comprehensive system report.
     */
    public void generateSystemReport() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           PHARMACY SYSTEM COMPREHENSIVE REPORT");
        System.out.println("=".repeat(60));
        
        System.out.println("\n--- PATIENTS (" + patients.size() + ") ---");
        if (patients.isEmpty()) {
            System.out.println("No patients in system.");
        } else {
            patients.forEach(Patient::displayInfo);
        }
        
        System.out.println("\n--- DOCTORS (" + doctors.size() + ") ---");
        if (doctors.isEmpty()) {
            System.out.println("No doctors in system.");
        } else {
            doctors.forEach(Doctor::displaySummary);
        }
        
        System.out.println("\n--- MEDICATIONS (" + medications.size() + ") ---");
        if (medications.isEmpty()) {
            System.out.println("No medications in system.");
        } else {
            medications.forEach(Medication::displayInfo);
        }
        
        System.out.println("\n--- PRESCRIPTIONS (" + prescriptions.size() + ") ---");
        if (prescriptions.isEmpty()) {
            System.out.println("No prescriptions in system.");
        } else {
            prescriptions.forEach(Prescription::displayInfo);
        }
        
        System.out.println("=".repeat(60));
    }

    /**
     * Checks for and displays expired medications.
     * 
     * @return list of expired medications
     */
    public List<Medication> checkExpiredMedications() {
        List<Medication> expiredMeds = medications.stream()
                .filter(Medication::isExpired)
                .collect(Collectors.toList());
        
        System.out.println("\n--- EXPIRED MEDICATIONS REPORT ---");
        if (expiredMeds.isEmpty()) {
            System.out.println("No expired medications found.");
        } else {
            System.out.println("Found " + expiredMeds.size() + " expired medications:");
            expiredMeds.forEach(med -> {
                System.out.println("  - " + med.getName() + " (Expired: " + med.getExpiryDate() + ")");
            });
        }
        
        return expiredMeds;
    }

    /**
     * Prints all prescriptions issued by a specific doctor.
     * 
     * @param doctorId the ID of the doctor
     */
    public void printPrescriptionsByDoctor(int doctorId) {
        Doctor doctor = findDoctorById(doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }
        
        List<Prescription> doctorPrescriptions = prescriptions.stream()
                .filter(p -> p.getDoctor() != null && p.getDoctor().equals(doctor))
                .collect(Collectors.toList());
        
        System.out.println("\n--- PRESCRIPTIONS BY DR. " + doctor.getName().toUpperCase() + " ---");
        if (doctorPrescriptions.isEmpty()) {
            System.out.println("No prescriptions found for this doctor.");
        } else {
            doctorPrescriptions.forEach(Prescription::displayInfo);
        }
    }

    /**
     * Generates a report of all patients' prescriptions from the past year,
     * summarized to just the drug names.
     */
    public void generatePastYearPrescriptionsReport() {
        List<Prescription> pastYearPrescriptions = prescriptions.stream()
                .filter(Prescription::isFromPastYear)
                .collect(Collectors.toList());
        
        System.out.println("\n--- PAST YEAR PRESCRIPTIONS SUMMARY ---");
        System.out.println("Total prescriptions in past year: " + pastYearPrescriptions.size());
        
        // Group by medication name and count
        pastYearPrescriptions.stream()
                .collect(Collectors.groupingBy(
                    p -> p.getMedication().getName(),
                    Collectors.counting()
                ))
                .forEach((medName, count) -> 
                    System.out.println("  - " + medName + ": " + count + " prescription(s)"));
    }

    // ===== STOCK MANAGEMENT =====

    /**
     * Restocks all medications in the pharmacy by a random amount (1-50 units).
     */
    public void restockAllMedications() {
        System.out.println("\n--- RESTOCKING ALL MEDICATIONS ---");
        int totalAdded = 0;
        
        for (Medication medication : medications) {
            int randomAmount = (int) (Math.random() * 50) + 1; // 1-50 units
            medication.increaseStock(randomAmount);
            totalAdded += randomAmount;
            System.out.println("  - " + medication.getName() + ": +" + randomAmount + " units");
        }
        
        System.out.println("Total units added: " + totalAdded);
    }

    /**
     * Restocks a specific medication by a given amount.
     * 
     * @param medicationId the ID of the medication to restock
     * @param amount the amount to add
     * @return true if successful, false if medication not found
     */
    public boolean restockMedication(int medicationId, int amount) {
        Medication medication = findMedicationById(medicationId);
        if (medication != null && amount > 0) {
            medication.increaseStock(amount);
            System.out.println("Restocked " + medication.getName() + " by " + amount + " units.");
            return true;
        }
        return false;
    }

    // ===== FINDER METHODS =====

    private Patient findPatientById(int id) {
        return patients.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private Doctor findDoctorById(int id) {
        return doctors.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private Medication findMedicationById(int id) {
        return medications.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // ===== GETTERS =====

    public List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }

    public List<Medication> getMedications() {
        return new ArrayList<>(medications);
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctors);
    }

    public List<Prescription> getPrescriptions() {
        return new ArrayList<>(prescriptions);
    }
}
