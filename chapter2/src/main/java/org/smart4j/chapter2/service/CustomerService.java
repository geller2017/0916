package org.smart4j.chapter2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter2.entity.Customer;
import org.smart4j.chapter2.helper.DatabaseHelper;


/***
 * @author Administrator
 *	提供客户服务类
 */
public class CustomerService {
	//获取客户列表
	public List<Customer> getCustomerList(){
		String sql = "select * from customer";
		List<Customer> customerList = DatabaseHelper.queryEntityList(Customer.class, sql, null);
		return customerList;
	}
	
	//根据id获取客户信息
	public Customer getCustomerById(Integer id){
		String sql = "select * from customer where id=?";
		List<Customer> customerList = DatabaseHelper.queryEntityList(Customer.class,sql,id);
		if(customerList!=null&&customerList.size()>0){
			return customerList.get(0);
		}
		return null;
	}
	
	//创建客户
	public void createCustomer(Customer customer){
		/*DatabaseHelper.insertIntity(customer.getClass(), MapUtils.);*/
	}
	
	//更新客户信息
	public void updateCustomer(Customer customer){
		
	}
	
	//根据用户id删除用户
	public void deleteCustomerById(Integer id){
		
	}
	
}
