# ToDoList
Projekt ToDoList wraz z rozwiązaniami wszystkich zadań (0-10) zaimplementowanych w jednym projekcie:

Zadanie 0.

Przeformatować kod i zastosować zmiany, które podpowiada Android studio w plikach *.java.

Zadanie 1.

Przerobić w pliku activity_main.xml LinearLayout na ConstraintLayout tak aby elementy były
zagnieżdżone tylko w jednym layout (pomiędzy tagiem otwierającym a zamykającym
ConstraintLayout). Pole do wpisania tekstu i button mają znajdować się bezpośrednio pod listą
elementów.

Zadanie 2.

Wyświetlić Toast albo SnackBar w momencie, gdy nie znaleziono pliku “listinfoa.dat”.

Zadanie 3.

Do wyświetlenia pytania o usunięcie elementu proszę użyć AlertDialogFragmentu zamiast
AlertDialogu.

Zadanie 4.

Do wyświetlania elementów proszę użyć RecyclerView zamiat ListView. Na kocu każdego elementu
powinien znajdować się obrazek (może być ikona śmietniczka). Po kliknięciu w ikonę dopiero wtedy
powinien pojawić się komunikat o usunięciu danego elementu.

Zadanie 5. (Wymagane zadanie nr 4)

Co drugi element na liście zamiast zadania powinien wyświetlić dowolny element w WebView.
Proszę użyć listę dowolnych 3 elementów (np. strona www, link do obrazka). Wysokość elementu nie
większa niż 200dp.

Zadanie 6.

Proszę utworzyć Service (komponent Androida), który będzie modyfikował plik, w którym
zapisywane są elementy przy użyciu już istniejących klas. Do przekazywania danych należy użyć
kolejnego komponentu Intent.

Zadanie 7. (Wymagane zadanie nr 6)

Proszę zmodyfikować kod tak aby utworzony wcześniej Service zamiast zapisywać do pliku zapisywał
elementy w SharedPreferences.

Zadanie 8.

Proszę przerobić wczytywanie i zapisywanie danych do pliku (lub do SharedPreferences) tak aby
wykorzystać standard JSON. Do przetwarzania danych można użyć dowolnej biblioteki –
proponowany Gson.

Struktura jednego rekordu:

{

id: '1',

data: '2022-12-27T15:05:11.953Z’

text: 'Wysłać zadanie dla kursantów',

isDeleted: 'false'

}

Id – kolejny numer porządkowy

Format daty - " yyyy-MM-dd'T'HH:mm:ss.SSSZ

Elementy usunięte nie powinny być wyświetlane.

Zadanie 9. (Wymagane zadanie nr 6)

A) Proszę dodać 2 przyciski w dowolnym miejscu, które pozwolą sortować elementy na liście:
- po ilości znaków w polu text
- po dacie dodania

B) Przycisk, który pozwoli wyświetlić i ukryć elementy usunięte (proszę te elementy oznaczyć
wizualnie w dowolny sposób) - elementy usunięte mają być wyświetlana wraz z elementarni
nie usuniętymi według id.

C) Dla elementu usuniętego proszę dodać ikonę lub przycisk, który umożliwi przywrócenie
elementu – element powinien być wyświetlany wraz z elementami nie usuniętymi. Po
przywróceniu elementu należy mu nadać aktualną datę.

Zadanie 10.

Jeśli poprzednie zadania nie spełniły Twoich oczekiwań lub były zbyt trudne możesz zaproponować
dowolną modyfikację UI i kodu w wraz z komentarzem co ten kod robi.
