public class Item {
    private String name;
    private int price;
    private boolean isSelected;

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
        this.isSelected = false;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return  name + ":"
                + price
                + "\n"
                ;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean getSelected()
    {
        return this.isSelected;
    }

    public int getPrice() {
        return price;
    }
}
