package contrat;

import decorateur.LevelDecorateur;
import enumeration.Nature;
import servicesImplementations.Level;

public class LevelContrat extends LevelDecorateur{
	
	public LevelContrat() {
		super(new Level());
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEntranceHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEntranceWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getExitHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getExitWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getEditing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Nature getNature(int h, int w) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(int h, int w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNature(int h, int w, Nature nat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goPlay(int entranceH, int entranceW, int exitH, int exitW) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int h, int w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void build(int h, int w) {
		// TODO Auto-generated method stub
		
	}
}
