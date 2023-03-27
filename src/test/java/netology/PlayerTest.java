package netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.Game;
import ru.netology.GameStore;
import ru.netology.NotFoundException;
import ru.netology.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {


    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    // другие ваши тесты

    //Суммирует время всех игр игрока по названию жанра- разные игры
    @Test
    public void shouldSumGenreIfFewGames() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Баттл Онлайн2", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);

        player.installGame(game2);
        player.play(game2, 3);

        int expected = 6;
        int actual = player.sumGenre("Аркады");
        assertEquals(expected, actual);
    }

    // Суммирует общее время игры  - одна игра
    @Test
    public void shouldSumPlayHours() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);
        player.play(game1, 3);

        int expected = 6;
        int actual = player.sumGenre("Аркады");
        assertEquals(expected, actual);
    }

    //Тест, который два раза подряд устанавливает одну и ту же игру, но играет только один раз
    @Test
    public void shouldSumPlayHoursIfInstallTwoTimes() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);
        player.installGame(game1);

        int expected = 3;
        int actual = player.sumGenre("Аркады");
        assertEquals(expected, actual);
    }

    //Суммирует общее время игры во все игры, если играли меньше часа
    @Test
    public void shouldSumPlayHoursIfZero() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Цивилизация", "Стратегия");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 0);
        player.installGame(game2);
        player.play(game2, 0);

        int expected = 0;
        int actual = store.getSumPlayedTime();
        Assertions.assertEquals(expected, actual);
    }

    // Выкидывает сообщение, если игра не установлена
    @Test
    public void shouldSumPlayHoursIfGameNotFound() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Цивилизация", "Стратегия");

        Player player = new Player("Petya");
        player.installGame(game2);
        player.play(game2, 5);

        Assertions.assertThrows(NotFoundException.class,
                () -> player.play(game1, 5)
        );
    }

    // Возвращает название жанра игры, в который играли больше всего
    @Test
    public void shouldFindMostPlayerByGenre() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Баттл Онлайн2", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 4);
        player.installGame(game2);
        player.play(game2, 2);

        Game expected = game1;
        Game actual = player.mostPlayerByGenre("Аркады");
        Assertions.assertEquals(expected, actual);
    }

    // Возвращает 0 если игра не установлена у игрока
    @Test
    public void notFoungGameByGenre() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 4);

        int expected = 0;
        int actual = player.sumGenre("Стратегии");
        Assertions.assertEquals(expected, actual);
    }

    // Возвращает null, если в указанный жанр игры не играли ни разу
    @Test
    public void shouldFindMostPlayerByGenreIfNull() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 4);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Стратегии");
        Assertions.assertEquals(expected, actual);
    }
}
