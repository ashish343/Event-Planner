package event;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.DefaultController;
import database.mongo.DataConnection;

@SuppressWarnings("serial")
@WebServlet(name = "CreateEventServlet", urlPatterns = { "/addEvent" })
public class CreateEvent extends DefaultController {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eventId = EventIdGenerator.generateUniqueEventId();
		
		EventData eventData = createEventDataObject(eventId, request);
		if(eventData != null) {
			try {
				addtoDB(eventData);
				notifyForNewEvent(eventId);
			} catch (Exception e) {
				//TODO: May be add a retry here.
			}
		}
		
        ServletOutputStream out = response.getOutputStream();
        
        out.write(("Event Id").getBytes());
        out.flush();
        out.close();
        response.setStatus(HttpServletResponse.SC_OK);
	}

	private void notifyForNewEvent(String eventId) {
		// TODO Auto-generated method stub
	}

	private void addtoDB(EventData eventData) throws UnknownHostException {
		DataConnection.addEvent(eventData);
	}

	private EventData createEventDataObject(String eventId, HttpServletRequest request) {
		EventData eventData = null;
		String contacts = request.getParameter(EventParams.CONTACT_LIST.toString());
		String objectId = request.getParameter(EventParams.OBJECT_ID.toString());
		
		if(objectId != null) {
			eventData = new EventData();
			Map<String, String> contactsMap = getContactMap(contacts);
			eventData.setEventId(eventId);
			eventData.setContacts(contactsMap);
			eventData.setOwner(objectId);
		}
		return eventData;
	}

	private Map<String, String> getContactMap(String contacts) {
		return null;
	}
}