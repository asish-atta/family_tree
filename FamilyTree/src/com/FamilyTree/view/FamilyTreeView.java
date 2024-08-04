package com.FamilyTree.view;


import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.text.MaskFormatter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.FamilyTree.Actions.ValidationsUtil;
import com.FamilyTree.DAO.FamilyTreeDAO;
import com.FamilyTree.entity.FamilyMember;
import com.FamilyTree.entity.UserActions;

public class FamilyTreeView {
	FamilyTreeDAO familyTreeDAO = new FamilyTreeDAO();		
	Map<DefaultMutableTreeNode, Integer> nodeToIdMap = new HashMap<>();
	private JTextField Name_textfield, parent_textfield;
	private String gender;
	private Boolean spouse = false; 
	private JRadioButton marriedButton,unmarriedButton, maleButton,femaleButton ;
	private ButtonGroup G,G0, G1;
	 private JFormattedTextField dobTextField; 
	 private SimpleDateFormat dobFormat = new SimpleDateFormat("dd/MM/yyyy"); 
	 JTree tree;
	 Integer selectedMemberSNo;
	@SuppressWarnings("serial")
	public FamilyTreeView() {
		
		 List<FamilyMember> members;
		try {
			members = familyTreeDAO.familytree();
			tree = Tree(members);
			tree.setCellRenderer(new DefaultTreeCellRenderer() {
			    @Override
			    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
			        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

			        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			        if (node.getUserObject() instanceof FamilyMember) {
			            FamilyMember member = (FamilyMember) node.getUserObject();
			            String displayName = member.getName();
			            // Check if the member is married and append the spouse's name
			            if (member.isMarried() && member.getSpouseName() != null) {
			                displayName += " & " + member.getSpouseName();
			            }
			            setText(displayName);

			            // Set icon based on imagePath (assuming you have such logic)
			            String imagePath = member.getIcon();
			            if (imagePath != null && !imagePath.isEmpty()) {
			                ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			                setIcon(icon);
			            }
			        }
			        return this;
			    }
			});

			
	/*		tree.setCellRenderer(new DefaultTreeCellRenderer() {

			    @Override
			    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
			        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

			        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			        if (node.getUserObject() instanceof FamilyMember) {
			            FamilyMember member = (FamilyMember) node.getUserObject();
			            String imagePath = member.getIcon();
			            if (imagePath != null && !imagePath.isEmpty()) {
			                ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			                setIcon(icon);
			            } else {
			                // Optionally set a default icon if no image path is found
			                // setIcon(defaultIcon);
			            }
			        }

			        return this;
			    }
			});
*/
			
	/*	to store image directly in database..
	 * 	tree.setCellRenderer(new DefaultTreeCellRenderer() {
			    
			    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
			        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
			        
			        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
			        if (node.getUserObject() instanceof FamilyMember) {
			            FamilyMember member = (FamilyMember) node.getUserObject();
			            ImageIcon icon = member.getImageIcon(); 
			            if (icon != null) {
			                Image image = icon.getImage();  
			                Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // Scale it to 20x20
			                icon = new ImageIcon(newimg);  
			            }
			            setIcon(icon);
			        }
			        
			        return this;
			    }
			});
			*/

		} catch (Exception e) {
			e.printStackTrace();
		}

	
		
		
			
		
		SwingUtilities.invokeLater(ValidationsUtil.LookandFeel());
        JFrame frame = new JFrame("Family tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new JScrollPane(tree));
        frame.setSize(500,500);
        frame.setVisible(true);
        
	
        frame.setLayout(new GridLayout(1, 2));
		JPanel inputPanel = new JPanel();

		frame.add(inputPanel);
		inputPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(5, 5, 5, 5);		

		Name_textfield = new JTextField();
		parent_textfield = new JTextField();

		inputPanel.add(new JLabel("Name"),gridBagConstraints);
		gridBagConstraints.gridx = 1;
		inputPanel.add(Name_textfield,gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy++;
	//	inputPanel.add(new JLabel("S/o,D/o,W/o"),gridBagConstraints);
		JPanel spouse_or_childPanel = new JPanel();
		JRadioButton spouseButton = new JRadioButton("spouse"); 
		JRadioButton childButton = new JRadioButton("S/o,D/o");
		G0 = new ButtonGroup();  
        G0.add(spouseButton);
        G0.add(childButton);
        spouse_or_childPanel.add(spouseButton);
        spouse_or_childPanel.add(childButton);
        inputPanel.add(spouse_or_childPanel,gridBagConstraints);
		gridBagConstraints.gridx = 1;
		inputPanel.add(parent_textfield,gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy++;
		inputPanel.add(new JLabel("Gender"),gridBagConstraints);
		JPanel genderStatusPanel = new JPanel();
		maleButton = new JRadioButton("Male"); 
		femaleButton = new JRadioButton("Female");
		G = new ButtonGroup();  
        G.add(maleButton);
        G.add(femaleButton);
        genderStatusPanel.add(maleButton);
        genderStatusPanel.add(femaleButton);
        gridBagConstraints.gridx = 1;
        inputPanel.add(genderStatusPanel, gridBagConstraints);

		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy++;
		inputPanel.add(new JLabel("Marrital status"),gridBagConstraints);
		JPanel maritalStatusPanel = new JPanel();
		marriedButton = new JRadioButton("Married"); 
		unmarriedButton = new JRadioButton("Un-married");
		G1 = new ButtonGroup();  
        G1.add(marriedButton);
        G1.add(unmarriedButton);
        maritalStatusPanel.add(marriedButton);
        maritalStatusPanel.add(unmarriedButton);
        gridBagConstraints.gridx = 1;
        inputPanel.add(maritalStatusPanel, gridBagConstraints);
    
        
        try {
            MaskFormatter dobMask = new MaskFormatter("##/##/####");
            dobMask.setPlaceholderCharacter('_');
            dobTextField = new JFormattedTextField(dobMask);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        inputPanel.add(new JLabel("DOB (dd/mm/yyyy)"), gridBagConstraints);
        gridBagConstraints.gridx = 1;
        inputPanel.add(dobTextField, gridBagConstraints);
		
		JButton upload_button = new JButton("Upload profile picture...");
		 gridBagConstraints.gridx = 0;
	     gridBagConstraints.gridy++;
	     inputPanel.add(new JLabel("Choose image"), gridBagConstraints);
	     gridBagConstraints.gridx = 1;
	     inputPanel.add(upload_button , gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy++;
		gridBagConstraints.gridwidth = 3; 
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;	
		gridBagConstraints.insets = new Insets(50, 0, 0, 0);
		

		JPanel buttonsPanel = new JPanel();
		JButton add_button = new JButton("Add");
		JButton delete_button = new JButton("Delete");
		JButton fetch_button = new JButton("Fetch Family");

		buttonsPanel.add(add_button);
		buttonsPanel.add(delete_button);
		buttonsPanel.add(fetch_button);
		inputPanel.add(buttonsPanel, gridBagConstraints);
		
		
		ValidationsUtil validationsUtil = new ValidationsUtil();
		UserActions options = new UserActions();
		FamilyMember member = new FamilyMember();
		
		
		tree.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		        if (selectedNode != null) {
		            selectedMemberSNo = nodeToIdMap.get(selectedNode); 
		        }int row=tree.getRowForLocation(e.getX(),e.getY());
			    if(row==-1) 
			        tree.clearSelection();
		    }
		});

		frame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    int row=tree.getRowForLocation(e.getX(),e.getY());
			    if(row==-1) 
			        tree.clearSelection();
			}
			});

		
		add_button.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member.setName(Name_textfield.getText());
				member.setparent(parent_textfield.getText());
				spouse = (Boolean) (marriedButton.isSelected()? true : unmarriedButton.isSelected() ? false : "");
				member.setSpouse(spouse);
				gender =  maleButton.isSelected() ? "Male" : femaleButton.isSelected() ? "Female" : "";
				member.setGender(gender);
				Boolean child = (Boolean) (childButton.isSelected()? true : spouseButton.isSelected() ? false : "");
				member.setSpouse_child(child);
		        String dobStr = dobTextField.getText(); 
		        Date dob = null;
		        try {
		            dob = dobFormat.parse(dobStr);
		        } catch (ParseException ex) {
		            return;
		        }
		        member.setdob(dob);
				if(member.getName().equals("")
						|| member.getparent().equals("")
						|| member.getGender().equals("")) {
			validationsUtil.add_action_not_Performed();
				} else {
					try {  
						if( (!familyTreeDAO.parentExists(member.getparent()))) {
							validationsUtil.parent_not_found();
							return;
						}else {
						options.addUser(member);
						validationsUtil.add_action_Performed();
						refreshTree();
						}
					}catch (Exception e1) {
						e1.printStackTrace();
					}
					
						clearFields();
				}
			}
		});
				
		
/*		 
		 upload_button.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        JFileChooser fileChooser = new JFileChooser();
			        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			        int result = fileChooser.showOpenDialog(frame); 
			        if (result == JFileChooser.APPROVE_OPTION) {
			            File selectedFile = fileChooser.getSelectedFile();
			            try {
			                byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());
			                member.setImg(imageBytes); 
			                } catch (IOException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(frame, "Failed to load image", "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        }
			    }
			});

		 */
		
//	 copying img to project folder.
		upload_button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		        int result = fileChooser.showOpenDialog(frame);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            String destinationPath = "C:/Users/srini/eclipse-workspace/FamilyTree/images/"; 
		            
		            File directory = new File(destinationPath);
		            if (!directory.exists()) {
		                directory.mkdirs(); 
		                }
		            
		            String destinationFilePath = destinationPath + selectedFile.getName(); 
		            File destinationFile = new File(destinationFilePath);
		            try {
		                Files.copy(selectedFile.toPath(), destinationFile.toPath()); 
		                member.setIcon(destinationFilePath); 
		            } catch (IOException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(frame, "Failed to save image", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});


	
		
		 fetch_button.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	 if (selectedMemberSNo != null) {
		                    try {
		                    	List<FamilyMember> members;
		                        members = familyTreeDAO.showFamily(selectedMemberSNo);
		            			JTree t = Tree(members);
		            			t.setCellRenderer(new DefaultTreeCellRenderer() {
		            			    @Override
		            			    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		            			        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		            			        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		            			        if (node.getUserObject() instanceof FamilyMember) {
		            			        	
		            			        	String spouseImagepath;
		            			        	ImageIcon spouseicon;
		            			        	
		            			            FamilyMember member = (FamilyMember) node.getUserObject();
		            			            String displayName = member.getName();
		            			            // Check if the member is married and append the spouse's name
		            			            if (member.isMarried() && member.getSpouseName() != null) {
		            			                displayName += " & " + member.getSpouseName();
		            			      //spouse img         
		            			                spouseImagepath = member.getSpouseImg();
		            				//             System.out.println(spouseImagepath);

		            			                if (spouseImagepath != null && !spouseImagepath.isEmpty()) {
			            			                spouseicon = new ImageIcon(new ImageIcon(spouseImagepath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
			            			                setIcon(spouseicon);
			            			            }
		            			            }
		            			            setText(displayName);
		            			            
		            			            String imagePath = member.getIcon();
		            			            if (imagePath != null && !imagePath.isEmpty()) {
		            			                ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		            			                setIcon(icon);
		            			            }
		            			        }
		            			        return this;
		            			    }
		            			});

		            			JFrame familyFrame = new JFrame("Family Details");
		                	    familyFrame.setSize(600, 400);
		                	    familyFrame.add(t);
		                	    familyFrame.setVisible(true);
		                        refreshTree();
		                        selectedMemberSNo = null; 
		                    } catch (Exception ex) {
		                        ex.printStackTrace();
		                    }
		                }		          
		        }
		    });
		    

		delete_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (selectedMemberSNo != null) {
                    try {
                        options.deleteUser(selectedMemberSNo); 
                        refreshTree(); 
                        selectedMemberSNo = null; 
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
               
            }
        });
			
		
	}
	

	private void clearFields() {
		Name_textfield.setText(null);
		parent_textfield.setText(null);
		G.clearSelection();
		G0.clearSelection();
		G1.clearSelection();
		dobTextField.setText(""); 
	}
	
	
	public JTree Tree(List<FamilyMember> members) {
		
	    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Atta");
		
	    nodeToIdMap.clear(); 
	    
	    Map<Integer, DefaultMutableTreeNode> idToNodeMap = new HashMap<>();

	    for (FamilyMember member : members) {
	        DefaultMutableTreeNode memberNode = new DefaultMutableTreeNode(member);
	        idToNodeMap.put(member.gets_no(), memberNode);
	        nodeToIdMap.put(memberNode, member.gets_no());
	        
	        
	        if (member.getparent_no() == 0 ) { 
	            root.add(memberNode); 
	        }else {
	        	DefaultMutableTreeNode childNode = idToNodeMap.get(member.gets_no());
	            DefaultMutableTreeNode parentNode = idToNodeMap.get(member.getparent_no());
	            parentNode.add(childNode);
	          
	    }
	}
	    return new JTree(root);
	}
	
	
	private void refreshTree() {
	    List<FamilyMember> members = familyTreeDAO.familytree(); 
	    JTree updatedTree = Tree(members); 
	    tree.setModel(updatedTree.getModel()); 
	    tree.revalidate(); 
	}
	


	
	public static void main(String[] args) {
		
		new FamilyTreeView();
	}
		
	}
	
	
