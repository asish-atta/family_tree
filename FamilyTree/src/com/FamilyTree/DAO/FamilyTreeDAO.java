package com.FamilyTree.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.FamilyTree.entity.FamilyMember;

public class FamilyTreeDAO {
	

	String dbURL = "jdbc:mysql://localhost:3306/library";
	String username = "root";
	String password = "mysql123";
	
	 String familyMembers = "WITH RECURSIVE family AS (" +
	            "SELECT f1.s_no FROM members AS f1 WHERE f1.s_no = ? " +
	            "UNION ALL " +
	            "SELECT f2.s_no FROM members AS f2 JOIN family ON f2.parent = family.s_no) " +
	            "SELECT * FROM family";

	
	public int parent_sno(String parent) {
		int s_no= -1;
		int parent_exists= -1;
		String gen= "Male";
		int ch = -1;
		if(parent == "root" || parent == "Root") {
			s_no = 0;
			return s_no;
		}else {
		try {

			Connection connection = DriverManager.getConnection(dbURL, username, password);
			String query = "select s_no,parent, gender, child from members where name = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, parent);
			ResultSet resultSet = preparedStatement.executeQuery(); 

	        if (resultSet.next()) { 			
	            s_no = resultSet.getInt("s_no"); 
	            parent_exists = resultSet.getInt("parent");
	            gen = resultSet.getString("gender");
	            ch = resultSet.getInt("child");
	            
	        }
			
		} catch (Exception e) {
			System.out.println(e);
		}
		if((gen.matches("Female") && ch !=1) || (gen.matches("Male") && ch !=1)) {
			s_no =  parent_exists;
			return s_no; 
		}
		return s_no;
		}
	}
	
	public void SaveUser(FamilyMember member) {
		try {

			Connection connection = DriverManager.getConnection(dbURL, username, password);
			String query = "insert into members(name, parent, is_spouse, gender, dob, imgpath, child) values(?,?,?,?,?,?,?)";
			try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
			preparedStatement.setString(1, member.getName());
			preparedStatement.setInt(2, parent_sno(member.getparent()));
			preparedStatement.setBoolean(3, member.getSpouse());
			preparedStatement.setString(4, member.getGender());
			java.sql.Date sqlDate = new java.sql.Date(member.getdob().getTime());
			preparedStatement.setDate(5, sqlDate);
			preparedStatement.setString(6, member.getIcon());
			preparedStatement.setBoolean(7, member.isSpouse_child());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void modifyData(FamilyMember member) {
		// TODO Auto-generated method stub
		
	}
	
	public List<FamilyMember> familytree() {
		 List<FamilyMember> members = new ArrayList<>();
		
	        try {
				Connection connection = DriverManager.getConnection(dbURL, username, password);
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery("SELECT s_no, name, parent,is_spouse, imgpath FROM members where child = 1 ");

	            while (resultSet.next()) {
	            	FamilyMember member = new FamilyMember();
	                member.sets_no(resultSet.getInt("s_no"));
	                member.setName(resultSet.getString("name"));
	                member.setparent_no(resultSet.getInt("parent"));
	                member.setSpouse(resultSet.getBoolean("is_spouse"));
	                member.setIcon(resultSet.getString("imgpath"));  
	                member.setSpouseName(getSpouse(member.gets_no()));
	                members.add(member);
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return members;
	    }
	
	private String getSpouse(int no) {
		String sp = null;
		String query = "SELECT name,imgpath FROM members WHERE parent = ? && is_spouse= 1 && child = ?";
	    try (Connection connection = DriverManager.getConnection(dbURL, username, password);
	         PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        
	        preparedStatement.setInt(1, no);
	        preparedStatement.setBoolean(2, false);
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	             sp = resultSet.getString(1);
	             String spouse = resultSet.getString(2);
		//		FamilyMember.setSpouseImg(spouse);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return sp;
	}

	public boolean parentExists(String parentName) {
	    String query = "SELECT COUNT(*) FROM members WHERE name = ?";
	   
	    try {
	    	Connection connection = DriverManager.getConnection(dbURL, username, password);
	         PreparedStatement statement = connection.prepareStatement(query);
	        
	        statement.setString(1, parentName);
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	            return resultSet.getInt(1) > 0; 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false; 
	}
	

	public void deleteMember(Integer selectedMemberSNo) {
	 
	//	String query = "DELETE FROM members WHERE s_no = ?";
		 String query = "WITH RECURSIVE family AS (" +
	        "SELECT s_no FROM members WHERE s_no = ? " +
	        "UNION ALL " +
	        "SELECT m.s_no FROM members m JOIN family ON m.parent = family.s_no) " +
	        "DELETE FROM members WHERE s_no IN (SELECT s_no FROM family)";
		
	    try (Connection connection = DriverManager.getConnection(dbURL, username, password)){
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, selectedMemberSNo);
	        preparedStatement.execute();
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

public List<FamilyMember> showFamily(Integer selectedMemberSNo) {
	 List<FamilyMember> members = new ArrayList<>();
		
	 String query = "WITH RECURSIVE ancestors AS (" +
	 "SELECT m1.s_no, m1.name, m1.parent, m1.is_spouse, m1.imgpath FROM members AS m1 WHERE m1.s_no = ? and m1.child=1" +
	 " UNION ALL " +
	 "SELECT m2.s_no, m2.name, m2.parent, m2.is_spouse, m2.imgpath FROM members AS m2" +
	 " JOIN ancestors ON m2.s_no = ancestors.parent where m2.child=1 )," +
	" descendants AS ( SELECT m.s_no, m.name, m.parent, m.is_spouse, m.imgpath FROM members AS m WHERE m.parent = ? and m.child=1)" +
	"SELECT * FROM ( SELECT * FROM ancestors UNION ALL SELECT * FROM descendants )AS combined ORDER BY parent ASC ";

	    try (Connection connection = DriverManager.getConnection(dbURL, username, password)){
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, selectedMemberSNo);
			preparedStatement.setInt(2, selectedMemberSNo);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	        while (resultSet.next()) {
            	FamilyMember member = new FamilyMember();
                member.sets_no(resultSet.getInt("s_no"));
                member.setName(resultSet.getString("name"));
                member.setparent_no(resultSet.getInt("parent"));
                member.setSpouse(resultSet.getBoolean("is_spouse"));
                member.setIcon(resultSet.getString("imgpath"));  
                member.setSpouseName(getSpouse(member.gets_no()));
                members.add(member); 
            }
	    }
	    catch (SQLException e) {
	        e.printStackTrace();
	    }
		return members;
	}

	}
