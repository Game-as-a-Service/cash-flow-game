package tw.waterball.cashflow.usecase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OuterCircleTest {

        @Test
        public void givenPlayerWithPassiveIncomeGreaterThanExpenses_whenIsInOuterCircle_thenReturnsTrue() {
            Player player = new Player();
            player.setPassiveIncome(200);
            player.setExpenses(100);
            assertTrue(player.isInOuterCircle());
        }

        @Test
        public void givenPlayerWithPassiveIncomeLessThanExpenses_whenIsInOuterCircle_thenReturnsFalse() {
            Player player = new Player();
            player.setPassiveIncome(100);
            player.setExpenses(200);
            assertFalse(player.isInOuterCircle());
        }

        @Test
        public void givenPlayerWithNoPassiveIncome_whenIsInOuterCircle_thenReturnsFalse() {
            Player player = new Player();
            player.setPassiveIncome(0);
            player.setExpenses(100);
            assertFalse(player.isInOuterCircle());
        }

        @Test
        public void givenPlayerWithNoExpenses_whenIsInOuterCircle_thenReturnsTrue() {
            Player player = new Player();
            player.setPassiveIncome(200);
            player.setExpenses(0);
            assertTrue(player.isInOuterCircle());
        }

        @Test
        public void givenPlayerWithEqualPassiveIncomeAndExpenses_whenIsInOuterCircle_thenReturnsFalse() {
            Player player = new Player();
            player.setPassiveIncome(100);
            player.setExpenses(100);
            assertFalse(player.isInOuterCircle());
        }
    }

