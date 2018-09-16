package org.smart4j.chapter2.test;

import java.util.List;

import org.smart4j.chapter2.entity.Customer;
import org.smart4j.chapter2.service.CustomerService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public static void main(String[] args) {
		CustomerService customerService = new CustomerService();
		List<Customer> customerList = customerService.getCustomerList();
		System.out.println(customerList.size());
	}
}
