package de.leonardo.guavaPlayground.registry;
import com.google.common.collect.*;
import de.leonardo.guavaPlayground.registry.api.CuisinesRegistry;
import de.leonardo.guavaPlayground.registry.model.Cuisine;
import de.leonardo.guavaPlayground.registry.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

public class CuisiniesRegistry implements CuisinesRegistry {

    private static final Logger logger = LoggerFactory.getLogger(CuisiniesRegistry.class);
    private final Multimap<Cuisine, Customer> cuisineCustomerMultimap = Multimaps
            .synchronizedSetMultimap(HashMultimap.create());

    /**
     * This method register an Entry to the Multimap<Cuisine, Customer>:
     * it is not necessary to populate empty collection before the pull()
     * NULL values are accepted and handled in the get()
     * as it is a synchronizedSetMultimap is thread-safe and skips duplicated
     * @param customer Customer object to add
     * @param cuisine  Cuisine object to add
     */
    @Override
    public synchronized void register(final Customer customer, final Cuisine cuisine) {

        cuisineCustomerMultimap.put(cuisine, customer);
        }

    /**
     * This method shows a list containing the customers associated with the cuisine passed:
     * @param cuisine  Cuisine object to show associated customers
     * @return the list of customers associated with a cuisine, empty [] if cuisine is null
     */
    @Override
    public List<Customer> cuisineCustomers( final Cuisine cuisine) {

        return ImmutableMultimap.copyOf(cuisineCustomerMultimap)
                .get(cuisine)
                .asList();

    }

    /**
     * This method shows a list containing the customers associated with the cuisine passed:
     * @param customer  Customer object to show associated cuisines
     * @return the list of cuisines associated with a customer, empty [] if customer is null
     */
    @Override
    public List<Cuisine> customerCuisines(final Customer customer) {

        return ImmutableMultimap.copyOf(cuisineCustomerMultimap)
                .inverse()
                .get(customer)
                .asList();
    }

    /**
     * This method shows a list of the most popular (n) Cuisines:
     * @param n  number of top cuisines to show
     * @return the list of the mos popular cuisines
     */
    @Override
    public List<Cuisine> topCuisines(final int n) {
        int inMemoryCuisineKeysListSize = cuisineCustomerMultimap.keySet().size();

        System.out.println(cuisineCustomerMultimap.keySet().size());
        if (n>0 && n<= inMemoryCuisineKeysListSize) {
            ImmutableMultiset<Cuisine> cuisineList = Multisets.copyHighestCountFirst(cuisineCustomerMultimap.keys());

            return cuisineList.elementSet().asList().subList(0,n);
        }
        logger.warn("invalid request trying to access topCuisines with n: {} for cuisines list size: {}",
                n, inMemoryCuisineKeysListSize);
        return Collections.emptyList();
    }

}
