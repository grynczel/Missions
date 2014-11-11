import java.util.Comparator;

	public class EntreeComparable implements Comparator<Entree> {
		private String orderBy;
		private boolean ascending;
		public EntreeComparable(String orderBy,boolean ascending) {
			this.orderBy 	= orderBy;
			this.ascending 	= ascending;
		}

		@Override
		public int compare(Entree arg0, Entree arg1) {
			if(ascending)
				return arg0.get(orderBy).compareTo(arg1.get(orderBy));
			else
				return arg1.get(orderBy).compareTo(arg0.get(orderBy));
		}
	}