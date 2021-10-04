package api.microservices.product.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityValidation {

    public static String formatMessage(BindingResult result) {
        List<Map<String, String >> errors = result.getFieldErrors().stream()
                .map( e -> {
                    Map<String, String > error = new HashMap<>();
                    error.put(e.getField(), e.getDefaultMessage());
                    return error;
                } ).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder().code("01").messages(errors).build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException exception){
            exception.printStackTrace();
        }
        return jsonString;
    }
}
