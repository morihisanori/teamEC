
package com.internousdev.latte.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.latte.dto.CartInfoDTO;
import com.internousdev.latte.util.DBConnector;

public class ProductDetailsFunctionCartInfoDAO {
	private CartInfoDTO cartInfoDTO = new CartInfoDTO();


	/**
	 * 「カートに追加ボタン」押下時に受けとった商品IDをもとに
	 * カート情報に一致する情報があるか確認するためのメソッド
	 * (一致すれば購入個数を加算、一致しなければ新たに挿入  !!!この２つのメソッドも下に書く!!!)
	 * @return カートテーブルのユーザーIDと商品ID
	 */
	public CartInfoDTO getCartInfoLinkUserId(String userId, int productId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "SELECT * FROM cart_info WHERE user_id = ? AND product_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				cartInfoDTO.setUserId(rs.getString("user_id"));
				cartInfoDTO.setProductId(rs.getInt("product_id"));
			}

		} catch(SQLException e) {
			e.printStackTrace();
		} try {
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return cartInfoDTO;
	}



	/**
	 * ユーザーIDと商品IDどちらも一致する
	 * カート情報がないときに新たにデータを挿入するためのメソッド
	 * @return 挿入実行件数(1 or 0)
	 */
	public int insertCartInfo(String userId, int productId, int productCount) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int insertCount = 0;

		String sql = "INSERT INTO cart_info "
				+ "(user_id, product_id, product_count, regist_date, update_date) "
				+ "VALUES(?,?,?, now(), now())";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, productId);
			ps.setInt(3, productCount);
			insertCount = ps.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		} try {
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return insertCount;
	}



	/**
	 * ユーザーIDと商品IDどちらも一致する
	 * カート情報があるときに個数の値に
	 * 受け取った個数を加算するためのメソッド
	 * @return 加算実行件数(1 or 0)
	 */
	public int addProductCountOfCartInfo(int productCount, String userId, int productId) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int addCount = 0;

		String sql = "UPDATE cart_info "
				+ "SET product_count = product_count + ? , regist_date = now() "
				+ "WHERE user_id = ? AND product_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, productCount);
			ps.setString(2, userId);
			ps.setInt(3, productId);
			addCount = ps.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		} try {
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return addCount;
	}

}
