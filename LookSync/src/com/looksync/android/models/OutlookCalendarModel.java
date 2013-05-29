package com.looksync.android.models;

//Modele pour le calendrier
//Inspire de la structure de calendrier Outlook, donc devrait fonctionner pour une synchronisation Outlook...

public class OutlookCalendarModel {
	@Override
	public String toString() {
		return "CalendrierOutlookModel [sujet=" + sujet + ", message="
				+ message + ", lieu=" + lieu + ", debut=" + debut + ", fin="
				+ fin + ", rappel=" + rappel + ", rappelAvantDebut="
				+ rappelAvantDebut + ", son=" + son + ", importance="
				+ importance + ", statut=" + statut + "]";
	}


	private String sujet;

    public String getSujet() {
		return sujet;
	}


	public void setSujet(String sujet) {
		this.sujet = sujet;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getLieu() {
		return lieu;
	}


	public void setLieu(String lieu) {
		this.lieu = lieu;
	}


	public String getDebut() {
		return debut;
	}


	public void setDebut(String debut) {
		this.debut = debut;
	}


	public String getFin() {
		return fin;
	}


	public void setFin(String fin) {
		this.fin = fin;
	}


	public Boolean getRappel() {
		return rappel;
	}


	public void setRappel(Boolean rappel) {
		this.rappel = rappel;
	}


	public Integer getRappelAvantDebut() {
		return rappelAvantDebut;
	}


	public void setRappelAvantDebut(Integer rappelAvantDebut) {
		this.rappelAvantDebut = rappelAvantDebut;
	}


	public Boolean getSon() {
		return son;
	}


	public void setSon(Boolean son) {
		this.son = son;
	}


	public String getImportance() {
		return importance;
	}


	public void setImportance(String importance) {
		this.importance = importance;
	}


	public String getStatut() {
		return statut;
	}


	public void setStatut(String statut) {
		this.statut = statut;
	}


	private String message;

    private String lieu;

    private String debut;

    private String fin;

    private Boolean rappel;

    private Integer rappelAvantDebut;

    private Boolean son;

    private String importance;

    private String statut;


    public void lireFichierPlat(String filename) 
    {
        
    }
}
