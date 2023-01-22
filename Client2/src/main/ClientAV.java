package main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientAV {
	
	public static void main(String[] args)throws MalformedURLException, RemoteException, NotBoundException {
		
		// TODO Auto-generated method stub
		RemoteMethods stub=(RemoteMethods) Naming.lookup("rmi://localhost:1126/RMI");
		Point[] pts=stub.listA();
		String name="";
		int Capacite;
		Etat etat;
		int vitesse;
		Point position;
		Scanner sc=new Scanner(System.in);
		System.out.println("choisir dans quel aeroport voulez vous commancer");
		for(int i=1;i<=pts.length;i++) {
			System.out.println("aeroport "+i+" press:"+i);
		}
		int Ar=-1;
		while(Ar<0||Ar>=pts.length) {
			sc=new Scanner(System.in);
			Ar=sc.nextInt()-1;
		}
		
		Capacite=5000;
		int restant=Capacite;
		etat=Etat.Idel;
		vitesse=5;
		position=new Point(pts[Ar].x,pts[Ar].y);
		Ar=0;
		while(Ar==0) {
			System.out.println("donner un nom a votre avion");
			 sc=new Scanner(System.in);
			name=sc.nextLine();
			Ar=stub.newAvion(name,Capacite,etat,vitesse,position);
			if(Ar==0) {
				System.out.println("Avion existe déja veuillez choisir un autre nom");
			}
		}
		
		
		
		while(!etat.equals(Etat.Broken)) {
			
		if(etat.equals(Etat.Idel)) {
			
		
		do {
			etat=stub.waiting(name);
			
		}while(!etat.equals(Etat.Standby));
		}
		
		do {
			etat=stub.waiting(name);
			
			
		}while(!etat.equals(Etat.Active));
		
		
		float[]h=stub.request(name,position);

		float a=h[0];
		float b=h[1];
		float o=h[2];
		float i=h[3];
		
		int distance;
		int time;
		
		int newX;
		int newY;
		
		
		try {
			
			do {
				
				if(a!=0||b!=0){
		  newX= (int) (position.x+o);
			newY=(int)(a*newX+b);
			
				}else {
					newX=position.x;
					newY=(int) (position.y+o);
				}
			
			
			distance=(int) ( Math.sqrt(Math.pow((newX-position.x),2)+Math.pow((newY-position.y),2)));
			
			time=(distance * 1000)/vitesse;
		
			Thread.sleep(time);
			
			position.x=newX;
			position.y=newY;
			
			restant=restant-20;
			int et=stub.update(position, name, (int)h[3],restant);
			if(et==1) {
				etat=Etat.Idel;
			}else {
				if(et==2) {
					etat=Etat.Standby;
					restant=stub.getR(name);
				}
				if(et==3) {
					etat=Etat.Broken;
					
					restant=stub.getR(name);
				}
			}
			
			}while(etat.equals(Etat.Active));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}}
	/*public void start(float a,float b,int o,int index) {
		int distance;
		int time;
		
		int newX;
		int newY;
		
		etat=Etat.Active;
		try {
			
			do {
				
				if(a!=0||b!=0){
		  newX= (position.x+o);
			newY=(int)(a*newX+b);
			
				}else {
					newX=position.x;
					newY=(position.y+o);
				}
			
			
			distance=(int) ( Math.sqrt(Math.pow((newX-position.x),2)+Math.pow((newY-position.y),2)));
			
			time=(distance * 1000)/this.vitesse;
		
			Thread.sleep(time);
			
			this.position.x=newX;
			this.position.y=newY;
			
			this.Capacite=this.Capacite-20;
			MainR.update(this.position, name, index);
			
			}while(etat.equals(Etat.Active));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
