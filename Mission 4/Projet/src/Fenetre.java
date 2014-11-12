import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class Fenetre extends JFrame{
	/**
	 * @author Baptiste Degryse
	 */

	private static final long serialVersionUID = 1L;
	private JTextField recherche[]=new JTextField[3];
	private JButton bouton=new JButton("OK");
	private JPanel cont=new JPanel();
	private JPanel rech=new JPanel();
	private JCheckBox cb=new JCheckBox("ordre ascendant");

	public Fenetre(){
		super("Recherche dans la base de donnée");
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.white);
		
		String dir="";
		JFileChooser fc = new JFileChooser();
		// Demonstrate "Open" dialog:
		fc.setCurrentDirectory(new File("."));
		int rVal = fc.showOpenDialog(null);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			dir=fc.getCurrentDirectory().toString()+"/"+fc.getSelectedFile().getName();
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			dir="";
		}

		if(!dir.equals(""))
			Main.setDictionnaire(new Dictionnaire(1,dir,","));//"song.dat"));
		else
			System.exit(1);
		getContentPane().setLayout(new BorderLayout());
		
		JLabel[] name=new JLabel[]{new JLabel("fieldName"),new JLabel("fieldValue"),new JLabel("orderBy")};
		for(int i=0;i<recherche.length;i++){
			recherche[i]=new JTextField(name[i].getText());
			//recherche[i].setMinimumSize(new Dimension(100,20));
			rech.add(name[i]);
			rech.add(recherche[i]);
		}
		cb.setSelected(true);
		rech.add(cb);
		rech.add(bouton);
		bouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent a) {
				if(bouton.equals(a.getSource())){
					String s[]=new String[3];
					for(int i=0;i<s.length;i++)
						s[i]=recherche[i].getText();
					printResult((ArrayList<Entree>)Main.getDictionnaire().getSortedList(s[2],cb.isSelected(), s[0], s[1]));
				}
			}
		});
		getContentPane().add(rech,BorderLayout.NORTH);
		
		
		//addActionListener(this);
		
		setVisible(true);
	}
	
	public void printResult(ArrayList<Entree> es){
		
		JPanel fenPan=new JPanel();
		fenPan.setLayout(new BorderLayout());
		fenPan.add(rech,BorderLayout.NORTH);
		
		cont=new JPanel();
		cont.setLayout(new GridLayout(es.size()/2+1,2,10,10));
		
		for(Entree e: es)
			cont.add(new EntreePanel(e));
		JScrollPane sc=new JScrollPane(cont);
		sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		fenPan.add(sc,BorderLayout.CENTER);
		setContentPane(fenPan);
		invalidate();
		validate();
		repaint();
	}
}
