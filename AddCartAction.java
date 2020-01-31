package com.internousdev.latte.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.latte.dao.CartInfoDAO;
import com.internousdev.latte.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class AddCartAction extends ActionSupport implements SessionAware {

	private int productId;
	private int productCount;
	private int price;
	private Map<String, Object> session;
	private List<CartInfoDTO> cartInfoDTO = new ArrayList<CartInfoDTO>();
	private int totalPrice;

	public String execute() throws SQLException {
		String res = ERROR;
		if (!session.containsKey("tempUserId") && !session.containsKey("userId")) {
			return "sessionTimeout";
		}

		String userId = null;
		String _logined = String.valueOf(session.get("logined"));
		int logined = "null".equals(_logined) ? 0 : Integer.parseInt(_logined);
		// カート情報を取得;
		if (logined == 1) {
			//ログインしているならユーザーID
			userId = session.get("userId").toString();
		} else {
			//ログインしていないなら仮ユーザーID
			userId = session.get("tempUserId").toString();
		}

		int count = 0;
		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		if (cartInfoDAO.isExistsProduct(userId, productId)) {
			//存在する場合、仮ユーザーのカートの個数を足す。
			count = cartInfoDAO.updateCartInfo(userId, productId, productCount);

		} else {
			//存在しない場合仮ユーザーIDを本ユーザーIDに更新する
			count = cartInfoDAO.registCartInfo(userId, productId, productCount);
		}

		if (count > 0) {

			cartInfoDTO = cartInfoDAO.getCartInfoDTO(userId);
			totalPrice = cartInfoDAO.getTotalPrice(userId);
			res = SUCCESS;
		}
		return res;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<CartInfoDTO> getCartInfoDTO() {
		return this.cartInfoDTO;
	}

	public void setCartInfoDTO(List<CartInfoDTO> cartInfoDTO) {
		this.cartInfoDTO = cartInfoDTO;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}