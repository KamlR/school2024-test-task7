import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class WorkClass {

    // Структура данных, которая хранит пользователя и кол-во его/её коммитов.
    private static HashMap<String, Integer> usersActivity = new HashMap<>();


    /*
        Данный метод предназначен для чтения данных из файла commits.txt.
        Предполагается, что файл хранится в корне проекта.
        Файл читается построчно.
        Каждая запись сплитится по пробелу.
        Прежде чем записывать данные в hashmap, они проверяются на корректность с помощью написанного класса Checker.

        Чтение обёрнуто в try catch для предотвращения ошибок при работе с файлом.
     */
    private static void readFile(){
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + File.separator + "commits.txt"))) {
            String userData;
            while ((userData = br.readLine()) != null) {
                String[] splitUserData = userData.split(" ");
                //  Добавление новых данных происходит в случае их корректности.
                if(Checker.checkData(splitUserData[0], splitUserData[1], splitUserData[2])){
                    if(usersActivity.containsKey(splitUserData[0])){
                        usersActivity.put(splitUserData[0], usersActivity.get(splitUserData[0])+1);
                    }
                    else{
                        usersActivity.put(splitUserData[0], 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Возникла ошибка при чтения файла commits.txt "+ e.getMessage());
        }
    }

    /*
        Данный метод перекидывает данные из HashMap в  LinkedHashMap в сортированном виде.
        Сортировка реализована по убыванию.
        После сортировки данные передаются в formResultFile для записи результата.
     */

    private static void getBestContributors(){
        LinkedHashMap<String, Integer> sortedUsersActivity = usersActivity.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        formResultFile(sortedUsersActivity);
    }

    /*
        Данный метод записывает 3 самых активных работников в файл.
        Данные записываются по убыванию.
        Предполагается, что нет людей с одинаковым числом коммитов.
        Данные пишутся в файл results.txt, он будет сохранён в корне проекта.
     */
    private static void formResultFile(LinkedHashMap<String, Integer> sortedUsersActivity){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt"))) {
            int count = 0;
            for (Map.Entry<String, Integer> entry : sortedUsersActivity.entrySet()) {
                if (count == 3) break;
                writer.write(entry.getKey());
                writer.newLine();
                count++;
            }
        } catch (IOException e) {
            System.out.println("Возникла ошибка при записи данных в файл results.txt"+ e.getMessage());
        }
    }

    /*
        Точка входа в программу.
     */
    public static void main(String[] args) {
        readFile();
        getBestContributors();
    }
}
