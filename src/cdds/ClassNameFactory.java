package cdds;

public class ClassNameFactory {
				public static YTClass getClass(String type) {
					switch(type) {
					case "Props": return Props.getInstance();
					case "DB": return  DB.getInstance();
					}
					return null;
				}
		public void PrintClassName(String type) {
			YTClass instance =getClass(type);
			String Classname =instance.getClassName();
			System.out.println(Classname);
			
		}
}
