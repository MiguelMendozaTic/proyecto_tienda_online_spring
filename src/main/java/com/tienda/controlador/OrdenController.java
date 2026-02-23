package com.tienda.controlador;

import com.tienda.modelo.Orden;
import com.tienda.modelo.Usuario;
import com.tienda.servicio.OrdenService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    /**
     * Muestra la página de confirmación de orden antes del pago.
     * @param id El ID de la orden.
     * @param session La sesión HTTP.
     * @return ModelAndView con la página de confirmación de orden.
     */
    @GetMapping("/orden/{id}")
    public ModelAndView mostrarOrden(@PathVariable Long id, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
        
        if (usuarioAutenticado == null) {
            usuarioAutenticado = (Usuario) session.getAttribute("usuarioLogueado");
        }

        if (usuarioAutenticado == null) {
            mv.setViewName("redirect:/login");
            return mv;
        }

        Optional<Orden> ordenOptional = ordenService.obtenerPorId(id);

        if (ordenOptional.isEmpty()) {
            mv.setViewName("error");
            mv.addObject("mensaje", "Orden no encontrada.");
            return mv;
        }

        Orden orden = ordenOptional.get();

        // Verificar que la orden pertenece al usuario autenticado
        if (!orden.getUsuario().getId().equals(usuarioAutenticado.getId())) {
            mv.setViewName("error");
            mv.addObject("mensaje", "No tienes permisos para acceder a esta orden.");
            return mv;
        }

        mv.setViewName("orden/confirmacion");
        mv.addObject("orden", orden);
        mv.addObject("usuario", usuarioAutenticado);
        
        return mv;
    }

    /**
     * Muestra el historial de órdenes del usuario autenticado (HU-29).
     * @param session La sesión HTTP.
     * @return ModelAndView con la página del historial de órdenes.
     */
    @GetMapping("/mis-ordenes")
    public ModelAndView mostrarHistorialOrdenes(HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
        
        if (usuarioAutenticado == null) {
            usuarioAutenticado = (Usuario) session.getAttribute("usuarioLogueado");
        }

        if (usuarioAutenticado == null) {
            mv.setViewName("redirect:/login");
            return mv;
        }

        // Obtener todas las órdenes del usuario
        List<Orden> ordenes = ordenService.obtenerOrdenesPorUsuario(usuarioAutenticado);

        // Calcular estadísticas
        long ordenesCount = ordenes.size();
        long ordenesPendientesCount = ordenes.stream()
                .filter(o -> o.getEstado() == Orden.EstadoOrden.pendiente)
                .count();
        long ordenesPagadasCount = ordenes.stream()
                .filter(o -> o.getEstado() == Orden.EstadoOrden.pagada)
                .count();

        mv.setViewName("orden/historial");
        mv.addObject("ordenes", ordenes);
        mv.addObject("usuario", usuarioAutenticado);
        mv.addObject("totalOrdenes", ordenesCount);
        mv.addObject("ordenesPendientesCount", ordenesPendientesCount);
        mv.addObject("ordenesPagadasCount", ordenesPagadasCount);
        
        return mv;
    }
}
