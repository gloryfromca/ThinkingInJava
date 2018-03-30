package chapter18_io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.omg.PortableServer.POA;
import com.sun.xml.internal.ws.db.glassfish.BridgeWrapper;

import nu.xom.*;

public class Person {
	private String first;
	private String last;

	public Person(String first, String last) {
		this.first = first;
		this.last = last;

	}

	public Person(Element person) {
		this.first = person.getFirstChildElement("first").getValue();
		this.last = person.getFirstChildElement("last").getValue();
	}

	@Override
	public String toString() {
		return first + " " + last;
	}

	public static void format(OutputStream os, Document doc) throws IOException {
		Serializer serializer = new Serializer(os, "ISO-8859-1");
		serializer.setIndent(4);
		serializer.setMaxLength(60);
		serializer.write(doc);
		serializer.flush();
	}

	public Element getXML() {
		Element person = new Element("person");
		Element firstname = new Element("first");
		Element lastname = new Element("last");
		firstname.appendChild(first);
		lastname.appendChild(last);
		person.appendChild(firstname);
		person.appendChild(lastname);
		return person;

	}

	public static void main(String[] args) throws IOException {
		List<Person> people = Arrays.asList(new Person("zhang", "hui"), new Person("wang", "zhi"));
		System.out.println(people);
		Element root = new Element("people");
		for (Person e : people)
			root.appendChild(e.getXML());
		Document doc = new Document(root);
		format(System.out, doc);
		format(new BufferedOutputStream(new FileOutputStream(FilesForTest.XMLMac)), doc);
		
	}
}
