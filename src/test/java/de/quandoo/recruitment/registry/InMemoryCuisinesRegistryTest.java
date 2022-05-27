package de.quandoo.recruitment.registry;

import de.quandoo.recruitment.registry.api.CuisinesRegistry;
import de.quandoo.recruitment.registry.model.Cuisine;
import de.quandoo.recruitment.registry.model.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryCuisinesRegistryTest {

    private static final Cuisine FRENCH = new Cuisine("french");
    private static final Cuisine GERMAN = new Cuisine("german");
    private static final Cuisine ITALIAN = new Cuisine("italian");

    private static final Customer CUSTOMER1 = new Customer("1");
    private static final Customer CUSTOMER2 = new Customer("2");
    private static final Customer CUSTOMER3 = new Customer("3");

    private CuisinesRegistry cuisinesRegistry;

    @Before
    public void setUp(){
        cuisinesRegistry = new InMemoryCuisinesRegistry();
    }

    @Test
    public void shouldShowOnlyTheRegisteredCustomerToCuisine() {

        cuisinesRegistry.register(CUSTOMER1, FRENCH);
        cuisinesRegistry.register(CUSTOMER2, GERMAN);
        cuisinesRegistry.register(CUSTOMER3, ITALIAN);

        List<Customer> customerList = cuisinesRegistry.cuisineCustomers(FRENCH);

        assertThat(customerList).containsOnly(CUSTOMER1);

    }

    @Test
    public void shouldReturnEmptyIfCuisineIsNull(){

        List<Customer> customerList = cuisinesRegistry.cuisineCustomers(null);

        assertThat(customerList).isEmpty();
    }

    @Test
    public void shouldReturnEmptyIfCustomerIsNull(){

        List<Cuisine> cuisineList = cuisinesRegistry.customerCuisines(null);

        assertThat(cuisineList).isEmpty();
    }

    @Test
    public void shouldReturnListOfTheTwoMostPopularCuisine() {
        cuisinesRegistry.register(CUSTOMER3, ITALIAN);
        cuisinesRegistry.register(CUSTOMER2, ITALIAN);
        cuisinesRegistry.register(CUSTOMER1, ITALIAN);
        cuisinesRegistry.register(CUSTOMER1, FRENCH);
        cuisinesRegistry.register(CUSTOMER2, GERMAN);
        cuisinesRegistry.register(CUSTOMER2, GERMAN);

        List<Cuisine> cuisineList = cuisinesRegistry.topCuisines(2);

        List<Cuisine> expectedCuisineList = new ArrayList<>();
        expectedCuisineList.add(ITALIAN);
        expectedCuisineList.add(GERMAN);
        assertThat(cuisineList).containsExactlyElementsOf(expectedCuisineList);
    }



    /*  Some tests created during development, as the task states not to increase test coverage
        will just leave then inside the comment block, but could be useful in the future
    * */

    /*
    @Test
    public void shouldShowAllCuisinesWhenCustomerFollowsMultipleCuisines() {

        cuisinesRegistry.register(CUSTOMER1, FRENCH);
        cuisinesRegistry.register(CUSTOMER1, GERMAN);
        cuisinesRegistry.register(CUSTOMER1, ITALIAN);
        List<Cuisine> customerList = cuisinesRegistry.customerCuisines(CUSTOMER1);

        List<Cuisine> expectedCustomerList = new ArrayList<>();
        expectedCustomerList.add(FRENCH);
        expectedCustomerList.add(GERMAN);
        expectedCustomerList.add(ITALIAN);

        assertThat(customerList).containsExactlyInAnyOrderElementsOf(expectedCustomerList);
    }


    @Test
    public void shouldNotAddEntryWhenCustomerFollowsTheSameCuisineMoreThanOneTime() {

        cuisinesRegistry.register(CUSTOMER1, FRENCH);
        cuisinesRegistry.register(CUSTOMER1, GERMAN);
        cuisinesRegistry.register(CUSTOMER1, ITALIAN);
        List<Cuisine> customerListBeforeDuplicateCuisine = cuisinesRegistry.customerCuisines(CUSTOMER1);

        cuisinesRegistry.register(CUSTOMER1, FRENCH);
        List<Cuisine> customerListAfterDuplicateCuisine = cuisinesRegistry.customerCuisines(CUSTOMER1);

        assertThat(customerListBeforeDuplicateCuisine).containsExactlyInAnyOrderElementsOf(customerListAfterDuplicateCuisine);
    }

    @Test
    public void shouldShowAllMultipleRegisteredCustomerToACuisine() {

        cuisinesRegistry.register(CUSTOMER1, FRENCH);
        cuisinesRegistry.register(CUSTOMER2, FRENCH);
        cuisinesRegistry.register(CUSTOMER3, FRENCH);
        cuisinesRegistry.register(CUSTOMER3, GERMAN);
        List<Customer> customerList = cuisinesRegistry.cuisineCustomers(FRENCH);

        List<Customer> expectedFrenchCustomerList = new ArrayList<>();
        expectedFrenchCustomerList.add(CUSTOMER1);
        expectedFrenchCustomerList.add(CUSTOMER2);
        expectedFrenchCustomerList.add(CUSTOMER3);


        assertThat(customerList).containsExactlyInAnyOrderElementsOf(expectedFrenchCustomerList);
    }
    */

}