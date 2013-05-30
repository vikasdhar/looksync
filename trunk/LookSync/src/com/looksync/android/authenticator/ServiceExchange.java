package com.looksync.android.authenticator;

import com.independentsoft.exchange.Service;

public class ServiceExchange {

	private Service service;
	
	public ServiceExchange() {
	}	
	
	public Service getService() {
		return service;
	}

	/*public void setService(Service serviceAuthentification) {
		this.serviceAuthentification = serviceAuthentification;
	}*/
	public void setService(String serveur, String nomUtilisateur, String motDePasse) {
		//Service service = new Service("https://myserver/ews/Exchange.asmx", "username", "password");
    	//Service service = new Service("https://213.245.163.98/ews/Exchange.asmx", "LOOKSYNC\\Administrateur", "Utilisatéur428"); => faux
        //Service service = new Service("https://213.245.163.98/ews/Exchange.asmx", "Administrateur", "Utilisatéur428");
        //ou : Service service = new Service("https://213.245.163.98/ews/Exchange.asmx", "Administrateur", "Utilisatéur428", "LOOKSYNC");
        service = new Service("https://" + serveur + "/ews/Exchange.asmx", nomUtilisateur, motDePasse);
	}
	
}
