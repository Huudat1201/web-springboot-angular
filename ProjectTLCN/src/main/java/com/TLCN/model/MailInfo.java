package com.TLCN.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class chua thong tin gui mail
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
	String from;
	String to;
	String[] cc;
	String[] bcc;
	String subject;
	String body;
	String[] attachment;
	public MailInfo(String to, String subject, String body) {
		this.from = "Cửa hàng điện thoại DATHAU STORE<Huudat1201@gmail.com>";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
