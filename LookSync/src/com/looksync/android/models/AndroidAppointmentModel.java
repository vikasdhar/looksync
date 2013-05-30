package com.looksync.android.models;

public class AndroidAppointmentModel {
	public AndroidAppointmentModel() {
		super();
	}

	/*
	 Cf. classe MainCalendar.java...
	 String l_str = "title: calendar test\n" +
    		"description: This is a simple test for calendar api\n" + 
    		"eventLocation: @home\n" + 
    		"start time:" + getDateTimeStr(0) + "\n" + 
    		"end time: " + getDateTimeStr(30) + "\n" + 
    		"event status: confirmed\n" + 
    		"all day: no\n" + 
    		"has alarm: yes\n";
	 */
	
	public AndroidAppointmentModel(String description, String titre,
			String debut, String fin, String statut, String allDay,
			Boolean son, String alarm, String calendarId, String visibility,
			String hasAlarm) {
		super();
		this.description = description;
		this.titre = titre;
		this.debut = debut;
		this.fin = fin;
		this.statut = statut;
		this.allDay = allDay;
		this.son = son;
		this.alarm = alarm;
		this.calendarId = calendarId;
		this.visibility = visibility;
		this.hasAlarm = hasAlarm;
	}
	
	@Override
	public String toString() {
		return "CalendrierAndroidModel [description=" + description
				+ ", titre=" + titre + ", debut=" + debut + ", fin=" + fin
				+ ", statut=" + statut + ", allDay=" + allDay + ", son=" + son
				+ ", alarm=" + alarm + ", calendarId=" + calendarId
				+ ", visibility=" + visibility + ", hasAlarm=" + hasAlarm + "]";
	}
	
	private String description;

	private String titre;

    private String debut;

    private String fin;

    private String statut;

    private String allDay;

    private Boolean son;

    private String alarm;
    
    private String calendarId; //l'event est rattaché à un calendrier donné... 
    
    private String visibility;
    
    private String hasAlarm;

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
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

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getAllDay() {
		return allDay;
	}

	public void setAllDay(String allDay) {
		this.allDay = allDay;
	}

	public Boolean getSon() {
		return son;
	}

	public void setSon(Boolean son) {
		this.son = son;
	}

	public String getAlarm() {
		return alarm;
	}

	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getHasAlarm() {
		return hasAlarm;
	}

	public void setHasAlarm(String hasAlarm) {
		this.hasAlarm = hasAlarm;
	}
}
