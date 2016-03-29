package com.chenlai.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/genarate")
public class GenFieldSetAndGet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String entityType = request.getParameter("entityType");
		String fields = request.getParameter("fields");
		String fieldsArr[] = fields.split("\n");

		StringBuffer setget = new StringBuffer();
		StringBuffer mapPropety = new StringBuffer();
		StringBuffer fieldSb = new StringBuffer();

		List<String> fieldNames = new ArrayList<String>();
		StringBuffer updateSql = new StringBuffer();
		StringBuffer insertSql = new StringBuffer();

		String split = "-------------------------------------------------------------------------------";
		updateSql.append("Update SQL:<br>" + split + "<br>UPDATE 【table_name_update_here】 set ");
		insertSql.append("Insert SQL:<br>" + split + "<br>INSERT INTO 【table_name_update_here】(");

		try {

			for (String field : fieldsArr) {
				if (field != null && !"".equals(field.trim())) {
					field = field.trim();
					String arr[] = Genarater.work(field, entityType);
					setget.append(arr[0]).append(arr[1]);
					mapPropety.append(arr[2]);
					fieldSb.append(arr[3]);
					fieldNames.add(arr[4]);
				}
			}

			for (String fieldName : fieldNames) {
				updateSql.append(fieldName.toLowerCase()).append("=").append("#{" + fieldName + "},");
				insertSql.append(fieldName.toLowerCase()).append(",");
			}

			if (updateSql.length() > 0) {
				updateSql.delete(updateSql.length() - 1, updateSql.length());
				updateSql.append("<br>").append(split);
			}

			if (insertSql.length() > 0) {
				insertSql.delete(insertSql.length() - 1, insertSql.length());
				insertSql.append(") VALUES(");
			}

			for (String fieldName : fieldNames) {
				insertSql.append("#{" + fieldName + "}").append(",");
			}

			if (insertSql.length() > 0) {
				insertSql.delete(insertSql.length() - 1, insertSql.length());
				insertSql.append(")").append("<br>" + split);
			}

			System.out.println(mapPropety.toString().replaceAll("<br>", "\n"));
			System.out.println(setget.toString().replaceAll("<br>", "\n"));

			System.out.println("updateSql : " + updateSql.toString());
			System.out.println("insertSql : " + insertSql.toString());

			fieldSb.append("<br><br>").append(setget.toString());
			mapPropety.append("<br><br>").append(insertSql.toString()).append("<br><br>").append(updateSql.toString()).append("<br><br>")
					.append(fieldSb.toString());

			request.getSession().setAttribute("result", mapPropety.toString());

		} catch (Exception e) {
			request.getSession().setAttribute("errorInfo", "出错啦，请检查一下格式是否正确^_^");
			e.printStackTrace();
		}

		response.sendRedirect("index.jsp");
	}
}
