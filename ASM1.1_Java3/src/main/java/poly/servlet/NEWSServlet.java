package poly.servlet;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.entity.NEWS;
@WebServlet({ "/NEWS/index", 
			"/NEWS/edit/*", 
			"/NEWS/create", 
			"/NEWS/update",
			"/NEWS/delete", 
			"/NEWS/reset"})

public class NEWSServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NEWS form = new NEWS();
		try {
			BeanUtils.populate(form, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		NEWSDAO dao = new NEWSDAO();
		String path = req.getServletPath();
		if (path.contains("edit")) {
			String id = req.getPathInfo().substring(1);
			form = dao.findById(id);
		} else if (path.contains("create")) {
			dao.create(form);
			form = new NEWS();
		} else if (path.contains("update")) {
			dao.update(form);
		} else if (path.contains("delete")) {
			dao.deleteById(form.getId());
			form = new NEWS();
		} else {
			form = new NEWS();
		}
		req.setAttribute("item", form);
		List<NEWS> list = dao.findAll();
		req.setAttribute("list", list);
		req.getRequestDispatcher("index.html").forward(req, resp);
	}
}
