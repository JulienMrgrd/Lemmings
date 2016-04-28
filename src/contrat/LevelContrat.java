package contrat;

import decorateur.LevelDecorateur;
import enumeration.Nature;
import errors.InvariantError;
import errors.PreConditionError;
import servicesImplementations.Level;

public class LevelContrat extends LevelDecorateur{
	
	public LevelContrat() {
		super(new Level());
	}

	@Override
	public int getHeight() {
		return delegate.getHeight();
	}

	@Override
	public int getWidth() {
		return delegate.getWidth();
	}

	@Override
	public int getEntranceHeight() {
		return delegate.getEntranceHeight();
	}

	@Override
	public int getEntranceWidth() {
		return delegate.getEntranceWidth();
	}

	@Override
	public int getExitHeight() {
		return delegate.getExitHeight();
	}

	@Override
	public int getExitWidth() {
		return delegate.getExitWidth();
	}

	@Override
	public boolean getEditing() {
		return delegate.getEditing();
	}

	@Override
	public Nature getNature(int h, int w) {
		
		checkInvariants();
		//TESTER LES PRECONDITION
		if(! (h>=0)) throw new PreConditionError("preConditionError (getNature) : h<0");
		if(! (w>=0)) throw new PreConditionError("preConditionError (getNature) : w<0");
		if(! (h<= delegate.getHeight())) throw new PreConditionError("postConditionError (getNature) : h>getHeight()");
		if(! (w<= delegate.getWidth())) throw new PreConditionError("postConditionError (getNature) : w>getWidth()");
		
		Nature n =delegate.getNature(h,w);
		
		checkInvariants();
		
		return n;
	}

	@Override
	public void init(int h, int w) {
		// TODO Auto-generated method stub
		checkInvariants();
		//TESTER LES PRECONDITION
		if(! (h>=5)) throw new PreConditionError("preConditionError (Init) : h<5");
		if(! (w>=4)) throw new PreConditionError("preConditionError (Init) : w<4");
		
		
		
		delegate.init(h,w);
		
		
		//TESTER LES POST CONDITION
		if(! (h== delegate.getHeight())) throw new PreConditionError("postConditionError (Init) : h!=getHeight()");
		if(! (w== delegate.getWidth())) throw new PreConditionError("postConditionError (Init) : w!=getWidth()");
		if(!(delegate.getEditing())) throw new PreConditionError("postConditionError (Init) : !getEditing()");
		
		checkInvariants();
	}


	@Override
	public void setNature(int h, int w, Nature nat) {
		// TODO Auto-generated method stub
		checkInvariants();
		//TESTER LES PRECONDITION
		if(! (h>=0)) throw new PreConditionError("preConditionError (setNature) : <0");
		if(! (w>=0)) throw new PreConditionError("preConditionError (setNature) : w<0");
		if(! (h<= delegate.getHeight())) throw new PreConditionError("postConditionError (setNature) : h>getHeight()");
		if(! (w<= delegate.getWidth())) throw new PreConditionError("postConditionError (setNature) : w>getWidth()");
		if(!(delegate.getEditing())) throw new PreConditionError("preConditionError (setNature) : !getEditing()");
		
		delegate.setNature(h, w, nat);
		
		//TESTER LES POST CONDITION
		if(! (nat== delegate.getNature(h,w))) throw new PreConditionError("postConditionError (setNature) : nat!=getNature(h,w)");
		
		checkInvariants();
	}

	@Override
	public void goPlay(int entranceH, int entranceW, int exitH, int exitW) {
		// TODO Auto-generated method stub
		checkInvariants();
		//TESTER LES PRECONDITION
		if(!(delegate.getEditing())) throw new PreConditionError("preConditionError (goPlay) : !getEditing()");
		if(! (entranceH>=0)) throw new PreConditionError("preConditionError (goPlay) : entranceH<0");
		if(! (entranceW>=0)) throw new PreConditionError("preConditionError (goPlay) : entranceW<0");
		if(! (exitH>=0)) throw new PreConditionError("preConditionError (goPlay) : exitH<0");
		if(! (exitW>=0)) throw new PreConditionError("preConditionError (goPlay) : exitW<0");
		
		int i;
		for(i=0; i < (delegate.getHeight()); i++){
			if(!(delegate.getNature(i, 0)==Nature.METAL)) throw new PreConditionError("preConditionError (goPlay) : getNature("+i+", 0)!=Nature.METAL)");
			if(!(delegate.getNature(i, delegate.getWidth()-1)==Nature.METAL)) throw new PreConditionError("preConditionError (goPlay) : getNature("+i+", "+(delegate.getWidth()-1)+")!=Nature.METAL)");
		}  
		for(i=0; i < getWidth(); i++){
			if(!(delegate.getNature(0, i)==Nature.METAL)) throw new PreConditionError("preConditionError (goPlay) : getNature(0 , "+i+")!=Nature.METAL)");
			if(!(delegate.getNature(delegate.getHeight()-1, i)==Nature.METAL)) throw new PreConditionError("preConditionError (goPlay) : getNature("+(delegate.getHeight()-1)+" , "+i+")!=Nature.METAL)");
		}
		if(!(delegate.getNature(entranceH-1, entranceW)==Nature.EMPTY)) throw new PreConditionError("preConditionError (goPlay) : getNature(entranceH-1 , entranceW)!=Nature.EMPTY)");
		if(!(delegate.getNature(entranceH+1, entranceW)==Nature.EMPTY)) throw new PreConditionError("preConditionError (goPlay) : getNature(entranceH+1 , entranceW)!=Nature.EMPTY)");
		if(!(delegate.getNature(exitH-1, exitW)==Nature.EMPTY)) throw new PreConditionError("preConditionError (goPlay) : getNature(exitH-1 , exitW)!=Nature.EMPTY)");
		if(!(delegate.getNature(exitH+1, exitW)==Nature.METAL)) throw new PreConditionError("preConditionError (goPlay) : getNature(exitH+1 , exitW)!=Nature.METAL)");
		
		delegate.goPlay(entranceH, entranceW, exitH, exitW);
		
		
		
		//post : !getEditing() ^ getEntranceHeight()==entranceH ^ getEntranceWidth()==entranceW ^ getExitHeight()==exitH ^ getExitWidth()==exitW 

	}

	@Override
	public void remove(int h, int w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void build(int h, int w) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	protected void checkInvariants() throws InvariantError {
		// TODO Auto-generated method stub
		
	}
}
