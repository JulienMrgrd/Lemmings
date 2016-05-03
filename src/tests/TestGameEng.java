package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import contrat.GameEngContrat;
import contrat.LevelContrat;
import errors.PreConditionError;
import services.IGameEng;
import services.ILevel;
import servicesImplementations.Lemming;

public class TestGameEng {

	ILevel level;
	IGameEng gameEng;
	
	private void resetGameEng(){
		gameEng=new GameEngContrat();
	}
	
	@Before
	public void init(){
		level=new LevelContrat();
		
		int height = 15;
		int width = 30;
		level.init(height, width);
		
		gameEng=new GameEngContrat();
		int sizeColony=10;
		int spawnSpeed=2;
		
		gameEng.init(level, sizeColony, spawnSpeed);
		
	}
	
	/**
	 * Objectif de Test: getLemVivantById(int id) reussit
	 * 
	 * Cas de Test: gameEng.GetLemVivantById(int id)
	 * 		 0 <= id <sizeColony && containsIdColony(id)
	 * 
	 * Condition initial:
	 * 
	 * Operation:
	 *  getLemVivantById(5)
	 *  
	 */
	@Test
	public void testGetLemVivantById(){
		for(int i=0; i<gameEng.getSizeColony();i++){ //ON Ajoute manuellement tous les lemmings
			Lemming lem = new Lemming();
			lem.init(gameEng);
			gameEng.addLemming(lem);
		} 
		int id=5;
		int sizeColony = gameEng.getSizeColony();
		assertTrue("id a tester '"+id+"' id>=0 ?",
				id>=0);
		try{
			assertTrue("id a tester '"+id+"', containsIdColony(id) ?", gameEng.containsIdColony(id));
		}catch(PreConditionError e){
			assertFalse(e.toString(),true);
		}
		assertTrue("id a tester '"+id+"', sizeColony = "+sizeColony+ " id<sizeColony ?",
				id < sizeColony);
	}
	
	
	/**
	 * Objectif de Test: getLemVivantById(int id) retourne failed
	 * 
	 * Cas de Test: gameEng.GetLemVivantById(int id)
	 * 		 id<0
	 * 
	 * Condition initial:
	 * 
	 * Operation:
	 *  getLemVivantById(-2)
	 *  
	 */
	@Test
	public void testGetLemVivantById2(){
		for(int i=0; i<gameEng.getSizeColony();i++){ //ON Ajoute manuellement tous les lemmings
			Lemming lem = new Lemming();
			lem.init(gameEng);
			gameEng.addLemming(lem);
		} 
		int id=-2;
		int sizeColony = gameEng.getSizeColony();
		assertTrue("id a tester '"+id+"' id>=0 ?",
				id>=0);
		try{
			assertTrue("id a tester '"+id+"', containsIdColony(id) ?", gameEng.containsIdColony(id));
		}catch(PreConditionError e){
			assertFalse(e.toString(),true);
		}
		assertTrue("id a tester '"+id+"', sizeColony = "+sizeColony+ " id<sizeColony ?",
				id < sizeColony);
	}
	
	/**
	 * Objectif de Test: getLemVivantById(int id) retourne failed
	 * 
	 * Cas de Test: gameEng.GetLemVivantById(int id)
	 * 		 id>sizeColony
	 * 
	 * Condition initial:
	 * 
	 * Operation:
	 *  getLemVivantById(15)
	 *  
	 */
	@Test
	public void testGetLemVivantById3(){
		for(int i=0; i<gameEng.getSizeColony();i++){ //ON Ajoute manuellement tous les lemmings
			Lemming lem = new Lemming();
			lem.init(gameEng);
			gameEng.addLemming(lem);
		} 
		int id=15;
		int sizeColony = gameEng.getSizeColony();
		assertTrue("id a tester '"+id+"' id>=0 ?",
				id>=0);
		try{
			assertTrue("id a tester '"+id+"', containsIdColony(id) ?", gameEng.containsIdColony(id));
		}catch(PreConditionError e){
			assertFalse(e.toString(),true);
		}
		assertTrue("id a tester '"+id+"', sizeColony = "+sizeColony+ " id<sizeColony ?",
				id < sizeColony);
	}
	
	/**
	 * Objectif de Test: getLemVivantById(int id) retourne failed
	 * 
	 * Cas de Test: gameEng.GetLemVivantById(int id)
	 * 		 !containsIdColony(id)
	 * 
	 * Condition initial:
	 * 
	 * Operation:
	 *  getLemVivantById(15)
	 *  
	 */
	@Test
	public void testGetLemVivantById4(){
		for(int i=0; i<gameEng.getSizeColony();i++){ //ON Ajoute manuellement tous les lemmings
			Lemming lem = new Lemming();
			lem.init(gameEng);
			gameEng.addLemming(lem);
		} 
		int id=15;
		int sizeColony = gameEng.getSizeColony();
		assertTrue("id a tester '"+id+"' id>=0 ?",
				id>=0);
		try{
			assertTrue("id a tester '"+id+"', containsIdColony(id) ?", gameEng.containsIdColony(id));
		}catch(PreConditionError e){
			assertFalse(e.toString(),true);
		}
		assertTrue("id a tester '"+id+"', sizeColony = "+sizeColony+ " id<sizeColony ?",
				id < sizeColony);
	}
	
	
	/**
	 * Objectif de Test: init(ILevel level, int sizeColony, int spawnSpeed) reussit
	 * 
	 * Cas de Test: gameEng.init(ILevel level, int sizeColony, int spawnSpeed)
	 * 		 sizeColony > 0 && spawnSpeed > 0
	 * 		 sizeColony = 10 && spawnSpeed =10
	 * 
	 * Condition initial:
	 * 
	 * Operation:
	 *  
	 */
	@Test
	public void testInit(){
		int sizeColony=10;
		int spawnSpeed=10;
		
		assertTrue("sizeColony a tester '"+sizeColony+"' sizeColony>=0 ?",
				sizeColony>0);
		assertTrue("spawnSpeed a tester '"+spawnSpeed+"' spawnSpeed>=0 ?",
				spawnSpeed>0);
		
		resetGameEng();
		
		gameEng.init(level, sizeColony, spawnSpeed);
		
		
		assertTrue("gameEng.getLevel() == level ?",gameEng.getLevel() == level);
		
		assertTrue("getSizeColony() = "+gameEng.getSizeColony()+" sizeColony = "+sizeColony
				+"gameEng.getSizeColony() == sizeColony ?",
				gameEng.getSizeColony() == sizeColony);
		
		assertTrue("getSpawnSpeed() = "+gameEng.getSpawnSpeed()+" spawnSpeed = "+spawnSpeed
				+"gameEng.getSpawnSpeed() == spawnSpeed ?",
				gameEng.getSpawnSpeed() == spawnSpeed);
		
		assertTrue("getSpawned() = "+gameEng.getSpawned()+" getSpawned == 0 ? ",
				gameEng.getSpawned() == 0);
		
		assertTrue("getNbTours() = "+gameEng.getNbTours()+" getNbTours == 0 ? ",
				gameEng.getNbTours() == 0);
		
		assertTrue("getNbLemVivants() = "+gameEng.getNbLemVivants()+" getNbLemVivants == 0 ? ",
				gameEng.getNbLemVivants() == 0);
		
		assertTrue("getNbLemSauves() = "+gameEng.getNbLemSauves()+" getNbLemSauves == 0 ? ",
				gameEng.getNbLemSauves() == 0);
		
		assertTrue("gameOver() = "+gameEng.gameOver()+" gameOver == 0 ? ",
				gameEng.gameOver() == false);
	}
	
	
	
	/**
	 * Objectif de Test: init(ILevel level, int sizeColony, int spawnSpeed) failed sizeColony <=0
	 * 
	 * Cas de Test: gameEng.init(ILevel level, int sizeColony, int spawnSpeed)
	 * 		 sizeColony > 0 && spawnSpeed > 0
	 * 		 sizeColony = 0 && spawnSpeed =10
	 * 
	 * Condition initial:
	 * 
	 * Operation:
	 *  
	 */
	@Test
	public void testInit2(){
		int sizeColony=0;
		int spawnSpeed=10;
		
		assertTrue("sizeColony a tester '"+sizeColony+"' sizeColony>=0 ?",
				sizeColony>0);
		assertTrue("spawnSpeed a tester '"+spawnSpeed+"' spawnSpeed>=0 ?",
				spawnSpeed>0);
		
		resetGameEng();
		
		gameEng.init(level, sizeColony, spawnSpeed);
		
		
		assertTrue("gameEng.getLevel() == level ?",gameEng.getLevel() == level);
		
		assertTrue("getSizeColony() = "+gameEng.getSizeColony()+" sizeColony = "+sizeColony
				+"gameEng.getSizeColony() == sizeColony ?",
				gameEng.getSizeColony() == sizeColony);
		
		assertTrue("getSpawnSpeed() = "+gameEng.getSpawnSpeed()+" spawnSpeed = "+spawnSpeed
				+"gameEng.getSpawnSpeed() == spawnSpeed ?",
				gameEng.getSpawnSpeed() == spawnSpeed);
		
		assertTrue("getSpawned() = "+gameEng.getSpawned()+" getSpawned == 0 ? ",
				gameEng.getSpawned() == 0);
		
		assertTrue("getNbTours() = "+gameEng.getNbTours()+" getNbTours == 0 ? ",
				gameEng.getNbTours() == 0);
		
		assertTrue("getNbLemVivants() = "+gameEng.getNbLemVivants()+" getNbLemVivants == 0 ? ",
				gameEng.getNbLemVivants() == 0);
		
		assertTrue("getNbLemSauves() = "+gameEng.getNbLemSauves()+" getNbLemSauves == 0 ? ",
				gameEng.getNbLemSauves() == 0);
		
		assertTrue("gameOver() = "+gameEng.gameOver()+" gameOver == 0 ? ",
				gameEng.gameOver() == false);
	}
	
	/**
	 * Objectif de Test: init(ILevel level, int sizeColony, int spawnSpeed) failed spawnSpeed <=0
	 * 
	 * Cas de Test: gameEng.init(ILevel level, int sizeColony, int spawnSpeed)
	 * 		 sizeColony > 0 && spawnSpeed > 0
	 * 		 sizeColony = 10 && spawnSpeed = 0
	 * 
	 * Condition initial:
	 * 
	 * Operation:
	 *  
	 */
	@Test
	public void testInit3(){
		int sizeColony=10;
		int spawnSpeed=0;
		
		assertTrue("sizeColony a tester '"+sizeColony+"' sizeColony>=0 ?",
				sizeColony>0);
		assertTrue("spawnSpeed a tester '"+spawnSpeed+"' spawnSpeed>=0 ?",
				spawnSpeed>0);
		
		resetGameEng();
		
		gameEng.init(level, sizeColony, spawnSpeed);
		
		assertTrue("gameEng.getLevel() == level ?",gameEng.getLevel() == level);
		
		assertTrue("getSizeColony() = "+gameEng.getSizeColony()+" sizeColony = "+sizeColony
				+"gameEng.getSizeColony() == sizeColony ?",
				gameEng.getSizeColony() == sizeColony);
		
		assertTrue("getSpawnSpeed() = "+gameEng.getSpawnSpeed()+" spawnSpeed = "+spawnSpeed
				+"gameEng.getSpawnSpeed() == spawnSpeed ?",
				gameEng.getSpawnSpeed() == spawnSpeed);
		
		assertTrue("getSpawned() = "+gameEng.getSpawned()+" getSpawned == 0 ? ",
				gameEng.getSpawned() == 0);
		
		assertTrue("getNbTours() = "+gameEng.getNbTours()+" getNbTours == 0 ? ",
				gameEng.getNbTours() == 0);
		
		assertTrue("getNbLemVivants() = "+gameEng.getNbLemVivants()+" getNbLemVivants == 0 ? ",
				gameEng.getNbLemVivants() == 0);
		
		assertTrue("getNbLemSauves() = "+gameEng.getNbLemSauves()+" getNbLemSauves == 0 ? ",
				gameEng.getNbLemSauves() == 0);
		
		assertTrue("gameOver() = "+gameEng.gameOver()+" gameOver == 0 ? ",
				gameEng.gameOver() == false);
	}
	
	
	@Test
	public void killLemming(){
		int id=2;
		
		for(int i=0; i<gameEng.getSizeColony();i++){ //ON Ajoute manuellement tous les lemmings
			Lemming lem = new Lemming();
			lem.init(gameEng);
			gameEng.addLemming(lem);
		} 
		
		assertTrue("getNbLemVivants() = '"+gameEng.getNbLemVivants()+"' getNbLemVivants>0 ?",
				gameEng.getNbLemVivants()>0);
		try{
			assertTrue("id a tester '"+id+"', containsIdColony(id) ?", gameEng.containsIdColony(id));
		}catch(PreConditionError e){
			assertFalse(e.toString(),true);
		}
	}
	
	
	@Test
	public void killLemming2(){
		
		int id=2;
		
		assertTrue("getNbLemVivants() = '"+gameEng.getNbLemVivants()+"' getNbLemVivants>0 ?",
				gameEng.getNbLemVivants()>0);
		try{
			assertTrue("id a tester '"+id+"', containsIdColony(id) ?", gameEng.containsIdColony(id));
		}catch(PreConditionError e){
			assertFalse(e.toString(),true);
		}
		
	}
	
	
	@Test
	public void killLemming3(){
		int id=2;
		
		for(int i=0; i<gameEng.getSizeColony();i++){ //ON Ajoute manuellement tous les lemmings
			Lemming lem = new Lemming();
			lem.init(gameEng);
			gameEng.addLemming(lem);
		} 
		
		gameEng.killLemming(id);
		
		assertTrue("getNbLemVivants() = '"+gameEng.getNbLemVivants()+"' getNbLemVivants>0 ?",
				gameEng.getNbLemVivants()>0);
		try{
			assertTrue("id a tester '"+id+"', containsIdColony(id) ?", gameEng.containsIdColony(id));
		}catch(PreConditionError e){
			assertFalse(e.toString(),true);
		}
	}
	
	
	@Test
	public void saveLemming(){
		int id=2;
		
		for(int i=0; i<gameEng.getSizeColony();i++){ //ON Ajoute manuellement tous les lemmings
			Lemming lem = new Lemming();
			lem.init(gameEng);
			gameEng.addLemming(lem);
		} 
		
		assertTrue("id = '"+id+"' id>=0 ?",
				id>=0);
		assertTrue("id = '"+id+"' id<sizeColony ?",
				id<gameEng.getSizeColony());
		
		try{
			assertTrue("id a tester '"+id+"', containsIdColony(id) ?", gameEng.containsIdColony(id));
		}catch(PreConditionError e){
			assertFalse(e.toString(),true);
		}
	}
	
	@Test
	public void saveLemming2(){
		int id=-2;
		
		for(int i=0; i<gameEng.getSizeColony();i++){ //ON Ajoute manuellement tous les lemmings
			Lemming lem = new Lemming();
			lem.init(gameEng);
			gameEng.addLemming(lem);
		} 
		
		assertTrue("id = '"+id+"' id>=0 ?",
				id>=0);
		assertTrue("id = '"+id+"' id<sizeColony ?",
				id<gameEng.getSizeColony());
		
		try{
			assertTrue("id a tester '"+id+"', containsIdColony(id) ?", gameEng.containsIdColony(id));
		}catch(PreConditionError e){
			assertFalse(e.toString(),true);
		}
	}
	
	
	@Test
	public void saveLemming3(){
		int id=gameEng.getSizeColony();
		
		for(int i=0; i<gameEng.getSizeColony();i++){ //ON Ajoute manuellement tous les lemmings
			Lemming lem = new Lemming();
			lem.init(gameEng);
			gameEng.addLemming(lem);
		} 
		
		assertTrue("id = '"+id+"' id>=0 ?",
				id>=0);
		assertTrue("id = '"+id+"' id<sizeColony ?",
				id<gameEng.getSizeColony());
		
		try{
			assertTrue("id a tester '"+id+"', containsIdColony(id) ?", gameEng.containsIdColony(id));
		}catch(PreConditionError e){
			assertFalse(e.toString(),true);
		}
	}
	
	@Test
	public void saveLemming4(){
		int id=5;
		
		assertTrue("id = '"+id+"' id>=0 ?",
				id>=0);
		assertTrue("id = '"+id+"' id<sizeColony ?",
				id<gameEng.getSizeColony());
		
		try{
			assertTrue("id a tester '"+id+"', containsIdColony(id) ?", gameEng.containsIdColony(id));
		}catch(PreConditionError e){
			assertFalse(e.toString(),true);
		}
	}
	
	
	@Test
	public void containsIdColony(){
		int id=5;
		
		assertTrue("id = '"+id+"' id>=0 ?",
				id>=0);
		assertTrue("id = '"+id+"' id<sizeColony ?",
				id<gameEng.getSizeColony());
	}
	
	@Test
	public void containsIdColony2(){
		int id=-2;
		
		assertTrue("id = '"+id+"' id>=0 ?",
				id>=0);
		assertTrue("id = '"+id+"' id<sizeColony ?",
				id<gameEng.getSizeColony());
	}
	
	@Test
	public void containsIdColony3(){
		int id=gameEng.getSizeColony();
		
		assertTrue("id = '"+id+"' id>=0 ?",
				id>=0);
		assertTrue("id = '"+id+"' id<sizeColony ?",
				id<gameEng.getSizeColony());
	}
	
	
	/*
	public void changeSpawnSpeed(int newSpawnSpeed) {
		checkInvariants();
		if(! (newSpawnSpeed > 0) ) throw new PreConditionError("newSpawnSpeed <= 0");
		
		delegate.changeSpawnSpeed(newSpawnSpeed);
		
		if(! (getSpawnSpeed() == newSpawnSpeed) ) throw new PostConditionError("getSpawnSpeed() != newSpawnSpeed");
		checkInvariants();
	}
	
	
	
	public void changeSizeColony(int newSizeColony) {
		checkInvariants();
		if(! (newSizeColony > 0) ) throw new PreConditionError("newSizeColony <= 0");
		if(! (getLevel().getEditing()==true)) throw new PreConditionError("getLevel().getEditing()==false");
		
		delegate.changeSizeColony(newSizeColony);
		
		if(! (getSizeColony() == newSizeColony) ) throw new PostConditionError("getSizeColony() != newSizeColony");
		checkInvariants();
	}*/
	
}