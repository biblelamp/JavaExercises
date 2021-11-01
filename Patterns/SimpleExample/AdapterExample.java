// Это интерфейс USB с единственным методом – вставить USB-кабель

interface USB {
   void connectWithUsbCable();
}

// Класс, имитирующий карту памяти. В нем есть два нужных нам метода, но интерфейс USB он не реализует. Карту нельзя вставить в USB-разъем.

class MemoryCard {

   public void insert() {
       System.out.println("Memory card inserted successfully");
   }

   public void copyData() {
       System.out.println("Data copied successfully");
   }
}

// Наш адаптер

class CardReader implements USB {
   private MemoryCard memoryCard;

   public CardReader(MemoryCard memoryCard) {
       this.memoryCard = memoryCard;
   }

   @Override
   public void connectWithUsbCable() {
       this.memoryCard.insert();
       this.memoryCard.copyData();
   }
}

public class AdapterExample {
   public static void main(String[] args) {
       USB cardReader = new CardReader(new MemoryCard());
       cardReader.connectWithUsbCable();
   }
}