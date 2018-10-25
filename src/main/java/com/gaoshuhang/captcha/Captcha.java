package com.gaoshuhang.captcha;

import java.awt.image.BufferedImage;

/**
 * 验证码实体类
 *
 * @author CiyaZ
 */
public class Captcha
{
	private String str;
	private BufferedImage image;

	public Captcha(String str, BufferedImage image)
	{
		this.str = str;
		this.image = image;
	}

	public String getStr()
	{
		return str;
	}

	public BufferedImage getImage()
	{
		return image;
	}
}
