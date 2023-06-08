import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindowTest {
    @Test
    void windowNotNull() {
        Window validWindow = TestHelper.makeWindow();
        assertNotNull(validWindow);
    }
    @Test
    void createsValidWindowHeight() {
        Window validWindow = TestHelper.makeWindow();
        assertTrue(validWindow.getHeight() == 720);
        assertTrue(validWindow.getHeight() != 1000);
    }
    @Test
    void createsValidWindowWidth() {
        Window validWindow = TestHelper.makeWindow();
        assertTrue(validWindow.getWidth() == 720);
        assertTrue(validWindow.getWidth() != 1000);
    }
    @Test
    void createsWindowWithCorrectTitle() {
        Window validWindow = TestHelper.makeWindow();
        assertTrue(validWindow.getTitle().equals("Minesweeper"));
    }
}