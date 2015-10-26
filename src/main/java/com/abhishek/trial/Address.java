package com.abhishek.trial;

public class Address implements IHideable {
	public final String city;
	public final String state;
	public final boolean hidden;
	
	public Address(String city, String state, boolean hidden) {
		this.city = city;
		this.state = state;
		this.hidden = hidden;
	}
	
	@Override
	public boolean isHidden() {
		return hidden;
	}

}
