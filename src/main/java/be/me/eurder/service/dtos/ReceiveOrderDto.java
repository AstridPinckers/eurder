package be.me.eurder.service.dtos;

import be.me.eurder.domain.pojos.user.Customer;

import java.util.HashSet;
import java.util.Set;

public class ReceiveOrderDto {

    private Set<ReceiveItemGroupDto> itemGroupSet;
    private String customerEmail;
    private String customerAddressLine;
    private String customerPostalCode;
    private String customerCity;
    private double totalPrice;


    public ReceiveOrderDto() {
        this.itemGroupSet = new HashSet<>();
    }

    public ReceiveOrderDto setCustomer(Customer customer) {
        customerEmail = customer.getEmail();
        customerAddressLine = customer.getAddress().getAddressLine();
        customerPostalCode = customer.getAddress().getPostalCode();
        customerCity = customer.getAddress().getCity();
        return this;
    }

    public ReceiveOrderDto setItemGroupSet(Set<ReceiveItemGroupDto> itemGroupSet) {
        this.itemGroupSet = itemGroupSet;
        return this;
    }


    public Set<ReceiveItemGroupDto> getItemGroupSet() {
        return itemGroupSet;
    }

    public ReceiveOrderDto setTotalPrice() {
        this.totalPrice = calculateOrderPrice();
        return this;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerAddressLine() {
        return customerAddressLine;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private double calculateOrderPrice(){
        double price = 0;
        for(ReceiveItemGroupDto receiveItemGroupDto: getItemGroupSet()){
            double itemPrice = receiveItemGroupDto.getPrice().getValue()*receiveItemGroupDto.getAmount();
            price+=itemPrice;
        }
        return price;
    }
}
