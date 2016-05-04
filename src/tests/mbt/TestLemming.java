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
	 * heiht<=0
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
	 * heiht<=getLevel().getHeight()
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
	
	@Test
	public void testSetWidth(){
		int height=5;
		
		assertTrue("height a tester '"+height+"' height>0 ?",
				height>0);
		
		assertTrue("height a tester '"+height+"' getLevel().getHeight() = "+
		gameEng.getLevel().getHeight()+" height<getLevel().getHeight() ?",
				height<gameEng.getLevel().getHeight());
	}
	
	/**
	 * width<=0
	 */
	@Test
	public void testSetWidth2(){
		int width=0;
		
		assertTrue("width a tester '"+width+"' height>0 ?",
				width>0);
		
		assertTrue("width a tester '"+width+"' getLevel().getWidth() = "+
		gameEng.getLevel().getWidth()+" height<getLevel().getWidth() ?",
		width<gameEng.getLevel().getWidth());
	}
	
	/**
	 * width<=getLevel().getWidth()
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
