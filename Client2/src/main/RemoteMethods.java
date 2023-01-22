package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteMethods extends Remote {
public Point[] listA()throws RemoteException;
public int newAvion(String name,int capacite,Etat etat,int vitesse,Point position)throws RemoteException;
public Etat waiting(String name)throws RemoteException;
public float[] request(String name,Point position)throws RemoteException;
public int update(Point position,String name,int i,int capacite)throws RemoteException;
public int getR(String name)throws RemoteException;
}
