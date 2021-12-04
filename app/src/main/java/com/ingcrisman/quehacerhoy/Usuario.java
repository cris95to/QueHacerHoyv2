package com.ingcrisman.quehacerhoy;

public class Usuario {
    //1 paso: definir las variables a utilizar
    int id;
    String Nombre, Apellidos, Usuario, Password, Email;

    //2 paso: crear metodos constructores
    public Usuario() {
    }

    //3 paso: generar getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    /*public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }*/


    //4 paso: generar el metodo string

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", Apellidos='" + Apellidos + '\'' +
                ", Usuario='" + Usuario + '\'' +
                ", Password='" + Password + '\'' +
                //", Email='" + Email + '\'' +
                '}';
    }
    //5 paso: crear un metodo llamado isNull que va a servir para validar los campos y determinar si estan vacios o no
    public boolean isNull(){
        if(Nombre.equals("")&&Apellidos.equals("")&&Usuario.equals("")&&Password.equals("")){
            return false;
        }else{
            return true;
        }
    }

    //6 paso: crear otro constructor con las variables de tipo string

    public Usuario(String nombre, String apellidos, String usuario, String password) {
        Nombre = nombre;
        Apellidos = apellidos;
        Usuario = usuario;
        Password = password;
        //Email = email;
    }
}
