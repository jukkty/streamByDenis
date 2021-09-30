import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Google {
    public static void main(String[] args) throws IOException {
        String filename = System.getProperty("user.dir") + "\\randomPeople.csv";
        List<Person> persons = new CsvToBeanBuilder(new FileReader(filename))
                .withType(Person.class)
                .build()
                .parse();
        persons.forEach(System.out::println);

        Person maxAgePerson = persons.stream()
                .max(Comparator.comparing(Person::getAge))
                .orElseThrow(NoSuchElementException::new);


        System.out.println("\n Max Age Person: " + maxAgePerson);

        System.out.println("Engineers: " + persons.stream()
                .filter(p -> "Engineer".equalsIgnoreCase(p.getJobTitle()))
                .collect(Collectors.toList()));

        System.out.println("\n Sorted list of persons: ");
        persons.stream()
                .sorted((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()))
                .map(person -> person.getFirstName() + " " + person.getLastName())
                .forEach(System.out::println);

    }


}
