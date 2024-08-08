Используется Java / Postgre / Json
Создается POJO класс аналог класса сущностей, но без создания в базе данных таблицы, которая является образом этого класса. 
Класс POJO отрабатывает свои обязанности по распределению данных из json файла. Данные из файла json распределются по классу его полям.
Класс возвращает данные с полями в виде объекта в коллекцию List с типов этого класса. Коллекция List собирает все объекты, в которых имеюся поля. В эти поля записываются данные из файла json.
Эта коллекция отправляется в путешествие по классам и методам. В них извлекаются необходимые данные с помощью 2 разных компараторов (сортировка по дате и ценам). На выходе получается сортировка каждого 
перевозчика из каждого объекта из файла json. Сам файл состоит из одного массива с объектами, в которых записы данные, среди этих данных имеется строка с ключом Перевозчик. Задача состоит в получение 
по каждому перевозчику (отдельно по каждому) минимального времени полета между двумя городами и получение разницы между средней ценой и медиана по всем перевозчикам (не зависимо от ключа Перевозчик) 
между двуми конкретными городами. Выполняется выборка, отбор из файла ключа Перевозчик по значению. Всех одинаковых перевозчиков группируем отдельно от не одинаковых. Между одинаковыми перевозчиками ищем 
по созданному компаратору минимальное время полета между двумя городами. Выводим одного перевозчика из каждой группы. Находим разницу в цене посозданному компаратору по все перевозчикам между двуми конкретными города.
Дополнительно создается база данных Postgre. В одну таблицу можно внести все данные из файла и можно создать таблицы для каждого перевозчика. Данные можно извлекать, отправлять на анализ, получения необходимых данных
