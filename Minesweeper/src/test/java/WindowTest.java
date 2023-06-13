import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WindowTest {

    Window window;
    Game game;

    @BeforeEach
    public void setup() {
        game = Mockito.mock(Game.class);
        Window window = TestHelper.makeWindow();
        when(game.getWindow())
                .thenReturn(window);
    }
    @Test
    void windowNotNull() {
        assertNotNull(game.getWindow());
    }
    @Test
    void createsValidWindowHeight() {
        assertTrue(game.getWindow().getHeight() == 720);
    }
    @Test
    void doesNotCreateInvalidWindowHeight() {
        assertTrue(game.getWindow().getHeight() < 721);
        assertTrue(game.getWindow().getHeight() > 719);
    }

    @Test
    void createsValidWindowWidth() {
        assertTrue(game.getWindow().getWidth() == 720);
    }
    @Test
    void doesNotCreateInvalidWindowWidth() {
        assertTrue(game.getWindow().getWidth() < 721);
        assertTrue(game.getWindow().getHeight() > 719);
    }
    @Test
    void createsWindowWithCorrectTitle() {
        Window validWindow = TestHelper.makeWindow();
        assertTrue(validWindow.getTitle().equals("Minesweeper"));
    }
}