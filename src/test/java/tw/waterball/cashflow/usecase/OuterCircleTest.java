package tw.waterball.cashflow.usecase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OuterCircleTest {

        @Test
        public void givenPlayerWithPassiveIncomeGreaterThanExpenses_whenIsInOuterCircle_thenReturnsTrue() {
            Actor actor = new Actor();
            actor.setPassiveIncome(200);
            actor.setExpenses(100);
            assertTrue(actor.isInOuterCircle());
        }

        @Test
        public void givenPlayerWithPassiveIncomeLessThanExpenses_whenIsInOuterCircle_thenReturnsFalse() {
            Actor actor = new Actor();
            actor.setPassiveIncome(100);
            actor.setExpenses(200);
            assertFalse(actor.isInOuterCircle());
        }

        @Test
        public void givenPlayerWithNoPassiveIncome_whenIsInOuterCircle_thenReturnsFalse() {
            Actor actor = new Actor();
            actor.setPassiveIncome(0);
            actor.setExpenses(100);
            assertFalse(actor.isInOuterCircle());
        }

        @Test
        public void givenPlayerWithNoExpenses_whenIsInOuterCircle_thenReturnsTrue() {
            Actor actor = new Actor();
            actor.setPassiveIncome(200);
            actor.setExpenses(0);
            assertTrue(actor.isInOuterCircle());
        }

        @Test
        public void givenPlayerWithEqualPassiveIncomeAndExpenses_whenIsInOuterCircle_thenReturnsFalse() {
            Actor actor = new Actor();
            actor.setPassiveIncome(100);
            actor.setExpenses(100);
            assertTrue(actor.isInOuterCircle());
        }
    }

