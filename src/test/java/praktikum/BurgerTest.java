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


}
