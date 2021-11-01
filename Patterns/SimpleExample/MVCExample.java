// Класс Модель

class Model {
   private int id;
   private String name;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}

// Класс Вид

class View {
   public void printModelDetails(int id, String name){
      System.out.println("Model: ");
      System.out.println("Id: " + id);
      System.out.println("Name: " + name);
   }
}

// Класс Контроллер

class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view){
        this.model = model;
        this.view = view;
    }

    public void setModelName(String name){
        model.setName(name);
    }

    public String getModelName(){
        return model.getName();
    }

    public void setModelId(int id){
        model.setId(id);
    }

    public int getModelId(){
        return model.getId();
    }

    public void updateView() {
        view.printModelDetails(model.getId(), model.getName());
    }
}

public class MVCExample {
    public static void main(String[] args) {
        Model model  = retriveModelFromDb();

        View view = new View();
        Controller controller = new Controller(model, view);
        controller.updateView();

        //update model data
        controller.setModelName("SecondName");
        System.out.println("\nAfter updating, Model details are as follows:");
        controller.updateView();
    }

    private static Model retriveModelFromDb(){
       Model model = new Model();
       model.setId(1);
       model.setName("Name");
       return model;
    }
}
