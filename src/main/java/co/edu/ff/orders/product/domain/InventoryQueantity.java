package co.edu.ff.orders.product.domain;

import co.edu.ff.orders.common.Preconditions;
import co.edu.ff.orders.serialization.NumberSerializable;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

@Value(staticConstructor = "of")
public class InventoryQueantity implements NumberSerializable {
    public static InventoryQueantity fromNumber(Number number) {
        return new InventoryQueantity(number.intValue());
    }

    Integer value;

    private InventoryQueantity(int value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value >= 0);
        this.value = value;
    }


    @Override
    public Integer valueOf() {
        return value;
    }
}
