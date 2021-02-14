import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        //comparing Current time with restaurant closing time
        LocalTime currentTime = this.getCurrentTime();
        int currentHour = currentTime.getHour();
        int closingHour = this.closingTime.getHour();
        int openingHour = this.openingTime.getHour();

        //if current time is greater than or equal to closing time, return isRestaurantOpen as false
        if((currentHour - closingHour) < 0 && (currentHour > openingHour))
        {
            return true;
        }
        return false;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return this.menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    //This is used to update the isSelected property on the menu, when user selects/add items to the cart
    public void selectItem(String itemName)
    {
        List<Item> itemList = this.getMenu();
        Item selectedItem = itemList.stream().filter(x -> x.getName() == itemName)
                .findAny().orElse(null);
        if(selectedItem != null)
        {
            selectedItem.setSelected(true);
            int index = menu.indexOf(selectedItem);
            menu.set(index, selectedItem);
        }

    }

    //This is used to calculate price for all selected Items - order cost
    public int getOrderCost(List<Item> itemList) {
        int orderTotal = 0;

        //As itemList is part of menu, check for isSelected property to calculate price
        List<Item> selectedItems = itemList.stream().filter(x ->  x.getSelected() == true)
                   .collect(Collectors.toList());

        //Add price for all the selected Items
        if(selectedItems.size() > 0)
        {
            orderTotal = selectedItems.stream().mapToInt(Item::getPrice).sum();
        }

        return orderTotal;
    }
}
