// Paquete donde se ubicarán tus controladores
package com.tienda.controlador;

import com.tienda.servicio.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    
    @Autowired
    private UsuarioService usuarioService;

    /**
     * Muestra la página de login.
    */
    @GetMapping("/login")
    public String showLoginForm(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Correo o contraseña incorrectos.");
        }
        return "login";
    }

    /**
     * Cierra la sesión del usuario.
    */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        return "redirect:/";
    }

    /**
     * Verifica si un correo existe en la base de datos.
     */
    @PostMapping("/verificar-correo")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> verificarCorreo(@RequestBody Map<String, String> request) {
        String correo = request.get("correo");
        Map<String, Object> response = new HashMap<>();
        
        boolean existe = usuarioService.existsByCorreo(correo);
        response.put("existe", existe);
        response.put("mensaje", existe ? "Correo encontrado" : "El correo no está registrado");
        
        return ResponseEntity.ok(response);
    }

    /**
     * Cambia la contraseña de un usuario.
     */
    @PostMapping("/cambiar-contrasena")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> cambiarContrasena(@RequestBody Map<String, String> request) {
        String correo = request.get("correo");
        String nuevaContrasena = request.get("nuevaContrasena");
        Map<String, Object> response = new HashMap<>();
        
        boolean cambiado = usuarioService.cambiarContrasena(correo, nuevaContrasena);
        response.put("success", cambiado);
        response.put("mensaje", cambiado ? "Contraseña cambiada exitosamente" : "Error al cambiar la contraseña");
        
        return ResponseEntity.ok(response);
    }
}