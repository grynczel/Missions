/*
 * Class that represents an object of type Journal 
 * @author : Thomas Bollen
 */

public class Journal {

	String name=""; //name of the journal
	String rank=""; // rank of the journal
	Integer foR1=null;
	String foR1Name="";
	Integer foR2=null;
	String foR2Name="";
	Integer foR3=null;
	String foR3Name="";
	
	public Journal(String name, String rank, int foR1, String foR1Name,int foR2, String foR2Name,int foR3, String foR3Name) {
		this.setName(name);
		this.setRank(rank);
		this.setfoR1(foR1);
		this.setfoR1Name(foR1Name);
		this.setfoR2(foR2);
		this.setfoR2Name(foR2Name);
		this.setfoR3(foR3);
		this.setfoR3Name(foR3Name);
	}
	
	public Journal(String name, String rank) {
		this.name = name;
		this.rank = rank;
	}
	
	public Journal() {

	}
	
	/*
	 * Constructor that creates a Journal with the values contained in values
	 * If the length of values is not equals to 8, just fills the fields that are contained in values
	 */
	
	public Journal(String[] values){
		
		if(values.length>=1){
			this.rank=values[0];
		}
		if(values.length>=2){
			this.name=values[1];
		}
		if(values.length>=3){
			
			try{
				this.foR1=Integer.parseInt(values[2]);
			}catch (NumberFormatException e){
				//Not an integer, foR1 not set
			}
		
		}
		if(values.length>=4){
			this.foR1Name = values[3];
		}
		if(values.length>=5){
			
			try{
				this.foR2=Integer.parseInt(values[4]);
			}catch (NumberFormatException e){
				//Not an integer, foR2 not set
			}
			
		}
		if(values.length>=6){
			this.foR2Name = values[5];
		}
		if(values.length>=7){
			
			try{
				this.foR3=Integer.parseInt(values[6]);
			}catch (NumberFormatException e){
				//Not an integer, foR3 not set
			}
			
		}
		if(values.length==8){
			this.foR3Name = values[7];
		}
	}
	
	public String toString(){
		return (this.getName()+" : "+this.getRank());
	}
	
	/*
	 * Function that returns a String representing the object Journal with all the details
	 */
	
	public String toStringFull(){
		
		String result = this.getName();
		result = result + ", " + this.getRank();
		
		if(this.getfoR1()!=null){
			result = result + ", " + this.getfoR1();
		}
		if(!this.getfoR1Name().equals("")){
			result = result + ", " + this.getfoR1Name();
		}
		if(this.getfoR2()!=null){
			result = result + ", " + this.getfoR2();
		}
		if(!this.getfoR2Name().equals("")){
			result = result + ", " + this.getfoR2Name();
		}
		if(this.getfoR3()!=null){
			result = result + ", " + this.getfoR3();
		}
		System.out.println(this.foR3Name);
		if(!this.getfoR3Name().equals("")){
			result = result + ", " + this.getfoR3Name();
		}
		
		return result;
	}
	
	/*
	 * Function that sets the name of the Journal
	 */
	
	public void setName(String name){
		this.name = name;
	}
	
	/*
	 * Function that sets the rank of the Journal
	 */
	
	public void setRank(String rank){
		this.rank = rank;
	}
	
	/*
	 * Function that sets the foR1 of the Journal
	 */
	
	public void setfoR1(int foR1){
		this.foR1 = new Integer(foR1);
	}
	
	/*
	 * Function that sets the foR1Name of the Journal
	 */
	
	public void setfoR1Name(String foR1Name){
		this.foR1Name = foR1Name;
	}
	
	/*
	 * Function that sets the foR2 of the Journal
	 */
	
	public void setfoR2(int foR2){
		this.foR2 = new Integer(foR2);
	}
	
	/*
	 * Function that sets the foR2Name of the Journal
	 */
	
	public void setfoR2Name(String foR2Name){
		this.foR2Name = foR2Name;
	}
	
	/*
	 * Function that sets the foR3 of the Journal
	 */
	
	public void setfoR3(int foR3){
		this.foR3 = new Integer(foR3);
	}
	
	/*
	 * Function that sets the foR3Name of the Journal
	 */
	
	public void setfoR3Name(String foR3Name){
		this.foR3Name = foR3Name;
	}
	
	/*
	 * Function that returns the journal's name
	 */
	
	public String getName(){
		return this.name;
	}
	
	/*
	 * Function that returns the journal's rank
	 */
	
	public String getRank(){
		return this.rank;
	}
	
	/*
	 * Function that returns the journal's foR1
	 */
	
	public Integer getfoR1(){
		return this.foR1;
	}
	
	/*
	 * Function that returns the journal's foR1Name
	 */
	
	public String getfoR1Name(){
		return this.foR1Name;
	}
	
	/*
	 * Function that returns the journal's foR2
	 */
	
	public Integer getfoR2(){
		return this.foR2;
	}
	
	/*
	 * Function that returns the journal's foR2Name
	 */
	
	public String getfoR2Name(){
		return this.foR2Name;
	}
	
	/*
	 * Function that returns the journal's foR3
	 */
	
	public Integer getfoR3(){
		return this.foR3;
	}
	
	/*
	 * Function that returns the journal's foR3Name
	 */
	
	public String getfoR3Name(){
		return this.foR3Name;
	}
	

}
