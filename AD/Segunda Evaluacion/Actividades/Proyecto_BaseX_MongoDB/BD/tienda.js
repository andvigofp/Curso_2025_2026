use tienda;

//Insercción Colleción de Clientes
db.Clientes.insertMany([
  {
    "_id": "1",
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan.perez@gmail.com",
    "telefono": "555-1234"
  },
  {
    "_id": "2",
    "nombre": "María",
    "apellido": "Gómez",
    "email": "maria.gomez@gmail.com",
    "telefono": "555-5678"
  },
  {
    "_id": "3",
    "nombre": "Carlos",
    "apellido": "Ruiz",
    "email": "carlos.ruiz@gmail.com",
    "telefono": "555-9876"
  },
  {
    "_id": "4",
    "nombre": "Laura",
    "apellido": "Fernández",
    "email": "laura.fernandez@gmail.com",
    "telefono": "555-4321"
  }
])

//Insercción Colleción de Carrito
db.Carrito.insertMany([
  {
    "carrito_id": "1",
    "cliente_id": "1",
    "productos": [
      {
        "producto_id": "1",
        "nombre": "Laptop HP Pavilion",
        "cantidad": 1,
        "precio": 799.99
      },
      {
        "producto_id": "11",
        "nombre": "Altavoces Bluetooth JBL Flip 5",
        "cantidad": 2,
        "precio": 99.99
      }
    ]
  },
  {
    "carrito_id": "2",
    "cliente_id": "2",
    "productos": [
      {
        "producto_id": "2",
        "nombre": "Smartphone Samsung Galaxy S21",
        "cantidad": 1,
        "precio": 899.99
      },
      {
        "producto_id": "13",
        "nombre": "Teclado Mecánico RGB Logitech G Pro X",
        "cantidad": 1,
        "precio": 149.99
      }
    ]
  },
  {
    "carrito_id": "3",
    "cliente_id": "3",
    "productos": [
      {
        "producto_id": "8",
        "nombre": "Impresora 3D Creality Ender 3",
        "cantidad": 1,
        "precio": 249.99
      }
    ]
  },
  {
    "carrito_id": "4",
    "cliente_id": "4",
    "productos": [
      {
        "producto_id": "15",
        "nombre": "Monitor Gaming ASUS ROG Swift PG279Q",
        "cantidad": 1,
        "precio": 799.99
      },
      {
        "producto_id": "4",
        "nombre": "Auriculares Inalámbricos Sony WH-1000XM4",
        "cantidad": 1,
        "precio": 299.99
      }
    ]
  }
])

//Insercción Colleción de Pedidos
db.Pedidos.insertMany([
  {
    "pedido_id": "1",
    "cliente_id": "1",
    "fecha": "2026-02-01",
    "estado": "Entregado",
    "total": 1099.98,
    "productos": [
      {
        "producto_id": "1",
        "nombre": "Laptop HP Pavilion",
        "cantidad": 1,
        "precio": 799.99
      },
      {
        "producto_id": "4",
        "nombre": "Auriculares Inalámbricos Sony WH-1000XM4",
        "cantidad": 1,
        "precio": 299.99
      }
    ]
  },
  {
    "pedido_id": "2",
    "cliente_id": "2",
    "fecha": "2026-02-02",
    "estado": "Enviado",
    "total": 1299.99,
    "productos": [
      {
        "producto_id": "5",
        "nombre": "Televisor Samsung QLED Q80T",
        "cantidad": 1,
        "precio": 1299.99
      }
    ]
  },
  {
    "pedido_id": "3",
    "cliente_id": "3",
    "fecha": "2026-02-03",
    "estado": "Procesando",
    "total": 499.99,
    "productos": [
      {
        "producto_id": "7",
        "nombre": "Aspiradora Robot iRobot Roomba 960",
        "cantidad": 1,
        "precio": 499.99
      }
    ]
  },
  {
    "pedido_id": "4",
    "cliente_id": "4",
    "fecha": "2026-02-04",
    "estado": "Entregado",
    "total": 699.99,
    "productos": [
      {
        "producto_id": "14",
        "nombre": "Cafetera Espresso Breville Barista Express",
        "cantidad": 1,
        "precio": 699.99
      }
    ]
  }
])
