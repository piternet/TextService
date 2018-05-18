# TextService

### Opis rozwiązania

Głównym problemem, który starałem się rozwiązac, była obsługa dużych plików. Odpowiadając 
na zapytanie HTTP, chemy podać n-tą linie danego pliku. Nie chcemy jednak za każdym zapytaniem
od nowa go wczytywać, gdyż to dawałoby nam liniową złozoność czasową (w zależności od długości pliku)
dla każdego zapytnaia. Najlepiej zatem byłoby plik wczytać do pamięci raz, a później w czasie
stałym odpowiadać na zapytania. Problem pojawia się, jesli plik nie mieści sie w pamięci RAM.
Postanowiłem wtedy zastosowac następujące rozwiązanie - na początku przejrzeć plik i dla każdej
linii zapamietać jej pozycję w pliku. Potem, korzystając z `RandomAccessFile` mogę wczytać linię 
od konkretnego bajtu, nie potrzebujac przetwarzać wcześniejszych danych.

Innym pomysłem na rozwiązanie tego problemu który rozważałem było wczytywanie pliku do bazy danych
(gdzie w tabeli klucz główny - numer linii) i potem szybkie odpowiadanie na zapytanie, 
pytając bazę jaką treść ma dana linia.

### Budowanie
Projekt do zbudowania wymaga zainstalowanego Mavena. 
W celu zbudowania programu należy wykonać polecenie `mvn compile` w katalogu z projektem.

### Uruchomienie
Wykonujemy `mvn spring-boot:run`. Program uruchomi się i zapyta o ścieżkę do pliku tekstowego. 
Jeżeli pod tą ścieżką istnieje plk tekstowy, to program uruchomi prosty serwis HTTP,
z którym można komunikować się pod `localhost:8080/textservice`.

Przykładowe zapytanie: 
```
curl -X GET -G \
'http://localhost:8080/textservice' \
-d line=2 \
-d user=admin
```

#### Logowanie
Aplikacja domyślnie loguje informacje o żądaniach od użytkowników na standardowe wyjście.
Jeśli chcemy zapisywać logi do pliku, należy w opcjach kompilacji (`_JAVA_OPTIONS`) ustawić 
flagę

`-Djava.util.logging.config.file=/sciezka/do/pliku.log`.
