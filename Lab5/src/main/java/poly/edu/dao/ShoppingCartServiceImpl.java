package poly.edu.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import poly.edu.DB;
import poly.edu.entity.Item;
import poly.edu.service.ShoppingCartService;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	Map<Integer, Item> map = new HashMap<>();

	@Override
	public Item add(Integer id) {
		Item existing = map.get(id);
		if (existing != null) {
			existing.setQty(existing.getQty() + 1);
			return existing;
		}
		Item dbItem = DB.items.get(id);
		if (dbItem == null) {
			return null;
		}
		// tạo bản sao để không làm thay đổi dữ liệu DB tĩnh
		Item newItem = new Item(dbItem.getId(), dbItem.getName(), dbItem.getPrice(), 1);
		map.put(id, newItem);
		return newItem;
	}

	@Override
	public void remove(Integer id) {
		map.remove(id);
	}

	@Override
	public Item update(Integer id, int qty) {
		Item item = map.get(id);
		if (item != null) {
			item.setQty(qty);
		}
		return item;
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Collection<Item> getItems() {
		return map.values();
	}

	@Override
	public int getCount() {
		return map.values().stream().mapToInt(Item::getQty).sum();
	}

	@Override
	public double getAmount() {
		return map.values().stream()
				.mapToDouble(i -> i.getPrice() * i.getQty())
				.sum();
	}
}
