package event;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import servlet.DefaultController;
import contacts.Contact;
import event.parse.notifications.Notifications;

@SuppressWarnings("serial")
@WebServlet(name = "CreateEventServlet", urlPatterns = { "/addEvent" })
public class CreateEvent extends DefaultController {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String eventId = EventIdGenerator.generateUniqueEventId();
		ServletOutputStream out = response.getOutputStream();
		EventData eventData = null;
		try {

			eventData = createEventDataObject(eventId, request);
			out.write(eventData.toString().getBytes());

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		if (eventData != null) {
			try {
				addtoDB(eventData);
				notifyForNewEvent(eventData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		out.write(("Event Id").getBytes());
		out.flush();
		out.close();
		response.setStatus(HttpServletResponse.SC_OK);
	}

	private void notifyForNewEvent(EventData eventData) {
		Notifications.registerChannel(eventData);
		Notifications.notifyChannel(eventData);
	}

	private void addtoDB(EventData eventData) throws IOException {
		// DataConnection.addEvent(eventData);
	}

	private EventData createEventDataObject(String eventId,
			HttpServletRequest request) throws JSONException {
		EventData eventData = null;
		String contacts = request.getParameter(EventParams.CONTACT_LIST
				.toString());
		ArrayList<Contact> carr = new ArrayList<Contact>();
		if (contacts != null) {
			JSONArray jar = new JSONArray(contacts);
			for (int ix = 0; ix < jar.length(); ix++) {
				JSONObject obj = (JSONObject) jar.get(ix);
				String name = obj.getString(EventParams.NAME.toString());
				String phone = obj.getString(EventParams.CONTACT_NUMBER
						.toString());
				carr.add(new Contact(name, phone));
			}
		}

		String objectId = request
				.getParameter(EventParams.OBJECT_ID.toString());

		if (objectId != null) {
			eventData = new EventData("", eventId, carr);
		}
		return eventData;
	}

}
