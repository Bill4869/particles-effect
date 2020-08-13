package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class readFile {

	public static List<language> getLanguages() {
		List<language> languages = new ArrayList<>();
		List<String> strings = new ArrayList<>();
		File languageFile = new File("language.pd");
		try {
			BufferedReader buffLanguage = new BufferedReader(new FileReader(languageFile));
			String line = buffLanguage.readLine();
			while (line != null) {
				strings.add(line);
				line = buffLanguage.readLine();
			}
			for (String string : strings) {
				String[] memos = string.split("%:%");
				languages.add(new language(memos[0], memos[1]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return languages;

	}

	public static List<person> getPerson() {
		List<person> people = new ArrayList<>();
		List<String> strings = new ArrayList<>();
		File peopleFile = new File("person.pd");
		try {
			BufferedReader buffPeople = new BufferedReader(new FileReader(peopleFile));
			String line = buffPeople.readLine();
			while (line != null) {
				strings.add(line);
				line = buffPeople.readLine();

			}
			for (String string : strings) {
				String[] memos = string.split("%:%");
				people.add(new person(memos[0], memos[1], memos[2]));
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return people;

	}

}
