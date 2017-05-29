package iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class RestaurantMenu {
	private Dinner dinner;
	private Breakfast breakfast;
	public RestaurantMenu()
	{
		dinner = new Dinner();
		breakfast = new Breakfast();
	}
	
	public ArrayList<Iterable<MenuItem>> getMenus()
	{
		ArrayList<Iterable<MenuItem>> menus = new ArrayList<Iterable<MenuItem>>();
		menus.add(breakfast);
		menus.add(dinner);
		return menus;
	}
	public static void main(String[] args) {
		RestaurantMenu restaurantMenu = new RestaurantMenu();
		for(Iterable<MenuItem> menu : restaurantMenu.getMenus())
		{
			Iterator<MenuItem> iter = menu.createIterator();
			while(iter.hasNext())
			{
				System.out.println(iter.next());
			}
			
		}
	}
}
