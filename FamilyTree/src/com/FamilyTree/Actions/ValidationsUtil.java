package com.FamilyTree.Actions;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

public class ValidationsUtil {
	
	private JFrame frame_work() {
		JFrame frame = new JFrame();
		frame.setSize(200, 200);
		frame.setLocationRelativeTo(null);
		return frame;
		}
	
	
	public  void add_action_not_Performed() {
		JOptionPane.showMessageDialog(frame_work(), "Enter all Details..","Warning!..", JOptionPane.WARNING_MESSAGE);		
	}



public void add_action_Performed() {
	JOptionPane.showMessageDialog(frame_work(), "Details added successfully.");	
}

public void root_node_operation() {
	JOptionPane.showMessageDialog(frame_work(), "Cannot perform operation on root node.");
	
}

public  void data_not_found() {
	JOptionPane.showMessageDialog(frame_work() , "Person not found.");
}


public void fields_empty() {
	JOptionPane.showMessageDialog(frame_work(), "Original Name and New Name cannot be empty.");
	
}

	public static java.lang.Runnable LookandFeel() {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
	        if ("Nimbus".equals(info.getName())) {
	        	try {
	            UIManager.setLookAndFeel(info.getClassName());
	        	} catch(ClassNotFoundException |
       InstantiationException |
       IllegalAccessException |
       UnsupportedLookAndFeelException e) {
	        		e.printStackTrace();
	        	}
	        	break;
	        }
	    }
		return null ;
	}


	public void parent_not_found() {
		JOptionPane.showMessageDialog(frame_work() , "Parent not exists.");		
	}




	
}
