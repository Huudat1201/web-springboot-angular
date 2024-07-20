package com.TLCN.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TLCN.common.Constants;
import com.TLCN.entity.Product;
import com.TLCN.model.BestSellerModel;
import com.TLCN.model.CartModel;
import com.TLCN.model.ShowProduct;
import com.TLCN.service.CategoryService;
import com.TLCN.service.CommentService;
import com.TLCN.service.OrderService;
import com.TLCN.service.ProductService;
import com.TLCN.service.SessionService;
import com.TLCN.service.impl.ShoppingCartServiceImpl;

@Controller
public class DetailProductController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	CommentService commentService;

	@Autowired
	ShoppingCartServiceImpl cartService;
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/san-pham/{nameSearch}")
	public String index(@PathVariable("nameSearch") String nameSearch, Model model) {
		productService.updateView(nameSearch);
		model.addAttribute("infor", false);
		sessionService.set("sessionProduct", cartService);
		return Constants.USER_DISPLAY_DETAIL_PRODUCT;
	}
	
	@SuppressWarnings("static-access")
	@PostMapping("/san-pham/{nameSearch}")
	public String orderProduct(@PathVariable("nameSearch") String nameSearch, @RequestParam("quantity") int quantity, @RequestParam("action") String action, Model model, RedirectAttributes redirectAttributes) {
	    Product product = productService.getProductByNameSearch(nameSearch);
	    
	    // Kiểm tra số lượng sản phẩm có đủ để thêm vào giỏ hàng không
	    if (quantity <= product.getQuality()) {
	        Map<Integer, CartModel> map = cartService.map;
	        CartModel cartModel = map.get(product.getId());
	        
	        if (cartModel == null) {
	            cartModel = new CartModel();
	            cartModel.setId(product.getId());
	            cartModel.setProduct(product);
	            cartModel.setQuality(quantity);
	            cartService.add(product.getId(), cartModel);
	        } else {
	            cartService.update(cartModel.getId(), cartModel.getQuality() + quantity);
	        }
	        
	        model.addAttribute("infor", true);
	        model.addAttribute("quantity", quantity);
	        sessionService.set("sessionProduct", cartService);
	    } else {
	        redirectAttributes.addFlashAttribute("error", "Số lượng yêu cầu cho " + quantity + " vượt quá số lượng hiện có.");
	        return "redirect:/san-pham/" + nameSearch;
	    }
	    
	    if (action.equals("buy-now")) {
	    	List<CartModel> list = new ArrayList<>(cartService.getItems());
			int total = 0;
			for(CartModel i: list) {
				total = total + i.getProduct().getPrice() * i.getQuality();
			}
			model.addAttribute("total", total);
			return "redirect:/shop/cart";
	    }
	    return Constants.USER_DISPLAY_DETAIL_PRODUCT;
	}
	
	@ModelAttribute("inforProduct")
	public Product inforCategory(@PathVariable("nameSearch") String nameSearch) {
		Product product = productService.getProductByNameSearch(nameSearch);
		return product;
	}

	@ModelAttribute("listProductRelated")
	public List<ShowProduct> listProductRelated(@PathVariable("nameSearch") String nameSearch) {
		Product product = productService.getProductByNameSearch(nameSearch);
		List<Product> list = productService.getListProductRelated(product.getCategory().getId());

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product item : list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(item.getNamesearch());
			showProduct.setProduct(item);
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}

		return listProduct;
	}

	@ModelAttribute("countComment")
	public int countComment(@PathVariable("nameSearch") String nameSearch) {
		int result = commentService.getCountCommentByProductNameSearch(nameSearch);
		return result;
	}

	@ModelAttribute("totalStar")
	public int totalStar(@PathVariable("nameSearch") String nameSearch) {
		int result = commentService.getAllStarCommentByProductNameSearch(nameSearch);
		return result;
	}
	
	@ModelAttribute("listBestSeller")
	public List<ShowProduct> getListBestSeller(Model model){
		Pageable topFour = PageRequest.of(0, 4);
		
		List<BestSellerModel> list = orderService.getListBestSellerProduct(topFour);
		
		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();
		
		for(BestSellerModel bestSeller: list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(bestSeller.getProduct().getNamesearch());
			showProduct.setProduct(bestSeller.getProduct());
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}		
		return listProduct;
	}
}