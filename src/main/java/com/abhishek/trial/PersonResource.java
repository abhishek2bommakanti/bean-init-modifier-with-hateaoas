/**
 * 
 */
package com.abhishek.trial;

import org.springframework.hateoas.ResourceSupport;

/**
 * @author abhishek
 *
 */
public class PersonResource extends ResourceSupport implements IHideable {
	public final String name;
	public final int age;
	public final Address address;
	public final boolean hidden;
	
	public PersonResource(String name, int age, Address address, boolean hidden) {
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
