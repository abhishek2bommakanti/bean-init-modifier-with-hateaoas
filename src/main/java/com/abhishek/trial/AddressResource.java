package com.abhishek.trial;

import org.springframework.hateoas.ResourceSupport;

public class AddressResource extends ResourceSupport implements IHideable {
	public final String city;
	public final String state;
	public final boolean hidden;
	
	public AddressResource(String city, String state, boolean hidden) {
		this.city = city;
		this.state = state;
		this.hidden = hidden;
	}
	
	@Override
	public boolean isHidden() {
		return hidden;
	}

}
