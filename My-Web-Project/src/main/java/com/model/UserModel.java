package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.UserBean;
import com.util.JDBCDataSource;

public class UserModel {

	// Create Primary key

	public int nextPk() throws Exception {

		Connection con = null;
		int pk = 0;

		try {

			con = JDBCDataSource.getConnection();

			PreparedStatement pstmt = con.prepareStatement("Select max(id) from users");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCDataSource.closeConnection(con);
		}

		return pk + 1;

	}

	// add Method

	public void add(UserBean bean) throws Exception {

		Connection con = null;

		UserBean existBean = findByLoginID(bean.getLoginId());
		if (existBean != null) {
			throw new RuntimeException("loginId already exist");
		}

		try {
			con = JDBCDataSource.getConnection();

			con.setAutoCommit(false);

			PreparedStatement pstmt = con.prepareStatement("insert into users values(?,?,?,?,?,?)");

			pstmt.setInt(1, nextPk());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLoginId());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));

			int i = pstmt.executeUpdate();

			con.commit();

			System.out.println("Data Inserted Successfully... " + i);

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(con);
		} finally {
			JDBCDataSource.closeConnection(con);
		}

	}

	// update Method

	public void update(UserBean bean) throws Exception {

		Connection con = null;

		try {
			con = JDBCDataSource.getConnection();

			con.setAutoCommit(false);

			PreparedStatement pstmt = con.prepareStatement(
					"update users set firstName = ?, lastName = ?, loginId =?, password =?, dob =? where id = ?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLoginId());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setInt(6, bean.getId());

			int i = pstmt.executeUpdate();

			con.commit();
			System.out.println("Data Updated Successfully... " + i + " Row Effected");

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(con);

		} finally {
			JDBCDataSource.closeConnection(con);
		}
	}

	// Delete Method

	public void Delete(int id) throws Exception {

		Connection con = null;

		try {
			con = JDBCDataSource.getConnection();

			con.setAutoCommit(false);

			PreparedStatement pstmt = con.prepareStatement("Delete from users where id = ?");

			pstmt.setInt(1, id);

			int i = pstmt.executeUpdate();

			con.commit();

			System.out.println("Data Deleted Successfully... " + i + " Row Effected ");

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(con);

		} finally {
			JDBCDataSource.closeConnection(con);

		}
	}

	// find by Pk
	public UserBean findByPk(int id) throws Exception {

		Connection con = null;
		UserBean bean = null;

		try {
			con = JDBCDataSource.getConnection();

			PreparedStatement pstmt = con.prepareStatement("select * from users where id = ?");

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt("id"));
				bean.setFirstName(rs.getString("firstName"));
				bean.setLastName(rs.getString("lastName"));
				bean.setLoginId(rs.getString("loginId"));
				bean.setPassword(rs.getString("password"));
				bean.setDob(rs.getDate("dob"));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCDataSource.closeConnection(con);
		}

		return bean;

	}

	// find by LoginId

	public UserBean findByLoginID(String loginId) throws Exception {

		Connection con = null;
		UserBean bean = null;

		try {
			con = JDBCDataSource.getConnection();

			PreparedStatement pstmt = con.prepareStatement("select * from users where loginId = ?");

			pstmt.setString(1, loginId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getInt("id"));
				bean.setFirstName(rs.getString("firstName"));
				bean.setLastName(rs.getString("lastName"));
				bean.setLoginId(rs.getString("loginId"));
				bean.setPassword(rs.getString("password"));
				bean.setDob(rs.getDate("dob"));

			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			JDBCDataSource.closeConnection(con);

		}

		return bean;

	}

	// Authentication
	public UserBean authenticate(String loginId, String password) throws Exception {

		UserBean bean = new UserBean();

		bean = findByLoginID(loginId);

		if (bean != null && bean.getPassword().equals(password)) {
			return bean;
		}

		return null;

	}

	// Search Mehtod

	public List<UserBean> search(UserBean bean, int pageNo, int pageSize) {

		Connection con = null;

		List<UserBean> list = new ArrayList<UserBean>();

		StringBuffer sql = new StringBuffer("SELECT * FROM users WHERE 1 = 1 "); // Where 1=1 is Sql Injection

		try {

			if (bean != null) {

				if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
					sql.append(" and firstName like '" + bean.getFirstName() + "%'");
				}

				if (bean.getLastName() != null && bean.getLastName().length() > 0) {
					sql.append(" and lastName like '" + bean.getLastName() + "%'");
				}

				if (bean.getLoginId() != null && bean.getLoginId().length() > 0) {
					sql.append(" and loginId = '" + bean.getLoginId() + "'");
				}

				if (bean.getPassword() != null && bean.getPassword().length() > 0) {
					sql.append(" and password  = '" + bean.getPassword() + "'");
				}

				if (bean.getDob() != null && bean.getDob().getTime() > 0) {
					sql.append(" and dob = '" + new java.sql.Date(bean.getDob().getTime()) + "'");
				}

			}

			if (pageSize > 0) {

				int index = (pageNo - 1) * pageSize;

				sql.append(" limit " + index + ", " + pageSize);
			}

			System.out.println("SQL Search Query ====> " + sql.toString());
			con = JDBCDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				bean = new UserBean();

				bean.setId(rs.getInt("id"));
				bean.setFirstName(rs.getString("firstName"));
				bean.setLastName(rs.getString("lastName"));
				bean.setLoginId(rs.getString("loginId"));
				bean.setPassword(rs.getString("password"));
				bean.setDob(rs.getDate("dob"));

				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(con);
		}

		return list;
	}
}