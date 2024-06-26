# Тестовое задание для отбора на Летнюю ИТ-школу КРОК по разработке

## Условие задания
Будучи тимлидом команды разработки, вы получили от менеджера проекта задачу повысить скорость разработки. Звучит, как начало плохого анекдота, но, тем не менее, решение вам все же нужно найти. В ходе размышлений и изучений различного внешнего опыта других команд разработки вы решили попробовать инструменты геймификации. То есть применить техники и подходы игрового характера с целью повышения вовлеченности команды в решение задач.

Вами была придумана рейтинговая таблица самых активных контрибьютеров за спринт. Что это значит в теории: по окончании итерации (4 рабочие недели) выгружается список коммитов, сделанных в релизную ветку продукта, и на его основе вычисляются трое самых активных разработчиков, сделавших наибольшее количество коммитов. В зависимости от занятого места, разработчик получает определенное количество внутренней валюты вашей компании, которую он впоследствии может обменять на какие-то товары из внутреннего магазина.

На практике вы видите решение следующим образом: на следующий день после окончания спринта в 00:00 запускается автоматическая процедура, которая забирает файл с данными о коммитах в релизную ветку, сделанных в период спринта, после чего выполняется поиск 3-х самых активных контрибьютеров. Имена найденных разработчиков записываются в файл, который впоследствии отправляется вам на почту.

В рамках практической реализации данной задачи вам необходимо разработать процедуру формирование отчета “Топ-3 контрибьютера”. Данная процедура принимает на вход текстовый файл (commits.txt), содержащий данные о коммитах (построчно). Каждая строка содержит сведения о коммите в релизную ветку в формате: “_<Имя пользователя> <Сокращенный хэш коммита> <Дата и время коммита>_”.
Например: AIvanov 25ec001 2024-04-24T13:56:39.492

К данным предъявляются следующие требования:
- имя пользователя может содержать латинские символы в любом регистре, цифры (но не начинаться с них), а также символ "_";
- сокращенный хэш коммита представляет из себя строку в нижнем регистре, состояющую из 7 символов: букв латинского алфавита, а также цифр;
- дата и время коммита в формате YYYY-MM-ddTHH:mm:ss.

В результате работы процедура формирует новый файл (result.txt), содержащий информацию об именах 3-х самых активных пользователей по одному в каждой строке в порядке убывания места в рейтинге. Пример содержимого файла:
AIvanov
AKalinina
CodeKiller777

Ручной ввод пути к файлу (через консоль, через правку переменной в коде и т.д.) недопустим. Необходимость любых ручных действий с файлами в процессе работы программы будут обнулять решение.

## Автор решения
Мавлетова Карина Радиковна

## Описание реализации
#### Хранение данных:
  В программе применяется две стрктуры для работы с данными.
  1. HashMap<String, Integer> usersActivity;
     Она хранит в себе информацию о пользователе и кол-ве его коммитов.
  2. LinkedHashMap<String, Integer> sortedUsersActivity также хранит информацию о кол-ве коммитов для каждого пользователя, но в отсортированном виде. Формируется на основе usersActivity.
#### Класс Checker
  Содержит в себе методы, которые предназначены для проверки данных коммита: name, hash, time and date

#### Функиция readFile()
  Данная функция вызывается первой для чтения данных о коммитах из файла commits.txt (предполагается, что файл лежит  в корне проекта или в одной директории с .jar в случае запуска программы через jar файл). Для чтения данных из файла используются классы BufferedReader и FileReader. Данные построчно. Далее они сплитятся по пробелу и отправляются на проверку в класс Checker. Если все данные коммита корректны, то они могут быть записаны в HashMap<String, Integer> usersActivity;

#### Функиция getBestContributors()
  Данная функция вызывается после readFile() и формирует на основне HashMap<String, Integer> usersActivity новую структуру данных, которая поддерживает порядок LinkedHashMap<String, Integer> sortedUsersActivity. Далее данные уходят в метод formResultFile.

#### Функиция formResultFile()
  Данная функция формирует итоговый отчёт по 3 лучшим пользователям. Предполагается, что каждый пользователь совершил разное кол-во коммтов. Так как в тз ничего не сказано про случай, когда два пользователя могли совершить одинаковое число коммитов.
  Для записи данных в файл используются классы BufferedWriter и FileWriter. Записываем первые три значения из LinkedHashMap<String, Integer> sortedUsersActivity, так как структура хранит данные в убывающем порядке.

#### Общая схема реализации

<img width="823" alt="Снимок экрана 2024-05-22 в 14 15 10" src="https://github.com/KamlR/school2024-test-task7/assets/115434090/34ba51a8-cf3a-4528-bb75-207cf4e58a36">


## Инструкция по сборке и запуску решения
Код написан на java, сборка Maven. 
Для запуска есть два варианта:
1) Склонировать репозиторий, открыть его в Intellij Idea и запустить.
2) Склонировать репозиторий. Запустить программу через jar файл. В папке target лежит файл с расширением .jar 
Для этого предварительно на вашей ОС должна стоять Java Runtime Environment (JRE).
После её установки jar файл можно запустить вот такой командой: java -jar имя_файла.jar
В качестве имени файла подставляете название jar файла, в target он хранится, как TestKrok-1.0-SNAPSHOT.jar
Обратите внимание, что при запуске программы через jar, файл commits.txt должен лежать в той же директории, что и файл jar.


