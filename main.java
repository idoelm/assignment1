//Ido Elmakies 316476340

package assignment1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class main {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		int numOfvehicles;
		int StationsWash;
		double 𝜆;
		Scanner s = new Scanner(System.in);
		System.out.println("How many vehicles will arrive?");
		numOfvehicles = s.nextInt();
		System.out.println("How many wash stations?");
		StationsWash = s.nextInt();
		//System.out.println("what is 𝜆?");
	//	𝜆 = s.nextDouble();
		
		int random;
		WehicleWasher washer = new WehicleWasher(StationsWash);
		Wehicle[] vehicles = new Wehicle[numOfvehicles];
		for(int i = 0 ; i < numOfvehicles ; i++)
		{
			random = (int)Math.floor(Math.random()*(4-1+1)+1);
			switch (random) {
			case 1: 
			{
				vehicles[i] = new Car((int)Math.floor(Math.random()*(1000-1+1)+1));
				break;
			}
			case 2:
			{
				vehicles[i] = new SUV((int)Math.floor(Math.random()*(1000-1+1)+1));
				break;
			}
			case 3:
			{
				vehicles[i] = new Truck((int)Math.floor(Math.random()*(1000-1+1)+1));
				break;
			}
			case 4:
			{
				vehicles[i] = new MiniBus((int)Math.floor(Math.random()*(1000-1+1)+1));
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + random);
			}
		}
		
		ThreadW[] ThreadOfCar = new ThreadW[numOfvehicles];
		
		for(int i = 0 ; i < numOfvehicles ; i++)
		{
			ThreadOfCar[i] = new ThreadW(washer, vehicles[i]);
		}
		
		for(int i = 0 ; i < numOfvehicles ; i++)
		{
			ThreadOfCar[i].start();
		}
		
		for(int i = 0 ; i < numOfvehicles ; i++)
		{
			ThreadOfCar[i].join();
		}
		
		/*
		WehicleWasher MyW = new WehicleWasher();
		Car c = new Car("renault",(int)Math.floor(Math.random()*(1000-100+1)+100));
		SUV b = new SUV("bmw",(int)Math.floor(Math.random()*(1000-100+1)+100));
		Truck a = new Truck("audi",(int)Math.floor(Math.random()*(1000-100+1)+100));
		MiniBus d = new MiniBus("toyota",(int)Math.floor(Math.random()*(1000-100+1)+100));
		Car e = new Car("jili",(int)Math.floor(Math.random()*(1000-100+1)+100));
		a.print();b.print();c.print();d.print();e.print();
		System.out.println();

			ThreadW t1 = new ThreadW(MyW, a);
			ThreadW t2 = new ThreadW(MyW, b);
			ThreadW t3 = new ThreadW(MyW, c);
			ThreadW t4 = new ThreadW(MyW, d);
			ThreadW t5 = new ThreadW(MyW, e);
			t3.start();
			t2.start();
			t4.start();
			t5.start();
			t1.start();
			try
			{
				t3.join();
				t1.join();
				t2.join();
				t4.join();
				t5.join();
			} 
			catch (InterruptedException e1){}
			
			MyW.close();
			*/
		
			washer.close();
			System.out.println("finish");
		

	
		
		
	}

}
