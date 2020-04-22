package helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



//Controller
@RestController
public class HelloWorldController {
	
	@GetMapping("/helloworld")
	
	public String helloWorld()
	{
		
		return "Hello World";
	}
	
	
	@GetMapping("/helloIndia-bean")
	
	public HelloWorldBean helloIndiaBean()
	{
		return new HelloWorldBean("Hello India");
	}
	
	@GetMapping("/helloIndia-bean/{name}")
	
	public HelloWorldBean helloIndiaBean(@PathVariable String name)
	{
		return new HelloWorldBean(String.format("Hello India %S", name));
	}
	
}
