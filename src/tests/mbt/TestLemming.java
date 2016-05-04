package tests.mbt;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import services.IGameEng;
import services.ILemming;
import services.ILevel;
import servicesImplementations.GameEng;
import servicesImplementations.Lemming;
import servicesImplementations.Level;

public class TestLemming {
	
	ILemming lemming;
	ILevel level;
	IGameEng gameEng;
	
	@Before
	public void init(){
		level=new Level();
		
		int height = 15;
		int width = 30;
		level.init(height, width);
		
		gameEng=new GameEng();
		int sizeColony=10;
		int spawnSpeed=2;
		
		gameEng.init(level, sizeColony, spawnSpeed);
		
		lemming=new Lemming();
		lemming.init(gameEng);
	}
	
	
	/**
	 * Objectif de Test: setHeight(int height) reussi
	 * 
	 * Cas de Test: gameEng.setHeight(int height)
	 * 		 0 < height < getGameEng().getLevel().getHeight()
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  setHeight(5)
	 */
	@Test
	public void testSetHeight(){
		int height=5;
		
		assertTrue("height a tester '"+height+"' height>0 ?",
				height>0);
		
		assertTrue("height a tester '"+height+"' getLevel().getHeight() = "+
		gameEng.getLevel().getHeight()+" height<getLevel().getHeight() ?",
				height<gameEng.getLevel().getHeight());
	}
	
	
	
	/**
	 * Objectif de Test: setHeight(int height) failed heiht<=0
	 * 
	 * Cas de Test: gameEng.setHeight(int height)
	 * 		 height<=0
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  setHeight(0)
	 */
	@Test
	public void testSetHeight2(){
		int height=0;
		
		assertTrue("height a tester '"+height+"' height>0 ?",
				height>0);
		
		assertTrue("height a tester '"+height+"' getLevel().getHeight() = "+
		gameEng.getLevel().getHeight()+" height<getLevel().getHeight() ?",
				height<gameEng.getLevel().getHeight());
	}
	
	
	
	/**
	 * Objectif de Test: setHeight(int height) failed height<=getLevel().getHeight()
	 * 
	 * Cas de Test: gameEng.setHeight(int height)
	 * 		height>= getLevel().getHeight()
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  setHeight(gameEng.getLevel().getHeight())
	 */
	@Test
	public void testSetHeight3(){
		int height=gameEng.getLevel().getHeight();
		
		assertTrue("height a tester '"+height+"' height>0 ?",
				height>0);
		
		assertTrue("height a tester '"+height+"' getLevel().getHeight() = "+
		gameEng.getLevel().getHeight()+" height<getLevel().getHeight() ?",
				height<gameEng.getLevel().getHeight());
	}
	
	
	/**
	 * Objectif de Test: setWidth(int width) reussi
	 * 
	 * Cas de Test: gameEng.setWidth(int width)
	 * 		 0 < width < getGameEng().getLevel().getWidth()
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  setWidth(5)
	 */
	@Test
	public void testSetWidth(){
		int width=5;
		
		assertTrue("width a tester '"+width+"' width>0 ?",
				width>0);
		
		assertTrue("width a tester '"+width+"' getLevel().getWidth() = "+
		gameEng.getLevel().getWidth()+" width<getLevel().getWidth() ?",
		width<gameEng.getLevel().getWidth());
	}
	
	
	/**
	 * Objectif de Test: setWidth(int width) failed width<= 0 
	 * 
	 * Cas de Test: gameEng.setWidth(int width)
	 * 		 width<= 0 
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  setWidth(0)
	 */
	@Test
	public void testSetWidth2(){
		int width=0;
		
		assertTrue("width a tester '"+width+"' width>0 ?",
				width>0);
		
		assertTrue("width a tester '"+width+"' getLevel().getWidth() = "+
		gameEng.getLevel().getWidth()+" height<getLevel().getWidth() ?",
		width<gameEng.getLevel().getWidth());
	}
	
	
	/**
	 * Objectif de Test: setWidth(int width) failed width >= getGameEng().getLevel().getWidth()
	 * 
	 * Cas de Test: gameEng.setWidth(int width)
	 * 		 width >= getGameEng().getLevel().getWidth()
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  setWidth(getGameEng().getLevel().getWidth())
	 */
	@Test
	public void testSetWidth3(){
		int width=gameEng.getLevel().getWidth();
		
		assertTrue("width a tester '"+width+"' width>0 ?",
				width>0);
		
		assertTrue("width a tester '"+width+"' getLevel().getWidth() = "+
		gameEng.getLevel().getWidth()+" height<getLevel().getWidth() ?",
		width<gameEng.getLevel().getWidth());
	}
}
