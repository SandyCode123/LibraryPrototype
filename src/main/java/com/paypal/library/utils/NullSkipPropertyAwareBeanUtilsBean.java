package com.paypal.library.utils;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Service;
/**
 * @author Sandip
 * @apiNote This is Common utility class to perform shallow copy and below functionalities appended in addition.
 * -> merge by skipping null properties
 * -> ability to skip copying particular properties
 */

@Service
public class NullSkipPropertyAwareBeanUtilsBean extends BeanUtilsBean{
	
	List<String> skipProperties=null;
	NullSkipPropertyAwareBeanUtilsBean(String... skipProperties){
		this.skipProperties=Arrays.asList(skipProperties);
	}
	
	public void setSkipProperties(String... skipProperties){
		this.skipProperties=Arrays.asList(skipProperties);
	}
	
    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
          if(value==null) // To Skip copying null values.
        	  return;
          if(skipProperties.contains(name)) // To skip copying mentioned skip list fields
        	  return; 
          super.copyProperty(dest, name, value);
    }

}