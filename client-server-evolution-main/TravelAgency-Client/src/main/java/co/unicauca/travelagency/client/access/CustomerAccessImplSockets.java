
package co.unicauca.travelagency.client.access;

import co.unicauca.travelagency.commons.infra.Protocol;
import co.unicauca.travelagency.commons.domain.Customer;
import co.unicauca.travelagency.commons.infra.JsonError;
import co.unicauca.travelagency.client.infra.TravelAgencySocket;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servicio de Cliente. Permite hacer el CRUD de clientes solicitando los
 * servicios con la aplicación server. Maneja los errores devueltos
 *
 * @author Libardo Pantoja, Julio A. Hurtado
 */
public class CustomerAccessImplSockets implements ICustomerAccess {
    /**
     * El servicio utiliza un socket para comunicarse con la aplicación server
     */
    private TravelAgencySocket mySocket;

    public CustomerAccessImplSockets() {
        mySocket = new TravelAgencySocket();
    }

    /**
     * Busca un Customer. Utiliza socket para pedir el servicio al servidor
     *
     * @param id cedula del cliente
     * @return Objeto Customer
     * @throws Exception cuando no pueda conectarse con el servidor
     */
    @Override
    public Customer findCustomer(String id) throws Exception {
        String jsonResponse = null;
        String ruta =  "/customer/findById/"+id;
        String requestJson ="";
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest("GET",requestJson,ruta);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CustomerAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(CustomerAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Encontró el customer
                Customer customer = jsonToCustomer(jsonResponse);
                Logger.getLogger(CustomerAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+jsonResponse.toString()+ ")");
                return customer;
            }
        }

    }

    /**
     * Crea un Customer. Utiliza socket para pedir el servicio al servidor
     *
     * @param customer cliente de la agencia de viajes
     * @return devuelve la cedula del cliente creado
     * @throws Exception error crear el cliente
     */
    @Override
    public String createCustomer(Customer customer) throws Exception {
        String jsonResponse = null;
        String requestJson = "";
        String ruta = "/customers/save";
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest("POST","{\n" +
"    \"idCustomer\":\"98000011\",\n" +
"    \"name\":\"Carlos\",\n" +
"    \"lastName\": \"Pantoja\",\n" +
"    \"address\": \"Santa Barbar Popayan\",\n" +
"    \"phone\":\"3141257845\",\n" +
"    \"email\":\"carlos@gmail.com\",\n" +
"    \"sex\":\"Masculino\"\n" +
"}",ruta);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(CustomerAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {

            if (jsonResponse.contains("error")) {
                //Devolvió algún error                
                Logger.getLogger(CustomerAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            } else {
                //Agregó correctamente, devuelve la cedula del customer 
                return customer.getId();
            }

        }

    }
    /**
     * Extra los mensajes de la lista de errores
     * @param jsonResponse lista de mensajes json
     * @return Mensajes de error
     */
    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }

    /**
     * Convierte el jsonError a un array de objetos jsonError
     *
     * @param jsonError
     * @return objeto MyError
     */
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }

    /**
     * Crea una solicitud json para ser enviada por el socket
     *
     *
     * @param idCustomer identificación del cliente
     * @return solicitud de consulta del cliente en formato Json, algo como:
     * {"resource":"customer","action":"get","parameters":[{"name":"id","value":"98000001"}]}
     */
    private String doFindCustomerRequestJson(String idCustomer) throws IOException, InterruptedException {

        Protocol protocol = new Protocol();
        protocol.setResource("customer");
        protocol.setAction("get");
        protocol.addParameter("id", idCustomer);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }

    /**
     * Crea la solicitud json de creación del customer para ser enviado por el
     * socket
     *
     * @param customer objeto customer
     * @return devulve algo como:
     * {"resource":"customer","action":"post","parameters":[{"name":"id","value":"980000012"},{"name":"fistName","value":"Juan"},...}]}
     */
    private String doCreateCustomerRequestJson(Customer customer) {

        Protocol protocol = new Protocol();
        protocol.setResource("customer");
        protocol.setAction("post");
        protocol.addParameter("id", customer.getId());
        protocol.addParameter("fistName", customer.getName());
        protocol.addParameter("lastName", customer.getLastName());
        protocol.addParameter("address", customer.getAddress());
        protocol.addParameter("email", customer.getEmail());
        protocol.addParameter("gender", customer.getSex());
        protocol.addParameter("mobile", customer.getPhone());

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    /**
     * Convierte jsonCustomer, proveniente del server socket, de json a un
     * objeto Customer
     *
     * @param jsonCustomer objeto cliente en formato json
     */
    private Customer jsonToCustomer(String jsonCustomer) {
        Gson gson = new Gson();
        Customer customer = gson.fromJson(jsonCustomer, Customer.class);
        return customer;

    }

}
