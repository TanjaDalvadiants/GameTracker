package netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.Game;
import ru.netology.GameStore;
import ru.netology.Player;

import static org.junit.jupiter.api.Assertions.*;

public class GameStoreTest {

    Player player1 = new Player("Oleg");
    Player player2 = new Player("Igor");

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    // другие ваши тесты

    // Проверяем, что если мы добавили больше одной игры, все они содержатся в каталоге
    @Test
    public void shouldAddMoreThanOneGame() {

        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Аркада Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Квест Онлайн", "Квест");
        Game game3 = store.publishGame("Нетология Шутер Онлайн", "Шутер");

        assertTrue(store.containsGame(game1));
        assertTrue(store.containsGame(game2));
        assertTrue(store.containsGame(game3));
    }

    // Проверяем, что если игра не содержится GameStore, метод containsGame() вернет False
    @Test
    public void containsGameIfNotExistsTest() {

        GameStore store = new GameStore();
        Game game = new Game("Нетология Баттл Онлайн", "Аркады", store);

        assertFalse(store.containsGame(game));
    }

    // Проверяем, что метод getSumPlayedTime() возвращает верную сумму времени по двум игрокам
    @Test
    public void getSumPlayedTimeTest() {

        GameStore store = new GameStore();
        store.addPlayTime(player1.getName(), 3);
        store.addPlayTime(player2.getName(), 6);

        int expected = 9;
        int actual = store.getSumPlayedTime();
        Assertions.assertEquals(expected, actual);
    }

    // Проверяем, что метод getMostPlayer() возвращает null если нет игроков
    @Test
    public void getMostPlayerIfPlayersWereNotAddedTest() {

        GameStore store = new GameStore();

        String actual = store.getMostPlayer();
        Assertions.assertEquals(null, actual);
    }

    // Проверяем, что метод getMostPlayer() возвращает имя игрока с большим количеством часов игры
    @Test
    public void getMostPlayerTest() {

        GameStore store = new GameStore();
        store.addPlayTime(player1.getName(), 3);
        store.addPlayTime(player2.getName(), 6);

        String expected = "Igor";
        String actual = store.getMostPlayer();
        Assertions.assertEquals(expected, actual);
    }

    // Проверяем, что метод getMostPlayer() возвращает имя игрока с большим количеством часов игры, если игрок 1
    @Test
    public void getMostPlayerWithOnePlayerTest() {

        GameStore store = new GameStore();
        store.addPlayTime(player1.getName(), 3);

        String expected = "Oleg";
        String actual = store.getMostPlayer();
        Assertions.assertEquals(expected, actual);
    }

    // Проверяем, что метод getMostPlayer() возвращает имя игрока с большим количеством часов игры,
    // если количество часов игры минимальное
    @Test
    public void getMostPlayerWithLessTimeTest() {

        GameStore store = new GameStore();
        store.addPlayTime(player1.getName(), 1);

        String expected = "Oleg";
        String actual = store.getMostPlayer();
        Assertions.assertEquals(expected, actual);
    }

    // Проверяем, что метод getMostPlayer() возвращает имя игрока с большим количеством часов игры,
    // если часы добавлены не за один раз и игроков несколько

    @Test
    public void getMostPlayerMoreThanOnePlayerAndTimeAddedByPartTest() {

        GameStore store = new GameStore();
        store.addPlayTime(player1.getName(), 1);
        store.addPlayTime(player1.getName(), 3);
        store.addPlayTime(player1.getName(), 5);
        store.addPlayTime(player1.getName(), 1);
        store.addPlayTime(player2.getName(), 5);
        store.addPlayTime(player2.getName(), 1);

        String expected = "Oleg";
        String actual = store.getMostPlayer();
        Assertions.assertEquals(expected, actual);
    }

    // Проверяем, что метод getSumPlayedTime() добавляет часы в несколько приемов для одного игрока
    @Test
    public void addPlayTimeTest() {

        GameStore store = new GameStore();
        store.addPlayTime(player1.getName(), 1);
        store.addPlayTime(player1.getName(), 3);
        store.addPlayTime(player1.getName(), 5);
        store.addPlayTime(player1.getName(), 1);

        int expected = 10;
        int actual = store.getSumPlayedTime();
        Assertions.assertEquals(actual, expected);
    }
}
