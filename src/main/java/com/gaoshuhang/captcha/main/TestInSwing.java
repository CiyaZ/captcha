package com.gaoshuhang.captcha.main;

import com.gaoshuhang.captcha.CaptchaFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 生成一个用于测试的窗口，显示随机生成的验证码
 *
 * @author CiyaZ
 */
class TestInSwing
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() -> {
			JFrame jFrame = new JFrame("验证码生成测试");

			long startTime = System.currentTimeMillis();
			//调用验证码生成工具类
			BufferedImage captchaImage = CaptchaFactory.create("CAPTCHA", 1, 1);
			long endTime = System.currentTimeMillis();
			System.out.println("function CaptchaFactory.create() time cost: " + (endTime - startTime) + "ms");

			JLabel jLabel = new JLabel();
			if (captchaImage != null)
			{
				jLabel.setIcon(new ImageIcon(captchaImage));
			}
			jFrame.add(jLabel);
			jFrame.pack();

			jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			jFrame.setVisible(true);
		});
	}
}
