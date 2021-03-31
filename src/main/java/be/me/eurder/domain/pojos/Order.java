package be.me.eurder.domain.pojos;

import be.me.eurder.domain.pojos.user.Customer;

import java.util.HashSet;
import java.util.Set;

public class Order {
    private Customer customer;
    private Set<ItemGroup> itemGroupSet;

    public Order(Customer customer, Set<ItemGroup> itemGroupSet) {
        this.customer = customer;
        this.itemGroupSet = itemGroupSet;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<ItemGroup> getItemGroupSet() {
        return itemGroupSet;
    }
}
