package pokerBase;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pokerEnums.eSuit;
import pokerEnums.eRank;
import pokerEnums.eHandStrength;

import pokerBase.Card;

public class Hand_Test {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFiveOfAKind() {
		ArrayList<Card> TestHand = new ArrayList<Card>();

		Card c1 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c1);

		Card c2 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c2);

		Card c3 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c3);

		Card c4 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c4);

		Card c5 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c5);

		Hand THand1 = new Hand(TestHand);

		THand1.EvalHand();

		assertTrue(THand1.getHandStrength() == eHandStrength.FiveOfAKind.getHandStrength());

	}

	@Test
	public void testRoyalFlush(){
		ArrayList<Card> TestHand = new ArrayList<Card>();

		Card c1 = new Card(eSuit.HEARTS, eRank.ACE, 1);
		TestHand.add(c1);

		Card c2 = new Card(eSuit.HEARTS, eRank.QUEEN, 1);
		TestHand.add(c2);

		Card c3 = new Card(eSuit.HEARTS, eRank.KING, 1);
		TestHand.add(c3);

		Card c4 = new Card(eSuit.HEARTS, eRank.JACK, 1);
		TestHand.add(c4);

		Card c5 = new Card(eSuit.HEARTS, eRank.TEN, 1);
		TestHand.add(c5);

		Hand THand1 = new Hand(TestHand);

		THand1.EvalHand();

		assertTrue(THand1.getHandStrength() == eHandStrength.RoyalFlush.getHandStrength());

	}

	@Test
	public void testStraightFlush(){
		ArrayList<Card> TestHand = new ArrayList<Card>();

		Card c1 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c1);

		Card c2 = new Card(eSuit.HEARTS, eRank.THREE, 1);
		TestHand.add(c2);

		Card c3 = new Card(eSuit.HEARTS, eRank.FOUR, 1);
		TestHand.add(c3);

		Card c4 = new Card(eSuit.HEARTS, eRank.FIVE, 1);
		TestHand.add(c4);

		Card c5 = new Card(eSuit.HEARTS, eRank.SIX, 1);
		TestHand.add(c5);

		Hand THand1 = new Hand(TestHand);

		THand1.EvalHand();

		assertTrue(THand1.getHandStrength() == eHandStrength.StraightFlush.getHandStrength());

	}

	@Test
	public void testFourOfAKind(){
		ArrayList<Card> TestHand = new ArrayList<Card>();

		Card c1 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c1);

		Card c2 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c2);

		Card c3 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c3);

		Card c4 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c4);

		Card c5 = new Card(eSuit.HEARTS, eRank.THREE, 1);
		TestHand.add(c5);

		Hand THand1 = new Hand(TestHand);

		THand1.EvalHand();

		assertTrue(THand1.getHandStrength() == eHandStrength.FourOfAKind.getHandStrength());

	}

	@Test
	public void testFullHouse(){
		ArrayList<Card> TestHand = new ArrayList<Card>();

		Card c1 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c1);

		Card c2 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c2);

		Card c3 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c3);

		Card c4 = new Card(eSuit.HEARTS, eRank.TEN, 1);
		TestHand.add(c4);

		Card c5 = new Card(eSuit.HEARTS, eRank.TEN, 1);
		TestHand.add(c5);

		Hand THand1 = new Hand(TestHand);

		THand1.EvalHand();

		assertTrue(THand1.getHandStrength() == eHandStrength.FullHouse.getHandStrength());

	}

	@Test
	public void testFlush(){
		ArrayList<Card> TestHand = new ArrayList<Card>();

		Card c1 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c1);

		Card c2 = new Card(eSuit.HEARTS, eRank.TEN, 1);
		TestHand.add(c2);

		Card c3 = new Card(eSuit.HEARTS, eRank.ACE, 1);
		TestHand.add(c3);

		Card c4 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c4);

		Card c5 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c5);

		Hand THand1 = new Hand(TestHand);

		THand1.EvalHand();

		assertTrue(THand1.getHandStrength() == eHandStrength.Flush.getHandStrength());

	}

	@Test
	public void testStraight(){
		ArrayList<Card> TestHand = new ArrayList<Card>();

		Card c1 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c1);

		Card c2 = new Card(eSuit.CLUBS, eRank.THREE, 1);
		TestHand.add(c2);

		Card c3 = new Card(eSuit.HEARTS, eRank.FOUR, 1);
		TestHand.add(c3);

		Card c4 = new Card(eSuit.HEARTS, eRank.FIVE, 1);
		TestHand.add(c4);

		Card c5 = new Card(eSuit.HEARTS, eRank.SIX, 1);
		TestHand.add(c5);

		Hand THand1 = new Hand(TestHand);

		THand1.EvalHand();

		assertTrue(THand1.getHandStrength() == eHandStrength.Straight.getHandStrength());

	}

	@Test
	public void testThreeOfAKind(){
		ArrayList<Card> TestHand = new ArrayList<Card>();

		Card c1 = new Card(eSuit.CLUBS, eRank.TWO, 1);
		TestHand.add(c1);

		Card c2 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c2);

		Card c3 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c3);

		Card c4 = new Card(eSuit.HEARTS, eRank.TEN, 1);
		TestHand.add(c4);

		Card c5 = new Card(eSuit.HEARTS, eRank.JACK, 1);
		TestHand.add(c5);

		Hand THand1 = new Hand(TestHand);

		THand1.EvalHand();

		assertTrue(THand1.getHandStrength() == eHandStrength.ThreeOfAKind.getHandStrength());

	}

	@Test
	public void testTwoPair(){
		ArrayList<Card> TestHand = new ArrayList<Card>();

		Card c1 = new Card(eSuit.CLUBS, eRank.TWO, 1);
		TestHand.add(c1);

		Card c2 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c2);

		Card c3 = new Card(eSuit.HEARTS, eRank.THREE, 1);
		TestHand.add(c3);

		Card c4 = new Card(eSuit.HEARTS, eRank.THREE, 1);
		TestHand.add(c4);

		Card c5 = new Card(eSuit.HEARTS, eRank.FOUR, 1);
		TestHand.add(c5);

		Hand THand1 = new Hand(TestHand);

		THand1.EvalHand();

		assertTrue(THand1.getHandStrength() == eHandStrength.TwoPair.getHandStrength());

	}

	@Test
	public void testPair(){
		ArrayList<Card> TestHand = new ArrayList<Card>();

		Card c1 = new Card(eSuit.CLUBS, eRank.TWO, 1);
		TestHand.add(c1);

		Card c2 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c2);

		Card c3 = new Card(eSuit.HEARTS, eRank.THREE, 1);
		TestHand.add(c3);

		Card c4 = new Card(eSuit.HEARTS, eRank.FOUR, 1);
		TestHand.add(c4);

		Card c5 = new Card(eSuit.HEARTS, eRank.SEVEN, 1);
		TestHand.add(c5);

		Hand THand1 = new Hand(TestHand);

		THand1.EvalHand();

		assertTrue(THand1.getHandStrength() == eHandStrength.Pair.getHandStrength());

	}

	@Test
	public void testHighCard(){
		ArrayList<Card> TestHand = new ArrayList<Card>();

		Card c1 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c1);

		Card c2 = new Card(eSuit.HEARTS, eRank.THREE, 1);
		TestHand.add(c2);

		Card c3 = new Card(eSuit.HEARTS, eRank.SEVEN, 1);
		TestHand.add(c3);

		Card c4 = new Card(eSuit.HEARTS, eRank.ACE, 1);
		TestHand.add(c4);

		Card c5 = new Card(eSuit.DIAMONDS, eRank.KING, 1);
		TestHand.add(c5);

		Hand THand1 = new Hand(TestHand);

		THand1.EvalHand();

		assertTrue(THand1.getHandStrength() == eHandStrength.HighCard.getHandStrength());

	}
	
	@Test
	public void testCompare(){
		ArrayList<Hand> Hands = new ArrayList<Hand>();
		
		ArrayList<Card> TestHand = new ArrayList<Card>();

		Card c1 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c1);

		Card c2 = new Card(eSuit.HEARTS, eRank.THREE, 1);
		TestHand.add(c2);

		Card c3 = new Card(eSuit.HEARTS, eRank.SEVEN, 1);
		TestHand.add(c3);

		Card c4 = new Card(eSuit.HEARTS, eRank.ACE, 1);
		TestHand.add(c4);

		Card c5 = new Card(eSuit.HEARTS, eRank.KING, 1);
		TestHand.add(c5);

		Hand THand1 = new Hand(TestHand);

		THand1.EvalHand();
		
		Hands.add(THand1);

		c1 = new Card(eSuit.HEARTS, eRank.TWO, 1);
		TestHand.add(c1);

		c2 = new Card(eSuit.HEARTS, eRank.THREE, 1);
		TestHand.add(c2);

		c3 = new Card(eSuit.HEARTS, eRank.ACE, 1);
		TestHand.add(c3);

		c4 = new Card(eSuit.HEARTS, eRank.ACE, 1);
		TestHand.add(c4);

		c5 = new Card(eSuit.DIAMONDS, eRank.ACE, 1);
		TestHand.add(c5);

		Hand THand2 = new Hand(TestHand);

		THand2.EvalHand();
		
		Hands.add(THand2);
		
		Collections.sort(Hands, Hand.HandRank);
		
		assertTrue(Hands.get(1) == THand1);
	}




}
