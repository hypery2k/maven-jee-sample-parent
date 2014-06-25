/**
 * Disclaimer: this code is only for demo no production use
 */
package com.vilivesoft;

import javax.ejb.Stateless;

/**
 * @author papo
 *
 */
@Stateless
public class UserServices {

	public String sayHello(){
		return "hello";
	}

}
