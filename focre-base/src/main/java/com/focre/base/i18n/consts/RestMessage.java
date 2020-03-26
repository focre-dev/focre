package com.focre.base.i18n.consts;

public enum RestMessage implements I18nMessage {

	/** 管理员添加失败 */
	ADMIN_ADD_FAILURE("admin.add.failure"),

	/** 管理员删除失败 */
	ADMIN_DELETE_FAILURE("admin.delete.failure"),

	/** 管理员信息更新失败 */
	ADMIN_UPDATE_FAILURE("admin.update.failure"),

	/** 更新管理员状态失败 */
	ADMIN_UPDATE_STATUS_FAILURE("admin.update.status.failure"),

	/** 重置管理员密码失败 */
	ADMIN_RESET_PASSWORD_FAILURE("admin.reset.password.failure"),

	/** 部门添加失败 */
	DEPT_ADD_FAILURE("dept.add.failure"),

	/** 部门信息更新失败 */
	DEPT_UPDATE_FAILURE("dept.update.failure"),

	/** 部门删除失败 */
	DEPT_DELETE_FAILURE("dept.delete.failure"),

	/** 角色添加失败 */
	ROLE_ADD_FAILURE("role.add.failure"),

	/** 角色信息更新失败 */
	ROLE_UPDATE_FAILURE("role.update.failure"),

	/** 角色删除失败 */
	ROLE_DELETE_FAILURE("role.delete.failure"),
	;

	private String key;

	RestMessage(String key) {
		this.key = key;
	}

	public static I18nMessage getRestMessageByName(String name) {
		for (RestMessage item : RestMessage.values()) {
			if (item.name().equals(name)) {
				return item;
			}
		}

		for (CommonMessage item : CommonMessage.values()) {
			if (item.name().equals(name)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public String getkey() {
		return this.key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
