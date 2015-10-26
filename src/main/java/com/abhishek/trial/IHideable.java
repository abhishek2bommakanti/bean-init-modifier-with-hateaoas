package com.abhishek.trial;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IHideable {
	@JsonIgnore
    boolean isHidden();
}
