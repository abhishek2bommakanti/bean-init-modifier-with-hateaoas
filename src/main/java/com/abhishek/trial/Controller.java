package com.abhishek.trial;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	private Address a1 = new Address("New York", "NY", true);
	private Address a2 = new Address("Los Angeles", "CA", false);
	
	private Person p1 = new Person("Abhishek Bommakanti", 36, a1, false);
	private Person p2 = new Person("Tom Smith", 30, a2, true);
	private Person p3 = new Person("Mike Jones", 25, a1, true);
	private Person p4 = new Person("Adam Lee", 21, a2, false);
	
	private AddressResource ar1 = new AddressResource("New York", "NY", true);
	private AddressResource ar2 = new AddressResource("Los Angeles", "CA", false);
	
	private PersonResource pr1 = new PersonResource("Abhishek Bommakanti", 36, a1, false);
	private PersonResource pr2 = new PersonResource("Tom Smith", 30, a2, true);
	private PersonResource pr3 = new PersonResource("Mike Jones", 25, a1, true);
	private PersonResource pr4 = new PersonResource("Adam Lee", 21, a2, false);
	
	@RequestMapping("/list")
	public List<IHideable> getList() {
		return Arrays.asList(p1, p2, p3, p4, a1, a2);
	}
	
	@RequestMapping("/list-resource")
	public List<IHideable> getListOfResources() {
		return Arrays.asList(pr1, pr2, pr3, pr4, ar1, ar2);
	}
	
	@RequestMapping("/person/{id}")
	public Person getPerson(@PathVariable("id")String id) {
		if ("1".equals(id)) {
			return p1;
		}
		if ("2".equals(id)) {
			return p2;
		}
		if ("3".equals(id)) {
			return p3;
		}
		if ("4".equals(id)) {
			return p4;
		}
		
		throw new RuntimeException("Person not found.");
	}
	
	@RequestMapping("/person-resource/{id}")
	public PersonResource getPersonResource(@PathVariable("id")String id) {
		if ("1".equals(id)) {
			return pr1;
		}
		if ("2".equals(id)) {
			return pr2;
		}
		if ("3".equals(id)) {
			return pr3;
		}
		if ("4".equals(id)) {
			return pr4;
		}
		
		throw new RuntimeException("Person Resource not found.");
	}
	
	@RequestMapping("/address-resource/{id}")
	public AddressResource getAddress(@PathVariable("id")String id) {
		if ("1".equals(id)) {
			return ar1;
		}
		if ("2".equals(id)) {
			return ar2;
		}
		
		throw new RuntimeException("Address Resource not found.");
	}
}
