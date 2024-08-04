package com.FamilyTree.entity;

import com.FamilyTree.DAO.FamilyTreeDAO;

public class UserActions {

	
	private FamilyTreeDAO memberDAO = new FamilyTreeDAO();
	
	public void addUser(FamilyMember member) throws Exception {
		if(member != null) {
			memberDAO.SaveUser(member);
		}else {
			throw new Exception("member data not there.");
		}
	}
	
	
 public void modifyData(FamilyMember member) throws Exception {
		if(member != null) {
			memberDAO.modifyData(member);
		}else {
			
			throw new Exception("member data not there.");
		}
	} 
 
 public void deleteUser(Integer selectedMemberSNo) throws Exception{
		if(selectedMemberSNo != null) {
			memberDAO.deleteMember(selectedMemberSNo);
		}else {
			throw new Exception("student data not there.");
		}
	}


public void Familyview(Integer selectedMemberSNo) throws Exception {
	if(selectedMemberSNo != null) {
		memberDAO.showFamily(selectedMemberSNo);
	}else {
		throw new Exception("no family.");
	}	
}
 
 
}
