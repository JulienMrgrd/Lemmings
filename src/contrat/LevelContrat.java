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
		checkInvariants("getHeight");
		int res = delegate.getHeight();
		checkInvariants("getHeight");
		return res;
	}

	@Override
	public int getWidth() {
		checkInvariants("getWidth");
		int res = delegate.getWidth();
		checkInvariants("getWidth");
		return res;
	}

	@Override
	public int getEntranceHeight() {
		checkInvariants("getEntranceHeight");
		int res = delegate.getEntranceHeight();
		checkInvariants("getEntranceHeight");
		return res;
	}

	@Override
	public int getEntranceWidth() {
		checkInvariants("getEntranceWidth");
		int res = delegate.getEntranceWidth();
		checkInvariants("getEntranceWidth");
		return res;
	}

	@Override
	public int getExitHeight() {
		checkInvariants("getExitHeight");
		int res = delegate.getExitHeight();
		checkInvariants("getExitHeight");
		return res;
	}

	@Override
	public int getExitWidth() {
		checkInvariants("getExitWidth");
		int res = delegate.getExitWidth();
		checkInvariants("getExitWidth");
		return res;
	}

	@Override
	public boolean getEditing() {
		checkInvariants("getEditing");
		boolean res = delegate.getEditing();
		checkInvariants("getEditing");
		return res;
	}

	@Override
	public Nature getNature(int h, int w) {
		
		checkInvariants("getNature");
		//TEST PRECONDITION
		if(!(h>=0)) throw new PreConditionError(" h<0");
		if(!(w>=0)) throw new PreConditionError(" w<0");
		if(!(h<= getHeight())) throw new PreConditionError(" h>getHeight()");
		if(!(w<= getWidth())) throw new PreConditionError(" w>getWidth()");
		
		//APPEL METHODE
		Nature n =delegate.getNature(h,w);
		
		checkInvariants("getNature");
		
		return n;
	}

	@Override
	public void init(int h, int w) {

		checkInvariants("init");
		//TEST PRECONDITION
		if(!(h>=5)) throw new PreConditionError(" h<5");
		if(!(w>=4)) throw new PreConditionError(" w<4");
		
		//APPEL METHODE
		delegate.init(h,w);
		
		//TEST POSTCONDITION
		if(!(h== getHeight())) throw new PostConditionError(" h!=getHeight()");
		if(!(w== getWidth())) throw new PostConditionError(" w!=getWidth()");
		if(!(getEditing())) throw new PostConditionError(" !getEditing()");
		
		checkInvariants("init");
	}


	@Override
	public void setNature(int h, int w, Nature nat) {
		
		checkInvariants("setNature");
		//TEST PRECONDITION
		if(!(h>=0)) throw new PreConditionError(" h<0");
		if(!(w>=0)) throw new PreConditionError(" w<0");
		if(!(h<= getHeight())) throw new PreConditionError(" h>getHeight()");
		if(!(w<= getWidth())) throw new PreConditionError(" w>getWidth()");
		if(!(getEditing())) throw new PreConditionError(" !getEditing()");
		
		//APPEL METHODE
		delegate.setNature(h, w, nat);
		
		//TEST POSTCONDITION
		if(! (nat== getNature(h,w))) throw new PostConditionError(" nat!=getNature(h,w)");
		
		checkInvariants("setNature");
	}

	@Override
	public void goPlay(int entranceH, int entranceW, int exitH, int exitW) {

		checkInvariants("goPlay");
		//TEST PRECONDITION
		if(!(getEditing())) throw new PreConditionError(" : !getEditing()");
		if(!(entranceH>=0)) throw new PreConditionError(" entranceH<0");
		if(!(entranceW>=0)) throw new PreConditionError(" entranceW<0");
		if(!(exitH>=0)) throw new PreConditionError(" exitH<0");
		if(!(exitW>=0)) throw new PreConditionError(" exitW<0");
		
		int i;
		for(i=0; i < getHeight(); i++){
			if(!(getNature(i, 0)==Nature.METAL)) throw new PreConditionError(" getNature("+i+", 0)!=Nature.METAL)");
			if(!(getNature(i, getWidth()-1)==Nature.METAL)) throw new PreConditionError(" getNature("+i+", "+(getWidth()-1)+")!=Nature.METAL)");
		}  
		for(i=0; i < getWidth(); i++){
			if(!(getNature(0, i)==Nature.METAL)) throw new PreConditionError(" getNature(0 , "+i+")!=Nature.METAL)");
			if(!(getNature(getHeight()-1, i)==Nature.METAL)) throw new PreConditionError(" getNature("+(getHeight()-1)+" , "+i+")!=Nature.METAL)");
		}
		if(!(getNature(entranceH-1, entranceW)==Nature.EMPTY)) throw new PreConditionError(" getNature(entranceH-1 , entranceW)!=Nature.EMPTY)");
		if(!(getNature(entranceH+1, entranceW)==Nature.EMPTY)) throw new PreConditionError(" getNature(entranceH+1 , entranceW)!=Nature.EMPTY)");
		if(!(getNature(exitH-1, exitW)==Nature.EMPTY)) throw new PreConditionError(" getNature(exitH-1 , exitW)!=Nature.EMPTY)");
		if(!(getNature(exitH+1, exitW)==Nature.METAL)) throw new PreConditionError(" getNature(exitH+1 , exitW)!=Nature.METAL)");
		
		//APPEL METHODE
		delegate.goPlay(entranceH, entranceW, exitH, exitW);
		
		//TEST POSTCONDITION
		if( (getEditing())) throw new PostConditionError(" getEditing()");
		if(!(getEntranceHeight()==entranceH) ) throw new PostConditionError(" getEntranceHeight()!=entranceH");
		if(!(getEntranceWidth()==entranceW) ) throw new PostConditionError(" getEntranceWidth()!=entranceW");
		if(!(getExitHeight()==exitH) ) throw new PostConditionError(" getExitHeight()!=exitH");
		if(!(getExitWidth()==exitW) ) throw new PostConditionError(" getExitWidth()!=exitW");
		
		checkInvariants("goPlay");
		
	}

	@Override
	public void remove(int h, int w) {
		
		checkInvariants("remove");
		//TEST PRECONDITION
		if(!(getNature(h, w)==Nature.DIRT)) throw new PreConditionError(" getNature(h, w) != Nature.DIRT");
		if( (getEditing())) throw new PreConditionError(" getEditing()");
		
		//APPEL METHODE
		delegate.remove(h, w);
		
		//TEST POSTCONDITION
		if(!(getNature(h, w)==Nature.EMPTY)) throw new PostConditionError(" getNature(h, w) != Nature.EMPTY");
		
		checkInvariants("remove");
	}

	@Override
	public void build(int h, int w) {

		checkInvariants();
		//TEST PRECONDITION
		if(!(getNature(h, w)==Nature.EMPTY)) throw new PreConditionError(" getNature(h, w) != Nature.EMPTY");
		if( (getEditing())) throw new PreConditionError(" getEditing()");
		
		//APPEL METHODE
		delegate.build(h, w);
		
		//TEST POSTCONDITION
		if(!(getNature(h, w)==Nature.DIRT)) throw new PostConditionError(" getNature(h, w) != Nature.DIRT");
		
		checkInvariants();
		
	}
	
	
	@Override
	protected void checkInvariants(){

		if(!(getHeight()>=5)) throw new InvariantError("getHeight<5");
		if(!(getWidth()>=4)) throw new InvariantError("getWidth<4");
		
	}
}
