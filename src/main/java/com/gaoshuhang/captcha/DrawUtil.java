package com.gaoshuhang.captcha;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 绘制相关操作封装工具类
 *
 * @author CiyaZ
 */
class DrawUtil
{
	private static Random random = new Random();

	/**
	 * 在背景上画小三角形干扰识别
	 *
	 * @param srcX     源顶点X坐标
	 * @param srcY     源顶点Y坐标
	 * @param triWidth 三角形边长
	 * @param g2d      绘制上下文
	 */
	static void fillTri(int srcX, int srcY, int triWidth, Graphics2D g2d)
	{
		//初始化正三角形三个点
		double point1x = srcX;
		double point1y = srcY;
		double[] point1 = {point1x, point1y};

		double point2x = srcX - ((double) triWidth) / 2;
		double point2y = srcY + Math.sqrt(3) / 2 * triWidth;
		double[] point2 = {point2x, point2y};

		double point3x = srcX + ((double) triWidth) / 2;
		double point3y = point2y;
		double[] point3 = {point3x, point3y};

		//旋转变换
		double centerx = srcX;
		double centery = srcY - Math.sqrt(3) / 3 * triWidth;
		int theta = random.nextInt(120);

		AffineTransform.getRotateInstance(Math.toRadians(theta), centerx, centery).transform(point1, 0, point1, 0, 1);
		AffineTransform.getRotateInstance(Math.toRadians(theta), centerx, centery).transform(point2, 0, point2, 0, 1);
		AffineTransform.getRotateInstance(Math.toRadians(theta), centerx, centery).transform(point3, 0, point3, 0, 1);

		//绘制
		g2d.fillPolygon(new int[]{
				(int) point1[0], (int) point2[0], (int) point3[0]
		}, new int[]{
				(int) point1[1], (int) point2[1], (int) point3[1]
		}, 3);
	}

	static void fillChar(int srcX, int srcY, char c, Graphics2D g2d)
	{
		// ???
		int fuck = 6;

		//坐标偏移
		int randXOffset = random.nextInt(fuck);
		int randYOffset = random.nextInt(fuck);

		//旋转缩放及绘制
		int randScale = random.nextInt(fuck);
		int fontSize = new Double((CaptchaFactory.CHAR_HEIGHT_UNIT + fuck) / 2).intValue() + randScale;
		AffineTransform orig = g2d.getTransform();
		g2d.translate(srcX + CaptchaFactory.CHAR_WIDTH_UNIT / 2, srcY + CaptchaFactory.CHAR_HEIGHT_UNIT / 2);
		int theta = random.nextInt(61) - 30;
		g2d.rotate(Math.toRadians(theta));
		g2d.setFont(new Font("Sans Serif", Font.BOLD, fontSize));
		g2d.drawString(String.valueOf(c), -randXOffset, randYOffset);
		g2d.setTransform(orig);
	}

	/**
	 * 随机画灰度噪声点
	 * @param captcha 图片
	 * @param g2d 绘图上下文
	 */
	static void fillNoisePoint(BufferedImage captcha, Graphics2D g2d)
	{
		int posX = random.nextInt(captcha.getWidth());
		int posY = random.nextInt(captcha.getHeight());
		g2d.setColor(ColorUtil.createRandGrayScaleColor(55, 200));
		g2d.fillOval(posX, posY, 2, 2);
	}
}
