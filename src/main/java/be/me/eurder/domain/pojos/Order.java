package be.me.eurder.domain.pojos;

import be.me.eurder.domain.pojos.user.Customer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Order {

    private UUID uuid;
    private Customer customer;
    private Set<ItemGroup> itemGroupSet;

    public Order(Customer customer, Set<ItemGroup> itemGroupSet) {
        this.uuid = UUID.randomUUID();
        this.customer = customer;
        this.itemGroupSet = itemGroupSet;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<ItemGroup> getItemGroupSet() {
        return itemGroupSet;
    }

    public UUID getUuid() {
        return uuid;
    }
}
