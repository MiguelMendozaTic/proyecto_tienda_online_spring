package com.tienda.repositorio;

import com.tienda.modelo.Orden;
import com.tienda.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    Optional<Orden> findByNumeroOrden(String numeroOrden);
    List<Orden> findByUsuario(Usuario usuario);
    List<Orden> findByUsuarioOrderByFechaDesc(Usuario usuario);
}
