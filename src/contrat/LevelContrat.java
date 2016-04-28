package contrat;

import decorateur.LevelDecorateur;
import enumeration.Nature;
import errors.PreConditionError;
import services.ILevel;
import servicesImplementations.Level;

public class LevelContrat extends LevelDecorateur{
	
	public LevelContrat() {
		super(new Level());
	}

	@Override
	public int getHeight() {
		return ((ILevel) super.getDelegate()).getHeight();
	}

	@Override
	public int getWidth() {
		return ((ILevel) super.getDelegate()).getWidth();
	}

	@Override
	public int getEntranceHeight() {
		return ((ILevel) super.getDelegate()).getEntranceHeight();
	}

	@Override
	public int getEntranceWidth() {
		return ((ILevel) super.getDelegate()).getEntranceWidth();
	}

	@Override
	public int getExitHeight() {
		return ((ILevel) super.getDelegate()).getExitHeight();
	}

	@Override
	public int getExitWidth() {
		return ((ILevel) super.getDelegate()).getExitWidth();
	}

	@Override
	public boolean getEditing() {
		return ((ILevel) super.getDelegate()).getEditing();
	}

	@Override
	public Nature getNature(int h, int w) {
		
		checkInvariants();
		//TESTER LES PRECONDITION
		if(! (h>=0)) throw new PreConditionError("preConditionError (getNature) : h<0");
		if(! (w>=0)) throw new PreConditionError("preConditionError (getNature) : w<0");
		if(! (h<= ((ILevel) super.getDelegate()).getHeight())) throw new PreConditionError("postConditionError (getNature) : h>getHeight()");
		if(! (w<= ((ILevel) super.getDelegate()).getWidth())) throw new PreConditionError("postConditionError (getNature) : w>getWidth()");
		
		Nature n =((ILevel) super.getDelegate()).getNature(h,w);
		
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
		
		
		
		((ILevel) super.getDelegate()).init(h,w);
		
		
		//TESTER LES POST CONDITION
		if(! (h== ((ILevel) super.getDelegate()).getHeight())) throw new PreConditionError("postConditionError (Init) : h!=getHeight()");
		if(! (w== ((ILevel) super.getDelegate()).getWidth())) throw new PreConditionError("postConditionError (Init) : w!=getWidth()");
		if(!(((ILevel) super.getDelegate()).getEditing())) throw new PreConditionError("postConditionError (Init) : !getEditing()");
		
		checkInvariants();
	}


	@Override
	public void setNature(int h, int w, Nature nat) {
		// TODO Auto-generated method stub
		checkInvariants();
		//TESTER LES PRECONDITION
		if(! (h>=0)) throw new PreConditionError("preConditionError (setNature) : <0");
		if(! (w>=0)) throw new PreConditionError("preConditionError (setNature) : w<0");
		if(! (h<= ((ILevel) super.getDelegate()).getHeight())) throw new PreConditionError("postConditionError (setNature) : h>getHeight()");
		if(! (w<= ((ILevel) super.getDelegate()).getWidth())) throw new PreConditionError("postConditionError (setNature) : w>getWidth()");
		if(!(((ILevel) super.getDelegate()).getEditing())) throw new PreConditionError("preConditionError (setNature) : !getEditing()");
		
		((ILevel) super.getDelegate()).setNature(h, w, nat);
		
		//TESTER LES POST CONDITION
		if(! (nat== ((ILevel) super.getDelegate()).getNature(h,w))) throw new PreConditionError("postConditionError (setNature) : nat!=getNature(h,w)");
		
		checkInvariants();
	}

	@Override
	public void goPlay(int entranceH, int entranceW, int exitH, int exitW) {
		// TODO Auto-generated method stub
		checkInvariants();
		//TESTER LES PRECONDITION
		if(!(((ILevel) super.getDelegate()).getEditing())) throw new PreConditionError("preConditionError (goPlay) : !getEditing()");
		if(! (entranceH>=0)) throw new PreConditionError("preConditionError (goPlay) : entranceH<0");
		if(! (entranceW>=0)) throw new PreConditionError("preConditionError (goPlay) : entranceW<0");
		if(! (exitH>=0)) throw new PreConditionError("preConditionError (goPlay) : exitH<0");
		if(! (exitW>=0)) throw new PreConditionError("preConditionError (goPlay) : exitW<0");
		
		int i;
		for(i=0; i < (((ILevel) super.getDelegate()).getHeight()); i++){
			if(!(((ILevel) super.getDelegate()).getNature(i, 0)==Nature.METAL)) throw new PreConditionError("preConditionError (goPlay) : getNature("+i+", 0)!=Nature.METAL)");
			if(!(((ILevel) super.getDelegate()).getNature(i, ((ILevel) super.getDelegate()).getWidth()-1)==Nature.METAL)) throw new PreConditionError("preConditionError (goPlay) : getNature("+i+", "+(((ILevel) super.getDelegate()).getWidth()-1)+")!=Nature.METAL)");
		}  
		for(i=0; i < getWidth(); i++){
			if(!(((ILevel) super.getDelegate()).getNature(0, i)==Nature.METAL)) throw new PreConditionError("preConditionError (goPlay) : getNature(0 , "+i+")!=Nature.METAL)");
			if(!(((ILevel) super.getDelegate()).getNature(((ILevel) super.getDelegate()).getHeight()-1, i)==Nature.METAL)) throw new PreConditionError("preConditionError (goPlay) : getNature("+(((ILevel) super.getDelegate()).getHeight()-1)+" , "+i+")!=Nature.METAL)");
		}
		if(!(((ILevel) super.getDelegate()).getNature(entranceH-1, entranceW)==Nature.EMPTY)) throw new PreConditionError("preConditionError (goPlay) : getNature(entranceH-1 , entranceW)!=Nature.EMPTY)");
		if(!(((ILevel) super.getDelegate()).getNature(entranceH+1, entranceW)==Nature.EMPTY)) throw new PreConditionError("preConditionError (goPlay) : getNature(entranceH+1 , entranceW)!=Nature.EMPTY)");
		if(!(((ILevel) super.getDelegate()).getNature(exitH-1, exitW)==Nature.EMPTY)) throw new PreConditionError("preConditionError (goPlay) : getNature(exitH-1 , exitW)!=Nature.EMPTY)");
		if(!(((ILevel) super.getDelegate()).getNature(exitH+1, exitW)==Nature.METAL)) throw new PreConditionError("preConditionError (goPlay) : getNature(exitH+1 , exitW)!=Nature.METAL)");
		
		((ILevel) super.getDelegate()).goPlay(entranceH, entranceW, exitH, exitW);
		
		
		
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
	
	private void checkInvariants() {
		// TODO Auto-generated method stub
		
	}
}
