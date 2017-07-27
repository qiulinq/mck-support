package com.xiangjia.common.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import com.xiangjia.base.dto.ResultDtoModel;

/**
 * @author qiulinq 短信发送工具类
 */
public class SmmsUtil {
	// 短信配置文件名称
	// private static final String SMMS_FILE_NAME =
	// "com/xiangjia/config/properties/smms.properties";
	// 默认签名
	private static final String SMS_FREE_SIGN_NAME = "木木";

	private static final Logger log = LoggerFactory.getLogger(SmmsUtil.class);

	private static final String SMMS_URL = "http://gw.api.taobao.com/router/rest";

	private static final String APP_KEY = "23372724";

	private static final String SECRET = "8bbbeea64fc45b646e8365026d686c33";

	/**
	 * @param templateId
	 *            短信模板ID
	 * @param paramsMap
	 *            短信参数内容
	 * @param userTels
	 *            短信接收者的手机号码 多个手机号码用英文逗号分隔 单次最多200个手机号码
	 * @param smsFreeSignName
	 *            短信签名
	 * @return
	 */
	public static ResultDtoModel sendMsg(String templateId, Map<String, Object> paramsMap, String userTels, String smsFreeSignName) {
		// 读取短信发送的URL
		// String url = PropertiesUtil.readProperties("url", SMMS_FILE_NAME);
		// 读取短信发送的KEY
		// String appkey = PropertiesUtil.readProperties("appkey",
		// SMMS_FILE_NAME);
		// 读取短信发送的secret
		// String secret = PropertiesUtil.readProperties("secret",
		// SMMS_FILE_NAME);

		TaobaoClient client = new DefaultTaobaoClient(SMMS_URL, APP_KEY, SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");// 公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
		req.setSmsType("normal");// 短信类型，传入值请填写normal
		// 设置短信签名
		if (smsFreeSignName == null) {
			req.setSmsFreeSignName(SMS_FREE_SIGN_NAME);
		} else {
			req.setSmsFreeSignName(smsFreeSignName);
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			req.setSmsParamString(mapper.writeValueAsString(paramsMap));// 短信参数
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		req.setRecNum(userTels);// 设置短信接收人的手机号码
		req.setSmsTemplateCode(templateId);// 设置短信模板ID
		AlibabaAliqinFcSmsNumSendResponse rsp;
		ResultDtoModel res = null;
		try {
			rsp = client.execute(req);
			log.debug(rsp.getBody());
			res = formatterMsgResult(rsp.getBody());
		} catch (ApiException e) {
			res = new ResultDtoModel(false, "短信发送失败[" + e.getMessage() + "]");
		}
		log.info("短信发送结果：" + res);
		return res;
	}

	public static ResultDtoModel sendMsgWithDefaultSign(String templateId, Map<String, Object> paramsMap, String userTels) {
		return sendMsg(templateId, paramsMap, userTels, null);
	}

	/**
	 * 将短信返回结果格式化为ResultPojo
	 * 
	 * @param result
	 *            短信发送返回结果
	 * @return 格式化后的返回值
	 */
	@SuppressWarnings({ "unchecked" })
	private static ResultDtoModel formatterMsgResult(String result) {
		Map<String, Object> resultMap = JSON.parseObject(result, Map.class);
		if (resultMap.containsKey("error_response")) {// 异常返回
			Map<String, Object> errMap = (Map<String, Object>) resultMap.get("error_response");
			ResultDtoModel res = new ResultDtoModel(false, errMap.get("sub_code") + "," + errMap.get("sub_msg").toString());
			res.setCode(errMap.get("sub_code").toString());
			return res;
		} else if (resultMap.containsKey("alibaba_aliqin_fc_sms_num_send_response")) {// 正常返回
			Map<String, Object> map = (Map<String, Object>) resultMap.get("alibaba_aliqin_fc_sms_num_send_response");
			map = (Map<String, Object>) map.get("result");
			if ("true".equals(map.get("success") + "")) {
				if (!map.containsKey("msg")) {
					map.put("msg", "短信发送成功");
				}
				ResultDtoModel res = new ResultDtoModel(true, map.get("err_code").toString() + ":" + map.get("msg").toString());
				res.setCode(map.get("err_code").toString());
				return res;
			} else {
				if (!map.containsKey("msg")) {
					map.put("msg", "短信发送失败");
				}
				ResultDtoModel res = new ResultDtoModel(false, map.get("err_code").toString() + ":" + map.get("msg").toString());
				res.setCode(map.get("err_code").toString());
				return res;
			}
		} else {
			return new ResultDtoModel(false, "-2222", "未知返回结果");
		}
	}

	public static void main(String[] args) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("name", "邱先生");
		paramsMap.put("projectName", "测试项目");

		System.out.println(SmmsUtil.sendMsgWithDefaultSign("SMS_16751161", paramsMap, "18324146686"));
	}
}
