package com.rollingstone.dto;

import com.rollingstone.model.Address;
import com.rollingstone.model.Customer;
import com.rollingstone.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CustomerDTO {
    private long customerId;

    @NotBlank(message = "Customer Number cannot be blank")
    @Size(min=4, max=20)
    private String customerNumber;

    @NotBlank(message = "First Name cannot be blank")
    @Size(min=4, max=100)
    private String firstName;

    @NotBlank(message = "Middle Name cannot be blank")
    @Size(min=4, max=50)
    private String middleName;

    @NotBlank(message = "Last Name cannot be blank")
    @Size(min=4, max=100)
    private String lastName;


    @NotBlank(message = "Date Of Birth cannot be blank")
    private String dateOfBirth;

    @NotBlank(message = "Date Of Joining cannot be blank")
    private String dateOfJoining;

    @NotBlank(message = "Loyalty Status cannot be blank")
    private String loyaltyStatus;

    @NotBlank(message = "Loyalty Points cannot be blank")
    @Size(min=4, max=12)
    private String loyaltyPoints;

    private long addressId;
    @NotBlank(message = "House Number cannot be blank")
    @Size(min=4, max=100)
    private String houseNumber;

    @NotBlank(message = "Street cannot be blank")
    @Size(min=4, max=100)
    private String street;

    @NotBlank(message = "City cannot be blank")
    @Size(min=4, max=100)
    private String city;

    @NotBlank(message = "State cannot be blank")
    private String state;
    @NotBlank(message = "Zip cannot be blank")
    private String zip;


    @NotBlank
    @Size(min=4, max=100)
    private String homePhoneNumber;

    @NotBlank
    @Size(min=4, max=100)
    private String mobilePhoneNumber;

    @NotBlank
    @Size(min=4, max=100)
    @Email(message = "Email should be valid")
    private String emailAddress;


    private String createdBy;


    private String createdTime;


    private String updatedBy;


    private String updatedTime;

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
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


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public CustomerDTO() {
    }

    public CustomerDTO(long customerId, String customerNumber, String firstName, String middleName, String lastName, String dateOfBirth, String dateOfJoining, String loyaltyStatus, String loyaltyPoints, Set<Address> addresses, String createdBy, String createdTime, String updatedBy, String updatedTime) {
        this.customerId = customerId;
        this.customerNumber = customerNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.loyaltyStatus = loyaltyStatus;
        this.loyaltyPoints = loyaltyPoints;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO customer = (CustomerDTO) o;
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

