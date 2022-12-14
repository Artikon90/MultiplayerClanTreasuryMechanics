# Тестовое задание Skytec Games #
Реализовать механику изменения золота
в казне клана, при условии, что пополнение/уменьшение может производиться несколькими 
игроками по разным причинам (золото может начисляться за завершение квеста, 
либо игрок кладет его из собственного кармана) 
<br/>
Учесть возможность изменения баланса казны из 100 различных источников.
При реализации не рекомендуется использовать высокоуровневые библиотеки и фреймворки. 
Для доступа к БД необходимо использовать JDBC.
<br/>
Так же, необходимо реализовать систему отслеживания операций изменения золота для использования саппортами при
необходимости.

### Используемый стек технологий:
- Java 11
- JDBC (PostgreSQL Driver)
- Log4j/Slf4j
- Gradle

### Инструкция по запуску:
- склонировать проект в удобную директорию
- открыть папку проекта через IDE (например, Intellij IDEA)
- поднять на локальном компьютере СУБД PostgreSQL
- создать базу данных и пользователя для этой БД, с данными, как указано в файле db.properties, либо указать свои
- выполнить SQL-код из файла init.sql (src/main/resources/db)
- запустить из класса Main метод main()
- проверка результатов выполнения - таблица tracker

### Примечания для ревьюера
При реализации системы трекинга использовались записи в таблицу tracker, т.к 
данное решение максимально простое и доступное. Однако (как это вижу я), гораздо удобнее было бы использовать
отправку логов в LogStash/ElasticSearch, где саппорт сможет гораздо быстрее и удобнее получить инфу.
Так же, для Эластика или Кибаны есть готовые решения по автоматической очистке устаревших файлов,
чего нет для PostgreSQL (нужно писать кастомную функцию, но её тоже кому-то надо запускать).
<br/>
<br/>
В проекте использовался ограниченный набор исключений, в основном использовалась обёртка проверяемого исключения
в непроверяемое для соблюдения инкапсуляции и сокрытия внутренней реализации методов.
По факту же - было бы правильнее сделать более точные исключения под каждую ситуацию,
я не стал этого делать, т.к нет конкретики о необходимости подобных уточнений.