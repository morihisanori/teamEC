package com.internousdev.latte.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.latte.dao.ProductInfoDAO;
import com.internousdev.latte.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDetailsAction extends ActionSupport implements SessionAware {
	private int productId;
	private int categoryId;
	private List<Integer> productCountList;
	private List<ProductInfoDTO> relatedProductList;
	private ProductInfoDTO productInfoDTO = new ProductInfoDTO();
	private String message;
	private Map<String, Object> session;

	public String execute() {

		//商品情報を取得

		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		productInfoDTO = productInfoDAO.getProductDetails(productId);
		session.put("productId", productId);

		if (productInfoDTO.getProductName() == null) {
			productInfoDTO = null;
			setMessage("商品情報なし");

		} else {
			productCountList = new ArrayList<Integer>();
			for (int i = 1; i < 6; i++) {
				productCountList.add(i);
			}

			relatedProductList = productInfoDAO.getReleatedProductInfo(productInfoDTO.getCategoryId(),
					productInfoDTO.getProductId());

		}

		return SUCCESS;
	}

	public String getMessage() {
		return message;

	}

	public void setMessage(String message) {
		this.message = message;

	}

	public List<Integer> getProductCountList() {
		return productCountList;
	}

	public void setProuductCountList(List<Integer> productCountList) {
		this.productCountList = productCountList;
	}

	public List<ProductInfoDTO> getRelatedProductList() {
		return relatedProductList;
	}

	public void setRelatedProductList(List<ProductInfoDTO> relatedProductList) {
		this.relatedProductList = relatedProductList;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public ProductInfoDTO getProductInfoDTO() {
		return productInfoDTO;

	}

	public void setProductInfoDTO(ProductInfoDTO productInfoDTO) {
		this.productInfoDTO = productInfoDTO;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setProductCountList(List<Integer> productCountList) {
		this.productCountList = productCountList;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
