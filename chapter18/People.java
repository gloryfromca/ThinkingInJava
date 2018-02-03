package chapter18;

import java.io.IOException;
import java.util.ArrayList;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

public class People extends ArrayList<Person> {
	public People(String docname) throws ValidityException, ParsingException, IOException {
		Document doc = new Builder().build(docname);
		Elements people = doc.getRootElement().getChildElements();
		for (int i = 0; i < people.size(); i++)
			add(new Person(people.get(i)));
	}

	public static void main(String[] args) throws ValidityException, ParsingException, IOException {
		People people = new People(FilesForTest.XMLMac);
		System.out.println(people);
	}

}
