package de.leonardo.guavaPlayground.registry.api;

import de.leonardo.guavaPlayground.registry.model.Cuisine;
import de.leonardo.guavaPlayground.registry.model.Customer;

import java.util.List;

public interface CuisinesRegistry {

    void register(Customer customer, Cuisine cuisine);

    List<Cuisine> customerCuisines(Customer customer);

    List<Cuisine> topCuisines(int n);

    List<Customer> cuisineCustomers(Cuisine cuisine);
}
