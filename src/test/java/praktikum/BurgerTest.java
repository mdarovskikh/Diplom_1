package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(Parameterized.class)
public class BurgerTest {
    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;
    private Burger burger;

    private float bunPrice;
    private float saucePrice;
    private float fillingPrice;
    private float expectedPrice;

    // конструктор
    public BurgerTest(float bunPrice, float saucePrice, float fillingPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.saucePrice = saucePrice;
        this.fillingPrice = fillingPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 70f, 300f},
                {0f, 0f, 0f, 0f},
                {200f, 100f, 0f, 500f}
        });
    }

    @Before
    public void setUp() {
        org.mockito.MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    /*
     * Проверяем установку булки для бургера
     */
    @Test
    public void setBunShouldSetBun() {
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    /*
     * Проверяет, что при добавлении ингредиента он попадает в список
     */
    @Test
    public void addIngredientShouldAddIngredientToList() {
        burger.addIngredient(sauce);
        assertEquals(1, burger.ingredients.size());
        assertSame(sauce, burger.ingredients.get(0));
    }

    /*
     * Проверяет удаление ингредиента по индексу
     */
    @Test
    public void removeIngredientShouldRemoveIngredientByIndex() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertSame(filling, burger.ingredients.get(0));
    }
    /*
     * Проверяет удаление ингредиента по несуществующему индексу
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientWithInvalidIndexShouldThrowException() {
        burger.addIngredient(sauce);
        burger.removeIngredient(5);
    }

    /*
     * Проверяет перемещение ингредиента на другую позицию
     */
    @Test
    public void moveIngredientShouldMoveIngredientToNewIndex() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        Ingredient third = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);

        burger.moveIngredient(0, 2);

        assertEquals(Arrays.asList(second, third, first), burger.ingredients);
    }


    /*
     * Проверяет перемещение ингредиента на несуществующую позицию
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientWithInvalidIndexShouldThrowException() {
        burger.addIngredient(sauce);
        burger.moveIngredient(0, 5);
    }

    /*
     * Параметризованный тест расчёта цены
     */
    @Test
    public void getPriceShouldCalculateCorrectTotal() {
        when(bun.getPrice()).thenReturn(bunPrice);
        when(sauce.getPrice()).thenReturn(saucePrice);
        when(filling.getPrice()).thenReturn(fillingPrice);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }


}
