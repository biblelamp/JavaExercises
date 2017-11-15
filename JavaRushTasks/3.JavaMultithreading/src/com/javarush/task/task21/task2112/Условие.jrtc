taskKey="com.javarush.task.task21.task2112"

AutoCloseable и try-with-resources

В классе FakeConnection реализуй интерфейс AutoCloseable, чтобы объекты этого типа можно было использовать в try-with-resources.
Метод close() должен выводить на экран фразу "Closing database connection..."
В блоке try последовательно вызови методы usefulOperation() и unsupportedOperation().

Вывод на экран должен быть следующим:
Creating database connection...
Entering the body of try block.
Executing useful operation.
Operation is not supported yet!
Closing database connection...

Обрати внимание на то, что ресурсы были освобождены автоматически несмотря на исключение брошенное методом unsupportedOperation.


Требования:
1.	Класс FakeConnection должен поддерживать интерфейс AutoCloseable.
2.	Метод close класса FakeConnection должен выводить на экран фразу "Closing database connection...".
3.	В методе main класса Solution должны быть вызваны методы usefulOperation и unsupportedOperation у объекта класса FakeConnection.
4.	Вывод на экран должен соответствовать условию задачи.
5.	Метод close класса FakeConnection не должен бывать вызван явно.


