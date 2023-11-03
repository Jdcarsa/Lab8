package co.unicauca.travelagency.commons.domain;

import java.util.Date;

/**
 * Cliente de la agencia de viajes
 *
 * @author Libardo, Julio
 */
public class Customer {

    /**
     * Cedula
     */
    private String id;
    /**
     * Nombres
     */
    private String name;
    /**
     * Apellidos
     */
    private String lastName;
    /**
     * Dirección de residencia
     */
    private String address;
    /**
     * Teléfono Móvil
     */
    private String phone;
    /**
     * Email
     */
    private String email;
    /**
     * Sexo
     */
    private String sex;

    /**
     * Constructor parametrizado
     *
     * @param id cedula
     * @param firstName nombres
     * @param lastName apellidos
     * @param address dirección
     * @param mobile celular
     * @param email email
     * @param gender sexo
     */
    public Customer(String id, String firstName, String lastName, String address, String mobile, String email, String gender) {
        this.id = id;
        this.name = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = mobile;
        this.email = email;
        this.sex = gender;
    }

    /**
     * Constructor por defecto
     */
    public Customer() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    
    /**
     * Getters and Setters
     *
     * @return
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
