package medicationtracking;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

/**
 * The Medication class represents a medication in the pharmacy system.
 * It contains information about the medication including its dosage,
 * quantity in stock, and expiry date with random date generation.
 * 
 * @author Your Name
 * @version 1.0
 */
public class Medication {
    private int id;
    private String name;
    private String dose;
    private int quantityInStock;
    private LocalDate expiryDate;

    private static final Random random = new Random();

    /**
     * Default constructor for Medication class.
     * Generates a random expiry date between past and future dates.
     */
    public Medication() {
        this.expiryDate = generateRandomExpiryDate();
    }

    /**
     * Parameterized constructor for Medication class.
     * 
     * @param id the unique identifier for the medication
     * @param name the name of the medication
     * @param dose the dosage of the medication
     * @param quantityInStock the amount of medication in stock
     */
    public Medication(int id, String name, String dose, int quantityInStock) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.quantityInStock = quantityInStock;
        this.expiryDate = generateRandomExpiryDate();
    }

    /**
     * Full parameterized constructor for Medication class.
     * 
     * @param id the unique identifier for the medication
     * @param name the name of the medication
     * @param dose the dosage of the medication
     * @param quantityInStock the amount of medication in stock
     * @param expiryDate the expiry date of the medication
     */
    public Medication(int id, String name, String dose, int quantityInStock, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.dose = dose;
        this.quantityInStock = quantityInStock;
        this.expiryDate = expiryDate;
    }

    /**
     * Generates a random expiry date that can be in the past or future.
     * Dates range from 2 years in the past to 2 years in the future.
     * 
     * @return a random LocalDate for expiry
     */
    private LocalDate generateRandomExpiryDate() {
        LocalDate today = LocalDate.now();
        // Generate random days between -730 (2 years past) and +730 (2 years future)
        int randomDays = random.nextInt(1461) - 730; // 1461 days = 4 years range
        return today.plusDays(randomDays);
    }

    // Business Methods

    /**
     * Checks if the medication is expired.
     * 
     * @return true if expired, false otherwise
     */
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    /**
     * Checks if the medication will expire within the specified number of days.
     * 
     * @param days the number of days to check
     * @return true if expiring within specified days, false otherwise
     */
    public boolean isExpiringWithinDays(int days) {
        LocalDate threshold = LocalDate.now().plusDays(days);
        return !isExpired() && (expiryDate.isBefore(threshold) || expiryDate.isEqual(threshold));
    }

    /**
     * Gets the number of days until expiry.
     * Negative values indicate days since expiry.
     * 
     * @return number of days until/since expiry
     */
    public long getDaysUntilExpiry() {
        return ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
    }

    /**
     * Reduces the quantity in stock by the specified amount.
     * 
     * @param amount the amount to reduce
     * @return true if successful, false if insufficient stock
     */
    public boolean reduceStock(int amount) {
        if (amount <= 0 || amount > quantityInStock) {
            return false;
        }
        quantityInStock -= amount;
        return true;
    }

    /**
     * Increases the quantity in stock by the specified amount.
     * 
     * @param amount the amount to add
     */
    public void increaseStock(int amount) {
        if (amount > 0) {
            quantityInStock += amount;
        }
    }

    /**
     * Checks if the medication is in stock (quantity > 0).
     * 
     * @return true if in stock, false otherwise
     */
    public boolean isInStock() {
        return quantityInStock > 0;
    }

    /**
     * Checks if the medication is low in stock (less than or equal to threshold).
     * 
     * @param threshold the low stock threshold
     * @return true if low in stock, false otherwise
     */
    public boolean isLowStock(int threshold) {
        return quantityInStock <= threshold;
    }

    // Getters and Setters

    /**
     * Gets the unique identifier of the medication.
     * 
     * @return the medication's ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the medication.
     * 
     * @param id the medication's ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the medication.
     * 
     * @return the medication's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the medication.
     * 
     * @param name the medication's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the dosage of the medication.
     * 
     * @return the medication's dosage
     */
    public String getDose() {
        return dose;
    }

    /**
     * Sets the dosage of the medication.
     * 
     * @param dose the medication's dosage to set
     */
    public void setDose(String dose) {
        this.dose = dose;
    }

    /**
     * Gets the quantity of medication in stock.
     * 
     * @return the quantity in stock
     */
    public int getQuantityInStock() {
        return quantityInStock;
    }

    /**
     * Sets the quantity of medication in stock.
     * 
     * @param quantityInStock the quantity to set
     */
    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    /**
     * Gets the expiry date of the medication.
     * 
     * @return the expiry date
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date of the medication.
     * 
     * @param expiryDate the expiry date to set
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Returns a string representation of the Medication object.
     * 
     * @return a string containing the medication's details
     */
    @Override
    public String toString() {
        String expiryStatus = isExpired() ? "EXPIRED" : "Valid";
        return "ID: " + id + 
               ", Name: " + name + 
               ", Dose: " + dose + 
               ", Stock: " + quantityInStock + 
               ", Expiry: " + expiryDate + 
               " (" + expiryStatus + ")";
    }

    /**
     * Displays the medication's information to the console.
     */
    public void displayInfo() {
        System.out.println(this.toString());
    }

    /**
     * Displays detailed medication information.
     */
    public void displayDetailedInfo() {
        System.out.println("=== Medication Details ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Dose: " + dose);
        System.out.println("Quantity in Stock: " + quantityInStock);
        System.out.println("Expiry Date: " + expiryDate);
        System.out.println("Status: " + (isExpired() ? "EXPIRED" : "Valid"));
        System.out.println("Days until expiry: " + getDaysUntilExpiry());
        System.out.println("In Stock: " + (isInStock() ? "Yes" : "No"));
        System.out.println("==========================");
    }

    /**
     * Checks equality based on medication ID.
     * 
     * @param obj the object to compare
     * @return true if medications have the same ID
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Medication that = (Medication) obj;
        return id == that.id;
    }

    /**
     * Generates hash code based on medication ID.
     * 
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}