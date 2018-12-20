package com.example.demo.model.filter;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFilter(value="UserBeanFilter")
public class SampleBean {
private String val1;
private String val2;
private String val3;
public String getVal1() {
	return val1;
}
public void setVal1(String val1) {
	this.val1 = val1;
}
public String getVal2() {
	return val2;
}
public void setVal2(String val2) {
	this.val2 = val2;
}
public String getVal3() {
	return val3;
}
public void setVal3(String val3) {
	this.val3 = val3;
}
public SampleBean(String val1, String val2, String val3) {
	super();
	this.val1 = val1;
	this.val2 = val2;
	this.val3 = val3;
}

}
