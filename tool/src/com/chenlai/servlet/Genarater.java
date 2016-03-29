/*
 * Copyright (c) 2014-2018 Chenlaisoft Co.Ltd. All rights reserved.
 */

package com.chenlai.servlet;

/**
 * TODO
 * 
 * @author jerry.chen
 */
public class Genarater {

	public static String[] work(String fields, String entityType) throws Exception {

		String result[] = new String[5];

		String resouce = fields.split("//")[0];
		resouce = resouce.replaceAll("<", "&lt;").replaceAll(">", "&gt;") + "<br>";

		String arr[] = fields.split(";");
		String arrField[] = arr[0].split(" ");
		String type = arrField[1];
		type = type.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

		String fieldName = arrField[2];
		String define = arr[0];
		String annotation = arr[1].substring(2);

		define = define.replace("private", "public");

		String get = "get";
		String set = "set";

		String firstFiledNameChar = String.valueOf(fieldName.charAt(0)).toUpperCase();
		String getMethodName = get + firstFiledNameChar + fieldName.substring(1);
		getMethodName = define.replace(fieldName, getMethodName).replaceAll("<", "&lt;").replaceAll(">", "&gt;");

		String setMethodName = set + firstFiledNameChar + fieldName.substring(1);
		String setDefine = "public void " + setMethodName;

		String getMethod = null;
		String setMethod = null;

		if ("1".equals(entityType)) {

			getMethod = "<br><br>/**<br>*获取" + annotation + "<br>*@return " + fieldName + " " + annotation + "<br>*/<br>" + getMethodName
					+ "(){ <br>return " + fieldName + ";<br>}<br><br>";

			setMethod = "/**<br>*设置" + annotation + "<br>*@param " + fieldName + " " + annotation + "<br>*/<br>" + setDefine + "(" + type + " "
					+ fieldName + "){<br>" + "this." + fieldName + " = " + fieldName + ";<br>}";

		} else if ("2".equals(entityType)) {

			getMethod = "<br><br>/**<br>*获取" + annotation + "<br>*@return " + fieldName + " " + annotation + "<br>*/<br>" + getMethodName
					+ "(){ <br>return getValue(\"" + fieldName + "\");<br>}<br><br>";

			setMethod = "/**<br>*设置" + annotation + "<br>*@param " + fieldName + " " + annotation + "<br>*/<br>" + setDefine + "(" + type + " "
					+ fieldName + "){<br>" + "this.setValue(\"" + fieldName + "\", " + fieldName + ");<br>}";
		}

		String mapPropety = "&lt;result column=\"" + fieldName.toLowerCase() + "\" property=\"" + fieldName + "\" /&gt;<br>";

		result[0] = getMethod;
		result[1] = setMethod;
		result[2] = mapPropety;
		result[3] = resouce;
		result[4] = fieldName;
		return result;
	}
}
