package co.edu.ff.orders.configuration;

import co.edu.ff.orders.product.domain.*;
import co.edu.ff.orders.product.ecxeptions.ProductException;
import co.edu.ff.orders.serialization.NumberValueAdapter;
import co.edu.ff.orders.user.domain.Password;
import co.edu.ff.orders.user.domain.Username;
import co.edu.ff.orders.user.exceptions.UserException;
import co.edu.ff.orders.serialization.StringValueAdapter;
import co.edu.ff.orders.user.serialization.UsernameAdapter;
import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.util.function.Function;

@Configuration
public class GsonConfiguration {

    @Bean
    public Gson gson(){
        Function<String, Password> creator = new Function<String, Password>() {
            @Override
            public Password apply(String s) {
                return Password.of(s);
            }
        };

        Function<Number, ProductId> creatorP= s -> ProductId.fromNumber(s);

        return new GsonBuilder()
                .registerTypeAdapter(Username.class, new StringValueAdapter<>(Username::of))
                .registerTypeAdapter(Password.class, new StringValueAdapter<Password>(creator))
                .registerTypeAdapter(ProductId.class, new NumberValueAdapter<ProductId>(creatorP))
                .registerTypeAdapter(Name.class, new StringValueAdapter<>(Name::of))
                .registerTypeAdapter(Description.class, new StringValueAdapter<>(Description::of))
                .registerTypeAdapter(BasePrice.class, new NumberValueAdapter<>(BasePrice::fromNumber))
                .registerTypeAdapter(TaxRate.class, new NumberValueAdapter<>(TaxRate::fromNumber))
                .registerTypeAdapter(InventoryQueantity.class, new NumberValueAdapter<>(InventoryQueantity::fromNumber))
                //.registerTypeAdapter(ProductoperationRequest.class, new StringValueAdapter<>(ProductoperationRequest::of))
                .registerTypeAdapter(UserException.class, new JsonSerializer<UserException>() {
                    @Override
                    public JsonElement serialize(UserException src, Type typeOfSrc, JsonSerializationContext context) {
                        JsonObject jsonObject = new JsonObject();
                        String message = src.getMessage();
                        JsonPrimitive errorValue = new JsonPrimitive(message);
                        jsonObject.add("error", errorValue);
                        return jsonObject;
                    }
                })
                .registerTypeAdapter(ProductException.class, new JsonSerializer<ProductException>() {
                    @Override
                    public JsonElement serialize(ProductException src, Type typeOfSrc, JsonSerializationContext context) {
                        JsonObject jsonObject = new JsonObject();
                        String message = src.getMessage();
                        JsonPrimitive errorValue = new JsonPrimitive(message);
                        jsonObject.add("error", errorValue);
                        return jsonObject;
                    }
                })
                .create();
    }
}
