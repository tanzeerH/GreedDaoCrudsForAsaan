import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;


public class AsaanDaoGenerator {
	
  public static void main(String[] args) {
	  Schema schema = new Schema(1000, "asaan.dao");

      addItem(schema);

      try {
		new DaoGenerator().generateAll(schema, "../AssanDaoGenerator");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  private static void addItem(Schema schema) {
      Entity item = schema.addEntity("AddItem");
      Property item_id=item.addIdProperty().autoincrement().getProperty();
      item.addIntProperty("quantity").notNull();
      item.addIntProperty("item_id").notNull();
      item.addStringProperty("order_for").notNull();
      
      Entity modItem = schema.addEntity("ModItem");
      modItem.addIntProperty("quantity").notNull();
      modItem.addIntProperty("item_id").notNull();
      
      ToMany ItemToModItem=item.addToMany(modItem, item_id);
      
      
      
      
  }

 

}
