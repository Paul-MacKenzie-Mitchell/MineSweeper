//import org.junit.Before;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//class GameTest {
//    Game game = TestHelper.makeGame();
//    Grid mockGrid = Mockito.mock(Grid.class);
//    @BeforeEach
//    void setup() {
//        game.runGame(game);
//        when(mockGrid.getBound())
//                .thenReturn((game.getGridSize() * game.getGridSize()));
//    }
//    @AfterEach
//    void reset() {
//        mockGrid.cellGrid.clear();
//    }
//    @Test
//    void newGameShouldNotBeNull() {
//        assertNotNull(game);
//    }
//    @Test
//    void newGameWindowShouldNotBeNull() {
//        Window window = TestHelper.makeWindow();
//        assertNotNull(game.getWindow());
//    }
//    @Test
//    void newGameHandlerShouldNotBeNull() {
//        Handler handler = new Handler();
//        assertNotNull(game.getHandler());
//    }
//    @Test
//    void getHandlerShouldReturnHandler() {
//        assertEquals(Handler.class, game.getHandler().getClass());
//    }
//    @Test
//    void setHandlerShouldReturnCorrectHandler() {
//        Handler expected = new Handler();
//        expected.setFlaggedCells(9);
//        game.setHandler(expected);
//        assertEquals(9, game.getHandler().getFlaggedCells());
//        assertTrue(expected.equals(game.getHandler()));
//    }
////    @Test
////    void getWindowShouldReturnExpectedWindow() {
////        Game game = TestHelper.makeGame();
////        Window expected = TestHelper.makeWindow();
////        assertTrue(expected.equals(game.getWindow()));
////        System.out.println(game.getWindow().getHeight());
////        System.out.println(expected.getHeight());
////    }
//
//}