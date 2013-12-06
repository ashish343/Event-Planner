package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import event.EventIdGenerator;

@SuppressWarnings("serial")
@WebServlet(name = "CreateEventServlet", urlPatterns = { "/addEvent" })
public class CreateEvent extends DefaultController {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventId = EventIdGenerator.generateUniqueEventId();
		String name = "Name is ::" + request.getParameter("name");
		String id = "ID is ::" + request.getParameter("id");
		String phoneNo = "PhoneNo is ::" + request.getParameter("phoneNo");
		
		ServletOutputStream out = response.getOutputStream();
        out.write(name.getBytes());
        out.write("\n".getBytes());
        out.write(id.getBytes());
        out.write("\n".getBytes());
        out.write(phoneNo.getBytes());
        out.write("\n".getBytes());
        out.write(("Event Id" + eventId).getBytes());
        out.flush();
        out.close();
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
