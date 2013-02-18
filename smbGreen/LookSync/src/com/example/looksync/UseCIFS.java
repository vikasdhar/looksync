package com.example.looksync;

import java.net.MalformedURLException;

import android.util.Log;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

public class UseCIFS {
	/*
	 * Cette classe permet de réaliser les actions liées au protocole SAMBA/CIFS
	 * i.e. le partage réseau...
	 */
	
	//On a besoin d'une adresse IP, d'un nom d'utilisateur et d'un mot de passe
	private String ip;
	private String user;
	private String mdp;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	//user test: Afabulations
	//mot de passe test: afabuler
	//lien donné par Windows: TestPartageDossier (file://MATHERN-PC/Users/Mathern/Desktop/Projets/android/projets_android/TestPartageDossier)

	/*
	 * Exemple...
	      url = "smb://" + ipaddress + "/";
		  NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, user, pass);
		  SmbFile dir = null;
		  try {
		   dir = new SmbFile(url, auth);
		  } catch (MalformedURLException e) {
		   e.printStackTrace();
		   Log.i("MyHome", "Authenication problem");
		  }
	 */
	
	//ca y est, les problemes commencent
	//comment se connecter à un réseau sans passer par wifi, i.e. en passant par le câble USB
	
	//bon, on peut faire communiquer le PC et le Android par cable USB... (good news)
	//Lien: http://developer.android.com/guide/topics/connectivity/usb/index.html
	//sauf que cela ne nous concerne pas dans ce cas (le mode accessoire aurait pu être intéressant, mais non...)
	public void connectSMB(){
		String url = "smb://" + ip + "/";
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, user, mdp);
		SmbFile dir = null;
		//tentative de connexion
		try {
		    dir = new SmbFile(url, auth);
		    Log.i("SMB_Authentification", "Authentification OK - ip:"+ ip +", user: "+user+", mdp: "+mdp);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			Log.i("SMB_Authentification", "Authentification KO!!!");
		}
	}
}
	
