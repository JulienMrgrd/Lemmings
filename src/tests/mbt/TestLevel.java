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
	 * h<0
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
	 * w<0
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
	 * w>getWidth()
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
	 * h>getHeight()
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
	 * h<5
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
	 * w<4
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
	 * h<0
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
	 * w<0
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
	 * w>getWidth()
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
	 * h>getHeight()
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
	 * h!=Nature.DIRT
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
	 * h!=Nature.EMPTY
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
	 * h!=Nature.EMPTY
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
	
	@Test
	public void  testRemoveStopper(){
		int h=5;
		int w=8;
		level.setNature(h, w, Nature.STOPPER);
		
		assertTrue("getNature(h, w) '"+level.getNature(h, w)+"'"
				+ " getNature(h, w)==Nature.STOPPER ?",
				level.getNature(h, w)==Nature.STOPPER);
	}
	
	/**
	 * h!=Nature.EMPTY
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
	 * h<0
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
	 * w<0
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
	 * w>=getWidth()
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
	 * h>=getHeight()
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
	 * h<0
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
	 * w<0
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
	 * w>=getWidth()
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
	 * h>=getHeight()
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
