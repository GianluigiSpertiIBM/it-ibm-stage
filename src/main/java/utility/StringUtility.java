package utility;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import model.Utente;

public class StringUtility {

	public static void toJSON(Utente u) {
		// Create an ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Serialize the Utente object to a JSON file
            objectMapper.writeValue(new File("src/main/resources/userGianluigi.json"), u);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static Utente[] fromJSON() {
		byte[] file = FileUtility.getFileFromInternalResources("user.json");
		String stringFile = new String(file);
		JsonObject jsonObject = JsonParser.parseString(stringFile).getAsJsonObject();
		JsonArray usersArray = jsonObject.get("utenti").getAsJsonArray();
		Utente[] utenti = new Utente[3];
		
		Gson gson = new Gson();
		System.out.println("Lettura dal file JSON: ");
		for (int i = 0; i < usersArray.size(); i++) {
			utenti[i] = gson.fromJson(usersArray.get(i), Utente.class);
			String nome = usersArray.get(i).getAsJsonObject().get("nome").getAsString();
			if(nome.equals("Gianluigi")) { //per stampare il json con il mio nome
				System.out.println(usersArray.get(i).getAsJsonObject() + "\n");
			}
		}
		return utenti;
	}
	
	public static void toXML(Utente u) {
		try {
			// Create a JAXB context for the Utente class
			JAXBContext context = JAXBContext.newInstance(Utente.class);

			// Create a marshaller from the context
			Marshaller marshaller = context.createMarshaller();

			// Set the marshaller property to format the XML output
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			System.out.println("File XML creato a partire dall'oggetto Utente: ");
			// Marshal the Utente object to XML and print it to the console
			marshaller.marshal(u, System.out);

			//per salvare il file
			// Alternatively, you can marshal the object to a file or other output streams
			marshaller.marshal(u, new File("src/main/resources/userGianluigi.xml"));
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static Utente fromXML() {
		byte[] file = FileUtility.getFileFromInternalResources("userGianluigi.xml");
		String stringFile = new String(file);
		Utente u = new Utente();
		try {
            // Create a JAXB context for the User class
            JAXBContext context = JAXBContext.newInstance(Utente.class);

            // Create an Unmarshaller
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Unmarshal the XML string to a Java object
            u = (Utente) unmarshaller.unmarshal(new StringReader(stringFile));


        } catch (JAXBException e) {
            e.printStackTrace();
        }
		return u;
	}
}
