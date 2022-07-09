package it.polito.tdp.yelp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.polito.tdp.yelp.model.Business;
import it.polito.tdp.yelp.model.Coppia;
import it.polito.tdp.yelp.model.Review;
import it.polito.tdp.yelp.model.User;

public class YelpDao {
	
	
	public List<Business> getAllBusiness(){
		String sql = "SELECT * FROM Business";
		List<Business> result = new ArrayList<Business>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Business business = new Business(res.getString("business_id"), 
						res.getString("full_address"),
						res.getString("active"),
						res.getString("categories"),
						res.getString("city"),
						res.getInt("review_count"),
						res.getString("business_name"),
						res.getString("neighborhoods"),
						res.getDouble("latitude"),
						res.getDouble("longitude"),
						res.getString("state"),
						res.getDouble("stars"));
				result.add(business);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Review> getAllReviews(){
		String sql = "SELECT * FROM Reviews";
		List<Review> result = new ArrayList<Review>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Review review = new Review(res.getString("review_id"), 
						res.getString("business_id"),
						res.getString("user_id"),
						res.getDouble("stars"),
						res.getDate("review_date").toLocalDate(),
						res.getInt("votes_funny"),
						res.getInt("votes_useful"),
						res.getInt("votes_cool"),
						res.getString("review_text"));
				result.add(review);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<User> getAllUsers(){
		String sql = "SELECT * FROM Users";
		List<User> result = new ArrayList<User>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				User user = new User(res.getString("user_id"),
						res.getInt("votes_funny"),
						res.getInt("votes_useful"),
						res.getInt("votes_cool"),
						res.getString("name"),
						res.getDouble("average_stars"),
						res.getInt("review_count"));
				
				result.add(user);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<String> getCitta(){
		String sql = "SELECT DISTINCT city FROM Business "
				+"ORDER BY city ";
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				result.add(res.getString("city"));
			}
			
			res.close();
			st.close();
			conn.close();
			return result;
	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getLocale(String citta){
		String sql = "SELECT b.business_name AS locale "
				+ "FROM business b "
				+ "WHERE b.city = ? "
				+ "ORDER BY locale ";
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, citta);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				result.add(res.getString("locale"));
			}
			
			res.close();
			st.close();
			conn.close();
			return result;
	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getVertici(String c, String locale){
		String sql = "SELECT r.review_id AS r "
				+ "FROM reviews r, business b "
				+ "WHERE r.business_id=b.business_id "
				+"AND b.city=? "
				+ "	AND b.business_name =? ";
		
		List<String> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, c);
			st.setString(2, locale);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				result.add(res.getString("r"));
			}
			
			res.close();
			st.close();
			conn.close();
			return result;
	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public List<Coppia> getArchi(String locale){
		String sql ="SELECT r1.review_id AS r1, r2.review_id AS r2, (DATE(r2.review_date)-DATE(r1.review_date) ) AS peso "
				+ "FROM reviews r1, reviews r2, business b1 "
				+ "WHERE date(r1.review_date)<DATE(r2.review_date) "
				+ "		AND r1.review_id<>r2.review_id "
				+ "		AND r1.business_id = b1.business_id "
				+ "		AND r2.business_id = b1.business_id "
				+ "		AND b1.business_name = ? "
				+ "HAVING peso>0 "
				+ "ORDER BY peso";
		List<Coppia> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, locale);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				result.add(new Coppia(res.getString("r1"), res.getString("r2"), res.getInt("peso")));
			}
			
			res.close();
			st.close();
			conn.close();
			return result;
	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
