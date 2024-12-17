package br.com.alura.screenmatch;

public class ConverteDados implements IConverteDados{
    private ObjectMapper = new ObjectMapper();

    @Override
    <T> T obterDados(String json, Class<T> classe){
        try{
            return mapper.readValue(json, classe);
        } catch(JsonProcessingException e){
            throw new RuntimeException(e);
        }        
    }
}
