package com.gaoshuhang.captcha;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码生成工厂方法
 *
 * @author CiyaZ
 */
public class CaptchaFactory
{
	public static int CHAR_WIDTH_UNIT = 48;
	public static int CHAR_HEIGHT_UNIT = 64;

	private static Random random = new Random();

	public static int BACKGROUND_TRI_ALPHA = 60;
	public static int BACKGROUND_TRI_NUM = 20;
	public static int BACKGROUND_TRI_WIDTH = 30;

	public static int CHAR_ALPHA = 150;

	public static int DECORATE_SNOW_NUM = 3000;

	/**
	 * 自动生成随机的验证码图片
	 *
	 * @param charNum   验证码字符数
	 * @param charArray 可选字符
	 * @param xScale    在生成的原图基础上的X轴缩放
	 * @param yScale    在生成的原图基础上的Y轴缩放
	 * @return 生成的验证码
	 */
	public static Captcha createRandom(int charNum, char[] charArray, float xScale, float yScale)
	{
		//生成随机字符串
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < charNum; i++)
		{
			int randInt = random.nextInt(charArray.length);
			stringBuilder.append(charArray[randInt]);
		}
		String randString = stringBuilder.toString();

		return create(randString, xScale, yScale);
	}

	/**
	 * 根据参数字符串生成一个验证码图片
	 *
	 * @param str    参数字符串
	 * @param xScale 在生成的原图基础上的X轴缩放
	 * @param yScale 在生成的原图基础上的Y轴缩放
	 * @return 生成的验证码
	 */
	public static Captcha create(String str, float xScale, float yScale)
	{
		//原始图片长宽
		int charNum = str.length();
		int width = charNum * CHAR_WIDTH_UNIT;
		int height = CHAR_HEIGHT_UNIT;

		//生成图片对象
		BufferedImage captcha = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) captcha.getGraphics();

		//captcha生成管线
		drawBackground(g2d, captcha);
		drawString(g2d, str, captcha);
		drawDecorator(g2d, captcha);

		//整体缩放
		captcha = ImageUtil.scale(xScale, yScale, captcha);

		//释放资源
		g2d.dispose();
		return new Captcha(str, captcha);
	}

	private static void drawBackground(Graphics2D g2d, BufferedImage captcha)
	{
		int drawTimes = (captcha.getWidth() / CHAR_WIDTH_UNIT) * BACKGROUND_TRI_NUM;

		//背景底色层
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, captcha.getWidth(), captcha.getHeight());
		//背景层
		for (int i = 0; i < drawTimes; i++)
		{
			//随机颜色坐标画小三角
			g2d.setColor(ColorUtil.createRandRGBColor(BACKGROUND_TRI_ALPHA));
			int triX = random.nextInt(captcha.getWidth());
			int triY = random.nextInt(captcha.getHeight());
			DrawUtil.fillTri(triX, triY, BACKGROUND_TRI_WIDTH, g2d);
		}
	}

	private static void drawString(Graphics2D g2d, String str, BufferedImage captcha)
	{
		for (int i = 0; i < str.length(); i++)
		{
			g2d.setColor(ColorUtil.createRandRGBColor(CHAR_ALPHA));
			DrawUtil.fillChar(CHAR_WIDTH_UNIT * i, 0, str.charAt(i), g2d);
		}
	}

	private static void drawDecorator(Graphics2D g2d, BufferedImage captcha)
	{
		for (int i = 0; i < DECORATE_SNOW_NUM; i++)
		{
			DrawUtil.fillNoisePoint(captcha, g2d);
		}
	}
}
