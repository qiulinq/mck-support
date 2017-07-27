package com.xiangjia.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.VelocityContext;
import org.springframework.util.StringUtils;

import com.xiangjia.base.dto.ResultDtoModel;

/**
 * @author qiulinq 邮件发送工具类
 */
public class MailUtil {
	// 设置是否邮件调试
	private static final boolean EMAIL_DEBUG = true;
	// 邮件发送的properties名称
	private static final String MAIL_PROPERTIES_NAME = "com/xiangjia/config/properties/mail";
	// 默认邮件主题 当邮件主题为空时采用当前值
	private static final String DEFAULT_SUBJECT = "系统邮件";

	/**
	 * @param toList
	 *            收件人列表(多个收件人用英文逗号隔开)
	 * @param subject
	 *            邮件主题
	 * @param Content
	 *            邮件内容
	 */
	public static ResultDtoModel sendMail(String toList, String subject, String Content) {
		String host = PropertiesUtil.readProperties("mail.smtp.host", MAIL_PROPERTIES_NAME);// 读取邮件host
		String from = PropertiesUtil.readProperties("sendUserEmail", MAIL_PROPERTIES_NAME);// 读取发件人的email
		String pwd = PropertiesUtil.readProperties("sendUserPassword", MAIL_PROPERTIES_NAME);// 读取发件人的邮箱密码
		Properties props = new Properties();
		// 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
		props.put("mail.smtp.host", host);
		// 需要经过授权，也就是有户名和密码的校验，这样才能通过验证
		props.put("mail.smtp.auth", "true");
		// 用刚刚设置好的props对象构建一个session
		Session session = Session.getDefaultInstance(props);

		// 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
		// 用（你可以在控制台（console)上看到发送邮件的过程）
		session.setDebug(EMAIL_DEBUG);
		// 用session为参数定义消息对象
		MimeMessage message = new MimeMessage(session);
		try {
			// 加载发件人地址
			message.setFrom(new InternetAddress(from));
			message.addRecipients(Message.RecipientType.TO, toList); // 收件人
			// message.addRecipients(Message.RecipientType.CC, ccList); // 抄送人
			// 加载标题
			if (StringUtils.isEmpty(subject)) {
				subject = DEFAULT_SUBJECT;
			}
			message.setSubject(subject);
			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			// 设置邮件的文本内容
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setText(Content);
			multipart.addBodyPart(contentPart);
			// 将multipart对象放到message中
			message.setContent(multipart);

			// 保存邮件
			message.saveChanges();

			// 发送邮件
			Transport transport = session.getTransport("smtp");

			// 连接服务器的邮箱
			transport.connect(host, from, pwd);
			// 把邮件发送出去
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			return new ResultDtoModel(false, "邮件发送失败[" + e.getMessage() + "]");
		}
		return new ResultDtoModel(true, "邮件发送成功");
	}
	
	public static ResultDtoModel sendMail(Map<String, Object> paramsMap,String velocityId,String toUser){
		//判断主题  若参数未设置主题则采用默认主题
		if(!paramsMap.containsKey("subject")){
			paramsMap.put("subject", DEFAULT_SUBJECT);
		}
		VelocityContext context = new VelocityContext();
		Iterator<String> it = paramsMap.keySet().iterator();
		String key = null;
		//将所有参数设置到context中 
		while(it.hasNext()){
			key = it.next();
			context.put(key, paramsMap.get(key));
		}
		//根据参数及模板ID获取邮件内容
		String content = VelocityResource.getHtmlRender(velocityId, context);
		//发送邮件
		return sendMail(toUser, paramsMap.get("subject").toString(), content);
	}
}
