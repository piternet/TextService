# TextService

### Budowanie
Projekt do zbudowania wymaga zainstalowanego Mavena. 
W celu zbudowania programu należy wykonać polecenie `mvn compile` w katalogu z projektem.

### Uruchomienie
Wykonujemy `mvn spring-boot:run`. Program uruchomi się i zapyta o ścieżkę do pliku tekstowego. 
Jeżeli pod tą ścieżką istnieje plk tekstowy, to program uruchomi prosty serwis HTTP,
z którym można komunikować się pod `localhost:8080/textservice`.
#### Logowanie
Aplikacja domyślnie loguje informacje o żądaniach od użytkowników na standardowe wyjście.
Jeśli chcemy zapisywać logi do pliku, należy w opcjach kompilacji (`_JAVA_OPTIONS`) ustawić 
flagę `-Djava.util.logging.config.file=/sciezka/do/pliku.log`.
