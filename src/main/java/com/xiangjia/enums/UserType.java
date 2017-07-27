package com.xiangjia.enums;

public enum UserType {
	VISITOR {
		public String getName() {
			return "游客";
		}
	},
	REAL {
		public String getName() {
			return "真实用户";
		}
	};
	public abstract String getName();
}
