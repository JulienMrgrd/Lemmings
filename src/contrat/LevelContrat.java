package contrat;

import decorateur.LevelDecorateur;
import enumeration.Nature;
import errors.InvariantError;
import errors.PostConditionError;
import errors.PreConditionError;
import servicesImplementations.Level;

public class LevelContrat extends LevelDecorateur{
	
	public LevelContrat() {
		super(new Level());
	}

	@Override
	public int getHeight() {
		checkInvariants();
		int res = delegate.getHeight();
		checkInvariants();
		return res;
	}

	@Override
	public int getWidth() {
		checkInvariants();
		int res = delegate.getWidth();
		checkInvariants();
		return res;
	}

	@Override
	public int getEntranceHeight() {
		checkInvariants();
		int res = delegate.getEntranceHeight();
		checkInvariants();
		return res;
	}

	@Override
	public int getEntranceWidth() {
		checkInvariants();
		int res = delegate.getEntranceWidth();
		checkInvariants();
		return res;
	}

	@Override
	public int getExitHeight() {
		checkInvariants();
		int res = delegate.getExitHeight();
		checkInvariants();
		return res;
	}

	@Override
	public int getExitWidth() {
		checkInvariants();
		int res = delegate.getExitWidth();
		checkInvariants();
		return res;
	}

	@Override
	public boolean getEditing() {
		checkInvariants();
		boolean res = delegate.getEditing();
		checkInvariants();
		return res;
	}

	@Override
	public Nature getNature(int h, int w) {
		checkInvariants();
		if(! (h>=0)) throw new PreConditionError("h<0");
		if(! (w>=0)) throw new PreConditionError("w<0");
		if(! (h<= getHeight())) throw new PreConditionError("h>getHeight()");
		if(! (w<= getWidth())) throw new PreConditionError("w>getWidth()");
		
		Nature n = delegate.getNature(h,w);
		
		checkInvariants();
		return n;
	}

	@Override
	public void init(int h, int w) {
		if(! (h>=5)) throw new PreConditionError("h<5");
		if(! (w>=4)) throw new PreConditionError("w<4");
		
		delegate.init(h,w);
		
		if(! (h== getHeight())) throw new PostConditionError("h!=getHeight()");
		if(! (w== getWidth())) throw new PostConditionError("w!=getWidth()");
		if(! (getEditing()==true)) throw new PostConditionError("getEditing()==false");
		checkInvariants();
	}


	@Override
	public void setNature(int h, int w, Nature nat) {
		checkInvariants();
		if(! (h>=0)) throw new PreConditionError("h<0");
		if(! (w>=0)) throw new PreConditionError("w<0");
		if(! (h <= getHeight())) throw new PreConditionError("h > getHeight()");
		if(! (w <= getWidth())) throw new PreConditionError("w > getWidth()");
		if(! (getEditing()==true)) throw new PreConditionError("getEditing()==false");
		
		delegate.setNature(h, w, nat);
		
		if(! (nat == getNature(h,w))) throw new PostConditionError("nat!=getNature(h,w)");
		checkInvariants();
	}

	@Override
	public void goPlay(int entranceH, int entranceW, int exitH, int exitW) {
		checkInvariants();
		if(! (getEditing()==true)) throw new PreConditionError("getEditing()==false");
		if(! (entranceH>=0)) throw new PreConditionError("entranceH<0");
		if(! (entranceW>=0)) throw new PreConditionError("entranceW<0");
		if(! (exitH>=0)) throw new PreConditionError("exitH<0");
		if(! (exitW>=0)) throw new PreConditionError("exitW<0");
		
		for(int i=0; i < getHeight(); i++){
			if(! (getNature(i, 0)==Nature.METAL)) throw new PreConditionError("getNature("+i+", 0) != Nature.METAL)");
			if(! (getNature(i, getWidth()-1)==Nature.METAL)) throw new PreConditionError("getNature("+i+", "+(getWidth()-1)+") != Nature.METAL)");
		}  
		for(int i=0; i < getWidth(); i++){
			if(! (getNature(0, i)==Nature.METAL)) throw new PreConditionError("getNature(0 , "+i+") != Nature.METAL)");
			if(! (getNature(getHeight()-1, i)==Nature.METAL)) throw new PreConditionError("getNature("+(getHeight()-1)+" , "+i+") != Nature.METAL)");
		}
		if(! (getNature(entranceH-1, entranceW)==Nature.EMPTY)) throw new PreConditionError("getNature(entranceH-1 , entranceW) != Nature.EMPTY)");
		if(! (getNature(entranceH+1, entranceW)==Nature.EMPTY)) throw new PreConditionError("getNature(entranceH+1 , entranceW) != Nature.EMPTY)");
		if(! (getNature(exitH-1, exitW)==Nature.EMPTY)) throw new PreConditionError("getNature(exitH-1 , exitW) != Nature.EMPTY)");
		if(! (getNature(exitH+1, exitW)==Nature.METAL)) throw new PreConditionError("getNature(exitH+1 , exitW) != Nature.METAL)");
		
		delegate.goPlay(entranceH, entranceW, exitH, exitW);
		
		if(! (getEditing()==false)) throw new PostConditionError("getEditing()==true");
		if(! (getEntranceHeight()==entranceH) ) throw new PostConditionError("getEntranceHeight()!=entranceH");
		if(! (getEntranceWidth()==entranceW) ) throw new PostConditionError("getEntranceWidth()!=entranceW");
		if(! (getExitHeight()==exitH) ) throw new PostConditionError("getExitHeight()!=exitH");
		if(! (getExitWidth()==exitW) ) throw new PostConditionError("getExitWidth()!=exitW");
		
		checkInvariants();
		
	}

	@Override
	public void remove(int h, int w) {
		checkInvariants();
		if(! (getNature(h, w)==Nature.DIRT)) throw new PreConditionError("getNature(h, w) != Nature.DIRT");
		if(! (getEditing()==false)) throw new PreConditionError("getEditing()==true");
		
		delegate.remove(h, w);
		
		if(! (getNature(h, w)==Nature.EMPTY)) throw new PostConditionError("getNature(h, w) != Nature.EMPTY");
		checkInvariants();
	}

	@Override
	public void build(int h, int w) {
		checkInvariants();
		if(! (getNature(h, w)==Nature.EMPTY)) throw new PreConditionError("getNature(h, w) != Nature.EMPTY");
		if(! (getEditing()==false)) throw new PreConditionError("getEditing()==true");
		
		delegate.build(h, w);
		
		if(! (getNature(h, w)==Nature.DIRT)) throw new PostConditionError("getNature(h, w) != Nature.DIRT");
		checkInvariants();
	}
	
	
	@Override
	protected void checkInvariants(){
		if(! (delegate.getHeight()>=5)) throw new InvariantError("getHeight<5");
		if(! (delegate.getWidth()>=4)) throw new InvariantError("getWidth<4");
	}

	@Override
	public void reset() {
		delegate.reset();
		checkInvariants();
	}

	@Override
	public void addStopper(int h, int w) {
		checkInvariants();
		if(! (getNature(h, w)==Nature.EMPTY)) throw new PreConditionError("getNature(h, w) != Nature.EMPTY");
		if(! (getEditing()==false)) throw new PreConditionError("getEditing()==true");
		
		delegate.addStopper(h, w);
		
		if(! (getNature(h, w)==Nature.STOPPER)) throw new PostConditionError("getNature(h, w) != Nature.STOPPER");
		checkInvariants();
		
	}

	@Override
	public void removeStopper(int h, int w) {
		checkInvariants();
		if(! (getNature(h, w)==Nature.STOPPER)) throw new PreConditionError("getNature(h, w) != Nature.STOPPER");
		if(! (getEditing()==false)) throw new PreConditionError("getEditing()==true");
		
		delegate.removeStopper(h, w);
		
		if(! (getNature(h, w)==Nature.EMPTY)) throw new PostConditionError("getNature(h, w) != Nature.EMPTY");
		checkInvariants();
	}

	@Override
	public void addEnter(Integer height, Integer width) {
		// TODO Auto-generated method stub
		delegate.addEnter(height, width);
		// TODO Auto-generated method stub
	}

	@Override
	public void addExit(Integer height, Integer width) {
		// TODO Auto-generated method stub
		delegate.addExit(height, width);
		// TODO Auto-generated method stub
	}
}
