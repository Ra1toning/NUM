import java.util.*;
import java.util.stream.Collectors;

public class NUM {
    public static void main(String[] args) {

        List<String> programs = Arrays.asList("Computer Science", "Software Engineering", "Information Technology", "Information Systems");

        Map<String, Integer> programApplicants = programs.stream()
                .collect(Collectors.toMap(program -> program, program -> (int) (Math.random() * 100)));

        System.out.println("Хөтөлбөр тус бүрийг сонгосон оюутны тоо:");
        programApplicants.forEach((program, applicants) -> System.out.println(program + ": " + applicants));

        Map<String, Map<String, Integer>> programLocationApplicants = programs.stream()
                .collect(Collectors.toMap(program -> program, program -> generateLocationApplicants(programApplicants.get(program))));

        System.out.println("\nНийслэл, аймаг тус бүрээс хөтөлбөр тус бүрийг сонгосон оюутны тоо:");
        programLocationApplicants.forEach((program, locationApplicants) -> {
            System.out.println(program + ":");
            locationApplicants.forEach((location, applicants) -> System.out.println("  " + location + ": " + applicants));
        });

        String mostApplicantsProgram = programApplicants.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("None");

        System.out.println("\nХамгийн олон элсэгчийг хүлээн авсан хөтөлбөр: " + mostApplicantsProgram);
    }

    private static Map<String, Integer> generateLocationApplicants(int totalApplicants) {
        List<String> locations = Arrays.asList("Нийслэл", "Аймаг");
        Map<String, Integer> locationApplicants = new HashMap<>();

        int randomNumber = (int) (Math.random() * totalApplicants);

        locationApplicants.put(locations.get(0), randomNumber);
        locationApplicants.put(locations.get(1), totalApplicants - randomNumber);

        return locationApplicants;
    }
}
