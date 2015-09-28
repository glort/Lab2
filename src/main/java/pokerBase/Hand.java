package pokerBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;

import pokerEnums.eCardNo;
import pokerEnums.eHandStrength;
import pokerEnums.eRank;

public class Hand {
	private UUID playerID;
	@XmlElement
	private ArrayList<Card> CardsInHand;
	private ArrayList<Card> BestCardsInHand;
	private ArrayList<Card> Kickers = new ArrayList<Card>();
	

	@XmlElement
	private int HandStrength;
	@XmlElement
	private int HiHand;
	@XmlElement
	private int LoHand;


	private boolean bScored = false;

	private boolean Flush;
	private boolean Straight;
	private boolean Ace;
	private static Deck dJoker = new Deck();

	public Hand()
	{
		
	}
	
	public ArrayList<Card> getKickers(){
		return this.Kickers;
	}
	
	public void  AddCardToHand(Card c)
	{
		if (this.CardsInHand == null)
		{
			CardsInHand = new ArrayList<Card>();
		}
		this.CardsInHand.add(c);
	}
	
	public Card  GetCardFromHand(int location)
	{
		return CardsInHand.get(location);
	}
	
	public Hand(Deck d) {
		ArrayList<Card> Import = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			Import.add(d.drawFromDeck());
		}
		CardsInHand = Import;


	}


	public Hand(ArrayList<Card> setCards) {
		this.CardsInHand = setCards;
	}

	public ArrayList<Card> getCards() {
		return CardsInHand;
	}

	public ArrayList<Card> getBestHand() {
		return BestCardsInHand;
	}

	public void setPlayerID(UUID playerID)
	{
		this.playerID = playerID;
	}
	public UUID getPlayerID()
	{
		return playerID;
	}
	public void setBestHand(ArrayList<Card> BestHand) {
		this.BestCardsInHand = BestHand;
	}

	public int getHandStrength() {
		return HandStrength;
	}


	public int getHighPairStrength() {
		return HiHand;
	}

	public int getLowPairStrength() {
		return LoHand;
	}

	public boolean getAce() {
		return Ace;
	}

	public static Hand EvalHand(ArrayList<Card> SeededHand) {
		
		Deck d = new Deck();
		Hand h = new Hand(d);
		h.CardsInHand = SeededHand;

		return h;
	}

	public void EvalHand() {
		// Evaluates if the hand is a flush and/or straight then figures out
		// the hand's strength attributes

		// Sort the cards!
		Collections.sort(CardsInHand, Card.CardRank);
		
		boolean fiveOfAKind = false;
		boolean straightFlush = false;

		// Ace Evaluation
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == eRank.ACE) {
			Ace = true;
		}

		// Flush Evaluation
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
				.get(eCardNo.SecondCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getSuit()) {
			Flush = true;
		} else {
			Flush = false;
		}

		// five of a Kind

		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == CardsInHand
				.get(eCardNo.FifthCard.getCardNo()).getRank().getRank()) {
			ScoreHand(eHandStrength.FiveOfAKind,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0);
			fiveOfAKind = true;
		}

		// Straight Evaluation
		else if (Ace) {
			// Looks for Ace, King, Queen, Jack, 10
			if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == eRank.KING
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.QUEEN
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo())
							.getRank() == eRank.JACK
					&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN) {
				Straight = true;
				// Looks for Ace, 2, 3, 4, 5
			} else if (CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TWO
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo())
							.getRank() == eRank.THREE
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.FOUR
					&& CardsInHand.get(eCardNo.SecondCard.getCardNo())
							.getRank() == eRank.FIVE) {
				Straight = true;
			} else {
				Straight = false;
			}
			// Looks for straight without Ace
		} 
		
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
				.getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo())
				.getRank().getRank() + 1
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank() + 2
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.FourthCard.getCardNo()).getRank()
						.getRank() + 3
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank() == CardsInHand
						.get(eCardNo.FifthCard.getCardNo()).getRank().getRank() + 4) {
			Straight = true;
		} 
		
		else {
			Straight = false;
		}

		// Evaluate Royal Flush
		if (Straight == true
				&& Flush == true
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN
				&& Ace) {
			ScoreHand(eHandStrength.RoyalFlush, 0, 0);
			straightFlush = true;
		}

		// Straight Flush
		else if (Straight == true && Flush == true) {
			ScoreHand(eHandStrength.StraightFlush,
					CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
							.getRank(), 0);
			straightFlush = true;
		}
		// Four of a Kind

		//TODO: You need to build the logic to figure out Four of a kind
		if (!fiveOfAKind && !straightFlush){
			if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == 
					CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() && !fiveOfAKind){
				ScoreHand(eHandStrength.FourOfAKind, CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(),
						0);
				this.Kickers.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			}
			else if (CardsInHand.get
					(eCardNo.SecondCard.getCardNo()).getRank() == 
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() && !fiveOfAKind){
				ScoreHand(eHandStrength.FourOfAKind, CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(), 0);
				this.Kickers.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			}

			// Full House
			//TODO: You need to build the logic to figure out Full House
			else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == 
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()&&CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == 
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()){
				ScoreHand(eHandStrength.FullHouse, CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(),
						CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank());
			}
			else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == 
					CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()&&CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == 
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()){
				ScoreHand(eHandStrength.FullHouse, CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(),
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank());
			}

			// Flush
			//TODO: You need to build the logic to figure out Flush
			else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getSuit() &&
					CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getSuit() == CardsInHand.get(eCardNo.FourthCard.getCardNo()).getSuit() && 
					CardsInHand.get(eCardNo.FifthCard.getCardNo()).getSuit() == CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit())
			{
				ScoreHand(eHandStrength.Flush, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(),
						CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank());

			}

			// Straight
			//TODO: You need to build the logic to figure out Straight
			else if (Straight == true)
			{
				ScoreHand(eHandStrength.Straight,
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(), 0);
			}

			// Three of a Kind
			//TODO: You need to build the logic to figure out Three of a Kind
			else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo())
					.getRank().getRank()){
				ScoreHand(eHandStrength.ThreeOfAKind, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(), 0);
				this.Kickers.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
				this.Kickers.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));

			}

			else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo())
					.getRank().getRank())
			{
				ScoreHand(eHandStrength.ThreeOfAKind, CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
						.getRank(), 0);

				this.Kickers.add(CardsInHand.get(0));
				this.Kickers.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));

			}

			else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo())
					.getRank().getRank())
			{

				ScoreHand(eHandStrength.ThreeOfAKind, CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()
						.getRank(), 0);
				this.Kickers.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
				this.Kickers.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));

			}

			// Two Pair
			//TODO: You need to build the logic to figure out Two Pair
			else if

			(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo())
					.getRank().getRank()
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo())
					.getRank().getRank())
			{
				ScoreHand(eHandStrength.TwoPair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(), CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
						.getRank());
				this.Kickers.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			}
			else if 
			(CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo())
					.getRank().getRank()
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo())
					.getRank().getRank())
			{
				ScoreHand(eHandStrength.TwoPair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(), CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
						.getRank());
				this.Kickers.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
			}
			else if

			(CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo())
					.getRank().getRank()
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo())
					.getRank().getRank())
			{
				ScoreHand(eHandStrength.TwoPair, CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
						.getRank(), CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
						.getRank());
				this.Kickers.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
			}



			// Pair
			//TODO: You need to build the logic to figure out Pair
			else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo())
					.getRank().getRank())
			{
				ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(), 0);
				this.Kickers.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
				this.Kickers.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
				this.Kickers.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			}
			else if

			(CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo())
					.getRank().getRank())
			{
				ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
						.getRank(), 0);
				this.Kickers.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
				this.Kickers.add(CardsInHand.get(eCardNo.FourthCard.getCardNo()));
				this.Kickers.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			}

			else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo())
					.getRank().getRank())
			{
				ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
						.getRank(), 0);
				this.Kickers.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
				this.Kickers.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
				this.Kickers.add(CardsInHand.get(eCardNo.FifthCard.getCardNo()));
			}
			else if (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
					.getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo())
					.getRank().getRank())
			{
				ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
						.getRank(), 0 );
				this.Kickers.add(CardsInHand.get(eCardNo.FirstCard.getCardNo()));
				this.Kickers.add(CardsInHand.get(eCardNo.SecondCard.getCardNo()));
				this.Kickers.add(CardsInHand.get(eCardNo.ThirdCard.getCardNo()));
			}

			// High Card
			//	I'll give you this one :)
			else {
				ScoreHand(eHandStrength.HighCard,
						CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank()
						.getRank(), 0);
			}
		}
	}



	private void ScoreHand(eHandStrength hST, int HiHand, int LoHand) {
		this.HandStrength = hST.getHandStrength();
		this.HiHand = HiHand;
		this.LoHand = LoHand;
		this.bScored = true;

	}

	/**
	 * Custom sort to figure the best hand in an array of hands
	 */
	public static Comparator<Hand> HandRank = new Comparator<Hand>() {

		public int compare(Hand h1, Hand h2) {

			int result = 0;

			result = h2.getHandStrength() - h1.getHandStrength();

			if (result != 0) {
				return result;
			}

			result = h2.getHighPairStrength() - h1.getHighPairStrength();
			if (result != 0) {
				return result;
			}

			result = h2.getLowPairStrength() - h1.getLowPairStrength();
			if (result != 0) {
				return result;
			}
			
			
			if (h2.getKickers().get(0)!=null){
				result = h2.getKickers().get(0).getRank().getRank() - h1.getKickers().get(0).getRank().getRank();
				if (result != 0) {
					return result;
				}
			}
			
			if ( h2.getKickers().get(1) != null){
				result = h2.getKickers().get(1).getRank().getRank() - h1.getKickers().get(1).getRank().getRank();
				if (result != 0) {
					return result;
				}
			}
			
			if (h2.getKickers().get(2)!=null){
				result = h2.getKickers().get(2).getRank().getRank() - h1.getKickers().get(2).getRank().getRank();
				if (result != 0) {
					return result;
				}
			}
			
			if (h2.getKickers().get(3) != null){
				result = h2.getKickers().get(3).getRank().getRank() - h1.getKickers().get(3).getRank().getRank();
				if (result != 0) {
					return result;
				}
			}

			return 0;
		}
	};
}
