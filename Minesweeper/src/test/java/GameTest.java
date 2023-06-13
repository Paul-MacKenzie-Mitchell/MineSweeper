import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GameTest {
    private Game gameMock;
//    private Game gameMock = Mockito.mock(Game.class);
    @BeforeEach
    void setup() {
        gameMock = Mockito.mock(Game.class);
    }
    @Test
    void newGameShouldNotBeNull() {
        assertNotNull(gameMock);
    }
    @Test
    void newGameWindowShouldNotBeNull() {
        Window window = TestHelper.makeWindow();
        when(gameMock.getWindow())
                .thenReturn(window);
        assertNotNull(gameMock.getWindow());
    }
    @Test
    void newGameHandlerShouldNotBeNull() {
        Handler handler = new Handler();
        when(gameMock.getHandler())
                .thenReturn(handler);
        assertNotNull(gameMock.getHandler());
    }
}