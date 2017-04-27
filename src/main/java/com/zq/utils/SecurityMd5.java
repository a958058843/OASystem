package com.zq.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityMd5 {

	public static String md5(String content) {
		try {
			MessageDigest mdDigest = MessageDigest.getInstance("MD5");
			mdDigest.update(content.getBytes());
			byte[] b = mdDigest.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < b.length; i++) {
				String j = Integer.toHexString(b[i] & 0xff);
				if(j.length()==1){
					sb.append("0");
				}
				sb.append(j);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args){
		System.out.println(md5("123456"));
	}
}
