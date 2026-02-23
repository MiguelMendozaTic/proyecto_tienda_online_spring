package com.tienda.modelo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordenes")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, unique = true)
    private String numeroOrden;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoOrden estado;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalDescuentoProductos = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal descuentoCupon = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_id")
    private List<DetalleOrden> detalles;

    @ManyToOne
    @JoinColumn(name = "cupon_id", nullable = true)
    private Cupon cupon;

    @Column(columnDefinition = "TEXT")
    private String notas;

    public enum EstadoOrden {
        pendiente,
        confirmada,
        pagada,
        cancelada
    }

    // Constructores
    public Orden() {
        this.fecha = LocalDateTime.now();
        this.estado = EstadoOrden.pendiente;
    }

    public Orden(Usuario usuario, String numeroOrden) {
        this.usuario = usuario;
        this.numeroOrden = numeroOrden;
        this.fecha = LocalDateTime.now();
        this.estado = EstadoOrden.pendiente;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotalDescuentoProductos() {
        return totalDescuentoProductos;
    }

    public void setTotalDescuentoProductos(BigDecimal totalDescuentoProductos) {
        this.totalDescuentoProductos = totalDescuentoProductos;
    }

    public BigDecimal getDescuentoCupon() {
        return descuentoCupon;
    }

    public void setDescuentoCupon(BigDecimal descuentoCupon) {
        this.descuentoCupon = descuentoCupon;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<DetalleOrden> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrden> detalles) {
        this.detalles = detalles;
    }

    public Cupon getCupon() {
        return cupon;
    }

    public void setCupon(Cupon cupon) {
        this.cupon = cupon;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "id=" + id +
                ", numeroOrden='" + numeroOrden + '\'' +
                ", fecha=" + fecha +
                ", estado=" + estado +
                ", total=" + total +
                '}';
    }
}
