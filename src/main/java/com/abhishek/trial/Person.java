/**
 * 
 */
package com.abhishek.trial;

/**
 * @author abhishek
 *
 */
public class Person implements IHideable {
	public final String name;
	public final int age;
	public final Address address;
	public final boolean hidden;
	
	public Person(String name, int age, Address address, boolean hidden) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.hidden = hidden;
	}

	@Override
	public boolean isHidden() {
		return hidden;
	}

}
