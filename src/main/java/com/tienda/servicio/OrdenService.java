package com.tienda.servicio;

import com.tienda.modelo.Orden;
import com.tienda.modelo.Usuario;
import java.util.Optional;
import java.util.List;

public interface OrdenService {
    /**
     * Crea una nueva orden a partir del carrito del usuario.
     * @param usuario El usuario que crea la orden.
     * @return La orden creada.
     */
    Orden crearOrdenDesdeCarrito(Usuario usuario);

    /**
     * Busca una orden por su número único.
     * @param numeroOrden El número de la orden.
     * @return La orden encontrada, o Optional.empty() si no existe.
     */
    Optional<Orden> buscarPorNumeroOrden(String numeroOrden);

    /**
     * Obtiene todas las órdenes de un usuario.
     * @param usuario El usuario cuyas órdenes se desean obtener.
     * @return Lista de órdenes del usuario.
     */
    List<Orden> obtenerOrdenesPorUsuario(Usuario usuario);

    /**
     * Actualiza el estado de una orden.
     * @param idOrden El ID de la orden.
     * @param estado El nuevo estado.
     */
    void actualizarEstado(Long idOrden, Orden.EstadoOrden estado);

    /**
     * Genera un número de orden único.
     * @return Número de orden único.
     */
    String generarNumeroOrden();

    /**
     * Obtiene una orden por ID.
     * @param id El ID de la orden.
     * @return La orden encontrada, o Optional.empty() si no existe.
     */
    Optional<Orden> obtenerPorId(Long id);
}
