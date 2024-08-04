 package com.FamilyTree.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class FamilyTreeStaticExample {
	
	
	JFrame f;  
	FamilyTreeStaticExample(){  
	    f=new JFrame();   
	    DefaultMutableTreeNode atta=new DefaultMutableTreeNode("Seshaiah❤️Samrajyam");  
	    DefaultMutableTreeNode venkateswarao=new DefaultMutableTreeNode("VenkateswaraRao❤️VenkataSubbamma");  
	    DefaultMutableTreeNode narayanaswami=new DefaultMutableTreeNode("Narayanaswami❤️Bujjimma");  
	    DefaultMutableTreeNode koteswarao=new DefaultMutableTreeNode("KoteswaraRao❤️Chittemma");  
	    DefaultMutableTreeNode narasimharao=new DefaultMutableTreeNode("NarasimharaRAo❤️Subbamma");  
	    DefaultMutableTreeNode prakaswarao=new DefaultMutableTreeNode("PrakaswaraRao❤️KrishnaKumari");  
	    DefaultMutableTreeNode nageswarao=new DefaultMutableTreeNode("NageswaraRao❤️Vijayalakshmi");  
	    DefaultMutableTreeNode suseela=new DefaultMutableTreeNode("Suseela❤️Subbarao");  
	    DefaultMutableTreeNode jamalaiah=new DefaultMutableTreeNode("Jamalaiah❤️Dhanalakshmi");  
	    DefaultMutableTreeNode srinivasarao=new DefaultMutableTreeNode("SrinivasaRao❤️Padmaja");  
	    
	    atta.add(venkateswarao);  
	    atta.add(narayanaswami);
	    atta.add(koteswarao);
	    atta.add(narasimharao);
	    atta.add(prakaswarao);
	    atta.add(nageswarao);
	    atta.add(suseela);
	    atta.add(jamalaiah);
	    atta.add(srinivasarao);
	    
	    DefaultMutableTreeNode ramesh=new DefaultMutableTreeNode("RameshBabu❤️Nagalakshmi"); 
	    DefaultMutableTreeNode abhi=new DefaultMutableTreeNode("Abhinav");  
	    DefaultMutableTreeNode pranu=new DefaultMutableTreeNode("pranathi"); 
	    
	    DefaultMutableTreeNode ramu=new DefaultMutableTreeNode("RamakrishnaRao❤️Subhashini");  
	    DefaultMutableTreeNode pradhyu=new DefaultMutableTreeNode("Pradhyulatha");  
	    DefaultMutableTreeNode sai=new DefaultMutableTreeNode("Venkata Sai Avinash"); 
	    
	    venkateswarao.add(ramesh); venkateswarao.add(ramu); 
	    ramesh.add(abhi);ramesh.add(pranu);
	    ramu.add(pradhyu);ramu.add(sai);
	    
	    
	    DefaultMutableTreeNode venu=new DefaultMutableTreeNode("venu❤️rajeswari"); 
	    DefaultMutableTreeNode viswak=new DefaultMutableTreeNode("viswak");  
	    DefaultMutableTreeNode adhithya=new DefaultMutableTreeNode("Adhithya"); 
	    
	    narayanaswami.add(venu);
	    venu.add(viswak);
	    venu.add(adhithya);
	    
	    DefaultMutableTreeNode mahesh= new DefaultMutableTreeNode("MaheswaraRao❤️Thirumala"); 
	    DefaultMutableTreeNode asish=new DefaultMutableTreeNode("Asish Kumar");  
	    DefaultMutableTreeNode srinivas=new DefaultMutableTreeNode("Srinivas"); 
	    
	    koteswarao.add(mahesh);
	    mahesh.add(asish);
	    mahesh.add(srinivas);
	    
	    DefaultMutableTreeNode tirupatirao=new DefaultMutableTreeNode("TirupatiRao❤️Sudharani"); 
	    DefaultMutableTreeNode prasanna=new DefaultMutableTreeNode("Sri Lakshmi Prasanna");  
	    DefaultMutableTreeNode harsha=new DefaultMutableTreeNode("Venkata Sri Harsha"); 
	    
	    koteswarao.add(tirupatirao);
	    tirupatirao.add(prasanna);
	    tirupatirao.add(harsha);
	    
	    DefaultMutableTreeNode lakshmi=new DefaultMutableTreeNode("Bhu Lakshmi❤️Ramesh"); 
	    DefaultMutableTreeNode harika=new DefaultMutableTreeNode("Sri Harika");  
	    DefaultMutableTreeNode saran=new DefaultMutableTreeNode("Sai Saran");
	    
	    koteswarao.add(lakshmi);
	    lakshmi.add(harika);
	    lakshmi.add(saran);
	    
	    DefaultMutableTreeNode prasad=new DefaultMutableTreeNode("Prasad❤️Anuradha"); 
	    DefaultMutableTreeNode navya=new DefaultMutableTreeNode("Navya");  
	    DefaultMutableTreeNode pranavi=new DefaultMutableTreeNode("pranavi");
	    
	    narasimharao.add(prasad);
	    prasad.add(navya);
	    prasad.add(pranavi);
	    
	    DefaultMutableTreeNode chinni=new DefaultMutableTreeNode("Jamaliah❤️Rani"); 
	    DefaultMutableTreeNode nithya=new DefaultMutableTreeNode("Nithya");  
	    DefaultMutableTreeNode sahithi=new DefaultMutableTreeNode("Sahithi");
	    DefaultMutableTreeNode akshaya = new DefaultMutableTreeNode("Akshaya");
	    
	    narasimharao.add(chinni);
	    chinni.add(nithya);
	    chinni.add(sahithi);
	    chinni.add(akshaya);
	    
	    DefaultMutableTreeNode bulli=new DefaultMutableTreeNode("Siva❤️Rani"); 
	    DefaultMutableTreeNode sanjay=new DefaultMutableTreeNode("Sanjay");  
	    DefaultMutableTreeNode sanjana = new DefaultMutableTreeNode("Sanjana"); 
	    
	    narasimharao.add(bulli);
	    bulli.add(sanjay);
	    bulli.add(sanjana);
	    
	    DefaultMutableTreeNode suresh=new DefaultMutableTreeNode("Naga Suresh❤️Lavanya");  
	    DefaultMutableTreeNode pradeep=new DefaultMutableTreeNode("pradeep❤️Sri Harika");
	    DefaultMutableTreeNode aryan=new DefaultMutableTreeNode("Aryan Samrat");  
	    DefaultMutableTreeNode anshu=new DefaultMutableTreeNode("Anshu"); 
	     
	    prakaswarao.add(suresh);
	    prakaswarao.add(pradeep);
	    suresh.add(aryan);
	    suresh.add(anshu);
	    
	    DefaultMutableTreeNode thirumala=new DefaultMutableTreeNode("Thirumala");  
	    DefaultMutableTreeNode rajini=new DefaultMutableTreeNode("Rajini"); 
	    DefaultMutableTreeNode giri = new DefaultMutableTreeNode("Giri");
	    
	    nageswarao.add(thirumala);
	    nageswarao.add(rajini);
	    nageswarao.add(giri);
	    
	    DefaultMutableTreeNode kittu=new DefaultMutableTreeNode("Kishore❤️Ramya"); 
	    DefaultMutableTreeNode haritha=new DefaultMutableTreeNode("Haritha❤️Ravi");  
	    jamalaiah.add(haritha);
	    jamalaiah.add(kittu);
	    
	    DefaultMutableTreeNode munni=new DefaultMutableTreeNode("Mounika❤️prathap"); 
	    DefaultMutableTreeNode hari=new DefaultMutableTreeNode("Sai Harika❤️Gopi");  
	    srinivasarao.add(munni);
	    srinivasarao.add(hari);
	    
	    
	    
	    JTree jt=new JTree(atta);  
	    JScrollPane scrollpane = new JScrollPane(jt);
	    f.add(scrollpane);
	    f.setSize(400,400);  
	    f.setVisible(true);  
	}  
	public static void main(String[] args) {
		new FamilyTreeStaticExample();
			
	}
	
	 void add() {
		 JTextField Name_textfield = new JTextField();
			JTextField Father_Name_textfield = new JTextField();
			
			
			f.add(new JLabel("Name"));
			f.add(Name_textfield);

			f.add(new JLabel("Father_Name"));
			f.add(Father_Name_textfield);
	}

}







