package com.java.bean;

public class Event {
	
	private String eventId;
	private String title;
	private String titleMessage;
	private String background;
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleMessage() {
		return titleMessage;
	}
	public void setTitleMessage(String titleMessage) {
		this.titleMessage = titleMessage;
	}
	
	
	public String getBackground() {
		return "url('<%=request.getContextPath()%>/img/"+background+"')";
	}
	public void setBackground(String background) {
		this.background = background;
	}
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", title=" + title + ", titleMessage=" + titleMessage + "]";
	}
	

}
