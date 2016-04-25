package servicesImplementations;

import enumeration.Nature;
import services.ILevel;

public class Level implements ILevel {

	
	int height;
	int width;
	int inH;
	int inW;
	int outH;
	int outW;
	boolean editing;
	Nature[][] nat;
	
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getInH() {
		return inH;
	}

	@Override
	public int getInW() {
		return inW;
	}

	@Override
	public int getOutH() {
		return outH;
	}

	@Override
	public int getOutW() {
		return outW;
	}

	@Override
	public boolean getEditing() {
		return editing;
	}

	@Override
	public Nature getNature(int h, int w) {
		if(h>=0 && w>=0 && h<=getHeight() && w<=getWidth()){
			return nat[h][w];
		}
		return null;
	}

	@Override
	public void init(int h, int w, int inH, int inW, int outH, int outW) { 
		if(h>=0 && w >= 0 && inH >= 0 && inW >= 0 && outH >= 0 && outW >= 0){
			height=h;
			width=w;
			this.inH=inH;
			this.inW=inW;
			this.outH=outH;
			this.outW=outW;
			editing=true;
			nat=new Nature[h][w];
		}
	}

	@Override
	public void setNature(int h, int w, Nature nat) {
		if(getEditing()==true && getNature(h, w)!=nat){
			this.nat[h][w]=nat;
		}
	}

	@Override
	public void goPlay() {
		boolean allMetal = true;
		for(int i=0;i<getHeight()&&allMetal==true;i++){
			if((getNature(i, 0)!=Nature.METAL)||
					getNature(i, getWidth())!=Nature.METAL)
				allMetal=false;
		}
		for(int i=0;i<getWidth()&&allMetal==true;i++){
			if(getNature(0, i)!=Nature.METAL||
					getNature(getHeight()-1, i)!=Nature.METAL)
				allMetal=false;
		}
		if(allMetal==false) editing = true;
		else editing = false;
	}

	@Override
	public void remove(int h, int w) {
		if(!getEditing()&&getNature(h, w)==Nature.DIRT){
			setNature(h,w,Nature.EMPTY);
		}
	}

	@Override
	public void build(int h, int w) {
		if(!getEditing()&&getNature(h, w)==Nature.EMPTY){
			setNature(h,w,Nature.DIRT);
		}
	}

	@Override
	public void edit(int h, int w, Nature nat) {
		if(getEditing() && getNature(h,w)!=nat){
			this.nat[h][w]=nat;
		}
	}

}
