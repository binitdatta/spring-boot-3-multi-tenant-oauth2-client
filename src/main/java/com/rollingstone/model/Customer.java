package com.rollingstone.model;



import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class Customer {

    private long customerId;

    private String customerNumber;

    private String firstName;

    private String middleName;

    private String lastName;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateOfBirth;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dateOfJoining;

    private String loyaltyStatus;

    private String loyaltyPoints;

    private Set<Address> addresses  = new HashSet<Address>();

    private User user;

    private String createdBy;


    private LocalDateTime createdTime;


    private String updatedBy;

    
    private LocalDateTime updatedTime;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDateTime dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getLoyaltyStatus() {
        return loyaltyStatus;
    }

    public void setLoyaltyStatus(String loyaltyStatus) {
        this.loyaltyStatus = loyaltyStatus;
    }

    public String getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(String loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer() {
    }

    public Customer(long customerId, String customerNumber, String firstName, String middleName, String lastName, LocalDateTime dateOfBirth, LocalDateTime dateOfJoining, String loyaltyStatus, String loyaltyPoints, Set<Address> addresses, String createdBy, LocalDateTime createdTime, String updatedBy, LocalDateTime updatedTime) {
        this.customerId = customerId;
        this.customerNumber = customerNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.loyaltyStatus = loyaltyStatus;
        this.loyaltyPoints = loyaltyPoints;
        this.addresses = addresses;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && Objects.equals(customerNumber, customer.customerNumber) && Objects.equals(firstName, customer.firstName) && Objects.equals(middleName, customer.middleName) && Objects.equals(lastName, customer.lastName) && Objects.equals(dateOfBirth, customer.dateOfBirth) && Objects.equals(dateOfJoining, customer.dateOfJoining) && Objects.equals(loyaltyStatus, customer.loyaltyStatus) && Objects.equals(loyaltyPoints, customer.loyaltyPoints)  && Objects.equals(createdBy, customer.createdBy) && Objects.equals(createdTime, customer.createdTime) && Objects.equals(updatedBy, customer.updatedBy) && Objects.equals(updatedTime, customer.updatedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerNumber, firstName, middleName, lastName, dateOfBirth, dateOfJoining, loyaltyStatus, loyaltyPoints, createdBy, createdTime, updatedBy, updatedTime);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerNumber='" + customerNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfJoining=" + dateOfJoining +
                ", loyaltyStatus='" + loyaltyStatus + '\'' +
                ", loyaltyPoints='" + loyaltyPoints + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
