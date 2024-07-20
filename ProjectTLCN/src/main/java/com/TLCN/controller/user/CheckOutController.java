package com.TLCN.controller.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;

import com.TLCN.common.Constants;
import com.TLCN.config.PaypalPaymentIntent;
import com.TLCN.config.PaypalPaymentMethod;
import com.TLCN.entity.Address;
import com.TLCN.entity.Discount;
import com.TLCN.entity.Order;
import com.TLCN.entity.Product;
import com.TLCN.model.CartModel;
import com.TLCN.service.AddressService;
import com.TLCN.service.DiscountService;
import com.TLCN.service.OrderService;
import com.TLCN.service.ParamService;
import com.TLCN.service.PaypalService;
import com.TLCN.service.ProductService;
import com.TLCN.service.SessionService;
import com.TLCN.service.impl.MailerServiceImpl;
import com.TLCN.service.impl.ShoppingCartServiceImpl;
import com.TLCN.utils.Utils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;


@Controller
public class CheckOutController {
	public Model model1;
	public String addressId1;
	public String method1;
	public String comment1;
	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private PaypalService paypalService;
	@Autowired
	AddressService addressService;

	@Autowired
	AddressService provinceService;
	
	@Autowired
	ShoppingCartServiceImpl cartService;
	
	@Autowired
	DiscountService discountService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ParamService paramService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	MailerServiceImpl mailerService;

	@GetMapping("/shop/cart/checkout")
	public String index(Model model) {
		List<CartModel> listCartModel = new ArrayList<>(cartService.getItems());
		if(listCartModel.isEmpty()) {
			return "redirect:/shop/cart";
		}
		model.addAttribute("cart", cartService);
		return Constants.USER_DISPLAY_CHECKOUT;
	}
	
	@PostMapping("/shop/cart/checkout")
	public String order(HttpServletRequest request, Model model) {
		model1 = model;
		String addressId = paramService.getString("address_id", "");
		String method = paramService.getString("shipping_method", "");
		String comment = paramService.getString("comment", null);
		double totalPrice = paramService.getDouble("total_price", 0);
		addressId1= addressId;
		method1 = method;
		comment1 = comment;
		if ("1".equals(method)) {
			String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
			String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
			try {
				// Convert price from VND to USD
		        RestTemplate restTemplate = new RestTemplate();
		        String apiUrl = "https://openexchangerates.org/api/latest.json?app_id=878405e77cff46de937a43f166b06be8&symbols=USD,VND";
		        String response = restTemplate.getForObject(apiUrl, String.class);
		        JSONObject json = new JSONObject(response);
		        double usdRate = json.getJSONObject("rates").getDouble("USD");
		        double vndRate = json.getJSONObject("rates").getDouble("VND");
		        double usdPrice = totalPrice / vndRate * usdRate;
		        
				Payment payment = paypalService.createPayment(
						usdPrice,
						"USD",
						PaypalPaymentMethod.paypal,
						PaypalPaymentIntent.sale,
						"payment description",
						cancelUrl,
						successUrl);
				for(Links links : payment.getLinks()){
					if(links.getRel().equals("approval_url")){
						return "redirect:" + links.getHref();
					}
				}
			} catch (PayPalRESTException e) {
				log.error(e.getMessage());
			}
			return Constants.USER_DISPLAY_INDEX;
	    }
		
		Address address = addressService.getAddressById(Integer.parseInt(addressId));
		
		Discount discount = cartService.getDiscount();
		
		if(discount.getId() == 0) {
			discount = null;
		}	
		
		int code;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);
		
		while (true) {
			code = (int) Math.floor(((Math.random() * 899999) + 100000));
			List<Order> list = orderService.getOrderByName(String.valueOf(code));
			if (list.isEmpty()) {
				break;
			}
		}
		
		List<CartModel> listCartModel = new ArrayList<>(cartService.getItems());
		for(CartModel cart: listCartModel) {
			Order order = new Order();
			Product product = cart.getProduct();
			order.setCode(String.valueOf(code));			
			order.setAddress(address);
			order.setDiscount(discount);
			order.setProduct(product);
			order.setQuality(cart.getQuality());
			order.setDate(strDate);
			order.setMethod(method);
			order.setStatus("0");
			order.setComment(comment);
			orderService.save(order);
			
			product.setQuality(product.getQuality() - cart.getQuality());
			productService.updateQuality(product);
		}
		
		discountService.updateQuality(discount);
		
		cartService.clear();
		cartService.clearDiscount();
		model.addAttribute("cart", cartService);
		
		mailerService.queue(address.getUser().getEmail(), "Đặt Hàng Thành Công Tại Web DatHau Store", 
				"Kính chào " + address.getUser().getFullname() +",<br>"
				+ "Cảm ơn bạn đã mua hàng tại DatHau Store. Mã đơn hàng của bạn là " + code + "<br>"
				+ "Xin vui lòng click vào đường link http://localhost:8086/account/order/invoice/" + code + " để xem chi tiết hóa đơn.<br>"
				+ "<br><br>"
				+ "Xin chân thành cảm ơn đã sử dụng dịch vụ,<br>"
				+ "DatHau Store");
		
		
		return "redirect:/shop/cart/checkout/success";
	}
	
	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay(){
		return "user/checkout/cancel";
	}
	
	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				Address address = addressService.getAddressById(Integer.parseInt(addressId1));
				
				Discount discount = cartService.getDiscount();
				
				if(discount.getId() == 0) {
					discount = null;
				}	
				
				int code;
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String strDate = formatter.format(date);
				
				while (true) {
					code = (int) Math.floor(((Math.random() * 899999) + 100000));
					List<Order> list = orderService.getOrderByName(String.valueOf(code));
					if (list.isEmpty()) {
						break;
					}
				}
				
				List<CartModel> listCartModel = new ArrayList<>(cartService.getItems());
				for(CartModel cart: listCartModel) {
					Order order = new Order();
					Product product = cart.getProduct();
					order.setCode(String.valueOf(code));			
					order.setAddress(address);
					order.setDiscount(discount);
					order.setProduct(product);
					order.setQuality(cart.getQuality());
					order.setDate(strDate);
					order.setMethod(method1);
					order.setStatus("1");
					order.setComment(comment1);
					orderService.save(order);
					
					product.setQuality(product.getQuality() - cart.getQuality());
					productService.updateQuality(product);
				}
				
				discountService.updateQuality(discount);
				
				cartService.clear();
				cartService.clearDiscount();
				model1.addAttribute("cart", cartService);
				
				mailerService.queue(address.getUser().getEmail(), "Đặt Hàng Thành Công Tại Web DatHau Store", 
						"Kính chào " + address.getUser().getFullname() +",<br>"
						+ "Cảm ơn bạn đã mua hàng tại DatHau Store. Mã đơn hàng của bạn là " + code + "<br>"
						+ "Xin vui lòng click vào đường link http://localhost:8086/account/order/invoice/" + code + " để xem chi tiết hóa đơn.<br>"
						+ "<br><br>"
						+ "Xin chân thành cảm ơn đã sử dụng dịch vụ,<br>"
						+ "DatHau Store SHOP");
				
				
				return "redirect:/shop/cart/checkout/success";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return Constants.USER_DISPLAY_INDEX;
	}
	
	@GetMapping("/shop/cart/checkout/success")
	public String success(Model model) {
		return Constants.USER_DISPLAY_CHECKOUT_SUCCESS;
	}
	
	@ModelAttribute("total")
	public int total() {
		List<CartModel> list = new ArrayList<>(cartService.getItems());
		int total = 0;
		for(CartModel i: list) {
			total = total + i.getProduct().getPrice() * i.getQuality();
		}
		return total;
	}

	@ModelAttribute("listAddress")
	public List<Address> getListAddress(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		return addressService.findListAddressByEmail(username);
	}
	
}
