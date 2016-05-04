package tests.mbt;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import enumeration.Nature;
import services.ILevel;
import servicesImplementations.Level;

public class TestLevel {

	ILevel level;
	
	@Before
	public void init(){
		level=new Level();
		
		int height = 15;
		int width = 30;
		level.init(height, width);
		
	}
	
	/**
	 * Objectif de Test: getNature(int h, int w) reussi
	 * 
	 * Cas de Test: gameEng.getNature(int h, int w)
	 * 		 0 < h < getHeight() & 0 < w < getWidth()
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  getNature(5,8)
	 */
	@Test
	public void  testGetNature(){
		int h=5;
		int w=8;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				h<=level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				w<=level.getWidth()); 
	}
	
	
	/**
	 * Objectif de Test: getNature(int h, int w) failed h<0
	 * 
	 * Cas de Test: gameEng.getNature(int h, int w)
	 * 		 h<0
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  getNature(-5,8)
	 */
	@Test
	public void  testGetNature2(){
		int h=-5;
		int w=8;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				h<=level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				w<=level.getWidth()); 
	}
	
	/**
	 * Objectif de Test: getNature(int h, int w) failed w<0
	 * 
	 * Cas de Test: gameEng.getNature(int h, int w)
	 * 		 w<0
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  getNature(5,-8)
	 */
	@Test
	public void  testGetNature3(){
		int h=5;
		int w=-8;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				h<=level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				w<=level.getWidth()); 
	}
	
	
	/**
	 * Objectif de Test: getNature(int h, int w) failed w>getWidth()
	 * 
	 * Cas de Test: gameEng.getNature(int h, int w)
	 * 		 w>getWidth()
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  getNature(5,getWidth()+1)
	 */
	@Test
	public void  testGetNature4(){
		int h=5;
		int w=level.getWidth()+1;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				h<=level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				w<=level.getWidth()); 
	}
	
	/**
	 * Objectif de Test: getNature(int h, int w) failed h>getHeight()
	 * 
	 * Cas de Test: gameEng.getNature(int h, int w)
	 * 		h>getHeight()
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  getNature(getHeight()+1,5)
	 */
	@Test
	public void  testGetNature5(){
		int h=level.getHeight()+1;
		int w=5;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				h<=level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				w<=level.getWidth()); 
	}
	
	/**
	 * Objectif de Test: init(int h, int w) reussi
	 * 
	 * Cas de Test: gameEng.init(int h, int w)
	 * 		h>=5 & w>=4
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  init(5,8)
	 */
	@Test
	public void  testInit(){
		int h=5;
		int w=8;
		
		assertTrue("h a tester '"+h+"' h>=5 ?",
				h>=5); 
		assertTrue("w a tester '"+w+"' w>=4 ?",
				w>=4); 
	}
	
	
	/**
	 * Objectif de Test: init(int h, int w) failed h<5
	 * 
	 * Cas de Test: gameEng.init(int h, int w)
	 * 		h<5
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  init(1,8)
	 */
	@Test
	public void  testInit2(){
		int h=1;
		int w=8;
		
		assertTrue("h a tester '"+h+"' h>=5 ?",
				h>=5); 
		assertTrue("w a tester '"+w+"' w>=4 ?",
				w>=4); 
	}
	
	
	/**
	 * Objectif de Test: init(int h, int w) failed w<4
	 * 
	 * Cas de Test: gameEng.init(int h, int w)
	 * 		w<4
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  init(5,3)
	 */
	@Test
	public void  testInit3(){
		int h=5;
		int w=3;
		
		assertTrue("h a tester '"+h+"' h>=5 ?",
				h>=5); 
		assertTrue("w a tester '"+w+"' w>=4 ?",
				w>=4); 
	}
	
	/**
	 * Objectif de Test: setNature(int h, int w, Nature nat) reussi
	 * 
	 * Cas de Test: gameEng.setNature(int h, int w, Nature nat)
	 * 		 0 < h < getHeight() & 0 < w < getWidth() & getEditing()
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  setNature(5,8,nat)
	 */
	@Test
	public void  testSetNature(){
		int h=5;
		int w=8;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				h<=level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				w<=level.getWidth()); 
		assertTrue("getEditing '"+level.getEditing()+"'level.getEditing() ?",
				level.getEditing()); 
	}
	
	
	/**
	 * Objectif de Test: setNature(int h, int w, Nature nat) failed h<0
	 * 
	 * Cas de Test: gameEng.setNature(int h, int w, Nature nat)
	 * 		h<0
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  setNature(-5,8,nat)
	 */
	@Test
	public void  testSetNature2(){
		int h=-5;
		int w=8;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				h<=level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				w<=level.getWidth()); 
		assertTrue("getEditing '"+level.getEditing()+"'level.getEditing() ?",
				level.getEditing()); 
	}
	
	
	/**
	 * Objectif de Test: setNature(int h, int w, Nature nat) failed w<0
	 * 
	 * Cas de Test: gameEng.setNature(int h, int w, Nature nat)
	 * 		w<0
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  setNature(5,-8,nat)
	 */
	@Test
	public void  testSetNature3(){
		int h=5;
		int w=-8;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				h<=level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				w<=level.getWidth()); 
		assertTrue("getEditing '"+level.getEditing()+"'level.getEditing() ?",
				level.getEditing()); 
	}
	
	
	/**
	 * Objectif de Test: setNature(int h, int w, Nature nat) failed w>getWidth()
	 * 
	 * Cas de Test: gameEng.setNature(int h, int w, Nature nat)
	 * 		w>getWidth()
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  setNature(5,getWidth()+1,nat)
	 */
	@Test
	public void  testSetNature4(){
		int h=5;
		int w=level.getWidth()+1;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				h<=level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				w<=level.getWidth()); 
		assertTrue("getEditing '"+level.getEditing()+"'level.getEditing() ?",
				level.getEditing()); 
	}
	
	/**
	 * Objectif de Test: setNature(int h, int w, Nature nat) failed h>getHeight()
	 * 
	 * Cas de Test: gameEng.setNature(int h, int w, Nature nat)
	 * 		h>getHeight()
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  setNature(getHeight()+1,5,nat)
	 */
	@Test
	public void  testSetNature5(){
		int h=level.getHeight()+1;
		int w=5;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				h<=level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<=getHeight() ?",
				w<=level.getWidth()); 
		assertTrue("getEditing '"+level.getEditing()+"'level.getEditing() ?",
				level.getEditing());
		assertTrue("getEditing '"+level.getEditing()+"'level.getEditing() ?",
				level.getEditing()); 
	}
	
	
	/**
	 * Objectif de Test: remove(int h, int w) reussi
	 * 
	 * Cas de Test: gameEng.remove(int h, int w)
	 * 		getNature(h, w)==Nature.EMPTY
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  remove(5,8)
	 */
	@Test
	public void  testRemove(){
		int h=5;
		int w=8;
		level.setNature(h, w, Nature.DIRT);
		
		assertTrue("getNature(h, w) '"+level.getNature(h, w)+"'"
				+ " getNature(h, w)==Nature.DIRT ?",
				level.getNature(h, w)==Nature.DIRT);
	}
	
	
	/**
	 * Objectif de Test: remove(int h, int w) failed getNature(h, w)!=Nature.DIRT
	 * 
	 * Cas de Test: gameEng.remove(int h, int w)
	 * 		getNature(h, w)!=Nature.DIRT
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  remove(5,8)
	 */
	@Test
	public void  testRemove2(){
		int h=5;
		int w=8;
		level.setNature(h, w, Nature.EMPTY);
		
		assertTrue("getNature(h, w) '"+level.getNature(h, w)+"'"
				+ " getNature(h, w)==Nature.DIRT ?",
				level.getNature(h, w)==Nature.DIRT);
	}
	
	
	/**
	 * Objectif de Test: build(int h, int w) reussi
	 * 
	 * Cas de Test: gameEng.build(int h, int w)
	 * 		getNature(h, w)==Nature.EMPTY
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  build(5,8)
	 */
	@Test
	public void  testBuild(){
		int h=5;
		int w=8;
		level.setNature(h, w, Nature.EMPTY);
		
		assertTrue("getNature(h, w) '"+level.getNature(h, w)+"'"
				+ " getNature(h, w)==Nature.EMPTY ?",
				level.getNature(h, w)==Nature.EMPTY);
	}
	
	
	/**
	 * Objectif de Test: build(int h, int w) failed getNature(h, w)!=Nature.EMPTY
	 * 
	 * Cas de Test: gameEng.build(int h, int w)
	 * 		getNature(h, w)!=Nature.EMPTY
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  build(5,8)
	 */
	@Test
	public void  testBuild2(){
		int h=5;
		int w=8;
		level.setNature(h, w, Nature.DIRT);
		
		assertTrue("getNature(h, w) '"+level.getNature(h, w)+"'"
				+ " getNature(h, w)==Nature.EMPTY ?",
				level.getNature(h, w)==Nature.EMPTY);
	}
	
	/**
	 * Objectif de Test: addStopper(int h, int w) reussi
	 * 
	 * Cas de Test: gameEng.addStopper(int h, int w)
	 * 		getNature(h, w)==Nature.EMPTY
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  addStopper(5,8)
	 */
	@Test
	public void  testAddStopper(){
		int h=5;
		int w=8;
		level.setNature(h, w, Nature.EMPTY);
		
		assertTrue("getNature(h, w) '"+level.getNature(h, w)+"'"
				+ " getNature(h, w)==Nature.EMPTY ?",
				level.getNature(h, w)==Nature.EMPTY);
	}
	
	/**
	 * Objectif de Test: addStopper(int h, int w) failed getNature(h, w)!=Nature.EMPTY
	 * 
	 * Cas de Test: gameEng.addStopper(int h, int w)
	 * 		getNature(h, w)!=Nature.EMPTY
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  addStopper(5,8)
	 */
	@Test
	public void  testAddStopper2(){
		int h=5;
		int w=8;
		level.setNature(h, w, Nature.METAL);
		
		assertTrue("getNature(h, w) '"+level.getNature(h, w)+"'"
				+ " getNature(h, w)==Nature.EMPTY ?",
				level.getNature(h, w)==Nature.EMPTY);
	}
	
	
	/**
	 * Objectif de Test: removeStopper(int h, int w) reussi
	 * 
	 * Cas de Test: gameEng.removeStopper(int h, int w)
	 * 		getNature(h, w)==Nature.STOPPER
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  removeStopper(5,8)
	 */
	@Test
	public void testRemoveStopper(){
		int h=5;
		int w=8;
		level.setNature(h, w, Nature.STOPPER);
		
		assertTrue("getNature(h, w) '"+level.getNature(h, w)+"'"
				+ " getNature(h, w)==Nature.STOPPER ?",
				level.getNature(h, w)==Nature.STOPPER);
	}
	
	/**
	 * Objectif de Test: removeStopper(int h, int w) failed getNature(h, w)!=Nature.STOPPER
	 * 
	 * Cas de Test: gameEng.removeStopper(int h, int w)
	 * 		getNature(h, w)!=Nature.STOPPER
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  removeStopper(5,8)
	 */
	@Test
	public void  testRemoveStopper2(){
		int h=5;
		int w=8;
		level.setNature(h, w, Nature.EMPTY);
		
		assertTrue("getNature(h, w) '"+level.getNature(h, w)+"'"
				+ " getNature(h, w)==Nature.STOPPER ?",
				level.getNature(h, w)==Nature.STOPPER);
	}
	
	
	/**
	 * Objectif de Test: addEnter(int h, int w) reussi
	 * 
	 * Cas de Test: gameEng.addEnter(int h, int w)
	 * 		h>0 & w>0 & h< getHeight() & w< getWidth()
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  addEnter(5,8)
	 */
	@Test
	public void  TestAddEnter(){
		int h=5;
		int w=8;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				h<level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				w<level.getWidth()); 
	}
	
	
	/**
	 * Objectif de Test: addEnter(int h, int w) failed h<0
	 * 
	 * Cas de Test: gameEng.addEnter(int h, int w)
	 * 	h<0
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  addEnter(-5,8)
	 */
	@Test
	public void  TestAddEnter2(){
		int h=-5;
		int w=8;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				h<level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				w<level.getWidth()); 
	}
	
	/**
	 * Objectif de Test: addEnter(int h, int w) failed w<0
	 * 
	 * Cas de Test: gameEng.addEnter(int h, int w)
	 * 		w<0
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  addEnter(5,-8)
	 */
	@Test
	public void  TestAddEnter3(){
		int h=5;
		int w=-8;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				h<level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				w<level.getWidth()); 
	}
	
	
	/**
	 * Objectif de Test: addEnter(int h, int w) failed w>=getWidth()
	 * 
	 * Cas de Test: gameEng.addEnter(int h, int w)
	 * 		w>=getWidth()
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  addEnter(5,getWidth())
	 */
	@Test
	public void  TestAddEnter4(){
		int h=5;
		int w=level.getWidth();
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				h<level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				w<level.getWidth()); 
	}
	
	/**
	 * Objectif de Test: addEnter(int h, int w) failed h>=getHeight()
	 * 
	 * Cas de Test: gameEng.addEnter(int h, int w)
	 * 		h>=getHeight()
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  addEnter(getHeight(),5)
	 */
	@Test
	public void  TestAddEnter5(){
		int h=level.getHeight();
		int w=5;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				h<level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				w<level.getWidth()); 
	}
	
	/**
	 * Objectif de Test: addExit(int h, int w) reussi
	 * 
	 * Cas de Test: gameEng.addExit(int h, int w)
	 * 		h>0 & w>0 & h< getHeight() & w< getWidth()
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  addExit(5,8)
	 */
	@Test
	public void  TestAddExit(){
		int h=5;
		int w=8;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				h<level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				w<level.getWidth()); 
	}
	
	/**
	 * Objectif de Test: addExit(int h, int w) failed h<0
	 * 
	 * Cas de Test: gameEng.addExit(int h, int w)
	 * 	h<0
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  addExit(-5,8)
	 */
	@Test
	public void  TestAddExit2(){
		int h=-5;
		int w=8;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				h<level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				w<level.getWidth()); 
	}
	
	/**
	 * Objectif de Test: addExit(int h, int w) failed w<0
	 * 
	 * Cas de Test: gameEng.addExit(int h, int w)
	 * 		w<0
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  addExit(5,-8)
	 */
	@Test
	public void  TestAddExit3(){
		int h=5;
		int w=-8;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				h<level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				w<level.getWidth()); 
	}
	
	/**
	 * Objectif de Test: addExit(int h, int w) failed w>=getWidth()
	 * 
	 * Cas de Test: gameEng.addExit(int h, int w)
	 * 		w>=getWidth()
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  addExit(5,getWidth())
	 */
	@Test
	public void  TestAddExit4(){
		int h=5;
		int w=level.getWidth();
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				h<level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				w<level.getWidth()); 
	}
	
	
	/**
	 * Objectif de Test: addExit(int h, int w) failed h>=getHeight()
	 * 
	 * Cas de Test: gameEng.addExit(int h, int w)
	 * 		h>=getHeight()
	 * 
	 * Condition initiale:
	 * !getEditing()
	 * 
	 * Operation:
	 *  addExit(getHeight(),5)
	 */
	@Test
	public void  TestAddExit5(){
		int h=level.getHeight();
		int w=5;
		
		assertTrue("h a tester '"+h+"' h>0 ?",
				h>0); 
		assertTrue("w a tester '"+w+"' w>0 ?",
				w>0);
		assertTrue("h a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				h<level.getHeight()); 
		assertTrue("w a tester '"+h+"' getWidth() = "+level.getWidth()+""
				+ "h<getHeight() ?",
				w<level.getWidth()); 
	}
	
}
