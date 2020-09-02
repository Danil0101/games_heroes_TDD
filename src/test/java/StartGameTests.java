import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StartGameTest {

    private StartGame startGame;

    @Before
    public void setUp() {
        startGame = new StartGame();
        startGame.createSquads();
    }

    @Test
    public void shouldBeCreatedTwoSquadsOfOppositeFactions() {
        Fraction expectedFraction;

        if (startGame.getSquad(1).getFraction() == Fraction.FRACTION1) {
            Fraction expectedFraction = Fraction.FRACTION2;
        } else {
            Fraction expectedFraction = Fraction.FRACTION1;
        }
        assertEquals(expectedFraction, startGame.getSquad(2).getFraction());
    }

    @Test
    public void theSquadShouldHaveEightCharacters() {
        assertEquals(8, startGame.getSquad(1).size());
        assertEquals(8, startGame.getSquad(2).size());
    }

    @Test
    public void sortingSquadByPrivileges() {
        Squad squad = startGame.getSquad(1);
        squad.getCharacter(7).setPrivileged();
        squad.sortByPrivileges();
        assertTrue(squad.getCharacter(0).isPrivileged());
    }

    @Test
    public void oneSquadShouldRemainAfterTheBattle() {
        startGame.startFight();
        if (startGame.getSquad(1).size() > 0) {
            assertEquals(0, startGame.getSquad(2).size());
        } else {
            assertEquals(0, startGame.getSquad(1).size());
        }
    }

    @Test
    public void checkingLogWritingToFile() {
        startGame.startFight();
        Log.saveLogToDisk();
        assertEquals(Log.getLog(), Log.getFromFile);
    }

}
