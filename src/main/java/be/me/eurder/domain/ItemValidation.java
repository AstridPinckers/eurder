package be.me.eurder.domain;

import be.me.eurder.domain.pojos.Item;
import be.me.eurder.domain.pojos.User.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemValidation {

    static Logger logger = LoggerFactory.getLogger(UserValidation.class);
    public static void assertValidItem(Item item){
        if(item==null){
            illegalArgument("Item cannot be empty");
        }
        if(item.getName() == null || item.getName().isBlank()){
            illegalArgument("Name cannot be empty");
        }
        if(item.getDescription() == null || item.getDescription().isBlank()){
            illegalArgument("Description cannot be empty");
        }
        if(item.getPrice().getValue()<0){
            illegalArgument("Price cannot be negative");
        }
        if(item.getAmount()<0){
            illegalArgument("Stock cannot be negative");
        }
    }

    private static void illegalArgument(String message){
        logger.error(message);
        throw new IllegalArgumentException(message);
    }

}
