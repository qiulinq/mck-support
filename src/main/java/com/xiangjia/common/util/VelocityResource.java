package com.xiangjia.common.util;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Scope("singleton")
@Component("VelocityResource")
public class VelocityResource {

	private static VelocityEngine velocityEngine;

	private static String vpath = "com/xiangjia/common/template/";

	private static Map<String, Template> tmap = new HashMap<String, Template>();
	static {
		init();
	}

	@PostConstruct
	public static void init() {
		velocityEngine = new VelocityEngine();
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("input.encoding", "UTF-8");
		velocityEngine.setProperty("output.encoding", "UTF-8");
		velocityEngine.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		velocityEngine.init();
	}

	public static String getHtmlRender(String templateName, VelocityContext context) {

		Template t = null;
		if (tmap.containsKey(templateName)) {
			t = tmap.get(templateName);
		} else {
			t = velocityEngine.getTemplate(vpath + templateName);
			tmap.put(templateName, t);
		}

		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		return writer.toString();
	}

	public static void main(String[] args) {
		init();
	}
}
