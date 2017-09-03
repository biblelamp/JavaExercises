taskKey="com.javarush.task.task17.task1703"

Синхронизированные заметки

1. Класс Note будет использоваться нитями. Поэтому сделай так, чтобы обращения к листу notes блокировали мьютекс notes, не this
2. Все System.out.println не должны быть заблокированы (синхронизированы), т.е. не должны находиться в блоке synchronized


Требования:
1.	Метод addNote() должен добавлять записки в список notes.
2.	Метод removeNote() должен удалять записку из списка notes.
3.	В методе addNote() должен находиться synchronized блок.
4.	В методе removeNote() должен находиться synchronized блок.
5.	Synchronized блок в методе addNote() должен блокировать мьютекс notes.
6.	Synchronized блок в методе removeNote() должен блокировать мьютекс notes.
7.	В synchronized блоке метода addNote() должен находится вызов метода add у notes.
8.	В synchronized блоке метода removeNote() должен находится вызов метода remove у notes.
9.	Все команды вывода на экран не должны находиться в блоке synchronized.


