package com.FamilyTree.entity;

import java.util.Date;

import javax.swing.ImageIcon;

public class FamilyMember {

	private Date dob;
	private String Name;
	private String parent;
	private String Gender;
	private boolean Spouse;
	private int parent_no;
	private int s_no;
	private String Icon;
	private byte[] Img;
	private String SpouseName;
	private boolean spouse_child;
	private String SpouseImg; 
	
	public String getSpouseName() {
		return SpouseName;
	}
	public void setSpouseName(String spouseName) {
		SpouseName = spouseName;

	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getparent() {
		return parent;
	}
	public void setparent(String parent) {
		this.parent = parent;
	}
	public Date getdob() {
		return dob;
	}
	public void setdob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		this.Gender = gender;
	}
	public boolean getSpouse() {
		return Spouse;
	}
	public void setSpouse(boolean spouse) {
		this.Spouse = spouse;
	}
	public int getparent_no() {
		return parent_no;
	}
	public void setparent_no(int parent_no) {
		this.parent_no = parent_no;
	}
	public int gets_no() {
		return s_no;
	}
	public void sets_no(int s_no) {
		this.s_no = s_no;
	}
	public String getIcon() {
		return Icon;
	}
	public void setIcon(String icon) {
		Icon = icon;
	}
	public byte[] getImg() {
		return Img;
	}
	public void setImg(byte[] imageBytes) {
		Img = imageBytes;
	}
	
	public ImageIcon getImageIcon() {
	    if (this.Img != null) {
	        return new ImageIcon(this.Img);
	    } else {
	        // Return a default icon or null
	        return null;
	    }
	}

	 public String toString() {
	        return this.Name; // Adjust this to display whatever information you want
	    }
	public boolean isMarried() {
		if(this.Spouse)
		return Spouse;
		
		return false;
	}
	public boolean isSpouse_child() {
		return spouse_child;
	}
	public void setSpouse_child(boolean spouse_child) {
		this.spouse_child = spouse_child;
	}
	public String getSpouseImg() {
		// TODO Auto-generated method stub
		return SpouseImg;
	}
	
	public void setSpouseImg(String spouseimg) {
		 SpouseImg = spouseimg;
	}
	
	
	
}
