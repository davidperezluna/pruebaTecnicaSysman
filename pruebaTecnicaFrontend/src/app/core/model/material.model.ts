
export class Material {
    idCiudad: number;
    nombre: string;
    tipo: string;
    descripcion: string;
    fechaCompra: Date;
    fechaVenta: Date;
    estado: string;
    id?: number;
  
    constructor(
        nombre: string,
        tipo: string,
        fechaCompra: Date,
        fechaVenta: Date,
        descripcion: string,
        estado: string,
        idCiudad: number,
        id?: number,
    ) {
      this.nombre = nombre;
      this.tipo = tipo;
      this.descripcion = descripcion;
      this.estado = estado;
      this.fechaCompra = fechaCompra;
      this.fechaVenta = fechaVenta;
      this.idCiudad = idCiudad;
      if (id) {
        this.id = id;
      }
    }
  }   