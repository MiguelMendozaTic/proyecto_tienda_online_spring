package com.tienda.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pasarela.pago")
public class PasarelaPagoConfig {

    private Yape yape = new Yape();
    private Plin plin = new Plin();
    private Paypal paypal = new Paypal();

    public static class Yape {
        private String telefono = "999 999 999";
        private String apiKey = "demo-key";
        private String apiUrl = "https://api.yape.pe";
        private boolean habilitado = true;

        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
        
        public String getApiKey() { return apiKey; }
        public void setApiKey(String apiKey) { this.apiKey = apiKey; }
        
        public String getApiUrl() { return apiUrl; }
        public void setApiUrl(String apiUrl) { this.apiUrl = apiUrl; }
        
        public boolean isHabilitado() { return habilitado; }
        public void setHabilitado(boolean habilitado) { this.habilitado = habilitado; }
    }

    public static class Plin {
        private String telefono = "999 999 999";
        private String apiKey = "demo-key";
        private String apiUrl = "https://api.plin.pe";
        private boolean habilitado = true;

        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
        
        public String getApiKey() { return apiKey; }
        public void setApiKey(String apiKey) { this.apiKey = apiKey; }
        
        public String getApiUrl() { return apiUrl; }
        public void setApiUrl(String apiUrl) { this.apiUrl = apiUrl; }
        
        public boolean isHabilitado() { return habilitado; }
        public void setHabilitado(boolean habilitado) { this.habilitado = habilitado; }
    }

    public static class Paypal {
        private String businessEmail = "plazachina@paypal.com";
        private String clientId = "demo-client-id";
        private String clientSecret = "demo-client-secret";
        private String apiUrl = "https://www.paypal.com";
        private boolean habilitado = true;

        public String getBusinessEmail() { return businessEmail; }
        public void setBusinessEmail(String businessEmail) { this.businessEmail = businessEmail; }
        
        public String getClientId() { return clientId; }
        public void setClientId(String clientId) { this.clientId = clientId; }
        
        public String getClientSecret() { return clientSecret; }
        public void setClientSecret(String clientSecret) { this.clientSecret = clientSecret; }
        
        public String getApiUrl() { return apiUrl; }
        public void setApiUrl(String apiUrl) { this.apiUrl = apiUrl; }
        
        public boolean isHabilitado() { return habilitado; }
        public void setHabilitado(boolean habilitado) { this.habilitado = habilitado; }
    }

    public Yape getYape() { return yape; }
    public void setYape(Yape yape) { this.yape = yape; }

    public Plin getPlin() { return plin; }
    public void setPlin(Plin plin) { this.plin = plin; }

    public Paypal getPaypal() { return paypal; }
    public void setPaypal(Paypal paypal) { this.paypal = paypal; }
} 