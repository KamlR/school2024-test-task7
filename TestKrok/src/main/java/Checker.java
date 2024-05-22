import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    Данный класс предназначен для проверки данных, которые содержит commits.txt.
    Изначально данные приходят в checkData, далее этот метод уже распределяет, что и куда пойдёт на проверку.
 */
public class Checker {

    /*
        Данный метод проверяет корректность имени пользователя.
        Используется регулярное выражение для проверки.
     */
    private static boolean checkUsername(String userName){
        String regex = "^[a-zA-Z_][a-zA-Z0-9_]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }

    /*
        Данный метод проверяет корректность хэша.
        Используется регулярное выражение для проверки.
     */
    private static boolean checkHash(String hash){
        String regex = "^[a-z0-9]{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(hash);
        return matcher.matches();
    }

    /*
        Данный метод проверяет корректность даты.
        Используется регулярное выражение для проверки.
     */
    private static boolean checkDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        try {
            LocalDateTime.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /*
        Данный метод принимает данные, которые должны проверяться.
        Далее он уже знает, что и куда распределить.
     */
    public static boolean checkData(String userName, String hash, String date){
        return checkUsername(userName) && checkHash(hash) && checkDate(date);
    }
}
