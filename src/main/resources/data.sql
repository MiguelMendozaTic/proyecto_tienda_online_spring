-- Insertar cupón solo si la tabla existe y el cupón no existe
INSERT INTO cupon (nombre_clave, porcentaje_descuento, activo, fecha_expiracion, descripcion)
SELECT 'BIENVENIDA10', 10, true, NULL, 'Cupón de bienvenida con 10% de descuento'
WHERE EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'CUPON')
  AND NOT EXISTS (SELECT 1 FROM cupon WHERE nombre_clave = 'BIENVENIDA10');