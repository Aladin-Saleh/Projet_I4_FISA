import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONHandler {
    

    private ObjectMapper mapper;

    // Constructeur
    public JSONHandler()
    {
        this.mapper = new ObjectMapper();
    }
    
    
    // Lecture d'une grappe de données au format JSON
    public Map<String, Object> readJSON(String json)
    {
        try {
            Object obj              = mapper.readValue(json, Object.class);
            Map<String, Object> map = (Map<String, Object>) obj;

            System.out.println("Lecture de la grappe de données au format JSON effectuée avec succès.");
            return map;
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture de la grappe de données au format JSON. " + e.getMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ObjectMapper getMapper() {
        return this.mapper;
    }


}
