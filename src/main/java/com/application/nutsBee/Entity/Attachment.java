package com.application.nutsBee.Entity;

import lombok.Data;

@Data
public class Attachment {
	
	private String name;
	private String contentType;
	private Byte[] content;
}
