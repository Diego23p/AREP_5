package edu.escuelaing.arep.Docker;

import com.mongodb.BasicDBObject;
import java.util.Date;

/**
 * Modelo de datos
 * @author Diego23p
 */
public class Data {

        private String cadena;
        private Date fecha;

	public Data() {
	}

        /**
         * Crea in objeto Data
         * @param cadena cadena a insertar
         * @param fecha recha de insersión de la cadena
         */
	public Data(String cadena, Date fecha) {
		this.cadena = cadena;
                this.fecha = fecha;
	}

        /** 
         * Transforma un objecto MongoDB a un objeto Java
         * @param dBObject objeto MongoDB 
         */
	public Data(BasicDBObject dBObject) {
		this.cadena = dBObject.getString("cadena");
		this.fecha = dBObject.getDate("fecha");
	}

        /**
         * Transforma de objeto Mongo a objeto Data Java 
         * @return objeto Mongo creado
         */
	public BasicDBObject toDBObjectData() {

		// Creamos una instancia BasicDBObject
		BasicDBObject dBObjectData = new BasicDBObject();

		dBObjectData.append("cadena", this.getCadena());
		dBObjectData.append("fecha", this.getFecha());

		return dBObjectData;
	}

	public String getCadena() {
		return cadena;
	}

	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setEdad(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Mensaje: " + this.getCadena() + " Fecha: " + this.getFecha();
	}
}
