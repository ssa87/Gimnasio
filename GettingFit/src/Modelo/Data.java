package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Data 
{
    public static void main(String[] args)
    {
        try 
        {
            /*Conexión a base de datos*/
            Connection conexion = Conexion.conexionPostgresql();
            Statement sentencia = conexion.createStatement();       
            /*Borrado de tablas y tipos de la base de datos*/
            String drop_tipo_profesor = "drop type if exists T_Profesor cascade";
            String drop_tipo_curso = "drop type if exists T_Curso cascade";
            String drop_tipo_horario = "drop type if exists T_Horario cascade";
            String drop_tipo_grupo = "drop type if exists T_Grupo cascade";
            sentencia.execute(drop_tipo_profesor);
            sentencia.execute(drop_tipo_curso);
            sentencia.execute(drop_tipo_horario);
            sentencia.execute(drop_tipo_grupo);           
            /*Creación de tipos*/
            String tipo_Profesor = "create type T_Profesor as (Id numeric(8), Nombre varchar(10), Apellido1 varchar(10), Apellido2 varchar(10), Dni varchar(9), FechaNacimiento date, Sexo varchar(6), Telefono numeric(9), Direccion varchar(30), FechaAlta date, FechaBaja date)";
            String tipo_Curso = "create type T_Curso as (Id numeric(8), Nombre varchar(10), Descripcion varchar(50), Nivel varchar(10), Precio float, Duracion numeric(3), FechaAlta date, FechaBaja date)";
            String tipo_Horario = "create type T_Horario as (Id numeric(8), Dia varchar(10), HoraComienzo date, HoraFin date)";
            String tipo_Grupo = "create type T_Grupo as (Id numeric(8), CursoId numeric(8), ProfesorId numeric(8), HorarioId numeric(8), NumeroPlazas numeric(3))";
            /*Ejecución de sentencias de creación de tipos*/
            sentencia.execute(tipo_Profesor);   
            sentencia.execute(tipo_Curso);
            sentencia.execute(tipo_Horario);
            sentencia.execute(tipo_Grupo);
            /*Creación de tablas*/
            String tabla_Profesores = "create table Profesores of T_Profesor (primary key(Id), unique(Dni))";
            String tabla_Cursos = "create table Cursos of T_Curso (primary key(Id))";
            String tabla_Horarios = "create table Horarios of T_Horario (primary key(Id))";
            String tabla_Grupos = "create table Grupos of T_Grupo (primary key(Id))";
            /*Ejecución de sentencias de creación de tablas*/
            sentencia.execute(tabla_Profesores);
            sentencia.execute(tabla_Cursos);
            sentencia.execute(tabla_Horarios);
            sentencia.execute(tabla_Grupos);
            /*Cerramos la conexion*/
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) 
        {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        
}
