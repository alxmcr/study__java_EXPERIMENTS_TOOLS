package com.alxmcr.capa.rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alxmcr.capa.dao.TareaDao;
import com.alxmcr.capa.dto.Tarea;
import com.alxmcr.constantes.ConstanteDatoConfigBD;
import com.alxmcr.constantes.ConstanteMensajes;

@Path("/tareas")
public class ServicioBase {
	private TareaDao dao = new TareaDao(
			ConstanteDatoConfigBD.USER_BD_DEVELOPER,
			ConstanteDatoConfigBD.PASS_BD_DEVELOPER,
			ConstanteDatoConfigBD.IP_SERVIDOR_LOCAL,
			ConstanteDatoConfigBD.PORT_BD);

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Tarea> listarTareas() {
		List<Tarea> salida = null;

		try {
			salida = dao.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (salida != null) {
			for (Tarea tarea : salida) {
				System.out.println(tarea);
			}
		} else {
			System.out.println(ConstanteMensajes.SIN_RESULTADOS);
		}

		return salida;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Tarea newTarea(Tarea tarea) {
		Tarea salida = dao.create(tarea);

		if (salida == null) {
			System.out.println(ConstanteMensajes.ERROR_ADICION);
		}

		return salida;
	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Tarea getTarea(@PathParam("id") String id) {
		Tarea salida = null;

		try {
			if (id != null && id.length() > 0) {
				System.out.println("id -> " + id);

				salida = dao.findById(id);

				if (salida == null) {
					System.out
							.println(ConstanteMensajes.ERROR_REGISTRO_NO_ENCONTRADO);
				}

				System.out.println("salida->" + salida);

			} else {
				System.out.println(ConstanteMensajes.ID_SIN_DEFINIR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Tarea edicionTarea(Tarea nueva, @PathParam("id") String id) {
		Tarea salida = null;

		try {
			if (id != null && id.length() > 0) {
				Tarea anterior = dao.findById(id);

				if (anterior != null) {
					boolean resultado = dao.edit(nueva, anterior);

					if (resultado) {
						salida = dao.findById(id);
					} else {
						System.out
								.println(ConstanteMensajes.ERROR_ACTUALIZACION);
					}
				} else {
					System.out.println(ConstanteMensajes.NO_EXISTE_REGISTRO);
				}
			} else {
				System.out.println(ConstanteMensajes.ID_SIN_DEFINIR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salida;
	}

	@DELETE
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean eliminacionTarea(Tarea tarea, @PathParam("id") String id) {
		System.out
				.println("_________________estamos en el metodo: 'eliminacionTarea'_________________");

		System.out.println("id -> " + id);
		System.out.println("Tarea -> " + tarea);
		System.out.println("____________________________________");
		return true;
	}

}
