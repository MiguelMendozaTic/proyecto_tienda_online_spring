package com.tienda.servicio;

import com.tienda.modelo.Pago;
import com.tienda.config.PasarelaPagoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Map;
import java.util.HashMap;

@Service
public class PasarelaPagoService {

    @Autowired
    private PasarelaPagoConfig config;

    /**
     * Procesa el pago a través de la pasarela correspondiente
     */
    public Map<String, Object> procesarPago(Pago.MetodoPago metodoPago, BigDecimal monto, String numeroBoleta) {
        Map<String, Object> resultado = new HashMap<>();
        
        try {
            switch (metodoPago) {
                case yape:
                    return procesarPagoYape(monto, numeroBoleta);
                case plin:
                    return procesarPagoPlin(monto, numeroBoleta);
                case paypal:
                    return procesarPagoPayPal(monto, numeroBoleta);
                default:
                    resultado.put("exitoso", false);
                    resultado.put("mensaje", "Método de pago no soportado");
                    return resultado;
            }
        } catch (Exception e) {
            resultado.put("exitoso", false);
            resultado.put("mensaje", "Error en el procesamiento del pago: " + e.getMessage());
            return resultado;
        }
    }

    /**
     * Procesa pago a través de Yape
     */
    private Map<String, Object> procesarPagoYape(BigDecimal monto, String numeroBoleta) {
        Map<String, Object> resultado = new HashMap<>();
        
        // Simulación de integración con API de Yape
        try {
            // Aquí iría la integración real con la API de Yape
            // Por ahora simulamos el proceso
            
            // Generar QR code único para esta transacción
            String qrCode = generarQRCodeYape(monto, numeroBoleta);
            
            resultado.put("exitoso", true);
            resultado.put("qrCode", qrCode);
            resultado.put("numeroTelefono", config.getYape().getTelefono());
            resultado.put("referencia", numeroBoleta);
            resultado.put("monto", monto);
            resultado.put("mensaje", "QR generado exitosamente");
            
        } catch (Exception e) {
            resultado.put("exitoso", false);
            resultado.put("mensaje", "Error al generar QR de Yape: " + e.getMessage());
        }
        
        return resultado;
    }

    /**
     * Procesa pago a través de Plin
     */
    private Map<String, Object> procesarPagoPlin(BigDecimal monto, String numeroBoleta) {
        Map<String, Object> resultado = new HashMap<>();
        
        try {
            // Simulación de integración con API de Plin
            String qrCode = generarQRCodePlin(monto, numeroBoleta);
            
            resultado.put("exitoso", true);
            resultado.put("qrCode", qrCode);
            resultado.put("numeroTelefono", config.getPlin().getTelefono());
            resultado.put("referencia", numeroBoleta);
            resultado.put("monto", monto);
            resultado.put("mensaje", "QR generado exitosamente");
            
        } catch (Exception e) {
            resultado.put("exitoso", false);
            resultado.put("mensaje", "Error al generar QR de Plin: " + e.getMessage());
        }
        
        return resultado;
    }

    /**
     * Procesa pago a través de PayPal
     */
    private Map<String, Object> procesarPagoPayPal(BigDecimal monto, String numeroBoleta) {
        Map<String, Object> resultado = new HashMap<>();
        
        try {
            // Simulación de integración con PayPal
            String paypalUrl = generarPayPalUrl(monto, numeroBoleta);
            
            resultado.put("exitoso", true);
            resultado.put("paypalUrl", paypalUrl);
            resultado.put("referencia", numeroBoleta);
            resultado.put("monto", monto);
            resultado.put("mensaje", "URL de PayPal generada exitosamente");
            
        } catch (Exception e) {
            resultado.put("exitoso", false);
            resultado.put("mensaje", "Error al generar URL de PayPal: " + e.getMessage());
        }
        
        return resultado;
    }

    /**
     * Verifica el estado de un pago
     */
    public Map<String, Object> verificarEstadoPago(String referencia, Pago.MetodoPago metodoPago) {
        Map<String, Object> resultado = new HashMap<>();
        
        try {
            // Simulación de verificación de estado
            // En producción, aquí se consultaría la API correspondiente
            
            boolean pagoConfirmado = simularVerificacionPago(referencia, metodoPago);
            
            resultado.put("exitoso", true);
            resultado.put("pagoConfirmado", pagoConfirmado);
            resultado.put("estado", pagoConfirmado ? "COMPLETADO" : "PENDIENTE");
            resultado.put("mensaje", pagoConfirmado ? "Pago confirmado" : "Pago pendiente de confirmación");
            
        } catch (Exception e) {
            resultado.put("exitoso", false);
            resultado.put("mensaje", "Error al verificar estado del pago: " + e.getMessage());
        }
        
        return resultado;
    }

    // Métodos auxiliares para simulación
    private String generarQRCodeYape(BigDecimal monto, String numeroBoleta) {
        // Generar QR usando API externa (QR Server)
        String datos = "yape://pay?phone=999999999&amount=" + monto.toString() + "&reference=" + numeroBoleta;
        return "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" + java.net.URLEncoder.encode(datos, java.nio.charset.StandardCharsets.UTF_8);
    }

    private String generarQRCodePlin(BigDecimal monto, String numeroBoleta) {
        // Generar QR usando API externa (QR Server)
        String datos = "plin://pay?phone=999999999&amount=" + monto.toString() + "&reference=" + numeroBoleta;
        return "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" + java.net.URLEncoder.encode(datos, java.nio.charset.StandardCharsets.UTF_8);
    }

    private String generarPayPalUrl(BigDecimal monto, String numeroBoleta) {
        // Simulación de URL de PayPal
        return "https://www.paypal.com/cgi-bin/webscr?cmd=_xclick&business=plazachina@paypal.com&item_name=Compra PlazaChina&amount=" + monto.toString() + "&currency_code=PEN&invoice=" + numeroBoleta;
    }

    private boolean simularVerificacionPago(String referencia, Pago.MetodoPago metodoPago) {
        // Simulación de verificación - en producción esto consultaría las APIs reales
        // Por ahora, simulamos que el pago se confirma después de un tiempo
        return Math.random() > 0.3; // 70% de probabilidad de confirmación
    }
} 