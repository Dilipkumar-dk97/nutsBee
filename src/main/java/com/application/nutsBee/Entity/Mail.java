package com.application.nutsBee.Entity;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Mail {
	
	private String from;
	private String to;
	private String[] cc;
	private String bcc;
	private String message;
	private String subject;
	private String mailTemplate;
	private Map<String,Object> model;
	private List<Attachment> attachments;
}
