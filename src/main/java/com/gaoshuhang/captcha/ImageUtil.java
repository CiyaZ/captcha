package com.gaoshuhang.captcha;

import java.awt.image.BufferedImage;

/**
 * 一些图片操作封装的工具类
 *
 * @author CiyaZ
 */
class ImageUtil
{
	/**
	 * 图片缩放
	 * @param xScale X轴缩放
	 * @param yScale Y轴缩放
	 * @param captcha 原始图片内存对象
	 * @return 缩放后的图片内存对象
	 */
	static BufferedImage scale(float xScale, float yScale, BufferedImage captcha)
	{
		//获取原始图像的宽度和高度
		int width = (int) (captcha.getWidth() * xScale);
		int height = (int) (captcha.getHeight() * yScale);

		//不能低于输出图片的下限大小1px*1px
		if (width <= 0)
		{
			width = 1;
		}
		if (height <= 0)
		{
			height = 1;
		}

		//生成新图片
		BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		resultImage.getGraphics().drawImage(captcha.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
		return resultImage;
	}
}
