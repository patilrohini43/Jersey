package com.bridgelabz.usermanagement.dto;

public class UserDto {
	
	
	    private String name;
	  
	    private String email;
	    
	    private String password;
	    
	    private String mobno;
	  
	    private String address;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getMobno() {
			return mobno;
		}

		public void setMobno(String mobno) {
			this.mobno = mobno;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return "UserDto [name=" + name + ", email=" + email + ", password=" + password + ", mobno=" + mobno
					+ ", address=" + address + "]";
		}
	    
	    
	    
	   

}
