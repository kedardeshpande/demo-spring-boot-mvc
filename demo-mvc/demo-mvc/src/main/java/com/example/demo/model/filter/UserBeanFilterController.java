package com.example.demo.model.filter;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class UserBeanFilterController {
	
	@GetMapping("/filtering")
	public MappingJacksonValue retriveFilteredBeanFields()
	{
		SampleBean u = new SampleBean("val1","val2","val3");
		SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("val1","val3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(u);
		
		mapping.setFilters(filters);
		return mapping;
	}
}
