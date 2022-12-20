//Ido Elmakies 316476340
package assignment1;

public class ThreadW extends Thread 
{
	private WehicleWasher a;
	private Wehicle w;
	
	public ThreadW(WehicleWasher ma,Wehicle wm)
	{
		this.a = ma;
		this.w = wm;
	}
	
	public void run()
	{

		this.a.waiting(w);
		this.a.washing(w);
		this.a.addToList(w);

	}
}
