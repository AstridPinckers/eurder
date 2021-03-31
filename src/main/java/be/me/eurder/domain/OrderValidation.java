package be.me.eurder.domain;

import be.me.eurder.domain.mock_data.ItemData;
import be.me.eurder.domain.mock_data.UserData;
import be.me.eurder.domain.pojos.ItemGroup;
import be.me.eurder.domain.pojos.Order;
import be.me.eurder.infrastructure.exceptions.NotFoundInDatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderValidation {

    static Logger logger = LoggerFactory.getLogger(OrderValidation.class);

    public static void assertValidOrder(Order order) {
        if (order == null) {
            illegalArgument("Order cannot be empty");
        }
        if (!UserData.getCustomerList().contains(order.getCustomer())) {
            logger.error("user not found in database: " + order.getCustomer().getUuid());
            throw new NotFoundInDatabaseException("customer");
        }
        if (order.getItemGroupSet().size() == 0) {
            illegalArgument("Order must contain items");
        }
        for(ItemGroup itemGroup : order.getItemGroupSet()){
            assertValidItemGroup(itemGroup);
        }
    }

    private static void assertValidItemGroup(ItemGroup itemGroup) {
        if (itemGroup.getAmount() < 1) {
            illegalArgument("The minimum order amount per item is one");
        }
    }


    private static void illegalArgument(String message) {
        logger.error(message);
        throw new IllegalArgumentException(message);
    }
}
