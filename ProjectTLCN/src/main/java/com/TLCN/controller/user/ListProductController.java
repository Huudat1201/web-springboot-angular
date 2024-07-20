package com.TLCN.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.TLCN.common.Constants;
import com.TLCN.entity.Category;
import com.TLCN.entity.Manufacturer;
import com.TLCN.model.BestSellerModel;
import com.TLCN.model.ShowProduct;
import com.TLCN.service.CategoryService;
import com.TLCN.service.CommentService;
import com.TLCN.service.ManufacturerService;
import com.TLCN.service.OrderService;
import com.TLCN.service.ProductService;
import com.TLCN.service.SessionService;

/**
 * Class de danh sach san pham
 */
@Controller
public class ListProductController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	SessionService sessionService;

	@Autowired
	ManufacturerService manufacturerService;

	@Autowired
	OrderService orderService;

	@Autowired
	CommentService commentService;

	@GetMapping("/danh-sach/{nameSearch}")
	public String index(@PathVariable("nameSearch") String nameSearch, Model model,
			@RequestParam("p") Optional<Integer> p, @RequestParam(name = "gia", required = false) String price,
			@RequestParam(name = "hang", required = false) String manu,
			@RequestParam(name = "xep", required = false) String sort) {
		try {
			Pageable pageable = PageRequest.of(p.orElse(0), 15);

			Page<ShowProduct> listProduct = productService.getListProductByFilter(nameSearch, price, manu, sort,
					pageable, "danh-sach", "", "");

			model.addAttribute("listProduct", listProduct);
			model.addAttribute("price", price);
			model.addAttribute("manu", manu);
			model.addAttribute("sort", sort);
			model.addAttribute("nameSearch", nameSearch);

			Category category = categoryService.getCategoryByNameSearch(nameSearch);
			model.addAttribute("inforCategory", category);
		} catch (Exception e) {
			return "redirect:/index";
		}

		return Constants.USER_DISPLAY_LIST_PRODUCT_BY_CATEGORY;
	}

	@GetMapping("/uu-dai")
	public String sales(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam(name = "gia", required = false) String price,
			@RequestParam(name = "hang", required = false) String manu,
			@RequestParam(name = "xep", required = false) String sort) {

		Pageable pageable = PageRequest.of(p.orElse(0), 15);

		Page<ShowProduct> listProduct = productService.getListProductByFilter("", price, manu, sort, pageable, "uu-dai",
				"", "");
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("price", price);
		model.addAttribute("manu", manu);
		model.addAttribute("sort", sort);

		return Constants.USER_DISPLAY_LIST_PRODUCT_BY_SALES;
	}

	@GetMapping("/tim-kiem")
	public String searcch(Model model, @RequestParam(name = "q", required = false) String name,
			@RequestParam("p") Optional<Integer> p, @RequestParam(name = "gia", required = false) String price,
			@RequestParam(name = "hang", required = false) String manu,
			@RequestParam(name = "xep", required = false) String sort,
			@RequestParam(name = "category", required = false) String category) {
		try {
			Pageable pageable = PageRequest.of(p.orElse(0), 15);

			if (category == null) {
				category = "";
			}

			if (name == null) {
				name = "";
			}

			Page<ShowProduct> listProduct = productService.getListProductByFilter("", price, manu, sort, pageable,
					"tim-kiem", name.trim(), category);
			if (!name.trim().isEmpty()) {
				model.addAttribute("title", "- " + name.trim());
				model.addAttribute("name", name.trim());
			}
			model.addAttribute("cate", category);
			model.addAttribute("listProduct", listProduct);
			model.addAttribute("price", price);
			model.addAttribute("manu", manu);
			model.addAttribute("sort", sort);

			List<Category> listCategory = categoryService.findAll();
			model.addAttribute("listCategory", listCategory);

		} catch (Exception e) {
			return "redirect:/index";
		}

		return Constants.USER_DISPLAY_LIST_PRODUCT_BY_SEARCH;
	}

	@ModelAttribute("listManu")
	public List<Manufacturer> listManu() {
		List<Manufacturer> list = manufacturerService.findAll();
		return list;
	}

	@ModelAttribute("listBestSeller")
	public List<ShowProduct> getListBestSeller(Model model) {
		Pageable topFour = PageRequest.of(0, 4);

		List<BestSellerModel> list = orderService.getListBestSellerProduct(topFour);

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (BestSellerModel bestSeller : list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService
					.getAllStarCommentByProductNameSearch(bestSeller.getProduct().getNamesearch());
			showProduct.setProduct(bestSeller.getProduct());
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}
		return listProduct;
	}
}
