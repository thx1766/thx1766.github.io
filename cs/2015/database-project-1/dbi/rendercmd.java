public class rendercmd{
	public static void main(String[] args){

	System.out.println("rendering board");


	System.out.println(rendertoprow());
	}

	public static String renderboxtop(){
		String boxtop = "_____";
		return boxtop;
		}

	public static String renderboxbottom(){
		String boxbottom = "|___|";
		return boxbottom;
		}
	
	public static String rendertoprow(){
		String toprow = "";
		int count = 8;
		while(count>0){
			toprow+=renderboxtop();
			count--;
			}
		return toprow;
		}

}
