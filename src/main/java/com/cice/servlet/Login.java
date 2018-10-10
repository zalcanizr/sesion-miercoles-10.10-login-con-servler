package com.cice.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.*;

public class Login extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       //params. que me tienen que llegar de la peticion
        String user= req.getParameter("user");
        String pass= req.getParameter("pass");


        //conexion a la bbdd
        try {

            Class.forName("com.mysql.jdbc.Driver");

            //creamos la conexion a la bbdd
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:8889/login","root","root");

            //con la conexion generamos el statement
            Statement statement=connection.createStatement();

            //la consulta que le enviamos a la bbdd
            String sql="SELECT * FROM usuarios WHERE user='"+user+"' AND pass='"+pass+"'";

            //usamos el statemtn para hacer un execute query que nos devuelve un result set con los resultados de la busqeda
            ResultSet resultado=statement.executeQuery(sql);

            //ahora con el resultado preguntamos si tiene un first i.e. si tiene una coincidencia
            if(resultado.first()){
                resp.getWriter().print("Usuario logueado correctamente.");
            }else{
                resp.getWriter().print("Usuario o contraseña no coinciden.");
            }


            //cerramos
            resultado.close();
            statement.close();
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



}
