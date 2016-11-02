package com.muan.takeout.Utils;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSAUtils {
	/**公钥*/
    private static final String RSA_PUBLICE =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDrp50m+cs9W9pkfMT1c28OSeY2" + "\r" +
            "JI/hBxqQMFfj9JPJSNWgFJNecpOhKhQOYges9a0LXiSK4on10vSyj8lD/qhJ9Dyf" + "\r" +
            "QrlsOnoHAuJ58ELfxYXgVSNNhVgnjfHJXba2/WWlZd+6BUnjDjHe1I0ffl7S5qpV" + "\r" +
            "c6szCAqMiLe2fZP8xQIDAQAB";
	private static final String ALGORITHM = "RSA";

	/**
	 * 得到公钥对象
	 * @param algorithm
	 * @param bysKey
	 * @return
	 */
	private static PublicKey getPublicKeyFromX509(String algorithm,
			String bysKey) throws NoSuchAlgorithmException, Exception {
		byte[] decodedKey = Base64.decode(bysKey, Base64.DEFAULT);
		X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);

		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		return keyFactory.generatePublic(x509);
	}

	/**
	 * 使用公钥加密
	 * @param content
	 * @param key
	 * @return
	 */
	public static String encryptByPublic(String content) {
		try {
			PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, RSA_PUBLICE);
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, pubkey);
			byte plaintext[] = content.getBytes("UTF-8");
			byte[] output = cipher.doFinal(plaintext);
			String s = new String(Base64.encode(output, Base64.DEFAULT));
			return s;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	* 使用公钥解密
	* @param content 密文
	* @param key 商户私钥
	* @return 解密后的字符串
	*/
	public static String decryptByPublic(String content) {
		try {
			PublicKey pubkey = getPublicKeyFromX509(ALGORITHM, RSA_PUBLICE);
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.DECRYPT_MODE, pubkey);
			InputStream ins = new ByteArrayInputStream(Base64.decode(content,
					Base64.DEFAULT));
			ByteArrayOutputStream writer = new ByteArrayOutputStream();
			byte[] buf = new byte[128];
			int bufl;
			while ((bufl = ins.read(buf)) != -1) {
				byte[] block = null;
				if (buf.length == bufl) {
					block = buf;
				} else {
					block = new byte[bufl];
					for (int i = 0; i < bufl; i++) {
						block[i] = buf[i];
					}
				}
				writer.write(cipher.doFinal(block));
			}
			return new String(writer.toByteArray(), "utf-8");
		} catch (Exception e) {
			return null;
		}
	}

}
