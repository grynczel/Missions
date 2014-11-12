import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class EntreePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	JLabel lab[];
	int width=500,height=200;

	public EntreePanel(Entree e){
		setBackground(Color.lightGray);
		String[] cles=e.getCles();
		lab=new JLabel[cles.length*2];
		setLayout(new GridLayout(cles.length,2,5,5));
		for(int i=0;i<cles.length*2;i++){
			if(i%2==0){
				lab[i]=new JLabel(cles[i/2]);
				lab[i].setForeground(Color.gray);
			}
			else{
				lab[i]=new JLabel(e.get(cles[(i-1)/2]));
				lab[i].setForeground(Color.black);
			}
			add(lab[i]);
		}
		
	}
	public Dimension getPreferredSize(){
		return new Dimension(width,height);
	}
	public Dimension getMinimumSize(){
		return new Dimension(width-200,height);
	}
	public Dimension getMaximumSize(){
		return new Dimension(width,height);
	}
}
