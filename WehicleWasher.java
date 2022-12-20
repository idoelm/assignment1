//Ido Elmakies 316476340
package assignment1;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class WehicleWasher 
{
	private Wehicle[] NowInWash;
	private LinkedList<Car> ListCar;
	private LinkedList<SUV> ListSUV;
	private LinkedList<Truck> ListTruck;
	private LinkedList<MiniBus> ListMiniBus;
	private Stack<Wehicle> waiting;
	boolean WashingOn = false;
	private Formatter file;
	double 𝜆;

	
	public WehicleWasher(int StationsWash) throws FileNotFoundException
	{
		this.NowInWash = new Wehicle[StationsWash];
		this.waiting = new Stack<Wehicle>();
		this.ListCar = new LinkedList<Car>();
		this.ListSUV = new LinkedList<SUV>();
		this.ListTruck = new LinkedList<Truck>();
		this.ListMiniBus = new LinkedList<MiniBus>();
		this.file = new Formatter("log.txt");
		//this.𝜆 = My𝜆;
	}
	
	public Object CheckFreePositions()
	{
		for(int i = 0 ; i < this.NowInWash.length ; i++)
		{
			if(this.NowInWash[i] == null)
			{
				return i;
			}
		}
		return null;
	}
	
	public synchronized int searchcar(int MyID)
	{
		for(int i = 0 ; i < this.NowInWash.length ; i++)
		{
			if(this.NowInWash[i].GetId() == MyID)
			{
				return i;
			}
		}
		return -1;
	}
	
	public synchronized void waiting(Wehicle w)
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {}
		synchronized(file)
		{
		waiting.push(w);
		file.format("%s", "Hello, " + w +" has arrived\n");
		System.out.println("Hello, " + w +" has arrived");
		}
		
	}
	
	public void washing(Wehicle w)
	{
		
		synchronized(w) 
		{
			while(CheckFreePositions() == null)
			{
				try {
					synchronized(file)
					{
						file.format("%s", "Oops " + w + " doesn't have a position available.\n");
						System.out.println("Oops " + w + " doesn't have a position available.");
					w.wait();
					}
					
				
				} catch (InterruptedException e) {}
			}
	
				this.waiting.peek().SetIndex((int) CheckFreePositions());
				synchronized(file)
				{
					file.format("%s", this.waiting.peek() + " I am washing and I found in " + this.waiting.peek().GetIndex() + " position\n");
				System.out.println(this.waiting.peek() + " I am washing and I found in " + this.waiting.peek().GetIndex() + " position");
				}
				this.NowInWash[this.waiting.peek().GetIndex()] = this.waiting.pop();
				try 
				{
					Thread.sleep(3000);
				} 
				catch (InterruptedException e){}
				synchronized(file)
				{
					file.format("%s", "Ok " + w + " finished and left position " + w.GetIndex() + "\n");
					System.out.println("Ok " + w + " finished and left position " + w.GetIndex());

				}
				w.setWashed(true);	
				this.NowInWash[w.GetIndex()] = null;
		}
	}
	
	public synchronized void addToList(Wehicle MyW)
	{
		/*
		while(!MyW.GetWashed())
		{
			try {
				System.out.println("Oops " + MyW + " dosn't washed.");
				MyW.wait();
				
			} catch (InterruptedException e) {}
		}
		*/
		if(MyW instanceof Car)
		{
			System.out.println(MyW + "I added to ListCar");
			ListCar.add((Car) MyW);
		}
		else if(MyW instanceof SUV)
		{
			System.out.println(MyW + "I added to ListSUV");
			ListSUV.add((SUV) MyW);
		}
		else if(MyW instanceof Truck)
		{
			System.out.println(MyW + "I added to ListTruck");
			ListTruck.add((Truck) MyW);
		}
		else if(MyW instanceof MiniBus)
		{
			System.out.println(MyW + "I added to ListMiniBus");
			ListMiniBus.add((MiniBus) MyW);
		}

		notifyAll();
	}
	
	public String toString()
	{
		while(!this.waiting.isEmpty())
		{
			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			System.out.println(this.waiting.pop());
		}
		return "finish";
	}
	
	public void close()
	{
		this.file.close();
	}
}
