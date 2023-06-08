import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void newGameShouldNotBeNull() {
        Game validNewGame = TestHelper.makeGame();
        Window validWindow = validNewGame.getWindow();
        assertNotNull(validNewGame != null);
    }
}