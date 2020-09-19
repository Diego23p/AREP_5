package edu.escuelaing.arep.Docker;

import com.mongodb.BasicDBObject;
import java.util.Date;

public class Data {

        private String cadena;
        private Date fecha;

	public Data() {
	}

	public Data(String cadena, Date fecha) {
		this.cadena = cadena;
                this.fecha = fecha;
	}

	// Transformo un objecto que me da MongoDB a un Objecto Java
	public Data(BasicDBObject dBObjectFutbolista) {
		this.cadena = dBObjectFutbolista.getString("cadena");
		this.fecha = dBObjectFutbolista.getDate("fecha");
	}

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
