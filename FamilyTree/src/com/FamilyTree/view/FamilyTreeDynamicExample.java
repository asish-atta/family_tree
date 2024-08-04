package com.FamilyTree.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class FamilyTreeDynamicExample {

	 JFrame frame;
	    DefaultMutableTreeNode root;
	    DefaultTreeModel treeModel;
	    JTree tree;
	    JTextField nameTextField, parentTextField;
	    Map<String, DefaultMutableTreeNode> nameToNodeMap;

	    public FamilyTreeDynamicExample() {
	        frame = new JFrame();
	        root = new DefaultMutableTreeNode("Atta");
	        treeModel = new DefaultTreeModel(root);
	        tree = new JTree(treeModel);
	        nameToNodeMap = new HashMap<>();

	        JSplitPane splitPane = new JSplitPane();
	        frame.add(splitPane);

	        // Left side - Tree View
	        JScrollPane scrollPane = new JScrollPane(tree);
	        splitPane.setLeftComponent(scrollPane);

	        // Right side - User Input
	        JPanel inputPanel = new JPanel();
	        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
	        nameTextField = new JTextField(20);
	        parentTextField = new JTextField(20);
	        JButton addButton = new JButton("Add Person");

	        inputPanel.add(new JLabel("Name:"));
	        inputPanel.add(nameTextField);
	        inputPanel.add(new JLabel("Parent's Name:"));
	        inputPanel.add(parentTextField);
	        inputPanel.add(addButton);

	        splitPane.setRightComponent(inputPanel);

	        // Action Listener for the Add Button
	        addButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String name = nameTextField.getText();
	                String parentName = parentTextField.getText();

	                DefaultMutableTreeNode parentNode = nameToNodeMap.getOrDefault(parentName, root);
	                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(name);

	                treeModel.insertNodeInto(childNode, parentNode, parentNode.getChildCount());
	                nameToNodeMap.put(name, childNode);

	                // Clear input fields
	                nameTextField.setText("");
	                parentTextField.setText("");
	            }
	        });

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(800, 600);
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        new FamilyTreeDynamicExample();
	    }
}
