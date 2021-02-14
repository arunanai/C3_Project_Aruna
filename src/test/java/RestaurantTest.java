import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void SetUp()
    {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");

        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //Arrange
        LocalTime mockTime = LocalTime.NOON;
        Restaurant restaurantSpy = Mockito.spy(restaurant);
        Mockito.when(restaurantSpy.getCurrentTime()).thenReturn(mockTime);

        //Act
        boolean isOpen = restaurantSpy.isRestaurantOpen();

        //Assert
        Assertions.assertTrue(isOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //Arrange
        LocalTime mockTime = LocalTime.MIDNIGHT;
        Restaurant restaurantSpy = Mockito.spy(restaurant);
        Mockito.when(restaurantSpy.getCurrentTime()).thenReturn(mockTime);

        //Act
        boolean isOpen = restaurantSpy.isRestaurantOpen();

        //Assert
        Assertions.assertFalse(isOpen);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        //Arrange
        int initialMenuSize = restaurant.getMenu().size();

        //Act
        restaurant.addToMenu("Sizzling brownie",319);

        //Assert
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        //Arrange
        int initialMenuSize = restaurant.getMenu().size();

        //Act
        restaurant.removeFromMenu("Vegetable lasagne");

        //Assert
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        //Act, Assert
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}