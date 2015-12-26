package com.tyt.dto;

import org.springframework.data.annotation.Id;

public class Trail {

	@Id
	private String id;

	private String title;
	private String iframe;

	public Trail() {
	}

	public Trail(String title, String iframe) {
		this.title = title;
		this.iframe = iframe;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIframe() {
		return iframe;
	}

	public void setIframe(String iframe) {
		this.iframe = iframe;
	}

	@Override
	public String toString() {
		return String.format("Trail[id=%s, title='%s', iframe='%s']", id, title, iframe);
	}
}
