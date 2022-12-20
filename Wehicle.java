//Ido Elmakies 316476340
package assignment1;

public abstract class Wehicle 
{
	protected String name;
	protected int ID;
	protected int index;
	protected boolean washed = false;
	protected String kind;
	
	public Wehicle() 
	{
		
	}
	
	public Wehicle(int id , String Mykind)
	{
		this.ID = id;
		this.kind = Mykind;
	}
	
	public String toString()
	{
		return(this.ID + " " + this.kind);
	}
	
	public void print()
	{
		System.out.println(this.name + " " + this.ID);
	}
	
	public int GetId()
	{
		return this.ID;
	}
	
	public int GetIndex()
	{
		return this.index;
	}
	public void SetIndex(int MyIndex)
	{
		 this.index = MyIndex;
	}
	
	public void setWashed(boolean status)
	{
		this.washed = status;
	}
	
	public boolean GetWashed()
	{
		return this.washed;
	}
	
	
}
