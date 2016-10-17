package restspringServer.tests;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import restspring.model.Municipio;

public class SpringRestTestClient {

	public static final String REST_SERVICE_URI = "http://localhost:8081/restspringServer";
	
	/* GET */
	@SuppressWarnings("unchecked")
	private static void listAllMunicipios(){
		System.out.println("Listando todos os municipios...");
		
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> municipiosMap = restTemplate.getForObject(REST_SERVICE_URI+"/municipio/", List.class);
		
		if(municipiosMap!=null){
			for(LinkedHashMap<String, Object> map : municipiosMap){
	            System.out.println("Municipio : id="+map.get("id")+", Nome="+map.get("nome"));
	        }
		}else{
			System.out.println("Não existe municipios...");
		}
	}
	
	/* GET */
	private static void getMunicipio(){
		System.out.println("Testando pegar um Municipio...");
		RestTemplate restTemplate = new RestTemplate();
        Municipio municipio = restTemplate.getForObject(REST_SERVICE_URI+"/municipio/1", Municipio.class);
        System.out.println(municipio);
	}
	
	/* POST */
    private static void createMunicipio() {
		System.out.println("Testando criar um Municipio...");
    	RestTemplate restTemplate = new RestTemplate();
        Municipio municipio = new Municipio();
        municipio.setNome("Teste");
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/municipio/", municipio, Municipio.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updateMunicipio() {
		System.out.println("Testando atualizar um Municipio...");
        RestTemplate restTemplate = new RestTemplate();
        Municipio municipio = new Municipio();
        municipio.setNome("São Paulo 2");
        restTemplate.put(REST_SERVICE_URI+"/municipio/32", municipio);
        System.out.println(municipio);
    }

    /* DELETE */
    private static void deleteMunicipio() {
		System.out.println("Testando excluir um Municipio...");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/municipio/32");
    }

    public static void main(String args[]){
		listAllMunicipios();
		getMunicipio();
		createMunicipio();
		listAllMunicipios();
		updateMunicipio();
		listAllMunicipios();
		deleteMunicipio();
		listAllMunicipios();
    }
}