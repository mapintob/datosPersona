
package datos_persona;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Scanner;

public class Datos_persona {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion=0;
        Scanner leer = new Scanner(System.in);
        String rut,nombre, fecha;         
        int edad, agregado, activo;
        
        System.out.println("Ingrese una de las opciones siguientes: ");
        do{
            System.out.println("1.- Agregar Persona.");
            System.out.println("2.- Eliminar Persona.");
            System.out.println("3.- Actualizar Persona.");
            System.out.println("4.- Salir");
            opcion = leer.nextInt();
            leer.nextLine();
            
            switch(opcion){
                case 1:
                {
                 try {
                    System.out.println("Ingrese datos de la persona: ");
                    System.out.println("Rut de la persona: ");
                    rut = leer.nextLine();                    
                    System.out.println("Nombre de la persona: ");
                    nombre = leer.nextLine();
                    System.out.println("Fecha Nacimiento de la persona: ");
                    fecha = leer.nextLine();                    

                    System.out.println("Edad de la persona: ");
                    edad = leer.nextInt();                   
                    activo = 1;
                    agregado = 0;
                    
                    String connectionUrl = "jdbc:sqlserver://192.168.50.190;databaseName=Mpinto_SBD;user=Mpinto_SQL;password=****;";
                    Connection conect1 = DriverManager.getConnection(connectionUrl);
                    System.out.println("Conectado.");            
                        
                    CallableStatement cstmt = conect1.prepareCall("{call dbo.agregarPersona(?,?,?,?,?,?)}");                      
                    cstmt.setString(1, rut);                     
                    cstmt.setString(2, nombre);                                         
                    cstmt.setString(3, fecha);                     
                    cstmt.setInt(4, edad);
                    cstmt.setInt(5, activo);                    
                    cstmt.registerOutParameter(6, java.sql.Types.INTEGER);  
                    cstmt.execute();  
                    
                    System.out.println("Resultado (1 correcto y 0 incorrecto ): " + cstmt.getInt(6));
                        
                    }catch (Exception ex){
                        System.out.println("Error."+ ex.getMessage());
                    } 
                 break;
                }
                case 2:
                {
                    
                 try {
                    System.out.println("Ingrese Rut de la persona a eliminar: ");                    
                    rut = leer.nextLine();                    
                                        
                    String connectionUrl = "jdbc:sqlserver://192.168.50.190;databaseName=Mpinto_SBD;user=Mpinto_SQL;password=******;";
                    Connection conect1 = DriverManager.getConnection(connectionUrl);
                    System.out.println("Conectado.");            
                        
                    CallableStatement cstmt = conect1.prepareCall("{call dbo.eliminarPersona(?,?)}");                      
                    cstmt.setString(1, rut);
                    cstmt.registerOutParameter(2, java.sql.Types.INTEGER);  
                    cstmt.execute();  
                    
                    System.out.println("Resultado (1 correcto y 0 incorrecto ): " + cstmt.getInt(2));
                        
                    }catch (Exception ex){
                        System.out.println("Error."+ ex.getMessage());
                    }
                    break;
                }
                case 3:
                {
                    System.out.println("ingrese los datos de la persona a actualizar");
                    System.out.println("llamada al procdeimiento almacenado");
                    break;
                }
                case 4:
                {
                    System.out.println("saliendo de la aplicacion");
                    opcion = 0;
                    break;
                }   
            }
            
        }while(opcion != 0);
    }
    
}
