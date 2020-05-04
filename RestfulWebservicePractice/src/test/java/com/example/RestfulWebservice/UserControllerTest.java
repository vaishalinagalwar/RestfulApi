package com.example.RestfulWebservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.mockito.*;

import com.example.controllers.UserController;
import com.example.models.User;
import com.example.repository.UserRepositoryImpl;

import javafx.beans.binding.When;

class UserControllerTest {

	@InjectMocks
	
	UserController controller;
	@Mock
	UserRepositoryImpl service;
	
	private MockMvc mockmvc;
	User user;
	@BeforeEach
	void setUp() throws Exception {
		
		mockmvc=MockMvcBuilders.standaloneSetup(UserController.class).build();
		MockitoAnnotations.initMocks(this);
		
		//user=new User();
	}
	
	@Test
    public void testSaveUser() 
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        User user = new User(1, "Lokesh",new Date());
        Mockito.when(service.save(user)).thenReturn(user);
        	
        ResponseEntity<Object> responseEntity = controller.savedUser(user);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }

   @Test
   void testRetrieveAllUser() throws Exception {
	  
	   User user=new User();
	   user.setId(2);
	   user.setName("Vaishali");
	   user.setBirthDate(new Date());
	  
	   List<User> users=new ArrayList<User>();
	   users.add(user);
	   Mockito.when(service.findAll()).thenReturn(users);
	   
	   //when
	   List<User> result1=controller.retrieveAllUsers();
	   System.out.println(result1.get(0).getName());
	   //Then
	   assertThat(result1.get(0).getName()).isEqualTo(user.getName());
		
}
   @Test
   public void testFindOneUser()
   {
	   
   }
   
	/*
	 * @Test public void deleteProduct() throws Exception { String uri = "/users/2";
	 * Mockito.when(service.delete(1)).thenReturn(user); MvcResult mvcResult =
	 * mockmvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn(); //when
	 * controller.deleteById(1); assertThat(user.getId()). int status =
	 * mvcResult.getResponse().getStatus(); assertEquals(200, status); // String
	 * content = mvcResult.getResponse().getContentAsString(); //
	 * assertEquals(content, "Product is deleted successsfully"); }
	 */

/*
 * private List<User> getEntityUserList() { // TODO Auto-generated method stub
 * List<User> list=new ArrayList<User>(); list.add(new User(1,"Ganavee",new
 * Date())); return list;
 */


	

/*
 * @Test void testRetrieveOneUser() {
 * Mockito.when(service.findOne(1)).thenReturn(user);
 * 
 * 
 * 
 * //fail("Not yet implemented"); }
 */

	

}
