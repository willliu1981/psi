package test1;

import java.util.ArrayList;
import java.util.List;

import idv.fc.model.Card;
import idv.kwl.handle.cardhandler.DrawCardHandler;
import idv.kwl.handle.cardhandler.drawcardcase.DrawCardCase;

public class Test2 {

	public static void main(String[] args) {
		List<Card> reviews = new ArrayList<>();
		Card c=new Card();
		c.setId(12);
		reviews.add(c);
		
		Card c2=new Card();
		c2.setId(12);
		System.out.println(reviews.contains(c2));
		
		DrawCardHandler dch=new DrawCardHandler(new DrawCardCase(new ArrayList<>()));
		//dch.addReview(c);
		//dch.addReview(c2);
		
		Card c3=new Card();
		c2.setId(2);
		
		//dch.addReview(c3);
		
		//System.out.println(dch.getTest());
		

	}

}