package tests.mbt;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import errors.PreConditionError;
import services.IGameEng;
import services.ILevel;
import servicesImplementations.GameEng;
import servicesImplementations.Lemming;
import servicesImplementations.Level;

public class TestGameEng {

	ILevel level;
	IGameEng gameEng;
	
	private void resetGameEng(){
		gameEng=new GameEng();
	}
	
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
	}
	
	/**
	 * Objectif de Test: getLemVivantById(int id) reussi
	 * 
	 * Cas de Test: gameEng.getLemVivantById(int id)
	 * 		 0 <= id <sizeColony && containsIdColony(id)
	 * 
	 * Condition initiale:
	 * Tous les lemmings ont été ajouté
	 * 
	 * Operation:
	 *  getLemVivantById(5)
	 */
	@Test
	public void testGetLemVivantById(){
		for(int i=0; i<gameEng.getSizeColony();i++){ 
			//ON Ajoute manuellement tous les lemmings
			Lemming lem = new Lemming();
			lem.init(gameEng);
			gameEng.addLemming(lem);
		} 
		int id=5;
		int sizeColony = gameEng.getSizeColony();
		assertTrue("id a tester '"+id+"' id>=0 ?",
				id>=0);
		try{
			assertTrue("id a tester '"+id+"', containsIdColony(id) ?"
					, gameEng.containsIdColony(id));
		}catch(PreConditionError e){
			assertFalse(e.toString(),true);
		}
		assertTrue("id a tester '"+id+"', sizeColony = "+sizeColony+ " "
				+ "id<sizeColony ?",
				id < sizeColony);
	}
	
	/**
	 * Objectif de Test: getLemVivantById(int id) retourne failed id<0
	 * 
	 * Cas de Test: gameEng.getLemVivantById(int id)
	 * 		 id<0
	 * 
	 * Condition initiale:
	 * Tous les lemmings ont été ajouté
	 * 
	 * Operation:
	 *  getLemVivantById(-2)
	 *  
	 */
	@Test
	public void testGetLemVivantById2(){
		for(int i=0; i<gameEng.getSizeColony();i++){ 
			//ON Ajoute manuellement tous les lemmings
			Lemming lem = new Lemming();
			lem.init(gameEng);
			gameEng.addLemming(lem);
		} 
		int id=-2;
		int sizeColony = gameEng.getSizeColony();
		assertTrue("id a tester '"+id+"' id>=0 ?",
				id>=0);
		try{
			assertTrue("id a tester '"+id+"', containsIdColony(id) ?",
					gameEng.containsIdColony(id));
		}catch(PreConditionError e){
			assertFalse(e.toString(),true);
		}
		assertTrue("id a tester '"+id+"', sizeColony = "+sizeColony+ 
				" id<sizeColony ?",
				id < sizeColony);
	}
	
	/**
	 * Objectif de Test: getLemVivantById(int id) retourne failed id>sizeColony
	 * 
	 * Cas de Test: gameEng.getLemVivantById(int id)
	 * 		 id>sizeColony
	 * 
	 * Condition initiale:
	 * Tous les lemmings ont été ajouté
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
	 * Objectif de Test: getLemVivantById(int id) retourne failed !containsIdColony(id)
	 * 
	 * Cas de Test: gameEng.getLemVivantById(int id)
	 * 		 !containsIdColony(id)
	 * 
	 * Condition initiale:
	 * id du lemming pas ajouté
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
	 * Objectif de Test: init(ILevel level, int sizeColony, int spawnSpeed) reussi
	 * 
	 * Cas de Test: gameEng.init(ILevel level, int sizeColony, int spawnSpeed)
	 * 		 sizeColony > 0 && spawnSpeed > 0
	 * 		 sizeColony = 10 && spawnSpeed =10
	 * 
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  init(ILevel level, int sizeColony, int spawnSpeed)
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
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  init(ILevel level, int sizeColony, int spawnSpeed)
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
	 * Condition initiale:
	 * Aucune
	 * 
	 * Operation:
	 *  init(ILevel level, int sizeColony, int spawnSpeed)
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
	
	
	/**
	 * Objectif de Test: killLemming(int id) reussi
	 * 
	 * Cas de Test: gameEng.killLemming(int id)
	 * 		 id >= 0 && containsIdColony(id)
	 * 
	 * Condition initiale:
	 * Tous les lemmings ont été ajouté
	 * 
	 * Operation:
	 *  killLemming(int id)
	 */
	@Test
	public void testKillLemming(){
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
	
	
	/**
	 * Objectif de Test: killLemming(int id) retourne failed id<0
	 * 
	 * Cas de Test: gameEng.killLemming(int id)
	 * 		 id<0
	 * 
	 * Condition initiale:
	 * Tous les lemmings ont été ajouté
	 * 
	 * Operation:
	 *  killLemming(-2)
	 *  
	 */
	@Test
	public void testKillLemming2(){
		
		int id=-2;
		
		assertTrue("getNbLemVivants() = '"+gameEng.getNbLemVivants()+"' getNbLemVivants>0 ?",
				gameEng.getNbLemVivants()>0);
		try{
			assertTrue("id a tester '"+id+"', containsIdColony(id) ?", gameEng.containsIdColony(id));
		}catch(PreConditionError e){
			assertFalse(e.toString(),true);
		}
		
	}
	
	
	
	/**
	 * Objectif de Test: killLemming(int id) retourne failed !containsIdColony(id)
	 * 
	 * Cas de Test: gameEng.killLemming(int id)
	 * 		 !containsIdColony(id)
	 * 
	 * Condition initiale:
	 * Lemming plus dans la liste
	 *  
	 * Operation:
	 *  killLemming(id)
	 *  
	 */
	@Test
	public void testKillLemming3(){
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

	
	/**
	 * Objectif de Test: saveLemming(int id) reussi
	 * 
	 * Cas de Test: gameEng.saveLemming(int id)
	 * 		 id >= 0 && containsIdColony(id)
	 * 
	 * Condition initiale:
	 * Tous les lemmings ont été ajouté
	 * 
	 * Operation:
	 *  saveLemming(int id)
	 */
	@Test
	public void testSaveLemming(){
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
	
	/**
	 * id<0
	 */
	@Test
	public void testSaveLemming2(){
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
	
	/**
	 * id trop grand
	 */
	@Test
	public void testSaveLemming3(){
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
	
	/**
	 * liste pas encore créé
	 */
	@Test
	public void testSaveLemming4(){
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
	public void testContainsIdColony(){
		int id=5;
		
		assertTrue("id = '"+id+"' id>=0 ?",
				id>=0);
		assertTrue("id = '"+id+"' id<sizeColony ?",
				id<gameEng.getSizeColony());
	}
	
	@Test
	public void testContainsIdColony2(){
		int id=-2;
		
		assertTrue("id = '"+id+"' id>=0 ?",
				id>=0);
		assertTrue("id = '"+id+"' id<sizeColony ?",
				id<gameEng.getSizeColony());
	}
	
	@Test
	public void testContainsIdColony3(){
		int id=gameEng.getSizeColony();
		
		assertTrue("id = '"+id+"' id>=0 ?",
				id>=0);
		assertTrue("id = '"+id+"' id<sizeColony ?",
				id<gameEng.getSizeColony());
	}
	
	//TODO change spawn et sizeColony
	
	@Test
	public void testChangeSpawnSpeed(){
		int newSpawnSpeed=5;
		assertTrue("newSpawnSpeed = '"+newSpawnSpeed+"' newSpawnSpeed>0 ?",
				newSpawnSpeed>0);
	}
	
	/**
	 * SpawnSpeed<0
	 */
	@Test
	public void testChangeSpawnSpeed2(){
		int newSpawnSpeed=-5;
		assertTrue("newSpawnSpeed = '"+newSpawnSpeed+"' newSpawnSpeed>0 ?",
				newSpawnSpeed>0);
	}
	
	public void testChangeSizeColony(){
		int newSpawnSpeed=5;
		assertTrue("newSpawnSpeed = '"+newSpawnSpeed+"' newSpawnSpeed>0 ?",
				newSpawnSpeed>0);
		assertTrue("getEditing = "+gameEng.getLevel().getEditing()+" getEditing() ? ",
				gameEng.getLevel().getEditing());
	}
	
	/**
	 * SpawnSpeed<0
	 */
	@Test
	public void testChangeSizeColony2(){
		int newSpawnSpeed=-5;
		assertTrue("newSpawnSpeed = '"+newSpawnSpeed+"' newSpawnSpeed>0 ?",
				newSpawnSpeed>0);
		assertTrue("getEditing = "+gameEng.getLevel().getEditing()+" getEditing() ? ",
				gameEng.getLevel().getEditing());
	}
}