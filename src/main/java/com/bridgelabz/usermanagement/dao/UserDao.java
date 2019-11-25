package com.bridgelabz.usermanagement.dao;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.bridgelabz.usermanagement.dto.UserDto;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.utility.EmailUtil;
import com.bridgelabz.usermanagement.utility.SessionUtil;
import com.bridgelabz.usermanagement.utility.UserToken;


public class UserDao {
	
	 private HibernateTemplate hibernateTemplate;
	 

	
	 public void addEmployee(UserDto userDto){
	        Session session = SessionUtil.getSession();        
	        Transaction tx = session.beginTransaction();
	        addEmployee(session,userDto);        
	        tx.commit();
	        session.close();
	        
	    }
	 
	    
	    @SuppressWarnings("unchecked")
		private void addEmployee(Session session, UserDto userDto){

	    	ModelMapper modelMapper = new ModelMapper();
	    	String selectQuery = "FROM User WHERE email = :email";
	    	Query query = session.createQuery(selectQuery);
	    	query.setParameter("email", userDto.getEmail());
	    	
	    	User e = (User) query.setMaxResults(1).uniqueResult();
	    	
	    	System.out.println("data0"+e);
	    	if(e!=null) {
	    		System.out.println("user already Present");
	    	}
	    	else
	    	{
	    		User user = modelMapper.map(userDto, User.class);
    		    session.save(user);
    		    String url=this.getUrl("loginVerify",user.getId());
    		    EmailUtil.sendEmail(userDto.getEmail()," login Verify","click on link "+ url);
	    	}

	        
	    }
	    
	    public List<User> getUsers(){
	        Session session = SessionUtil.getSession();    
	        Query query = session.createQuery("from User");
	        List<User> user =  query.list();
	        session.close();
	        return user;
	    }
	    
	    

	    public String getUrl(String service, int id) {
	    	
	    	return "http://localhost:8081/" + service 
	    			+ "/" + UserToken.createToken(id);

	    }
	    
	    
	    public String verify(String token) {
	    	Long userId=UserToken.tokenVerify(token);
	    	Session session = SessionUtil.getSession();  
	    	Transaction tx = session.beginTransaction();  
	    	User user=new User();
	    	user.setIsverify(true);
	    	System.out.println(user.getIsverify()+"value of token");
	    	session.save(user);
	    	tx.commit();
	    	session.close();
	    //	System.out.println("token verify successfully");
	    	return "token verify successfully";
	  
	   }

		

}
