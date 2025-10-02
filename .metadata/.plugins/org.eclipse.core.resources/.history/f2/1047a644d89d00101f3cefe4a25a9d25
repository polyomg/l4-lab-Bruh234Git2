package poly.edu.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import poly.edu.Entity.Product;

@Controller
public class ProductController2 {
	@GetMapping("poly/product2/form3")
	public String form2(Model model) {
		Product p1 = new Product();
		p1.setName("iPhone 30");
		p1.setPrice(5000.0);
		/*?1*/ model.addAttribute("p1", p1);
		return "poly/product/form3";
	}
	
	@PostMapping("poly/product2/save")
	public String save2(@ModelAttribute("product") Product p2, Model model) {
	    if (p2 == null) {
	        p2 = new Product();
	        p2.setName("No name");
	        p2.setPrice(0.0);
	    }
	    model.addAttribute("p2", p2);
		return "poly/product/form3";
	}
	
	/*?3*/@ModelAttribute("items")
	public List<Product> getItems() {
	return Arrays.asList(
			new Product("A", 1.0), 
			new Product("B", 12.0)
			);
	}
}
