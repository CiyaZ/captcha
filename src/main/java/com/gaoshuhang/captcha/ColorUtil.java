package com.gaoshuhang.captcha;

import java.awt.*;
import java.util.Random;

/**
 * 颜色相关操作封装的工具类
 *
 * @author CiyaZ
 */
class ColorUtil
{

	private static Random random = new Random();

	/**
	 * 生成随机颜色
	 *
	 * @return 随机颜色对象
	 */
	static Color createRandColor()
	{
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		return new Color(r, g, b);
	}

	/**
	 * 生成随机红黄绿颜色
	 *
	 * @return 随机颜色对象
	 */
	static Color createRandRGBColor()
	{
		int choice = random.nextInt(3);
		switch (choice)
		{
			case 0:
				return new Color(255, 0, 0);
			case 1:
				return new Color(0, 255, 0);
			case 2:
				return new Color(0, 0, 255);
			default:
				return null;
		}
	}

	/**
	 * 生成随机红黄绿颜色
	 *
	 * @param alpha 透明度
	 * @return 随机颜色对象
	 */
	static Color createRandRGBColor(int alpha)
	{
		int choice = random.nextInt(3);
		switch (choice)
		{
			case 0:
				return new Color(255, 0, 0, alpha);
			case 1:
				return new Color(0, 255, 0, alpha);
			case 2:
				return new Color(0, 0, 255, alpha);
			default:
				return null;
		}
	}

	/**
	 * 生成随机灰度噪点
	 *
	 * @return 随机颜色对象
	 */
	static Color createRandGrayScaleColor(int grayScaleOffset, int grayScaleRange)
	{
		int grayScale = random.nextInt(grayScaleRange) + grayScaleOffset;
		return new Color(grayScale, grayScale, grayScale);
	}
}
